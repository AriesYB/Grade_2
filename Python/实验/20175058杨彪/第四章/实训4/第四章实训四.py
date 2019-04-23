# -*- coding: utf-8 -*-
"""
Created on Sun Oct 21 17:23:18 2018

@author: 杨彪 20175058
"""

import numpy as np
import pandas as pd

#读取CSV文件
master=pd.read_csv("D://data/Training_Master.csv",sep=',',encoding='gbk')
user_update=pd.read_csv("D://data/Training_Userupdate.csv",sep=',',encoding='gbk')
log_info=pd.read_csv("D://data/Training_LogInfo.csv",sep=',',encoding='gbk')
p_table1=pd.pivot_table(user_update[['Idx','ListingInfo1','UserupdateInfo2']],index='Idx',aggfunc = np.sum)
p_table2=pd.pivot_table(log_info[['Idx','Listinginfo1','LogInfo3']],index='Idx',aggfunc = np.sum)
c_table=pd.crosstab(index=master['Idx'],columns=master['ListingInfo'],values =master['UserInfo_1'],aggfunc = np.sum)

print('用户更新表的透视表：\n', p_table1.head())
print('登陆信息表的透视表：\n',p_table2.head())
print('主表的交叉表：\n',c_table.head())


