//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.13 at 05:48:11 PM IST 
//


package com.xsd.slcustoms;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Table1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Table1">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Bank">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="4"/>
 *               &lt;pattern value="[0-9][0-9][0-9][0-9]"/>
 *               &lt;whiteSpace value="collapse"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Branch">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="3"/>
 *               &lt;pattern value="[0-9][0-9][0-9]"/>
 *               &lt;whiteSpace value="collapse"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Name">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="2"/>
 *               &lt;maxLength value="50"/>
 *               &lt;whiteSpace value="collapse"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Add1">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Add2">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Add3">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="NIC_CR_BR">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *               &lt;whiteSpace value="collapse"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="VatNo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="17"/>
 *               &lt;whiteSpace value="collapse"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BName">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="2"/>
 *               &lt;maxLength value="50"/>
 *               &lt;whiteSpace value="collapse"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BAdd">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BCountry">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ECA">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *               &lt;maxInclusive value="2"/>
 *               &lt;minInclusive value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="FEP">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *               &lt;maxInclusive value="2"/>
 *               &lt;minInclusive value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CurrCode">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="3"/>
 *               &lt;pattern value="[a-zA-Z][a-zA-Z][a-zA-Z]"/>
 *               &lt;whiteSpace value="collapse"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Amount">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;maxExclusive value="200000000000"/>
 *               &lt;minExclusive value="0"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ToP">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="2"/>
 *               &lt;maxLength value="2"/>
 *               &lt;pattern value="[0-9][0-9]"/>
 *               &lt;whiteSpace value="collapse"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ToD">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="3"/>
 *               &lt;maxLength value="3"/>
 *               &lt;pattern value="[a-zA-Z][a-zA-Z][a-zA-Z]"/>
 *               &lt;whiteSpace value="collapse"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="RefNo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="2"/>
 *               &lt;maxLength value="30"/>
 *               &lt;whiteSpace value="collapse"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LCNo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *               &lt;whiteSpace value="collapse"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Table1", propOrder = {
    "bank",
    "branch",
    "name",
    "add1",
    "add2",
    "add3",
    "niccrbr",
    "vatNo",
    "bName",
    "bAdd",
    "bCountry",
    "eca",
    "fep",
    "currCode",
    "amount",
    "toP",
    "toD",
    "refNo",
    "lcNo",
    "date"
})
public class Table1 {

    @XmlElement(name = "Bank", required = true)
    protected String bank;
    @XmlElement(name = "Branch", required = true)
    protected String branch;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Add1", required = true)
    protected String add1;
    @XmlElement(name = "Add2", required = true)
    protected String add2;
    @XmlElement(name = "Add3", required = true)
    protected String add3;
    @XmlElement(name = "NIC_CR_BR", required = true)
    protected String niccrbr;
    @XmlElement(name = "VatNo", required = true)
    protected String vatNo;
    @XmlElement(name = "BName", required = true)
    protected String bName;
    @XmlElement(name = "BAdd", required = true)
    protected String bAdd;
    @XmlElement(name = "BCountry", required = true)
    protected String bCountry;
    @XmlElement(name = "ECA")
    protected int eca;
    @XmlElement(name = "FEP")
    protected int fep;
    @XmlElement(name = "CurrCode", required = true)
    protected String currCode;
    @XmlElement(name = "Amount", required = true)
    protected BigDecimal amount;
    @XmlElement(name = "ToP", required = true)
    protected String toP;
    @XmlElement(name = "ToD", required = true)
    protected String toD;
    @XmlElement(name = "RefNo", required = true)
    protected String refNo;
    @XmlElement(name = "LCNo", required = true)
    protected String lcNo;
    @XmlElement(name = "Date", required = true)
    @XmlSchemaType(name = "date")
    protected String date;

    /**
     * Gets the value of the bank property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBank() {
        return bank;
    }

    /**
     * Sets the value of the bank property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBank(String value) {
        this.bank = value;
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
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the add1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdd1() {
        return add1;
    }

    /**
     * Sets the value of the add1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdd1(String value) {
        this.add1 = value;
    }

    /**
     * Gets the value of the add2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdd2() {
        return add2;
    }

    /**
     * Sets the value of the add2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdd2(String value) {
        this.add2 = value;
    }

    /**
     * Gets the value of the add3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdd3() {
        return add3;
    }

    /**
     * Sets the value of the add3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdd3(String value) {
        this.add3 = value;
    }

    /**
     * Gets the value of the niccrbr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNICCRBR() {
        return niccrbr;
    }

    /**
     * Sets the value of the niccrbr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNICCRBR(String value) {
        this.niccrbr = value;
    }

    /**
     * Gets the value of the vatNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVatNo() {
        return vatNo;
    }

    /**
     * Sets the value of the vatNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVatNo(String value) {
        this.vatNo = value;
    }

    /**
     * Gets the value of the bName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBName() {
        return bName;
    }

    /**
     * Sets the value of the bName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBName(String value) {
        this.bName = value;
    }

    /**
     * Gets the value of the bAdd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBAdd() {
        return bAdd;
    }

    /**
     * Sets the value of the bAdd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBAdd(String value) {
        this.bAdd = value;
    }

    /**
     * Gets the value of the bCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBCountry() {
        return bCountry;
    }

    /**
     * Sets the value of the bCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBCountry(String value) {
        this.bCountry = value;
    }

    /**
     * Gets the value of the eca property.
     * 
     */
	/*
	 * public double getECA() { return eca; }
	 */
    public int getECA() {
        return eca;
    }
    /**
     * Sets the value of the eca property.
     * 
     */
    public void setECA(int value) {
        this.eca = value;
    }

    /**
     * Gets the value of the fep property.
     * 
     */
    public int getFEP() {
        return fep;
    }

    /**
     * Sets the value of the fep property.
     * 
     */
    public void setFEP(int value) {
        this.fep = value;
    }

    /**
     * Gets the value of the currCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrCode() {
        return currCode;
    }

    /**
     * Sets the value of the currCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrCode(String value) {
        this.currCode = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the value of the toP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToP() {
        return toP;
    }

    /**
     * Sets the value of the toP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToP(String value) {
        this.toP = value;
    }

    /**
     * Gets the value of the toD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToD() {
        return toD;
    }

    /**
     * Sets the value of the toD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToD(String value) {
        this.toD = value;
    }

    /**
     * Gets the value of the refNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefNo() {
        return refNo;
    }

    /**
     * Sets the value of the refNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefNo(String value) {
        this.refNo = value;
    }

    /**
     * Gets the value of the lcNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLCNo() {
        return lcNo;
    }

    /**
     * Sets the value of the lcNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLCNo(String value) {
        this.lcNo = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

}
