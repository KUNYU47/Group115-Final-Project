package view;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PetMovementPanel extends JPanel {
    private final JLabel petLabel;
    private final Random random;
    private int petX;
    private int petY;

    private ImageIcon sitGif;
    private ImageIcon idleGif;
    private ImageIcon runGif;
    private final String viewName = "Pet Movement";

    private final JButton sitButton = new JButton();

    public PetMovementPanel(String petType) {
        // Load the GIFs based on the chosen pet type
        loadPetGifs(petType);

        // Set up panel
        this.setLayout(null);
        this.setPreferredSize(new Dimension(400, 100));
        this.setBackground(Color.WHITE);

        // Create the pet label
        petLabel = new JLabel(idleGif);
        petLabel.setBounds(150, 100, 100, 100);
        this.add(petLabel);

        sitButton.setBounds(150, 100, 100, 100);
        sitButton.setOpaque(false);
        sitButton.setContentAreaFilled(false);
        sitButton.setBorderPainted(false);
        this.add(sitButton);

        sitButton.addActionListener(evt -> petSleep());

        // Initialize position variables
        random = new Random();
        petX = 150;
        petY = 100;

        // Start animation
        startAnimation();
    }

    private void startAnimation() {
        final Timer timer = new Timer(1000, e -> updatePetMovement());
        timer.start();
    }

    private void updatePetMovement() {
        final int action = random.nextInt(3);

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
                // this should never be met since by design we will not have number greater than 2.
                throw new IllegalStateException("Unexpected value: " + action);
        }

        // Update the pet's position on the panel
        petLabel.setLocation(petX, petY);
        repaint();
    }

    private void petSleep() {
        petLabel.setIcon(sitGif);
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
        if (petX < 0) {
            petX = 0;
        }
        if (petY < 0) {
            petY = 0;
        }
        if (petX > getWidth() - petLabel.getWidth()) {
            petX = getWidth() - petLabel.getWidth();
        }
        if (petY > getHeight() - petLabel.getHeight()) {
            petY = getHeight() - petLabel.getHeight();
        }
    }

    private void loadPetGifs(String petType) {
        switch (petType) {
            case "Dog":
                sitGif = new ImageIcon("resources/pet_packs/dogpack_gifs/dog_sit_12fps.gif");
                idleGif = new ImageIcon("resources/pet_packs/dogpack_gifs/dog_idle_12fps.gif");
                runGif = new ImageIcon("resources/pet_packs/dogpack_gifs/dog_run_16fps.gif");

                break;

            case "Cat":
                sitGif = new ImageIcon("resources/pet_packs/catset_gifs/cat01_gifs/cat01_sit_8fps.gif");
                idleGif = new ImageIcon("resources/pet_packs/catset_gifs/cat01_gifs/cat01_idle_8fps.gif");
                runGif = new ImageIcon("resources/pet_packs/catset_gifs/cat01_gifs/cat01_run_12fps.gif");
                break;

            case "Fox":
                sitGif = new ImageIcon("resources/pet_packs/foxpack_gifs/fox_sit01_12fps.gif");
                idleGif = new ImageIcon("resources/pet_packs/foxpack_gifs/fox_idle_12fps.gif");
                runGif = new ImageIcon("resources/pet_packs/foxpack_gifs/fox_run_16fps.gif");
                break;

            case "Rabbit":
                sitGif = new ImageIcon("resources/pet_packs/bunnypack_gifs/bunny_sit_8fps.gif");
                idleGif = new ImageIcon("resources/pet_packs/bunnypack_gifs/bunny_idle_8fps.gif");
                runGif = new ImageIcon("resources/pet_packs/bunnypack_gifs/bunny_run_16fps.gif");
                break;

            default:
                // this should never be run since drop down menu does not provide any other options.
//                throw new IllegalArgumentException("Invalid pet type: " + petType);
        }
    }

    public String getViewName() {
        return viewName;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pet Animation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new PetMovementPanel("Dog"));
            frame.pack();
            frame.setVisible(true);
        });
    }
}
