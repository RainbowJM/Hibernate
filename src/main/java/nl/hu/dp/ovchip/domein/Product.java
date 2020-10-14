package nl.hu.dp.ovchip.domein;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity (name = "product")
public class Product {
    @Id
    private int product_nummer;
    private String naam;
    private String beschrijving;
    private int prijs;

//    @Transient
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ov_chipkaart_product",
            joinColumns = {@JoinColumn(name = "product_nummer")},
            inverseJoinColumns = {@JoinColumn(name = "kaart_nummer")})
    private List<OvChipkaart> ovChipkaarten = new ArrayList<>();

    public Product(int product_nummer, String naam, String beschrijving, int prijs) {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public Product(){}

    public void addOvChipkaart(OvChipkaart ov){
        ovChipkaarten.add(ov);

    }

    public void deleteOvChipkaart(OvChipkaart ov){
        ovChipkaarten.remove(ov);
    }

    //getters
    public int getProduct_nummer() {
        return product_nummer;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public int getPrijs() {
        return prijs;
    }

    public List<OvChipkaart> getOvChipkaarten() {
        return ovChipkaarten;
    }

    //setters
    public void setProduct_nummer(int product_nummer) {
        this.product_nummer = product_nummer;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }

    public void setOvChipkaarten(List<OvChipkaart> ovChipkaarten) {
        this.ovChipkaarten = ovChipkaarten;
    }

    @Override
    public String toString() {
        return "Product: " +
                "product_nummer = " + product_nummer +
                ", naam = " + naam +
                ", beschrijving = " + beschrijving +
                ", prijs = " + prijs ;
    }
}

