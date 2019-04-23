# -*- coding: utf-8 -*-
"""
Created on Sat Oct 27 18:50:32 2018

@author: 杨彪20175058
"""

import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.preprocessing import MinMaxScaler
from sklearn.decomposition import PCA

wine=pd.DataFrame(pd.read_csv("D://data/wine.csv",encoding='gbk'))
wine_quality=pd.DataFrame(pd.read_csv("D://data/winequality.csv",encoding='gbk',sep=';'))
#提取数据集特征、标签、特征名
wine_data=wine.iloc[:,1:14].values
wine_target=wine.iloc[:,0].values
wine_names=wine.columns[:]
wine_quality_data=wine_quality.iloc[:,0:11].values
wine_quality_target=wine_quality.iloc[:,-1:].values
wine_quality_names=wine_quality.columns[:]
print("wine的数据集形状\n",wine_data.shape)
print("wine的标签形状\n",wine_target.shape)
print("wine_quality的数据集形状\n",wine_quality_data.shape)
print("wine_quality的标签形状\n",wine_quality_target.shape)
#将wine_quality_data划分为训练集和测试集
a=train_test_split(wine_quality_data)
print("训练集的形状:\n",a[0].shape)
print("测试集的形状:\n",a[1].shape)
#标准差标准化wine和wine_quality数据集
stdScale1=StandardScaler().fit(wine_data)   #生成规则
stdScale2=StandardScaler().fit(wine_quality_data)
dataScale1=stdScale1.transform(wine_data)   #应用规则到数据集
dataScale2=stdScale2.transform(wine_quality_data)
print("Ps:下面只展示wine的标准化数据")
print("标准差标准化wine数据集前的方差",np.var(wine_data))
print("标准差标准化wine数据集后的方差",np.var(dataScale1))
print("标准差标准化wine数据集前的均值",np.mean(wine_data))
print("标准差标准化wine数据集后的均值",np.mean(dataScale1))
#离差标准化wine和wine_quality数据集
Scale1=MinMaxScaler().fit(wine_data)
Scale2=MinMaxScaler().fit(wine_quality_data)
dataScale3=Scale1.transform(wine_data)
dataScale4=Scale2.transform(wine_quality_data)
print("离差标准化wine数据集前的最小值",np.min(wine_data))
print("离差标准化wine数据集后的最小值",np.min(dataScale3))
print("离差标准化wine数据集前的最大值",np.max(wine_data))
print("离差标准化wine数据集后的最大值",np.max(dataScale3))
#对wine和wine_quality的数据集进行PCA降维
pca_model1=PCA().fit(wine_data)
trainPca1=pca_model1.transform(wine_data)
pca_model2=PCA().fit(wine_quality_data)
trainPca2=pca_model2.transform(wine_quality_data)
print("PCA降维前wine数据集的形状:\n",wine_data.shape)
print("PCA降维后wine数据集的形状:\n",trainPca1.shape)
print("PCA降维前wine_quality数据集的形状:\n",wine_quality_data.shape)
print("PCA降维后wine_quality数据集的形状:\n",trainPca2.shape)
