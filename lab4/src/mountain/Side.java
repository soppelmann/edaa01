package mountain;

import java.util.Objects;

public class Side {
    private Point a;
    private Point b;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Side)) return false;
        Side side = (Side) o;
        return this.a.equals(side.b);
    }

    @Override
    public int hashCode() {
        return a.hashCode() + b.hashCode();
    }

    public Side(Point a, Point b) {
        this.a = a;
        this.b = b;
    }



}
