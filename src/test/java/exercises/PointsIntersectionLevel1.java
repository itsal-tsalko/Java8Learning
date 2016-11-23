package exercises;

import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class PointsIntersectionLevel1 {

    @Test(expected = IllegalStateException.class)
    public void testPointsIntersectionUsingToMap() {

        Point p1 = new Point(2, 0);
        Point p2 = new Point(3, 0);
        Point p3 = new Point(2, 0);
        List<Point> points = new ArrayList<Point>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.stream().collect(Collectors.toMap(Point::getLocation, Point::getLocation));
    }

    @Test(expected = IllegalStateException.class)
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
                        throw new IllegalComponentStateException();
                    }
                });
    }

    @Test(expected = IllegalStateException.class)
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
                .reduce(this::reduce);
    }

    private int reduce(int a, int b) {
        if (a == b) {
            throw new IllegalComponentStateException();
        } else {
            return Math.max(a, b);
        }
    }

}
