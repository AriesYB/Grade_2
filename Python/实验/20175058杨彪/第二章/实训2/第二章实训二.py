# -*- coding: utf-8 -*-
"""
Created on Sun Oct 14 15:29:26 2018

@author: 杨彪 20175058
"""

import numpy as np
arr0=np.zeros((8,8))    #创建二维数组
for i in range(0,8):
    for j in range(0,8):
        if (i%2==0 and j%2==0) or (i%2==1 and j%2==1) :
            arr0[i][j]=1
        else:
            arr0[i][j]=0
arr1=np.array(arr0)
matr0=np.mat(arr1)
print(matr0)