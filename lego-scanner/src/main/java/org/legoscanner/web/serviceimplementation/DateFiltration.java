package org.legoscanner.web.serviceimplementation;

import info.facilitator.bean.LegoBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFiltration {

    public enum DateFormat {
        DATE_FORMAT("yyyy-mm-dd");

        private String value;

        DateFormat(String v) {
            this.value = v;
        }

        public String getValue() {
            return value;
        }
    }

    private String date;

    public DateFiltration(String date) {
        this.date = date;
    }

    public boolean fromDateOnward(LegoBean legoBean) {
        return asDate(date).compareTo(asDate(legoBean.getDate())) <= 0;
    }

    public boolean fromDateBackward(LegoBean legoBean) {
        return asDate(date).compareTo(asDate(legoBean.getDate())) >= 0;
    }

    private Date asDate(String date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DateFormat.DATE_FORMAT.getValue());
        try {
            return dateFormatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
}
