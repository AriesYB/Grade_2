# -*- coding: utf-8 -*-
"""
Created on Mon Apr  1 21:27:41 2019

@author: 杨彪
"""
import turtle as t


#分割路径
def split_path(path):
	i = 0
	arr = []
	while i < len(path):
		if path[i] == 'F':
			arr.append(path[i:i+2])
			i = i + 2
		else:
			arr.append(path[i])
			i = i + 1
	return arr

#应用变换规则           
def apply_rule(path,rule):
    arr = split_path(path)
    for i in range(len(arr)):
        str = arr[i]
        if str in rule:
            arr[i] = rule[str]
    path = "".join(str for str in arr)
    return path

#画图函数
def draw_path(path):
    arr = split_path(path)
    for str in arr:
        if str == 'Fl' or str == 'Fr':
            t.forward(length)
        elif str == '+':
            t.right(angle)
        elif str == '-':
            t.left(angle)


length = 3#长度
angle = 60#角度
rule={#规则
	"Fl":"Fr+Fl+Fr",
	"Fr":"Fl-Fr-Fl"
}
path = "Fl+Fr+"#路径
for i in range(10):
    path = apply_rule(path,rule)
t.speed(0)
draw_path(path)
t.done()