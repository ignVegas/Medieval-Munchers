package main;

import java.awt.*;
import javax.swing.*;



// Main class that contains the main method
public class Main {
    // JFrame for the main window
    private static JFrame window;

    // GamePanel for displaying the game
    private static GamePanel gamePanel;

    // Constants for the window size
    private static final int WINDOW_WIDTH = 768;
    private static final int WINDOW_HEIGHT = 576;

    // Main method
    public static void main(String[] args) {
        // Invoke the Swing GUI update on the event dispatch thread
        SwingUtilities.invokeLater(() -> {
            // Initialize the main window and display the title screen
            initializeWindow();
            showTitleScreen();
        });
    }

    // Initialize the main window
    private static void initializeWindow() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Medieval Munchers");
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    // Display the title screen
    private static void showTitleScreen() {
        // Create a panel for the title screen
        JPanel titleScreenPanel = createTitleScreen();
        // Add the title screen panel to the main window
        window.add(titleScreenPanel);
    }

    // Create the panel for the title screen
    private static JPanel createTitleScreen() {
        // Create a panel for the title screen with a vertical box layout
        JPanel titleScreenPanel = new JPanel();
        titleScreenPanel.setLayout(new BoxLayout(titleScreenPanel, BoxLayout.Y_AXIS));
        titleScreenPanel.setBackground(Color.BLACK);

        // Create a center panel within the title screen panel
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.BLACK);

        // Create a title label and set its properties
        JLabel titleLabel = new JLabel("Medieval Munchers");
        Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.WHITE);
        centerPanel.add(titleLabel);

        // Add vertical struts for spacing
        titleScreenPanel.add(Box.createVerticalStrut(70));
        titleScreenPanel.add(centerPanel);
        titleScreenPanel.add(Box.createVerticalStrut(0));

        // Create a panel for the start button and add it to the title screen panel
        JPanel startButtonPanel = createStartButtonPanel();
        titleScreenPanel.add(startButtonPanel);

        // Return the title screen panel
        return titleScreenPanel;
    }

    // Create the panel for the start button
    private static JPanel createStartButtonPanel() {
        // Create a panel for the start button with a black background
        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setBackground(Color.BLACK);

        // Create a start button and set its properties
        JButton startButton = new JButton("START GAME");
        Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
        startButton.setFont(normalFont);
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(e -> showGamePanel()); // Add action listener to start the game
        startButton.setBorderPainted(false);
        startButtonPanel.add(startButton);

        // Return the start button panel
        return startButtonPanel;
    }

    // Show the game panel and start the game
    private static void showGamePanel() {
        // Remove all components from the content pane of the main window
        window.getContentPane().removeAll();

        // Create a game panel and set its properties
        gamePanel = new GamePanel();
        gamePanel.setBackground(Color.BLACK);

        // Add the game panel to the main window
        window.add(gamePanel);

        // Set the size and location of the main window to match the game panel
        window.setSize(gamePanel.screenWidth, gamePanel.screenHeight);
        window.setLocationRelativeTo(null);

        // Revalidate and repaint the main window
        window.revalidate();
        window.repaint();

        // Initialize and start the game thread
        gamePanel.setItem();
        gamePanel.gameThread();
    }
}
