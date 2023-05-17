import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Car  implements Runnable  {
    private static final int MAX_SPEED = 10;
    private Point startPosition;
    private Point endPosition;
    private int speed;
    private Color color;
    private int width,height;

    public Car(Point startPosition, Point endPosition,int width, int height) {
        this.width = width;
        this.height = height;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.speed = new Random().nextInt(MAX_SPEED) + 1;
        this.color = Color.RED;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(startPosition.x, startPosition.y, 50, 30);
    }
    public void move() {
        double distance = Math.sqrt(Math.pow(endPosition.x - startPosition.x, 2) + Math.pow(endPosition.y - startPosition.y, 2));
        double ratio = speed / distance;
        int deltaX = (int) Math.round((endPosition.x - startPosition.x) * ratio);
        int deltaY = (int) Math.round((endPosition.y - startPosition.y) * ratio);
        startPosition.translate(deltaX, deltaY);
    }

    @Override
    public void run() {
        if(startPosition.x <= width/2 || startPosition.y <= height/2){
        while (!startPosition.equals(endPosition)) {
            move();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    }
}