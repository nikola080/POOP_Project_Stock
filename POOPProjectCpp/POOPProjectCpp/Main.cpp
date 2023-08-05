#include <iostream>
#include<functional>
#include "Izlomljena.h"
using namespace std;

class Tacka {
	int x, y;
public:
	Tacka(int xx = 0, int yy = 0) {
		x = xx;
		y = yy;
	}
	int getX() const { return x; }
	int getY() const { return y; }

	friend ostream& operator<<(ostream& os, const Tacka& t) {
		return os << "(" << t.x << "-" << t.y << ")\n";
	}
};

int main() {
	Izlomljena *iz = new Izlomljena();
	iz->dodaj(new Tacka(0, 3))->dodaj(new Tacka(3, 9))->dodaj(new Tacka(1, 2))->dodaj(new Tacka(-3, 8))->dodaj(new Tacka(-7, 7));
	cout << *iz;

	for (MojIterator it = iz->begin(); it != iz->end(); it++) {
		cout << *it << endl;
	}
	return 0;
}