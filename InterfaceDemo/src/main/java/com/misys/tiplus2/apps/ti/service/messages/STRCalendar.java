//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.22 at 03:37:05 PM IST 
//


package com.misys.tiplus2.apps.ti.service.messages;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.misys.tiplus2.apps.ti.service.common.EnigmaBoolean;


/**
 * <p>Java class for STRCalendar complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="STRCalendar">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:messages.service.ti.apps.tiplus2.misys.com}GatewayStatic">
 *       &lt;sequence>
 *         &lt;element name="Description" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRCalendarDescription" minOccurs="0"/>
 *         &lt;element name="MondayIsWorkingDay" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRBool" minOccurs="0"/>
 *         &lt;element name="TuesdayIsWorkingDay" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRBool" minOccurs="0"/>
 *         &lt;element name="WednesdayIsWorkingDay" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRBool" minOccurs="0"/>
 *         &lt;element name="ThursdayIsWorkingDay" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRBool" minOccurs="0"/>
 *         &lt;element name="FridayIsWorkingDay" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRBool" minOccurs="0"/>
 *         &lt;element name="SaturdayIsWorkingDay" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRBool" minOccurs="0"/>
 *         &lt;element name="SundayIsWorkingDay" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRBool" minOccurs="0"/>
 *         &lt;element name="SpecialNonWorkingDays" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TIDate" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SpecialWorkingDays" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TIDate" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="CalendarYear" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRInteger" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "STRCalendar", propOrder = {
    "description",
    "mondayIsWorkingDay",
    "tuesdayIsWorkingDay",
    "wednesdayIsWorkingDay",
    "thursdayIsWorkingDay",
    "fridayIsWorkingDay",
    "saturdayIsWorkingDay",
    "sundayIsWorkingDay",
    "specialNonWorkingDays",
    "specialWorkingDays",
    "calendarYear"
})
@XmlSeeAlso({
    STRCountryCalendar.class,
    STRProcessingCycleCalendar.class,
    STRBranchCalendar.class,
    STRCurrencyCalendar.class
})
public class STRCalendar
    extends GatewayStatic
{

    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "MondayIsWorkingDay")
    protected EnigmaBoolean mondayIsWorkingDay;
    @XmlElement(name = "TuesdayIsWorkingDay")
    protected EnigmaBoolean tuesdayIsWorkingDay;
    @XmlElement(name = "WednesdayIsWorkingDay")
    protected EnigmaBoolean wednesdayIsWorkingDay;
    @XmlElement(name = "ThursdayIsWorkingDay")
    protected EnigmaBoolean thursdayIsWorkingDay;
    @XmlElement(name = "FridayIsWorkingDay")
    protected EnigmaBoolean fridayIsWorkingDay;
    @XmlElement(name = "SaturdayIsWorkingDay")
    protected EnigmaBoolean saturdayIsWorkingDay;
    @XmlElement(name = "SundayIsWorkingDay")
    protected EnigmaBoolean sundayIsWorkingDay;
    @XmlElement(name = "SpecialNonWorkingDays")
    protected STRCalendar.SpecialNonWorkingDays specialNonWorkingDays;
    @XmlElement(name = "SpecialWorkingDays")
    protected STRCalendar.SpecialWorkingDays specialWorkingDays;
    @XmlElementRef(name = "CalendarYear", namespace = "urn:messages.service.ti.apps.tiplus2.misys.com", type = JAXBElement.class)
    protected JAXBElement<Integer> calendarYear;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the mondayIsWorkingDay property.
     * 
     * @return
     *     possible object is
     *     {@link EnigmaBoolean }
     *     
     */
    public EnigmaBoolean getMondayIsWorkingDay() {
        return mondayIsWorkingDay;
    }

    /**
     * Sets the value of the mondayIsWorkingDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnigmaBoolean }
     *     
     */
    public void setMondayIsWorkingDay(EnigmaBoolean value) {
        this.mondayIsWorkingDay = value;
    }

    /**
     * Gets the value of the tuesdayIsWorkingDay property.
     * 
     * @return
     *     possible object is
     *     {@link EnigmaBoolean }
     *     
     */
    public EnigmaBoolean getTuesdayIsWorkingDay() {
        return tuesdayIsWorkingDay;
    }

    /**
     * Sets the value of the tuesdayIsWorkingDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnigmaBoolean }
     *     
     */
    public void setTuesdayIsWorkingDay(EnigmaBoolean value) {
        this.tuesdayIsWorkingDay = value;
    }

    /**
     * Gets the value of the wednesdayIsWorkingDay property.
     * 
     * @return
     *     possible object is
     *     {@link EnigmaBoolean }
     *     
     */
    public EnigmaBoolean getWednesdayIsWorkingDay() {
        return wednesdayIsWorkingDay;
    }

    /**
     * Sets the value of the wednesdayIsWorkingDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnigmaBoolean }
     *     
     */
    public void setWednesdayIsWorkingDay(EnigmaBoolean value) {
        this.wednesdayIsWorkingDay = value;
    }

    /**
     * Gets the value of the thursdayIsWorkingDay property.
     * 
     * @return
     *     possible object is
     *     {@link EnigmaBoolean }
     *     
     */
    public EnigmaBoolean getThursdayIsWorkingDay() {
        return thursdayIsWorkingDay;
    }

    /**
     * Sets the value of the thursdayIsWorkingDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnigmaBoolean }
     *     
     */
    public void setThursdayIsWorkingDay(EnigmaBoolean value) {
        this.thursdayIsWorkingDay = value;
    }

    /**
     * Gets the value of the fridayIsWorkingDay property.
     * 
     * @return
     *     possible object is
     *     {@link EnigmaBoolean }
     *     
     */
    public EnigmaBoolean getFridayIsWorkingDay() {
        return fridayIsWorkingDay;
    }

    /**
     * Sets the value of the fridayIsWorkingDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnigmaBoolean }
     *     
     */
    public void setFridayIsWorkingDay(EnigmaBoolean value) {
        this.fridayIsWorkingDay = value;
    }

    /**
     * Gets the value of the saturdayIsWorkingDay property.
     * 
     * @return
     *     possible object is
     *     {@link EnigmaBoolean }
     *     
     */
    public EnigmaBoolean getSaturdayIsWorkingDay() {
        return saturdayIsWorkingDay;
    }

    /**
     * Sets the value of the saturdayIsWorkingDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnigmaBoolean }
     *     
     */
    public void setSaturdayIsWorkingDay(EnigmaBoolean value) {
        this.saturdayIsWorkingDay = value;
    }

    /**
     * Gets the value of the sundayIsWorkingDay property.
     * 
     * @return
     *     possible object is
     *     {@link EnigmaBoolean }
     *     
     */
    public EnigmaBoolean getSundayIsWorkingDay() {
        return sundayIsWorkingDay;
    }

    /**
     * Sets the value of the sundayIsWorkingDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnigmaBoolean }
     *     
     */
    public void setSundayIsWorkingDay(EnigmaBoolean value) {
        this.sundayIsWorkingDay = value;
    }

    /**
     * Gets the value of the specialNonWorkingDays property.
     * 
     * @return
     *     possible object is
     *     {@link STRCalendar.SpecialNonWorkingDays }
     *     
     */
    public STRCalendar.SpecialNonWorkingDays getSpecialNonWorkingDays() {
        return specialNonWorkingDays;
    }

    /**
     * Sets the value of the specialNonWorkingDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link STRCalendar.SpecialNonWorkingDays }
     *     
     */
    public void setSpecialNonWorkingDays(STRCalendar.SpecialNonWorkingDays value) {
        this.specialNonWorkingDays = value;
    }

    /**
     * Gets the value of the specialWorkingDays property.
     * 
     * @return
     *     possible object is
     *     {@link STRCalendar.SpecialWorkingDays }
     *     
     */
    public STRCalendar.SpecialWorkingDays getSpecialWorkingDays() {
        return specialWorkingDays;
    }

    /**
     * Sets the value of the specialWorkingDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link STRCalendar.SpecialWorkingDays }
     *     
     */
    public void setSpecialWorkingDays(STRCalendar.SpecialWorkingDays value) {
        this.specialWorkingDays = value;
    }

    /**
     * Gets the value of the calendarYear property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getCalendarYear() {
        return calendarYear;
    }

    /**
     * Sets the value of the calendarYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setCalendarYear(JAXBElement<Integer> value) {
        this.calendarYear = ((JAXBElement<Integer> ) value);
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="TIDate" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "tiDate"
    })
    public static class SpecialNonWorkingDays {

        @XmlElement(name = "TIDate", nillable = true)
        @XmlSchemaType(name = "date")
        protected List<XMLGregorianCalendar> tiDate;

        /**
         * Gets the value of the tiDate property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the tiDate property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTIDate().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link XMLGregorianCalendar }
         * 
         * 
         */
        public List<XMLGregorianCalendar> getTIDate() {
            if (tiDate == null) {
                tiDate = new ArrayList<XMLGregorianCalendar>();
            }
            return this.tiDate;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="TIDate" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "tiDate"
    })
    public static class SpecialWorkingDays {

        @XmlElement(name = "TIDate", nillable = true)
        @XmlSchemaType(name = "date")
        protected List<XMLGregorianCalendar> tiDate;

        /**
         * Gets the value of the tiDate property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the tiDate property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTIDate().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link XMLGregorianCalendar }
         * 
         * 
         */
        public List<XMLGregorianCalendar> getTIDate() {
            if (tiDate == null) {
                tiDate = new ArrayList<XMLGregorianCalendar>();
            }
            return this.tiDate;
        }

    }

}
