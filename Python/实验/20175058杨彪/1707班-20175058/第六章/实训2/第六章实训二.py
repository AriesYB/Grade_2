# -*- coding: utf-8 -*-
"""
Created on Wed Oct 31 16:59:49 2018

@author: 杨彪 20175058
"""

import pandas as pd
from sklearn.cluster import KMeans
from sklearn.preprocessing import MinMaxScaler
#from sklearn.preprocessing import StandardScaler
from sklearn.metrics import fowlkes_mallows_score
from sklearn.metrics import silhouette_score
import matplotlib.pyplot as plt
from sklearn.metrics import calinski_harabaz_score 
from sklearn.manifold import TSNE

wine=pd.DataFrame(pd.read_csv("D://data/wine.csv",encoding='gbk'))
#提取数据集特征、标签、特征名
wine_data=wine.iloc[:,1:14].values
wine_target=wine.iloc[:,0].values
#####################################
##标准差标准化wine数据集
#Scale = StandardScaler().fit(wine_data)
#####################################
#离差标准化wine数据集
Scale=MinMaxScaler().fit(wine_data)
dataScale=Scale.transform(wine_data)
##构建并训练聚类模型
kmeans0 = KMeans(n_clusters=3,random_state=123).fit(dataScale)
print("构建的K-Means模型是:\n",kmeans0)
#根据真实标签和聚类标签求FMI
score = fowlkes_mallows_score(wine_target,kmeans0.labels_)
#print(score)
#从2--10中选择最优聚类属
SilhouetteScore = []
for i in range(2,11):
    kmeans = KMeans(n_clusters=i,random_state=123).fit(wine_data)
    score = fowlkes_mallows_score(wine_target,kmeans.labels_)
    score1 = silhouette_score(wine_data,kmeans.labels_)            
    SilhouetteScore.append(score1)                                     
    print("数据聚%d类FMI评价分值为：%f" %(i,score))


plt.rcParams['font.sans-serif']=['SimHei']
plt.rcParams['axes.unicode_minus']=False 
#创建画布
p = plt.figure(figsize = (10,8))
#根据轮廓系数话折线图
plt.plot(range(2,11),SilhouetteScore,linewidth=1.8,linestyle = "-",color = "r")
plt.title("wine（轮廓系数）聚类评价折线图")
plt.xlabel("聚类数目")
plt.ylabel("FMI评分")
plt.show()
#使用Calinski-Harabasz指数评价K-Means模型
for i in range(2,11):
    kmeans = KMeans(n_clusters=i,random_state=123).fit(wine_data)
    score2 = calinski_harabaz_score(wine_data,kmeans.labels_)
    print("wine数据聚%d类calinski_harabaz指数为：%f"%(i,score2))
    
##使用TSNE进行数据降维,降成两维
tsne = TSNE(n_components=2,init='random', random_state=177).fit(wine_data)
df=pd.DataFrame(tsne.embedding_) ##将原始数据转换为DataFrame
df['labels'] = kmeans.labels_ ##将聚类结果存储进df数据表
##提取不同标签的数据
df1 = df[df['labels']==0]
df2 = df[df['labels']==1]
df3 = df[df['labels']==2]
## 绘制图形
fig = plt.figure(figsize=(10,6)) ##设定画布
##用不同的颜色表示不同数据
plt.plot(df1[0],df1[1],'bo',df2[0],df2[1],'r*',df3[0],df3[1],'gD')
plt.legend(['class1','class2','class3'])
plt.show() ##显示图片
