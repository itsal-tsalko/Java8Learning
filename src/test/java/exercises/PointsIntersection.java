package exercises;

import static exercises.PointsIntersection.Point.p;
import static exercises.PointsIntersection.Segment.s;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class PointsIntersection {

    public static void main(String[] args) {
        Segment segment1 = s(p(0, 0), p(1, 1));
        Segment segment2 = s(p(2, 1), p(3, 0));

        System.out.println(isSegmentsHaveIntersection(segment1, segment2));
    }

    public static boolean isSegmentsHaveIntersection(Segment s1, Segment s2) {
        s1.normalize();
        s2.normalize();
        double k1 = (s1.p2.y - s1.p1.y) / (s1.p2.x - s1.p1.x);
        double k2 = (s2.p2.y - s2.p1.y) / (s2.p2.x - s2.p1.x);
        if (k1 == k2) return false;
        double b1 = s1.p1.y - k1 * s1.p1.x;
        double b2 = s2.p1.y - k2 * s2.p1.x;
        double x = (b2 - b1) / (k1 - k2);
        double y = k1 * x + b1;
        if (s1.p1.x <= s2.p2.x && s2.p2.x <= s2.p2.x || s1.p1.x <= s2.p1.x && s2.p1.x <= s2.p2.x) {
            return true;
        } else {
            return false;
        }
    }

    static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        static Point p(int x, int y) {
            return new Point(x, y);
        }
    }

    static class Segment {

        Point p1, p2;

        public Segment(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        void normalize() {
            if (p1.x >= p2.x) {
                Point p = p1;
                p1 = p2;
                p2 = p;
            }
        }

        static Segment s(Point p1, Point p2) {
            return new Segment(p1, p2);
        }
    }
}
