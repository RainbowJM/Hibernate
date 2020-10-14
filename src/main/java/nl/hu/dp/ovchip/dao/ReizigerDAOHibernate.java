package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domein.Reiziger;
import nl.hu.dp.ovchip.interfaces.ReizigerDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO {
    private SessionFactory factory;

    public ReizigerDAOHibernate(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        Session session = factory.openSession();
        session.save(reiziger);
        return true;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        Session session = factory.openSession();
        session.update(reiziger);
        return true;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        Session session = factory.openSession();
        session.delete(reiziger);
        return true;
    }

    @Override
    public Reiziger findById(int id) {
        Session session = factory.openSession();
        Reiziger reiziger = session.get(Reiziger.class, id);
        return reiziger;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        return null;
    }

    @Override
    public List<Reiziger> findAll() {
        return null;
    }
}
