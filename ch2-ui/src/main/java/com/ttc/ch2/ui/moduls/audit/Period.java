package com.ttc.ch2.ui.moduls.audit;

import java.util.Calendar;

public enum Period {
    H24(Calendar.HOUR, 1, Calendar.DATE, 1, "{0,time,H}", "{0,date,medium}"),
    D7(Calendar.HOUR, 6, Calendar.DATE, 7, "{0,time,H}", "{0,date,medium}"),
    M1(Calendar.DATE, 1, Calendar.MONTH, 1, "{0,date,d}", "{0,date,MMMM YYYY}");
    
    private int calendarField;
    private int amount;
    private int added;
    private int amountAdded;
    private String format;
    private String groupFormat;
    
    private Period(int calendarField, int amount, int added, int amountAdded, String format, String groupFormat) {
        this.calendarField = calendarField;
        this.amount = amount;
        this.added = added;
        this.amountAdded = amountAdded;
        this.format = format;
        this.groupFormat = groupFormat;
    }
    
    public int calendarField() {
        return calendarField;
    }
    public int amount() {
        return amount;
    }
    public int added() {
        return added;
    }
    public int amountAdded() {
        return amountAdded;
    }
    public String format() {
        return format;
    }
    public String groupFormat() {
        return groupFormat;
    }
    
}
