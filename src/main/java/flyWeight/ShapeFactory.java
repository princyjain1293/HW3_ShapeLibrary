package flyWeight;

import java.io.File;
import java.util.HashMap;

public class ShapeFactory {
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
}
