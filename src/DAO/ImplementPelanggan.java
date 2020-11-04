/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ModPelanggan;
import java.util.List;

/**
 *
 * @author Fauzan
 */
public interface ImplementPelanggan {
    public void insert(ModPelanggan mp);
    public void delete(int id );
    public void update(ModPelanggan mp);
    public List<ModPelanggan> getAll();
    public List<ModPelanggan> getAllByID(int id);
    public List<ModPelanggan> getCariNama(String nama);
}
