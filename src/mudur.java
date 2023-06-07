import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class mudur extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JButton dövizEkleButton;
    private JTextField textField3;
    private JButton maaşıGüncelleButton;
    private JTextField textField4;
    private JTextField textField5;
    private JButton faizGüncelleButton;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JButton müşteriEkleButton;
    private JPanel panel1;
    private JTextField textField13;
    private JTextField textField14;
    private JButton dövizGüncelleButton;
    private JTextField textField15;
    private JButton işlemleriGösterButton;
    private JTable table1;
    private User user;

    public mudur(){
        add(panel1);
        setSize(800,400);
        setTitle("Prolab 3");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public mudur(User user) {
        this.user = user;
        add(panel1);
        setSize(800, 400);
        setTitle("Prolab 3");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dövizEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String doviz_turu = textField1.getText();
                Double doviz_endeksi = Double.parseDouble(textField2.getText());
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();


                    String query = " INSERT  INTO dovizler (doviz_turu,doviz_endeksi) VALUES ('"+doviz_turu+"', "+doviz_endeksi+" ) ";
                    statement.executeUpdate(query);



                    statement.close();
                    connection.close();
                }
                catch (Exception a){
                    a.printStackTrace();
                }
            }
        });
        faizGüncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double kredi_faiz = Double.parseDouble(textField4.getText());
                Double gecikme_faiz = Double.parseDouble(textField5.getText());
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();

                    String query = "UPDATE faiz SET kredi_faiz = "+kredi_faiz+"";
                    statement.executeUpdate(query);

                    String query1 = "UPDATE faiz SET gecikme_faiz = "+gecikme_faiz+"";
                    statement.executeUpdate(query1);

                    statement.close();
                    connection.close();
                }
                catch (Exception a){
                    a.printStackTrace();
                }
            }
        });
        dövizGüncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int doviz_id = Integer.parseInt(textField13.getText());
                Double doviz_endeksi = Double.parseDouble(textField14.getText());
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();

                    String query = "UPDATE dovizler SET doviz_endeksi = "+doviz_endeksi+" where doviz_id = "+doviz_id+"";
                    statement.executeUpdate(query);
                    statement.close();
                    connection.close();
                }
                catch (Exception a){
                    a.printStackTrace();
                }
            }
        });
        maaşıGüncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double maas = Double.parseDouble(textField3.getText());
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();

                    String query = "UPDATE maas SET maas_miktar = "+maas+"";
                    statement.executeUpdate(query);
                    statement.close();
                    connection.close();
                }
                catch (Exception a){
                    a.printStackTrace();
                }
            }
        });
        müşteriEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long tc_no = Long.parseLong(textField6.getText());
                String ad_soyad = textField7.getText();
                String adres = textField9.getText();
                String eposta = textField10.getText();
                Long tel_no = Long.parseLong(textField11.getText());
                String parola = textField12.getText();
                Long temsilci_id;
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();
                    String query1 = "SELECT temsilci_tc, COUNT(temsilci_tc) FROM temsilci_musteri GROUP BY temsilci_tc ORDER BY COUNT(temsilci_tc) ASC limit 1";
                    ResultSet rs= statement.executeQuery(query1);
                    rs.next();
                    temsilci_id = rs.getLong(1);

                    String query = " INSERT  INTO musteri (tc_no,ad_soyad,adres,eposta,tel_no,parola) VALUES ("+tc_no+", '"+ad_soyad+"', '"+adres+"', '"+eposta+"', "+tel_no+", '"+parola+"' ) ";
                    statement.executeUpdate(query);

                    String query2 = " INSERT  INTO temsilci_musteri (musteri_tc,temsilci_tc) VALUES ("+tc_no+", "+temsilci_id+") ";
                    statement.executeUpdate(query2);


                    statement.close();
                    connection.close();
                }
                catch (Exception a){
                    a.printStackTrace();
                }
            }
        });
        işlemleriGösterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int adet = Integer.parseInt(textField15.getText());
                try {


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

                    Statement statement = connection.createStatement();

                    String query = "select * from islemler order by islem_id desc limit "+adet+"";
                    ResultSet rs= statement.executeQuery(query);
                    ResultSetMetaData rsmd= rs.getMetaData();
                    DefaultTableModel model= (DefaultTableModel) table1.getModel();
                    model.setRowCount(0);
                    int cols = rsmd.getColumnCount();
                    String[] colName = new String[cols];
                    for (int i=0;i<cols;i++){
                        colName[i]=rsmd.getColumnName(i+1);
                    }
                    model.setColumnIdentifiers(colName);
                    String islem_id,kaynak_hesap,hedef_hesap,islem_turu,tutar,kaynak_bakiye,hedef_bakiye,islem_tarihi;
                    while (rs.next()) {
                        islem_id = rs.getString(1);
                        kaynak_hesap = rs.getString(2);
                        hedef_hesap = rs.getString(3);
                        islem_turu = rs.getString(4);
                        tutar = rs.getString(5);
                        kaynak_bakiye = rs.getString(6);
                        hedef_bakiye = rs.getString(7);
                        islem_tarihi = rs.getString(8);
                        String[] row = {islem_id, kaynak_hesap, hedef_hesap, islem_turu, tutar, kaynak_bakiye, hedef_bakiye, islem_tarihi};
                        model.addRow(row);
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
