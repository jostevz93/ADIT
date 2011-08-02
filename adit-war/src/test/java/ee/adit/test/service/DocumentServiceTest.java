package ee.adit.test.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

import javax.activation.FileTypeMap;

import ee.adit.dao.pojo.DocumentFile;
import ee.adit.pojo.ArrayOfFileType;
import ee.adit.service.DocumentService;
import ee.adit.util.Configuration;
import ee.adit.util.StartEndOffsetPair;
import junit.framework.TestCase;

/**
 * The class <code>DocumentServiceTest</code> contains tests for the class
 * {@link <code>DocumentService</code>}
 *
 * @pattern JUnit Test Case
 *
 * @generatedBy CodePro at 7.04.11 11:32
 *
 * @author Jaak Lember, Interinx, jaak@interinx.com
 */
public class DocumentServiceTest extends TestCase {

	/**
	 * Construct new test instance
	 *
	 * @param name the test name
	 */
	public DocumentServiceTest(String name) {
		super(name);
	}

	public void testFileIsOfRequestedType() {
		ArrayOfFileType types = null;
		assertTrue(DocumentService.fileIsOfRequestedType(DocumentService.FILETYPE_DOCUMENT_FILE, types));
		assertTrue(DocumentService.fileIsOfRequestedType(DocumentService.FILETYPE_SIGNATURE_CONTAINER, types));
		assertTrue(DocumentService.fileIsOfRequestedType(DocumentService.FILETYPE_SIGNATURE_CONTAINER_DRAFT, types));

		types = new ArrayOfFileType();
		assertTrue(DocumentService.fileIsOfRequestedType(DocumentService.FILETYPE_DOCUMENT_FILE, types));
		assertTrue(DocumentService.fileIsOfRequestedType(DocumentService.FILETYPE_SIGNATURE_CONTAINER, types));
		assertTrue(DocumentService.fileIsOfRequestedType(DocumentService.FILETYPE_SIGNATURE_CONTAINER_DRAFT, types));

		types.setFileType(new ArrayList<String>());
		assertTrue(DocumentService.fileIsOfRequestedType(DocumentService.FILETYPE_DOCUMENT_FILE, types));
		assertTrue(DocumentService.fileIsOfRequestedType(DocumentService.FILETYPE_SIGNATURE_CONTAINER, types));
		assertTrue(DocumentService.fileIsOfRequestedType(DocumentService.FILETYPE_SIGNATURE_CONTAINER_DRAFT, types));

		types.getFileType().add(DocumentService.FILETYPE_NAME_DOCUMENT_FILE);
		assertTrue(DocumentService.fileIsOfRequestedType(DocumentService.FILETYPE_DOCUMENT_FILE, types));
		assertFalse(DocumentService.fileIsOfRequestedType(DocumentService.FILETYPE_SIGNATURE_CONTAINER, types));
		assertFalse(DocumentService.fileIsOfRequestedType(DocumentService.FILETYPE_SIGNATURE_CONTAINER_DRAFT, types));

		types.getFileType().add(DocumentService.FILETYPE_NAME_SIGNATURE_CONTAINER);
		assertTrue(DocumentService.fileIsOfRequestedType(DocumentService.FILETYPE_DOCUMENT_FILE, types));
		assertTrue(DocumentService.fileIsOfRequestedType(DocumentService.FILETYPE_SIGNATURE_CONTAINER, types));
		assertFalse(DocumentService.fileIsOfRequestedType(DocumentService.FILETYPE_SIGNATURE_CONTAINER_DRAFT, types));

		types.getFileType().add(DocumentService.FILETYPE_NAME_SIGNATURE_CONTAINER_DRAFT);
		assertTrue(DocumentService.fileIsOfRequestedType(DocumentService.FILETYPE_DOCUMENT_FILE, types));
		assertTrue(DocumentService.fileIsOfRequestedType(DocumentService.FILETYPE_SIGNATURE_CONTAINER, types));
		assertTrue(DocumentService.fileIsOfRequestedType(DocumentService.FILETYPE_SIGNATURE_CONTAINER_DRAFT, types));
	}

	public void testIsNecessaryToRemoveFileContentsAfterSigning_NullParameters_ExpectFalse() {
		assertFalse(DocumentService.isNecessaryToRemoveFileContentsAfterSigning(null, null));
	}

	public void testIsNecessaryToRemoveFileContentsAfterSigning_OffsetTableParameterNull_ExpectFalse() {
		DocumentFile file = new DocumentFile();
		assertFalse(DocumentService.isNecessaryToRemoveFileContentsAfterSigning(file, null));
	}

	public void testIsNecessaryToRemoveFileContentsAfterSigning_FileNull_ExpectFalse() {
		Hashtable<String, StartEndOffsetPair> fileOffsetsInDdoc = new Hashtable<String, StartEndOffsetPair>();
		StartEndOffsetPair offsets = new StartEndOffsetPair();
		offsets.setStart(100);
		offsets.setEnd(200);
		fileOffsetsInDdoc.put("D0", offsets);
		assertFalse(DocumentService.isNecessaryToRemoveFileContentsAfterSigning(null, fileOffsetsInDdoc));
	}

	public void testIsNecessaryToRemoveFileContentsAfterSigning_FileDeletedTrue_ExpectFalse() {
		DocumentFile file = new DocumentFile();
		file.setDocumentFileTypeId(DocumentService.FILETYPE_DOCUMENT_FILE);
		file.setDdocDataFileId("D0");
		file.setDeleted(true);

		Hashtable<String, StartEndOffsetPair> fileOffsetsInDdoc = new Hashtable<String, StartEndOffsetPair>();
		StartEndOffsetPair offsets = new StartEndOffsetPair();
		offsets.setStart(100);
		offsets.setEnd(200);
		fileOffsetsInDdoc.put("D0", offsets);

		assertFalse(DocumentService.isNecessaryToRemoveFileContentsAfterSigning(file, fileOffsetsInDdoc));
	}

	public void testIsNecessaryToRemoveFileContentsAfterSigning_FileDeletedNull_ExpectTrue() {
		DocumentFile file = new DocumentFile();
		file.setDocumentFileTypeId(DocumentService.FILETYPE_DOCUMENT_FILE);
		file.setDdocDataFileId("D0");
		file.setDeleted(null);

		Hashtable<String, StartEndOffsetPair> fileOffsetsInDdoc = new Hashtable<String, StartEndOffsetPair>();
		StartEndOffsetPair offsets = new StartEndOffsetPair();
		offsets.setStart(100);
		offsets.setEnd(200);
		fileOffsetsInDdoc.put("D0", offsets);

		assertTrue(DocumentService.isNecessaryToRemoveFileContentsAfterSigning(file, fileOffsetsInDdoc));
	}

	public void testIsNecessaryToRemoveFileContentsAfterSigning_FileDeletedFalse_ExpectTrue() {
		DocumentFile file = new DocumentFile();
		file.setDocumentFileTypeId(DocumentService.FILETYPE_DOCUMENT_FILE);
		file.setDdocDataFileId("D0");
		file.setDeleted(false);

		Hashtable<String, StartEndOffsetPair> fileOffsetsInDdoc = new Hashtable<String, StartEndOffsetPair>();
		StartEndOffsetPair offsets = new StartEndOffsetPair();
		offsets.setStart(100);
		offsets.setEnd(200);
		fileOffsetsInDdoc.put("D0", offsets);

		assertTrue(DocumentService.isNecessaryToRemoveFileContentsAfterSigning(file, fileOffsetsInDdoc));
	}

	public void testIsNecessaryToRemoveFileContentsAfterSigning_FileIsSignatureContainer_ExpectFalse() {
		DocumentFile file = new DocumentFile();
		file.setDocumentFileTypeId(DocumentService.FILETYPE_SIGNATURE_CONTAINER);
		file.setDdocDataFileId("D0");
		file.setDeleted(false);

		Hashtable<String, StartEndOffsetPair> fileOffsetsInDdoc = new Hashtable<String, StartEndOffsetPair>();
		StartEndOffsetPair offsets = new StartEndOffsetPair();
		offsets.setStart(100);
		offsets.setEnd(200);
		fileOffsetsInDdoc.put("D0", offsets);

		assertFalse(DocumentService.isNecessaryToRemoveFileContentsAfterSigning(file, fileOffsetsInDdoc));
	}

	public void testIsNecessaryToRemoveFileContentsAfterSigning_FileIsSignatureContainerDraft_ExpectFalse() {
		DocumentFile file = new DocumentFile();
		file.setDocumentFileTypeId(DocumentService.FILETYPE_SIGNATURE_CONTAINER_DRAFT);
		file.setDdocDataFileId("D0");
		file.setDeleted(false);

		Hashtable<String, StartEndOffsetPair> fileOffsetsInDdoc = new Hashtable<String, StartEndOffsetPair>();
		StartEndOffsetPair offsets = new StartEndOffsetPair();
		offsets.setStart(100);
		offsets.setEnd(200);
		fileOffsetsInDdoc.put("D0", offsets);

		assertFalse(DocumentService.isNecessaryToRemoveFileContentsAfterSigning(file, fileOffsetsInDdoc));
	}

	public void testIsNecessaryToRemoveFileContentsAfterSigning_FileIsZipArchive_ExpectFalse() {
		DocumentFile file = new DocumentFile();
		file.setDocumentFileTypeId(DocumentService.FILETYPE_ZIP_ARCHIVE);
		file.setDdocDataFileId("D0");
		file.setDeleted(false);

		Hashtable<String, StartEndOffsetPair> fileOffsetsInDdoc = new Hashtable<String, StartEndOffsetPair>();
		StartEndOffsetPair offsets = new StartEndOffsetPair();
		offsets.setStart(100);
		offsets.setEnd(200);
		fileOffsetsInDdoc.put("D0", offsets);

		assertFalse(DocumentService.isNecessaryToRemoveFileContentsAfterSigning(file, fileOffsetsInDdoc));
	}

	public void testIsNecessaryToRemoveFileContentsAfterSigning_FileIsOfUnknownType_ExpectFalse() {
		DocumentFile file = new DocumentFile();
		file.setDocumentFileTypeId(0);
		file.setDdocDataFileId("D0");
		file.setDeleted(false);

		Hashtable<String, StartEndOffsetPair> fileOffsetsInDdoc = new Hashtable<String, StartEndOffsetPair>();
		StartEndOffsetPair offsets = new StartEndOffsetPair();
		offsets.setStart(100);
		offsets.setEnd(200);
		fileOffsetsInDdoc.put("D0", offsets);

		assertFalse(DocumentService.isNecessaryToRemoveFileContentsAfterSigning(file, fileOffsetsInDdoc));
	}

	public void testIsNecessaryToRemoveFileContentsAfterSigning_FileDdocIdNull_ExpectFalse() {
		DocumentFile file = new DocumentFile();
		file.setDocumentFileTypeId(DocumentService.FILETYPE_DOCUMENT_FILE);
		file.setDdocDataFileId(null);
		file.setDeleted(false);

		Hashtable<String, StartEndOffsetPair> fileOffsetsInDdoc = new Hashtable<String, StartEndOffsetPair>();
		StartEndOffsetPair offsets = new StartEndOffsetPair();
		offsets.setStart(100);
		offsets.setEnd(200);
		fileOffsetsInDdoc.put("D0", offsets);

		assertFalse(DocumentService.isNecessaryToRemoveFileContentsAfterSigning(file, fileOffsetsInDdoc));
	}

	public void testIsNecessaryToRemoveFileContentsAfterSigning_FileDdocIdEmpty_ExpectFalse() {
		DocumentFile file = new DocumentFile();
		file.setDocumentFileTypeId(DocumentService.FILETYPE_DOCUMENT_FILE);
		file.setDdocDataFileId("");
		file.setDeleted(false);

		Hashtable<String, StartEndOffsetPair> fileOffsetsInDdoc = new Hashtable<String, StartEndOffsetPair>();
		StartEndOffsetPair offsets = new StartEndOffsetPair();
		offsets.setStart(100);
		offsets.setEnd(200);
		fileOffsetsInDdoc.put("D0", offsets);

		assertFalse(DocumentService.isNecessaryToRemoveFileContentsAfterSigning(file, fileOffsetsInDdoc));
	}

	public void testIsNecessaryToRemoveFileContentsAfterSigning_FileDdocIdNotInOffsetTable_ExpectFalse() {
		DocumentFile file = new DocumentFile();
		file.setDocumentFileTypeId(DocumentService.FILETYPE_DOCUMENT_FILE);
		file.setDdocDataFileId("D1");
		file.setDeleted(false);

		Hashtable<String, StartEndOffsetPair> fileOffsetsInDdoc = new Hashtable<String, StartEndOffsetPair>();
		StartEndOffsetPair offsets = new StartEndOffsetPair();
		offsets.setStart(100);
		offsets.setEnd(200);
		fileOffsetsInDdoc.put("D0", offsets);

		assertFalse(DocumentService.isNecessaryToRemoveFileContentsAfterSigning(file, fileOffsetsInDdoc));
	}

	public void testIsNecessaryToRemoveFileContentsAfterSigning_IncorrectDataInOffsetTable_ExpectFalse() {
		DocumentFile file = new DocumentFile();
		file.setDdocDataFileId("D0");
		file.setDocumentFileTypeId(DocumentService.FILETYPE_DOCUMENT_FILE);

		Hashtable<String, StartEndOffsetPair> fileOffsetsInDdoc = new Hashtable<String, StartEndOffsetPair>();

		StartEndOffsetPair offsets = new StartEndOffsetPair();
		fileOffsetsInDdoc.put("D0", offsets);
		assertFalse(DocumentService.isNecessaryToRemoveFileContentsAfterSigning(file, fileOffsetsInDdoc));

		offsets.setStart(200);
		offsets.setEnd(100);
		fileOffsetsInDdoc.put("D0", offsets);
		assertFalse(DocumentService.isNecessaryToRemoveFileContentsAfterSigning(file, fileOffsetsInDdoc));

		offsets.setStart(100);
		offsets.setEnd(200);
		fileOffsetsInDdoc.put("D0", offsets);
		assertTrue(DocumentService.isNecessaryToRemoveFileContentsAfterSigning(file, fileOffsetsInDdoc));
	}

	public void testIsNecessaryToRemoveFileContentsAfterSigning_SuccessCase_ExpectTrue() {
		DocumentFile file = new DocumentFile();
		file.setDocumentFileTypeId(DocumentService.FILETYPE_DOCUMENT_FILE);
		file.setDdocDataFileId("D0");
		file.setDeleted(false);

		Hashtable<String, StartEndOffsetPair> fileOffsetsInDdoc = new Hashtable<String, StartEndOffsetPair>();
		StartEndOffsetPair offsets = new StartEndOffsetPair();
		offsets.setStart(100);
		offsets.setEnd(200);
		fileOffsetsInDdoc.put("D0", offsets);

		assertTrue(DocumentService.isNecessaryToRemoveFileContentsAfterSigning(file, fileOffsetsInDdoc));
	}

	public void testIsNecessaryToRemoveFileContentsAfterSigning_OffsetsAlreadySet_ExpectFalse() {
		DocumentFile file = new DocumentFile();
		file.setDocumentFileTypeId(DocumentService.FILETYPE_DOCUMENT_FILE);
		file.setDdocDataFileId("D0");
		file.setDeleted(false);
		file.setDdocDataFileStartOffset(100L);
		file.setDdocDataFileEndOffset(200L);

		Hashtable<String, StartEndOffsetPair> fileOffsetsInDdoc = new Hashtable<String, StartEndOffsetPair>();
		StartEndOffsetPair offsets = new StartEndOffsetPair();
		offsets.setStart(100);
		offsets.setEnd(200);
		fileOffsetsInDdoc.put("D0", offsets);

		assertFalse(DocumentService.isNecessaryToRemoveFileContentsAfterSigning(file, fileOffsetsInDdoc));
	}

	public void testResolveFileTypeId_TypeNameNull_ExpectDocumentFile() {
		assertEquals(DocumentService.FILETYPE_DOCUMENT_FILE, DocumentService.resolveFileTypeId(null));
	}

	public void testResolveFileTypeId_TypeNameEmpty_ExpectDocumentFile() {
		assertEquals(DocumentService.FILETYPE_DOCUMENT_FILE, DocumentService.resolveFileTypeId(""));
	}

	public void testResolveFileTypeId_TypeNameUnknown_ExpectDocumentFile() {
		assertEquals(DocumentService.FILETYPE_DOCUMENT_FILE, DocumentService.resolveFileTypeId("random type name"));
	}

	public void testResolveFileTypeId_TypeNameDocFile_ExpectDocumentFile() {
		assertEquals(DocumentService.FILETYPE_DOCUMENT_FILE, DocumentService.resolveFileTypeId(DocumentService.FILETYPE_NAME_DOCUMENT_FILE));
	}

	public void testResolveFileTypeId_TypeNameDigiDoc_ExpectDigiDoc() {
		assertEquals(DocumentService.FILETYPE_SIGNATURE_CONTAINER, DocumentService.resolveFileTypeId(DocumentService.FILETYPE_NAME_SIGNATURE_CONTAINER));
	}

	public void testResolveFileTypeId_TypeNameDigiDocDraft_ExpectDigiDocDraft() {
		assertEquals(DocumentService.FILETYPE_SIGNATURE_CONTAINER_DRAFT, DocumentService.resolveFileTypeId(DocumentService.FILETYPE_NAME_SIGNATURE_CONTAINER_DRAFT));
	}

	public void testResolveFileTypeId_TypeNameZipArchive_ExpectZipArchive() {
		assertEquals(DocumentService.FILETYPE_ZIP_ARCHIVE, DocumentService.resolveFileTypeId(DocumentService.FILETYPE_NAME_ZIP_ARCHIVE));
	}

	public void testIsSignatureContainerDraftExpired_LifetimeNotSetInConf_ExpectFalse() {
		Configuration conf = new Configuration();
		conf.setUnfinishedSignatureLifetimeSeconds(null);

		DocumentService svc = new DocumentService();
		svc.setConfiguration(conf);

		Calendar cal = Calendar.getInstance();
		cal.set(1970, Calendar.JANUARY, 1);

		DocumentFile containerDraft = new DocumentFile();
		containerDraft.setLastModifiedDate(cal.getTime());
		containerDraft.setDocumentFileTypeId(DocumentService.FILETYPE_SIGNATURE_CONTAINER_DRAFT);

		assertFalse(svc.isSignatureContainerDraftExpired(containerDraft));
	}

	public void testIsSignatureContainerDraftExpired_LifetimeZeroSetInConf_ExpectFalse() {
		Configuration conf = new Configuration();
		conf.setUnfinishedSignatureLifetimeSeconds(0L);

		DocumentService svc = new DocumentService();
		svc.setConfiguration(conf);

		Calendar cal = Calendar.getInstance();
		cal.set(1970, Calendar.JANUARY, 1);

		DocumentFile containerDraft = new DocumentFile();
		containerDraft.setLastModifiedDate(cal.getTime());
		containerDraft.setDocumentFileTypeId(DocumentService.FILETYPE_SIGNATURE_CONTAINER_DRAFT);

		assertFalse(svc.isSignatureContainerDraftExpired(containerDraft));
	}

	public void testIsSignatureContainerDraftExpired_LastModifiedInDistantPast_ExpectTrue() {
		Configuration conf = new Configuration();
		conf.setUnfinishedSignatureLifetimeSeconds(120L);

		DocumentService svc = new DocumentService();
		svc.setConfiguration(conf);

		Calendar cal = Calendar.getInstance();
		cal.set(1970, Calendar.JANUARY, 1);

		DocumentFile containerDraft = new DocumentFile();
		containerDraft.setLastModifiedDate(cal.getTime());
		containerDraft.setDocumentFileTypeId(DocumentService.FILETYPE_SIGNATURE_CONTAINER_DRAFT);

		assertTrue(svc.isSignatureContainerDraftExpired(containerDraft));
	}

	public void testIsSignatureContainerDraftExpired_LastModifiedLessThanLifetimeAgo_ExpectFalse() {
		Configuration conf = new Configuration();
		conf.setUnfinishedSignatureLifetimeSeconds(120L);

		DocumentService svc = new DocumentService();
		svc.setConfiguration(conf);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, -10);

		DocumentFile containerDraft = new DocumentFile();
		containerDraft.setLastModifiedDate(cal.getTime());
		containerDraft.setDocumentFileTypeId(DocumentService.FILETYPE_SIGNATURE_CONTAINER_DRAFT);

		assertFalse(svc.isSignatureContainerDraftExpired(containerDraft));
	}

	public void testIsSignatureContainerDraftExpired_LastModifiedMoreThanLifetimeAgo_ExpectTrue() {
		Configuration conf = new Configuration();
		conf.setUnfinishedSignatureLifetimeSeconds(120L);

		DocumentService svc = new DocumentService();
		svc.setConfiguration(conf);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, -150);

		DocumentFile containerDraft = new DocumentFile();
		containerDraft.setLastModifiedDate(cal.getTime());
		containerDraft.setDocumentFileTypeId(DocumentService.FILETYPE_SIGNATURE_CONTAINER_DRAFT);

		assertTrue(svc.isSignatureContainerDraftExpired(containerDraft));
	}

	public void testIsSignatureContainerDraftExpired_FileNotContainerDraft_ExpectFalse() {
		Configuration conf = new Configuration();
		conf.setUnfinishedSignatureLifetimeSeconds(120L);

		DocumentService svc = new DocumentService();
		svc.setConfiguration(conf);

		Calendar cal = Calendar.getInstance();
		cal.set(1970, Calendar.JANUARY, 1);

		DocumentFile containerDraft = new DocumentFile();
		containerDraft.setLastModifiedDate(cal.getTime());
		containerDraft.setDocumentFileTypeId(DocumentService.FILETYPE_SIGNATURE_CONTAINER);

		assertFalse(svc.isSignatureContainerDraftExpired(containerDraft));
	}

	public void testIsPossibleToSignFile_DocumentFileDeletedFalse_ExpectTrue() {
		DocumentService svc = new DocumentService();
		DocumentFile file = new DocumentFile();
		file.setDeleted(false);
		file.setDocumentFileTypeId(DocumentService.FILETYPE_DOCUMENT_FILE);
		assertTrue(svc.isPossibleToSignFile(file));
	}

	public void testIsPossibleToSignFile_DocumentFileDeletedNull_ExpectTrue() {
		DocumentService svc = new DocumentService();
		DocumentFile file = new DocumentFile();
		file.setDeleted(null);
		file.setDocumentFileTypeId(DocumentService.FILETYPE_DOCUMENT_FILE);
		assertTrue(svc.isPossibleToSignFile(file));
	}

	public void testIsPossibleToSignFile_DocumentFileDeletedTrue_ExpectFalse() {
		DocumentService svc = new DocumentService();
		DocumentFile file = new DocumentFile();
		file.setDeleted(true);
		file.setDocumentFileTypeId(DocumentService.FILETYPE_DOCUMENT_FILE);
		assertFalse(svc.isPossibleToSignFile(file));
	}

	public void testIsPossibleToSignFile_SignatureContainerDeletedFalse_ExpectFalse() {
		DocumentService svc = new DocumentService();
		DocumentFile file = new DocumentFile();
		file.setDeleted(false);
		file.setDocumentFileTypeId(DocumentService.FILETYPE_SIGNATURE_CONTAINER);
		assertFalse(svc.isPossibleToSignFile(file));
	}

	public void testIsPossibleToSignFile_SignatureContainerDraftDeletedFalse_ExpectFalse() {
		DocumentService svc = new DocumentService();
		DocumentFile file = new DocumentFile();
		file.setDeleted(false);
		file.setDocumentFileTypeId(DocumentService.FILETYPE_SIGNATURE_CONTAINER_DRAFT);
		assertFalse(svc.isPossibleToSignFile(file));
	}

	public void testIsPossibleToSignFile_ZipArchiveDeletedFalse_ExpectFalse() {
		DocumentService svc = new DocumentService();
		DocumentFile file = new DocumentFile();
		file.setDeleted(false);
		file.setDocumentFileTypeId(DocumentService.FILETYPE_ZIP_ARCHIVE);
		assertFalse(svc.isPossibleToSignFile(file));
	}

	public void testIsPossibleToSignFile_UnknownFileTypeDeletedFalse_ExpectFalse() {
		DocumentService svc = new DocumentService();
		DocumentFile file = new DocumentFile();
		file.setDeleted(false);
		file.setDocumentFileTypeId(0);
		assertFalse(svc.isPossibleToSignFile(file));
	}

	public void testIsPossibleToSignFile_FileNull_ExpectFalse() {
		DocumentService svc = new DocumentService();
		assertFalse(svc.isPossibleToSignFile(null));
	}
}

/*$CPS$ This comment was generated by CodePro. Do not edit it.
 * patternId = com.instantiations.assist.eclipse.pattern.testCasePattern
 * strategyId = com.instantiations.assist.eclipse.pattern.testCasePattern.junitTestCase
 * additionalTestNames =
 * assertTrue = false
 * callTestMethod = true
 * createMain = false
 * createSetUp = false
 * createTearDown = false
 * createTestFixture = false
 * createTestStubs = false
 * methods =
 * package = ee.adit.test.service
 * package.sourceFolder = adit-war/src/test/java
 * superclassType = junit.framework.TestCase
 * testCase = DocumentServiceTest
 * testClassType = ee.adit.service.DocumentService
 */