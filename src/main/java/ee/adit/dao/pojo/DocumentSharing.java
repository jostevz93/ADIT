package ee.adit.dao.pojo;

// Generated 21.06.2010 12:26:14 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * DocumentSharing generated by hbm2java
 */
public class DocumentSharing implements java.io.Serializable {

	private long id;
	private DocumentSharingType documentSharingType;
	private DocumentDvkStatus documentDvkStatus;
	private DocumentWfStatus documentWfStatus;
	private long documentId;
	private String userCode;
	private String taskDescription;
	private Date creationDate;
	private Date lastAccessDate;

	public DocumentSharing() {
	}

	public DocumentSharing(long id, DocumentSharingType documentSharingType,
			long documentId, String userCode) {
		this.id = id;
		this.documentSharingType = documentSharingType;
		this.documentId = documentId;
		this.userCode = userCode;
	}

	public DocumentSharing(long id, DocumentSharingType documentSharingType,
			DocumentDvkStatus documentDvkStatus,
			DocumentWfStatus documentWfStatus, long documentId,
			String userCode, String taskDescription, Date creationDate,
			Date lastAccessDate) {
		this.id = id;
		this.documentSharingType = documentSharingType;
		this.documentDvkStatus = documentDvkStatus;
		this.documentWfStatus = documentWfStatus;
		this.documentId = documentId;
		this.userCode = userCode;
		this.taskDescription = taskDescription;
		this.creationDate = creationDate;
		this.lastAccessDate = lastAccessDate;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DocumentSharingType getDocumentSharingType() {
		return this.documentSharingType;
	}

	public void setDocumentSharingType(DocumentSharingType documentSharingType) {
		this.documentSharingType = documentSharingType;
	}

	public DocumentDvkStatus getDocumentDvkStatus() {
		return this.documentDvkStatus;
	}

	public void setDocumentDvkStatus(DocumentDvkStatus documentDvkStatus) {
		this.documentDvkStatus = documentDvkStatus;
	}

	public DocumentWfStatus getDocumentWfStatus() {
		return this.documentWfStatus;
	}

	public void setDocumentWfStatus(DocumentWfStatus documentWfStatus) {
		this.documentWfStatus = documentWfStatus;
	}

	public long getDocumentId() {
		return this.documentId;
	}

	public void setDocumentId(long documentId) {
		this.documentId = documentId;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getTaskDescription() {
		return this.taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastAccessDate() {
		return this.lastAccessDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

}
