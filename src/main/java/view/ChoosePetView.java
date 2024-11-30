package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.choose_pet.ChoosePetController;
import interface_adapter.choose_pet.ChoosePetState;
import interface_adapter.choose_pet.ChoosePetViewModel;

/**
 * The View for the Choose Pet use case.
 * This class represents the UI where users can select a pet.
 */
public class ChoosePetView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Choose Your Pet";
    private final ChoosePetViewModel choosePetViewModel;
    private ChoosePetController choosePetController;

    private final JComboBox<String> petDropdown;
    private final JLabel messageLabel;
    private final JButton confirmButton;
    private final JButton switchToWeatherButton;

    public ChoosePetView(ChoosePetViewModel choosePetViewModel) {

        this.choosePetViewModel = choosePetViewModel;
        this.choosePetViewModel.addPropertyChangeListener(this);

        final JLabel label = new JLabel("Select Your Pet:");
        add(label);

        // Dropdown for pets
        final String[] petOptions = new String[] {"Dog", "Cat", "Fox", "Rabbit"};
        petDropdown = new JComboBox<>(petOptions);
        petDropdown.setSelectedItem(null);
        add(petDropdown);

        // Confirmation button
        confirmButton = new JButton("Confirm Selection");
        add(confirmButton);

        // Button to switch to current weather view
        switchToWeatherButton = new JButton("Switch to Current Weather View");
        add(switchToWeatherButton);

        switchToWeatherButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        choosePetController.switchToCurrView();
                    }
                }
        );

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
        final ChoosePetState state = (ChoosePetState) evt.getNewValue();
        if (!"".equals(state.getSelectedPet())) {
            displaySuccessMessage(state.getSelectedPet());
        }
        else {
            displayErrorMessage("Select Your Pet");
        }
    }

    public String getViewName() {
        return this.viewName;
    }

    public void setChoosePetController(ChoosePetController choosePetController) {
        this.choosePetController = choosePetController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // this function will never be run.
    }
}
