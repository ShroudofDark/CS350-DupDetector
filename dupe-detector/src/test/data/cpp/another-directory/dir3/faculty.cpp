#include <cassert>
#include "faculty.h"

using namespace std;


//const unsigned Faculty::MAXSECTIONS = 150;
/**
   * Construct a Faculty object with empty name and no course sections.
   */
Faculty::Faculty()
: name(), numberOfSections(0)
{
}

/**
   * Construct a faculty member with the given name and no course sections.
   * 
   * @param theName  name to give to this faculty member
   */
Faculty::Faculty(std::string theName)
: name(theName), numberOfSections(0)
{
}

/**
 * Construct a faculty with given name and an initializer_list of sections
 */
Faculty::Faculty(std::string theName, std::initializer_list<Section> theSections)
: name(theName), sections(theSections)
{
	numberOfSections = sections.size();
	sections.sort();
}

/**
   * Retrieve the sectionNum_th section associated with this faculty member
   * 
   * @param sectionNum identifies the section to be retrieved.
   * @return the desired section
   * @pre sectionNum < numSections()  
   */
const Section& Faculty::getSection(unsigned sectionNum) const
{
    //assert (sectionNum < numberOfSections);
    //return sections[sectionNum];

	unsigned i = 0;
    for (const_iterator it = sections.begin(); it != sections.end(); ++it) {
    	if(i == sectionNum) {
    		return *it;
    	}
    	i++;
    }

    //return *sections.front();
    return sections.front();
}


/**
   *  Add a section to the list for this faculty member. Sections are kept ordered
   *  by course number and CRN.
   * 
   * @param sect a course section
   */
void Faculty::add(const Section &sect)
{
	/*
    assert (numberOfSections < MAXSECTIONS);
    sections[numberOfSections] = sect;
    int k = numberOfSections;
    while (k > 0 && sections[k] < sections[k-1])
    {
        Section temp = sections[k];
        sections[k] = sections[k-1];
        sections[k-1] = temp;
        --k;
    }
    ++numberOfSections;
    */

	//Fixes memory issue for moment, may not be intended method, but its simple for now
	sections.push_back(sect);
	sections.sort();
	numberOfSections = sections.size();
}


/**
   *  Ordered by name
   */
bool Faculty::operator<(const Faculty & right) const
{
    if (name != right.name) {
        return name < right.name;
    }
    if (numberOfSections != right.numberOfSections) {
        return numberOfSections < right.numberOfSections;
    }
    if (!(sections == right.sections)) {
        return sections < right.sections;
    }

    return false;
}

bool Faculty::operator==(const Faculty & right) const
{
    if (name != right.name) {
        return false;
    }
    if (numberOfSections != right.numberOfSections) {
        return false;
    }
    if (sections != right.sections) {
    	return false;
    }

	return true;
}

/**
 *  Print a faculty member name, followed, one per line, by the sections associated
 *  with this faculty member;
 */
std::ostream& operator<< (std::ostream& out, const Faculty& fac)
{
    out << fac.getName() << "\n";
    for (unsigned i = 0; i < fac.numSections(); ++i)
    {
        const Section& sect = fac.getSection(i);
        out << "    " << sect << endl;
    }
    return out;
}

// The Big 3
Faculty::~Faculty()
{
}

Faculty::Faculty (const Faculty& fac)
: name(fac.name), numberOfSections(fac.numberOfSections),
  sections(fac.sections)
{
}

Faculty& Faculty::operator= (const Faculty& fac)
{ /*
    if (this != &fac) {
        name = fac.name;
        numberOfSections = fac.numberOfSections;
        for (unsigned i = 0; i < numberOfSections; ++i)
        {
            sections[i] = fac.sections[i];
        }
    }
    */

	if(this != &fac) {
		name = fac.name;
		numberOfSections = fac.numberOfSections;
		sections = fac.sections;
	}
    return *this;
}

//Iterator Functions
Faculty::iterator Faculty::begin() {
	return sections.begin();
}

Faculty::const_iterator Faculty::begin() const {
	return sections.begin();
}

Faculty::iterator Faculty::end() {
	return sections.end();
}

Faculty::const_iterator Faculty::end() const {
	return sections.end();
}
