#!/usr/bin/python
# -*- coding: utf-8 -*- 

####################################################################
# Script    : calc_sum_cnt_dict_red.py
# PURPOSE   : The hive streaming method to calculate the key value pair for some element. This is the map period.
#
# CREATED:    2014-07-11    Ralph Hu
#
#
# MODIFIED 
# DATE        AUTHOR            DESCRIPTION
# -------------------------------------------------------------------
# 
#####################################################################

import sys

for line in sys.stdin :
    line = line.strip()
    
    # Split line to different fields
    fields = line.split('\t')
    if len(fields) != 3:
        continue
    
    key, subkey, value = line.split('\t')
    
    try:
        # Combine data of province
        amt = {}
        cnt = {}
        amt[subkey] = int(float(value))
        cnt[subkey] = 1
        
        # Export
        print '\t'.join([key, amt.__str__(), cnt.__str__()])
    except Exception as e:
        raise Exception('[UPA]%s###%s' % (e, line))
