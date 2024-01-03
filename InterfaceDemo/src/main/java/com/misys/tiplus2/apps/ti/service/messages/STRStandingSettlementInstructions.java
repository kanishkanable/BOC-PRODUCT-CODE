//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.22 at 03:37:05 PM IST 
//


package com.misys.tiplus2.apps.ti.service.messages;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import com.misys.tiplus2.apps.ti.service.common.GWRAddPaySIDetails;
import com.misys.tiplus2.apps.ti.service.common.GWRCustomer;
import com.misys.tiplus2.apps.ti.service.common.GWRPayReceive;
import com.misys.tiplus2.apps.ti.service.common.GWRPayReceiveType;
import com.misys.tiplus2.apps.ti.service.common.GWRSSIDetails;


/**
 * Standing settlement instructions .
 * 
 * <p>Java class for STRStandingSettlementInstructions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="STRStandingSettlementInstructions">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:messages.service.ti.apps.tiplus2.misys.com}GatewayStatic">
 *       &lt;sequence>
 *         &lt;element name="BankingEntity" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRBranch" minOccurs="0"/>
 *         &lt;element name="PayReceive" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRPayReceive" minOccurs="0"/>
 *         &lt;element name="Branch" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRBranch" minOccurs="0"/>
 *         &lt;element name="MovementType" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRPayReceiveType" minOccurs="0"/>
 *         &lt;element name="Currency" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRCurrency" minOccurs="0"/>
 *         &lt;element name="Customer" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRCustomer" minOccurs="0"/>
 *         &lt;element name="InstructionGroup" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRInstructionGroup" minOccurs="0"/>
 *         &lt;element name="SSIDetails" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRSSIDetails" minOccurs="0"/>
 *         &lt;element name="SettlementCurrency" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRCurrency" minOccurs="0"/>
 *         &lt;element name="AddPaySIDetails" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRAddPaySIDetails" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "STRStandingSettlementInstructions", propOrder = {
    "bankingEntity",
    "payReceive",
    "branch",
    "movementType",
    "currency",
    "customer",
    "instructionGroup",
    "ssiDetails",
    "settlementCurrency",
    "addPaySIDetails"
})
public class STRStandingSettlementInstructions
    extends GatewayStatic
{

    @XmlElement(name = "BankingEntity")
    protected String bankingEntity;
    @XmlElementRef(name = "PayReceive", namespace = "urn:messages.service.ti.apps.tiplus2.misys.com", type = JAXBElement.class)
    protected JAXBElement<GWRPayReceive> payReceive;
    @XmlElement(name = "Branch")
    protected String branch;
    @XmlElementRef(name = "MovementType", namespace = "urn:messages.service.ti.apps.tiplus2.misys.com", type = JAXBElement.class)
    protected JAXBElement<GWRPayReceiveType> movementType;
    @XmlElement(name = "Currency")
    protected String currency;
    @XmlElement(name = "Customer")
    protected GWRCustomer customer;
    @XmlElement(name = "InstructionGroup")
    protected String instructionGroup;
    @XmlElement(name = "SSIDetails")
    protected GWRSSIDetails ssiDetails;
    @XmlElement(name = "SettlementCurrency")
    protected String settlementCurrency;
    @XmlElement(name = "AddPaySIDetails")
    protected GWRAddPaySIDetails addPaySIDetails;

    /**
     * Gets the value of the bankingEntity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankingEntity() {
        return bankingEntity;
    }

    /**
     * Sets the value of the bankingEntity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankingEntity(String value) {
        this.bankingEntity = value;
    }

    /**
     * Gets the value of the payReceive property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GWRPayReceive }{@code >}
     *     
     */
    public JAXBElement<GWRPayReceive> getPayReceive() {
        return payReceive;
    }

    /**
     * Sets the value of the payReceive property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GWRPayReceive }{@code >}
     *     
     */
    public void setPayReceive(JAXBElement<GWRPayReceive> value) {
        this.payReceive = ((JAXBElement<GWRPayReceive> ) value);
    }

    /**
     * Gets the value of the branch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Sets the value of the branch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranch(String value) {
        this.branch = value;
    }

    /**
     * Gets the value of the movementType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GWRPayReceiveType }{@code >}
     *     
     */
    public JAXBElement<GWRPayReceiveType> getMovementType() {
        return movementType;
    }

    /**
     * Sets the value of the movementType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GWRPayReceiveType }{@code >}
     *     
     */
    public void setMovementType(JAXBElement<GWRPayReceiveType> value) {
        this.movementType = ((JAXBElement<GWRPayReceiveType> ) value);
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the customer property.
     * 
     * @return
     *     possible object is
     *     {@link GWRCustomer }
     *     
     */
    public GWRCustomer getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value
     *     allowed object is
     *     {@link GWRCustomer }
     *     
     */
    public void setCustomer(GWRCustomer value) {
        this.customer = value;
    }

    /**
     * Gets the value of the instructionGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstructionGroup() {
        return instructionGroup;
    }

    /**
     * Sets the value of the instructionGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstructionGroup(String value) {
        this.instructionGroup = value;
    }

    /**
     * Gets the value of the ssiDetails property.
     * 
     * @return
     *     possible object is
     *     {@link GWRSSIDetails }
     *     
     */
    public GWRSSIDetails getSSIDetails() {
        return ssiDetails;
    }

    /**
     * Sets the value of the ssiDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link GWRSSIDetails }
     *     
     */
    public void setSSIDetails(GWRSSIDetails value) {
        this.ssiDetails = value;
    }

    /**
     * Gets the value of the settlementCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettlementCurrency() {
        return settlementCurrency;
    }

    /**
     * Sets the value of the settlementCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSettlementCurrency(String value) {
        this.settlementCurrency = value;
    }

    /**
     * Gets the value of the addPaySIDetails property.
     * 
     * @return
     *     possible object is
     *     {@link GWRAddPaySIDetails }
     *     
     */
    public GWRAddPaySIDetails getAddPaySIDetails() {
        return addPaySIDetails;
    }

    /**
     * Sets the value of the addPaySIDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link GWRAddPaySIDetails }
     *     
     */
    public void setAddPaySIDetails(GWRAddPaySIDetails value) {
        this.addPaySIDetails = value;
    }

}
