package Number_Guessing_Game_GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

class GuessNumber {
    private int guesses = 10;
    private int guessed;
    private ArrayList<Integer> arr = new ArrayList<>();
    private int ran;
    private int warning = 1;

    public GuessNumber() {
        Random rn = new Random();
        this.ran = rn.nextInt(1000);
    }

    public int getGuesses() {
        return guesses;
    }

    public int getRan() {
        return ran;
    }

    public int getWarning() {
        return warning;
    }

    public ArrayList<Integer> getArr() {
        return arr;
    }

    public void reduceGuesses() {
        this.guesses -= 1;
    }

    public void addGuess(int guess) {
        this.guessed = guess;
        this.arr.add(guess);
    }

    public void reduceWarning() {
        this.warning -= 1;
    }

    public boolean checkGuess(int guess) {
        return guess == ran;
    }
}

class GuessNumberGUI extends JFrame {
    private GuessNumber guessNumber;
    private JLabel messageLabel;
    private JLabel guessesLeftLabel;
    private JLabel guessedNumbersLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JButton closeButton;

    public GuessNumberGUI() {
        guessNumber = new GuessNumber();

        setTitle("Number Guessing Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        messageLabel = new JLabel("I am thinking of a number between 0-1000. You have 10 guesses.");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setForeground(Color.WHITE);
        backgroundPanel.add(messageLabel, gbc);

        gbc.gridy++;
        guessesLeftLabel = new JLabel("Guesses left: 10");
        guessesLeftLabel.setFont(new Font("Arial", Font.BOLD, 16));
        guessesLeftLabel.setForeground(Color.WHITE);
        backgroundPanel.add(guessesLeftLabel, gbc);

        gbc.gridy++;
        guessedNumbersLabel = new JLabel("Guessed numbers: ");
        guessedNumbersLabel.setFont(new Font("Arial", Font.BOLD, 16));
        guessedNumbersLabel.setForeground(Color.WHITE);
        backgroundPanel.add(guessedNumbersLabel, gbc);

        gbc.gridy++;
        guessField = new JTextField(15);
        backgroundPanel.add(guessField, gbc);

        gbc.gridy++;
        guessButton = new JButton("Guess");
        backgroundPanel.add(guessButton, gbc);

        gbc.gridy++;
        closeButton = new JButton("Close Game");
        backgroundPanel.add(closeButton, gbc);

        guessButton.addActionListener(new GuessButtonListener());
        closeButton.addActionListener(new CloseButtonListener());

        add(backgroundPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private class GuessButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                guessNumber.addGuess(guess);
                guessNumber.reduceGuesses();

                if (guessNumber.checkGuess(guess)) {
                    JOptionPane.showMessageDialog(null, "Correct guess, You won!");
                    resetGame();
                } else {
                    if (guess < guessNumber.getRan()) {
                        messageLabel.setText("Your guess is smaller");
                    } else {
                        messageLabel.setText("Your guess is bigger");
                    }

                    if (guessNumber.getGuesses() == 0) {
                        JOptionPane.showMessageDialog(null, "Try better next time. You lost!");
                        resetGame();
                    }

                    if (guess < 0 && guessNumber.getWarning() == 1) {
                        guessNumber.reduceWarning();
                        JOptionPane.showMessageDialog(null, "You lost the warning. Next time guesses will start reducing.");
                    }

                    guessesLeftLabel.setText("Guesses left: " + guessNumber.getGuesses());
                    guessedNumbersLabel.setText("Guessed numbers: " + guessNumber.getArr().toString());
                }

                guessField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            }
        }

        private void resetGame() {
            guessNumber = new GuessNumber();
            guessesLeftLabel.setText("Guesses left: 10");
            guessedNumbersLabel.setText("Guessed numbers: ");
            messageLabel.setText("I am thinking of a number between 0-1000. You have 10 guesses.");
        }
    }

    private class CloseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to close the game?", "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = new ImageIcon("D:\\C Drive Data\\Dell\\IdeaProjects\\OOP-1\\src\\Number_Guessing_Game_GUI\\m-background.jpg").getImage();
            } catch (Exception e) {
                System.out.println("Image not found");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        new GuessNumberGUI();
    }
}
