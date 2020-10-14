package nl.hu.dp.ovchip.interfaces;


import nl.hu.dp.ovchip.domein.OvChipkaart;
import nl.hu.dp.ovchip.domein.Product;

import java.util.List;

public interface ProductDAO {

    boolean save(Product product);

    boolean update(Product product);

    boolean delete(Product product);

    List<Product> findByOVChipkaart(OvChipkaart ovChipkaart);

    List<Product> findAll();
}
