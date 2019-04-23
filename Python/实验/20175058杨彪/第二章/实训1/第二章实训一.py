# -*- coding: utf-8 -*-
"""
Created on Sun Oct 14 14:07:45 2018

@author: 杨彪 20175058
"""

import numpy as np
print("(1)获取数组arr0\n")
arr0=np.arange(0,1,0.01)    #(1)
print(arr0)
print("*"*75)
print("(2)获取数组arr1\n")
arr1=np.random.randn(100)   #(2)
print(arr1)
print("*"*75)
print("(3)arr0+arr1\n")
print(arr0+arr1)            #(3)
print("*"*75)
print("(4)arr0-arr1\n")
print(arr0-arr1)
print("*"*75)
print("(5)arr0*arr1\n")
print(arr0*arr1)
print("*"*75)
print("(6)arr0/arr1\n")
print(arr0/arr1)
print("*"*75)
arr1.sort()                 #(4)
print("(7)将arr1按横轴排序\n",arr1)
print("*"*75)
print("(8)将arr1沿着横轴进行如下计算\n","求和:",
      np.sum(arr1),"\n","均值:",np.mean(arr1),
      "\n","标准差:",np.std(arr1),"\n","方差:",
      np.var(arr1),"\n","最小值:",np.min(arr1),
      "\n","最大值:",np.max(arr1),"\n","累计和:",
      np.cumsum(arr1),"\n","累计积:",np.cumprod(arr1),"\n")
print("*"*75)