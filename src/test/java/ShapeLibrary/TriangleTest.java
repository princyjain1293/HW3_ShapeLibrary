package ShapeLibrary;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.*;

public class TriangleTest {

    @Test
    public void testValidConstructor() throws ShapeException {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(3, 6);
        Line line1 = new Line(point1, point2);
        assertEquals(1, line1.getPoint1().getX(), 0);
        assertEquals(2, line1.getPoint1().getY(), 0);
        assertEquals(3, line1.getPoint2().getX(), 0);
        assertEquals(6, line1.getPoint2().getY(), 0);
        Point point3 = new Point(4, 1);
        Line line2 = new Line(point2, point3);
        assertEquals(3, line2.getPoint1().getX(), 0);
        assertEquals(6, line2.getPoint1().getY(), 0);
        assertEquals(4, line2.getPoint2().getX(), 0);
        assertEquals(1, line2.getPoint2().getY(), 0);
        Line line3 = new Line(point3, point1);
        assertEquals(4, line3.getPoint1().getX(), 0);
        assertEquals(1, line3.getPoint1().getY(), 0);
        assertEquals(1, line3.getPoint2().getX(), 0);
        assertEquals(2, line3.getPoint2().getY(), 0);
        Triangle myTriangle = new Triangle(line1, line2, line3);
        myTriangle = new Triangle(1, 2, 3, 6, 4, 1);
        assertEquals(1, myTriangle.getPoint1().getX(), 0);
        assertEquals(2, myTriangle.getPoint1().getY(), 0);
        assertEquals(3, myTriangle.getPoint2().getX(), 0);
        assertEquals(6, myTriangle.getPoint2().getY(), 0);
        assertEquals(4, myTriangle.getPoint3().getX(), 0);
        assertEquals(1, myTriangle.getPoint3().getY(), 0);
//        assertEquals(4, myTriangle.getPoint2().getX(), 0);
//        assertEquals(1, myTriangle.getPoint2().getY(), 0);
//        assertEquals(4, myTriangle.getPoint1().getX(), 0);
//        assertEquals(1, myTriangle.getPoint1().getY(), 0);
//        assertEquals(1, myTriangle.getPoint2().getX(), 0);
//        assertEquals(2, myTriangle.getPoint2().getY(), 0);
    }

    @Test
    public void testInvalidConstructor() throws ShapeException {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(1, 4);
        Point point3 = new Point(3, 4);
        Line line1 = new Line(point1, point2);
        Line line2 = new Line(point2, point3);
        Line line3 = new Line(point3, point1);
        try {
            new Triangle(line1, line2, null);
            fail("Expected exception not thrown for when the last parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Triangle(line1, null, line3);
            fail("Expected exception not thrown for when the last parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Triangle(null, line2, line3);
            fail("Expected exception not thrown for when the last parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Triangle(Double.POSITIVE_INFINITY, 2, 3,6,4,1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Triangle(1, Double.POSITIVE_INFINITY, 3,6,4,1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Triangle(1, 2, Double.POSITIVE_INFINITY,6,4,1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Triangle(1, 2, 3,Double.POSITIVE_INFINITY,4,1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Triangle(1, 2, 3,4,Double.POSITIVE_INFINITY,1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Triangle(1, 2, 3,4,4,Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Triangle(-3,2,5);
            fail("Expected exception not thrown for when the last parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid side", e.getMessage());
        }
        try {
            new Triangle(1,1,2,2,3,3);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Points must not be collinear", e.getMessage());
        }
        try {
            Point point4 = new Point(1, 1);
            Point point5 = new Point(2, 2);
            Point point6 = new Point(3, 3);
            Line line4 = new Line(point4, point5);
            Line line5 = new Line(point5, point6);
            Line line6 = new Line(point6, point4);
            new Triangle(line4, line5, line6);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Points must not be collinear", e.getMessage());
        }
    }

    @Test
    public void getPoint1() {
    }

    @Test
    public void getPoint2() {
    }

    @Test
    public void getPoint3() {
    }

    @Test
    public void getLine1() {
    }

    @Test
    public void getLine2() {
    }

    @Test
    public void getLine3() {
    }

    @Test
    public void testMove() throws ShapeException {
        Triangle myTriangle = new Triangle(1, 2, 3, 6, 4, 1);
        myTriangle.move(1.5,1.25);
        assertEquals(2.5, myTriangle.getPoint1().getX(), 0);
        assertEquals(3.25, myTriangle.getPoint1().getY(), 0);
        assertEquals(4.5, myTriangle.getPoint2().getX(), 0);
        assertEquals(7.25, myTriangle.getPoint2().getY(), 0);
        assertEquals(5.5, myTriangle.getPoint3().getX(), 0);
        assertEquals(2.25, myTriangle.getPoint3().getY(), 0);
//        assertEquals(5.5, myTriangle.getPoint2().getX(), 0);
//        assertEquals(2.25, myTriangle.getPoint2().getY(), 0);
//        assertEquals(5.5, myTriangle.getPoint1().getX(), 0);
//        assertEquals(2.25, myTriangle.getLine3().getPoint1().getY(), 0);
//        assertEquals(2.5, myTriangle.getLine3().getPoint2().getX(), 0);
//        assertEquals(3.25, myTriangle.getLine3().getPoint2().getY(), 0);
    }

    @Test
    public void testGetPerimeter() throws ShapeException {
        Triangle myTriangle = new Triangle(10,6,8);
        assertEquals(24, myTriangle.getPerimeter(),0);
    }

    @Test
    public void testScale() throws ShapeException {
        Triangle triangle= new Triangle(0,0,0,3,5,0);
        triangle.scale(2);
        assertEquals(0,triangle.getPoint1().getX(),0);
        assertEquals(0,triangle.getPoint1().getY(),0);
        assertEquals(0,triangle.getPoint2().getX(),0);
        assertEquals(6,triangle.getPoint2().getY(),0);
        assertEquals(10,triangle.getPoint3().getX(),0);
        assertEquals(0,triangle.getPoint3().getY(),0);
    }

    @Test
    public void testcomputeArea() throws ShapeException {
        Triangle myTriangle = new Triangle(10,6,8);
        assertEquals(24, myTriangle.computeArea(),0);
    }

    @Test
    public void testSaveOutputStream(){
        try{
            Triangle myTriangle = new Triangle(20,20,20,40,60,40);
            myTriangle.saveOutputStream("triangle.txt", myTriangle);
            myTriangle.saveOutputStream("myTriangle.txt", myTriangle);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testLoadInputStream(){
        try {
            File file = new File("C:\\Users\\princy\\Desktop\\Cs5700\\HW2_ShapeLibrary\\triangle.txt");
            Shape.loadInputStream(new FileInputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testGetShapes() {
        try{
            Triangle myTriangle = new Triangle(10, 20, 30, 60, 40, 10);
            assertEquals(null, myTriangle.getShapes());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testRender() {
        try {
            Triangle myTriangle = new Triangle(10, 20, 30, 60, 40, 10);
            BufferedImage bImg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = bImg.createGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, 100, 100);
            graphics.setColor(Color.BLACK);

            // Stimulus
            myTriangle.render( 0, 0,graphics);
            // Write to a file so the results can be compared manually
            assertTrue(ImageIO.write(bImg, "png", new File("triangle.png")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}