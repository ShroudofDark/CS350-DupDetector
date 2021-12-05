#include <utility>

#include "Schedule.h"

using namespace std::rel_ops;

Schedule::Node::Node(Course c)
    :data(c),
     next(nullptr)
{
}

//------------------------------------------------------------------------------
//Default Constructor
Schedule::Schedule()
    :head(nullptr),
     tail(nullptr),
     totalCredits(0)
{
}

/**
 * @todo implement this function (it is simliar to Review 01)
 * Implemented
 */

//Copy Constructor (Every constructor must initiliaze attributes)
Schedule::Schedule(const Schedule& src)
    :head(nullptr),
     tail(nullptr),
     totalCredits(0)
{
  // Copy the src Linked List **data** [deep]
  // Hold the source's current node
  Node* srcCurrent = src.head;

  //while the source node is not a nullptr
  while (srcCurrent != nullptr) {

    //Add current data to this list
    this->appendNoCheck(srcCurrent->data);

    //Go to the next node
    srcCurrent = srcCurrent->next;
  }
}

/**
 * @todo implement this function (it is similar to Review 01)
 * Implemented
 */

//Destructor
Schedule::~Schedule()
{
  //Will track the node we are working on/loop control
  Node *current = nullptr;
  
  //Temporary node that will hold the current node while we get the next node assigned to current (will then delete this)
  Node *toDelete = nullptr;

  //Start on the first node in the list
  current = this->head;

  //While there are still nodes in the list
  while(current != nullptr) {
    
    //Set the current node to the node to delete
    toDelete = current;
    
    //With that info stored, we set current to next node
    current = current->next;

    //We can safely delete the "current" node and removing dangling pointers
    delete toDelete;
    toDelete = nullptr;
    
  }

  //Not necessary, but for sake of copy pasting errors (Destructor gaurantees this won't be used anymore)
  head = nullptr;
  tail = nullptr;
  totalCredits = 0;
}

/**
 * @todo implement this function (it is similar to Review 01)
 * Implemented
 */

//Add course to schedule at the end of link list (course list)
void Schedule::appendNoCheck(Course course)
{
  //Create a new Node
  Node *newNode = nullptr;
  
  //Store the course to the new node (based on the Node constructor)
  newNode = new Node(course);

  //Check if there is a head of the link list (arrow b/c they are pointers)
  if(this->head == nullptr) {
    
    //If there isn't assign the head and tail to the new node
    this->head = newNode;
    this->tail = newNode;
  }
  
  //Otherwise add the new node the end of the list (tail)
  else {
    
    //It takes the current end of the list's next node and sets it the new node
    (this->tail)->next = newNode;
    
    //Then we set the new node as the end of the list
    this->tail = newNode;
  }

  //We add the number of credits of the course to total too
  totalCredits += course.getCredits();

  //Not a necessary line, but may help avoid errors if copy pasting. Courtesy of Professor Kennedy's review.
  newNode = nullptr;
}

/**
 * @todo implement this function
 * Implemented
 */

//Check if a new course would go over max credit limit
bool Schedule::wouldViolateCreditLimit(Course course) const
{
  //If the total added is more than the limit, it is true
  if((course.getCredits() + totalCredits) > CREDIT_LIMIT)
    return true;
  
  //Otherwise false
  return false;
}

/**
 * @todo implement this function
 * Implemented
 */

// Check if the student is registered
// for a different section of the same course
bool Schedule::alreadyInSchedule(Course course) const
{
  //Set new node to start of the schedule's linked list
  Node* current = head;

  //Go through the entire schedule's linked list. Loop structure provided by Professor Kennedy's review.
  while(current != nullptr) {
    
    //If the course matches, then it is already in the schedule and we can break the loop. Data is the course for the current node
    if(current->data == course)
      return true;
    
    //Otherwise we move to the next item in the list
    current = current->next;
  }
  
  //Not found in schedule
  return false;
}

//------------------------------------------------------------------------------
void Schedule::display(std::ostream& outs) const
{
    Node* it = head;

    outs << "  (" << totalCredits << " Total Credits)" << "\n";

    while (it != nullptr) {
        outs << "  - " << (it->data) << "\n";

        it = it->next;
    }
}

//------------------------------------------------------------------------------
Schedule& Schedule::operator=(Schedule rhs)
{
    swap(*this, rhs);

    return *this;
}
