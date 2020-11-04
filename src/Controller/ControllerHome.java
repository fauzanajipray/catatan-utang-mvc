/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.DAOHome;
import DAO.ImplementHome;
import Model.ModPelanggan;
import Model.ModPelangganHome;
import Model.ModTblPelanggan;
import Model.ModTblPelangganHome;
import Views.FormHome;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzan Aji Prayoga
 */
public class ControllerHome {
    FormHome frame;
    List<ModPelangganHome> lmph;
    ImplementHome implPelanggan;
    
    public ControllerHome(FormHome frame) {
        this.frame = frame;
        implPelanggan = new DAOHome();
        lmph = implPelanggan.getAll();
    }

    
    public void isiTable(){
        lmph = implPelanggan.getAll();
        ModTblPelangganHome mtph = new ModTblPelangganHome(lmph);
        frame.getTbl_pelanggan().setModel(mtph);
    }
    
    public void isiTableCariNama(){
        lmph = implPelanggan.getCariNama(frame.getTf_cari().getText().trim());
        ModTblPelangganHome mtph = new ModTblPelangganHome(lmph);
        frame.getTbl_pelanggan().setModel(mtph);
    }
    
    public void cariNama(){
        if(!frame.getTf_cari().getText().trim().isEmpty()){
            implPelanggan.getCariNama(frame.getTf_cari().getText().trim());
        }else{
            JOptionPane.showMessageDialog(null, "Silahkan masukkan Judul");
        }
    }
    
    public void isiLabelUtang(){
        DAOHome dh = new DAOHome();
        frame.getLbl_utangsaya().setText("Rp. "+dh.getUtangSaya());
        frame.getLbl_utangpela().setText("Rp. "+dh.getUtangPelanggan());
    }
}
