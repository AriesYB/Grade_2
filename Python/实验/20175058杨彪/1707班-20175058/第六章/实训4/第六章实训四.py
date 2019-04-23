# -*- coding: utf-8 -*-
"""
Created on Thu Nov  1 23:26:33 2018

@author: 杨彪 20175058
"""

import pandas as pd
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.ensemble import GradientBoostingRegressor
import matplotlib.pyplot as plt
from sklearn.metrics import mean_absolute_error, mean_squared_error,median_absolute_error,explained_variance_score, r2_score

wine_quality=pd.DataFrame(pd.read_csv("D://data/winequality.csv",encoding='gbk',sep=';'))
#提取数据集特征、标签、特征名
wine_quality_data=wine_quality.iloc[:,0:11].values
wine_quality_target=wine_quality.iloc[:,-1:].values
wine_quality_names=wine_quality.columns[:]
#将wine_quality_data和wine_quality_target划分为训练集和测试集
wine_quality_data_train,wine_quality_data_test,wine_quality_target_train,wine_quality_target_test=train_test_split(wine_quality_data,wine_quality_target,test_size=0.3,random_state=42)
#建立线性回归模型
clf = LinearRegression().fit(wine_quality_data_train,wine_quality_target_train)
print("建立的线性回归模型为:\n",clf)
#预测训练集结果
wine_quality_target_pred = clf.predict(wine_quality_data_test)
print('预测的前20个结果为:','\n',wine_quality_target_pred[:20])
print('winequality数据线性回归模型的平均绝对误差为：', mean_absolute_error(wine_quality_target_test,wine_quality_target_pred))
print('winequality数据线性回归模型的均方误差为：', mean_squared_error(wine_quality_target_test,wine_quality_target_pred))
print('winequality数据线性回归模型的中值绝对误差为：', median_absolute_error(wine_quality_target_test,wine_quality_target_pred))
print('winequality数据线性回归模型的可解释方差值为：', explained_variance_score(wine_quality_target_test,wine_quality_target_pred))
print('winequality数据线性回归模型的R方值为：', r2_score(wine_quality_target_test,wine_quality_target_pred))
print("*"*75)
#建立梯度提升回归模型
GBR= GradientBoostingRegressor().fit(wine_quality_data_train,wine_quality_target_train)
wine_quality_target_pred2 = GBR.predict(wine_quality_data_test)
print('建立的梯度提升回归模型为：','\n',GBR)
print('梯度提升回归树模型的平均绝对误差为：', mean_absolute_error(wine_quality_target_test,wine_quality_target_pred2))
print('梯度提升回归树模型的均方误差为：', mean_squared_error(wine_quality_target_test,wine_quality_target_pred2))
print('梯度提升回归树模型的中值绝对误差为：', median_absolute_error(wine_quality_target_test,wine_quality_target_pred2))
print('梯度提升回归树模型的可解释方差值为：', explained_variance_score(wine_quality_target_test, wine_quality_target_pred2))
print('wine_quality数据梯度提升回归树模型的R方值为：',r2_score(wine_quality_target_test,wine_quality_target_pred2))
print("*"*75)

fig = plt.figure(figsize=(10,6)) ##设定空白画布，并制定大小
##用不同的颜色表示不同数据
plt.plot(range(wine_quality_target_test.shape[0]),wine_quality_target_test,color="blue", linewidth=1.5, linestyle="-")
plt.plot(range(wine_quality_target_test.shape[0]),wine_quality_target_pred,color="red", linewidth=1.5, linestyle="-.")
plt.legend(['真实值','预测值'])
plt.show() ##显示图片