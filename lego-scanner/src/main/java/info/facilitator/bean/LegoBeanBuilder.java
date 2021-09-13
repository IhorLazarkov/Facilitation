package info.facilitator.bean;

import java.time.LocalDateTime;
import java.util.Date;

public class LegoBeanBuilder {

    private LegoBean bean;

    public LegoBeanBuilder createBean(){
        this.bean = new LegoBean();
        this.bean.setDate(LocalDateTime.now().toString());
        return this;
    }

    public LegoBeanBuilder setLegoName(String name){
        bean.setLegoName(name);
        return this;
    }

    public LegoBeanBuilder setPrice(String price){
        bean.setPrice(price);
        return this;
    }

    public LegoBeanBuilder setSalePrice(String salePrice){
        bean.setPriceForSale(salePrice);
        return this;
    }

    public LegoBean build(){
        return this.bean;
    }
}
