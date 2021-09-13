package info.facilitator.bean;

import javax.persistence.*;
import java.util.Objects;

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

    public long getId() {
        return id;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LegoBean legoBean = (LegoBean) o;
        return Objects.equals(id, legoBean.id) && Objects.equals(legoName, legoBean.legoName) && Objects.equals(price, legoBean.price) && Objects.equals(priceForSale, legoBean.priceForSale) && Objects.equals(date, legoBean.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(legoName, price, priceForSale, date);
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
