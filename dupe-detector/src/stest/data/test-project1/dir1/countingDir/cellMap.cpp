#include "cellMap.h"

using namespace std;

/**
 * Record in the map the approrpiate assignment of a character to a cell.
 * Must run in O(1) average time.
 *
 * @param character the character whose position is being recorded
 * @param characterPositions the mapping from cell indices to the characters in that cell
 * @param cellSize the width of a cell, also the max distance at which characters can see one another
 */
void recordPosition (const Character& character, CellMap& characterPositions, int cellSize) {

	//Index the character's position to a cell based on cell width
	int cellX = character.x/cellSize;
	int cellY = character.y/cellSize;
	CellIndex characterCell(cellX, cellY);

	//Insert this info into the cell map
	std::pair<CellIndex, Character> insertPair (characterCell, character);
	characterPositions.insert(insertPair);
}


/**
 * Check to see if a given character can see any others.
 * Must run in O(1) average time.
 *
 * @param character the character whose field of vision is to be checked
 * @param characterPositions the mapping from cell indices to the characters in that cell
 * @param cellSize the width of a cell, also the max distance at which characters can see one another
 * @returns true if this character can see at least one other.
 */
bool canSeeAnotherCharacter (const Character& character, const CellMap& characterPositions, int cellSize) {

	/* I honestly don't know what to do with whats asked of me
	 * So I am going to brute force it and accept what I get for a grade
	 * Then review the solution. Instructions were too confusing.
	 */

	//Get the current character's index
	int cellX = character.x/cellSize;
	int cellY = character.y/cellSize;
	CellIndex currentCharacterCell(cellX, cellY);

	//Check for other characters in same cell
	auto range = characterPositions.equal_range(currentCharacterCell);

	//Iterate through all the key members
	for(auto it = range.first; it != range.second; it++) {
		//Given we are checking the current cell we don't want to compare the same character to itself
		if(it->second == character) {
		}
		else {
			if(cellSize >= it->second.distanceSq(character)) {
				return true;
			}
		}
	}

	//Check cell to right
	CellIndex cellRight(cellX+1, cellY);
	range = characterPositions.equal_range(cellRight);
	//Iterate through all the key members
	for(auto it = range.first; it != range.second; it++) {
		if(cellSize >= it->second.distanceSq(character)) {
			return true;
		}
	}
	//Check cell to bottom right
	CellIndex cellBottomRight(cellX+1, cellY+1);
	range = characterPositions.equal_range(cellBottomRight);
	//Iterate through all the key members
	for(auto it = range.first; it != range.second; it++) {
		if(cellSize >= it->second.distanceSq(character)) {
			return true;
		}
	}

	//Check cell to bottom
	CellIndex cellBottom(cellX, cellY+1);
	range = characterPositions.equal_range(cellBottom);
	//Iterate through all the key members
	for(auto it = range.first; it != range.second; it++) {
		if(cellSize >= it->second.distanceSq(character)) {
			return true;
		}
	}

	//Check cell to bottom left
	CellIndex cellBottomLeft(cellX-1, cellY+1);
	range = characterPositions.equal_range(cellBottomLeft);
	//Iterate through all the key members
	for(auto it = range.first; it != range.second; it++) {
		if(cellSize >= it->second.distanceSq(character)) {
			return true;
		}
	}

	//Check cell to left
	CellIndex cellLeft(cellX-1, cellY);
	range = characterPositions.equal_range(cellLeft);
	//Iterate through all the key members
	for(auto it = range.first; it != range.second; it++) {
		if(cellSize >= it->second.distanceSq(character)) {
			return true;
		}
	}

	//Check cell to top left
	CellIndex cellTopLeft(cellX-1, cellY-1);
	range = characterPositions.equal_range(cellTopLeft);
	//Iterate through all the key members
	for(auto it = range.first; it != range.second; it++) {
		if(cellSize >= it->second.distanceSq(character)) {
			return true;
		}
	}

	//Check cell to top
	CellIndex cellTop(cellX, cellY-1);
	range = characterPositions.equal_range(cellTop);
	//Iterate through all the key members
	for(auto it = range.first; it != range.second; it++) {
		if(cellSize >= it->second.distanceSq(character)) {
			return true;
		}
	}

	//Check cell to top right
	CellIndex cellTopRight(cellX+1, cellY-1);
	range = characterPositions.equal_range(cellTopRight);
	//Iterate through all the key members
	for(auto it = range.first; it != range.second; it++) {
		if(cellSize >= it->second.distanceSq(character)) {
			return true;
		}
	}

	//Otherwise return false
	return false;
}
