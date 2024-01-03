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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Customer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Customer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UC_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PANNO_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IECODE_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CUSCAT_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CIFM_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NPAFLAG_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EXPOCAT_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CONREP_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SOUOCR_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CREXPID_Name" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="IFSC_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PRMEID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PRMGR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="REGCER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="REGMEM" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="REGSEZ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GOLDIS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GOLDATE" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="GOLEXD" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="SANUMB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SANEXPD" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ACCTOP" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="CONBOR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ULTBEN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FEMAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TRADIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ECGCOV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="STUSHR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EXPSTS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TIENG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SEGMNT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CATGRY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="REGION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AREA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PRFCEN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RBICLSF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="REGCON" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ECONOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="INDUST" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ORGAN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PSLRBIC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FCRARG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MHALET" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MHLETD" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="FAXIL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OTHEDT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FANUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EMID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EXPDT" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="CUSACC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HMBRA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SBPLC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BENFLC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SUBREQ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Customer", propOrder = {
    "ucName",
    "pannoName",
    "iecodeName",
    "cuscatName",
    "cifmName",
    "npaflagName",
    "expocatName",
    "conrepName",
    "souocrName",
    "crexpidName",
    "ifscName",
    "prmeid",
    "prmgr",
    "regcer",
    "regmem",
    "regsez",
    "goldis",
    "goldate",
    "golexd",
    "sanumb",
    "sanexpd",
    "acctop",
    "conbor",
    "ultben",
    "femad",
    "tradin",
    "ecgcov",
    "stushr",
    "expsts",
    "tieng",
    "segmnt",
    "catgry",
    "region",
    "area",
    "prfcen",
    "rbiclsf",
    "regcon",
    "econom",
    "indust",
    "organ",
    "pslrbic",
    "fcrarg",
    "mhalet",
    "mhletd",
    "faxil",
    "othedt",
    "fanum",
    "emid",
    "expdt",
    "cusacc",
    "hmbra",
    "sbplc",
    "benflc",
    "subreq"
})
@XmlSeeAlso({
    CcCustomer.class
})
public class Customer {

    @XmlElement(name = "UC_Name")
    protected String ucName;
    @XmlElement(name = "PANNO_Name")
    protected String pannoName;
    @XmlElement(name = "IECODE_Name")
    protected String iecodeName;
    @XmlElement(name = "CUSCAT_Name")
    protected String cuscatName;
    @XmlElement(name = "CIFM_Name")
    protected String cifmName;
    @XmlElement(name = "NPAFLAG_Name")
    protected String npaflagName;
    @XmlElement(name = "EXPOCAT_Name")
    protected String expocatName;
    @XmlElement(name = "CONREP_Name")
    protected String conrepName;
    @XmlElement(name = "SOUOCR_Name")
    protected String souocrName;
    @XmlElement(name = "CREXPID_Name")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar crexpidName;
    @XmlElement(name = "IFSC_Name")
    protected String ifscName;
    @XmlElement(name = "PRMEID")
    protected String prmeid;
    @XmlElement(name = "PRMGR")
    protected String prmgr;
    @XmlElement(name = "REGCER")
    protected String regcer;
    @XmlElement(name = "REGMEM")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar regmem;
    @XmlElement(name = "REGSEZ")
    protected String regsez;
    @XmlElement(name = "GOLDIS")
    protected String goldis;
    @XmlElement(name = "GOLDATE")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar goldate;
    @XmlElement(name = "GOLEXD")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar golexd;
    @XmlElement(name = "SANUMB")
    protected String sanumb;
    @XmlElement(name = "SANEXPD")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar sanexpd;
    @XmlElement(name = "ACCTOP")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar acctop;
    @XmlElement(name = "CONBOR")
    protected String conbor;
    @XmlElement(name = "ULTBEN")
    protected String ultben;
    @XmlElement(name = "FEMAD")
    protected String femad;
    @XmlElement(name = "TRADIN")
    protected String tradin;
    @XmlElement(name = "ECGCOV")
    protected String ecgcov;
    @XmlElement(name = "STUSHR")
    protected String stushr;
    @XmlElement(name = "EXPSTS")
    protected String expsts;
    @XmlElement(name = "TIENG")
    protected String tieng;
    @XmlElement(name = "SEGMNT")
    protected String segmnt;
    @XmlElement(name = "CATGRY")
    protected String catgry;
    @XmlElement(name = "REGION")
    protected String region;
    @XmlElement(name = "AREA")
    protected String area;
    @XmlElement(name = "PRFCEN")
    protected String prfcen;
    @XmlElement(name = "RBICLSF")
    protected String rbiclsf;
    @XmlElement(name = "REGCON")
    protected String regcon;
    @XmlElement(name = "ECONOM")
    protected String econom;
    @XmlElement(name = "INDUST")
    protected String indust;
    @XmlElement(name = "ORGAN")
    protected String organ;
    @XmlElement(name = "PSLRBIC")
    protected String pslrbic;
    @XmlElement(name = "FCRARG")
    protected String fcrarg;
    @XmlElement(name = "MHALET")
    protected String mhalet;
    @XmlElement(name = "MHLETD")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar mhletd;
    @XmlElement(name = "FAXIL")
    protected String faxil;
    @XmlElement(name = "OTHEDT")
    protected String othedt;
    @XmlElement(name = "FANUM")
    protected String fanum;
    @XmlElement(name = "EMID")
    protected String emid;
    @XmlElement(name = "EXPDT")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expdt;
    @XmlElement(name = "CUSACC")
    protected String cusacc;
    @XmlElement(name = "HMBRA")
    protected String hmbra;
    @XmlElement(name = "SBPLC")
    protected String sbplc;
    @XmlElement(name = "BENFLC")
    protected String benflc;
    @XmlElement(name = "SUBREQ")
    protected String subreq;

    /**
     * Gets the value of the ucName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUCName() {
        return ucName;
    }

    /**
     * Sets the value of the ucName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUCName(String value) {
        this.ucName = value;
    }

    /**
     * Gets the value of the pannoName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPANNOName() {
        return pannoName;
    }

    /**
     * Sets the value of the pannoName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPANNOName(String value) {
        this.pannoName = value;
    }

    /**
     * Gets the value of the iecodeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIECODEName() {
        return iecodeName;
    }

    /**
     * Sets the value of the iecodeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIECODEName(String value) {
        this.iecodeName = value;
    }

    /**
     * Gets the value of the cuscatName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCUSCATName() {
        return cuscatName;
    }

    /**
     * Sets the value of the cuscatName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCUSCATName(String value) {
        this.cuscatName = value;
    }

    /**
     * Gets the value of the cifmName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCIFMName() {
        return cifmName;
    }

    /**
     * Sets the value of the cifmName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCIFMName(String value) {
        this.cifmName = value;
    }

    /**
     * Gets the value of the npaflagName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNPAFLAGName() {
        return npaflagName;
    }

    /**
     * Sets the value of the npaflagName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNPAFLAGName(String value) {
        this.npaflagName = value;
    }

    /**
     * Gets the value of the expocatName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEXPOCATName() {
        return expocatName;
    }

    /**
     * Sets the value of the expocatName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEXPOCATName(String value) {
        this.expocatName = value;
    }

    /**
     * Gets the value of the conrepName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCONREPName() {
        return conrepName;
    }

    /**
     * Sets the value of the conrepName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCONREPName(String value) {
        this.conrepName = value;
    }

    /**
     * Gets the value of the souocrName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSOUOCRName() {
        return souocrName;
    }

    /**
     * Sets the value of the souocrName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSOUOCRName(String value) {
        this.souocrName = value;
    }

    /**
     * Gets the value of the crexpidName property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCREXPIDName() {
        return crexpidName;
    }

    /**
     * Sets the value of the crexpidName property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCREXPIDName(XMLGregorianCalendar value) {
        this.crexpidName = value;
    }

    /**
     * Gets the value of the ifscName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIFSCName() {
        return ifscName;
    }

    /**
     * Sets the value of the ifscName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIFSCName(String value) {
        this.ifscName = value;
    }

    /**
     * Gets the value of the prmeid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRMEID() {
        return prmeid;
    }

    /**
     * Sets the value of the prmeid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRMEID(String value) {
        this.prmeid = value;
    }

    /**
     * Gets the value of the prmgr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRMGR() {
        return prmgr;
    }

    /**
     * Sets the value of the prmgr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRMGR(String value) {
        this.prmgr = value;
    }

    /**
     * Gets the value of the regcer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREGCER() {
        return regcer;
    }

    /**
     * Sets the value of the regcer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREGCER(String value) {
        this.regcer = value;
    }

    /**
     * Gets the value of the regmem property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getREGMEM() {
        return regmem;
    }

    /**
     * Sets the value of the regmem property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setREGMEM(XMLGregorianCalendar value) {
        this.regmem = value;
    }

    /**
     * Gets the value of the regsez property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREGSEZ() {
        return regsez;
    }

    /**
     * Sets the value of the regsez property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREGSEZ(String value) {
        this.regsez = value;
    }

    /**
     * Gets the value of the goldis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGOLDIS() {
        return goldis;
    }

    /**
     * Sets the value of the goldis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGOLDIS(String value) {
        this.goldis = value;
    }

    /**
     * Gets the value of the goldate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGOLDATE() {
        return goldate;
    }

    /**
     * Sets the value of the goldate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGOLDATE(XMLGregorianCalendar value) {
        this.goldate = value;
    }

    /**
     * Gets the value of the golexd property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGOLEXD() {
        return golexd;
    }

    /**
     * Sets the value of the golexd property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGOLEXD(XMLGregorianCalendar value) {
        this.golexd = value;
    }

    /**
     * Gets the value of the sanumb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSANUMB() {
        return sanumb;
    }

    /**
     * Sets the value of the sanumb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSANUMB(String value) {
        this.sanumb = value;
    }

    /**
     * Gets the value of the sanexpd property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSANEXPD() {
        return sanexpd;
    }

    /**
     * Sets the value of the sanexpd property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSANEXPD(XMLGregorianCalendar value) {
        this.sanexpd = value;
    }

    /**
     * Gets the value of the acctop property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getACCTOP() {
        return acctop;
    }

    /**
     * Sets the value of the acctop property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setACCTOP(XMLGregorianCalendar value) {
        this.acctop = value;
    }

    /**
     * Gets the value of the conbor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCONBOR() {
        return conbor;
    }

    /**
     * Sets the value of the conbor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCONBOR(String value) {
        this.conbor = value;
    }

    /**
     * Gets the value of the ultben property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getULTBEN() {
        return ultben;
    }

    /**
     * Sets the value of the ultben property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setULTBEN(String value) {
        this.ultben = value;
    }

    /**
     * Gets the value of the femad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFEMAD() {
        return femad;
    }

    /**
     * Sets the value of the femad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFEMAD(String value) {
        this.femad = value;
    }

    /**
     * Gets the value of the tradin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTRADIN() {
        return tradin;
    }

    /**
     * Sets the value of the tradin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTRADIN(String value) {
        this.tradin = value;
    }

    /**
     * Gets the value of the ecgcov property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getECGCOV() {
        return ecgcov;
    }

    /**
     * Sets the value of the ecgcov property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setECGCOV(String value) {
        this.ecgcov = value;
    }

    /**
     * Gets the value of the stushr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTUSHR() {
        return stushr;
    }

    /**
     * Sets the value of the stushr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTUSHR(String value) {
        this.stushr = value;
    }

    /**
     * Gets the value of the expsts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEXPSTS() {
        return expsts;
    }

    /**
     * Sets the value of the expsts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEXPSTS(String value) {
        this.expsts = value;
    }

    /**
     * Gets the value of the tieng property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIENG() {
        return tieng;
    }

    /**
     * Sets the value of the tieng property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIENG(String value) {
        this.tieng = value;
    }

    /**
     * Gets the value of the segmnt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSEGMNT() {
        return segmnt;
    }

    /**
     * Sets the value of the segmnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSEGMNT(String value) {
        this.segmnt = value;
    }

    /**
     * Gets the value of the catgry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCATGRY() {
        return catgry;
    }

    /**
     * Sets the value of the catgry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCATGRY(String value) {
        this.catgry = value;
    }

    /**
     * Gets the value of the region property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREGION() {
        return region;
    }

    /**
     * Sets the value of the region property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREGION(String value) {
        this.region = value;
    }

    /**
     * Gets the value of the area property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAREA() {
        return area;
    }

    /**
     * Sets the value of the area property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAREA(String value) {
        this.area = value;
    }

    /**
     * Gets the value of the prfcen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRFCEN() {
        return prfcen;
    }

    /**
     * Sets the value of the prfcen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRFCEN(String value) {
        this.prfcen = value;
    }

    /**
     * Gets the value of the rbiclsf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRBICLSF() {
        return rbiclsf;
    }

    /**
     * Sets the value of the rbiclsf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRBICLSF(String value) {
        this.rbiclsf = value;
    }

    /**
     * Gets the value of the regcon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREGCON() {
        return regcon;
    }

    /**
     * Sets the value of the regcon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREGCON(String value) {
        this.regcon = value;
    }

    /**
     * Gets the value of the econom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getECONOM() {
        return econom;
    }

    /**
     * Sets the value of the econom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setECONOM(String value) {
        this.econom = value;
    }

    /**
     * Gets the value of the indust property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINDUST() {
        return indust;
    }

    /**
     * Sets the value of the indust property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINDUST(String value) {
        this.indust = value;
    }

    /**
     * Gets the value of the organ property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getORGAN() {
        return organ;
    }

    /**
     * Sets the value of the organ property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setORGAN(String value) {
        this.organ = value;
    }

    /**
     * Gets the value of the pslrbic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPSLRBIC() {
        return pslrbic;
    }

    /**
     * Sets the value of the pslrbic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPSLRBIC(String value) {
        this.pslrbic = value;
    }

    /**
     * Gets the value of the fcrarg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFCRARG() {
        return fcrarg;
    }

    /**
     * Sets the value of the fcrarg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFCRARG(String value) {
        this.fcrarg = value;
    }

    /**
     * Gets the value of the mhalet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMHALET() {
        return mhalet;
    }

    /**
     * Sets the value of the mhalet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMHALET(String value) {
        this.mhalet = value;
    }

    /**
     * Gets the value of the mhletd property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMHLETD() {
        return mhletd;
    }

    /**
     * Sets the value of the mhletd property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMHLETD(XMLGregorianCalendar value) {
        this.mhletd = value;
    }

    /**
     * Gets the value of the faxil property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFAXIL() {
        return faxil;
    }

    /**
     * Sets the value of the faxil property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFAXIL(String value) {
        this.faxil = value;
    }

    /**
     * Gets the value of the othedt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOTHEDT() {
        return othedt;
    }

    /**
     * Sets the value of the othedt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOTHEDT(String value) {
        this.othedt = value;
    }

    /**
     * Gets the value of the fanum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFANUM() {
        return fanum;
    }

    /**
     * Sets the value of the fanum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFANUM(String value) {
        this.fanum = value;
    }

    /**
     * Gets the value of the emid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEMID() {
        return emid;
    }

    /**
     * Sets the value of the emid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMID(String value) {
        this.emid = value;
    }

    /**
     * Gets the value of the expdt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEXPDT() {
        return expdt;
    }

    /**
     * Sets the value of the expdt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEXPDT(XMLGregorianCalendar value) {
        this.expdt = value;
    }

    /**
     * Gets the value of the cusacc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCUSACC() {
        return cusacc;
    }

    /**
     * Sets the value of the cusacc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCUSACC(String value) {
        this.cusacc = value;
    }

    /**
     * Gets the value of the hmbra property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHMBRA() {
        return hmbra;
    }

    /**
     * Sets the value of the hmbra property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHMBRA(String value) {
        this.hmbra = value;
    }

    /**
     * Gets the value of the sbplc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSBPLC() {
        return sbplc;
    }

    /**
     * Sets the value of the sbplc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSBPLC(String value) {
        this.sbplc = value;
    }

    /**
     * Gets the value of the benflc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBENFLC() {
        return benflc;
    }

    /**
     * Sets the value of the benflc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBENFLC(String value) {
        this.benflc = value;
    }

    /**
     * Gets the value of the subreq property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSUBREQ() {
        return subreq;
    }

    /**
     * Sets the value of the subreq property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSUBREQ(String value) {
        this.subreq = value;
    }

}
