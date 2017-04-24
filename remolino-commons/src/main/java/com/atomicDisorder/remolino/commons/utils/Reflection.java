package com.atomicDisorder.remolino.commons.utils;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;

import com.atomicDisorder.remolino.commons.modules.Module;
import com.atomicDisorder.remolino.commons.modules.ModuleConfiguration;

import xeus.jcl.JarClassLoader;
import xeus.jcl.JclObjectFactory;



// TODO: CHECK
// http://mvnrepository.com/artifact/org.slf4j/jcl-over-slf4j/1.7.6
public class Reflection {

	private static Logger logger = Logger.getLogger(Reflection.class.getName());
	private static JarClassLoader jcl = new JarClassLoader();
	
	@SuppressWarnings("null")
	public static Module getModuleInstance(ModuleConfiguration moduleConfiguration)
	{
		Module newModule = null;
		
		try {
		//	if (!this.getClass().getCanonicalName().equals(module.getCompletePathToMainClass())) {
				// IF CLASS IS NOT IN A ASSOCIATED PROJECT, ITS LOADED FROM
				// JAR.
				Class<?> newClass = Class.forName(moduleConfiguration.getCompletePathToMainClass());
				Constructor<?> constructor;
		/*		if (moduleConfiguration.getModuleType().equals(ModuleConfiguration.ModuleTypes.Controller)) {
					constructor = newClass.getConstructor();
					newModule = (Module) constructor.newInstance();
				} else*/
				if (moduleConfiguration.getModuleType().equals(ModuleConfiguration.ModuleTypes.Client)) {
					constructor = newClass.getConstructor();
						newModule = (Module) constructor.newInstance();
				//	constructor = newClass.getConstructor(ModuleController.class);
				//	newModule = (Module) constructor.newInstance(this);
				} else {
					logger.warn(moduleConfiguration.getName() + " has not defined its type. Client");
					return null;
				}
				logger.info(newModule.getName() + " created from project.");
				return newModule;
				//getModules().put(newModule.getName(), newModule);
				
		/*	} else {
				logger.info(module.getName() + " is already added from project.");
				getModules().put(this.getName(), this);
			}*/
		} catch (ClassNotFoundException e1) {
			try {
				jcl.add(moduleConfiguration.getCompletePathToMainClass());
				newModule = getModuleInstanceFromJAR(moduleConfiguration);
				if (newModule == null) {
						logger.error(moduleConfiguration.getName()
								+ " missing. Disable it or check module configuration data. Execution aborted.");
						System.exit(1);
					
				}
				logger.info(moduleConfiguration.getName() + " created from JAR File.");
				return newModule;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  null;
	}

	public static Module getModuleInstanceFromJAR(ModuleConfiguration moduleConfiguration) {

		JarClassLoader jcl = new JarClassLoader();
		try {
			jcl.add(moduleConfiguration.getCompletePathToJar());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JclObjectFactory factory = JclObjectFactory.getInstance();

		// Create object of loaded class
		try {
			// Object obj =
			// factory.create(jcl,dynamicModule.getModuleMainClass(),"getInstance",null);
			Object obj = factory.create(jcl, moduleConfiguration.getCompletePathToMainClass());
			return (Module) obj;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
			logger.info(moduleConfiguration.getCompletePathToJar() + " was not found.");
			return null;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return null;
		}

	}
}
