#include "Cylinder.h"

//------------------------------------------------------------------------------
Cylinder::Cylinder()
    :Cylinder(1, 1)
{
    //Teacher had a recomputeBox method which was called inside here
    /* Which seems to make it so don't need to call these lines every else
     * double d = this->getDiameter();
     * boundingBox.setUpperRightVertex(d, d, height);
     * but just call the recompute box method
     */
}

//------------------------------------------------------------------------------
Cylinder::Cylinder(double r, double h)
    :Polyhedron("Cylinder"),
     height(h),
     radius(r)
{
    double d = this->getDiameter();
    boundingBox.setUpperRightVertex(d, d, height);
}
//-----------------------------------------------------------------------------
//Notably setRadius and setHeight could be written as inline functions
void Cylinder::setHeight(double h) {
  height = h;

  /* This info needs to be updated as well, I forgot about this originally
   * and kept failing my tests. This info isn't updated in any other function
   * so whenever a number changes, it needs to be updated.
   */
  double d = this->getDiameter();
  boundingBox.setUpperRightVertex(d, d, height);
}
//-----------------------------------------------------------------------------
void Cylinder::setRadius(double r) {
  radius = r;

  double d = this->getDiameter();
  boundingBox.setUpperRightVertex(d, d, height);
}
//------------------------------------------------------------------------------
void Cylinder::read(std::istream& ins)
{
  //We take height first, then radius from input file as defined by format
  ins >> height >> radius;

  //Set diameter once new info is read in
  double d = this->getDiameter();
  //Then set the scale [As shone in the constructor]
  boundingBox.setUpperRightVertex(d, d, height);
}

//------------------------------------------------------------------------------
void Cylinder::display(std::ostream& outs) const
{
  //Use Polyhedron's display to handle the basic/same info
  Polyhedron::display(outs);
  //Then slight adjustments for Cylinder specific info
  outs << "Radius: " << radius
       << " "
       << "Height: " << height;
}

//------------------------------------------------------------------------------
void Cylinder::scale(double scalingFactor)
{
  //Change dimensions according to the factor
  radius *= scalingFactor;
  height *= scalingFactor;

  boundingBox.scale(scalingFactor);
}
//------------------------------------------------------------------------------
Polyhedron* Cylinder::clone() const {
  //Returns a new copy of this object
  return new Cylinder(*this);
}
