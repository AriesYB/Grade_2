#include <iostream>
#include <fstream>
#include <iomanip>
using namespace std;

int findMedian(int *x, int *y, int n) {
	if (n == 1) {
		return *x <= *y ? *x : *y;
	}
	int m = (n - 1) / 2;
	int p = m + 1;
	if (n % 2 != 0) {
		p--;
	}
	if (*(x + m) == *(y + m)) {
		return *(x + m);
	} else if (*(x + m) < *(y + m)) {
		return findMedian(x + p, y, m + 1);
	} else {
		return findMedian(x, y + p, m + 1);
	}
}

void getMedian(){
	int a[] = {5,15,18};
		int b[] = {3,14,21};
		int *pa,*pb;
		pa = &a[0];
		pb = &b[0];
		int c = findMedian(pa,pb,3);
		cout<<c;
}

void readFile(string name){
	ifstream inf(name);
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
int main() {
	getMedian();
	return 0;
}
