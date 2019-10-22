package flyWeight;

import ShapeLibrary.Shape;

import java.io.File;
import java.util.HashMap;

public class ShapeFactory {
//    private static HashMap<String, File> objectCollection= new HashMap<>();
//    public static File objectHandler(String fileName){
//        File source= objectCollection.get(fileName);
//        if(source==null){
//            source= new File(fileName);
//            objectCollection.put(fileName,source);
//            System.out.println("Added "+fileName);
//        }
//        return source;
//    }
    private  static HashMap<String, Shape> objectHandler= new HashMap<>();
    public static Shape getShape(String type){
        Shape s=null;
        if(objectHandler.containsKey(type))
            s=objectHandler.get(type);
        else
    }
}
