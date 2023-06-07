import model.Islemler;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class musteri_kredi extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JButton krediIsteğindeBulunButton;
    private JTextField textField3;
    private JTextField textField4;
    private JButton ödeButton;
    private JPanel musterikredi;
    private JTextField textField5;
    private User user;

    public musteri_kredi() {
        add(musterikredi);
        setSize(800,400);
        setTitle("Prolab 3");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public musteri_kredi(User user) {
        this.user=user;
        add(musterikredi);
        setSize(800,400);
        setTitle("Prolab 3");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        krediIsteğindeBulunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double miktar = Double.parseDouble(textField1.getText());
                int sure = Integer.parseInt(textField2.getText());
                int temsilci_musteri_id;
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();

                    String query1 = "select * from temsilci_musteri where musteri_tc ="+user.getTcno()+"";
                    ResultSet rs= statement.executeQuery(query1);
                    if (rs.next()){
                        temsilci_musteri_id = rs.getInt("temsicli_musteri_id");

                        String query = " INSERT  INTO kredi_istekleri (temsilci_musteri_id,miktar,sure) VALUES ("+temsilci_musteri_id+", "+miktar+", "+sure+" ) ";
                        statement.executeUpdate(query);

                    }



                    statement.close();
                    connection.close();
                }
                catch (Exception a){
                    a.printStackTrace();
                }
            }
        });
        ödeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double miktar = Double.parseDouble(textField4.getText());
                int krediid = Integer.parseInt(textField3.getText());
                int hesapid = Integer.parseInt(textField5.getText());
                int temsilci_musteri_id;
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();

                    String query1 = "select * from temsilci_musteri where musteri_tc ="+user.getTcno()+"";
                    ResultSet rs= statement.executeQuery(query1);
                    if (rs.next()){
                        temsilci_musteri_id = rs.getInt("temsicli_musteri_id");

                        String query = "UPDATE hesap SET hesap_bakiye = hesap_bakiye -"+miktar+" WHERE hesap_id = "+hesapid+"";
                        statement.executeUpdate(query);

                        String sql = "UPDATE krediler SET odenen_ana = odenen_ana +"+miktar+" WHERE kredi_id = "+krediid+"";
                        statement.executeUpdate(sql);

                        Islemler islemler = new Islemler(hesapid,krediid,miktar,"Borç ödeme");
                        islemler.islem_kaydet();

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
