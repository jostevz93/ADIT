package ee.adit.dao.pojo;

// Generated 21.06.2010 12:26:14 by Hibernate Tools 3.2.4.GA

import java.sql.Blob;

/**
 * Signature generated by hbm2java
 */
public class Signature implements java.io.Serializable {

	private long id;
	private Document document;
	private String userCode;
	private String signerRole;
	private String resolution;
	private String country;
	private String county;
	private String city;
	private String postIndex;
	private Blob containerData;

	public Signature() {
	}

	public Signature(long id, Document document) {
		this.id = id;
		this.document = document;
	}

	public Signature(long id, Document document, String userCode,
			String signerRole, String resolution, String country,
			String county, String city, String postIndex, Blob containerData) {
		this.id = id;
		this.document = document;
		this.userCode = userCode;
		this.signerRole = signerRole;
		this.resolution = resolution;
		this.country = country;
		this.county = county;
		this.city = city;
		this.postIndex = postIndex;
		this.containerData = containerData;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getSignerRole() {
		return this.signerRole;
	}

	public void setSignerRole(String signerRole) {
		this.signerRole = signerRole;
	}

	public String getResolution() {
		return this.resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostIndex() {
		return this.postIndex;
	}

	public void setPostIndex(String postIndex) {
		this.postIndex = postIndex;
	}

	public Blob getContainerData() {
		return this.containerData;
	}

	public void setContainerData(Blob containerData) {
		this.containerData = containerData;
	}

}
