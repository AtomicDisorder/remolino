package com.atomicDisorder.remolino.commons.modules;

/**
 * @author Mariano Blua
 *
 */
public class ModuleConfiguration {
	
	
	private String name;
	private String completePathToJar;
	private String completePathToMainClass;
	private ModuleTypes moduleType;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the completPathToJar
	 */
	public String getCompletePathToJar() {
		return completePathToJar;
	}
	/**
	 * @param completePathToJar the completePathToJar to set
	 */
	public void setCompletePathToJar(String completePathToJar) {
		this.completePathToJar = completePathToJar;
	}
	/**
	 * @return the completePathToMainClass
	 */
	public String getCompletePathToMainClass() {
		return completePathToMainClass;
	}
	/**
	 * @param completePathToMainClass the completePathToMainClass to set
	 */
	public void setCompletePathToMainClass(String completePathToMainClass) {
		this.completePathToMainClass = completePathToMainClass;
	}
	
	public ModuleTypes getModuleType() {
		return moduleType;
	}
	public void setModuleType(ModuleTypes moduleType) {
		this.moduleType = moduleType;
	}

	public enum ModuleTypes
	{
		Client;
	}
	
}
