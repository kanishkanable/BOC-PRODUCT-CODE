//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.22 at 03:37:05 PM IST 
//


package com.misys.tiplus2.apps.ti.service.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GWRBankCodes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GWRBankCodes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BankCode1" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRUserOption" minOccurs="0"/>
 *         &lt;element name="BankCode2" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRUserOption" minOccurs="0"/>
 *         &lt;element name="BankCode3" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRUserOption" minOccurs="0"/>
 *         &lt;element name="BankCode4" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRUserOption" minOccurs="0"/>
 *         &lt;element name="BankCode5" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRUserOption" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GWRBankCodes", propOrder = {
    "bankCode1",
    "bankCode2",
    "bankCode3",
    "bankCode4",
    "bankCode5"
})
public class GWRBankCodes {

    @XmlElement(name = "BankCode1")
    protected String bankCode1;
    @XmlElement(name = "BankCode2")
    protected String bankCode2;
    @XmlElement(name = "BankCode3")
    protected String bankCode3;
    @XmlElement(name = "BankCode4")
    protected String bankCode4;
    @XmlElement(name = "BankCode5")
    protected String bankCode5;

    /**
     * Gets the value of the bankCode1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankCode1() {
        return bankCode1;
    }

    /**
     * Sets the value of the bankCode1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankCode1(String value) {
        this.bankCode1 = value;
    }

    /**
     * Gets the value of the bankCode2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankCode2() {
        return bankCode2;
    }

    /**
     * Sets the value of the bankCode2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankCode2(String value) {
        this.bankCode2 = value;
    }

    /**
     * Gets the value of the bankCode3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankCode3() {
        return bankCode3;
    }

    /**
     * Sets the value of the bankCode3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankCode3(String value) {
        this.bankCode3 = value;
    }

    /**
     * Gets the value of the bankCode4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankCode4() {
        return bankCode4;
    }

    /**
     * Sets the value of the bankCode4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankCode4(String value) {
        this.bankCode4 = value;
    }

    /**
     * Gets the value of the bankCode5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankCode5() {
        return bankCode5;
    }

    /**
     * Sets the value of the bankCode5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankCode5(String value) {
        this.bankCode5 = value;
    }

}
