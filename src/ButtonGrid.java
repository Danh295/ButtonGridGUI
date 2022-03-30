import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Danny Hu
 * @description Displays 10x10 grid of buttons:
 *              Buttons labeled from 1-100, if button is clicked, label updates to indicate button clicked
 *              If a button has already been clicked, label updates to indicate that it cannot be clicked again
 *              Buttons that are clicked change to light-gray colour, surrounding buttons change to yellowish-orange colour
 */
public class ButtonGrid extends JFrame implements ActionListener { // GUI class of the program

    // class/instance variables
    GridLayout gLayout  = new GridLayout(10, 10);
    BoxLayout bLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
    JPanel pan = new JPanel();
    JPanel panOutput = new JPanel();
    JLabel output = new JLabel(" ");
    JButton[] buttons = new JButton[100];

    int[] pressed = new int[100]; // counter array to track buttons clicked

    /**
     * Constructor method
     */
    public ButtonGrid() {

        // initialize frame properties
        setTitle("Button Grid");
        setSize(600, 500);
        setLayout(bLayout);

        // initialize panels
        pan.setLayout(gLayout);
        panOutput.add(output);
        // initialize buttons
        for (int i = 0; i < 100; i++) {
            buttons[i] = new JButton(String.valueOf(i+1));
            pan.add(buttons[i]);
            buttons[i].addActionListener(this);
            buttons[i].setBackground(Color.white);
        }

        // add panels after modifying them
        add(pan);
        add(panOutput);

        setVisible(true); // last line of constructor - ensures that frame is visible
    }

    /**
     * ActionListener method
     * @param event user input from GUI interaction with buttons
     */
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand(); // get string value of string input

        // loop through integers to check for value of input
        for (int i = 0; i < 100; i++) {

            if (command.equals(String.valueOf(i + 1))) {

                if (pressed[i] == 0) {

                    // mark button clicked & change colour
                    output.setText("Button " + command + " has been clicked");
                    pressed[i] = 1;
                    buttons[i].setBackground(Color.lightGray);

                    // mark surrounding buttons & change colours
                    if (i - 10 > 0) {
                        ripple(buttons[i - 10]);
                    }
                    if (i + 10 < 100) {
                        ripple(buttons[i + 10]);
                    }
                    if (i % 10 != 0) {
                        ripple(buttons[i - 1]);
                        if (i - 11 > -1) {
                            ripple(buttons[i - 11]);
                        }
                        if (i + 9 < 100) {
                            ripple(buttons[i + 9]);
                        }
                    }
                    if ((i + 1) % 10 != 0) {
                        System.out.println((i + 1) + "% 10 = " + ((i + 1) % 10));
                        ripple(buttons[i + 1]);
                        if (i + 11 < 100) {
                            ripple(buttons[i + 11]);
                        }
                        if (i - 9 > 0) {
                            ripple(buttons[i - 9]);
                        }
                    }

                } else {
                    output.setText("This button has already been clicked, please choose another one");
                }
            }
        }
    }

    /**
     *
     * @param button
     */
    public static void ripple(JButton button) {
        if (button.getBackground() != Color.lightGray) {
            button.setBackground(Color.orange);
        }
    }
}
