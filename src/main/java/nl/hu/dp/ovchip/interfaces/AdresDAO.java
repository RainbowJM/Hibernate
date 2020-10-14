package nl.hu.dp.ovchip.interfaces;


import nl.hu.dp.ovchip.domein.Adres;
import nl.hu.dp.ovchip.domein.Reiziger;

import java.util.List;

public interface AdresDAO {

    public boolean save(Adres adres);

    public boolean update(Adres adres);

    public boolean delete(Adres adres);

    public Adres findById(int id);

    public Adres findByReiziger(Reiziger reiziger);

    public List<Adres> findAll();
}
