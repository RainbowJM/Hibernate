package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domein.Adres;
import nl.hu.dp.ovchip.domein.Reiziger;
import nl.hu.dp.ovchip.interfaces.AdresDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AdresDAOHibernate implements AdresDAO {
    private Session session;

    public AdresDAOHibernate(Session session){
        this.session = session;
    }

    @Override
    public boolean save(Adres adres) {
        try {
            Transaction trans = session.beginTransaction();
            session.save(adres);
            trans.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        try{
            Transaction trans = session.beginTransaction();
            session.update(adres);
            trans.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Adres adres) {
        try{
            Transaction trans = session.beginTransaction();
            session.delete(adres);
            trans.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Adres findById(int id) {
        try{
            Adres adres = session.get(Adres.class, id);
            return adres;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        try{
            Adres adres = session.get(Adres.class, reiziger.getReiziger_id());
            return adres;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Adres> findAll() {
        try{
            List<Adres> adresList = session.createQuery("FROM adres",Adres.class).getResultList();
            return adresList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
