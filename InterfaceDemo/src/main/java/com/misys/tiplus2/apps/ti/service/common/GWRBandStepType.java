//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.27 at 01:02:20 PM IST 
//


package com.misys.tiplus2.apps.ti.service.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GWRBandStepType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GWRBandStepType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="a2"/>
 *     &lt;enumeration value="i"/>
 *     &lt;enumeration value="a1"/>
 *     &lt;minLength value="1"/>
 *     &lt;maxLength value="2"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GWRBandStepType")
@XmlEnum
public enum GWRBandStepType {

    @XmlEnumValue("a2")
    A_2("a2"),
    @XmlEnumValue("i")
    I("i"),
    @XmlEnumValue("a1")
    A_1("a1");
    private final String value;

    GWRBandStepType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GWRBandStepType fromValue(String v) {
        for (GWRBandStepType c: GWRBandStepType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
