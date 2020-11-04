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
public class ModTblPelanggan extends AbstractTableModel{
    
    List<ModPelanggan> lmv ;
    
    public ModTblPelanggan(List<ModPelanggan> lmv){
        this.lmv = lmv;
    }
    @Override
    public int getRowCount() {
        return lmv.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0:
                return lmv.get(row).getId();
            case 1:
                return lmv.get(row).getNama();
            case 2:
                return lmv.get(row).getNohp();
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
                return "No. Hp";
             default:
                return null;
        }
    }

}
