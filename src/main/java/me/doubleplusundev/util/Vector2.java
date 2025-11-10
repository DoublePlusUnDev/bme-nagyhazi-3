package me.doubleplusundev.util;

public class Vector2 {
    private double x;
    private double y;

    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 other){
        return new Vector2(x + other.x, y + other.y);
    }

    public Vector2 subtract(Vector2 other){
        return new Vector2(x - other.x, y - other.y);
    }

    public Vector2 scale(double scalar){
        return new Vector2(x * scalar, y * scalar);
    }

    public double length(){
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
