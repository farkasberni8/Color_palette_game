import java.awt.*;

public class ColorShape extends FillableShape{

    private final double length;
    private Color newColor;

    public ColorShape(double x, double y, Color color, double length){
        super(x, y, color);
        this.length = length;
        this.newColor = color;
    }

    public double getLength(){
        return length;
    }

    public void paint(Graphics g){
        g.setColor(this.newColor);
        g.drawRect((int)getX(),(int)getY(),(int)length, (int)length);
        g.fillRect((int)getX(),(int)getY(),(int)length, (int)length);
    }

    public void setColor(Color c){
        this.newColor = c;
    }

    public Color getColor() { return this.newColor; }
}
