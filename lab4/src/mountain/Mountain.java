package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {

    private Point a;
    private Point b;
    private Point c;

    private double dev;


    public Mountain(Point a, Point b, Point c, double dev) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.dev = dev;
    }

    private void fractalTriangle(TurtleGraphics turtle, int order, Point a, Point b, Point c, double dev)
    {
        if (order == 0) {
            turtle.moveTo(a.getX(), a.getY());

            turtle.forwardTo(b.getX(), b.getY());
            turtle.forwardTo(c.getX(), c.getY());
            turtle.forwardTo(a.getX(), a.getY());

        } else {
            // move new point calculation + rand to new function maybe
            Point ab = midpoint(a, b, dev);
            Point ac = midpoint(a, c, dev);
            Point bc = midpoint(b, c, dev);

            // Points for the new triangles
            Point[][] triangles = new Point[][] { { a, ab, ac }, { ab, b, bc }, { ac, bc, c }, { bc, ac, ab } };

            // Draw foreach triangle
            for(Point[] p : triangles) {
                this.fractalTriangle(turtle, order - 1, p[0], p[1], p[2], dev / 2);
            }
        }
    }

    public Point midpoint(Point a, Point b, double dev) {
        return new Point(a.getX() + (b.getX() - a.getX()) / 2, (int) ((a.getY() + (b.getY() - a.getY()) / 2) + RandomUtilities.randFunc(dev)));
    }

    @Override
    public String getTitle() {
        return "Mountain";
    }

    @Override
    public void draw(TurtleGraphics g) {
        fractalTriangle(g, this.order, this.a, this.b, this.c, this.dev);
    }


}
