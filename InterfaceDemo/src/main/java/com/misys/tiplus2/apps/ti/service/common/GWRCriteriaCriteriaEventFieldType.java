//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.22 at 03:37:05 PM IST 
//


package com.misys.tiplus2.apps.ti.service.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GWRCriteria_Criteria_EventFieldType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GWRCriteria_Criteria_EventFieldType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="a"/>
 *     &lt;enumeration value="b"/>
 *     &lt;enumeration value="c"/>
 *     &lt;enumeration value="q"/>
 *     &lt;enumeration value="t"/>
 *     &lt;enumeration value="u"/>
 *     &lt;enumeration value="v"/>
 *     &lt;enumeration value="d"/>
 *     &lt;enumeration value="i"/>
 *     &lt;enumeration value="l"/>
 *     &lt;enumeration value="n"/>
 *     &lt;enumeration value="p"/>
 *     &lt;enumeration value="r"/>
 *     &lt;enumeration value="s"/>
 *     &lt;minLength value="1"/>
 *     &lt;maxLength value="1"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GWRCriteria_Criteria_EventFieldType")
@XmlEnum
public enum GWRCriteriaCriteriaEventFieldType {

    @XmlEnumValue("a")
    A("a"),
    @XmlEnumValue("b")
    B("b"),
    @XmlEnumValue("c")
    C("c"),
    @XmlEnumValue("q")
    Q("q"),
    @XmlEnumValue("t")
    T("t"),
    @XmlEnumValue("u")
    U("u"),
    @XmlEnumValue("v")
    V("v"),
    @XmlEnumValue("d")
    D("d"),
    @XmlEnumValue("i")
    I("i"),
    @XmlEnumValue("l")
    L("l"),
    @XmlEnumValue("n")
    N("n"),
    @XmlEnumValue("p")
    P("p"),
    @XmlEnumValue("r")
    R("r"),
    @XmlEnumValue("s")
    S("s");
    private final String value;

    GWRCriteriaCriteriaEventFieldType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GWRCriteriaCriteriaEventFieldType fromValue(String v) {
        for (GWRCriteriaCriteriaEventFieldType c: GWRCriteriaCriteriaEventFieldType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
