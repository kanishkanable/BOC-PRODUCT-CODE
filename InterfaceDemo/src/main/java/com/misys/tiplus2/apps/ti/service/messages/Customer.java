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
import com.misys.tiplus2.apps.ti.service.custom.CcCustomer;
import com.misys.tiplus2.apps.ti.service.custom.TtTICustomer;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{urn:messages.service.ti.apps.tiplus2.misys.com}STRCustomer">
 *       &lt;sequence>
 *         &lt;element name="CustomerExtraData" type="{urn:custom.service.ti.apps.tiplus2.misys.com}CcCustomer" minOccurs="0"/>
 *         &lt;element name="TICustomerExtraData" type="{urn:custom.service.ti.apps.tiplus2.misys.com}TtTICustomer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "customerExtraData", "tiCustomerExtraData" })
public class Customer extends STRCustomer {
	@XmlElement(name = "CustomerExtraData")
	protected CcCustomer customerExtraData;
	@XmlElement(name = "TICustomerExtraData")
	protected TtTICustomer tiCustomerExtraData;

	/**
	 * Gets the value of the customerExtraData property.
	 * 
	 * @return possible object is {@link CcCustomer }
	 * 
	 */
	public CcCustomer getCustomerExtraData() {
		return customerExtraData;
	}

	/**
	 * Sets the value of the customerExtraData property.
	 * 
	 * @param value
	 *            allowed object is {@link CcCustomer }
	 * 
	 */
	public void setCustomerExtraData(CcCustomer value) {
		this.customerExtraData = value;
	}

	/**
	 * Gets the value of the tiCustomerExtraData property.
	 * 
	 * @return possible object is {@link TtTICustomer }
	 * 
	 */
	public TtTICustomer getTICustomerExtraData() {
		return tiCustomerExtraData;
	}

	/**
	 * Sets the value of the tiCustomerExtraData property.
	 * 
	 * @param value
	 *            allowed object is {@link TtTICustomer }
	 * 
	 */
	public void setTICustomerExtraData(TtTICustomer value) {
		this.tiCustomerExtraData = value;
	}

}
