package info.facilitator.bean;

import javax.persistence.*;

@Entity
@Table(name = "Lego")
public class LegoBean {

    @Id
    @Column(name = "Lego_Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String legoName;
    private String price;
    private String priceForSale;
    private String date;

    public String getLegoName() {
        return legoName;
    }

    public void setLegoName(String legoName) {
        this.legoName = legoName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceForSale() {
        return priceForSale;
    }

    public void setPriceForSale(String priceForSale) {
        this.priceForSale = priceForSale;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "LegoBean{" +
                "id=" + id +
                ", legoName='" + legoName + '\'' +
                ", price='" + price + '\'' +
                ", priceForSale='" + priceForSale + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
