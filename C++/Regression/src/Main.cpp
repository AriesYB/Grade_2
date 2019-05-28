#include <iostream>
#include "Classifier.h"
using namespace std;

int main()
{
	Classifier c1;
	c1.Preprocess("bupa.txt");
	c1.ReadData("1bupa.txt");
	c1.OutputData();
	c1.train();
	system("pause");
	return 0;
}
