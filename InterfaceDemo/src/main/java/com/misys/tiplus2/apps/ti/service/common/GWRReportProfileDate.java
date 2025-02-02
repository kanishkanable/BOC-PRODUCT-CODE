//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.27 at 01:02:20 PM IST 
//


package com.misys.tiplus2.apps.ti.service.common;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for GWRReportProfileDate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GWRReportProfileDate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AbsoluteDate" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRDate" minOccurs="0"/>
 *         &lt;element name="RelativeDate" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRDateAbbr" minOccurs="0"/>
 *         &lt;element name="AbsoluteOrRelative" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRDateAbsOrRel" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GWRReportProfileDate", propOrder = {
    "absoluteDate",
    "relativeDate",
    "absoluteOrRelative"
})
public class GWRReportProfileDate {

    @XmlElementRef(name = "AbsoluteDate", namespace = "urn:common.service.ti.apps.tiplus2.misys.com", type = JAXBElement.class)
    protected JAXBElement<XMLGregorianCalendar> absoluteDate;
    @XmlElementRef(name = "RelativeDate", namespace = "urn:common.service.ti.apps.tiplus2.misys.com", type = JAXBElement.class)
    protected JAXBElement<String> relativeDate;
    @XmlElement(name = "AbsoluteOrRelative")
    protected GWRDateAbsOrRel absoluteOrRelative;

    /**
     * Gets the value of the absoluteDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getAbsoluteDate() {
        return absoluteDate;
    }

    /**
     * Sets the value of the absoluteDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setAbsoluteDate(JAXBElement<XMLGregorianCalendar> value) {
        this.absoluteDate = value;
    }

    /**
     * Gets the value of the relativeDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRelativeDate() {
        return relativeDate;
    }

    /**
     * Sets the value of the relativeDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRelativeDate(JAXBElement<String> value) {
        this.relativeDate = value;
    }

    /**
     * Gets the value of the absoluteOrRelative property.
     * 
     * @return
     *     possible object is
     *     {@link GWRDateAbsOrRel }
     *     
     */
    public GWRDateAbsOrRel getAbsoluteOrRelative() {
        return absoluteOrRelative;
    }

    /**
     * Sets the value of the absoluteOrRelative property.
     * 
     * @param value
     *     allowed object is
     *     {@link GWRDateAbsOrRel }
     *     
     */
    public void setAbsoluteOrRelative(GWRDateAbsOrRel value) {
        this.absoluteOrRelative = value;
    }

}
