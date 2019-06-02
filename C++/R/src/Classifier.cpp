#include "Classifier.h"
#include <iostream>
#include <fstream>
#include <iomanip>
#include <cmath>

using namespace std;


Classifier::Classifier(void)
{
}


Classifier::~Classifier(void)
{
}

void Classifier::ReadData(string textName)//读取数据
{
	ifstream inf(textName);
	char c;
	int row=0;
	while(inf.peek()!=EOF){
		for(int i=1;i<=N;i++)
		{
			data[row][0]=1;
			inf>>data[row][i];
			inf.get(c);
		}
		row++;
	}
	inf.close();
}

void Classifier::OutputData(void)//输出程序中保存数据
{
	cout<<left;
	for(int i=0;i<M;i++)
	{
		for(int j=0;j<N+1;j++)
			cout<<setw(10)<<data[i][j]<<"  ";
		cout<<endl;
	}
}

void Classifier::Preprocess(string textName)//数据预处理
{
	ReadData(textName);
	double maxMin[N][2];
	for(int i=0;i<N;i++)
	{
		maxMin[i][0]=-100000;
		maxMin[i][1]=100000;
	}

	for(int row=0;row<M;row++)
		for(int i=1;i<N;i++)
		{
			if(data[row][i] > maxMin[i][0])
				maxMin[i][0]=data[row][i];
			if(data[row][i] < maxMin[i][1])
				maxMin[i][1]=data[row][i];
		}

	ofstream outs("1"+textName);
	double d;
	for(int row=0;row<M;row++){
		for(int i=1;i<N;i++)
		{
			d = (data[row][i]-maxMin[i][1])/(maxMin[i][0]-maxMin[i][1]);//均值归一化，特征缩放，使梯度下降更快
			outs<<d;
			outs.put(',');
		}
		outs<<data[row][N]-1; //针对liver数据集，转换类标为0和1
		outs.put('\n');

    }
	outs.close();
}


void Classifier::Grad(void)//计算梯度
{
	for (int j = 0; j < N; j++) {
		p[j] = 0;
		for (int i = 0; i < M; i++) {
			p[j]+= (Sigmoid(i)-data[i][N])*data[i][j];
		}
	}
}

double Classifier::WtX(int row)//第row+1行的WtX
{
	double x = 0;
	for (int i = 0; i < N; i++) {
		x += data[row][i] * w[i];
	}
	return x;
}

double Classifier::Sigmoid(int row)//逻辑回归函数
{
	return double(1.0 / (1.0 + exp(-WtX(row) ) ));
}

void Classifier::train(void)//不断训练参数
{
	int j = 0;
	Grad();
	while (j < 1000) {//迭代一千次
		for (int i = 0; i < N; i++) {
			w[i] = w[i] - 0.01*p[i];
		}
		Grad();//每次更新完权重参数之后更新新的梯度
		j++;
	}
	for (int j = 0; j < N; j++) {
		cout << w[j] << endl;
	}
}

void Classifier::test(void)//测试数据
{
	double sum = 0;
	for (int i = 0; i < M; i++) {
		double hx = 0;
		hx = Sigmoid(i);

		if (hx >= 0.5)
			hx = 1;
		else if (hx < 0.5)
			hx = 0;

		if (hx == data[i][N])
			sum++;
	}
	cout << "准确率为:" << sum/345 << endl;
}
