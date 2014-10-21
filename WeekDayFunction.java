/*******************************************************************
 * CLASS		: com.upa.hadoop.hive.WeekFunction
 * PURPOSE	: 
 *
 * CREATED:	2014-07-23	Huangyong shan
 *
 *
 * use step
 * 
 * add jar /mnt/data/etl_framework/script/java/udf/udf.jar;
 * create temporary function getweekday  as 'com.upa.hadoop.hive.WeekDayFunction';
 * MODIFIED 
 * DATE		AUTHOR			DESCRIPTION
 * -------------------------------------------------------------------
 * 
 ********************************************************************/

package com.upa.hadoop.hive;

import java.util.Calendar;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.IntWritable;

public class WeekDayFunction extends UDF {

	private IntWritable result = new IntWritable();
	
	public int getweekday(int dt){
		
		Calendar calendar = Calendar.getInstance();	
		calendar.set(Calendar.DATE,dt%100);
		calendar.set(Calendar.MONTH,(dt%10000)/100 -1);		calendar.set(Calendar.YEAR,dt/10000);	
		
		//String i = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK) - 1);
		int i=calendar.get(Calendar.DAY_OF_WEEK) - 1;
		
		return i ;		
	}
	

	public IntWritable evaluate(IntWritable dt) {

		if ( dt == null) {
			return null;
		}
		
		result.set(getweekday(dt.get()));
		return result;
	}
	
}
