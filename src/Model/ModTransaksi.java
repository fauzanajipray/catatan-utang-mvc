/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author Fauzan Aji Prayoga
 */
public class ModTransaksi {
    private int id;
    private String tgltransaksi;
    private int idpelanggan;
    private int jenis;
    private int jumlah;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTgltransaksi() {
        return tgltransaksi;
    }

    public void setTgltransaksi(String tgltransaksi) {
        this.tgltransaksi = tgltransaksi;
    }

    public int getIdpelanggan() {
        return idpelanggan;
    }

    public void setIdpelanggan(int idpelanggan) {
        this.idpelanggan = idpelanggan;
    }

    public int getJenis() {
        return jenis;
    }

    public void setJenis(int jenis) {
        this.jenis = jenis;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public void CekData(){
        System.out.println("Tgl : "+getTgltransaksi());
        System.out.println("Id Pelanggan : "+getIdpelanggan());
        System.out.println("Jenis : "+getJenis());
        System.out.println("Jumlah : "+getJumlah());
    }
}
