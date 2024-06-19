package pl.auroramc.commons.math.curve;

import static java.lang.Math.ceil;
import static java.lang.Math.pow;
import static java.util.Arrays.copyOf;

import pl.auroramc.commons.math.Point;

public final class BezierCurve {

  private BezierCurve() {}

  public static Point[] cubicCurve(
      final Point p0, final Point p1, final Point p2, final Point p3, final double step) {
    final int estimatedSize = (int) ceil(1 / step) + 1;
    final Point[] points = new Point[estimatedSize];

    int index = 0;
    for (double t = 0; t <= 1; t += step) {
      points[index++] = cubicPoint(p0, p1, p2, p3, t);
    }

    if (index < estimatedSize) {
      return copyOf(points, index);
    }

    return points;
  }

  public static Point cubicPoint(
      final Point p0, final Point p1, final Point p2, final Point p3, final double t) {
    final double tSquare = pow(t, 2);
    final double tCube = pow(t, 3);
    final double x = cubicPoint(p0.x(), p1.x(), p2.x(), p3.x(), t, tSquare, tCube);
    final double y = cubicPoint(p0.y(), p1.y(), p2.y(), p3.y(), t, tSquare, tCube);
    return new Point(x, y);
  }

  private static double cubicPoint(
      final double v0,
      final double v1,
      final double v2,
      final double v3,
      final double t,
      final double tSquared,
      final double tCubed) {
    double a = 3 * (v1 - v0);
    double b = 3 * (v2 - v1) - a;
    double c = v3 - v0 - a - b;
    return c * tCubed + b * tSquared + a * t + v0;
  }
}
