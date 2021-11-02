package mountain;

public class Side {
    private Point a;
    private Point b;

    public Side(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object a) {
        Side p = (Side) a;

        // Check if two points are the same
        return this.a.equals(p.a) && this.b.equals(p.b) || this.a.equals(p.b) && this.b.equals(p.a);
    }

    @Override
    public int hashCode() {
        return a.hashCode() + b.hashCode();
    }


}
