/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzan Aji Prayoga
 */
public class ModTblPelangganHome extends AbstractTableModel{
    
     List<ModPelangganHome> lmph ;
    
    public ModTblPelangganHome(List<ModPelangganHome> lmph){
        this.lmph = lmph;
    }
    
     @Override
    public int getRowCount() {
        return lmph.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int row, int column) {
        
        switch(column){
            case 0:
                return lmph.get(row).getId();
            case 1:
                return lmph.get(row).getNama();
            case 2:
                return lmph.get(row).getUang();
            case 3:
                String jns_utang;
                
                if(lmph.get(row).getJenis_uang() == 1){
                    jns_utang = "Utang Saya";
                }else if(lmph.get(row).getJenis_uang() == 2){
                     jns_utang = "Utang Pelanggan";
                }else{
                     jns_utang = "Tidak Ada";
                }
                return jns_utang;
            case 4:
                String status;
                
                if(lmph.get(row).getStatus() == 1){
                    status = "Belum Lunas";
                }else if(lmph.get(row).getStatus() == 2){
                     status = "Lunas";
                }else{
                     status = "Tidak Ada";
                }
                return status;
                
             default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID";
            case 1:
                return "Nama";
            case 2:
                return "Jumlah Utang";
            case 3:
                return "Jenis Utang";
            case 4:
                return "Status";
             default:
                return null;
        }
    }

}
