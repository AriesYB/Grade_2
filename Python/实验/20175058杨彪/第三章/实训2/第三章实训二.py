# -*- coding: utf-8 -*-
"""
Created on Sun Oct 14 21:34:32 2018

@author: 杨彪 20175058
"""

import numpy as np
import matplotlib.pyplot as plt
plt.rcParams['font.sans-serif'] = 'SimHei'#设置显示中文
#读取并整理数据
arr0=np.load("D:/data/data.npy")
arr1=np.load("D://data/feature_names.npy")
arr0=arr0[:-2,:]    #删除无效片段
for i in range(len(arr0[:,0])):  #去除数组中单位年
    arr0[i][0]=arr0[i][0][:-1]
arr_year=arr0[:,0]
arr2=arr0.copy()
for j in range(len(arr_year)):  #排序
    arr0[len(arr_year)-j-1]=arr2[j]

arr_1=arr0[:,1]#年末总人口
arr_2=arr0[:,2]#男性人口
arr_3=arr0[:,3]#女性人口
arr_4=arr0[:,4]#城镇人口
arr_5=arr0[:,5]#乡村人口
#创建画布
fig1=plt.figure(figsize=(30,10))#直方图   
fig2=plt.figure(figsize=(25,20))#饼图
fig3=plt.figure(figsize=(25,20))#饼图
fig4=plt.figure(figsize=(20,10))#箱线图
#创建子图
subplot_h1=fig1.add_subplot(121)#画布1有2个子图
subplot_h2=fig1.add_subplot(122)

arr_subplot_pie=[]              #画布2有20个子图
for n in range(20):
    arr_subplot_pie.append("subplot_p"+str(n))
    arr_subplot_pie[n]=fig2.add_subplot(4,5,n+1)
    
arr_subplot_pie1=[]              #画布3有20个子图
for m in range(20):
    arr_subplot_pie1.append("subplot_p1"+str(m))
    arr_subplot_pie1[m]=fig3.add_subplot(4,5,m+1)
subplot_b1=fig4.add_subplot(111)#画布4有1个子图
#设置子图标题
subplot_h1.set_title('1996-2015年男女人口数量直方图',color='blue',fontsize='xx-large')
subplot_h2.set_title('1996-2015年城乡人口数量直方图',color='blue',fontsize='xx-large')
for i in range(len(arr_subplot_pie)):
    arr_subplot_pie[i].set_title(arr_year[i]+'年男女人口比例饼图',color='blue',fontsize='xx-large')
for j in range(len(arr_subplot_pie1)):
    arr_subplot_pie1[j].set_title(arr_year[j]+'年城乡人口比例饼图',color='blue',fontsize='xx-large')
subplot_b1.set_title('1996-2015年人口特征箱线图',color='blue',fontsize='xx-large')
#在相应的子图内添加图
h1=subplot_h1.bar(left=range(20),height=arr0[:,2],width=0.4)
h2=subplot_h1.bar(left=[i+0.4 for i in range(20)],height=arr0[:,3],width=0.4)
subplot_h1.set_xticks([i+0.2 for i in range(20)])#修改横坐标
subplot_h1.set_xticklabels(arr_year)
subplot_h1.set_ylabel('人口数量(万人)')
for x in h1+h2:#遍历条形图
    subplot_h1.text(x.get_x()+x.get_width()/2,x.get_height(),'%d'% x.get_height(),ha='center',va='bottom',fontsize=12)  
    
h3=subplot_h2.bar(left=range(20),height=arr0[:,4],width=0.4,color='red')
h4=subplot_h2.bar(left=[i+0.4 for i in range(20)],height=arr0[:,5],width=0.4,color='green')
subplot_h2.set_xticks([i+0.2 for i in range(20)])#修改横坐标
subplot_h2.set_xticklabels(arr_year)
subplot_h2.set_ylabel('人口数量(万人)')
for x in h3+h4:#遍历条形图
    subplot_h2.text(x.get_x()+x.get_width()/2,x.get_height(),'%d'% x.get_height(),ha='center',va='bottom',fontsize=12)
        
for p in range(len(arr_subplot_pie)):#饼图
    arr_subplot_pie[p].pie([arr_2[p],arr_3[p]],autopct='%3.1f%%',startangle=90,pctdistance=0.6)
    arr_subplot_pie[p].legend(labels=['男性人口','女性人口'],loc='best')    
for q in range(len(arr_subplot_pie1)):
    arr_subplot_pie1[q].pie([arr_4[q],arr_5[q]],colors=['green','red'],autopct='%3.1f%%',startangle=90,pctdistance=0.6)
    arr_subplot_pie1[q].legend(labels=['城镇人口','乡村人口'],loc='best')
                                    #箱线图
b1=subplot_b1.boxplot((arr_1,arr_2,arr_3,arr_4,arr_5),labels=('年末总人口','男性人口','女性人口','城镇人口','乡村人口'),notch=True,showmeans=True)
#在各个子图中添加图例
subplot_h1.legend([h1,h2],labels=['男性人口','女性人口'],loc='upper left')
subplot_h2.legend([h3,h4],labels=['城镇人口','乡村人口'],loc='upper right')

#保存和显示
plt.show()
