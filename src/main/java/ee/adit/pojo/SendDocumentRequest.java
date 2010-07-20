//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.07.20 at 11:37:41 AM EEST 
//


package ee.adit.pojo;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SendDocumentRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SendDocumentRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="document_id" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="recipient_list" type="{http://producers.ametlikud-dokumendid.xtee.riik.ee/producer/ametlikud-dokumendid}ArrayOfUserCode"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SendDocumentRequest", propOrder = {
    "documentId",
    "recipientList"
})
public class SendDocumentRequest {

    @XmlElement(name = "document_id", required = true)
    protected BigInteger documentId;
    @XmlElement(name = "recipient_list", required = true)
    protected ArrayOfUserCode recipientList;

    /**
     * Gets the value of the documentId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDocumentId() {
        return documentId;
    }

    /**
     * Sets the value of the documentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDocumentId(BigInteger value) {
        this.documentId = value;
    }

    /**
     * Gets the value of the recipientList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfUserCode }
     *     
     */
    public ArrayOfUserCode getRecipientList() {
        return recipientList;
    }

    /**
     * Sets the value of the recipientList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfUserCode }
     *     
     */
    public void setRecipientList(ArrayOfUserCode value) {
        this.recipientList = value;
    }

}
