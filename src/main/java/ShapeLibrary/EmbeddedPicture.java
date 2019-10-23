package ShapeLibrary;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class EmbeddedPicture extends Shape {
    private double length;
    private double breadth;
    private Point centre_Image;
    private String path;

    public EmbeddedPicture(double length,double breadth, double x, double y, String path) throws ShapeException {
        this.length=length;
        this.breadth=breadth;
        centre_Image=new Point(x,y);
        this.path=path;
    }

    public String getPath(){return path;}
    public Point getCentre_Image(){return centre_Image;}

    public double computeArea(){
        return length*breadth;
    }

    public void move(double deltaX, double deltaY) throws ShapeException {
        centre_Image.move(deltaX,deltaY);
    }

    public void scale(double scaleFactor){
        centre_Image.scale(scaleFactor);
    }

    public List<Shape> getShapes(){
        return null;
    }

    public void render(int xOffset, int yOffset, Graphics graphics) throws ShapeException{
        move(xOffset,yOffset);
        // Compute the left side of the bounding box
        int x = (int) Math.round(centre_Image.getX() - length);

        // Compute the top side of the bounding box
        int y = (int) Math.round(centre_Image.getY() - breadth);

        File sourceFile = new File(path);
        BufferedImage sourceImage = null;
        try{
            sourceImage = ImageIO.read(sourceFile);
        }catch (Exception e){
            e.printStackTrace();
        }
        graphics.drawImage(sourceImage, 0, 0, (int)breadth, (int)length, null);
        // Shift the shape back to its original location
        move(xOffset, yOffset);
    }

    public String toText() {
        return "EmbeddedPicture,"+centre_Image.getX()+","+centre_Image.getY()+","+String.valueOf(length)+","+String.valueOf(breadth)+","+path+",";
    }

}
