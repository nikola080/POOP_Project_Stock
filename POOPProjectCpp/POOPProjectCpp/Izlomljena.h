#pragma once
#ifndef IZLOMLJENA_H

#include <iostream>
#include <functional>
#include "MojIterator.h"
class Tacka;
class Izlomljena {
public:
	class Elem {
	public:
		Tacka* data;
		Elem* next, * prev;
		Elem(Tacka* t) :data(t), next(nullptr), prev(nullptr) {}
	};
	Elem* head, * tail;
	Izlomljena() :head(nullptr), tail(nullptr) {}
	Izlomljena* dodaj(Tacka* t) {
		if (!head) {
			head = new Elem(t);
			tail = head;
		}
		else {
			tail->next = new Elem(t);
			tail->next->prev = tail;
			tail = tail->next;
		}
		return this;
	}
	MojIterator begin() {
		return  MojIterator(head);
	}
	MojIterator end() {
		return  MojIterator(nullptr);
	}
	friend ostream& operator<<(ostream& os, const Izlomljena& iz) {
		Elem* pok = iz.head;
		while (pok) { os << "(" << pok->data->getX() << "-" << pok->data->getY() << ")" << endl; pok = pok->next; }
		return os;
	}
};
#endif
