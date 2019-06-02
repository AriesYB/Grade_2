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

void Classifier::ReadData(string textName)//��ȡ����
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

void Classifier::OutputData(void)//��������б�������
{
	cout<<left;
	for(int i=0;i<M;i++)
	{
		for(int j=0;j<N+1;j++)
			cout<<setw(10)<<data[i][j]<<"  ";
		cout<<endl;
	}
}

void Classifier::Preprocess(string textName)//����Ԥ����
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
			d = (data[row][i]-maxMin[i][1])/(maxMin[i][0]-maxMin[i][1]);//��ֵ��һ�����������ţ�ʹ�ݶ��½�����
			outs<<d;
			outs.put(',');
		}
		outs<<data[row][N]-1; //���liver���ݼ���ת�����Ϊ0��1
		outs.put('\n');

    }
	outs.close();
}


void Classifier::Grad(void)//�����ݶ�
{
	for (int j = 0; j < N; j++) {
		p[j] = 0;
		for (int i = 0; i < M; i++) {
			p[j]+= (Sigmoid(i)-data[i][N])*data[i][j];
		}
	}
}

double Classifier::WtX(int row)//��row+1�е�WtX
{
	double x = 0;
	for (int i = 0; i < N; i++) {
		x += data[row][i] * w[i];
	}
	return x;
}

double Classifier::Sigmoid(int row)//�߼��ع麯��
{
	return double(1.0 / (1.0 + exp(-WtX(row) ) ));
}

void Classifier::train(void)//����ѵ������
{
	int j = 0;
	Grad();
	while (j < 1000) {//����һǧ��
		for (int i = 0; i < N; i++) {
			w[i] = w[i] - 0.01*p[i];
		}
		Grad();//ÿ�θ�����Ȩ�ز���֮������µ��ݶ�
		j++;
	}
	for (int j = 0; j < N; j++) {
		cout << w[j] << endl;
	}
}

void Classifier::test(void)//��������
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
	cout << "׼ȷ��Ϊ:" << sum/345 << endl;
}
