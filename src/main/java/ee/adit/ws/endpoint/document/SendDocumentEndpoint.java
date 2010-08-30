package ee.adit.ws.endpoint.document;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import ee.adit.dao.pojo.AditUser;
import ee.adit.dao.pojo.Document;
import ee.adit.exception.AditCodedException;
import ee.adit.exception.AditException;
import ee.adit.exception.AditInternalException;
import ee.adit.pojo.ArrayOfMessage;
import ee.adit.pojo.ArrayOfRecipientStatus;
import ee.adit.pojo.ArrayOfUserCode;
import ee.adit.pojo.Message;
import ee.adit.pojo.RecipientStatus;
import ee.adit.pojo.SendDocumentRequest;
import ee.adit.pojo.SendDocumentResponse;
import ee.adit.service.DocumentService;
import ee.adit.service.LogService;
import ee.adit.service.UserService;
import ee.adit.util.CustomXTeeHeader;
import ee.adit.util.Util;
import ee.adit.ws.endpoint.AbstractAditBaseEndpoint;
import ee.webmedia.xtee.annotation.XTeeService;

/**
 * Implementation of "sendDocument" web method (web service request).
 * Contains request input validation, request-specific workflow
 * and response composition.  
 * 
 * @author Marko Kurm, Microlink Eesti AS, marko.kurm@microlink.ee
 * @author Jaak Lember, Interinx, jaak@interinx.com
 */
@XTeeService(name = "sendDocument", version = "v1")
@Component
public class SendDocumentEndpoint extends AbstractAditBaseEndpoint {

	private static Logger LOG = Logger.getLogger(SendDocumentEndpoint.class);
	
	private UserService userService;
	
	private DocumentService documentService;
	
	@Override
	protected Object invokeInternal(Object requestObject, int version) throws Exception {
		LOG.debug("sendDocument invoked. Version: " + version);

		if (version == 1) {
			return v1(requestObject);
		} else {
			throw new AditInternalException("This method does not support version specified: " + version);
		}
	}
	
	/**
	 * Executes "V1" version of "sendDocument" request.
	 * 
	 * @param requestObject		Request body object
	 * @return					Response body object
	 */
	protected Object v1(Object requestObject) {
		SendDocumentResponse response = new SendDocumentResponse();
		Calendar requestDate = Calendar.getInstance();
		String additionalInformationForLog = null;
		Long documentId = null;
		boolean success = true;		
		ArrayOfRecipientStatus reponseStatuses = new ArrayOfRecipientStatus();
		String description = "Recipients: ";
		int successCount = 0;
		
		try {
		
			LOG.debug("SendDocumentEndpoint.v1 invoked.");
			SendDocumentRequest request = (SendDocumentRequest) requestObject;
			CustomXTeeHeader header = this.getHeader();
			String applicationName = header.getInfosysteem();
			
			// Log request
			Util.printHeader(header);
			// TODO: printRequest(request);

			// Check header for required fields
			checkHeader(header);

			// Check request body
			// TODO: checkRequest(request);
			
			super.logCurrentRequest(documentId, requestDate.getTime(), additionalInformationForLog);
			
			// Check if the application is registered
			boolean applicationRegistered = this.getUserService().isApplicationRegistered(applicationName);
			if (!applicationRegistered) {
				LOG.error("Application is not registered.");
				AditCodedException aditCodedException = new AditCodedException("application.notRegistered");
				aditCodedException.setParameters(new Object[] { applicationName });
				throw aditCodedException;
			}
			
			// Check if the user is registered
			String userCode = ((this.getHeader().getAllasutus() != null) && (this.getHeader().getAllasutus().length() > 0)) ? this.getHeader().getAllasutus() : this.getHeader().getIsikukood();
			AditUser user = this.getUserService().getUserByID(userCode);
			if (user == null) {
				LOG.error("User is not registered.");
				AditCodedException aditCodedException = new AditCodedException("user.nonExistent");
				aditCodedException.setParameters(new Object[] { userCode });
				throw aditCodedException;
			}
			
			// Check application access level for user
			int applicationAccessLevelForUser = userService.getAccessLevelForUser(applicationName, user);
			if (applicationAccessLevelForUser < 2) {
				LOG.error("Application has insufficient privileges for user: " + user.getUserCode());
				AditCodedException aditCodedException = new AditCodedException("application.insufficientPrivileges.read");
				aditCodedException.setParameters(new Object[] { applicationName });
				throw aditCodedException;
			}
			
			// Check if the document exists
			Document doc = this.getDocumentService().getDocumentDAO().getDocument(request.getDocumentId());
			
			if(doc == null) {
				LOG.debug("Requested document does not exist. Document ID: " + request.getDocumentId());
				AditCodedException aditCodedException = new AditCodedException("document.nonExistent");
				aditCodedException.setParameters(new Object[] { request.getDocumentId() });
				throw aditCodedException;
			}
			
			// Check whether the document is marked as deleted
			if ((doc.getDeleted() != null) && doc.getDeleted()) {
				LOG.debug("Requested document is deleted. Document ID: " + request.getDocumentId());
				AditCodedException aditCodedException = new AditCodedException("document.deleted");
				aditCodedException.setParameters(new Object[] { request.getDocumentId() });
				throw aditCodedException;
			}
			
			// Check whether the document is marked as deflated
			if ((doc.getDeflated() != null) && doc.getDeflated()) {
				LOG.debug("Requested document is deflated. Document ID: " + request.getDocumentId());
				AditCodedException aditCodedException = new AditCodedException("document.deflated");
				aditCodedException.setParameters(new Object[] { Util.dateToEstonianDateString(doc.getDeflateDate()) });
				throw aditCodedException;
			}
			
			// Check whether the document belongs to user
			if (!doc.getCreatorCode().equalsIgnoreCase(userCode)) {
				LOG.debug("Requested document does not belong to user. Document ID: " + request.getDocumentId() + ", User ID: " + userCode);
				AditCodedException aditCodedException = new AditCodedException("document.doesNotBelongToUser");
				aditCodedException.setParameters(new Object[] { request.getDocumentId(), userCode });
				throw aditCodedException;
			}
			
			ArrayOfUserCode recipientList = request.getRecipientList();
			
			if(recipientList != null && recipientList.getCode() != null && recipientList.getCode().size() > 0) {
				
				Iterator<String> i = recipientList.getCode().iterator();
				while(i.hasNext()) {
					String recipientCode = i.next();
					
					RecipientStatus recipientStatus = new RecipientStatus();
					recipientStatus.setSuccess(true);
					
					// Check if the user is registered
					AditUser recipient = this.getUserService().getUserByID(recipientCode);
					
					if(recipient == null) {
						LOG.error("User is not registered.");
						recipientStatus.setSuccess(false);
						String errorMessage = this.getMessageSource().getMessage("user.nonExistent", new Object[] { userCode },	Locale.ENGLISH);
						ArrayOfMessage recipientMessages = new ArrayOfMessage();
						recipientMessages.addMessage(new Message("en", errorMessage));
						recipientStatus.setMessages(recipientMessages);
						success = false;
					} else {
						
						try {
							// Lock the document
							this.getDocumentService().lockDocument(doc);
							
							// Add history event
							this.getDocumentService().addHistoryEvent(
									applicationName, 
									doc, 
									userCode, 
									DocumentService.HistoryType_Lock,
									header.getIsikukood(),
									null,
									null
							);
							
							// Add sharing information to database
							this.getDocumentService().sendDocument(doc, recipient);										
							
							// Add success message to response
							recipientStatus.setSuccess(true);
							recipientStatus.setCode(recipient.getUserCode());			
							
							if(successCount > 0) {
								description = description + ", ";
							}
							description = description + recipient.getUserCode();							
							successCount++;
							
							// TODO: Send a notification to the XTee teavituskalender
							
						} catch (Exception e) {
							LOG.error("Exception while sharing document: ", e);
							recipientStatus.setSuccess(false);
							String errorMessage = this.getMessageSource().getMessage("service.error", new Object[] {},	Locale.ENGLISH);
							ArrayOfMessage recipientMessages = new ArrayOfMessage();
							recipientMessages.addMessage(new Message("en", errorMessage));
							recipientStatus.setMessages(recipientMessages);
							success = false;
						}
					}
					reponseStatuses.addRecipient(recipientStatus);
				}
				
			} else {
				throw new NullPointerException("Recipient list is empty or null.");
			}
			
			response.setSuccess(success);
			response.setRecipientList(reponseStatuses);
			
			this.getDocumentService().addHistoryEvent(
				applicationName, 
				doc, 
				userCode, 
				DocumentService.HistoryType_Send,
				header.getIsikukood(),
				null,
				description
			);
			
		} catch(Exception e) {
			success = false;
			LOG.error("Exception: ", e);
			super.logError(documentId, requestDate.getTime(), LogService.ErrorLogLevel_Error, e.getMessage());

			response.setSuccess(success);
			ArrayOfMessage arrayOfMessage = new ArrayOfMessage();
			
			if(e instanceof AditCodedException) {
				LOG.debug("Adding exception messages to response object.");
				arrayOfMessage.setMessage(this.getMessageService().getMessages((AditCodedException) e));
			} else if(e instanceof AditException) {
				LOG.debug("Adding exception message to response object.");
				arrayOfMessage.getMessage().add(new Message("en", e.getMessage()));
			} else {
				arrayOfMessage.getMessage().add(new Message("en", "Service error"));
			}
			
			LOG.debug("Adding exception messages to response object.");
			response.setMessages(arrayOfMessage);
		}

		return response;
	}

	@Override
	protected Object getResultForGenericException(Exception ex) {
		super.logError(null, Calendar.getInstance().getTime(), LogService.ErrorLogLevel_Fatal, ex.getMessage());
		SendDocumentResponse response = new SendDocumentResponse();
		response.setSuccess(false);
		ArrayOfMessage arrayOfMessage = new ArrayOfMessage();
		arrayOfMessage.getMessage().add(new Message("en", ex.getMessage()));
		response.setMessages(arrayOfMessage);
		return response;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public DocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

}
