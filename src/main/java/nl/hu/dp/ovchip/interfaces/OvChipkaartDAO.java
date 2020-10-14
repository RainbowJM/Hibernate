package nl.hu.dp.ovchip.interfaces;


import nl.hu.dp.ovchip.domein.OvChipkaart;
import nl.hu.dp.ovchip.domein.Reiziger;

import java.util.List;

public interface OvChipkaartDAO {

    boolean save(OvChipkaart ovChipkaart);

    boolean update(OvChipkaart ovChipkaart);

    boolean delete(OvChipkaart ovChipkaart);

    OvChipkaart findByNummer(int kaart_nummer);

    List<OvChipkaart> findByReiziger(Reiziger reiziger);

    List<OvChipkaart> findAll();
}
