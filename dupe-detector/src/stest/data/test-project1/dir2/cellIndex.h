#ifndef CELLINDEX_H_
#define CELLINDEX_H_

#include <cstddef>
#include <iostream>

#include <unordered_map>

// Indices of a cell in the world

struct CellIndex {
	int i, j;
  
	//Default constructor
	CellIndex();
	//Constructor with cell size
	CellIndex(int x, int y);
	//std::hash<CellIndex> cellHash;
	std::size_t hash() const;

};

//From Hash-Based Sets and Maps notes
namespace std {
	template <>
	struct hash<CellIndex> // denotes a specialization of hash<...>
	{
		std::size_t operator() (const CellIndex& cell) const
		{
			return (std::size_t)cell.hash();
		}
	};
}

inline
bool operator==(const CellIndex& left, const CellIndex& right) {
	return left.i == right.i &&
		   left.j == right.j;
}

#endif
