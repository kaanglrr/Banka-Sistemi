import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class musteri_guncelleme extends JFrame{
    private JTextField textField1;
    private JTextField adresfield;
    private JTextField telnofield;
    private JButton telefonuDeğiştirButton;
    private JButton adresiDeğiştirButton;
    private JButton epostayıDeğiştirButton;
    private JPanel panell;
    private User user;
    public musteri_guncelleme() {
        add(panell);
        setSize(800,400);
        setTitle("Prolab 3");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public musteri_guncelleme(User user) {
        this.user=user;
        add(panell);
        setSize(800,400);
        setTitle("Prolab 3");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        epostayıDeğiştirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mail = textField1.getText();
                Long tcno;
                tcno = user.getTcno();
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();

                    String query = "UPDATE musteri SET eposta = '"+mail+"' WHERE tc_no = "+tcno+"";
                    statement.executeUpdate(query);

                    statement.close();
                    connection.close();
                }
                catch (Exception a){
                    a.printStackTrace();
                }
            }
        });
        adresiDeğiştirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adres = adresfield.getText();
                Long tcno;
                tcno = user.getTcno();
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();

                    String query = "UPDATE musteri SET adres = '"+adres+"' WHERE tc_no = "+tcno+"";
                    statement.executeUpdate(query);

                    statement.close();
                    connection.close();
                }
                catch (Exception a){
                    a.printStackTrace();
                }
            }
        });
        telefonuDeğiştirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long telno = Long.parseLong(telnofield.getText());
                Long tcno;
                tcno = user.getTcno();
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();

                    String query = "UPDATE musteri SET tel_no = "+telno+" WHERE tc_no = "+tcno+"";
                    statement.executeUpdate(query);

                    statement.close();
                    connection.close();
                }
                catch (Exception a){
                    a.printStackTrace();
                }
            }
        });
    }

}
