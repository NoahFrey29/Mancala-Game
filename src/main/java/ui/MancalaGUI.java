package ui;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

import mancala.MancalaGame;

public class MancalaGUI extends JFrame{
    
    private JButton submitButton;
    private JTextField usernameField;
    private JLabel statusLabel;

    private boolean isButtonEnabled;
    private String userInputText;
    private int selectedComboBoxIndex;

    private JPanel gameContainer;
    private JLabel messageLabel;
    private JMenuBar menuBar;
    private PositionAwareButton[][] buttons;
    private MancalaGame game;

    public MancalaGUI(String title) {
        super();
        basicSetUp(title);
        setupGameContainer();
        add(gameContainer, BorderLayout.CENTER);
    }
       
    private void basicSetUp(String title){
        this.setTitle(title);
        gameContainer = new JPanel();
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        gameContainer.add(makeButtonPanel());
        gameContainer.add(makeButtonGrid(6, 2));
    }

    private JPanel startupMessage() {
        JPanel temp = new JPanel();
       // Customize the message as desired
        temp.add(new JLabel("Time to play some Mancala!"));
        return temp;
       }
    public void setupGameContainer(){
        gameContainer.add(startupMessage());
    }

    private JPanel makeButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(makeMancalaButton());
        buttonPanel.add(makeNewGameButton());
        return buttonPanel;
    }

    private JButton makeNewGameButton() {
        JButton button = new JButton("New Game");
        return button;
       }
       private JButton makeMancalaButton() {
        JButton button = new JButton("Mancala Game");
        return button;
    }

    private JPanel makeButtonGrid(int tall, int wide){
        JPanel panel = new JPanel();
        buttons = new PositionAwareButton[tall][wide];
        panel.setLayout(new GridLayout(wide, tall));
        for (int y = 0; y<wide; y++) {
            for (int x = 0; x<tall; x++){
                buttons[x][y] = new PositionAwareButton();
                buttons[x][y].setAcross(x+1);
                buttons[x][y].setDown(y+1);

                panel.add(buttons[x][y]);
            }
        }
        return panel;
    }
    public static void main(String[] args) {
        MancalaGUI example = new MancalaGUI("Mancala GUI");
        example.setVisible(true);
    }

}


