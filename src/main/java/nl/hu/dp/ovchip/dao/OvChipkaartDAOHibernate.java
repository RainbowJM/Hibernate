package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domein.OvChipkaart;
import nl.hu.dp.ovchip.domein.Reiziger;
import nl.hu.dp.ovchip.interfaces.OvChipkaartDAO;
import nl.hu.dp.ovchip.interfaces.ProductDAO;
import nl.hu.dp.ovchip.interfaces.ReizigerDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.List;

public class OvChipkaartDAOHibernate implements OvChipkaartDAO {
    private Session session;
    private ReizigerDAO rdao;
    private ProductDAO pdao;

    public OvChipkaartDAOHibernate(Session session){
        this.session = session;
    }

    public void setRdao(ReizigerDAO rdao) {
        this.rdao = rdao;
    }

    public void setPdao(ProductDAO pdao){
        this.pdao = pdao;
    }

    @Override
    public boolean save(OvChipkaart ovChipkaart) {
        try {
            Transaction trans = session.beginTransaction();
            session.save(ovChipkaart);
            trans.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(OvChipkaart ovChipkaart) {
        try{
            Transaction trans = session.beginTransaction();
            session.update(ovChipkaart);
            trans.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(OvChipkaart ovChipkaart) {
        try{
            Transaction trans = session.beginTransaction();
            session.delete(ovChipkaart);
            trans.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public OvChipkaart findByNummer(int kaart_nummer) {
        try{
            OvChipkaart ovChipkaart = session.get(OvChipkaart.class, kaart_nummer);
            return ovChipkaart;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OvChipkaart> findByReiziger(Reiziger reiziger) {
        try{
            List<OvChipkaart> ovChipkaartList = session.createQuery("FROM ov_chipkaart WHERE reiziger_id = '" + reiziger.getReiziger_id()+"'",OvChipkaart.class).getResultList();
            return ovChipkaartList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OvChipkaart> findAll() {
        try{
            List<OvChipkaart> ovChipkaartList = session.createQuery("FROM ov_chipkaart ",OvChipkaart.class).getResultList();
            return ovChipkaartList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
