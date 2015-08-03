package com.ttc.ch2.ui.moduls.audit;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.springframework.beans.BeansException;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Auxhead;
import org.zkoss.zul.Auxheader;
import org.zkoss.zul.Column;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Rows;

import com.ttc.ch2.common.SpringContext;
import com.ttc.ch2.ui.moduls.audit.common.AuditUiManager;
import com.ttc.ch2.ui.moduls.audit.common.MetricsAggregate;

public class MetricsComposer extends SelectorComposer<Panel> {
    private static final long serialVersionUID = -7136522677972390154L;

    @Wire
    Grid metricsGrid;

    @Wire
    Listbox metricsSelect;

    @Wire
    Listbox metricsPeriod;
    
    @Wire
    Auxheader metricsHead;

    @Wire
    Auxheader metricsCalendar;
    
    @Wire
    Auxhead calendarHead;

    @Override
    public void doAfterCompose(Panel comp) throws Exception {
        super.doAfterCompose(comp);
        
        refreshGrid();
    }

    @Listen("onSelect=#metricsSelect; onSelect=#metricsPeriod")
    public void refreshGrid() {
        Period period = period();
        
        List<MetricsAggregate> metrics = metrics(period);

        Rows rows = metricsGrid.getRows();
        rows.getChildren().clear();

        final List<Date> dates = makeHeaders(metrics, period);
        final String value = metricsType();
        
        metricsHead.setColspan(dates.size() + 2);

        metricsGrid.setRowRenderer(new RowRenderer<MetricsAggregate>() {
            @Override
            public void render(Row row, MetricsAggregate data, int index) throws Exception {
                if (data instanceof MetricsAggregate) {
                    row.appendChild(new Label("" + (index+1)));

                    MetricsAggregate agg = (MetricsAggregate) data;
                    row.appendChild(new Label(agg.getHost()));

                    Map<Date, Map<String, Object>> d = agg.getData();

                    for (Date date : dates) {
                        Map<String, Object> map = d.get(date);

                        if (map == null) {
                            row.appendChild(new Label());
                        } else {
                            Object object = map.get(value);

                            if (object instanceof Number) {
                                String formatted;

                                switch(value) {
                                case"THREADCOUNT": 
                                case"MEMPHYSICALFREE":
                                case"MEMPHYSICALTOTAL": 
                                case"MEMVIRTUALCOMMITTED": 
                                case"SWAPSPACEFREE":
                                case"SWAPSPACETOTAL":
                                    formatted = MessageFormat.format("{0,number,integer}", object);
                                    break;

                                case"PROCESS_CPU_LOAD": 
                                case"SYSTEM_CPU_LOAD":
                                case"MEMPHYSICALUSEDPERC": 
                                case"MEMVIRTUALCOMMITEDUSEDPERC": 
                                case"SWAPUSEDPERC":
                                    formatted = MessageFormat.format("{0,number,#0,00%}", object);
                                    break;

                                case"UPTIME": 
                                    formatted = DurationFormatUtils.formatDuration(((Number) object).longValue(), "d HH:mm", false);
                                    break;

                                default:
                                    formatted = MessageFormat.format("{0}", object);
                                    break;
                                }

                                Label label = new Label(formatted);
                                label.setSclass("audit_font");
//                                label.setStyle("font-size:8px;");

                                row.appendChild(label);

                            } else {
                                row.appendChild(new Label());
                            }
                        }
                    }
                }
            }
        });

        metricsGrid.setModel(new ListModelList<>(metrics));
    }

    private List<MetricsAggregate> metrics(Period period) {
        try {
            AuditUiManager auditManager = SpringContext.getApplicationContext().getBean(AuditUiManager.class);
            List<MetricsAggregate> metrics = auditManager.readMetrics(period);
            return metrics;
        } catch (BeansException ignored) {
        }
        return Collections.emptyList();
    }

    private Date lastDate (List<MetricsAggregate> metrics) {
        if (metrics == null) {
            return null;
        }

        Date lastDate = null;
        for (MetricsAggregate agg : metrics) {
            Map<Date, Map<String, Object>> data = agg.getData();
            if (data == null) {
                continue;
            }
            
            for (Date date : data.keySet()) {
                if (lastDate == null || date.after(lastDate)) {
                    lastDate = date;
                }
            }
        }
        return lastDate;
    }
    
    
    @SuppressWarnings("deprecation")
    private List<Date> makeHeaders(List<MetricsAggregate> metrics, Period period) {    
        Date last = lastDate(metrics);
        
        Date now = now(period);
        if (last == null || last.before(now)) {
            last = now;
        } else {
            Date ahead = DateUtils.add(now, period.calendarField(), period.amount() * 2);
            
            if (ahead.before(last)) {
                last = ahead;
            }
        }
        metricsGrid.getColumns().getChildren().clear();
        metricsGrid.getColumns().appendChild(new Column("No."));
        metricsGrid.getColumns().appendChild(new Column("Host"));

        ArrayList<Date> list = new ArrayList<Date>();
        for (Date d = last; !d.before(DateUtils.add(now, period.added(), -period.amountAdded())); d = DateUtils.add(d, period.calendarField(), -period.amount())) {
            list.add(d);
        }
        
        for (Date date : list) {
            String text = MessageFormat.format(period.format(), date);
            Column column = new Column(text);
            metricsGrid.getColumns().appendChild(column);
        }
        
        calendarHead.getChildren().clear();
        
        Auxheader firstHeader = new Auxheader();
        firstHeader.setColspan(2);
        calendarHead.appendChild(firstHeader);
        
        Map<Date,Integer> headers = new TreeMap<Date, Integer>();
        for (Date date : list) {
            Date truncated = DateUtils.truncate(date, period.added());
            Integer counter = headers.get(truncated);
            if (counter == null) {
                counter = 0;
            }
            
            headers.put(truncated, counter + 1);
        }
        
        List<Date> revertedDates = new ArrayList<Date>(headers.keySet());
        Collections.reverse(revertedDates);
        for (Date date : revertedDates) {
            String text = MessageFormat.format(period.groupFormat(), date);
            Auxheader header = new Auxheader(text);
            Integer counter = headers.get(date);
            header.setColspan(counter);
            calendarHead.appendChild(header);
        }
        
        return list;
    }

    private Date now(Period period) {
        Date d = DateUtils.truncate(new Date(), period.calendarField());
        if (period.calendarField() == Calendar.HOUR && period.amount() != 1) {
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            int h = c.get(Calendar.HOUR);
            int hours = period.amount();
            h = (h / hours) * hours;
            c.set(Calendar.HOUR, h);
            d = c.getTime();
        }
        
        return d;
    }

    private String metricsType() {
        Listitem selectedM = metricsSelect.getSelectedItem();
        final String value = StringUtils.upperCase(ObjectUtils.toString(selectedM.getValue(), ""));
        return value;
    }

    private Period period() {
        Listitem selected = metricsPeriod.getSelectedItem();
        String string = ObjectUtils.toString(selected.getValue(), "");
        
        try {
            return Period.valueOf(string);
        } catch (Exception ignored) {
        }
        return Period.H24;
    }

}
