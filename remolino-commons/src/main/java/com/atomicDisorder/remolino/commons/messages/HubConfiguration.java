package com.atomicDisorder.remolino.commons.messages;



public class HubConfiguration {
	private String name;
	private String completePathToJar;
	private String completePathToMainClass;
	private HubType hubType; 
	
	public enum HubType
	{
		String
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompletePathToJar() {
		return completePathToJar;
	}

	public void setCompletePathToJar(String completePathToJar) {
		this.completePathToJar = completePathToJar;
	}

	public String getCompletePathToMainClass() {
		return completePathToMainClass;
	}

	public void setCompletePathToMainClass(String completePathToMainClass) {
		this.completePathToMainClass = completePathToMainClass;
	}

	public HubType getHubType() {
		return hubType;
	}

	public void setHubType(HubType hubType) {
		this.hubType = hubType;
	}
}
