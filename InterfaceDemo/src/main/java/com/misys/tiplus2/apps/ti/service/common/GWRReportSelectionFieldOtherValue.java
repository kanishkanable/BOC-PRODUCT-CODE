//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.27 at 01:02:20 PM IST 
//


package com.misys.tiplus2.apps.ti.service.common;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GWRReportSelectionFieldOtherValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GWRReportSelectionFieldOtherValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRReportTypeName" minOccurs="0"/>
 *         &lt;element name="DataModelMemberName" type="{urn:common.service.ti.apps.tiplus2.misys.com}GWRReportTypeField" minOccurs="0"/>
 *         &lt;element name="OtherFieldValueToAdds" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="OtherFieldValueToAdd" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="DisplayValue" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="50"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="StoreValue" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="50"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="OtherFieldValueToDeletes" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="OtherFieldValueToDelete" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="DisplayValue" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="50"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="StoreValue" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="50"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GWRReportSelectionFieldOtherValue", propOrder = {
    "name",
    "dataModelMemberName",
    "otherFieldValueToAdds",
    "otherFieldValueToDeletes"
})
public class GWRReportSelectionFieldOtherValue {

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "DataModelMemberName")
    protected String dataModelMemberName;
    @XmlElement(name = "OtherFieldValueToAdds")
    protected GWRReportSelectionFieldOtherValue.OtherFieldValueToAdds otherFieldValueToAdds;
    @XmlElement(name = "OtherFieldValueToDeletes")
    protected GWRReportSelectionFieldOtherValue.OtherFieldValueToDeletes otherFieldValueToDeletes;

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
     * Gets the value of the dataModelMemberName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataModelMemberName() {
        return dataModelMemberName;
    }

    /**
     * Sets the value of the dataModelMemberName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataModelMemberName(String value) {
        this.dataModelMemberName = value;
    }

    /**
     * Gets the value of the otherFieldValueToAdds property.
     * 
     * @return
     *     possible object is
     *     {@link GWRReportSelectionFieldOtherValue.OtherFieldValueToAdds }
     *     
     */
    public GWRReportSelectionFieldOtherValue.OtherFieldValueToAdds getOtherFieldValueToAdds() {
        return otherFieldValueToAdds;
    }

    /**
     * Sets the value of the otherFieldValueToAdds property.
     * 
     * @param value
     *     allowed object is
     *     {@link GWRReportSelectionFieldOtherValue.OtherFieldValueToAdds }
     *     
     */
    public void setOtherFieldValueToAdds(GWRReportSelectionFieldOtherValue.OtherFieldValueToAdds value) {
        this.otherFieldValueToAdds = value;
    }

    /**
     * Gets the value of the otherFieldValueToDeletes property.
     * 
     * @return
     *     possible object is
     *     {@link GWRReportSelectionFieldOtherValue.OtherFieldValueToDeletes }
     *     
     */
    public GWRReportSelectionFieldOtherValue.OtherFieldValueToDeletes getOtherFieldValueToDeletes() {
        return otherFieldValueToDeletes;
    }

    /**
     * Sets the value of the otherFieldValueToDeletes property.
     * 
     * @param value
     *     allowed object is
     *     {@link GWRReportSelectionFieldOtherValue.OtherFieldValueToDeletes }
     *     
     */
    public void setOtherFieldValueToDeletes(GWRReportSelectionFieldOtherValue.OtherFieldValueToDeletes value) {
        this.otherFieldValueToDeletes = value;
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
     *         &lt;element name="OtherFieldValueToAdd" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="DisplayValue" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="50"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="StoreValue" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="50"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "otherFieldValueToAdd"
    })
    public static class OtherFieldValueToAdds {

        @XmlElement(name = "OtherFieldValueToAdd", required = true)
        protected List<GWRReportSelectionFieldOtherValue.OtherFieldValueToAdds.OtherFieldValueToAdd> otherFieldValueToAdd;

        /**
         * Gets the value of the otherFieldValueToAdd property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the otherFieldValueToAdd property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOtherFieldValueToAdd().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link GWRReportSelectionFieldOtherValue.OtherFieldValueToAdds.OtherFieldValueToAdd }
         * 
         * 
         */
        public List<GWRReportSelectionFieldOtherValue.OtherFieldValueToAdds.OtherFieldValueToAdd> getOtherFieldValueToAdd() {
            if (otherFieldValueToAdd == null) {
                otherFieldValueToAdd = new ArrayList<GWRReportSelectionFieldOtherValue.OtherFieldValueToAdds.OtherFieldValueToAdd>();
            }
            return this.otherFieldValueToAdd;
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
         *         &lt;element name="DisplayValue" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="50"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="StoreValue" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="50"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
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
            "displayValue",
            "storeValue"
        })
        public static class OtherFieldValueToAdd {

            @XmlElement(name = "DisplayValue")
            protected String displayValue;
            @XmlElement(name = "StoreValue")
            protected String storeValue;

            /**
             * Gets the value of the displayValue property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDisplayValue() {
                return displayValue;
            }

            /**
             * Sets the value of the displayValue property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDisplayValue(String value) {
                this.displayValue = value;
            }

            /**
             * Gets the value of the storeValue property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStoreValue() {
                return storeValue;
            }

            /**
             * Sets the value of the storeValue property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStoreValue(String value) {
                this.storeValue = value;
            }

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
     *         &lt;element name="OtherFieldValueToDelete" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="DisplayValue" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="50"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="StoreValue" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="50"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "otherFieldValueToDelete"
    })
    public static class OtherFieldValueToDeletes {

        @XmlElement(name = "OtherFieldValueToDelete", required = true)
        protected List<GWRReportSelectionFieldOtherValue.OtherFieldValueToDeletes.OtherFieldValueToDelete> otherFieldValueToDelete;

        /**
         * Gets the value of the otherFieldValueToDelete property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the otherFieldValueToDelete property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOtherFieldValueToDelete().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link GWRReportSelectionFieldOtherValue.OtherFieldValueToDeletes.OtherFieldValueToDelete }
         * 
         * 
         */
        public List<GWRReportSelectionFieldOtherValue.OtherFieldValueToDeletes.OtherFieldValueToDelete> getOtherFieldValueToDelete() {
            if (otherFieldValueToDelete == null) {
                otherFieldValueToDelete = new ArrayList<GWRReportSelectionFieldOtherValue.OtherFieldValueToDeletes.OtherFieldValueToDelete>();
            }
            return this.otherFieldValueToDelete;
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
         *         &lt;element name="DisplayValue" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="50"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="StoreValue" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="50"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
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
            "displayValue",
            "storeValue"
        })
        public static class OtherFieldValueToDelete {

            @XmlElement(name = "DisplayValue")
            protected String displayValue;
            @XmlElement(name = "StoreValue")
            protected String storeValue;

            /**
             * Gets the value of the displayValue property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDisplayValue() {
                return displayValue;
            }

            /**
             * Sets the value of the displayValue property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDisplayValue(String value) {
                this.displayValue = value;
            }

            /**
             * Gets the value of the storeValue property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStoreValue() {
                return storeValue;
            }

            /**
             * Sets the value of the storeValue property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStoreValue(String value) {
                this.storeValue = value;
            }

        }

    }

}
