/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.DAOPelanggan;
import DAO.ImplementPelanggan;
import Model.ModPelanggan;
import Model.ModTblPelanggan;
import Views.FormPelanggan;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzan Aji Prayoga
 */
public class ControllerPelanggan {
    FormPelanggan frame;
    ImplementPelanggan implPelanggan;
    List<ModPelanggan> lmp;
    
    public ControllerPelanggan(FormPelanggan frame) {
        this.frame = frame;
        implPelanggan = new DAOPelanggan();
        lmp = implPelanggan.getAll();
    }
    
    public void reset(){
        frame.getTf_id().setText("");
        frame.getTf_nama().setText("");
        frame.getTf_nohp().setText("");
    }
    
    public void isiTable(){
        lmp = implPelanggan.getAll();
        ModTblPelanggan mtp = new ModTblPelanggan(lmp);
        frame.getTbl_pelanggan().setModel(mtp);
    }
    
    
    public void isiField(int row){
        frame.getTf_id().setText(String.valueOf(lmp.get(row).getId()));
        frame.getTf_nama().setText(lmp.get(row).getNama());
        frame.getTf_nohp().setText(lmp.get(row).getNohp());
    }
    
    public void insert(){
        if(!frame.getTf_nama().getText().trim().isEmpty() & !frame.getTf_nohp().getText().trim().isEmpty()){
            ModPelanggan mp = new ModPelanggan();
            
            mp.setNama(frame.getTf_nama().getText().trim());
            mp.setNohp(String.valueOf(frame.getTf_nohp().getText().trim()));
            mp.setJenis(0);
            mp.setStatus(0);
            mp.setUang(0);
            
            implPelanggan.insert(mp);
            JOptionPane.showMessageDialog(null, "Data diSimpan!");
        } else{
            JOptionPane.showConfirmDialog(null, "Data Gagal diSimpa!");
        }
    }
    
    public void delete(){
        if(!frame.getTf_id().getText().trim().isEmpty()){
            int id = Integer.parseInt(frame.getTf_id().getText().trim());
            
            implPelanggan.delete(id);
            JOptionPane.showMessageDialog(null, "Data Di Hapus");
        }else{
            JOptionPane.showMessageDialog(null, "Data Gagal Di Hapus");
        }
    }
    
    public void update(){
        if(!frame.getTf_id().getText().trim().isEmpty()){
            ModPelanggan mp = new ModPelanggan();
            
            mp.setNama(frame.getTf_nama().getText().trim());
            mp.setNohp(String.valueOf(frame.getTf_nohp().getText().trim()));
            mp.setId(Integer.valueOf(frame.getTf_id().getText()));
           
            implPelanggan.update(mp);
            JOptionPane.showMessageDialog(null, "Data Di Perbarui");
        }else{
            JOptionPane.showMessageDialog(null, "Data Gagal Di Perbarui");
        }    
    }    
    public void isiTableCariNama(){
        lmp = implPelanggan.getCariNama(frame.getTf_cari().getText().trim());
        ModTblPelanggan mtv = new ModTblPelanggan(lmp);
        frame.getTbl_pelanggan().setModel(mtv);
    }
    
    public void cariNama(){
        if(!frame.getTf_cari().getText().trim().isEmpty()){
            implPelanggan.getCariNama(frame.getTf_cari().getText().trim());
            
        }else{
            JOptionPane.showMessageDialog(null, "Silahkan masukkan Judul");
        }
    }
}
