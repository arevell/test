package com.ttc.ch2.ui.moduls.audit;

import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.event.PagingEvent;

import com.ttc.ch2.common.SpringContext;
import com.ttc.ch2.ui.moduls.audit.common.AuditUiManager;
import com.ttc.ch2.ui.moduls.audit.common.EventsModel;

@Component("EventsDetails")
@Scope("request")
public class EventsDetails {
    private static Logger logger = LoggerFactory.getLogger(EventsDetails.class);
    
    private EventsModel auditEvents;
    private String eventsPeriod = Period.H24.name();

    private String eventsUserFilter;
    private String eventsActionFilter;
    private String eventsFunctionFilter;
    private String eventsHostFilter;
    private ListModel<String> eventsUserComboxModel;
    private ListModel<String> eventsActionComboxModel;
    private ListModel<String> eventsFunctionComboxModel;
    private ListModel<String> eventsHostComboxModel;
    
    @Init
    public void init() {
        logger.trace("start");
        initEvents();
        logger.trace("end");
    }
    
    private void initEvents() {

        readEvents(0);
        AuditUiManager auditManager = getAuditManager();
        if (auditManager == null) {
            return;
        }
        
        List<String> userFilter = auditEvents.userFilter();
        eventsUserComboxModel = new ListModelList<>( userFilter);
        if (!modelContainsString(userFilter, eventsUserFilter)) {
            eventsUserFilter = null;
        }
        
        List<String> actionFilter = auditEvents.actionFilter();
        eventsActionComboxModel = new ListModelList<>( actionFilter);
        if (!modelContainsString(actionFilter, eventsActionFilter)) {
            eventsActionFilter = null;
        }
        
        List<String> functionFilter = auditEvents.functionFilter();
        eventsFunctionComboxModel = new ListModelList<>( functionFilter);
        if (!modelContainsString(functionFilter, eventsFunctionFilter)) {
            eventsFunctionFilter = null;
        }
        
        List<String> hostFilter = auditEvents.hostFilter();
        eventsHostComboxModel = new ListModelList<>( hostFilter);
        if (!modelContainsString(hostFilter, eventsHostFilter)) {
            eventsHostFilter = null;
        }
    }

    private AuditUiManager getAuditManager() {
        try {
            return SpringContext.getApplicationContext().getBean(AuditUiManager.class);
        } catch (BeansException e) {
        }
        return null;

    }

    private boolean modelContainsString(List<String> model, String pattern) {
        if (model == null) {
            return false;
        }
        
        for (String string : model) {
            if (StringUtils.contains(string, pattern)) {
                return true;
            }
        }
        
        return false;
    }

    @Command("onPeriodEvents")
    @NotifyChange({ "auditEvents", "eventsUserFilter", "eventsActionFilter", "eventsFunctionFilter", "eventsHostFilter", "eventsUserComboxModel",
            "eventsActionComboxModel", "eventsFunctionComboxModel", "eventsHostComboxModel" })
    public void onPeriodEvents() {
        auditEvents = null;
        initEvents();    
    }
        
    @Command("onPagingAuditEvents")
    @NotifyChange("auditEvents")
    public void onPagingAuditEvents(BindContext ctx) {
        logger.trace("start");
        
        PagingEvent event = (PagingEvent) ctx.getTriggerEvent();
        readEvents(event.getActivePage());
        
        logger.trace("end");
    }

    private void readEvents(int page) {
        if(auditEvents == null) {
            AuditUiManager auditManager = getAuditManager();
            auditEvents = new EventsModel(auditManager, eventsPeriod(), page, eventsUserFilter, eventsActionFilter, eventsFunctionFilter, eventsHostFilter);
        }
        
        auditEvents.setActivePage(page);
    }
    
    @Command("onFilterEvents")
    @NotifyChange({"auditEvents"})
    public void onFilterEvents() {
        auditEvents = null;
        readEvents(0);
    }
    
    private Period eventsPeriod() {
        String string = ObjectUtils.toString(eventsPeriod);
        
        try {
            return Period.valueOf(string);
        } catch (Exception ignored) {
        }
        return Period.H24;
    }

    public EventsModel getAuditEvents() {
        return auditEvents;
    }

    public void setAuditEvents(EventsModel auditEvents) {
        this.auditEvents = auditEvents;
    }

    public String getEventsPeriod() {
        return eventsPeriod;
    }

    public void setEventsPeriod(String eventsPeriod) {
        this.eventsPeriod = eventsPeriod;
    }

    public String getEventsUserFilter() {
        return eventsUserFilter;
    }

    public void setEventsUserFilter(String eventsUserFilter) {
        this.eventsUserFilter = eventsUserFilter;
    }

    public String getEventsActionFilter() {
        return eventsActionFilter;
    }

    public void setEventsActionFilter(String eventsActionFilter) {
        this.eventsActionFilter = eventsActionFilter;
    }

    public String getEventsFunctionFilter() {
        return eventsFunctionFilter;
    }

    public void setEventsFunctionFilter(String eventsFunctionFilter) {
        this.eventsFunctionFilter = eventsFunctionFilter;
    }

    public String getEventsHostFilter() {
        return eventsHostFilter;
    }

    public void setEventsHostFilter(String eventsHostFilter) {
        this.eventsHostFilter = eventsHostFilter;
    }

    public ListModel<String> getEventsHostComboxModel() {
        return eventsHostComboxModel;
    }

    public void setEventsHostComboxModel(ListModel<String> eventsHostComboxModel) {
        this.eventsHostComboxModel = eventsHostComboxModel;
    }

    public void setEventsUserComboxModel(ListModel<String> eventsUserComboxModel) {
        this.eventsUserComboxModel = eventsUserComboxModel;
    }

    public void setEventsActionComboxModel(ListModel<String> eventsActionComboxModel) {
        this.eventsActionComboxModel = eventsActionComboxModel;
    }

    public void setEventsFunctionComboxModel(ListModel<String> eventsFunctionComboxModel) {
        this.eventsFunctionComboxModel = eventsFunctionComboxModel;
    }

    public ListModel<String> getEventsUserComboxModel() {
        return eventsUserComboxModel;
    }

    public ListModel<String> getEventsActionComboxModel() {
        return eventsActionComboxModel;
    }

    public ListModel<String> getEventsFunctionComboxModel() {
        return eventsFunctionComboxModel;
    }
}
