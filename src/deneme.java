import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class deneme extends JFrame{
    private JButton button1;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel succes;
    private JPanel panel1;
    private JButton çalışanGirişiButton;
    private JButton müdürGirişiButton;


    public deneme() {
        add(panel1);
        setSize(800,400);
        setTitle("Prolab 3");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long tc_no;
                String parola;
                tc_no = Long.parseLong(textField1.getText());
                parola = textField2.getText();

                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();


                    String query = "select * from musteri where tc_no = "+tc_no+" and parola='"+parola+"'";

                    ResultSet rs= statement.executeQuery(query);



                    if (rs.next()){
                        succes.setText("login başarılı!");
                        User user = new User();
                        user.setAd_soyad(rs.getString("ad_soyad"));
                        user.setEposta(rs.getString("eposta"));
                        user.setAdres(rs.getString("adres"));
                        user.setTelno(rs.getLong("tel_no"));
                        user.setTcno(rs.getLong("tc_no"));
                        musteri m = new musteri(user);
                        m.setVisible(true);
                    }else
                    {
                        succes.setText("yanlış ad veya şifre!");
                    }
                    statement.close();
                    connection.close();
                }
                catch (Exception a){
                    a.printStackTrace();
                }
            }
        });
        müdürGirişiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long tc_no;
                String parola;
                tc_no = Long.parseLong(textField1.getText());
                parola = textField2.getText();

                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();


                    String query = "select * from mudur where tc_no = "+tc_no+" and parola='"+parola+"'";

                    ResultSet rs= statement.executeQuery(query);



                    if (rs.next()){
                        succes.setText("login başarılı!");
                        User user = new User();
                        user.setAd_soyad(rs.getString("ad_soyad"));
                        user.setEposta(rs.getString("eposta"));
                        user.setAdres(rs.getString("adres"));
                        user.setTelno(rs.getLong("tel_no"));
                        user.setTcno(rs.getLong("tc_no"));
                        mudur mu = new mudur(user);
                        mu.setVisible(true);
                    }else
                    {
                        succes.setText("yanlış ad veya şifre!");
                    }
                    statement.close();
                    connection.close();
                }
                catch (Exception a){
                    a.printStackTrace();
                }
            }
        });
        çalışanGirişiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long tc_no;
                String parola;
                tc_no = Long.parseLong(textField1.getText());
                parola = textField2.getText();

                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();


                    String query = "select * from temsilci where tc_no = "+tc_no+" and parola='"+parola+"'";

                    ResultSet rs= statement.executeQuery(query);



                    if (rs.next()){
                        succes.setText("login başarılı!");
                        User user = new User();
                        user.setAd_soyad(rs.getString("ad_soyad"));
                        user.setEposta(rs.getString("eposta"));
                        user.setAdres(rs.getString("adres"));
                        user.setTelno(rs.getLong("tel_no"));
                        user.setTcno(rs.getLong("tc_no"));
                        temsilci t = new temsilci(user);
                        t.setVisible(true);
                    }else
                    {
                        succes.setText("yanlış ad veya şifre!");
                    }
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
