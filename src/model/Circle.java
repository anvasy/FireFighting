package model;

import java.awt.*;

public class Circle {

    private int radius;
    private int x;
    private int y;

    public Circle(int r, int x, int y) {
        radius = r;
        this.x = x;
        this.y = y;
    }

    public boolean intersects(Rectangle rect) {
        int distX = Math.abs(x - rect.x);
        int distY = Math.abs(y - rect.y);

        if (distX > (rect.width/2 + radius))
            return false;
        if (distY > (rect.height/2 + radius))
            return false;

        if (distX <= (rect.width/2)) {
            return true;
        }
        if (distY <= (rect.height/2)) {
            return true;
        }

        int corner = (distX- rect.width/2)^2 +
                (distY - rect.height/2)^2;

        return (corner <= (radius^2));
    }
}