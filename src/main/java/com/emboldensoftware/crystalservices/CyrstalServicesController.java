package com.emboldensoftware.crystalservices;

import com.datastax.astra.client.Collection;
import com.datastax.astra.client.DataAPIClient;
import com.datastax.astra.client.Database;
import com.datastax.astra.client.model.Document;
import com.datastax.astra.client.model.FindIterable;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.huggingface.HuggingFaceEmbeddingModel;
import dev.langchain4j.model.output.Response;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class CyrstalServicesController {

	static final String TOKEN = System.getenv("DB_APPLICATION_TOKEN");
	static final String API_ENDPOINT = System.getenv("DB_API_ENDPOINT");
	
	private Collection<Document> collection;
	private EmbeddingModel embeddingModel;
	
	public CyrstalServicesController() {

		// Astra DB
		DataAPIClient client = new DataAPIClient(TOKEN);
		Database dbAPI = client.getDatabase(API_ENDPOINT);
		collection = dbAPI.getCollection("crystal_data");
		
		// HuggingFace CLIP model
        embeddingModel = HuggingFaceEmbeddingModel.builder()
                .accessToken(System.getenv("HF_API_KEY"))
                .modelId("sentence-transformers/clip-ViT-B-32")
                .waitForModel(true)
                .timeout(Duration.ofSeconds(60))
                .build();
	}
	
	@GetMapping("/text/{text}")
	public ResponseEntity<List<Crystal>> getBook(@PathVariable(value="text") String searchText) {
		
		Response<Embedding> vEmbedding = embeddingModel.embed(searchText);
		float[] vector = vEmbedding.content().vector();
		FindIterable<Document> docs = collection.find(vector, 1);
		
		List<Crystal> crystals = mapDocsToCrystals(docs);
		
		return ResponseEntity.ok(crystals);
	}
	
	private List<Crystal> mapDocsToCrystals(FindIterable<Document> docs) {
		List<Crystal> returnVal = new ArrayList<Crystal>();
		
		for (Document doc : docs) {
			Crystal crystal = new Crystal();
			
			// System.out.println(doc);
			
			String crystalText = doc.getString("text");
			String[] crystalProperties = crystalText.split("\\| ",0);
			
			System.out.println(crystalText);
			
			// gemstone: Amber| alternate name: -| physical attributes: Immune system, glands| emotional attributes: Brings warmth and positivity, relieves stress| metaphysical attributes: Purifies energy, offers protection| origin: Baltic region, Dominican Republic, Myanmar| maximum mohs hardness: 2.5| minimum mohs hardness: 2

			crystal.setName(crystalProperties[0].split(":")[1]);
			crystal.setImageName(doc.getString("_id"));
			crystal.setPhysicalAttributes(crystalProperties[2].split(":")[1]);
			crystal.setEmotionalAttributes(crystalProperties[3].split(":")[1]);
			crystal.setMetaphysicalAttributes(crystalProperties[4].split(":")[1]);
			crystal.setOrigin(crystalProperties[5].split(":")[1]);
			crystal.setMaximumMohsHardness(Float.parseFloat(crystalProperties[6].split(":")[1]));
			crystal.setMinimumMohsHardness(Float.parseFloat(crystalProperties[7].split(":")[1]));
			crystal.setBirthMonth(doc.getString("birth_month"));
			crystal.setZodiacSign(doc.getString("zodiac_sign"));
			crystal.setChakra(doc.getArray("chakra", String.class));
			
			returnVal.add(crystal);
		}
	
		return returnVal;
	}
}
