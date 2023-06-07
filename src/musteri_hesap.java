import model.Islemler;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class musteri_hesap extends JFrame{
    private JTextField textField1;
    private JButton paraÇekButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton paraYatırButton;
    private JTextField dovizidfield;
    private JButton hesapOluşturButton;
    private JButton dovizleriGörüntüleButton;
    private JTextField hesapsilidfield;
    private JButton hesabıSilButton;
    private JTextField hedeffield;
    private JTextField kaynakfield;
    private JTextField textField9;
    private JButton paraGönderButton;
    private JPanel hesappanel;
    private User user;

    public musteri_hesap() {
        add(hesappanel);
        setSize(800,400);
        setTitle("Prolab 3");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    public musteri_hesap(User user) {
        this.user=user;
        add(hesappanel);
        setSize(800,400);
        setTitle("Prolab 3");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        paraYatırButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double yatirilan = Double.parseDouble(textField4.getText());
                int hesapid = Integer.parseInt(textField3.getText());

                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();

                    String query = "UPDATE hesap SET hesap_bakiye = hesap_bakiye +"+yatirilan+" WHERE hesap_id = "+hesapid+"";
                    statement.executeUpdate(query);
                    Islemler islemler = new Islemler(0,hesapid,yatirilan,"Para yatırma");
                    islemler.islem_kaydet();
                    statement.close();
                    connection.close();
                }
                catch (Exception a){
                    a.printStackTrace();
                }
            }
        });
        paraÇekButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double cekilen = Double.parseDouble(textField2.getText());
                int hesapid = Integer.parseInt(textField1.getText());
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();

                    String query = "UPDATE hesap SET hesap_bakiye = hesap_bakiye -"+cekilen+" WHERE hesap_id = "+hesapid+"";
                    statement.executeUpdate(query);
                    Islemler islemler = new Islemler(hesapid,0,cekilen,"Para çekme");
                    islemler.islem_kaydet();
                    statement.close();
                    connection.close();
                }
                catch (Exception a){
                    a.printStackTrace();
                }
            }
        });
        paraGönderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double gonderilen = Double.parseDouble(textField9.getText());
                int hedef_hesapid = Integer.parseInt(hedeffield.getText());
                int kaynak_hesapid = Integer.parseInt(kaynakfield.getText());
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();

                    String query = "UPDATE hesap SET hesap_bakiye = hesap_bakiye +"+gonderilen+" WHERE hesap_id = "+hedef_hesapid+"";
                    statement.executeUpdate(query);

                    String sql = "UPDATE hesap SET hesap_bakiye = hesap_bakiye -"+gonderilen+" WHERE hesap_id = "+kaynak_hesapid+"";
                    statement.executeUpdate(sql);

                    Islemler islemler = new Islemler(kaynak_hesapid,hedef_hesapid,gonderilen,"Para gönderme");
                    islemler.islem_kaydet();

                    statement.close();
                    connection.close();
                }
                catch (Exception a){
                    a.printStackTrace();
                }
            }
        });
        hesapOluşturButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dovizid = Integer.parseInt(dovizidfield.getText());
                int temsilci_musteri_id;
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();

                    String query1 = "select * from temsilci_musteri where musteri_tc ="+user.getTcno()+"";
                    ResultSet rs= statement.executeQuery(query1);
                    if (rs.next()){
                        temsilci_musteri_id = rs.getInt("temsicli_musteri_id");

                        String query = " INSERT  INTO hesap_istekleri (doviz_id,temsilci_musteri_id) VALUES ("+dovizid+", "+temsilci_musteri_id+" ) ";
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
        hesabıSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hesapid = Integer.parseInt(hesapsilidfield.getText());
                int temsilci_musteri_id;
                int bakiye=1;
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();

                    String query2 = "select * from hesap where hesap_id ="+hesapid+"";
                    ResultSet rs= statement.executeQuery(query2);
                    if (rs.next() && rs.getLong("kisi_tc")==user.getTcno()){
                        bakiye = rs.getInt("hesap_bakiye");
                    }

                    String query1 = "select * from temsilci_musteri where musteri_tc ="+user.getTcno()+"";
                    rs= statement.executeQuery(query1);



                    if (rs.next() && bakiye==0){
                        temsilci_musteri_id = rs.getInt("temsicli_musteri_id");

                        String query = " INSERT  INTO hesap_sil_istekleri (hesap_id,temsilci_musteri_id) VALUES ("+hesapid+", "+temsilci_musteri_id+" ) ";
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
    }
}
