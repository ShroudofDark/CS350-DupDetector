package polyhedra;

import java.util.Scanner;

/**
 * Polyhedron representing a cylinder.
 */
public class Cylinder extends Polyhedron {
    /**
     * Height of the cylinder. It must be > 0.
     */
    private double height;

    /**
     * Radius of the cylinder. It must be > 0.
     */
    private double radius;

    /**
     * Default Constructor - set the radius and height to 1.
     */
    public Cylinder()
    {
        this(1, 1);
    }

    /**
     * Construct a cylinder with specified height and radius.
     *
     * @param r desired radius
     * @param h deisred height
     */
    public Cylinder(double r, double h)
    {
        super("Cylinder");

        this.radius = r;
        this.height = h;

        double d = this.getDiameter();
        this.boundingBox.setUpperRightVertex(d, d, height);
    }

    /**
     * Construct a Cylinder (Copy Constructor)
     *
     * @param src the Cylinder to copy
     */
    public Cylinder(Cylinder src)
    {
	//This is base class constructor for Shape and sets the name
	super("Cylinder");

	this.radius = src.radius;
	this.height = src.height;
	
	//Adjustments to dimensions require update of bounding box 
	double d = this.getDiameter();
        this.boundingBox.setUpperRightVertex(d, d, height);
    }

    /**
     * Retrieve the radius.
     *
     * @return current radius
     */
    public double getRadius()
    {
        return this.radius;
    }

    /**
     * Retrieve the height.
     *
     * @return current height
     */
    public double getHeight()
    {
        return this.height;
    }

    /**
     * Update the radius.
     *
     * @param r desired radius
     */
    public void setRadius(double r)
    {
        // Implement this function
	// Sets this object radius to r
	this.radius = r;

	//Adjustments to dimensions require update of bounding box 
        double d = this.getDiameter();
        this.boundingBox.setUpperRightVertex(d, d, height);
    }

    /**
     * Update the height.
     *
     * @param h deisred height
     */
    public void setHeight(double h)
    {
        // Implement this function
	// Sets this object height to h
	this.height = h;

	//Adjustments to dimensions require update of bounding box
        double d = this.getDiameter();
        this.boundingBox.setUpperRightVertex(d, d, height);
    }

    /**
     * Compute diameter.
     *
     * @return diameter
     */
    public final double getDiameter()
    {
        return this.radius * 2;
    }

    @Override
    public Polyhedron clone()
    {
	// return null;
        // Implement this function
	// Wrapper for the copy constructor designed above
        return new Cylinder(this);
    }

    @Override
    public void read(Scanner scanner)
    {
        // Implement this function
	// Read from file (height is first according to documentation)
	this.height = scanner.nextDouble();
	this.radius = scanner.nextDouble();

       	//Adjustments to dimensions require update of bounding box 
        double d = this.getDiameter();
        this.boundingBox.setUpperRightVertex(d, d, height);
    }

    @Override
    public void scale(double scalingFactor)
    {
        // Implement this function
	// Adjust appropriate dimensions by scalingFactor
	this.radius *= scalingFactor;
	this.height *= scalingFactor;
	this.boundingBox.scale(scalingFactor);
    }

    @Override
    public String toString()
    {
	// return "Cylinder.toString Not Implemented";
	// Calls the base class toString then adds on our own
	return super.toString()
	    + "Radius: " + this.radius
	    + " "
	    + "Height: " + this.height;
    }
}
