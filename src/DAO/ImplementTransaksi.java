/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ModTransaksi;
import java.util.List;

/**
 *
 * @author Fauzan
 */
public interface ImplementTransaksi {
    public void insert(ModTransaksi mt);
    public void delete(int id);
    public List<ModTransaksi> getAll();
    public List<ModTransaksi> getAllByIdPelanggan(int id);
}
