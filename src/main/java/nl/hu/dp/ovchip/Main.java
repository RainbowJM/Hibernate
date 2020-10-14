package nl.hu.dp.ovchip;

import nl.hu.dp.ovchip.dao.AdresDAOHibernate;
import nl.hu.dp.ovchip.dao.OvChipkaartDAOHibernate;
import nl.hu.dp.ovchip.dao.ProductDAOHibernate;
import nl.hu.dp.ovchip.dao.ReizigerDAOHibernate;
import nl.hu.dp.ovchip.domein.Adres;
import nl.hu.dp.ovchip.domein.OvChipkaart;
import nl.hu.dp.ovchip.domein.Product;
import nl.hu.dp.ovchip.domein.Reiziger;
import nl.hu.dp.ovchip.interfaces.AdresDAO;
import nl.hu.dp.ovchip.interfaces.OvChipkaartDAO;
import nl.hu.dp.ovchip.interfaces.ProductDAO;
import nl.hu.dp.ovchip.interfaces.ReizigerDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.SQLException;
import java.util.List;

/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 *
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;

    static {
        try {
            // Create a Hibernate session factory
            Configuration configure = new Configuration();
            configure.configure("hibernate.cfg.xml");
            factory = configure.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retouneer een Hibernate session.
     *
     * @return Hibernate session
     * @throws HibernateException
     */
    private static Session getSession() throws HibernateException {
        return factory.openSession();
    }

    public static void main(String[] args) throws SQLException {
        testFetchAll();

        //DAO
        ReizigerDAOHibernate rdao = new ReizigerDAOHibernate(getSession());
        AdresDAOHibernate adao = new AdresDAOHibernate(getSession());
//        OvChipkaartDAOHibernate odao = new OvChipkaartDAOHibernate(getSession());
//        ProductDAOHibernate pdao = new ProductDAOHibernate(getSession());


        //relation
        rdao.setAdao(adao);
//        rdao.setOdao(odao);
//        odao.setRdao(rdao);
//        odao.setPdao(pdao);
//        pdao.setOdao(odao);

        testDAOHibernate(rdao);
    //,adao,odao,pdao);
    }

    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */
    private static void testFetchAll() {
        Session session = getSession();
        try {
            Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                Query query = session.createQuery("from " + entityType.getName());

                System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
                System.out.println();
            }
        } finally {
            session.close();
        }
    }

    private static  void testDAOHibernate(ReizigerDAO rdao){//, AdresDAO adao, OvChipkaartDAO odao, ProductDAO pdao){
        Session session = getSession();
        try{
            System.out.println("\n---------- Test ReizigerDAO -------------");

            // Haal alle reizigers op uit de database
            List<Reiziger> reizigers = rdao.findAll();
            System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
            for (Reiziger r : reizigers) {
                System.out.println(r);
            }
            System.out.println("------");

            // Maak een nieuwe reizigers aan en persisteer deze in de database
            String gbdatum1 = "1981-03-14";
//            Reiziger sietske = new Reiziger(8, "S", "", "Boers", java.sql.Date.valueOf(gbdatum1));
//            rdao.save(sietske);
//             xrdao.delete(sietske);

            String gbdatum2 = "2000-07-22";
            Reiziger ellen = new Reiziger(9, "E", "L", "Lopez", java.sql.Date.valueOf(gbdatum2));
            rdao.save(ellen);
//        rdao.delete(ellen);

            System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
            System.out.println("\n----------------------------");

            // Haal alle reizigers op uit de database
            List<Reiziger> nReizigers = rdao.findAll();
            System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
            for (Reiziger r : nReizigers) {
                System.out.println(r);
            }
            System.out.println();

//            reizigers = rdao.findAll();
//            System.out.println(reizigers.size() + " reizigers\n");

            // Delete de net aangemaakt reiziger
            rdao.delete(ellen);
            reizigers = rdao.findAll();
            System.out.println(reizigers.size() + " reizigers na het verwijderen van de net aangemaakt reiziger\n");
            System.out.println("\n----------------------------");

            // Find by id
            Reiziger reizigerId = rdao.findById(1);
            System.out.println("[Test] ReizigerDAO.findById() geeft: " + reizigerId);
            System.out.println();
            System.out.println("\n----------------------------");

            // Find by birthday
            List<Reiziger> reizigerG = rdao.findByGbdatum(gbdatum1);
            System.out.println("[Test] ReizigerDAo.findByGbdatum() geeft \n" + reizigerG);

        } finally {
            session.close();
        }
    }
}

