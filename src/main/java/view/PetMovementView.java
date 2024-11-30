package view;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PetMovementView extends JPanel {
    private final JLabel petLabel;
    private final Random random;
    private int petX, petY;

    private ImageIcon sleepGif;
    private ImageIcon idleGif;
    private ImageIcon runGif;
    private final String viewName = "Pet Movement";

    public String getViewName() {
        return viewName;
    }

    public PetMovementView(String petType) {
        // Load the GIFs based on the chosen pet type
        loadPetGifs(petType);

        // Set up panel
        this.setPreferredSize(new Dimension(400, 300));
        this.setBackground(Color.WHITE);
        this.setLayout(null);

        // Create the pet label
        petLabel = new JLabel(idleGif);
        petLabel.setBounds(150, 100, 100, 100);
        this.add(petLabel);

        // Initialize position variables
        random = new Random();
        petX = 150;
        petY = 100;

        // Start animation
        startAnimation();
    }

    private void startAnimation() {
        Timer timer = new Timer(500, e -> updatePetMovement());
        timer.start();
    }

    private void updatePetMovement() {
        int action = random.nextInt(3);

        switch (action) {
            case 0:
                petSleep();
                break;
            case 1:
                petIdle();
                break;
            case 2:
                petRun();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }

        // Update the pet's position on the panel
        petLabel.setLocation(petX, petY);
        repaint();
    }

    private void petSleep() {
        petLabel.setIcon(sleepGif);
    }

    private void petIdle() {
        petLabel.setIcon(idleGif);
    }

    private void petRun() {
        petLabel.setIcon(runGif);
        petX += random.nextInt(21) - 10;
        petY += random.nextInt(21) - 10;
        keepPetInBounds();
    }

    private void keepPetInBounds() {
        if (petX < 0) petX = 0;
        if (petY < 0) petY = 0;
        if (petX > getWidth() - petLabel.getWidth()) petX = getWidth() - petLabel.getWidth();
        if (petY > getHeight() - petLabel.getHeight()) petY = getHeight() - petLabel.getHeight();
    }

    private void loadPetGifs(String petType) {
        switch (petType) {
            case "Dog":
                sleepGif = new ImageIcon("path/to/dog_sit_12fps.gif");
                idleGif = new ImageIcon("path/to/dog_idle_12fps.gif");
                runGif = new ImageIcon("path/to/dog_run_16fps.gif");
                break;

            case "Cat":
                sleepGif = new ImageIcon("path/to/cat_sleep.gif");
                idleGif = new ImageIcon("path/to/cat_idle.gif");
                runGif = new ImageIcon("path/to/cat_run.gif");
                break;

            case "Fox":
                sleepGif = new ImageIcon("path/to/fox_sleep.gif");
                idleGif = new ImageIcon("path/to/fox_idle.gif");
                runGif = new ImageIcon("path/to/fox_run.gif");
                break;

            case "Rabbit":
                sleepGif = new ImageIcon("path/to/rabbit_sleep.gif");
                idleGif = new ImageIcon("path/to/rabbit_idle.gif");
                runGif = new ImageIcon("path/to/rabbit_run.gif");
                break;

            default:
                throw new IllegalArgumentException("Invalid pet type: " + petType);
        }
    }

}
