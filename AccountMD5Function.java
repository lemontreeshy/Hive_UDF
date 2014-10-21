/*******************************************************************
* CLASS		: com.upa.hadoop.hive.HiveUDF
* PURPOSE	: User defined function to calculate the MD5 value for a string taken as an account
*
*
*
* MODIFIED 
* DATE		AUTHOR			DESCRIPTION
* -------------------------------------------------------------------
* 
********************************************************************/

package com.upa.hadoop.hive;

import java.text.DecimalFormat;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import com.upa.util.EncryptionUtility;


public class AccountMD5Function extends UDF {
	
	public String rawMD5(String src) {
		String str = new DecimalFormat("00").format(src.length()) + src;
		String md5 = EncryptionUtility.toMD5(str);
		
		return md5;
	}
	
	public Text evaluate(Text input) {
		return new Text(rawMD5(input.toString()));
	}
}
