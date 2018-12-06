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

    public void setRadius(int r) {
        r = radius;
    }

    public int getRadius() {
        return radius;
    }

    public int getDiameter() {
        return radius * 2;
    }

    public double getArea() {
        return Math.PI  * Math.pow(radius, 2);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
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