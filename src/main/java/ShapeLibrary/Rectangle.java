package ShapeLibrary;

import java.awt.*;
import java.util.List;

public class Rectangle extends Shape{
    
    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;

    public Rectangle(double x1,double y1, double x2, double y2, double x3, double y3,double x4, double y4) throws ShapeException {
        point1=new Point(x1,y1);
        point2=new Point(x2,y2);
        point3=new Point(x3,y3);
        point4=new Point(x4,y4);
    }

    public Rectangle(Point point1,Point point2,Point point3,Point point4) throws ShapeException {
        if (point1==null || point2==null || point3==null || point4==null)
            throw new ShapeException("Invalid Point");

        this.point1=point1.copy();
        this.point2=point2.copy();
        this.point3=point3.copy();
        this.point4=point4.copy();
    }

    public Point getPoint1() throws ShapeException { return point1.copy(); }
    public Point getPoint2() throws ShapeException { return point2.copy(); }
    public Point getPoint3() throws ShapeException { return point3.copy(); }
    public Point getPoint4() throws ShapeException { return point4.copy(); }

    public void move(double deltaX, double deltaY) throws ShapeException {
        point1.move(deltaX, deltaY);
        point2.move(deltaX, deltaY);
        point3.move(deltaX,deltaY);
        point4.move(deltaX,deltaY);
    }

    public double computeLength1() {
        return Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2) +
                Math.pow(point2.getY() - point1.getY(), 2));
    }

    public double computeLength2() {
        return Math.sqrt(Math.pow(point2.getX() - point3.getX(), 2) +
                Math.pow(point2.getY() - point3.getY(), 2));
    }

    public double computeLength3() {
        return Math.sqrt(Math.pow(point4.getX() - point3.getX(), 2) +
                Math.pow(point4.getY() - point3.getY(), 2));
    }

    public double computeLength4() {
        return Math.sqrt(Math.pow(point4.getX() - point1.getX(), 2) +
                Math.pow(point4.getY() - point1.getY(), 2));
    }

    public double getLength(){return  computeLength1();}
    public double getWidth(){return computeLength3();}

    public double computeSlope1() {
        return (point2.getY() - point1.getY())/(point2.getX() - point1.getX());
    }
    public double computeSlope2() {
        return (point3.getY() - point2.getY())/(point3.getX() - point2.getX());
    }
    public double computeSlope3() {
        return (point3.getY() - point4.getY())/(point3.getX() - point4.getX());
    }
    public double computeSlope4() {
        return (point4.getY() - point1.getY())/(point4.getX() - point1.getX());
    }

    public double computeArea() throws ShapeException {
        if(!(((computeSlope1()*computeSlope2())==-1) || ((computeSlope2()*computeSlope3())==-1) ||
                ((computeSlope3()*computeSlope4())==-1) || ((computeSlope1()*computeSlope4())==-1))){
            throw new ShapeException("Rectangle cannot be created");
        }
        double area= computeLength1()*computeLength3();
        return area;
    }
    public String toString() {
        return "Rectangle,"+String.valueOf(point1.getX())+","+String.valueOf(point1.getY())+","+String.valueOf(point2.getX())+","+String.valueOf(point2.getY())+","+String.valueOf(point3.getX())+","+String.valueOf(point3.getY())+","+String.valueOf(point4.getX())+","+String.valueOf(point4.getY())+",";
    }

    //Use of this not understood:Shubham
    public List<Shape> getShapes() {
        return null;
    }
    public void render( int xOffset, int yOffset, Graphics graphics) throws ShapeException {

        // Shift the shape by the specified rendering offset
        move(-xOffset, -yOffset);

        // Compute the left side of the bounding box
        int x = (int) Math.round(point1.getX() - getLength());

        // Compute the top side of the bounding box
        int y = (int) Math.round(point1.getY() - getWidth());

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
