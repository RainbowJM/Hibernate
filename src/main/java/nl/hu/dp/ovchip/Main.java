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
        OvChipkaartDAOHibernate odao = new OvChipkaartDAOHibernate(getSession());
        ProductDAOHibernate pdao = new ProductDAOHibernate(getSession());


        //relation
        rdao.setAdao(adao);
        rdao.setOdao(odao);
        odao.setRdao(rdao);
        odao.setPdao(pdao);
        pdao.setOdao(odao);

        testDAOHibernate(rdao,adao,odao,pdao);
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

    private static  void testDAOHibernate(ReizigerDAO rdao, AdresDAO adao, OvChipkaartDAO odao, ProductDAO pdao){
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
            Reiziger sietske = new Reiziger(8, "S", "", "Boers", java.sql.Date.valueOf(gbdatum1));
//            rdao.save(sietske);
//             xrdao.delete(sietske);

            String gbdatum2 = "2000-07-22";
            Reiziger ellen = new Reiziger(9, "E", "L", "Lopez", java.sql.Date.valueOf(gbdatum2));
            rdao.save(ellen);
//        rdao.delete(ellen);

            System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
            System.out.println("----------------------------");

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
            System.out.println("----------------------------");

            // Find by id
            Reiziger reizigerId = rdao.findById(1);
            System.out.println("[Test] ReizigerDAO.findById() geeft: " + reizigerId);
            System.out.println();
            System.out.println("----------------------------");

            // Find by birthday
            List<Reiziger> reizigerG = rdao.findByGbdatum(gbdatum1);
            System.out.println("[Test] ReizigerDAo.findByGbdatum() geeft \n" + reizigerG);

            System.out.println("\n---------- Test AdresDAO -------------");

            // Haal alle reizigers op uit de database
            List<Adres> adressen = adao.findAll();
            System.out.println("[Test] AdresDAO.findAll() geeft de volgende adressen:");
            for (Adres a : adressen) {
                System.out.println(a);
            }
            System.out.println();

            // Maak een nieuwe adressen aan en persisteer deze in de database
            Adres a1 = new Adres(1, "3812RK", "13", "heidelberglaan", "Utrecht", sietske);
//            adao.save(a1);
//            adao.delete(a1);

            Adres a2 = new Adres(2, "3832EK", "3", "laan", "Utrecht", ellen);
//            adao.save(a2);
//            adao.delete(a2);

            System.out.print("[Test] Eerst " + adressen.size() + " adressen, na AdresDAO.save() ");
            System.out.println("----------------------------");

            // Haal alle reizigers op uit de database
            List<Adres> nAdressen = adao.findAll();
            System.out.println("[Test] AdresDAO.findAll() geeft de volgende adressen:");
            for (Adres a : nAdressen) {
                System.out.println(a);
            }
            System.out.println();

            adressen = adao.findAll();
            System.out.println(adressen.size() + " reizigers\n");

            // Delete de net aangemaakt adres
//            adao.delete(a2);
            adressen = adao.findAll();
            System.out.println(adressen.size() + " adressen na het verwijderen van de net aangemaakt adres\n");
            System.out.println("----------------------------");

            System.out.println("\n---------- Test OvChipkaartDAO -------------");

            // Haal alle ovchipkaarten op uit de database
            List<OvChipkaart> ovChipkaarten = odao.findAll();
            System.out.println("[Test] OvChipkaartDAO.findAll() geeft de volgende ovchipkaarten:");
            for (OvChipkaart o : ovChipkaarten) {
                System.out.println(o);
            }
            System.out.println();

            // Maak een nieuwe ovchipkaarten aan en persisteer deze in de database
            String gdatum1 = "2030-03-14";
            OvChipkaart o1 = new OvChipkaart(10977, java.sql.Date.valueOf(gdatum1),1,15,ellen);
//            odao.save(o1);
            //odao.delete(o1);

            String gdatum2 = "2050-03-21";
            OvChipkaart o2 = new OvChipkaart(24535, java.sql.Date.valueOf(gdatum2),2,40,sietske);
//            odao.save(o2);
            //odao.delete(o2);

            System.out.print("[Test] Eerst " + ovChipkaarten.size() + " ovchipkaarten, na OvChipkaartDAO.save() ");
            System.out.println("----------------------------");

            // Haal alle reizigers op uit de database
            List<OvChipkaart> nOvChipkaarten = odao.findAll();
            System.out.println("[Test] OvChipkaartDAO.findAll() geeft de volgende ovchipkaarten:");
            for (OvChipkaart o : nOvChipkaarten) {
                System.out.println(o);
            }
            System.out.println();

            ovChipkaarten = odao.findAll();
            System.out.println(ovChipkaarten.size() + " ovchipkaarten\n");

            // Delete de net aangemaakt adres
            odao.delete(o2);
            ovChipkaarten = odao.findAll();
            System.out.println(ovChipkaarten.size() + " ovchipkaarten na het verwijderen van de net aangemaakt ovchipkaart\n");
            System.out.println("----------------------------");

            System.out.println("\n---------- Test ProductDAO -------------");

            // Haal alle producten op uit de database
            List<Product> products = pdao.findAll();
            System.out.println("[Test] ProductDAO.findAll() geeft de volgende producten:");
            for (Product p : products) {
                System.out.println(p);
            }
            System.out.println();

            System.out.print("[Test] Eerst " + products.size() + " producten, na ProductDAO.save() ");
            System.out.println("\n----------------------------");

            // Maak een nieuwe producten aan en persisteer deze in de database
            Product p1  = new Product(7,"Peanutbutter", "snack on the train",2.50);
//            pdao.save(p1);
//            pdao.delete(p1);

            Product p2 = new Product(8,"Jelly","snack on the train",0.50);
//           pdao.save(p2);
//           pdao.delete(p2);

            Product p3 = new Product(9,"Dal Voordeel 40%","40% korting buiten de spits en in het weekeind.",50);
            pdao.save(p3);

            p3.addOvChipkaart(o1);

            // Haal alle reizigers op uit de database
            List<Product> products1 = pdao.findAll();
            System.out.println("[Test] ProductDAO.findAll() geeft de volgende producten:");
            for (Product p : products1) {
                System.out.println(p);
            }
            System.out.println();

        } finally {
            session.close();
        }
    }
}

