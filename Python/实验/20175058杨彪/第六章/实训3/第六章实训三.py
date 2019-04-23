# -*- coding: utf-8 -*-
"""
Created on Thu Nov  1 21:20:47 2018

@author: 杨彪 20175058
"""

import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler
from sklearn.svm import SVC
from sklearn.metrics import classification_report
import numpy as np

wine=pd.DataFrame(pd.read_csv("D://data/wine.csv",encoding='gbk'))
#提取数据集特征、标签
wine_data=wine.iloc[:,1:14].values
wine_target=wine.iloc[:,0].values
#将wine_data和wine_target划分为训练集和测试集
wine_data_train,wine_data_test,wine_target_train,wine_target_test=train_test_split(wine_data,wine_target,test_size=0.2,random_state=22)
#离差标准化wine数据集
Scale=MinMaxScaler().fit(wine_data_train)
stdScaler_train=Scale.transform(wine_data_train)
stdScaler_test=Scale.transform(wine_data_test)
#构建SVC模型
svm = SVC().fit(stdScaler_train,wine_target_train)
print("建立的SVC模型为：\n",svm)
#预测测试集结果
wine_target_pred = svm.predict(stdScaler_test)
print("预测的前20个结果为：\n:",wine_target_pred[:20])
#与真实值对比计算准确率
true = np.sum(wine_target_pred == wine_target_test)
print("预测对的结果数量为：",true)
print("预测错的结果数量为：",wine_target_test.shape[0]-true)
print("预测的准确率为：",true/wine_target_test.shape[0])
#分类报告
report =classification_report(wine_target_test,wine_target_pred)
print("使用SVC方法预测wine数据的分类报告为：\n",report)
