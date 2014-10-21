Hive_UDF
========

平时工作中遇到一些汇总HIVE原生的UDF不能满足需求，故开发一些定制化的UDF

使用方法:
进入hive后

 add jar /mnt/data/etl_framework/script/java/udf/udf.jar;
 create temporary function getlastdate  as 'com.upa.hadoop.hive.GetLastDateFunction';
