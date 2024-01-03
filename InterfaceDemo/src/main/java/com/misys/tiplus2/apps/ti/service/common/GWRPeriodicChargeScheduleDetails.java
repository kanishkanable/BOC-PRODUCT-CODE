//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.22 at 03:37:05 PM IST 
//


package com.misys.tiplus2.apps.ti.service.common;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GWRPeriodicChargeScheduleDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GWRPeriodicChargeScheduleDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Period" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRPeriod" minOccurs="0"/>
 *         &lt;element name="AdvanceOrArrears" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRAdvanceOrArrears" minOccurs="0"/>
 *         &lt;element name="PeriodicChargeCycleNotApportioned" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRBool" minOccurs="0"/>
 *         &lt;element name="PeriodicInterestFullCycle" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRBool" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GWRPeriodicChargeScheduleDetails", propOrder = {
    "period",
    "advanceOrArrears",
    "periodicChargeCycleNotApportioned",
    "periodicInterestFullCycle"
})
public class GWRPeriodicChargeScheduleDetails {

    @XmlElement(name = "Period")
    protected GWRPeriod period;
    @XmlElementRef(name = "AdvanceOrArrears", namespace = "urn:common.service.ti.apps.tiplus2.misys.com", type = JAXBElement.class)
    protected JAXBElement<GWRAdvanceOrArrears> advanceOrArrears;
    @XmlElement(name = "PeriodicChargeCycleNotApportioned")
    protected EnigmaBoolean periodicChargeCycleNotApportioned;
    @XmlElement(name = "PeriodicInterestFullCycle")
    protected EnigmaBoolean periodicInterestFullCycle;

    /**
     * Gets the value of the period property.
     * 
     * @return
     *     possible object is
     *     {@link GWRPeriod }
     *     
     */
    public GWRPeriod getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     * @param value
     *     allowed object is
     *     {@link GWRPeriod }
     *     
     */
    public void setPeriod(GWRPeriod value) {
        this.period = value;
    }

    /**
     * Gets the value of the advanceOrArrears property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GWRAdvanceOrArrears }{@code >}
     *     
     */
    public JAXBElement<GWRAdvanceOrArrears> getAdvanceOrArrears() {
        return advanceOrArrears;
    }

    /**
     * Sets the value of the advanceOrArrears property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GWRAdvanceOrArrears }{@code >}
     *     
     */
    public void setAdvanceOrArrears(JAXBElement<GWRAdvanceOrArrears> value) {
        this.advanceOrArrears = ((JAXBElement<GWRAdvanceOrArrears> ) value);
    }

    /**
     * Gets the value of the periodicChargeCycleNotApportioned property.
     * 
     * @return
     *     possible object is
     *     {@link EnigmaBoolean }
     *     
     */
    public EnigmaBoolean getPeriodicChargeCycleNotApportioned() {
        return periodicChargeCycleNotApportioned;
    }

    /**
     * Sets the value of the periodicChargeCycleNotApportioned property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnigmaBoolean }
     *     
     */
    public void setPeriodicChargeCycleNotApportioned(EnigmaBoolean value) {
        this.periodicChargeCycleNotApportioned = value;
    }

    /**
     * Gets the value of the periodicInterestFullCycle property.
     * 
     * @return
     *     possible object is
     *     {@link EnigmaBoolean }
     *     
     */
    public EnigmaBoolean getPeriodicInterestFullCycle() {
        return periodicInterestFullCycle;
    }

    /**
     * Sets the value of the periodicInterestFullCycle property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnigmaBoolean }
     *     
     */
    public void setPeriodicInterestFullCycle(EnigmaBoolean value) {
        this.periodicInterestFullCycle = value;
    }

}
