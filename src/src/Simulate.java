import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Simulate extends JPanel implements ActionListener {
    private ArrayList<Car> cars;
    private ArrayList<Truck> trucks;
    private Timer timer;
    private int width, height;
    private Random rand;

    public Simulate(int width, int height) {
        this.width = width;
        this.height = height;
        this.rand = new Random();
        this.cars = new ArrayList<>();
        this.trucks = new ArrayList<>();

        // create cars
        for (int i = 0; i < 2; i++) {
            Car car = new Car(getRandomPosition2(width, height), getRandomPosition(width/2, height/2),width,height);
            cars.add(car);
            new Thread(car).start();
        }
        for (int i = 0; i < 2; i++) {
            Truck truck = new Truck(getRandomPosition2(width, height), getRandomPosition2(width/2, height/2),width,height);
            trucks.add(truck);
            new Thread(truck).start();
        }

        this.timer = new Timer(10, this);
        this.timer.start();
    }

    int getwidth(){
        return width;
    }
    private Point getRandomPosition(int X, int Y) {
        int x = width/2 + rand.nextInt(X);
        int y = height/2 + rand.nextInt(Y);
        return new Point(x, y);
    }

    private Point getRandomPosition2(int X, int Y) {
        int x = rand.nextInt(X);
        int y = rand.nextInt(Y);
        return new Point(x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Car car : cars) {
            car.draw(g);
        for (Truck truck : trucks) {
            truck.draw(g);
        }
    }
    }
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1000); // встановлюємо більший розмір вікна
        Simulate panel = new Simulate(1200, 1000);
        frame.add(panel);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }
}
