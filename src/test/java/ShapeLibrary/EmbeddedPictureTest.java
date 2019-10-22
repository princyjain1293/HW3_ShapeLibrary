package ShapeLibrary;

import flyWeight.ShapeFactory;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.*;

public class EmbeddedPictureTest {

    @Test
    public void testGetPath() throws ShapeException {
        EmbeddedPicture myPicture = new EmbeddedPicture(10, 10, 0, 0, "C:\\Users\\princy\\Desktop\\Cs5700\\HW2_ShapeLibrary\\LandScape.jpg");
        assertEquals("C:\\Users\\princy\\Desktop\\Cs5700\\HW2_ShapeLibrary\\LandScape.jpg", myPicture.getPath());
    }

    @Test
    public void testComputeArea() throws ShapeException {
        EmbeddedPicture myPicture = new EmbeddedPicture(10, 10, 0, 0, "C:\\Users\\princy\\Desktop\\Cs5700\\HW2_ShapeLibrary\\LandScape.jpg");
        assertEquals(100,myPicture.computeArea(),0);
    }

    @Test
    public void move() {
    }

    @Test
    public void testGetShapes() {
        try{
            EmbeddedPicture myEmbeddedPicture = new EmbeddedPicture(600, 600, 10, 10, "LandScape.jpg");
            assertEquals(null, myEmbeddedPicture.getShapes());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testObjectHandler(){
        try
        {
            ShapeFactory shapeFactory= new ShapeFactory();
            shapeFactory.objectHandler("tomHanks.jpg");
            ShapeFactory.objectHandler("forrest-gump.jpg");
            shapeFactory.objectHandler("tomHanks.jpg");
            ShapeFactory.objectHandler("forrest-gump.jpg");
            shapeFactory.objectHandler("tomHanks.jpg");
            ShapeFactory.objectHandler("forrest-gump.jpg");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testRender() {
        try {
            double length = 600;
            double width = 480;
            String source = "LandScape.jpg";
            EmbeddedPicture myEmbeddedPicture = new EmbeddedPicture(length, width, 10, 10, source);
            File inputFile = ShapeFactory.objectHandler(source);
            BufferedImage inputImage = null;
            inputImage = ImageIO.read(inputFile);
            // creates output image
            BufferedImage outputImage = new BufferedImage(500, 600, inputImage.getType());

            // scales the input image to the output image
            Graphics2D graphics = outputImage.createGraphics();
            graphics.setColor(Color.BLACK);
            graphics.fillRect(0, 0, 500, 600);
            graphics.setColor(Color.RED);

            myEmbeddedPicture.render( 0, 0,graphics);
            assertTrue(ImageIO.write(outputImage, "jpg", new File("LandScape.jpg")));
            assertEquals("LandScape.jpg",myEmbeddedPicture.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}