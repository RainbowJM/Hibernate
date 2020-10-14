package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domein.OvChipkaart;
import nl.hu.dp.ovchip.domein.Reiziger;
import nl.hu.dp.ovchip.interfaces.OvChipkaartDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class OvChipkaartDAOHibernate implements OvChipkaartDAO {
    private SessionFactory factory;

    public OvChipkaartDAOHibernate(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public boolean save(OvChipkaart ovChipkaart) {

        return false;
    }

    @Override
    public boolean update(OvChipkaart ovChipkaart) {
        return false;
    }

    @Override
    public boolean delete(OvChipkaart ovChipkaart) {
        return false;
    }

    @Override
    public OvChipkaart findByNummer(int kaart_nummer) {
        return null;
    }

    @Override
    public List<OvChipkaart> findByReiziger(Reiziger reiziger) {
        return null;
    }

    @Override
    public List<OvChipkaart> findAll() {
        return null;
    }
}
