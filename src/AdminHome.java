import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdminHome extends JFrame implements ActionListener {
    JFrame f;
    JButton b1, b2, b3, b4;

    AdminHome() {
        f = new JFrame("Admin Home");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        b1 = new JButton("Add Show/Event");
        b1.setBounds(50, 50, 140, 120);
        b1.addActionListener(this);
        f.add(b1);

        b2 = new JButton("Change Details");
        b2.setBounds(200, 50, 140, 120);
        b2.addActionListener(this);
        f.add(b2);

        b3 = new JButton("Track Details");
        b3.setBounds(50, 180, 140, 120);
        b3.addActionListener(this);
        f.add(b3);

        b4 = new JButton("Log Out");
        b4.setBounds(200, 180, 140, 120);
        b4.addActionListener(this);
        f.add(b4);

        f.getContentPane();
        f.setVisible(true);
        f.setSize(390, 350);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            f.setVisible(false);
            new AddEvent();
        } else if (ae.getSource() == b2) {
            f.setVisible(false);
            new Change();
        } else if (ae.getSource() == b3) {
            f.setVisible(false);
            new Track();
        } else if (ae.getSource() == b4) {
            f.setVisible(false);
            new AdminLogin();
        }
    }
}