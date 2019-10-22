package ShapeLibrary;

import java.awt.*;
import java.util.List;

public class Rectangle extends Shape{
    private double length;
    private double breadth;
    private Line line1;
    private Line line2;
    private Line line3;
    private Line line4;
    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;

    /**
     * Constructor based on x-y Locations of points of Rectangle
     * @param x1                The x-location of first point -- must be a valid double.
     * @param y1                The y-location of first point -- must be a valid double.
     * @param x2                The x-location of second point -- must be a valid double.
     * @param y2                The y-location of second point -- must be a valid double.
     * @param x3                The y-location of second point -- must be a valid double.
     * @param y3                The y-location of second point -- must be a valid double.
     * @param x4                The y-location of second point -- must be a valid double.
     * @param y4                The y-location of second point -- must be a valid double.
     * @throws ShapeException   Exception throw if any parameter is invalid
     */

    public Rectangle(double x1,double y1, double x2, double y2, double x3, double y3,double x4, double y4) throws ShapeException {
        point1=new Point(x1,y1);
        point2=new Point(x2,y2);
        point3=new Point(x3,y3);
        point4=new Point(x4,y4);
        line1= new Line(point1,point2);
        line2= new Line(point2,point3);
        line3= new Line(point3,point4);
        line4= new Line(point4,point1);
        this.length=line1.computeLength();
        this.breadth= line2.computeLength();
        /**
         *  condition to check if the lines are
          *  perpendicular or not!!
         */
//        if((line1.computeSlope()*line2.computeSlope())!=-1 || (line2.computeSlope()*line3.computeSlope())!=-1 || (line3.computeSlope()*line4.computeSlope())!=-1 || (line4.computeSlope()*line1.computeSlope())!= -1)
//            throw new ShapeException("Adjacent line are not perpendicular");
    }

    /**
     * Constructs a rectangle with given 4 lines of rectangle
     * @param line1                 First line of rectangle -- must contain a valid Line reference
     * @param line2                 Second line of rectangle -- must contain a valid Line reference
     * @param line3                 Third line of rectangle -- must contain a valid Line reference
     * @param line4                 Fourth line of rectangle -- must contain a valid Line reference
     */

    public Rectangle(Line line1,Line line2,Line line3,Line line4) throws ShapeException {
        if (line1==null || line2==null || line3==null || line4==null)
            throw new ShapeException("Invalid Point");

        this.line1=line1;
        this.line2=line2;
        this.line3=line3;
        this.line4=line4;
        /**
         *  condition to check if the lines are
           * perpendicular or not!!
         */
        if((line1.computeSlope()*line2.computeSlope())!=-1 || (line2.computeSlope()*line3.computeSlope())!=-1 || (line3.computeSlope()*line4.computeSlope())!=-1 || (line4.computeSlope()*line1.computeSlope())!= -1)
            throw new ShapeException("Adjacent line are not perpendicular");
    }
    /**
     * Constructs a rectangle with given length and width of rectangle
     * @param length               length of rectangle -- must be a valid double
     * @param breadth                Width of rectangle -- must be a valid double
     */

    public Rectangle(double length, double breadth) throws ShapeException {
        if(length<=0 || breadth<=0)
            throw new ShapeException("Invalid side");
        this.length=length;
        this.breadth= breadth;
    }

    public Point getPoint1(){ return point1; }
    public Point getPoint2(){ return point2; }
    public Point getPoint3(){ return point3; }
    public Point getPoint4(){ return point4; }
    public double getLength() {return length;}
    public double getBreadth() {return breadth;}


    public void move(double deltaX, double deltaY) throws ShapeException {
        point1.move(deltaX, deltaY);
        point2.move(deltaX, deltaY);
        point3.move(deltaX,deltaY);
        point4.move(deltaX,deltaY);
    }

    public void scale(double scaleFactor){
        point1.scale(scaleFactor);
        point2.scale(scaleFactor);
        point3.scale(scaleFactor);
        point4.scale(scaleFactor);
    }

    /**
     * This function calculates the area of the rectangle
     */

    public double computeArea() throws ShapeException {

        double area= length*breadth;
        return area;
    }
//    public String toString() {
//        return "Rectangle,"+String.valueOf(point1.getX())+","+String.valueOf(point1.getY())+","+String.valueOf(point2.getX())+","+String.valueOf(point2.getY())+","+String.valueOf(point3.getX())+","+String.valueOf(point3.getY())+","+String.valueOf(point4.getX())+","+String.valueOf(point4.getY())+",";
//    }


    public List<Shape> getShapes() {
        return null;
    }
    public void render( int xOffset, int yOffset, Graphics graphics) throws ShapeException {

        // Shift the shape by the specified rendering offset
        move(-xOffset, -yOffset);

        // Compute the left side of the bounding box
        int x = (int) Math.round(point1.getX() - getLength());

        // Compute the top side of the bounding box
        int y = (int) Math.round(point1.getY() - getBreadth());

        // Compute the width of the bounding box
        //int width = (int) getWidth();

        int [] xPoints = {(int)point1.getX(), (int)point2.getX(), (int)point3.getX(), (int)point4.getX()};
        int [] yPoints = {(int)point1.getY(), (int)point2.getY(), (int)point3.getY(), (int)point4.getY()};

        // Draw the circle by drawing an oval in a square bounding box
        graphics.drawPolygon(xPoints, yPoints, 4);

        // Shift the shape back to its original location
        move(xOffset, yOffset);
    }

}
