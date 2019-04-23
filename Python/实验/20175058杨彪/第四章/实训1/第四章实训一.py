# -*- coding: utf-8 -*-
"""
Created on Sun Oct 21 13:58:46 2018

@author: 杨彪 20175058
"""

import pandas as pd

#读取CSV文件
master=pd.read_csv("D://data/Training_Master.csv",sep=',',header='infer',encoding='gbk')
user_update=pd.read_csv("D://data/Training_Userupdate.csv",sep=',',header='infer',)
log_info=pd.read_csv("D://data/Training_LogInfo.csv",sep=',',header='infer',)
arr_df=[master,user_update,log_info]
arr_name=["Master","Userupdate","LogInfo"]
for df,name in zip(arr_df,arr_name):
    print(name,"的维度大小:",df.ndim,"\n",name,"的大小:",df.shape,"\n",name,"的占用内存信息:\n",df.memory_usage(index=True,deep=True))
    print("*"*50)
for df,name in zip(arr_df,arr_name):
    print("进行",name,"描述性统计:\n",df.describe())
    print("*"*60)
for df,name in zip(arr_df,arr_name):
    df.dropna(axis=1,how='all')#axis 0为行 1为列
    for i in df.columns:
        if df[i].var==0:
            df.drop(labels=i,axis=1,inplace='true')
    print("剔除",name,"值相同或者全为空的列")
