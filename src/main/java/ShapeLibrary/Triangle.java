package ShapeLibrary;


import java.awt.*;
import java.util.List;

public class Triangle extends Shape{
    private Point point1;
    private Point point2;
    private Point point3;
    private Line line1;
    private Line line2;
    private Line line3;
    private double side1;
    private double side2;
    private double side3;
    /**
     * Constructor based on x-y Locations of points of Triangle
     * @param x1                The x-location of first point -- must be a valid double.
     * @param y1                The y-location of first point -- must be a valid double.
     * @param x2                The x-location of second point -- must be a valid double.
     * @param y2                The y-location of second point -- must be a valid double.
     * @param x3                The y-location of second point -- must be a valid double.
     * @param y3                The y-location of second point -- must be a valid double.
     * @throws ShapeException   Exception throw if any parameter is invalid
     */

    public Triangle(double x1,double y1, double x2, double y2, double x3, double y3) throws ShapeException {
        point1=new Point(x1,y1);
        point2=new Point(x2,y2);
        point3=new Point(x3,y3);
        line1=new Line(point1,point2);
        line2=new Line(point2,point3);
        line3=new Line(point1,point3);
        side1= line1.computeLength();
        side2=line2.computeLength();
        side3=line3.computeLength();
        if((line1.computeSlope()==line2.computeSlope()) || (line2.computeSlope()==line3.computeSlope()) || (line3.computeSlope()==line1.computeSlope()))
            throw new ShapeException("Points must not be collinear");
    }

    /**
     * Constructs a triangle with given 3 lines of triangle
     * @param line1                 First line of triangle -- must contain a valid Line reference
     * @param line2                 Second line of triangle -- must contain a valid Line reference
     * @param line3                 Third line of triangle -- must contain a valid Line reference
     */

    public Triangle(Line line1,Line line2,Line line3) throws ShapeException {
        if (line1==null || line2==null || line3==null)
            throw new ShapeException("Invalid Point");

        this.line1=line1;
        this.line2=line1;
        this.line3=line3;
        if((line1.computeSlope()==line2.computeSlope()) || (line2.computeSlope()==line3.computeSlope()) || (line3.computeSlope()==line1.computeSlope()))
            throw new ShapeException("Points must not be collinear");
    }

    /**
     * Constructs a triangle with given sides triangle
     * @param side1               side of triangle -- must be a valid double
     * @param side2                side of triangle -- must be a valid double
     * @param side3                side of triangle -- must be a valid double
     */
    public Triangle(double side1,double side2, double side3) throws ShapeException {
        Validator.validatePositiveDouble(side1, "Invalid side");
        Validator.validatePositiveDouble(side2, "Invalid side");
        Validator.validatePositiveDouble(side3, "Invalid side");
        this.side1=side1;
        this.side2=side2;
        this.side3=side3;
        if((side1+side2)<side3 || (side2+side3)<side1 || (side1+side3)<side2)
            throw new ShapeException("Does not follow side sum rule");
    }

    public Point getPoint1() throws ShapeException { return point1.copy(); }
    public Point getPoint2() throws ShapeException { return point2.copy(); }
    public Point getPoint3() throws ShapeException { return point3.copy(); }


    public void move(double deltaX, double deltaY) throws ShapeException {
        point1.move(deltaX, deltaY);
        point2.move(deltaX, deltaY);
        point3.move(deltaX,deltaY);
    }

    //This function computes the perimeter of triangle
    public double getPerimeter(){return side1+side2+side3;}

    //This function computes the area of Triangle
    public double computeArea() {

        double s= getPerimeter()/2;
        return Math.sqrt(s*(s-side1)*(s-side2)*(s-side3));
    }

    public List<Shape> getShapes() {
        return null;
    }
    public void render(int xOffset, int yOffset, Graphics graphics) throws ShapeException {

        // Shift the shape by the specified rendering offset
        move(-xOffset, -yOffset);

        // Compute the left side of the bounding box
        int x = (int) Math.round(point1.getX() - line1.computeLength());

        // Compute the top side of the bounding box
        int y = (int) Math.round(point3.getY() - line3.computeLength());

        // Compute the width of the bounding box
        int width = (int) line2.computeLength();

        int [] xPoints = {(int)point1.getX(), (int)point2.getX(), (int)point3.getX()};
        int [] yPoints = {(int)point1.getY(), (int)point2.getY(), (int)point3.getY()};

        // Draw the circle by drawing an oval in a square bounding box
        graphics.drawPolygon(xPoints, yPoints, 3);

        // Shift the shape back to its original location
        move(xOffset, yOffset);
    }
}
