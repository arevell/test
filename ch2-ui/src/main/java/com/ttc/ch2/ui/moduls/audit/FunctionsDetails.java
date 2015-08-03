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
import com.ttc.ch2.ui.moduls.audit.common.FunctionsModel;

@Component("FunctionsDetails")
@Scope("request")
public class FunctionsDetails {
    private static Logger logger = LoggerFactory.getLogger(FunctionsDetails.class);
    private FunctionsModel functions;
    private String functionsPeriod = Period.H24.name();
    
    private String functionsUserFilter;
    private String functionsActionFilter;
    private String functionsFunctionFilter;
    private String functionsHostFilter;
    private ListModel<String> functionsUserComboxModel;
    private ListModel<String> functionsActionComboxModel;
    private ListModel<String> functionsFunctionComboxModel;
    private ListModel<String> functionsHostComboxModel;
    
    @Init
    public void init() {
        logger.trace("start");
        initFunctions();
        logger.trace("end");
    }
    
    private void initFunctions() {
        readFunctions(0);

        AuditUiManager auditManager = getAuditManager();
        if (auditManager == null) {
            return;
        }
        
        List<String> userFilter = functions.readFunctionsUserFilter();
        functionsUserComboxModel = new ListModelList<>( userFilter);
        if (!modelContainsString(userFilter, functionsUserFilter)) {
            functionsUserFilter = null;
        }
        
        List<String> actionFilter = functions.readFunctionsActionFilter();
        functionsActionComboxModel = new ListModelList<>( actionFilter);
        if (!modelContainsString(actionFilter, functionsActionFilter)) {
            functionsActionFilter = null;
        }
        
        List<String> functionFilter = functions.readFunctionsFunctionFilter();
        functionsFunctionComboxModel = new ListModelList<>( functionFilter);
        if (!modelContainsString(functionFilter, functionsFunctionFilter)) {
            functionsFunctionFilter = null;
        }
        
        List<String> hostFilter = functions.readFunctionsHostFilter();
        functionsHostComboxModel = new ListModelList<>( hostFilter);
        if (!modelContainsString(hostFilter, functionsHostFilter)) {
            functionsHostFilter = null;
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

    @Command("onPeriodFunctions")
    @NotifyChange({ "functions", "functionsUserComboxModel", "functionsActionComboxModel", "functionsFunctionComboxModel", "functionsHostComboxModel",
            "functionsUserFilter", "functionsActionFilter", "functionsFunctionFilter", "functionsHostFilter" })
    public void onPeriodFunction() {
        functions = null;
        initFunctions();
    }
    
    @Command("onPagingFunctions")
    @NotifyChange("functions")
    public void onPagingFunctions(BindContext ctx) {
        logger.trace("start");
        
        PagingEvent event = (PagingEvent) ctx.getTriggerEvent();
        readFunctions(event.getActivePage());
        
        logger.trace("end");
    }
    
    private void readFunctions(int page) {
        if (functions == null) { 
            AuditUiManager auditManager = getAuditManager();
            functions = new FunctionsModel(auditManager, functionsPeriod(), page, functionsUserFilter, functionsActionFilter, functionsFunctionFilter, functionsHostFilter);
        }
        
        functions.setActivePage(page);
    }
    
    @Command("onFilterFunctions")
    @NotifyChange({"functions"})
    public void onFilterFunctions() {
        functions = null;
        readFunctions(0);
    }
    
    private Period functionsPeriod() {
        String string = ObjectUtils.toString(functionsPeriod);
        
        try {
            return Period.valueOf(string);
        } catch (Exception ignored) {
        }
        return Period.H24;
    }

    public FunctionsModel getFunctions() {
        return functions;
    }

    public void setFunctions(FunctionsModel functions) {
        this.functions = functions;
    }

    public String getFunctionsPeriod() {
        return functionsPeriod;
    }

    public void setFunctionsPeriod(String functionsPeriod) {
        this.functionsPeriod = functionsPeriod;
    }

    public String getFunctionsUserFilter() {
        return functionsUserFilter;
    }

    public void setFunctionsUserFilter(String functionsUserFilter) {
        this.functionsUserFilter = functionsUserFilter;
    }

    public String getFunctionsActionFilter() {
        return functionsActionFilter;
    }

    public void setFunctionsActionFilter(String functionsActionFilter) {
        this.functionsActionFilter = functionsActionFilter;
    }

    public String getFunctionsFunctionFilter() {
        return functionsFunctionFilter;
    }

    public void setFunctionsFunctionFilter(String functionsFunctionFilter) {
        this.functionsFunctionFilter = functionsFunctionFilter;
    }

    public String getFunctionsHostFilter() {
        return functionsHostFilter;
    }

    public void setFunctionsHostFilter(String functionsHostFilter) {
        this.functionsHostFilter = functionsHostFilter;
    }

    public ListModel<String> getFunctionsUserComboxModel() {
        return functionsUserComboxModel;
    }

    public void setFunctionsUserComboxModel(ListModel<String> functionsUserComboxModel) {
        this.functionsUserComboxModel = functionsUserComboxModel;
    }

    public ListModel<String> getFunctionsActionComboxModel() {
        return functionsActionComboxModel;
    }

    public void setFunctionsActionComboxModel(ListModel<String> functionsActionComboxModel) {
        this.functionsActionComboxModel = functionsActionComboxModel;
    }

    public ListModel<String> getFunctionsFunctionComboxModel() {
        return functionsFunctionComboxModel;
    }

    public void setFunctionsFunctionComboxModel(ListModel<String> functionsFunctionComboxModel) {
        this.functionsFunctionComboxModel = functionsFunctionComboxModel;
    }

    public ListModel<String> getFunctionsHostComboxModel() {
        return functionsHostComboxModel;
    }

    public void setFunctionsHostComboxModel(ListModel<String> functionsHostComboxModel) {
        this.functionsHostComboxModel = functionsHostComboxModel;
    }
}
