# -*- coding: utf-8 -*-
"""
Created on Fri Nov 16 16:49:23 2018

@author: Aries_BG
"""

import pandas as pd
import numpy as np
from sklearn.ensemble import RandomForestRegressor
from sklearn.preprocessing import StandardScaler
from sklearn.svm import SVC
from sklearn.metrics import accuracy_score,precision_score, recall_score,f1_score,cohen_kappa_score
from sklearn.metrics import roc_curve 
import matplotlib.pyplot as plt

#%% 读入数据
train=pd.DataFrame(pd.read_csv("D://data/train.csv",encoding='gbk'))
test=pd.DataFrame(pd.read_csv("D://data/test.csv",encoding='gbk'))
result=pd.DataFrame(pd.read_csv("D://data/1/result.csv",encoding='gbk'))
#%% 提取数据集的标签及特征
train_targets=[train['Pclass'],train['Sex'],train['Age'],train['SibSp'],train['Parch'],train['Fare'],train['Embarked']]
test_targets=[test['Pclass'],test['Sex'],test['Age'],test['SibSp'],test['Parch'],test['Fare'],test['Embarked']]
print("*"*75)
for i in range(len(train_targets)):
    print(train_targets[i].name,"的缺失值数量:",train_targets[i].isnull().sum())
print("*"*75)
for i in range(len(test_targets)):
    print(test_targets[i].name,"的缺失值数量:",test_targets[i].isnull().sum())
print("*"*75)
#%% 转换数据
    
# male：0 female：1
def deal_sex(series):
    for i in range(series.size):
        if(series[i]=='male'):
            series[i]=0
        elif(series[i]=='female'):
            series[i]=1
    return series
train['Sex']=deal_sex(train['Sex'])
test['Sex']=deal_sex(test['Sex'])
# C：0 Q：1 S：2
def deal_embarked(series):
    for i in range(series.size):
        if(series[i]=='C'):
            series[i]=0
        elif(series[i]=='Q'):
            series[i]=1
        elif(series[i]=='S'):
            series[i]=2
    return series
train['Embarked']=deal_embarked(train['Embarked'])
test['Embarked']=deal_embarked(test['Embarked'])
print("完成sex和embarked转化成数值类型的属性")
print("train的embarked众数:",train.loc[:,'Embarked'].mode())
print("test的fare众数:",test.loc[:,'Fare'].mode())
train.loc[(train.Embarked.isnull()),'Embarked']=2 #处理缺失的两个值
test.loc[(test.Fare.isnull()),'Fare']=7.75
print("完成Embarked缺失插值")
print("完成Fare缺失插值")
print("*"*75)
#%% 使用随机森林对缺失数据进行插值

age_df0=train[['Age','Pclass','SibSp','Parch','Fare','Embarked']]
age_df_notnull0=age_df0.loc[(train.Age.notnull())]
age_df_isnull0=age_df0.loc[(train.Age.isnull())]
X0=age_df_notnull0.values[:,1:]
Y0=age_df_notnull0.values[:,0]
rfr0 = RandomForestRegressor(n_estimators=1000,n_jobs=-1)
rfr0.fit(X0,Y0)
predictAges0=rfr0.predict(age_df_isnull0.values[:,1:])
train.loc[(train.Age.isnull()),'Age']=predictAges0
print("随机森林预测train年龄并插值完毕")

age_df1=test[['Age','Pclass','SibSp','Parch','Fare','Embarked']]
age_df_notnull1=age_df1.loc[(test.Age.notnull())]
age_df_isnull1=age_df1.loc[(test.Age.isnull())]
X1=age_df_notnull1.values[:,1:]
Y1=age_df_notnull1.values[:,0]
rfr1 = RandomForestRegressor(n_estimators=1000,n_jobs=-1)
rfr1.fit(X1,Y1)
predictAges1=rfr1.predict(age_df_isnull1.values[:,1:])
test.loc[(test.Age.isnull()),'Age']=predictAges1
print("随机森林预测test年龄并插值完毕")
print("*"*75)
def deal_survived(series):#处理结果的函数
    for i in range(series.size):
        if(series[i]>0.5):
            series[i]=1
        elif(series[i]<=0.5):
            series[i]=0
    return series
#%% 哑变量处理
# 0:male  1:female
def deal_sex(series):
    for i in range(series.size):
        if(series[i]==0):
            series[i]='male'
        elif(series[i]==1):
            series[i]='female'
    return series
train['Sex']=deal_sex(train['Sex'])
test['Sex']=deal_sex(test['Sex'])
# 0:C 1:Q  2:S
def deal_embarked(series):
    for i in range(series.size):
        if(series[i]==0):
            series[i]='C'
        elif(series[i]==1):
            series[i]='Q'
        elif(series[i]==2):
            series[i]='S'
    return series
train['Embarked']=deal_embarked(train['Embarked'])
test['Embarked']=deal_embarked(test['Embarked'])
print("完成将数值类型属性转化为原始属性")
train=pd.get_dummies(train,columns=['Sex','Embarked'])
test=pd.get_dummies(test,columns=['Sex','Embarked'])
print("完成对sex和embarked的哑变量处理")
print("*"*75)
#%% 数据标准差标准化
stdScaler = StandardScaler().fit(train[['Pclass','Age','Sex_male','Sex_female','SibSp','Parch','Fare','Embarked_C','Embarked_Q','Embarked_S']])
train_Std = stdScaler.transform(train[['Pclass','Age','Sex_male','Sex_female','SibSp','Parch','Fare','Embarked_C','Embarked_Q','Embarked_S']])
test_Std = stdScaler.transform(test[['Pclass','Age','Sex_male','Sex_female','SibSp','Parch','Fare','Embarked_C','Embarked_Q','Embarked_S']])
print("完成将train和test的相关数据的标准差标准化")
#%% 构建SVM模型
svm=SVC().fit(train_Std,train[['Survived']])
print('建立的SVM模型为：\n',svm)
#预测
predicts=svm.predict(test_Std)
result.loc[:,'Survived']=predicts
result['Survived']=deal_survived(result['Survived'])
result.to_csv('D://data/1/predicts.csv',index=False)
print("已保存预测结果")
print("*"*75)
#评价 再预测train里面的survived来进行
predict_train=svm.predict(train_Std)
true = np.sum(predict_train == train['Survived'])
print('预测对的结果数目为:', true) 
print('预测错的的结果数目为：', predict_train.shape[0]-true)
print('预测结果准确率为：', true/predict_train.shape[0])
print('使用SVM预测breast_cancer数据的准确率为：', accuracy_score(train['Survived'],predict_train)) 
print('使用SVM预测breast_cancer数据的精确率为：', precision_score(train['Survived'],predict_train)) 
print('使用SVM预测breast_cancer数据的召回率为：', recall_score(train['Survived'],predict_train)) 
print('使用SVM预测breast_cancer数据的F1值为：', f1_score(train['Survived'],predict_train)) 
print('使用SVM预测breast_cancer数据的Cohen’s Kappa系数为：', cohen_kappa_score(train['Survived'],predict_train))
print("*"*75)
## 求出ROC曲线的x轴和y轴 
print("绘制ROC曲线")
plt.rcParams['font.sans-serif'] = 'SimHei'#设置显示中文
fpr, tpr, thresholds = roc_curve(train['Survived'],predict_train) 
plt.figure(figsize=(10,6)) 
plt.xlim(0,1) ##设定x轴的范围 
plt.ylim(0.0,1.1) ## 设定y轴的范围 
plt.xlabel('假阳率(False Postive Rate)') 
plt.ylabel('真阳率(True Postive Rate)') 
plt.plot(fpr,tpr,linewidth=2, linestyle="-",color='red') 
plt.show()
print("*"*75)