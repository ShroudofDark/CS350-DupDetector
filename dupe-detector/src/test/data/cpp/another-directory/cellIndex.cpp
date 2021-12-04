#include "cellIndex.h"

using namespace std;

//Default constructor
CellIndex::CellIndex()
: i(0), j(0)
{}

//Constructor with cell size
CellIndex::CellIndex(int x, int y)
: i(x), j(y)
{}

//Returns a hash for the cell
std::size_t CellIndex::hash() const {
	return 677*i+131*j;
}
