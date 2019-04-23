# -*- coding: utf-8 -*-
"""
Created on Sun Oct 21 16:40:44 2018

@author: 杨彪 20175058
"""

import numpy as np
import pandas as pd

#读取CSV文件
user_update=pd.read_csv("D://data/Training_Userupdate.csv",sep=',',header='infer',)
log_info=pd.read_csv("D://data/Training_LogInfo.csv",sep=',',header='infer',)
#对时间表进行分组
group1=user_update.groupby(by='Idx')
group2=log_info.groupby(by='Idx')
#求更新和登录的最早最晚时间
detail1=group1['ListingInfo1','UserupdateInfo2'].agg([np.max,np.min])#使用np的最大值 最小值函数
detail2=group2['Listinginfo1','LogInfo3'].agg([np.max,np.min])
print(detail1)
print(detail2)
#求更新次数和登录次数
counts1=group1.size().head()
counts2=group2.size().head()
print(counts1)
print(counts2)

