import java.awt.*;

abstract class Shape {
    private double x,y;
    private Color color;

    public Shape(double x, double y, Color color) {
        this.x = x; this.y = y;
        this.color = color;
    }

    public double getX() { return x; }

    public double getY() { return y; }

    public Color getColor() { return color; }


    abstract public void paint(Graphics g);

}
