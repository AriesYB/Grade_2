# -*- coding: utf-8 -*-
"""
Created on Sun Oct 14 16:16:12 2018

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
#创建画布
fig=plt.figure(figsize=(32,10))     
plt.axis("off") #关闭坐标轴
#创建子图
ax1=fig.add_subplot(121) 
ax2=fig.add_subplot(122)
#设置子图标题         
ax1.set_title('1996-2015年人口特征散点图',color='blue',fontsize='xx-large')
ax2.set_title('1996-2015年人口特征折线图',color='blue',fontsize='xx-large')
#在各个子图里绘制图形
p1=ax1.scatter(arr_year,(arr0[:,1]),marker=",",color='c')
p2=ax1.scatter(arr_year,(arr0[:,2]),marker="o",color='b')
p3=ax1.scatter(arr_year,(arr0[:,3]),marker="s",color='g')
p4=ax1.scatter(arr_year,(arr0[:,4]),marker="*",color='r')
p5=ax1.scatter(arr_year,(arr0[:,5]),marker="^",color='k')
l1=ax2.plot(arr_year,arr0[:,1],color='c')
l2=ax2.plot(arr_year,arr0[:,2],color='b')
l3=ax2.plot(arr_year,arr0[:,3],color='g')
l4=ax2.plot(arr_year,arr0[:,4],color='r')
l5=ax2.plot(arr_year,arr0[:,5],color='k')
#添加图例
ax1.legend([p1,p2,p3,p4,p5],["年末总人口","男性人口","女性人口","城镇人口","乡村人口"],loc='upper left')
ax2.legend([l1,l2,l3,l4,l5],labels=["年末总人口","男性人口","女性人口","城镇人口","乡村人口"],loc='upper left')
plt.show()
