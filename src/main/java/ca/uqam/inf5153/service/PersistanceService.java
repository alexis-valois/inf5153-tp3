package ca.uqam.inf5153.service;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ca.uqam.inf5153.model.Partie;


public class PersistanceService {
	private static PersistanceService instance;

	private PersistanceService() {

	}

	public static PersistanceService obtenirInstance() {
		if (instance == null) {
			instance = new PersistanceService();
		}

		return instance;
	}

	public boolean jaxbObjectToXML(Partie partie, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Partie.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(partie, new File(filePath));
            return true;
        } catch (JAXBException e) {
            e.printStackTrace();
            return false;
        }
    }

	public Partie jaxbXMLToObject(String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Partie.class);
            Unmarshaller un = context.createUnmarshaller();
            Partie partie = (Partie) un.unmarshal(new File(filePath));
            return partie;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
