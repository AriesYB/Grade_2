# -*- coding: utf-8 -*-
"""
Created on Thu Oct 25 20:40:43 2018

@author: 杨彪 20175058
"""

import pandas as pd

#读取CSV文件
ele_loss=pd.DataFrame(pd.read_csv("D://data/ele_loss.csv",encoding='gbk'))
alarm=pd.DataFrame(pd.read_csv("D://data/alarm.csv",encoding='gbk'))
print("表ele_loss的形状",ele_loss.shape)
print("表alarm的形状",alarm.shape)
data=pd.merge(ele_loss,alarm,how='inner',left_on=['ID','date'],right_on=['ID','date'])
print("合并后的数据:\n",data)