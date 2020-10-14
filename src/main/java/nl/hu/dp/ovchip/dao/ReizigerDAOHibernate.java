package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domein.Adres;
import nl.hu.dp.ovchip.domein.Reiziger;
import nl.hu.dp.ovchip.interfaces.AdresDAO;
import nl.hu.dp.ovchip.interfaces.OvChipkaartDAO;
import nl.hu.dp.ovchip.interfaces.ReizigerDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO {
    private Session session;
    private AdresDAO adao;
    private OvChipkaartDAO odao;

    public ReizigerDAOHibernate(Session session){
        this.session = session;
    }

    public void setAdao(AdresDAO adao){this.adao = adao;}

    public void setOdao(OvChipkaartDAO odao){this.odao = odao;}

    @Override
    public boolean save(Reiziger reiziger) {
        try {
            Transaction trans = session.beginTransaction();
            session.save(reiziger);
            trans.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try{
            Transaction trans = session.beginTransaction();
            session.update(reiziger);
            trans.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try{
            Transaction trans = session.beginTransaction();
            session.delete(reiziger);
            trans.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) {
        try{
            Reiziger reiziger = session.get(Reiziger.class, id);
            return reiziger;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        try{
            Date date = java.sql.Date.valueOf(datum);
            List<Reiziger> reizigerList = session.createQuery("FROM reiziger WHERE geboortedatum = '" + date+"'",Reiziger.class).getResultList();
            return reizigerList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reiziger> findAll() {
        try{
            List<Reiziger> reizigerList = session.createQuery("FROM reiziger",Reiziger.class).getResultList();
            return reizigerList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
