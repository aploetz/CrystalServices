package com.emboldensoftware.crystalservices;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Crystal {
	private String name;
	@JsonProperty("image_name")
	private String imageName;
	private String[] chakra;
	@JsonProperty("physical_attributes")
	private String physicalAttributes;
	@JsonProperty("emotional_attributes")
	private String emotionalAttributes;
	@JsonProperty("metaphysical_attributes")
	private String metaphysicalAttributes;
	private String origin;
	@JsonProperty("birth_month")
	private String birthMonth;
	@JsonProperty("zodiac_sign")
	private String zodiacSign;
	@JsonProperty("maximum_mohs_hardness")
	private Float maximumMohsHardness;
	@JsonProperty("minimum_mohs_hardness")
	private Float minimumMohsHardness;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getImageName() {
		return imageName;
	}
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public String[] getChakra() {
		return chakra;
	}
	
	public void setChakra(String[] chakra) {
		this.chakra = chakra;
	}
	
	public String getPhysicalAttributes() {
		return physicalAttributes;
	}
	
	public void setPhysicalAttributes(String physicalAttributes) {
		this.physicalAttributes = physicalAttributes;
	}
	
	public String getEmotionalAttributes() {
		return emotionalAttributes;
	}
	
	public void setEmotionalAttributes(String emotionalAttributes) {
		this.emotionalAttributes = emotionalAttributes;
	}
	
	public String getMetaphysicalAttributes() {
		return metaphysicalAttributes;
	}
	
	public void setMetaphysicalAttributes(String metaphysicalAttributes) {
		this.metaphysicalAttributes = metaphysicalAttributes;
	}
	
	public String getOrigin() {
		return origin;
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public String getBirthMonth() {
		return birthMonth;
	}
	
	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}
	
	public String getZodiacSign() {
		return zodiacSign;
	}
	
	public void setZodiacSign(String zodiacSign) {
		this.zodiacSign = zodiacSign;
	}
	
	public Float getMaximumMohsHardness() {
		return maximumMohsHardness;
	}
	
	public void setMaximumMohsHardness(Float maximumMohsHardness) {
		this.maximumMohsHardness = maximumMohsHardness;
	}
	
	public Float getMinimumMohsHardness() {
		return minimumMohsHardness;
	}
	
	public void setMinimumMohsHardness(Float minimumMohsHardness) {
		this.minimumMohsHardness = minimumMohsHardness;
	}
}
