package exercises;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class PointsIntersectionLevel1 {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Found duplicate point with 2 coordinate");
    }

    @Test(expected = IllegalStateException.class)
    public void testPointsIntersectionUsingToMap() {
        Point p1 = new Point(2, 0);
        Point p2 = new Point(3, 0);
        Point p3 = new Point(2, 0);
        List<Point> points = Arrays.asList(p1, p2, p3);
        points.stream().collect(Collectors.toMap(Point::getLocation, Point::getLocation));
    }

    @Test
    public void testPointsIntersectionUsingGroupingBy() {
        Point p1 = new Point(2, 0);
        Point p2 = new Point(3, 0);
        Point p3 = new Point(2, 0);
        List<Point> points = new ArrayList<Point>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.stream().collect(Collectors.groupingBy(Point::getLocation, Collectors.counting()))
                .forEach((k, v) -> {
                    if (v > 1) {
                        throw new IllegalComponentStateException(String.format("Found duplicate point with %s coordinate", k.x));
                    }
                });
    }

    @Test
    public void testPointsIntersectionUsingReduce() {
        Point p1 = new Point(2, 0);
        Point p2 = new Point(3, 0);
        Point p3 = new Point(2, 0);
        List<Point> points = new ArrayList<Point>();
        points.add(p1);
        points.add(p2);
        points.add(p3);

        points.stream()
                .mapToInt(p -> (int) p.getX())
                .sorted()
                .reduce(this::intersectPoints);
    }

    private int intersectPoints(int a, int b) {
        if (a == b) {
            throw new IllegalStateException(String.format("Found duplicate point with %s coordinate", a));
        } else {
            return Math.max(a, b);
        }
    }

}
