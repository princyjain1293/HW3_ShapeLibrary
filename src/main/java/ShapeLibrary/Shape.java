package ShapeLibrary;

import java.awt.*;
import java.io.*;
import java.util.List;

public abstract class Shape {
    public abstract void move(double deltaX, double deltaY) throws ShapeException;
    public abstract double computeArea() throws ShapeException;
    public abstract List<Shape> getShapes();
    public abstract void render(int xOffset, int yOffset, Graphics graphics) throws ShapeException;

    public void saveOutputStream(String fileName, Shape shape)
    {
        try{
            File file = new File(fileName);
            if(!file.exists())
            {
                file.createNewFile();
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                System.out.println("Saving Shape in progress");
                bufferedWriter.write(shape.toString());
                bufferedWriter.close();
            }

        }catch(Exception e){
            System.out.println("Exception "+ e);
        }
    }

    public static Shape loadInputStream(FileInputStream fileInputStream)
    {
        try {
            System.out.println("Opening");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String[] separatedText = null;
            String text = null;
            String fullString = "";

            while ((text = bufferedReader.readLine())!=null) {
                fullString = fullString.concat(text).concat("\n");
            }

            System.out.println("String received: "+fullString);

            separatedText = fullString.split(",");
            String first = separatedText[0];

            if (first.equals("Circle")) {
                //System.out.println("if condition");
                System.out.println(separatedText[1]);
                return new Circle(Double.parseDouble(separatedText[1]), Double.parseDouble(separatedText[2]), Double.parseDouble(separatedText[3]));
            }
            if (first.equals("Line")) {
                System.out.println(separatedText[0]);
                return new Line(Double.parseDouble(separatedText[1]), Double.parseDouble(separatedText[2]), Double.parseDouble(separatedText[3]), Double.parseDouble(separatedText[4]));
            }
            if (first.equals("Point")) {
                System.out.println(separatedText[0]);
                return new Point(Double.parseDouble(separatedText[1]), Double.parseDouble(separatedText[2]));
            }
            if (first.equals("Rectangle")) {
                System.out.println(separatedText[0]);
                return new Rectangle(Double.parseDouble(separatedText[1]), Double.parseDouble(separatedText[2]), Double.parseDouble(separatedText[3]), Double.parseDouble(separatedText[4]), Double.parseDouble(separatedText[5]), Double.parseDouble(separatedText[6]), Double.parseDouble(separatedText[7]), Double.parseDouble(separatedText[8]));
            }
            if (first.equals("Triangle")) {
                System.out.println(separatedText[0]);
                return new Triangle(Double.parseDouble(separatedText[1]), Double.parseDouble(separatedText[2]), Double.parseDouble(separatedText[3]), Double.parseDouble(separatedText[4]), Double.parseDouble(separatedText[5]), Double.parseDouble(separatedText[6]));
            }
            if (first.equals("Compositeshape")) {
                return new CompositeImage(separatedText);
            }
            if(first.equals("EmbeddedPicture")){
                return new EmbeddedPicture(Double.parseDouble(separatedText[1]), Double.parseDouble(separatedText[2]), Double.parseDouble(separatedText[3]), Double.parseDouble(separatedText[4]), separatedText[5]);
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception "+ e);
        }
        return null;
    }

}
