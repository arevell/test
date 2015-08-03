package com.ttc.ch2.ui.moduls.audit.common;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class MetricsAggregate implements Serializable {
    private static final long serialVersionUID = 659411478139983474L;

    String host;

    Map<Date, Map<String, Object>> data = new TreeMap<>();

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Map<Date, Map<String, Object>> getData() {
        return data;
    }

    public void setData(Map<Date, Map<String, Object>> data) {
        this.data = data;
    }
}
