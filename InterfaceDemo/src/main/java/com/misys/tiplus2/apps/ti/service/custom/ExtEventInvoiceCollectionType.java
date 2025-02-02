//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.15 at 06:27:29 PM IST 
//


package com.misys.tiplus2.apps.ti.service.custom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Invoice Collection Details
 * 
 * <p>Java class for ExtEventInvoiceCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExtEventInvoiceCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CINVSRN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CINVNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CINVDAT" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="CFOBAMT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CINVFRAM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CINSUAMT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CCOMAM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CDISCAMT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CDEDUCAM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CPKGAMT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CINVBILN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CINBLDAT" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="CINVPRT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CINVAMT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CFORNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtEventInvoiceCollectionType", propOrder = {
    "cinvsrn",
    "cinvno",
    "cinvdat",
    "cfobamt",
    "cinvfram",
    "cinsuamt",
    "ccomam",
    "cdiscamt",
    "cdeducam",
    "cpkgamt",
    "cinvbiln",
    "cinbldat",
    "cinvprt",
    "cinvamt",
    "cforno"
})
public class ExtEventInvoiceCollectionType {

    @XmlElement(name = "CINVSRN")
    protected String cinvsrn;
    @XmlElement(name = "CINVNO")
    protected String cinvno;
    @XmlElement(name = "CINVDAT")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar cinvdat;
    @XmlElement(name = "CFOBAMT")
    protected String cfobamt;
    @XmlElement(name = "CINVFRAM")
    protected String cinvfram;
    @XmlElement(name = "CINSUAMT")
    protected String cinsuamt;
    @XmlElement(name = "CCOMAM")
    protected String ccomam;
    @XmlElement(name = "CDISCAMT")
    protected String cdiscamt;
    @XmlElement(name = "CDEDUCAM")
    protected String cdeducam;
    @XmlElement(name = "CPKGAMT")
    protected String cpkgamt;
    @XmlElement(name = "CINVBILN")
    protected String cinvbiln;
    @XmlElement(name = "CINBLDAT")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar cinbldat;
    @XmlElement(name = "CINVPRT")
    protected String cinvprt;
    @XmlElement(name = "CINVAMT")
    protected String cinvamt;
    @XmlElement(name = "CFORNO")
    protected String cforno;

    /**
     * Gets the value of the cinvsrn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCINVSRN() {
        return cinvsrn;
    }

    /**
     * Sets the value of the cinvsrn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCINVSRN(String value) {
        this.cinvsrn = value;
    }

    /**
     * Gets the value of the cinvno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCINVNO() {
        return cinvno;
    }

    /**
     * Sets the value of the cinvno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCINVNO(String value) {
        this.cinvno = value;
    }

    /**
     * Gets the value of the cinvdat property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCINVDAT() {
        return cinvdat;
    }

    /**
     * Sets the value of the cinvdat property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCINVDAT(XMLGregorianCalendar value) {
        this.cinvdat = value;
    }

    /**
     * Gets the value of the cfobamt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCFOBAMT() {
        return cfobamt;
    }

    /**
     * Sets the value of the cfobamt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCFOBAMT(String value) {
        this.cfobamt = value;
    }

    /**
     * Gets the value of the cinvfram property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCINVFRAM() {
        return cinvfram;
    }

    /**
     * Sets the value of the cinvfram property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCINVFRAM(String value) {
        this.cinvfram = value;
    }

    /**
     * Gets the value of the cinsuamt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCINSUAMT() {
        return cinsuamt;
    }

    /**
     * Sets the value of the cinsuamt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCINSUAMT(String value) {
        this.cinsuamt = value;
    }

    /**
     * Gets the value of the ccomam property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCCOMAM() {
        return ccomam;
    }

    /**
     * Sets the value of the ccomam property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCCOMAM(String value) {
        this.ccomam = value;
    }

    /**
     * Gets the value of the cdiscamt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCDISCAMT() {
        return cdiscamt;
    }

    /**
     * Sets the value of the cdiscamt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCDISCAMT(String value) {
        this.cdiscamt = value;
    }

    /**
     * Gets the value of the cdeducam property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCDEDUCAM() {
        return cdeducam;
    }

    /**
     * Sets the value of the cdeducam property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCDEDUCAM(String value) {
        this.cdeducam = value;
    }

    /**
     * Gets the value of the cpkgamt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCPKGAMT() {
        return cpkgamt;
    }

    /**
     * Sets the value of the cpkgamt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCPKGAMT(String value) {
        this.cpkgamt = value;
    }

    /**
     * Gets the value of the cinvbiln property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCINVBILN() {
        return cinvbiln;
    }

    /**
     * Sets the value of the cinvbiln property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCINVBILN(String value) {
        this.cinvbiln = value;
    }

    /**
     * Gets the value of the cinbldat property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCINBLDAT() {
        return cinbldat;
    }

    /**
     * Sets the value of the cinbldat property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCINBLDAT(XMLGregorianCalendar value) {
        this.cinbldat = value;
    }

    /**
     * Gets the value of the cinvprt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCINVPRT() {
        return cinvprt;
    }

    /**
     * Sets the value of the cinvprt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCINVPRT(String value) {
        this.cinvprt = value;
    }

    /**
     * Gets the value of the cinvamt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCINVAMT() {
        return cinvamt;
    }

    /**
     * Sets the value of the cinvamt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCINVAMT(String value) {
        this.cinvamt = value;
    }

    /**
     * Gets the value of the cforno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCFORNO() {
        return cforno;
    }

    /**
     * Sets the value of the cforno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCFORNO(String value) {
        this.cforno = value;
    }

}
