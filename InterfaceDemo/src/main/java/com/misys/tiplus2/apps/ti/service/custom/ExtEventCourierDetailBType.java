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
 * Courier Details 2
 * 
 * <p>Java class for ExtEventCourierDetailBType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExtEventCourierDetailBType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CORNAMEA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DOCNUMA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DATA" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtEventCourierDetailBType", propOrder = {
    "cornamea",
    "docnuma",
    "data"
})
public class ExtEventCourierDetailBType {

    @XmlElement(name = "CORNAMEA")
    protected String cornamea;
    @XmlElement(name = "DOCNUMA")
    protected String docnuma;
    @XmlElement(name = "DATA")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar data;

    /**
     * Gets the value of the cornamea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCORNAMEA() {
        return cornamea;
    }

    /**
     * Sets the value of the cornamea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCORNAMEA(String value) {
        this.cornamea = value;
    }

    /**
     * Gets the value of the docnuma property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDOCNUMA() {
        return docnuma;
    }

    /**
     * Sets the value of the docnuma property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDOCNUMA(String value) {
        this.docnuma = value;
    }

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDATA() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDATA(XMLGregorianCalendar value) {
        this.data = value;
    }

}
