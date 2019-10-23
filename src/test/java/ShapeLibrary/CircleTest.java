package ShapeLibrary;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.*;

public class CircleTest {

    @Test
    public void testValidConstruction() throws ShapeException {
        Point center = new Point(1,2);
        Circle myCircle = new Circle(center, 5);
        assertEquals(1, myCircle.getCenter().getX(), 0);
        assertEquals(2, myCircle.getCenter().getY(), 0);
        assertEquals(5, myCircle.getRadius(), 0);

        myCircle = new Circle(1.3, 2.6, 2.5);
        assertEquals(1.3, myCircle.getCenter().getX(), 0);
        assertEquals(2.6, myCircle.getCenter().getY(), 0);
        assertEquals(2.5, myCircle.getRadius(), 0);
    }
    @Test
    public void testInvalidConstruction() {

        try {
            new Circle(null, 2.5);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid center point", e.getMessage());
        }

        try {
            new Circle( new Point(1, 2), Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid radius", e.getMessage());
        }

        try {
            new Circle(new Point(1, 2), Double.NEGATIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid radius", e.getMessage());
        }

        try {
            new Circle(new Point(1, 2), Double.NaN);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid radius", e.getMessage());
        }

        try {
            new Circle(Double.POSITIVE_INFINITY, 2, 3);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }

        try {
            new Circle(Double.NEGATIVE_INFINITY, 2, 3);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }

        try {
            new Circle(Double.NaN, 2, 3);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }

        try {
            new Circle(1, Double.POSITIVE_INFINITY, 3);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }

        try {
            new Circle(1, Double.NEGATIVE_INFINITY, 3);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }

        try {
            new Circle(1, Double.NaN, 3);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }


        try {
            new Circle(1, 2, Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid radius", e.getMessage());
        }

        try {
            new Circle(1, 2, Double.NEGATIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid radius", e.getMessage());
        }

        try {
            new Circle(1, 2, Double.NaN);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid radius", e.getMessage());
        }

    }

    @Test
    public void getCenter() {
    }

    @Test
    public void getRadius() {
    }

    @Test
    public void testMove() throws ShapeException {
        Circle myCircle = new Circle(1, 2, 5);
        assertEquals(1, myCircle.getCenter().getX(), 0);
        assertEquals(2, myCircle.getCenter().getY(), 0);
        assertEquals(5, myCircle.getRadius(), 0);

        myCircle.move(3,  4);
        assertEquals(4, myCircle.getCenter().getX(), 0);
        assertEquals(6, myCircle.getCenter().getY(), 0);
        assertEquals(5, myCircle.getRadius(), 0);

        myCircle.move(0.123,  0.456);
        assertEquals(4.123, myCircle.getCenter().getX(), 0);
        assertEquals(6.456, myCircle.getCenter().getY(), 0);
        assertEquals(5, myCircle.getRadius(), 0);

        myCircle.move(-0.123,  -0.456);
        assertEquals(4, myCircle.getCenter().getX(), 0);
        assertEquals(6, myCircle.getCenter().getY(), 0);
        assertEquals(5, myCircle.getRadius(), 0);

        myCircle.move(-12,  -26);
        assertEquals(-8, myCircle.getCenter().getX(), 0);
        assertEquals(-20, myCircle.getCenter().getY(), 0);
        assertEquals(5, myCircle.getRadius(), 0);

        try {
            myCircle.move(Double.POSITIVE_INFINITY, 1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta value for move operation", e.getMessage());
        }

        try {
            myCircle.move(Double.NEGATIVE_INFINITY, 1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta value for move operation", e.getMessage());
            // ignore
        }

        try {
            myCircle.move(Double.NaN, 1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta value for move operation", e.getMessage());
        }

        try {
            myCircle.move(1, Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta value for move operation", e.getMessage());
        }

        try {
            myCircle.move(1, Double.NEGATIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta value for move operation", e.getMessage());
        }

        try {
            myCircle.move(1, Double.NaN);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid delta value for move operation", e.getMessage());
        }
    }

    @Test
    public void testScale() throws ShapeException {
        Circle myCircle = new Circle(1, 2, 5);
        assertEquals(1, myCircle.getCenter().getX(), 0);
        assertEquals(2, myCircle.getCenter().getY(), 0);
        assertEquals(5, myCircle.getRadius(), 0);

        myCircle.scale(3);
        assertEquals(1, myCircle.getCenter().getX(), 0);
        assertEquals(2, myCircle.getCenter().getY(), 0);
        assertEquals(15, myCircle.getRadius(), 0);

        myCircle.scale(0.2);
        assertEquals(1, myCircle.getCenter().getX(), 0);
        assertEquals(2, myCircle.getCenter().getY(), 0);
        assertEquals(3, myCircle.getRadius(), 0);

        try {
            myCircle.scale(Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid scale factor", e.getMessage());

        }

        try {
            myCircle.scale(Double.NEGATIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid scale factor", e.getMessage());
        }

        try {
            myCircle.scale(Double.NaN);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Invalid scale factor", e.getMessage());
        }
    }

    @Test
    public void testComputeArea() throws ShapeException {
        Circle myCircle = new Circle(1, 2, 5);
        assertEquals(78.53975, myCircle.computeArea(), 0.0001);

        myCircle = new Circle(1, 2, 4.234);
        assertEquals(56.3185174, myCircle.computeArea(), 0.0001);

        myCircle = new Circle(1, 2, 0);
        assertEquals(0, myCircle.computeArea(), 0);
    }

//    @Test
//    public void toString() {
//    }

    @Test
    public void testGetShapes() {
        try{
            Circle myCircle = new Circle(40, 40, 30);
            assertEquals(null, myCircle.getShapes());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testRender() {
        try {
            Circle myCircle = new Circle(40, 40, 30);
            BufferedImage bImg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = bImg.createGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, 100, 100);
            graphics.setColor(Color.BLACK);

            // Stimulus
            myCircle.render( 0, 0,graphics);
            // Write to a file so the results can be compared manually
            assertTrue(ImageIO.write(bImg, "png", new File("circle.png")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveOutputStream() throws ShapeException {
        Circle myCircle = new Circle(10,10,5);
        myCircle.saveOutputStream("circle.txt", myCircle);
        myCircle.saveOutputStream("myCircle.txt", myCircle);
    }

    @Test
    public void testToText() throws ShapeException {
        Circle myCircle= new Circle(10,10,5);
        String expected="Circle,10.0,10.0,5.0,";
        String actual=myCircle.toText();
        assertEquals(expected,actual);
    }

    @Test
    public void testLoadInputStream(){
        try {
            File file = new File("C:\\Users\\princy\\Desktop\\Cs5700\\HW2_ShapeLibrary\\circle.txt");
            Shape shape = Shape.loadInputStream(new FileInputStream(file));
            System.out.println(shape.computeArea());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}