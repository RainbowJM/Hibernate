package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domein.OvChipkaart;
import nl.hu.dp.ovchip.domein.Product;
import nl.hu.dp.ovchip.interfaces.OvChipkaartDAO;
import nl.hu.dp.ovchip.interfaces.ProductDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDAOHibernate implements ProductDAO {
    private Session session;
    private OvChipkaartDAO odao;

    public ProductDAOHibernate(Session session){
        this.session = session;
    }

    public void setOdao(OvChipkaartDAO odao){this.odao = odao;}

    @Override
    public boolean save(Product product) {
        try {
            Transaction trans = session.beginTransaction();
            session.save(product);
            trans.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Product product) {
        try{
            Transaction trans = session.beginTransaction();
            session.update(product);
            trans.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        try{
            Transaction trans = session.beginTransaction();
            session.delete(product);
            trans.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Product> findByOVChipkaart(OvChipkaart ovChipkaart) {
        try{
            List<Product> productList = session.createQuery("FROM product p LEFT JOIN ov_chipkaart_product ocp ON p.product_nummer = ocp.product_nummer WHERE kaart_nummer = '" +ovChipkaart.getKaart_nummer()+"'",Product.class).getResultList();
            return productList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> findAll() {
        try{
            List<Product> productList = session.createQuery("FROM product ",Product.class).getResultList();
            return productList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
