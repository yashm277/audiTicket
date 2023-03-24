import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class BookTickets extends JFrame implements ActionListener {
    JFrame f;
    JLabel l1, l2, l3;
    JButton b1, b2, b3;
    JTextField t2, t1;
    String UserId, eve;

    BookTickets(String UserId, String eve) {
        this.UserId = UserId;
        this.eve = eve;
        f = new JFrame("Book Seats");
        f.setBackground(Color.white);
        f.setLayout(null);
        l1 = new JLabel("Number of seats -");
        l1.setBounds(40, 70, 1000, 30);
        f.add(l1);

        t1 = new JTextField();
        t1.setBounds(200, 70, 150, 30);
        f.add(t1);

        l2 = new JLabel("Seat numbers -");
        l2.setBounds(40, 120, 1000, 30);
        f.add(l2);

        l3 = new JLabel("(format - 1 4 18)");
        l3.setBounds(35, 138, 1000, 25);
        f.add(l3);
        t2 = new JTextField();
        t2.setBounds(200, 120, 150, 30);
        f.add(t2);

        b1 = new JButton("Back");
        b1.setBounds(40, 180, 140, 30);
        b1.addActionListener(this);

        b2 = new JButton("Book");
        b2.setBounds(200, 180, 140, 30);
        b2.addActionListener(this);

        b3 = new JButton("View Available Seats");
        b3.setBounds(110, 210, 160, 30);
        b3.addActionListener(this);

        f.add(b3);
        f.add(b2);
        f.add(b1);

        f.getContentPane();
        f.setVisible(true);
        f.setSize(400, 330);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b2) {
            String bookSeats = t2.getText();
            String bookCount = t1.getText();
            if (bookSeats.equals("") || bookCount.equals("")) {
                JOptionPane.showMessageDialog(null, "Enter Valid Deatils");
            } else {

                try {
                    ConnectionClass c1 = new ConnectionClass();
                    ResultSet rs1 = c1.stm
                            .executeQuery("SELECT availableSeats FROM Event where EventName  = '" + eve + "';");
                    rs1.next();
                    String availableSeats = rs1.getString("availableSeats");
                    if (availableSeats == null) {
                        JOptionPane.showMessageDialog(null, "No seats are availble in " + eve);
                    } else {
                        String availableTest = availableSeats.trim();
                        String[] availableArray = availableTest.split(" ");
                        String bookTest = bookSeats.trim();
                        String[] bookArray = bookTest.split(" ");
                        int flag = 0;
                        if (Integer.parseInt(bookCount) != bookArray.length) {
                            JOptionPane.showMessageDialog(null, "Invalid Details");
                        } else {
                            for (String element : bookArray) {
                                for (String availableElement : availableArray) {
                                    if (Integer.parseInt(element) == Integer.parseInt(availableElement)) {
                                        flag++;
                                    }
                                }
                            }
                            if (flag != Integer.parseInt(bookCount)) {
                                JOptionPane.showMessageDialog(null, "The requested seats are not available!");
                            } else {
                                synchronized (this) {
                                    ConnectionClass c2 = new ConnectionClass();
                                    String q1 = "insert into tickets values('" + UserId + "','" + eve + "','"
                                            + bookCount
                                            + "','"
                                            + bookSeats + "')";
                                    int aa = c2.stm.executeUpdate(q1);
                                    if (aa == 1) {
                                        ConnectionClass c3 = new ConnectionClass();
                                        ArrayList<String> newa = new ArrayList<>();
                                        for (String d : availableArray) {
                                            newa.add(d);
                                        }
                                        for (String element : bookArray) {
                                            for (String availableElement : availableArray) {
                                                if (Integer.parseInt(element) == Integer.parseInt(availableElement)) {
                                                    newa.remove(element);
                                                }
                                            }
                                        }

                                        StringBuffer sb = new StringBuffer();
                                        for (String ele : newa) {
                                            sb.append(ele + " ");
                                        }
                                        String s = sb.toString();
                                        s.trim();
                                        String q = "Update event set bookedSeats = bookedSeats + "
                                                + Integer.parseInt(bookCount) + ", availableSeats ='" + s
                                                + "' where eventName = '"
                                                + eve + "';";
                                        int aaa = c3.stm.executeUpdate(q);
                                        JOptionPane.showMessageDialog(null, "Tickets booked Successfully");

                                    } else {
                                        JOptionPane.showMessageDialog(null, "Please enter valid details");
                                        this.f.setVisible(false);
                                        this.f.setVisible(true);
                                    }
                                }
                            }

                        }

                    }

                } catch (ArithmeticException e) {
                    JOptionPane.showMessageDialog(null, "Please enter Valid Deatils");
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Please enter Valid Details");
                }
            }
        } else if (ae.getSource() == b1) {
            f.setVisible(false);
            new BookSeats(UserId);
        } else if (ae.getSource() == b3) {
            try {
                new Table(eve, UserId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
