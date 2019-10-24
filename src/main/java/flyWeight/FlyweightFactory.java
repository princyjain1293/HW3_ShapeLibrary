package flyWeight;

import ShapeLibrary.*;

import java.io.File;
import java.util.HashMap;

public class FlyweightFactory {
    private static HashMap<String, File> objectCollection= new HashMap<>();
    public static File objectHandler(String fileName){
        File source= objectCollection.get(fileName);
        if(source==null){
            source= new File(fileName);
            objectCollection.put(fileName,source);
            System.out.println("Added "+fileName);
        }
        return source;
    }
    private  static HashMap<String, Shape> objectHandler= new HashMap<>();
    public static Shape getShape(String type) throws ShapeException {
        Shape s=null;
        if(objectHandler.containsKey(type))
            s=objectHandler.get(type);
        else{
            switch (type) {
                case "Circle":
                    s = new Circle(10, 10, 5);
                    break;
                case "Rectangle":
                    s = new Rectangle(0, 0, 0, 4, 3, 4, 3, 0);
                    break;
                case "Triangle":
                    s = new Triangle(0, 0, 0, 5, 6, 0);
                    break;
                case "Line":
                    s = new Line(10, 20, 12, 45);
                    break;
                case "Point":
                    s = new Point(0, 0);
                case "EmbeddedPicture":
                    s = new EmbeddedPicture(10, 20, 3, 4, "LandScape.jpg");
                    break;
                case "CompositeImage":
                    s = new CompositeImage();
            }
            objectHandler.put(type,s);
        }
        return s;
    }
}
