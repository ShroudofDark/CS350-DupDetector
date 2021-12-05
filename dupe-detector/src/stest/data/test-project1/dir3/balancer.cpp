/*
 * balancer.cpp
 *
 */

#include "balancer.h"

//Default constructor
Balancer::Balancer() {
	tagStatus = 1; //On creation the balancer is in a state of where there are no errors and no open tags
}

Balancer::~Balancer() {
}

/* Checks the tag and denotes what kind it is, returning an integer that will be compared to
 * Helper function for the tag function
 *
 * @param atag  the encountered tag is looked at to determine the kind it is
 * @return whether it is a singleton, open or closing tag
 *
 */
int Balancer::tagType(string& atag) const {

	//As note I attempted to use string::find to start, but believe substr is a more defensive alternative

	//An invalid tag was probably passed if this was the case
	if(atag.length() < 3) {
		return Error;
	}

	string subATag; //Holds part of the atag for comparison purposes

	//Check for singleton, which means the last 2 characters should be />
	subATag = atag.substr(atag.length()-2, 2);
	if(subATag.compare("/>") == 0) {
		//Singleton found
		return Singleton;
	}

	//Check for closing tag, which means first 2 characters should be </
	subATag = atag.substr(0, 2);
	if(subATag.compare("</") == 0) {
		//Closing found
		return Closing;
	}

	//If it was none of the above options, then it was an open tag
	return Open;
}

/* Gets the tag name from the current tag in order to compare it with other tags or to put it onto the stack
 * This is because <a> </a> needs to be comparable for example
 *
 * @param atag  the encountered tag that the name is being extracted from
 * @return part of the tag that can be identified as the name
 */
string Balancer::getTagName(string& atag, int tagType) const {

	size_t found; //Will hold the position of first found desired object in string given string::find returns size_t

	//Upon finding either a space or closing bracket return that position
	found = atag.find_first_of(" >");
	//One of the two were found, which at minimum > should be found if isn't found
	if(found != string::npos) {
		//Ignore the first character aka <
		if(tagType == Open) {
			return atag.substr(1, found-1);
		}
		//Ignore the first 2 characters aka </
		else if (tagType == Closing) {
			return atag.substr(2, found-2);
		}
	}
	//Passed in an inappropriate tag type or an error happened above
	return "Default";
}


/* Indicates driver encountered a tag.
 *
 * @param atag  the encountered tag, can be an opening tag, closing tag, or singleton tag
 */
void Balancer::tag(string atag) {

	int tagCheck = tagType(atag); //Checks what tag it is
	string tagName; //Holds tag name for comparing or storing
	bool tagError = false; //Acts as a flag if something is wrong with the check

	//Do things based on tag
	switch(tagCheck) {
		//Do nothing here
		case Singleton :
			break;
		case Open :
			tagName = getTagName(atag, Open);
			tagStack.push(tagName);
			break;
		case Closing :
			//If the stack is empty and we are checking a closing tag, there is an error
			if(tagStack.empty()) {
				tagError = true;
				break;
			}
			tagName = getTagName(atag, Closing);
			if(tagName.compare(tagStack.top()) != 0) {
				//The closing tag doesn't balance with the open tag
				tagError = true;
				break;
			}
			//If it does match, remove it from the stack
			tagStack.pop();
			break;
	}

	//If no open tags or we just passed over a non nested singleton
	if(tagStack.empty() && !tagError) {
		tagStatus = 1;
	}

	//Means there is an open tag that hasn't been closed
	else if(!tagStack.empty() && !tagError) {
		tagStatus = 0;
	}

	else if (tagError) {
	 tagStatus = -1;
	}
}
