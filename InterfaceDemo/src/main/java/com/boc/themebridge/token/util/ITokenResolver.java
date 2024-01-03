/*
 *	Copyright 2015 (C) Encore Theme
 *
 *	Class Name: ITokenResolver.java 
 *
 * 	Author		 : Raghu M	
 * 	Created on 	 : 07 APR, 2015
 * -------------------------------------------------------------------------
 * Revision History
 * -------------------------------------------------------------------------
 *  v1.0.0	|	07/04/2015	|	Raghu M		|	Initial ITokenResolver
 *  
 */
package com.boc.themebridge.token.util;

public interface ITokenResolver {
	public String resolveToken(String tokenName);
}