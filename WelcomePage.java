import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class WelcomePage extends JFrame {
    // Constructor for the WelcomePage class
    public WelcomePage() {
        // Set the title and dimensions of the frame
        setTitle("Welcome to Wunsay Galoon Ticketing System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBackground(new Color(173, 216, 230)); // Light Blue

        // Load and display an image
        ImageIcon logoIcon = new ImageIcon("themepark.jpeg"); // Replace with the actual image path
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomePanel.add(logoLabel);

        JLabel titleLabel = new JLabel("Welcome to Wunsay Galoon Ticketing System");
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(Color.RED); // Change the text color to red
        welcomePanel.add(titleLabel);

        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ActionListener for the "Start" button
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create an instance of your main application and make it visible
                WunsayTicketingSystem app = new WunsayTicketingSystem();
                app.setVisible(true);
                // Close the welcome page
                dispose();
            }
        });

        // Add spacing between elements
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        welcomePanel.add(startButton);

        add(welcomePanel);
    }

    // Main method to run the WelcomePage
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                WelcomePage welcomePage = new WelcomePage();
                welcomePage.setVisible(true);
            }
        });
    }
}