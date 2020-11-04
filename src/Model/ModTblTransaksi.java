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
public class ModTblTransaksi extends AbstractTableModel{
    List<ModTransaksi> lmt ;
    
    public ModTblTransaksi(List<ModTransaksi> lmt){
        this.lmt = lmt;
    }
    
    @Override
    public int getRowCount() {
        return lmt.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int row, int column) {
         
        switch(column){
            case 0:
                return lmt.get(row).getId();
            case 1:
                return lmt.get(row).getIdpelanggan();
            case 2:
                return lmt.get(row).getTgltransaksi();
            case 3:
                return lmt.get(row).getJenis();
            case 4:
                return lmt.get(row).getJumlah();
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
                return "ID Pelanggan";
            case 2:
                return "Tanggal Transaksi";
            case 3:
                return "Jenis Transaksi";
            case 4:
                return "Jumlah";
             default:
                return null;
        }
    }
    
}
