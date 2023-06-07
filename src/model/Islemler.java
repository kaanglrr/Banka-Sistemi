package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.Calendar;

public class Islemler {
    private int kaynak_id;
    private int hedef_id;
    private Double tutar;
    private String islem_turu;
    private Double kaynak_bakiye;
    private Double hedef_bakiye;
    private java.sql.Date islem_tarihi;

    public Islemler(int kaynak_id, int hedef_id, Double tutar, String islem_turu) {
        this.kaynak_id = kaynak_id;
        this.hedef_id = hedef_id;
        this.tutar = tutar;
        this.islem_turu = islem_turu;
    }

    public Islemler() {
    }

    public static void main(String[] args) {
        Date a = Date.valueOf("2022-12-15");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(a);
        System.out.println(1 + calendar.get(calendar.MONTH));
    }
    public void islem_kaydet(){
        try {


            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab", "root", "k147258369K.");

            Statement statement = connection.createStatement();

            String query2 = "select * from hesap where hesap_id ="+this.kaynak_id+"";
            ResultSet rs= statement.executeQuery(query2);
            if (rs.next()){
                this.kaynak_bakiye = rs.getDouble("hesap_bakiye");
            }

            String query1 = "select * from hesap where hesap_id ="+this.hedef_id+"";
            rs= statement.executeQuery(query1);
            if (rs.next() && this.islem_turu!= "Borç ödeme"){
                this.hedef_bakiye = rs.getDouble("hesap_bakiye");
            }else{
                this.hedef_bakiye=null;
            }

            String query3 = "select * from zaman ";
            rs= statement.executeQuery(query3);
            if (rs.next()){
                this.islem_tarihi = rs.getDate("sistem_tarihi");
                String query = " INSERT  INTO islemler (kaynak_id,hedef_id,islem_turu,tutar,kaynak_bakiye,hedef_bakiye,islem_tarihi) VALUES ("+this.kaynak_id+", "+this.hedef_id+", '"
                        +this.islem_turu+"', "+this.tutar+", "+this.kaynak_bakiye+", "+this.hedef_bakiye+", '"+this.islem_tarihi.toString()+"' ) ";
                statement.executeUpdate(query);
            }






            statement.close();
            connection.close();
        }
        catch (Exception a){
            a.printStackTrace();
        }
    }
    public boolean ay_kontrol(String tarih,String tarih1){
        Date a = Date.valueOf(tarih);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(a);
        int ilk = calendar.get(Calendar.MONTH);
        a = Date.valueOf(tarih1);
        calendar.setTime(a);
        int iki = calendar.get(Calendar.MONTH);
        if(ilk==iki){
            return true;
        }else {
            return false;
        }
    }
    public int getKaynak_id() {
        return kaynak_id;
    }

    public void setKaynak_id(int kaynak_id) {
        this.kaynak_id = kaynak_id;
    }

    public int getHedef_id() {
        return hedef_id;
    }

    public void setHedef_id(int hedef_id) {
        this.hedef_id = hedef_id;
    }

    public Double getTutar() {
        return tutar;
    }

    public void setTutar(Double tutar) {
        this.tutar = tutar;
    }

    public String getIslem_turu() {
        return islem_turu;
    }

    public void setIslem_turu(String islem_turu) {
        this.islem_turu = islem_turu;
    }

    public Double getKaynak_bakiye() {
        return kaynak_bakiye;
    }

    public void setKaynak_bakiye(Double kaynak_bakiye) {
        this.kaynak_bakiye = kaynak_bakiye;
    }

    public Double getHedef_bakiye() {
        return hedef_bakiye;
    }

    public void setHedef_bakiye(Double hedef_bakiye) {
        this.hedef_bakiye = hedef_bakiye;
    }

    public Date getIslem_tarihi() {
        return islem_tarihi;
    }

    public void setIslem_tarihi(Date islem_tarihi) {
        this.islem_tarihi = islem_tarihi;
    }
}
