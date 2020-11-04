/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.DAOPelanggan;
import DAO.DAOTransaksi;
import DAO.ImplementPelanggan;
import DAO.ImplementTransaksi;
import Model.ModPelanggan;
import Model.ModTblPelanggan;
import Model.ModTblTransaksi;
import Model.ModTransaksi;
import Views.FormTransaksi;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzan Aji Prayoga
 */
public class ControllerTransaksi{
    FormTransaksi frame;
    
    ImplementPelanggan implPelanggan;
    List<ModPelanggan> lmp;
    
    ImplementTransaksi implTransaksi;
    List<ModTransaksi> imt;
    
    public ControllerTransaksi(FormTransaksi frame) {
        this.frame = frame;
        implPelanggan = new DAOPelanggan();
        lmp = implPelanggan.getAll();
    }
    
    public void reset(){
        frame.getTf_id().setText("");
        frame.getTf_nama().setText("");
        frame.getTf_idpelanggan().setText("");
        frame.getTf_jumlah().setText("0");
        frame.getLbl_utang().setText("Rp. 0");
    }
    
    public void isiTablePelanggan(){
        lmp = implPelanggan.getAll();
        ModTblPelanggan mtp = new ModTblPelanggan(lmp);
        frame.getTbl_pelanggan().setModel(mtp);
    }
    
    public void isiFieldPelanggan(int row){
        int idpelanggan = lmp.get(row).getId();
        DAOTransaksi dt = new DAOTransaksi();
        int jenis = dt.getUtangJenis(idpelanggan);
        String labeljenis = null;
        if (jenis==1){
            labeljenis= "Utang Saya";
        } else{
            labeljenis = "Utang Pelanggan";
        }
        frame.getLbl_namaUtang().setText(labeljenis);
        frame.getLbl_utang().setText("Rp. "+dt.getUtangJumlah(idpelanggan));
        frame.getTf_idpelanggan().setText(String.valueOf(lmp.get(row).getId()));
        frame.getTf_nama().setText(lmp.get(row).getNama());
        
    }
    
    public void isiTableTransaksi(){
        lmp = implPelanggan.getAll();
        ModTblPelanggan mtp = new ModTblPelanggan(lmp);
        frame.getTbl_pelanggan().setModel(mtp);
    }
    
    public void insert(){
        if(!frame.getTf_idpelanggan().getText().trim().isEmpty() & !frame.getTf_jumlah().getText().trim().isEmpty()){
            ModTransaksi mt = new ModTransaksi();
            DAOTransaksi dt = new DAOTransaksi();
            
            mt.setJumlah(Integer.valueOf(frame.getTf_jumlah().getText().trim()));
            mt.setIdpelanggan(Integer.valueOf(frame.getTf_idpelanggan().getText().trim()));
            mt.setJenis(Integer.valueOf(frame.getCb_jenis().getSelectedIndex()+1));
            mt.CekData();
            dt.insert(mt);
            this.updatePelUang(Integer.valueOf(frame.getTf_idpelanggan().getText().trim()), Integer.valueOf(frame.getTf_jumlah().getText().trim()),  Integer.valueOf(frame.getCb_jenis().getSelectedIndex()+1));
            
            JOptionPane.showMessageDialog(null, "Data diSimpan!");
        } else{
            JOptionPane.showMessageDialog(null, "Data Gagal diSimpan!");
        }
    }
    
    public void isiTabeTransaksiByIDPel(int row){
        DAOTransaksi dt = new DAOTransaksi();
        int idpelanggan = lmp.get(row).getId();
        
        imt = dt.getAllByIdPelanggan(idpelanggan);
        ModTblTransaksi mtt = new ModTblTransaksi(imt);
        frame.getTbl_riwayat().setModel(mtt);
    }
    
    public void updatePelUang(int id, int jumlah, int jenis){
        DAOTransaksi dt = new DAOTransaksi();
        DAOPelanggan dp = new DAOPelanggan();
        
        int jumlahOld = dt.getUtangJumlah(id);
        int jenisOld = dt.getUtangJenis(id);
        int jenisNew = 0;
        int jumlahNew = 0;
        int status = 0;
            if(jenis == 1){
                if(jenisOld == 1){
                     jumlahNew = jumlah + jumlahOld;
                     jenisNew = 1;
                }else if(jenisOld == 2){
                    if(jumlahOld < jumlah){
                        jumlahNew = jumlah-jumlahOld;
                        jenisNew = 2;
                    }else if(jumlahOld > jumlah){
                        jumlahNew = jumlahOld-jumlah;
                        jenisNew = 1;
                    }else{
                        jumlahNew = 0;
                        jenisNew = 0;
                    }
                }else{
                    jumlahNew = jumlah;
                    jenisNew = jenis;
                }
            }else{
                if(jenisOld == 1){
                    if(jumlahOld > jumlah){
                        jumlahNew = jumlahOld - jumlah;
                        jenisNew = 1;
                    }else if(jumlahOld < jumlah){
                        jumlahNew = jumlah - jumlahOld;
                        jenisNew = 2;
                    }else{
                        jumlahNew  = 0;
                        jenisNew = 0;
                    }
                }else if(jenisOld == 2){
                     jumlahNew = jumlah + jumlahOld;
                     jenisNew = 2;
                }else{
                    jumlahNew = jumlah;
                    jenisNew = jenis;
                }
            }
            ModPelanggan mp = new ModPelanggan();
            
            if(jumlahNew == 0){
                status = 2;
            }else{
                status = 1;
            }
            mp.setJenis(jenisNew);
            mp.setUang(jumlahNew);
            mp.setStatus(status);
            mp.setId(id);
            dp.update2(mp);
            
    }
}
