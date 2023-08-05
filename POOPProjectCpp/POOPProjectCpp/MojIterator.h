#pragma once
#ifndef MOJITERATOR_H
#include "Izlomljena.h"
class MojIterator
{
	const Izlomljena::Elem* tac;
public:
	MojIterator(const Izlomljena::Elem* t) {
		this->tac = t;
	}
	bool operator!=(const MojIterator& i) {
		return this->tac != i.tac;
	}
	MojIterator& operator++() {
		MojIterator* novi = new MojIterator(tac->next);
		return *novi;
	}

	MojIterator operator++(int) {
		MojIterator* old = this;
		++* this;
		return *old;
	}

	Tacka& operator*() {
		return *(tac->data);
	}
};
#endif

