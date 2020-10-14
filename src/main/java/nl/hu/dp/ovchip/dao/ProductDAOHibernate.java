package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domein.OvChipkaart;
import nl.hu.dp.ovchip.domein.Product;
import nl.hu.dp.ovchip.interfaces.ProductDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProductDAOHibernate implements ProductDAO {
    private SessionFactory factory;

    public ProductDAOHibernate(SessionFactory factory){
        this.factory = factory;
    }
    @Override
    public boolean save(Product product) {
        return false;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public boolean delete(Product product) {
        return false;
    }

    @Override
    public List<Product> findByOVChipkaart(OvChipkaart ovChipkaart) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }
}
