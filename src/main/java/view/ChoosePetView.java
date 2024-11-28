package interface_adapter.choose_pet;

import use_case.choose_pet.ChoosePetOutputData;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;

/**
 * The View for the Choose Pet use case.
 * This class represents the UI where users can select a pet.
 */
public class ChoosePetView extends JFrame implements PropertyChangeListener {

    private final JComboBox<String> petDropdown;
    private final JLabel messageLabel;
    private final JButton confirmButton;
    private final JButton switchToWeatherButton;

    public ChoosePetView(List<String> availablePets) {
        super("Choose Your Pet");

        // Setup UI components
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JLabel label = new JLabel("Select Your Pet:");
        add(label);

        // Dropdown for pets
        petDropdown = new JComboBox<>(availablePets.toArray(new String[0]));
        add(petDropdown);

        // Confirmation button
        confirmButton = new JButton("Confirm Selection");
        add(confirmButton);

        // Button to switch to current weather view
        switchToWeatherButton = new JButton("Switch to Current Weather View");
        add(switchToWeatherButton);

        // Message label for displaying success or error
        messageLabel = new JLabel(" ");
        add(messageLabel);
    }

    /**
     * Adds an action listener to the confirm button.
     * @param actionListener The action listener for handling confirm button clicks.
     */
    public void addConfirmButtonListener(ActionListener actionListener) {
        confirmButton.addActionListener(actionListener);
    }

    /**
     * Adds an action listener to the switch to weather button.
     * @param actionListener The action listener for handling switch button clicks.
     */
    public void addSwitchToWeatherButtonListener(ActionListener actionListener) {
        switchToWeatherButton.addActionListener(actionListener);
    }

    /**
     * Gets the selected pet from the dropdown.
     * @return The selected pet.
     */
    public String getSelectedPet() {
        return (String) petDropdown.getSelectedItem();
    }

    /**
     * Displays an error message in the view.
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        messageLabel.setForeground(Color.RED);
        messageLabel.setText(message);
    }

    /**
     * Displays a success message in the view.
     * @param message The success message to display.
     */
    public void displaySuccessMessage(String message) {
        messageLabel.setForeground(Color.GREEN);
        messageLabel.setText(message);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            ChoosePetOutputData data = (ChoosePetOutputData) evt.getNewValue();
            if (data.getSuccessMessage() != null) {
                displaySuccessMessage(data.getSuccessMessage());
            } else {
                displayErrorMessage(data.getSelectedPet());
            }
        }
    }
}
