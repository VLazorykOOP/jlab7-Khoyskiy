import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Truck implements Runnable{
    private static final int MAX_SPEED = 10; // максимальна швидкість руху
    private int speed; // поточна швидкість руху
    private Color color;
    private Point startPosition;
    private Point endPosition;
    private int width,height;

    public Truck(Point startPosition, Point endPosition, int width,int height) {
        this.speed = new Random().nextInt(MAX_SPEED) + 1;
        this.color = Color.blue; // встановлюємо колір вантажівок
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.width = width;
        this.height = height;
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
        if(startPosition.x >= width/2 || startPosition.y >= height/2){
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
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(startPosition.x, startPosition.y, 50, 30);
    }
}