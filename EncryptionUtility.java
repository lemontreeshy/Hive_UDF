/*******************************************************************
* CLASS		: com.upa.util.EncryptionUtility
* PURPOSE	: Utilities for Encryption related operation
*
*
*
* MODIFIED 
* DATE		AUTHOR			DESCRIPTION
* -------------------------------------------------------------------
* 
********************************************************************/

package com.upa.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class EncryptionUtility {
	
	public static String toMD5(String str) {
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
			return null;
		}
		
		md.update(str.getBytes());
		byte[] bytes = md.digest();
		return new HexBinaryAdapter().marshal(bytes);
	}
	
	public static String toSHA1(String str) {
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
			return null;
		}
		
		md.update(str.getBytes());
		byte[] bytes = md.digest();
		return new HexBinaryAdapter().marshal(bytes);
	}
	
}
