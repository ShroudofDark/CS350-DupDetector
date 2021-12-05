#include "Polyhedron.h"
#include "Composite.h"

//------------------------------------------------------------------------------
Composite::Composite()
    :Polyhedron("Composite")
{
}

//------------------------------------------------------------------------------
/**
 * @todo write this function
 */
Composite::Composite(const Composite& src)
    :Polyhedron("Composite")
{
   // Perform a deep copy... maybe the _add_ method can help...
  int numPolyhedra = src.size();

  for(int i = 0; i < numPolyhedra; i++) {
    this->add(src.allPolyhedra[i]);
  }
  //this->allPolyhedra = src.allPolyhedra;
}
/*
Composite::Composite(const Composite& src)
    :Polyhedron("Composite")
{
    // Perform a deep copy... maybe the _add_ method can help...
    /
    for (const Polyhedron* poly : src) {
        add(poly);
    }
    /
    for (const Polyhedron* poly : src) {
        allPolyhedra.push_back(poly->clone());
    }

    this->boundingBox = src.boundingBox;
}
*/

//------------------------------------------------------------------------------
/**
 * @todo write this function
 */
Composite::~Composite()
{
  // Delete each component polyhedra
  for(int i = 0; i < this->size(); i++) {
    delete allPolyhedra[i];
  }

  /* Delete each component polyhedra
    for (Polyhedron*& poly : *this) {
        delete poly;
    }
    */
}

//------------------------------------------------------------------------------
void Composite::read(std::istream& ins){
    int numPolyhedra;

    ins >> numPolyhedra;

    allPolyhedra.resize(numPolyhedra);

    for (int i = 0; i < numPolyhedra; i++) {
        ins >> allPolyhedra[i];

        boundingBox.merge(allPolyhedra[i]->getBoundingBox());
    }
}

//------------------------------------------------------------------------------
/**
 * @todo write this function
 */
void Composite::display(std::ostream& outs) const
{
    Polyhedron::display(outs);

    outs << allPolyhedra.size() << " polyhedra" << "\n";

    // Loop through all component polyhedra and
    for(int i = 0; i < this->size(); i++) {
    // print (display) them
      outs << "  ";
      allPolyhedra[i]->display(outs);
      outs << "\n";
    }

    /*

    // Loop through all component polyhedra and
    // print (display) them
    for (const Polyhedron* poly : *this) {
        outs << "  " << *poly << "\n";
    }
    */
}

//------------------------------------------------------------------------------
/**
 * @todo write this function
 */
void Composite::scale(double scalingFactor)
{
    // Loop through all polyhedra and scale them
  for(int i = 0; i < this->size(); i++) {
    allPolyhedra[i]->scale(scalingFactor);
  }
  /*
  // Loop through all polyhedra and scale them
    for (Polyhedron* poly : *this) {
        poly->scale(scalingFactor);
    }
  */
    // Do not forget the bounding box... after the loop
  boundingBox.scale(scalingFactor);

}

//------------------------------------------------------------------------------
Composite& Composite::operator=(Composite rhs)
{
    swap(*this, rhs);
    return *this;
}

//------------------------------------------------------------------------------
Composite::iterator Composite::begin()
{
    return allPolyhedra.begin();
}

//------------------------------------------------------------------------------
Composite::iterator Composite::end()
{
    return allPolyhedra.end();
}

//------------------------------------------------------------------------------
Composite::const_iterator Composite::begin() const
{
    return allPolyhedra.begin();
}

//------------------------------------------------------------------------------
Composite::const_iterator Composite::end() const
{
    return allPolyhedra.end();
}

//------------------------------------------------------------------------------
/**
 * @todo write this function
 * Add a Polyhedron to the `Composite` collection.
 */
void Composite::add(const Polyhedron* toAdd)
{
  // Add one new polyhedra and _merge_ its boundingBox with _this->boundingBox_

  //Clone the Polyhedron to the end of the collection
  allPolyhedra.push_back(toAdd->clone());
  //Merge the boundingBox of new Polyhedron to current collection
  boundingBox.merge(toAdd->getBoundingBox());
}

//------------------------------------------------------------------------------
void swap(Composite& lhs, Composite& rhs)
{
    using std::swap;

    std::swap(lhs.allPolyhedra, rhs.allPolyhedra);

    swap(lhs.boundingBox, rhs.boundingBox);
}
