package ShapeLibrary;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CompositeImageTest {

//    @Test
//    public void testValidConstruction(){
//        String[] broke= new String[]{"Circle","0","0","7","Line","0","0","0","4","Rectangle","0","0","0","4","3","4","3","0","Triangle","0","0","0","4","3","0"};
//        CompositeImage compositeImage= new CompositeImage(broke);
//        for(int i=0;i<broke.length;)
//    }

    @Test
    public void testGetShapes() throws ShapeException {
        Circle shape1 = new Circle(3, 3, 7);
        Rectangle shape2 = new Rectangle(0, 0, 0, 4, 3, 4, 3, 0);
        CompositeImage compositeShape = new CompositeImage();
        compositeShape.addShape(shape1);
        compositeShape.addShape(shape2);
        ArrayList<Shape> shapes= new ArrayList<>();
        shapes.add(shape1);
        shapes.add(shape2);
        ArrayList<Shape> shapesActual= (ArrayList<Shape>) compositeShape.getShapes();
        assertEquals(shapes,shapesActual);
    }

    @Test
    public void testAddShape() throws ShapeException {
        Circle shape1 = new Circle(3, 3, 2);

        Triangle shape3 = new Triangle(1, 2, 3, 6, 4, 1);
        Rectangle shape4 = new Rectangle(0, 0, 0, 3, 4, 3, 4, 0);
        Line shape7 = new Line(2, 2, 4, 4);

        Point shape11 = new Point(1,2);
        EmbeddedPicture shape9 = new EmbeddedPicture(600, 600, 10, 10, "forrest-gump.jpg");
        CompositeImage shape5 = new CompositeImage();

        shape5.addShape(shape4);
        CompositeImage shape6 = new CompositeImage();
        shape6.addShape(shape1);
        shape6.addShape(shape3);
        CompositeImage shape8 = new CompositeImage();
        shape8.addShape(shape7);
        shape8.addShape(shape1);

        CompositeImage compositeShape = new CompositeImage();
        compositeShape.addShape(shape1);

        compositeShape.addShape(shape3);
        compositeShape.addShape(shape4);
        compositeShape.addShape(shape5);
        compositeShape.addShape(shape6);
        compositeShape.addShape(shape7);
        compositeShape.addShape(shape8);
        compositeShape.addShape(shape9);

        compositeShape.addShape(shape11);

        compositeShape.saveOutputStream("compositeShape.txt", compositeShape);
        compositeShape.saveOutputStream("myCompositeShape.txt", compositeShape);
    }

    @Test
    public void testRemoveShape() throws ShapeException {
        Circle shape1 = new Circle(3, 3, 2);
        Rectangle shape2 = new Rectangle(0, 0, 0, 4, 3, 4, 3, 0);
        CompositeImage compositeShape = new CompositeImage();
        compositeShape.addShape(shape1);
        compositeShape.addShape(shape2);
        compositeShape.removeShape(shape1);
        ArrayList<Shape>shapes = new ArrayList<>();
        shapes.add(shape2);
        ArrayList<Shape> shapesActual = (ArrayList<Shape>) compositeShape.getShapes();
        assertEquals(shapes,shapesActual);
    }

    @Test
    public void testRemoveAllShapes() throws ShapeException {
        Circle shape1 = new Circle(3, 3, 2);
        Rectangle shape2 = new Rectangle(0, 0, 0, 4, 3, 4, 3, 0);
        CompositeImage compositeShape = new CompositeImage();
        compositeShape.addShape(shape1);
        compositeShape.addShape(shape2);
        compositeShape.removeAllShapes();
    }

    @Test
    public void testComputeArea() throws ShapeException {
        Circle shape1 = new Circle(3, 3, 7);
        Rectangle shape2 = new Rectangle(0, 0, 0, 4, 3, 4, 3, 0);
        CompositeImage compositeShape = new CompositeImage();
        compositeShape.addShape(shape1);
        compositeShape.addShape(shape2);
        assertEquals(166,compositeShape.computeArea(),1.0);
    }

    @Test
    public void testMove() throws ShapeException {
        Circle shape1 = new Circle(3, 3, 2);
        Rectangle shape2 = new Rectangle(0, 0, 0, 4, 3, 4, 3, 0);
        CompositeImage compositeShape = new CompositeImage();
        compositeShape.addShape(shape1);
        compositeShape.addShape(shape2);
        compositeShape.move(5,5);
    }

    @Test
    public void makeFile() {
    }

    @Test
    public void testLoadInputStream(){
        try {
            File file = new File("C:\\Users\\princy\\Desktop\\Cs5700\\HW2_ShapeLibrary\\compositeShape.txt");
            Shape shape = Shape.loadInputStream(new FileInputStream(file));
            System.out.println("Size is : " + shape.getShapes().size());
            System.out.println("Area is : "+shape.computeArea());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRender() {
        try {
            //Setup
            CompositeImage composite = new CompositeImage();
            // composite.addChildShape(new EmbeddedPicture(44, 55, 80, 50, "graphics/dog.jpg"));
            composite.addShape(new Rectangle(20, 20, 20, 40, 60, 40, 60, 20));

            composite.addShape(new Triangle(10, 20, 30, 60, 40, 10));
            composite.addShape(new EmbeddedPicture(10, 10, 100, 100, "LandScape.jpg"));
            composite.addShape(new Line(10,20,10,15));
            CompositeImage compositeMix= new CompositeImage();
            compositeMix.addShape(new Rectangle(20, 20, 20, 40, 60, 40, 60, 20));
            compositeMix.addShape(composite);
            BufferedImage bImg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = bImg.createGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, 100, 100);
            graphics.setColor(Color.BLACK);

            // Stimulus
            composite.render( -100, 100,graphics);
            compositeMix.render(0,0,graphics);

            // Write to a file so the results can be compared manually
            assertTrue(ImageIO.write(bImg, "png", new File("composite.png")));
            assertTrue(ImageIO.write(bImg, "png", new File("compositeMix.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testToText(){
        String[] broke= new String[]{"Circle","0","0","7","Line","0","0","0","4","Rectangle","0","0","0","4","3","4","3","0","Triangle","0","0","0","4","3","0"};
        CompositeImage compositeImage= new CompositeImage(broke);
        compositeImage.toText();
    }
}