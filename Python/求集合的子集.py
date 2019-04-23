def getBinary(num):   #十进制转化二进制数 后面有“(2)”的标识
    s=""
    a=num
    while a!=0:
        b=int(a%2)       #a的余数
        s+=str(b)
        a=(a-a%2)/2      #a成为下一个被除数
    return (s[::-1])+"(2)"     #String类型的二进制串

def adds(n1,n2,n3,x,c=[]):          #该函数用于二进制进位计算
    c[x-1]+=(n1+n2+n3)//2
    return (n1+n2+n3)%2

def add(num1,num2):             #把两个二进制的数的字符串形式相加

    if len(num1)>len(num2):
        list1=[0]*len(num1)
        list2=[0]*len(num1)
        list3=[0]*(len(num1)+1)     #list3存进位
        list4=[0]*(len(num1)+1)     #list4存结果
    else:
        list1=[0]*len(num2)
        list2=[0]*len(num2)
        list3=[0]*(len(num2)+1)
        list4=[0]*(len(num2)+1)
        
    List1=list(num1[0:len(num1)-3])    #转换成单个数字的数组
    List2=list(num2[0:len(num2)-3])
        
    for x in range(1,len(List1)+1):
        list1[-x]=(int(List1[-x]))
    for x in range(1,len(List2)+1):
        list2[-x]=(int(List2[-x]))
       
    for i in range(1,len(list3)-1):
        list4[-i]=adds(list1[-i],list2[-i],list3[-i],-i,list3)
    result = ""
    for index in list4:
        result+=str(index)
        
    return result.lstrip("0")       #去掉前面多余的0


string=input("请输入集合的元素以英文逗号隔开")
element=string.split(",")
binCode="0"*len(element)
subArray=[element]

while binCode!="1"*len(element):            #遍历0000加1加到1111
    binCode=("0"*len(element)+binCode)[-len(element):]  #高位补零
    subItem=[]
    for i in range(len(binCode)):
        if binCode[i]=="1":
            subItem.append(element[i])      #1表示有该元素 0表示无
    subItem.sort()                          #内层数组排序
    if not(subItem in subArray):
        subArray.append(subItem)
    binCode=add(binCode+"(2)","1(2)")

print("一共有"+str(len(subArray))+"个子集")


for j in range(0,len(subArray)-1):        #冒泡排序
    for k in range(0,len(subArray)-1-j):
        if len(subArray[k])>len(subArray[k+1]):
            x=subArray[k+1]
            subArray[k+1]=subArray[k]
            subArray[k]=x
print()
for i in range(0,len(subArray)):
    if subArray[i]==[]:                 #把空的换成空集
        subArray[i]="∅"
#    print(subArray[i])         #直接打印数组
    print("{"+(",").join(subArray[i])+"}",end=" ")#把数组形式换成集合符号的形式
print()




