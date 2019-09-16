package edu.iastate.cs228.hw01;


/**
 * @author 
 * Sean Gordon
 *         
 */
 
 
public class Triangle extends GeometricObject
{
private double side1, side2, side3;
	
	public Triangle() {
		this.side1 = 1;
		this.side2 = 2;
		this.side3 = 3;
	}
	public Triangle(double side1, double side2, double side3) {
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}
	
	
	public double getSide1() {
		return side1;
	}
	public double getSide2() {
		return side2;
	}
	public double getSide3() {
		return side3;
	}
	
	public double getArea() {
		double area = (side1 * side2 * side3)/2;
		area = Math.sqrt(area*(area - side1)*(area - side2)*(area - side3));
		return area;
	}
	
	public double getPerimeter() {
		double perimeter = side1 + side2 + side3;
		return perimeter;
	}
	
	
	@Override
	public String toString()
	{
		return "Triangle: side1 = " + side1 + " side2 = " + side2 + " side3 = " + side3;
	}
}
