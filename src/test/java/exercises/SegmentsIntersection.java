package exercises;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static exercises.SegmentsIntersection.Segment.s;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class SegmentsIntersection {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Found segment (2, 4) intersection with (3, 7)");
    }

    @Test
    public void testSegmentsIntersection() {
        List<Segment> segments = Arrays.asList(s(0, 1), s(2, 4), s(3, 7));
        isSegmentsHaveIntersection(segments);
    }

    public static void isSegmentsHaveIntersection(List<Segment> segments) {
        segments.stream().sorted(Comparator.comparingInt(s -> s.p1))
                .reduce((s1, s2) -> {
                    if (s2.p1 <= s1.p2) {
                        throw new IllegalStateException(String.format("Found segment (%s, %s) intersection with (%s, %s)", s1.p1, s1.p2, s2.p1, s2.p2));
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
