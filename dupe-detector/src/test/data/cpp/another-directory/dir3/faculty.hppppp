#ifndef AUTHOR_H
#define AUTHOR_H

#include <iostream>
#include <string>
#include <list>
#include "section.h"


class Faculty {
private:

  //** You must not change the private data members of this class, nor
  //** may you add additional data members.
  //**
  //** You may add private functions if you wish.

  //static const unsigned MAXSECTIONS; Not necessary for lists

  std::string name;      //!< The name of the faculty member
  unsigned numberOfSections;  //!< Number of course sections being taught by this faculty member : this should be sections.size() now
  std::list<Section> sections;     //!< List of sections being taught by this faculty member

public:
  //** You may alter the public interface but it must continue to
  //** compile with the existing, unaltered application code and
  //** test code.

  //Convention learned in 330 with Professor Kennedy
  using iterator = std::list<Section>::const_iterator;
  using const_iterator = std::list<Section>::const_iterator;

  /**
   * Construct a Faculty object with empty name and no course sections.
   */
  Faculty();

  /**
   * Construct a faculty member with the given name and no course sections.
   * 
   * @param theName  name to give to this faculty member
   */
  Faculty(std::string theName);

  /**
   * Construct a faculty member with given name and pair of iterators denoting a range
   */
  template <class InputIterator>
  Faculty(std::string theName, InputIterator start, InputIterator stop)
  : name(theName)
  {
	  sections.assign(start, stop);
	  numberOfSections = sections.size();
  }

  /**
   * Construct a faculty member with given name and an initializer_list of sections
   */
  Faculty(std::string theName, std::initializer_list<Section> theSections);

  // The Big 3
  ~Faculty();
  Faculty (const Faculty& fac);
  Faculty& operator= (const Faculty& fac);

  /**
   * Get the name of the faculty member.
   * 
   * @return the name
   */
  std::string getName() const  {return name;}

  /**
   * Set the name of the faculty member.
   * 
   * @param theName the name to give to this faculty member.
   */
  void setName (std::string theName) {name = theName;}

  /**
   * How many sections are associated with this faculty member?
   * 
   * @return number of sections
   */
  unsigned numSections() const { return numberOfSections; }
  
  /**
   *  Add a section to the list for this faculty member. Sections are kept ordered
   *  by course number and CRN.
   * 
   * @param sect a course section
   * @pre numSections() < MAXSECTIONS
   */
  void add (const Section& sect);

  /**
   * Retrieve the sectionNum_th section associated with this faculty member
   * 
   * @param sectionNum identifies the section to be retrieved.
   * @return the desired section
   * @pre sectionNum < numSections()  
   */
  const Section& getSection (unsigned sectionNum) const;

  /*
   * Iterator functions/helpers
   */

  iterator begin();
  iterator end();

  const_iterator begin() const;
  const_iterator end() const;

  /**
   *  Ordered by name
   */
  bool operator< (const Faculty&) const;
  bool operator== (const Faculty&) const;

  /**
   * Used by the instructor for testing purposes.
   */
  bool sanityCheck() const;
};

/**
 *  Print a faculty member name, followed, one per line, by the sections associated
 *  with this faculty member;
 */
std::ostream& operator<< (std::ostream& out, const Faculty& a);

#endif
