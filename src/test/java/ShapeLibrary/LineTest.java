package ShapeLibrary;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.*;

public class LineTest {

    @Test
    public void testValidConstructor() throws ShapeException {
        Point p1 = new Point(1,2);
        Point p2 = new Point(4, 10);

        Line myLine = new Line(p1, p2);
        assertEquals(1, myLine.getPoint1().getX(), 0);
        assertEquals(2, myLine.getPoint1().getY(), 0);
        assertEquals(4, myLine.getPoint2().getX(), 0);
        assertEquals(10, myLine.getPoint2().getY(), 0);

        myLine = new Line(p1, p1);
        assertEquals(1, myLine.getPoint1().getX(), 0);
        assertEquals(2, myLine.getPoint1().getY(), 0);
        assertEquals(1, myLine.getPoint2().getX(), 0);
        assertEquals(2, myLine.getPoint2().getY(), 0);

        p1 = new Point(1.4,2.5);
        p2 = new Point(4.6, 10.7);
        myLine = new Line(p1, p2);
        assertEquals(1.4, myLine.getPoint1().getX(), 0);
        assertEquals(2.5, myLine.getPoint1().getY(), 0);
        assertEquals(4.6, myLine.getPoint2().getX(), 0);
        assertEquals(10.7, myLine.getPoint2().getY(), 0);

        myLine = new Line(1, 3.33, 4.444, 5.5555);
        assertEquals(1, myLine.getPoint1().getX(), 0);
        assertEquals(3.33, myLine.getPoint1().getY(), 0);
        assertEquals(4.444, myLine.getPoint2().getX(), 0);
        assertEquals(5.5555, myLine.getPoint2().getY(), 0);
    }

    @Test
    public void testInvalidConstructor() throws ShapeException {
        Point p1 = new Point(1,2);
        Point p2 = new Point(4, 10);

        try {
            new Line(p1, null);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            new Line(null, p2);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }
    }

    @Test
    public void getPoint1() {
    }

    @Test
    public void getPoint2() {
    }

    @Test
    public void testMove() throws ShapeException {
        Line myLine = new Line(1, 2, 4, 10);

        myLine.move(3, 4);
        assertEquals(4, myLine.getPoint1().getX(), 0);
        assertEquals(6, myLine.getPoint1().getY(), 0);
        assertEquals(7, myLine.getPoint2().getX(), 0);
        assertEquals(14, myLine.getPoint2().getY(), 0);

        myLine.move(.4321, .7654);
        assertEquals(4.4321, myLine.getPoint1().getX(), 0);
        assertEquals(6.7654, myLine.getPoint1().getY(), 0);
        assertEquals(7.4321, myLine.getPoint2().getX(), 0);
        assertEquals(14.7654, myLine.getPoint2().getY(), 0);

        myLine.move(-0.4321, -0.7654);
        assertEquals(4, myLine.getPoint1().getX(), 0);
        assertEquals(6, myLine.getPoint1().getY(), 0);
        assertEquals(7, myLine.getPoint2().getX(), 0);
        assertEquals(14, myLine.getPoint2().getY(), 0);
    }

    @Test
    public void testScale() throws ShapeException {
        Line line=new Line(2,0,3,0);
        line.scale(2);
        assertEquals(4,line.getPoint1().getX(),0);
        assertEquals(0,line.getPoint1().getY(),0);
        assertEquals(6,line.getPoint2().getX(),0);
        assertEquals(0,line.getPoint2().getY(),0);
    }

    @Test
    public void testComputeLength() throws ShapeException {
        Line myLine = new Line(1, 2, 4, 10);
        assertEquals(8.544, myLine.computeLength(), 0.001);

        myLine = new Line(1, 2, 1, 3);
        assertEquals(Math.sqrt(1), myLine.computeLength(), 0.001);

        myLine = new Line(3, -2, -4, 10);
        assertEquals(13.892, myLine.computeLength(), 0.001);
    }

    @Test
    public void testComputeSlope() throws ShapeException {
        Line myLine = new Line(2, 2, 4, 10);
        assertEquals(4.0, myLine.computeSlope(), 0.1);

        myLine = new Line(2, 2, 10, 4);
        assertEquals(0.25, myLine.computeSlope(), 0.1);

        myLine = new Line(2, 2, 4, 2);
        assertEquals(0, myLine.computeSlope(), 0.1);

        myLine = new Line(2, 2, 2, 4);
        assertEquals(Double.POSITIVE_INFINITY, myLine.computeSlope(), 0.1);

        myLine = new Line(2, 4, 2, 2);
        assertEquals(Double.NEGATIVE_INFINITY, myLine.computeSlope(), 0.1);
    }

    @Test
    public void computeArea() throws ShapeException {
        Line myLine= new Line(2,0,5,9);
        assertEquals(0,myLine.computeArea(),0);
    }

    @Test
    public void testSaveOutputStream(){
        try{
            Line myLine = new Line(10, 10,20,20);
            myLine.saveOutputStream("line.txt", myLine);
            myLine.saveOutputStream("myLine.txt", myLine);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testLoadInputStream(){
        try {
            File file = new File("C:\\Users\\princy\\Desktop\\Cs5700\\HW2_ShapeLibrary\\line.txt");
            Shape.loadInputStream(new FileInputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void toString() {
//    }

    @Test
    public void testGetShapes() {
        try{
            Line myLine = new Line(15,20,95,90);
            assertEquals(null, myLine.getShapes());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testRender() {
        try {
            Line myLine = new Line(15,20,95,90);
            BufferedImage bImg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = bImg.createGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, 100, 100);
            graphics.setColor(Color.BLACK);

            // Stimulus
            myLine.render(0, 0,graphics);
            // Write to a file so the results can be compared manually
            assertTrue(ImageIO.write(bImg, "png", new File("line.png")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}