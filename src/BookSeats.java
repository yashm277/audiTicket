import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class BookSeats extends JFrame implements ActionListener {
    JFrame f;
    JLabel l1, l2;
    JComboBox c1;
    JButton b1, b2;
    String UserId;

    BookSeats(String UserId) {
        this.UserId = UserId;
        f = new JFrame("Select Event");
        f.setBackground(Color.white);
        f.setLayout(null);

        l1 = new JLabel("Select Event -");
        l1.setBounds(70, 50, 1000, 30);
        f.add(l1);
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT eventname FROM event;";
            ResultSet rs = obj.stm.executeQuery(query);
            ArrayList<String> eve = new ArrayList<String>();
            while (rs.next()) {
                eve.add(rs.getString("eventname"));
            }
            Object[] ev = eve.toArray();
            c1 = new JComboBox<Object>(ev);
            c1.setBounds(180, 50, 150, 30);
            c1.addActionListener(this);
            f.add(c1);
        } catch (Exception e) {
            System.out.println(e);
        }
        b1 = new JButton("Book Tickets");
        b1.setBounds(50, 150, 140, 120);
        b1.addActionListener(this);
        f.add(b1);
        b2 = new JButton("Back");
        b2.setBounds(200, 150, 140, 120);
        b2.addActionListener(this);
        f.add(b2);
        f.getContentPane();
        f.setVisible(true);
        f.setSize(390, 350);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            f.setVisible(false);
            new BookTickets(UserId, (String) c1.getSelectedItem());
        } else if (ae.getSource() == b2) {
            f.setVisible(false);
            Thread t1 = new Thread(new UserHome(UserId));
            t1.start();
        }
    }
}
