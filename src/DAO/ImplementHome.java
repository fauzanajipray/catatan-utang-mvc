/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ModPelangganHome;
import java.util.List;

/**
 *
 * @author Fauzan
 */
public interface ImplementHome {
    public List<ModPelangganHome> getAll();
    public List<ModPelangganHome> getCariNama(String nama);
}
