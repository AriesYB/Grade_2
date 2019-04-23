# -*- coding: utf-8 -*-
"""
Created on Sun Oct 21 15:29:17 2018

@author: 杨彪 20175058
"""

import pandas as pd

#读取CSV文件
master=pd.read_csv("D://data/Training_Master.csv",sep=',',header='infer',encoding='gbk')
user_update=pd.read_csv("D://data/Training_Userupdate.csv",sep=',',header='infer',)
log_info=pd.read_csv("D://data/Training_LogInfo.csv",sep=',',header='infer',)
#arr_df=[master,user_update,log_info]
#arr_name=["Master","Userupdate","LogInfo"]
#转换时间字符串
user_update['ListingInfo1']=pd.to_datetime(user_update['ListingInfo1'])
user_update['UserupdateInfo2']=pd.to_datetime(user_update['UserupdateInfo2'])
log_info['Listinginfo1']=pd.to_datetime(log_info['Listinginfo1'])
log_info['LogInfo3']=pd.to_datetime(log_info['LogInfo3'])
#提取时间
for i in user_update['ListingInfo1']:
    year1=[i.year]
    month1=[i.month]
    week1=[i.week]
for i in user_update['UserupdateInfo2']:
    year2=[i.year]
    month2=[i.month]
    week2=[i.week]
for i in log_info['Listinginfo1']:
    year3=[i.year]
    month3=[i.month]
    week3=[i.week]
for i in log_info['LogInfo3']:  
    year4=[i.year]
    month4=[i.month]
    week4=[i.week]
    
#进行时间差的计算
td_day1=[]
td_day2=[]
td_hour1=[]
td_hour2=[]
td_minute1=[]
td_minute2=[]
for i,j in zip(user_update['ListingInfo1'],user_update['UserupdateInfo2']):
    td_day1.append(i.day-j.day)
    td_hour1.append(i.hour-j.hour)
    td_minute1.append(i.minute-j.minute)
for i,j in zip(log_info['Listinginfo1'],log_info['LogInfo3']):
    td_day2.append(i.day-j.day)
    td_hour2.append(i.hour-j.hour)
    td_minute2.append(i.minute-j.minute)  
print(td_day1)
print(td_hour1)
print(td_minute1)