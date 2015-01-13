#!/usr/bin/python
# -*- coding: utf-8 -*- 

####################################################################
# Script    : calc_sum_cnt_dict_red.py
# PURPOSE   : The hive streaming method to calculate the key value pair for some element. This is the reduce period.
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

def printDict(dict):
    res = ''
    for (k, v) in dict.items():
        res += '"%s":%s,' % (k, v)
        
    if len(res) > 0:
        res = res[0 : len(res) - 1]
    return '{' + res + '}'

prev_key = ''
all_amt = {}
all_cnt = {}
for line in sys.stdin :
    line = line.strip()
    
    # Split line to different fields
    key, amt, cnt = line.split('\t')
    
    # Get amt map and cnt map
    amt = eval(amt)
    cnt = eval(cnt)
    
    # Found a boundary; emit current key data
    if prev_key != '' and key != prev_key:
        print >> sys.stdout, '\t'.join([prev_key, printDict(all_amt), printDict(all_cnt)])
        prev_key = key
        all_amt = amt
        all_cnt = cnt
    else:
        prev_key = key
        for key, value in amt.items():
            if all_amt.has_key(key):
                all_amt[key] = int(float(all_amt[key])) + int(float(amt[key]))
            else:
                all_amt[key] = int(float(amt[key]))
                
        for key, value in cnt.items():
            if all_cnt.has_key(key):
                all_cnt[key] = int(float(all_cnt[key])) + int(float(cnt[key]))
            else:
                all_cnt[key] = int(float(cnt[key]))

# Process last key
if prev_key != '':
    print >> sys.stdout, '\t'.join([prev_key, printDict(all_amt), printDict(all_cnt)])
