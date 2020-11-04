/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Koneksi.koneksiDB;
import Model.ModPelanggan;
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
public class DAOPelanggan implements ImplementPelanggan{
    
    Connection connection;
    final String insert = " INSERT INTO pelanggan (nama, nohp, uang, jenis_uang, status) VALUES (?, ?, ?, ?, ?) ";
    final String delete = " DELETE FROM pelanggan WHERE idpel=? ";
    final String update = "UPDATE  pelanggan SET nama=?, nohp=? WHERE idpel=? ";
    final String select = " SELECT * FROM pelanggan";
    final String cariNama = "SELECT * FROM pelanggan WHERE nama like ?";
    
    public DAOPelanggan() {
        connection = koneksiDB.connection();
    }
    
    @Override
    public void insert(ModPelanggan mp) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, mp.getNama());
            statement.setString(2, mp.getNohp());
            statement.setInt(3, mp.getUang());
            statement.setInt(4, mp.getJenis());
            statement.setInt(5, mp.getStatus());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {                
                mp.setId(rs.getInt(1));
            }
            
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

    @Override
    public void delete(int id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setInt(1, id);
            statement.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
                try {
                    statement.close();                   
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
        }
    }

    @Override
    public void update(ModPelanggan mp) {
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, mp.getNama());
            statement.setString(2, mp.getNohp());
            statement.setInt(3, mp.getId());
            statement.executeUpdate();
                      
        } catch (SQLException ex) {
                ex.printStackTrace();
        } finally{
                try {
                    statement.close();                   
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        }
    }

    @Override
    public List<ModPelanggan> getAll() {
        List<ModPelanggan> lmp = null;
        
        try{
            lmp = new ArrayList<ModPelanggan>();
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                ModPelanggan mp = new ModPelanggan();
                mp.setId(rs.getInt("idpel"));
                mp.setNama(rs.getString("nama"));
                mp.setNohp(rs.getString("nohp"));
                lmp.add(mp);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return lmp;
    }

    @Override
    public List<ModPelanggan> getCariNama(String nama) {
        
        List<ModPelanggan> lmp = null;
        try{
            lmp = new ArrayList<ModPelanggan>();
            
            PreparedStatement st = connection.prepareStatement(cariNama);
            st.setString(1, "%"+nama+"%");
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                ModPelanggan mp = new ModPelanggan();
                
                mp.setId(rs.getInt("idpel"));
                mp.setNama(rs.getString("nama"));
                mp.setNohp(rs.getString("nohp"));
                lmp.add(mp);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return lmp;
    }

    @Override
    public List<ModPelanggan> getAllByID(int id) {
        List<ModPelanggan> lmp = null;
        
        try{
            lmp = new ArrayList<ModPelanggan>();
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM pelanggan WHERE idpel='"+id+"'");
            while(rs.next()){
                ModPelanggan mp = new ModPelanggan();
                mp.setId(rs.getInt("idpel"));
                mp.setNama(rs.getString("nama"));
                mp.setNohp(rs.getString("nohp"));
                mp.setUang(rs.getInt("uang"));
                mp.setJenis(rs.getInt("jenis_uang"));
                mp.setStatus(rs.getInt("status"));
                lmp.add(mp);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return lmp;
    }
    
    public void update2(ModPelanggan mp) {
        PreparedStatement statement = null;
         System.out.println("ID : "+mp.getId()+" Jumlah : "+mp.getUang()+" Jenis : "+mp.getJenis()+" status : "+mp.getStatus());
        try {
            statement = connection.prepareStatement("UPDATE pelanggan SET uang="+mp.getUang()+", jenis_uang="+mp.getJenis()+", status="+mp.getStatus()+" WHERE idpel="+mp.getId());
            statement.executeUpdate();
                      
        } catch (SQLException ex) {
                ex.printStackTrace();
        } finally{
                try {
                    statement.close();                   
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        }
    }
}
