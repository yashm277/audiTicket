import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class Change extends JFrame implements ActionListener {
    JFrame f;
    JLabel l1, l2, l3;
    JTextField t2, t3;
    JComboBox c1;
    JButton b1, b2;

    Change() {
        f = new JFrame("Change Details");
        f.setBackground(Color.white);
        f.setLayout(null);

        l1 = new JLabel("Select Event -");
        l1.setBounds(40, 20, 1000, 30);
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
            System.out.println(ev[0]);
            c1 = new JComboBox<Object>(ev);
            c1.setBounds(235, 20, 150, 30);
            c1.addActionListener(this);
            f.add(c1);
        } catch (Exception e) {
            System.out.println(e);
        }
        l2 = new JLabel("New price of a seat -");
        l2.setBounds(40, 70, 1000, 30);
        f.add(l2);

        t2 = new JTextField();
        t2.setBounds(235, 70, 150, 30);
        f.add(t2);

        b1 = new JButton("Back");
        b1.setBounds(40, 125, 120, 30);
        b1.addActionListener(this);
        f.add(b1);

        b2 = new JButton("Change");
        b2.setBounds(250, 125, 120, 30);
        b2.addActionListener(this);
        f.add(b2);

        f.getContentPane();
        f.setVisible(true);
        f.setSize(450, 200);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b2) {
            String name = (String) c1.getSelectedItem();
            String name1 = t2.getText();
            try {
                if (name.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Select an Event");
                } else if (name1.equals("")) {
                    JOptionPane.showMessageDialog(null, "No changes were made");
                } else {
                    ConnectionClass c1 = new ConnectionClass();
                    int a1 = 0;
                    ResultSet rs1 = c1.stm
                            .executeQuery("SELECT BookedSeats FROM Event where EventName  = '" + name + "';");
                    rs1.next();
                    int bookedSeats = Integer.parseInt(rs1.getString("BookedSeats"));
                    if (!name1.equals("") && Double.parseDouble(name1) < 0) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid price!");
                        this.f.setVisible(false);
                        this.f.setVisible(true);
                    } else {
                        Double newPrice = Double.parseDouble(name1);
                        a1 = c1.stm.executeUpdate(
                                "Update event set price = '" + newPrice + "' where eventname = '" + name + "';");

                        if (a1 == 1) {
                            JOptionPane.showMessageDialog(null, "Details Changed Successfully");
                            f.setVisible(false);
                            new AdminHome();
                        } else {
                            JOptionPane.showMessageDialog(null, "Please enter valid details");
                            this.f.setVisible(false);
                            this.f.setVisible(true);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == b1) {
            f.setVisible(false);
            new AdminHome();
        }
    }

}