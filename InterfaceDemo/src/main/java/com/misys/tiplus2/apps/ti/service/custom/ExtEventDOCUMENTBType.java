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
import javax.xml.bind.annotation.XmlType;


/**
 * DOCUMENT 2
 * 
 * <p>Java class for ExtEventDOCUMENTBType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExtEventDOCUMENTBType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DOCODEA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ORIGA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="COPEA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtEventDOCUMENTBType", propOrder = {
    "docodea",
    "origa",
    "copea"
})
public class ExtEventDOCUMENTBType {

    @XmlElement(name = "DOCODEA")
    protected String docodea;
    @XmlElement(name = "ORIGA")
    protected String origa;
    @XmlElement(name = "COPEA")
    protected String copea;

    /**
     * Gets the value of the docodea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDOCODEA() {
        return docodea;
    }

    /**
     * Sets the value of the docodea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDOCODEA(String value) {
        this.docodea = value;
    }

    /**
     * Gets the value of the origa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getORIGA() {
        return origa;
    }

    /**
     * Sets the value of the origa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setORIGA(String value) {
        this.origa = value;
    }

    /**
     * Gets the value of the copea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOPEA() {
        return copea;
    }

    /**
     * Sets the value of the copea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOPEA(String value) {
        this.copea = value;
    }

}
