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
    }

    public Triangle(Line line1,Line line2,Line line3) throws ShapeException {
        if (line1==null || line2==null || line3==null)
            throw new ShapeException("Invalid Point");

        this.line1=line1;
        this.line2=line1;
        this.line3=line3;
    }

    public Point getPoint1() throws ShapeException { return point1.copy(); }
    public Point getPoint2() throws ShapeException { return point2.copy(); }
    public Point getPoint3() throws ShapeException { return point3.copy(); }

    public void move(double deltaX, double deltaY) throws ShapeException {
        point1.move(deltaX, deltaY);
        point2.move(deltaX, deltaY);
        point3.move(deltaX,deltaY);
    }

    public double computeLength1() {
        return Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2) +
                Math.pow(point2.getY() - point1.getY(), 2));
    }

    public double computeLength2() {
        return Math.sqrt(Math.pow(point3.getX() - point1.getX(), 2) +
                Math.pow(point3.getY() - point1.getY(), 2));
    }

    public double computeLength3() {
        return Math.sqrt(Math.pow(point2.getX() - point3.getX(), 2) +
                Math.pow(point2.getY() - point3.getY(), 2));
    }

    public double computeSlope1() {
        return (point2.getY() - point1.getY())/(point2.getX() - point1.getX());
    }
    public double computeSlope2() {
        return (point3.getY() - point1.getY())/(point3.getX() - point1.getX());
    }
    public double computeSlope3() {
        return (point3.getY() - point2.getY())/(point3.getX() - point2.getX());
    }

    public double computeArea() throws ShapeException {
        if(!(computeSlope1()==(computeSlope2()) && (computeSlope2()==computeSlope3()) && (computeSlope1()==computeSlope3()))){
            throw new ShapeException("Triangle cannot be created");
        }
        double area;
        double s= (computeLength1()+computeLength2()+computeLength3())/3;
        area= Math.sqrt(s*(Math.pow((s-computeLength1()),2))*(Math.pow((s-computeLength2()),2))*(Math.pow((s-computeLength3()),2)));
        return area;
    }
    public String toString() {
        return "Triangle,"+String.valueOf(point1.getX())+","+String.valueOf(point1.getY())+","+String.valueOf(point2.getX())+","+String.valueOf(point2.getY())+","+String.valueOf(point3.getX())+","+String.valueOf(point3.getY())+",";
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
