//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.22 at 03:37:05 PM IST 
//


package com.misys.tiplus2.apps.ti.service.common;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GWRMarginDeposit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GWRMarginDeposit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AmountFieldCode" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWREventFieldCode" minOccurs="0"/>
 *         &lt;element name="Account" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRAccountNumber" minOccurs="0"/>
 *         &lt;element name="Code" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRCollateralTypeCode" minOccurs="0"/>
 *         &lt;element name="Description" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRText140" minOccurs="0"/>
 *         &lt;element name="MarginAmount" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRMoney" minOccurs="0"/>
 *         &lt;element name="Percentage" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRPercentAmount" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GWRMarginDeposit", propOrder = {
    "amountFieldCode",
    "account",
    "code",
    "description",
    "marginAmount",
    "percentage"
})
public class GWRMarginDeposit {

    @XmlElement(name = "AmountFieldCode")
    protected String amountFieldCode;
    @XmlElement(name = "Account")
    protected String account;
    @XmlElement(name = "Code")
    protected String code;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "MarginAmount")
    protected GWRMoney marginAmount;
    @XmlElement(name = "Percentage")
    protected BigDecimal percentage;

    /**
     * Gets the value of the amountFieldCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmountFieldCode() {
        return amountFieldCode;
    }

    /**
     * Sets the value of the amountFieldCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmountFieldCode(String value) {
        this.amountFieldCode = value;
    }

    /**
     * Gets the value of the account property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccount() {
        return account;
    }

    /**
     * Sets the value of the account property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccount(String value) {
        this.account = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

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
     * Gets the value of the marginAmount property.
     * 
     * @return
     *     possible object is
     *     {@link GWRMoney }
     *     
     */
    public GWRMoney getMarginAmount() {
        return marginAmount;
    }

    /**
     * Sets the value of the marginAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link GWRMoney }
     *     
     */
    public void setMarginAmount(GWRMoney value) {
        this.marginAmount = value;
    }

    /**
     * Gets the value of the percentage property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPercentage() {
        return percentage;
    }

    /**
     * Sets the value of the percentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPercentage(BigDecimal value) {
        this.percentage = value;
    }

}
