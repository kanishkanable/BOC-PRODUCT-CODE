//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.22 at 03:37:05 PM IST 
//


package com.misys.tiplus2.apps.ti.service.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GWRMT103ServiceLevel.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GWRMT103ServiceLevel">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CRED"/>
 *     &lt;enumeration value="SPRI"/>
 *     &lt;enumeration value="SPAY"/>
 *     &lt;enumeration value="SSTD"/>
 *     &lt;maxLength value="4"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GWRMT103ServiceLevel")
@XmlEnum
public enum GWRMT103ServiceLevel {

    CRED,
    SPRI,
    SPAY,
    SSTD;

    public String value() {
        return name();
    }

    public static GWRMT103ServiceLevel fromValue(String v) {
        return valueOf(v);
    }

}
