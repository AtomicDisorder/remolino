/**
 * 
 */
package com.atomicDisorder.remolino.persistance;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

/**
 * @author Mariano Blua
 *
 */
public class JAXBProvider {
	//private static Logger logger = Logger.getLogger(JAXBProvider.class.getName());
	private static Logger logger = Logger.getLogger(JAXBProvider.class.getName());

	public static void marshalClassToFile(Object objectToMarshal, String fileNameWithExtension) {
		
		try {

			File file = null;
			if (fileNameWithExtension == null) {
				file = new File(".//"
						+ objectToMarshal.getClass().getSimpleName() + ".xml");
			} else {
					file = new File( ".//" +fileNameWithExtension);
			}

			JAXBContext jaxbContext = JAXBContext.newInstance(objectToMarshal.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(objectToMarshal, file);
			logger.info(objectToMarshal.getClass().getSimpleName() + " Data Saved");
		} catch (JAXBException e) {
			logger.info("marshalClassToFile: Error saving module "
					+ objectToMarshal.getClass().getClass().getCanonicalName());
			System.out.println("marshalClassToFile: Error saving module "
					+ objectToMarshal.getClass().getClass().getCanonicalName());
			e.printStackTrace();
		}
	}

	public static void marshalClassToFile(Object objectToMarshal) {
		marshalClassToFile(objectToMarshal, null);
	}

	public static Object unmarshalFromFileOrCreate(Class classToUnmarshal, String fileNameWithExtension) {
		File file = null;

		file = new File(".//" + fileNameWithExtension);

		if (file.exists()) {
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(classToUnmarshal);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				return jaxbUnmarshaller.unmarshal(file);

			} catch (JAXBException JAXBException) {
				System.out.println("UNNACEPTABLE ERROR UNMARSHALING " + file.getAbsolutePath());
				JAXBException.printStackTrace();
				System.exit(3);
				return null;
			}
		} else {// If it doesnt exist, i create a new instance.
			return createNewInstance(classToUnmarshal);
		}
	}

	private static Object createNewInstance(Class classToBuildInstance) {
		try {
			Constructor constructor = classToBuildInstance.getConstructor();

			return (Object) constructor.newInstance();

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
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
		}
		System.exit(3);
		return null;

	}
}
