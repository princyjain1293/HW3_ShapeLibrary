package ShapeLibrary;

import java.awt.*;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class Point extends Shape{


    /**
     * Point
     *
     * This class represents point objects that can be moved and copied
     */


        private double x;
        private double y;

        /**
         * Constructor
         *
         * @param x                 The x-location of the point -- must be a valid double
         * @param y                 The y-location of the point -- must be a valid double
         * @throws ShapeException   Exception throw if any parameter is invalid
         */
        public Point(double x, double y) throws ShapeException {
            if (Double.isInfinite(x) || Double.isInfinite(y) ||
                    Double.isNaN(x) || Double.isNaN(y))
                throw new ShapeException("Invalid Point");

            this.x = x;
            this.y = y;
        }

        /**
         * @return  The x-location of the point
         */
        public double getX() { return x; }

        /**
         * @return  The y-location of the point
         */
        public double getY() { return y; }

        /**
         * Move the point in the x direction
         *
         * @param deltaX            The delta amount to move the point -- must be a valid double
         * @throws ShapeException   Exception thrown if the parameter is invalid
         */
        public void moveX(double deltaX) throws ShapeException {
            if (Double.isInfinite(deltaX) || Double.isNaN(deltaX))
                throw new ShapeException("Invalid delta value for move operation");

            x += deltaX;
        }

        /**
         * Move the point in the y direction
         *
         * @param deltaY            The delta amount to move the point -- must be a valid double
         * @throws ShapeException   Exception thrown if the parameter is invalid
         */
        public void moveY(double deltaY) throws ShapeException {
            if (Double.isInfinite(deltaY) || Double.isNaN(deltaY))
                throw new ShapeException("Invalid delta value for move operation");

            y += deltaY;
        }

        /**
         * Move the point
         *
         * @param deltaX            The delta amount to move the point in the x direction -- must be a valid double
         * @param deltaY            The delta amount to move the point in the y direction -- must be a valid double
         * @throws ShapeException   Exception throw if any parameter is invalid
         */
        public void move(double deltaX, double deltaY) throws ShapeException {
            moveX(deltaX);
            moveY(deltaY);
        }

        /**
         * Copy the point
         * @return                  A new point with same x and y locations
         * @throws ShapeException   Should never thrown because the current x and y are valid
         */
        public Point copy() throws ShapeException {
            return new Point(x, y);
        }

        public double computeArea(){
            return 0;
        }

        public void scale(double scaleFactor){
            x*=scaleFactor;
            y*=scaleFactor;
        }
//        public String toString() {
//            return "Point,"+(x)+","+(y)+",";
//        }
        public List<Shape> getShapes() {
            return null;
        }
    public void render(int xOffset, int yOffset, Graphics graphics) throws ShapeException {

        // Shift the shape by the specified rendering offset
        move(-xOffset, -yOffset);

        // Compute the left side of the bounding box
        int x1 = (int) Math.round(x);
        int x2 = (int) Math.round(x);

        // Compute the top side of the bounding box
        int y1 = (int) Math.round(y);
        int y2 = (int) Math.round(y);

        // Compute the width of the bounding box
        //int width = (int) myrectangle1.getWidth();

        //  int [] xPoints = {(int)myrectangle1.getPoint1().getX(), (int)myrectangle1.getPoint2().getX(), (int)myrectangle1.getPoint3().getX(), (int)myrectangle1.getPoint4().getX()};
        // int [] yPoints = {(int)myrectangle1.getPoint1().getY(), (int)myrectangle1.getPoint2().getY(), (int)myrectangle1.getPoint3().getY(), (int)myrectangle1.getPoint4().getY()};

        // Draw the circle by drawing an oval in a square bounding box
        graphics.drawLine(x1,y1,x2,y2);

        // Shift the shape back to its original location
        move(xOffset, yOffset);
    }

    public String toText() {
        return "Point,"+String.valueOf(x)+","+String.valueOf(y)+",";
    }


    }


