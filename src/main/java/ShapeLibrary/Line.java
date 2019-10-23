package ShapeLibrary;

import java.awt.*;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class Line extends Shape{


    /**
     *
     *  Line
     *
     *  This class represents line objects that can be moved.  Users of a line can also get its length and slope.
     *
     */


        private Point point1;
        private Point point2;

        /**
         * Constructor based on x-y Locations
         * @param x1                The x-location of first point -- must be a valid double.
         * @param y1                The y-location of first point -- must be a valid double.
         * @param x2                The x-location of second point -- must be a valid double.
         * @param y2                The y-location of second point -- must be a valid double.
         * @throws ShapeException   Exception throw if any parameter is invalid
         */
        public Line(double x1, double y1, double x2, double y2) throws ShapeException {
            point1 = new Point(x1, y1);
            point2 = new Point(x2, y2);
        }

        /**
         *
         * @param point1            The first point -- must not be null
         * @param point2            The second point -- must not b e null
         * @throws ShapeException   Exception throw if any parameter is invalid
         */
        public Line(Point point1, Point point2) throws ShapeException {
            if (point1==null || point2==null)
                throw new ShapeException("Invalid Point");

            this.point1 = point1.copy();
            this.point2 = point2.copy();
        }

        /**
         * @return  The first point
         */
        public Point getPoint1() throws ShapeException { return point1.copy(); }

        /**
         * @return  The second point
         */
        public Point getPoint2() throws ShapeException { return point2.copy(); }

        /**
         * Move a line
         *
         * @param deltaX            The delta x-location by which the line should be moved -- must be a valid double
         * @param deltaY            The delta y-location by which the line should be moved -- must be a valid double
         * @throws ShapeException   Exception throw if any parameter is invalid
         */
        public void move(double deltaX, double deltaY) throws ShapeException {
            point1.move(deltaX, deltaY);
            point2.move(deltaX, deltaY);
        }

        /**
         * @return  The length of the line
         */
        public double computeLength() {
            double length=Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2) +
                    Math.pow(point2.getY() - point1.getY(), 2));
            return length;
        }

    public void scale(double scaleFactor) throws ShapeException {
        Validator.validatePositiveDouble(scaleFactor, "Invalid scale factor");
        point1.scale(scaleFactor);
        point2.scale(scaleFactor);
    }


        public double computeSlope() {
            return (point2.getY() - point1.getY())/(point2.getX() - point1.getX());
        }

        public double computeArea(){
            return 0;
        }

        public List<Shape> getShapes() {
            return null;
        }
        public void render(int xOffset, int yOffset, Graphics graphics) throws ShapeException {

            // Shift the shape by the specified rendering offset
            move(-xOffset, -yOffset);
            // Compute the left side of the bounding box
            int x1 = (int) Math.round(point1.getX());
            int x2 = (int) Math.round(point2.getX());

            // Compute the top side of the bounding box
            int y1 = (int) Math.round(point1.getY());
            int y2 = (int) Math.round(point2.getY());

            // Draw the circle by drawing an oval in a square bounding box
            graphics.drawLine(x1,y1,x2,y2);

            // Shift the shape back to its original location
            move(xOffset, yOffset);
        }
    public String toText() {
        return "Line,"+String.valueOf(point1.getX())+","+String.valueOf(point1.getY())+","+String.valueOf(point2.getX())+","+String.valueOf(point2.getY())+",";
    }
    }


