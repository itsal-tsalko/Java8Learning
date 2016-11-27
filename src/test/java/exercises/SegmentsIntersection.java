package exercises;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static exercises.SegmentsIntersection.Segment.s;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class SegmentsIntersection {

    public static void main(String[] args) {
        List<Segment> segments = new ArrayList<>();
        Segment segment1 = s(0, 1);
        Segment segment2 = s(2, 4);
        Segment segment3 = s(5, 7);
        segments.add(segment1);
        segments.add(segment2);
        segments.add(segment3);

        isSegmentsHaveIntersection(segments);
    }

    public static void isSegmentsHaveIntersection(List<Segment> segments) {
        segments.stream().sorted(Comparator.comparingInt(s -> s.p1))
                .reduce((s1, s2) -> {
                    if (s2.p1 <= s1.p2) {
                        throw new IllegalComponentStateException();
                    } else {
                        return s2;
                    }
                });
    }

    static class Segment {

        int p1, p2;

        public Segment(int p1, int p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        static Segment s(int p1, int p2) {
            return new Segment(p1, p2);
        }
    }
}
