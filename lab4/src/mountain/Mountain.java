package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;

import java.util.HashMap;

public class Mountain extends Fractal {

    private Point a;
    private Point b;
    private Point c;

    private double dev;

    private HashMap<Side, Point> sides;


    public Mountain(Point a, Point b, Point c, double dev) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.dev = dev;
        this.sides = new HashMap<>();
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

        // Create side
        Side s = new Side(a,b);

        // Check map if point already exists
        if(sides.containsKey(s)) {
            Point p = sides.get(s);
            sides.remove(s);
            return p;
        }

        int x = a.getX() + (b.getX() - a.getX()) / 2;
        int y = (int) ((a.getY() + (b.getY() - a.getY()) / 2) + RandomUtilities.randFunc(dev));
        Point p = new Point(x, y);

        //add to map
        this.sides.put(s, p);

        return p;
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
