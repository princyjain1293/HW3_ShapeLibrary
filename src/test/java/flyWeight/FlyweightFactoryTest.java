package flyWeight;

import ShapeLibrary.Circle;
import ShapeLibrary.ShapeException;
import org.junit.Test;

import static org.junit.Assert.*;

public class FlyweightFactoryTest {

    @Test
    public void testObjectHandler() {
    }

    @Test
    public void testGetShapes() throws ShapeException {
        FlyweightFactory.getShape("Circle");
        FlyweightFactory.getShape("Rectangle");
        FlyweightFactory.getShape("Triangle");
        FlyweightFactory.getShape("Line");
        FlyweightFactory.getShape("Point");
        FlyweightFactory.getShape("EmbeddedPicture");
        FlyweightFactory.getShape("CompositeImage");
    }
}