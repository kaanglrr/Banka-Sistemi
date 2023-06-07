package model;

import java.time.LocalDate;
import java.util.Date;

public class User {

    private String ad_soyad;
    private String eposta;
    private String adres;
    private Long telno;
    private Long tcno;

    public User() {
    }

    public User(String ad_soyad, String eposta, String adres, Long telno, Long tcno) {
        this.ad_soyad = ad_soyad;
        this.eposta = eposta;
        this.adres = adres;
        this.telno = telno;
        this.tcno = tcno;
    }

    public String getAd_soyad() {
        return ad_soyad;
    }

    public void setAd_soyad(String ad_soyad) {
        this.ad_soyad = ad_soyad;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Long getTelno() {
        return telno;
    }

    public void setTelno(Long telno) {
        this.telno = telno;
    }

    public Long getTcno() {
        return tcno;
    }

    public void setTcno(Long tcno) {
        this.tcno = tcno;
    }
}
