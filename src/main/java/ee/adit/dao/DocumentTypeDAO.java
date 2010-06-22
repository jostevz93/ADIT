package ee.adit.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import ee.adit.dao.pojo.DocumentType;

public class DocumentTypeDAO extends AbstractAditDAO {
	
	private static Logger LOG = Logger.getLogger(DocumentTypeDAO.class);
	
	public List<DocumentType> listDocumentTypes() {
		return this.getHibernateTemplate().find("from DocumentType documentType");
	}

	public DocumentType getDocumentType(String documentTypeShortName) {
		LOG.debug("Fetching document type by short name: " + documentTypeShortName);		
		return (DocumentType) this.getHibernateTemplate().get(DocumentType.class, documentTypeShortName);
	}
	
}
