package ICS4U;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonGrid extends JFrame implements ActionListener{
    GridLayout gLayout  = new GridLayout(10, 10);
    BoxLayout bLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);

    JPanel pan = new JPanel();
    JPanel panOutput = new JPanel();

    JLabel output = new JLabel(" ");
    JButton[] buttons = new JButton[100];

    int[] pressed = new int[100];

    public ButtonGrid() {
        setTitle("Button Grid");
        setSize(600, 500);
        setLayout(bLayout);

        pan.setLayout(gLayout);

        for (int i = 0; i < 100; i++) {
            buttons[i] = new JButton(String.valueOf(i+1));
            pan.add(buttons[i]);
            buttons[i].addActionListener(this);
            buttons[i].setBackground(Color.white);
        }

        panOutput.add(output);

        // add panels after modifying them
        add(pan);
        add(panOutput);
        setVisible(true); // must be last line of constructor
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        for (int i = 0; i < 100; i++) {
            if (command.equals(String.valueOf(i + 1))) {
                if (pressed[i] == 0) {
                    output.setText("Button " + command + " has been clicked");
                    pressed[i] = 1;
                    buttons[i].setBackground(Color.lightGray);

                    System.out.println(i);
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

    public static void ripple(JButton button) {
        if (button.getBackground() != Color.lightGray) {
            button.setBackground(Color.orange);
        }
    }
}
