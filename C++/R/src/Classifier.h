#pragma once
#include <string>
using namespace std;

#define M 345  //number of instances
#define N 7    //number of attributes
#define ALPHA 0.01 //学习率α

class Classifier
{
private:
	double data[M][N+1];
	double w[N];//权重系数向量
	double p[N];//临时变量
	void Grad(void);//求梯度
	double WtX(int);//函数
	double Sigmoid(int);//逻辑回归
public:
	Classifier(void);
	~Classifier(void);
	void ReadData(string textName);//读取textName文件中的数据至data中
	void OutputData(void); //输出data中的数据
	void Preprocess(string textName);//预处理textName文件中的数据，存放到新文件“textName1”中
	void train(void);//训练模型
	void test(void);
};

