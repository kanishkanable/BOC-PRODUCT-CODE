//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.22 at 03:37:05 PM IST 
//


package com.misys.tiplus2.apps.ti.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * .
 * 
 * <p>Java class for STRClearingSystemID complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="STRClearingSystemID">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:messages.service.ti.apps.tiplus2.misys.com}GatewayStatic">
 *       &lt;sequence>
 *         &lt;element name="MainBankingEntity" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRMainBankingEntity" minOccurs="0"/>
 *         &lt;element name="Code" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRClearingSystemIDCode" minOccurs="0"/>
 *         &lt;element name="Description" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRFullName" minOccurs="0"/>
 *         &lt;element name="Currency" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRCurrency" minOccurs="0"/>
 *         &lt;element name="PayAccount" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRServiceAccount" minOccurs="0"/>
 *         &lt;element name="ReceiveAccount" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRServiceAccount" minOccurs="0"/>
 *         &lt;element name="BankClearingID" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRBankClearingId" minOccurs="0"/>
 *         &lt;element name="PayTransferMethod" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRTransferMethod" minOccurs="0"/>
 *         &lt;element name="ReceiveTransferMethod" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRTransferMethod" minOccurs="0"/>
 *         &lt;element name="ServiceID" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRClearingSystemServiceId" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "STRClearingSystemID", propOrder = {
    "mainBankingEntity",
    "code",
    "description",
    "currency",
    "payAccount",
    "receiveAccount",
    "bankClearingID",
    "payTransferMethod",
    "receiveTransferMethod",
    "serviceID"
})
public class STRClearingSystemID
    extends GatewayStatic
{

    @XmlElement(name = "MainBankingEntity")
    protected String mainBankingEntity;
    @XmlElement(name = "Code")
    protected String code;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Currency")
    protected String currency;
    @XmlElement(name = "PayAccount")
    protected String payAccount;
    @XmlElement(name = "ReceiveAccount")
    protected String receiveAccount;
    @XmlElement(name = "BankClearingID")
    protected String bankClearingID;
    @XmlElement(name = "PayTransferMethod")
    protected String payTransferMethod;
    @XmlElement(name = "ReceiveTransferMethod")
    protected String receiveTransferMethod;
    @XmlElement(name = "ServiceID")
    protected String serviceID;

    /**
     * Gets the value of the mainBankingEntity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainBankingEntity() {
        return mainBankingEntity;
    }

    /**
     * Sets the value of the mainBankingEntity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainBankingEntity(String value) {
        this.mainBankingEntity = value;
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
     * Gets the value of the payAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayAccount() {
        return payAccount;
    }

    /**
     * Sets the value of the payAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayAccount(String value) {
        this.payAccount = value;
    }

    /**
     * Gets the value of the receiveAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiveAccount() {
        return receiveAccount;
    }

    /**
     * Sets the value of the receiveAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiveAccount(String value) {
        this.receiveAccount = value;
    }

    /**
     * Gets the value of the bankClearingID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankClearingID() {
        return bankClearingID;
    }

    /**
     * Sets the value of the bankClearingID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankClearingID(String value) {
        this.bankClearingID = value;
    }

    /**
     * Gets the value of the payTransferMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayTransferMethod() {
        return payTransferMethod;
    }

    /**
     * Sets the value of the payTransferMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayTransferMethod(String value) {
        this.payTransferMethod = value;
    }

    /**
     * Gets the value of the receiveTransferMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiveTransferMethod() {
        return receiveTransferMethod;
    }

    /**
     * Sets the value of the receiveTransferMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiveTransferMethod(String value) {
        this.receiveTransferMethod = value;
    }

    /**
     * Gets the value of the serviceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceID() {
        return serviceID;
    }

    /**
     * Sets the value of the serviceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceID(String value) {
        this.serviceID = value;
    }

}
