package com.ttc.ch2.ui.moduls.audit.common;

import java.io.Serializable;
import java.util.Date;

public class LoggingEvent implements Serializable {
    private static final long serialVersionUID = 659411478139983474L;

    String user;
    Date start;
    Date end;
    String address;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
