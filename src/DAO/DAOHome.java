/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Koneksi.koneksiDB;
import Model.ModPelangganHome;
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
public class DAOHome implements ImplementHome{
    
    Connection connection;
    final String select = " SELECT * FROM pelanggan";
    final String cariNama = "SELECT * FROM pelanggan WHERE nama like ?";
    
    public DAOHome() {
        connection = koneksiDB.connection();
    }
    
    @Override
    public List<ModPelangganHome> getAll() {
        List<ModPelangganHome> lmph = null;
        
        try{
            lmph = new ArrayList<ModPelangganHome>();
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                ModPelangganHome mph = new ModPelangganHome();
                mph.setId(rs.getInt("idpel"));
                mph.setNama(rs.getString("nama"));
                mph.setUang(rs.getInt("uang"));
                mph.setJenis_uang(rs.getInt("jenis_uang"));
                mph.setStatus(rs.getInt("status"));
                lmph.add(mph);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return lmph;
    }

    @Override
    public List<ModPelangganHome> getCariNama(String nama) {
        List<ModPelangganHome> lmph = null;
        try{
            lmph = new ArrayList<ModPelangganHome>();
            
            PreparedStatement st = connection.prepareStatement(cariNama);
            st.setString(1, "%"+nama+"%");
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                ModPelangganHome mph = new ModPelangganHome();
                
                mph.setId(rs.getInt("idpel"));
                mph.setNama(rs.getString("nama"));
                mph.setUang(rs.getInt("uang"));
                mph.setJenis_uang(rs.getInt("jenis_uang"));
                mph.setStatus(rs.getInt("status"));
                lmph.add(mph);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return lmph;
    }
    
    public int getUtangSaya() {
        int jumlah = 0;
        try{
            PreparedStatement st = connection.prepareStatement("SELECT SUM(uang) FROM pelanggan WHERE jenis_uang=1");
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                jumlah = rs.getInt("SUM(uang)");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return jumlah;
    }
    
    public int getUtangPelanggan(){
        int jumlah = 0;
        try{
            PreparedStatement st = connection.prepareStatement("SELECT SUM(uang) FROM pelanggan WHERE jenis_uang=2");
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                jumlah = rs.getInt("SUM(uang)");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return jumlah;
    }
}
