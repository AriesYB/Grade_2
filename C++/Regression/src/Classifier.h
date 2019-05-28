#pragma once
#include <string>
using namespace std;

#define M 345  //number of instances
#define N 7    //number of attributes
#define ALPHA 0.01 //ѧϰ�ʦ�

class Classifier
{
private:
	double data[M][N+1];
	double w[N];//Ȩ��ϵ������
	double temp[N];//��ʱ����
	double h(int);//h(x)
	double Grad(int);//���ݶ�
	double Sigmoid(int);//�߼��ع�
	void GradientDescent(void);//�ݶ��½���
public:
	Classifier(void);
	~Classifier(void);
	void ReadData(string textName);//��ȡtextName�ļ��е�������data��
	void OutputData(void); //���data�е�����
	void Preprocess(string textName);//Ԥ����textName�ļ��е����ݣ���ŵ����ļ���textName1����
	void train(void);//ѵ��ģ��
};

