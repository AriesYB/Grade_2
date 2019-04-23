# -*- coding: utf-8 -*-
"""
Created on Sun Oct 21 22:10:09 2018

@author: 杨彪20175058
"""

import pandas as pd
from scipy.interpolate import lagrange

#读取CSV文件
data=pd.DataFrame(pd.read_excel("D://data/missing_data.xls",header=0))
print("缺失值所在列以及数量:\n",data.isnull().sum())
#插值函数
def ploy(s,n,k=2):
    y=s[list(range(n-k,n))+list(range(n+1,n+1-k))]
    y=y[y.notnull()]
    return lagrange(y.index,list(y))(n)
for i in data.columns:
    for j in range(len(data)):
        if(data[i].isnull())[j]:
            data[i][j]=ploy(data[i],j)
print("插值后的数据:\n",data)
print("判断是否存在缺失值:\n",data.isnull().sum())
print("无缺失值，说明插值成功")

