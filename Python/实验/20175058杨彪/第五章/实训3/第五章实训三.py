# -*- coding: utf-8 -*-
"""
Created on Thu Oct 25 21:55:43 2018

@author: 杨彪 20175058
"""

import pandas as pd
#读取数据
model=pd.DataFrame(pd.read_excel("D://data/model.xls",encoding='gbk'))
#自定义标准差标准化函数
def StandardScaler(data):
    data=(data-data.mean())/data.std()
    return data
std1=StandardScaler(model['电量趋势下降指标'])
std2=StandardScaler(model['线损指标'])
std3=StandardScaler(model['告警类指标'])
print("\n",std1)
print("\n",std2)
print("\n",std3)

