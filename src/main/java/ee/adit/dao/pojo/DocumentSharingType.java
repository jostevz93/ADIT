package ee.adit.dao.pojo;

// Generated 21.06.2010 12:26:14 by Hibernate Tools 3.2.4.GA

import java.util.HashSet;
import java.util.Set;

/**
 * DocumentSharingType generated by hbm2java
 */
public class DocumentSharingType implements java.io.Serializable {

	private String shortName;
	private String description;
	private Set documentSharings = new HashSet(0);

	public DocumentSharingType() {
	}

	public DocumentSharingType(String shortName) {
		this.shortName = shortName;
	}

	public DocumentSharingType(String shortName, String description,
			Set documentSharings) {
		this.shortName = shortName;
		this.description = description;
		this.documentSharings = documentSharings;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getDocumentSharings() {
		return this.documentSharings;
	}

	public void setDocumentSharings(Set documentSharings) {
		this.documentSharings = documentSharings;
	}

}
