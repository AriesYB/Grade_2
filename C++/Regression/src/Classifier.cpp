#include "Classifier.h"
#include <iostream>
#include <fstream>
#include <iomanip>
#include <cmath>

using namespace std;

Classifier::Classifier(void) {
	for (int i = 0; i < N; i++) { //��ʼ��Ȩֵ����
		w[i] = 0;
		temp[i] = 0;
	}
}

Classifier::~Classifier(void) {
}
void Classifier::ReadData(string textName) {
	ifstream inf(textName);
	char c;
	int row = 0;
	while (inf.peek() != EOF) {
		for (int i = 1; i <= N; i++) {
			data[row][0] = 1;
			inf >> data[row][i];
			inf.get(c);
		}
		row++;
	}
	inf.close();
}

void Classifier::OutputData(void) {
	cout << left;
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N + 1; j++)
			cout << setw(10) << data[i][j] << "  ";
		cout << endl;
	}
}

void Classifier::Preprocess(string textName) { //����׼��

	ReadData(textName);
	double maxMin[N][2];
	for (int i = 1; i < N; i++) {
		maxMin[i][0] = data[0][i]; //��Сֵ
		maxMin[i][1] = data[0][i]; //���ֵ
	}
	for (int j = 1; j < N; j++) { //��һ��ȫ��1  ���һ����target���Բ�����׼��
		for (int i = 0; i < M; i++) {
			if (data[i][j] < maxMin[j][0]) {
				maxMin[j][0] = data[i][j];
			} else if (data[i][j] > maxMin[j][1]) {
				maxMin[j][1] = data[i][j];
			}
		}
	}
	double temp;
	for (int i = 0; i < M; i++) {
		for (int j = 1; j < N; j++) {
			temp = (data[i][j] - maxMin[j][0]) / (maxMin[j][1] - maxMin[j][0]);
			if (isnan(temp)) { //�����ַ�ĸΪ0ʱ��ֱ�ӿ�����1
				temp = 1;
			}
			data[i][j] = temp;
		}
//		data[i][N] -= 1; //���liver���ݼ���ת�����Ϊ0��1
	}

	ofstream outs("1" + textName); //����׼���������д���ļ�
	double d;
	for (int row = 0; row < M; row++) {
		for (int i = 1; i < N; i++) {
			d = data[row][i];
			outs << d;
			outs.put(',');
		}
		outs << data[row][N];
		outs.put('\n');
	}
	outs.close();
}

double Classifier::h(int row) { //����h(x)
	double temp;
	for (int i = 0; i < N; i++) {
		temp += data[row][i] * w[i];
	}
	return temp;
}

double Classifier::Sigmoid(int row) { //�߼��ع麯��
	return double(1.0 / (1.0 + exp(-h(row))));
}

double Classifier::Grad(int column) { //ĳ���������ݶ�
	double temp;
	for (int i = 0; i < M; i++) {
		temp += (Sigmoid(i) - data[i][N]) * data[i][column];
	}
	temp = (1 / M) * temp;
	return temp;
}

void Classifier::GradientDescent() { //�ݶ��½���
	int count = 0;
	while (count < 1000) {
		for (int i = 0; i < N + 1; i++) { //�ݶ��½�����
			double g = Grad(i);
			temp[i] = w[i] - ALPHA * g;
		}
		for (int j = 0; j < N + 1; j++) { //����Ȩ��
			w[j] = temp[j];
		}
		count++;
	}
}

void Classifier::train() {
	GradientDescent();
	for (int i = 0; i < N + 1; i++) {
		cout << w[i] << " ";
	}
}
