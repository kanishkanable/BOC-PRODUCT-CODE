//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.22 at 03:37:05 PM IST 
//


package com.misys.tiplus2.apps.ti.service.messages;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SLADashboardReallocateTransactionsRequest_ReallocateTransactions_Status.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SLADashboardReallocateTransactionsRequest_ReallocateTransactions_Status">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="F"/>
 *     &lt;enumeration value="S"/>
 *     &lt;enumeration value="W"/>
 *     &lt;maxLength value="1"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SLADashboardReallocateTransactionsRequest_ReallocateTransactions_Status")
@XmlEnum
public enum SLADashboardReallocateTransactionsRequestReallocateTransactionsStatus {

    F,
    S,
    W;

    public String value() {
        return name();
    }

    public static SLADashboardReallocateTransactionsRequestReallocateTransactionsStatus fromValue(String v) {
        return valueOf(v);
    }

}
