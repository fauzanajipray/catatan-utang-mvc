/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Koneksi.koneksiDB;
import Model.ModTransaksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fauzan Aji Prayoga
 */
public class DAOTransaksi implements ImplementTransaksi{
    
    Connection connection;
    final String delete = " DELETE FROM transaksi WHERE idtransaksi=? ";
    final String select = " SELECT * FROM transaksi";
    
    public DAOTransaksi() {
        connection = koneksiDB.connection();
    }
    
    @Override
    public void insert(ModTransaksi mt) {
        PreparedStatement statement = null;
        
        int idpel = mt.getIdpelanggan();
        int jenis = mt.getJenis();
        int jumlah = mt.getJumlah();
        try {
            statement = connection.prepareStatement("INSERT INTO transaksi (tgltransaksi, idpelanggan, jenis, jumlah) VALUES ( now() ,"+idpel+" ,"+jenis+"," +jumlah+")");
              statement.executeUpdate();
            
        } catch (SQLException ex) {
                ex.printStackTrace();
        }
        
        finally{
                try {
                    statement.close();                   
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

   
    public int getUtangJumlah(int idpelanggan) {
        int jumlah = 0;
        try{
            
            PreparedStatement st = connection.prepareStatement("SELECT * FROM pelanggan WHERE idpel='"+idpelanggan+"' ");
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                jumlah = rs.getInt("uang");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return jumlah;
    }
    
    public int getUtangJenis(int idpelanggan) {
        int jumlah = 0;
        try{
            
            PreparedStatement st = connection.prepareStatement("SELECT * FROM pelanggan WHERE idpel='"+idpelanggan+"'");
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                jumlah = rs.getInt("jenis_uang");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return jumlah;
    }
    
     @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ModTransaksi> getAll() {
        List<ModTransaksi> lmt = null;
        
        try{
            lmt = new ArrayList<ModTransaksi>();
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM transaksi ");
            while(rs.next()){
                ModTransaksi mt = new ModTransaksi();
                mt.setId(rs.getInt("idtransaksi"));
                mt.setIdpelanggan(rs.getInt("idpelanggan"));
                mt.setTgltransaksi(rs.getString("tgltransaksi"));
                mt.setJenis(rs.getInt("jenis"));
                mt.setJumlah(rs.getInt("jumlah"));
                lmt.add(mt);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return lmt;
    }
    
    @Override
    public List<ModTransaksi> getAllByIdPelanggan(int idpelanggan){
        List<ModTransaksi> lmt = null;
        final String sql = " SELECT * FROM transaksi  WHERE idpelanggan=?";
        try{
            
            lmt = new ArrayList<ModTransaksi>();
            
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, idpelanggan);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                ModTransaksi mt = new ModTransaksi();
                mt.setId(rs.getInt("idtransaksi"));
                mt.setIdpelanggan(rs.getInt("idpelanggan"));
                mt.setTgltransaksi(rs.getString("tgltransaksi"));
                mt.setJenis(rs.getInt("jenis"));
                mt.setJumlah(rs.getInt("jumlah"));
                lmt.add(mt);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return lmt;
    }
    
    
}
