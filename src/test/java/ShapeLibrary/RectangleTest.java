package ShapeLibrary;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.*;

public class RectangleTest {
    @Test
    public void testValidConstructor() throws ShapeException {
        Point point1 = new Point(0,0);
        Point point2 = new Point(0,3);

        Line line1 = new Line(point1, point2);
        assertEquals(0, line1.getPoint1().getX(), 0);
        assertEquals(0, line1.getPoint1().getY(), 0);
        assertEquals(0, line1.getPoint2().getX(), 0);
        assertEquals(3, line1.getPoint2().getY(), 0);

        Point point3 = new Point(4,3);

        Line line2 = new Line(point2, point3);
        assertEquals(0, line2.getPoint1().getX(), 0);
        assertEquals(3, line2.getPoint1().getY(), 0);
        assertEquals(4, line2.getPoint2().getX(), 0);
        assertEquals(3, line2.getPoint2().getY(), 0);

        Point point4 = new Point(4,0);

        Line line3 = new Line(point3, point4);
        assertEquals(4, line3.getPoint1().getX(), 0);
        assertEquals(3, line3.getPoint1().getY(), 0);
        assertEquals(4, line3.getPoint2().getX(), 0);
        assertEquals(0, line3.getPoint2().getY(), 0);

        Line line4 = new Line(point4, point1);
        assertEquals(4, line4.getPoint1().getX(), 0);
        assertEquals(0, line4.getPoint1().getY(), 0);
        assertEquals(0, line4.getPoint2().getX(), 0);
        assertEquals(0, line4.getPoint2().getY(), 0);

        Rectangle myRectangle = new Rectangle(line1,line2,line3,line4);

        myRectangle = new Rectangle(0,0,0,3,4,3,4,0);
        assertEquals(0, myRectangle.getPoint1().getX(), 0);
        assertEquals(0, myRectangle.getPoint1().getY(), 0);
        assertEquals(3, myRectangle.getPoint2().getY(), 0);
        assertEquals(0, myRectangle.getPoint2().getX(), 0);
        assertEquals(0, myRectangle.getPoint1().getX(), 0);
        assertEquals(3, myRectangle.getPoint1().getY(), 0);
        assertEquals(4, myRectangle.getPoint2().getX(), 0);
        assertEquals(3, myRectangle.getPoint2().getY(), 0);
        assertEquals(4, myRectangle.getPoint1().getX(), 0);
        assertEquals(3, myRectangle.getPoint1().getY(), 0);
        assertEquals(4, myRectangle.getPoint2().getX(), 0);
        assertEquals(0, myRectangle.getPoint2().getY(), 0);
        assertEquals(4, myRectangle.getPoint1().getX(), 0);
        assertEquals(0, myRectangle.getPoint1().getY(), 0);
        assertEquals(0, myRectangle.getPoint2().getX(), 0);
        assertEquals(0, myRectangle.getPoint2().getY(), 0);
    }

    @Test
    public void testInvalidConstructor() throws ShapeException {
        Point point1 = new Point(0, 0);
        Point point2 = new Point(0, 3);
        Point point3 = new Point(4, 3);
        Point point4 = new Point(4, 0);
        Line line1 = new Line(point1, point2);
        Line line2 = new Line(point2, point3);
        Line line3 = new Line(point3, point4);
        Line line4 = new Line(point4, point1);
        try {
            new Rectangle(line1, line2, line3, null);
            fail("Expected exception not thrown for when the last parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Rectangle(line1, line2, null, line4);
            fail("Expected exception not thrown for when the last parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Rectangle(line1, null, line3, line4);
            fail("Expected exception not thrown for when the last parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Rectangle(null, line2, line3, line4);
            fail("Expected exception not thrown for when the last parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Rectangle(Double.POSITIVE_INFINITY, 0, 0, 3, 4, 3, 4, 0);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Rectangle(0, Double.POSITIVE_INFINITY, 0, 3, 4, 3, 4, 0);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Rectangle(0, 0, Double.POSITIVE_INFINITY, 3, 4, 3, 4, 0);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Rectangle(0, 0, 0, Double.POSITIVE_INFINITY, 4, 3, 4, 0);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Rectangle(0, 0, 0, 3, Double.POSITIVE_INFINITY, 3, 4, 0);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Rectangle(0, 0, 0, 3, 4, Double.POSITIVE_INFINITY, 4, 0);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Rectangle(0, 0, 0, 3, 4, 3, Double.POSITIVE_INFINITY, 0);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Rectangle(0, 0, 0, 3, 4, 3, 4, Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Rectangle(0, 0);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid side", e.getMessage());
        }
        try {
            new Rectangle(1, 3, 3, 5, 5, 3, 3, 2);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Adjacent line are not perpendicular", e.getMessage());
        }
        try{
            Point point5 = new Point(1, 3);
            Point point6 = new Point(3, 5);
            Point point7 = new Point(5, 3);
            Point point8 = new Point(3, 2);
            Line line5 = new Line(point5, point6);
            Line line6 = new Line(point6, point7);
            Line line7 = new Line(point7, point8);
            Line line8 = new Line(point8, point5);
            new Rectangle(line5, line6, line7, line8);
            fail("Expected exception not thrown");
        }catch(ShapeException e)
        {
            assertEquals("Adjacent line are not perpendicular", e.getMessage());
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
    public void getPoint4() {
    }

    @Test
    public void testGetLength() throws ShapeException {
        Rectangle myRectangle = new Rectangle(10, 20);
        assertEquals(10, myRectangle.getLength(), 0);
    }

    @Test
    public void testGetBreadth() throws ShapeException {
        Rectangle myRectangle = new Rectangle(10, 20);
        assertEquals(20, myRectangle.getBreadth(), 0);
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
    public void getLine4() {
    }

    @Test
    public void testMove() throws ShapeException {
        Rectangle myRectangle = new Rectangle(0, 0, 0, 3, 4, 3, 4, 0);
        myRectangle.move(3, 4);
        assertEquals(3, myRectangle.getPoint1().getX(), 0);
        assertEquals(4, myRectangle.getPoint1().getY(), 0);
        assertEquals(3, myRectangle.getPoint2().getX(), 0);
        assertEquals(7, myRectangle.getPoint2().getY(), 0);
        assertEquals(7, myRectangle.getPoint3().getX(), 0);
        assertEquals(7, myRectangle.getPoint3().getY(), 0);
        assertEquals(7, myRectangle.getPoint4().getX(), 0);
        assertEquals(4, myRectangle.getPoint4().getY(), 0);
//        assertEquals(7, myRectangle.getPoint1().getX(), 0);
//        assertEquals(7, myRectangle.getPoint1().getY(), 0);
//        assertEquals(7, myRectangle.getPoint2().getX(), 0);
//        assertEquals(4, myRectangle.getPoint2().getY(), 0);
//        assertEquals(7, myRectangle.getPoint1().getX(), 0);
//        assertEquals(4, myRectangle.getPoint1().getY(), 0);
//        assertEquals(3, myRectangle.getPoint2().getX(), 0);
//        assertEquals(4, myRectangle.getPoint2().getY(), 0);
    }

    @Test
    public void testComputeArea() throws ShapeException {
        Rectangle myRectangle = new Rectangle(10, 20);
        assertEquals(200, myRectangle.computeArea(), 0);
    }

    @Test
    public void testToString() {

    }

    @Test
    public void testGetShapes() {
        try{
            Rectangle myRectangle = new Rectangle(20,20,20,40,60,40,60,20);
            assertEquals(null, myRectangle.getShapes());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testRender() {
        try {
            Rectangle myRectangle = new Rectangle(20,20,20,40,60,40,60,20);
            BufferedImage bImg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = bImg.createGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, 100, 100);
            graphics.setColor(Color.BLACK);

            // Stimulus
            myRectangle.render( 0, 0,graphics);
            // Write to a file so the results can be compared manually
            assertTrue(ImageIO.write(bImg, "png", new File("rectangle.png")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}