package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domein.Adres;
import nl.hu.dp.ovchip.domein.Reiziger;
import nl.hu.dp.ovchip.interfaces.AdresDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AdresDAOHibernate implements AdresDAO {
    private SessionFactory factory;

    public AdresDAOHibernate(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public boolean save(Adres adres) {
        Session session = factory.openSession();

        Transaction trans = session.beginTransaction();
        session.save(adres);
        trans.commit();

        return true;
    }

    @Override
    public boolean update(Adres adres) {
        Session session = factory.openSession();
        session.update(adres);
        return true;
    }

    @Override
    public boolean delete(Adres adres) {
        Session session = factory.openSession();
        session.delete(adres);
        return true;
    }

    @Override
    public Adres findById(int id) {
        Session session = factory.openSession();
        Adres adres = session.get(Adres.class, id);
        return adres;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        Session session = factory.openSession();
        Adres adres = session.get(Adres.class, reiziger.getReiziger_id());
        return adres;
    }

    @Override
    public List<Adres> findAll() {
//        List<Adres>
        return null;
    }
}
