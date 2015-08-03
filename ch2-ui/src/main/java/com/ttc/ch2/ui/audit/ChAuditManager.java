package com.ttc.ch2.ui.audit;

import javax.inject.Inject;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ttc.ch2.audit.AuditManager;
import com.ttc.ch2.audit.annotations.AuditInfo;
import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.ui.moduls.tour.mvc.GetBrochureController;
import com.ttc.ch2.ui.moduls.tour.mvc.GetContentRepositoryXmlControler;
import com.ttc.ch2.ui.moduls.tour.mvc.GetOutgoingArchivesController;
import com.ttc.ch2.ui.moduls.tour.mvc.TourMatchStatusViewControler;

@Aspect
public class ChAuditManager {
    
    private static Logger Logger = LoggerFactory.getLogger(ChAuditManager.class);
    
    @Inject
    private AuditManager auditManager;
    
    // audit login errrors
//    @Around("execution(* com.ttc.ch2.api.ccapi.TokenAuthInterceptor.handleRequest(..))")
    public Object auditTokenAuthentication(ProceedingJoinPoint joinPoint) throws Throwable {
        String result = null; 
        try {
            Object[] args = joinPoint.getArgs();
            if (ArrayUtils.getLength(args) >= 3) {
                if (args[2] instanceof Throwable) {
                    Throwable th = (Throwable) args[2];
                    result = th.getMessage();
                    if (StringUtils.isBlank(result)) {
                        result = th.getClass().getName();
                    }
                }
            }
        } catch (Throwable ignored) {
        }
        
        return handle(joinPoint, null, null, result);
    }
    
    // v3
    @Around("execution(* com.ttc.ch2.search.services.SearchServiceImpl.getTourCategories(..))")
    public Object auditGetTourCategories(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint, FunctionType.GET_TOUR_CATEGORIES);
    }
    
    @Around("execution(* com.ttc.ch2.search.services.SearchServiceImpl.getContinentsAndCountriesVisited(..))")
    public Object auditGetContinentsAndCountriesVisited(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint, FunctionType.GET_CONTINENTS_AND_COUNTRIES_VISITED);
    }
    
    @Around("execution(* com.ttc.ch2.search.services.SearchServiceImpl.searchTours(com.ttc.ch2.api.ccapi.v3.SearchToursRequest))")
    public Object auditSearchTours(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint, FunctionType.SEARCH_TOURS_V3);
    }
    
    @Around("execution(* com.ttc.ch2.search.services.SearchServiceImpl.searchToursAggregated(..))")
    public Object handleSearchToursAggregated(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint, FunctionType.SEARCH_TOURS_AGGREGATED);
    }
    
    @Around("execution(* com.ttc.ch2.brox.service.BrochureServiceImpl.getBrochure(..))")
    public Object auditBrochure(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint, FunctionType.GET_BROCHURE);
    }
    
    @Around("execution(* com.ttc.ch2.bl.upload.UploadTourInfoServiceImpl.addApiUploadTourInfoFile(..))")
    public Object auditUpload(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint, FunctionType.UPLOAD_TOUR_INFO);
    }
   
    @Around("execution(* com.ttc.ch2.api.ccapi.v3.weave.V3WeaveService.getTourDetailsFull(..))")
    public Object auditV3(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint, FunctionType.GET_TOUR_DETAILS_FULL);
    }
    
    // v1
    @Around("execution(* com.ttc.ch2.search.services.SearchServiceImpl.searchTours(com.travelcorp.ccapi.SearchTours))")
    public Object auditSearchToursV1(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint, FunctionType.SEARCH_TOURS_V1);
    }
    
  @Around("execution(* com.ttc.ch2.api.ccapi.v1.weave1.V1WeaveService.getTourDetailsFull(..))")
    public Object auditConsolidatedContentAPIv1(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint, FunctionType.TOUR_DETAILS_FULL);
    }
  
    // rest services
    @Around("execution(* com.ttc.ch2.ui.moduls.tour.services.GetBrochureWeaveService.getBrochure(..))")
    public Object auditGetBrochureController(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint, methodName(GetBrochureController.class, joinPoint), FunctionType.EBROCHURE_V1, null, arg(joinPoint, 4));
    }
    
    @Around("execution(* com.ttc.ch2.ui.moduls.tour.services.GetContentRepositoryXmlWeaveService.get*FileNew(..))")
    public Object auditGetContentRepositoryXmlControler_new(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint, methodName(GetContentRepositoryXmlControler.class, joinPoint), FunctionType.CR_VIEW_V3, null, arg(joinPoint, 2));
    }
    @Around("execution(* com.ttc.ch2.ui.moduls.tour.services.GetContentRepositoryXmlWeaveService.get*File(..))")
    public Object auditGetContentRepositoryXmlControler_old(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint, methodName(GetContentRepositoryXmlControler.class, joinPoint), FunctionType.CR_VIEW_V1, null, arg(joinPoint, 2));
    }
    
    @Around("execution(* com.ttc.ch2.ui.moduls.tour.services.GetOutgoingArchivesWeaveService.get*(..))")
    public Object auditGetOutgoingArchivesController(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = methodName(joinPoint);
        
        FunctionType type = null;
        String resource = null;
        if ("getOutgoingArchivesV3".equals(method)) {
            Object[] args = joinPoint.getArgs();
            if (ArrayUtils.getLength(args) >= 3) {
                if ("V1".equals(args[2])) {
                    type = FunctionType.OA_VIEW_V1;
                    
                } else if ("V3".equals(args[2])) {
                    type = FunctionType.OA_VIEW_V3;
                }
            }
            
            resource = arg(joinPoint, 3);
            
        } else if ("getOutgoingArchivesV1".equals(method)) {
            type = FunctionType.OA_VIEW_V1;
            resource = arg(joinPoint, 2);
        }
        
        return handle(joinPoint, methodName(GetOutgoingArchivesController.class, joinPoint), type, null, resource);
    }
    

    // pseudo rest services
    @Around("execution(* com.ttc.ch2.ui.moduls.tour.services.TourRepositoryExtWeaveService.downloadDepartureNewExt(..))")
    public Object auditTourDepartureNewExt(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint, methodName(com.ttc.ch2.ui.moduls.tour.TourDepartureRepositoryNewExt.class, "download()"), FunctionType.CR_VIEW_V3, null);
    }
    
    @Around("execution(* com.ttc.ch2.ui.moduls.tour.services.TourRepositoryExtWeaveService.downloadDepartureOldExt(..))")
    public Object auditTourDepartureOldExt(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint, methodName(com.ttc.ch2.ui.moduls.tour.TourDepartureRepositoryOldExt.class, "download()"), FunctionType.CR_VIEW_V1, null);
    }

    @Around("execution(* com.ttc.ch2.ui.moduls.tour.services.TourRepositoryExtWeaveService.downloadInfoNewExt(..))")
    public Object auditTourInfoNewdExt(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint, methodName(com.ttc.ch2.ui.moduls.tour.TourInfoRepositoryNewExt.class, "download()"), FunctionType.CR_VIEW_V3, null);
    }
    @Around("execution(* com.ttc.ch2.ui.moduls.tour.services.TourRepositoryExtWeaveService.downloadInfoOldExt(..))")
    public Object auditTourInfoOldExt(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint, methodName(com.ttc.ch2.ui.moduls.tour.TourInfoRepositoryOldExt.class, "download()"), FunctionType.CR_VIEW_V1, null);
    }
    
    //UI
    // users
    
    @Around("execution(* com.ttc.ch2.ui.moduls.tour.services.TourMatchStatusWeaveService.generateView(..))")
    public Object auditTourMatchStatusViewControler(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint,  methodName(TourMatchStatusViewControler.class, joinPoint), "Tour Matching", null);
    }
    
    @Around("execution(* com.ttc.ch2.hibernate.security.UserCCAPIDAOImpl.save(..)) || execution(* com.ttc.ch2.hibernate.security.UserCCAPIDAOImpl.remove(..)) || execution(* com.ttc.ch2.hibernate.security.UserGuiDAOImpl.save(..)) || execution(* com.ttc.ch2.hibernate.security.UserGuiDAOImpl.remove(..))")
    public Object auditUser(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint, null);
    }
    
    
    // error handling
    
    @AfterThrowing(pointcut="execution(* com.ttc.ch2.api.ccapi.TokenAuthInterceptor.handleRequest(..))",
            throwing="th")
    public void handleTokenAuthException(JoinPoint joinPoint, Exception th) {
        
        AuditInfo ai = new AuditInfo();
        ai.setObject("authentication error"); 
        
        auditManager.handleAudit(joinPoint, ai, th);
    }
    
    
    @Around("execution(protected * org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor.handleRequestValidationErrors(..))")
    public Object handleSoapFault(ProceedingJoinPoint jp) throws Throwable {
        return handle(jp, null);
    }
    
    private static String arg(JoinPoint jp, int index) {
        if (jp == null) {
            return null;
        }    
        
        Object[] args = jp.getArgs();
        if (0 <= index && index < ArrayUtils.getLength(args)) {
            return ObjectUtils.toString(args[index], null);
        }
        
        return null;
    }
    
/*    
    @Around("execution(* com.ttc.ch2.ui.moduls.tour.services.FileCollectV3WeaveService.download(..))")
    public Object auditFileCollectV3(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint, methodName(FileCollectV3.class, joinPoint), "File Collect V3", null);
    }
    
    @Around("execution(* com.ttc.ch2.ui.moduls.tour.services.FileCollectV1WeaveService.download(..))")
    public Object auditFileCollectV1(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint, methodName(FileCollectV1.class, joinPoint), "File Collect V1", null);
    }

    @Around("execution(* com.ttc.ch2.ui.moduls.tour.services.GetBrochureWeaveService.getBrochureExample(..))")
    public Object auditGetBrochureController_byexample(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint, methodName(GetBrochureController.class, joinPoint), "GetBrochure", null);
    }

    @Around("execution(* com.ttc.ch2.ui.moduls.tour.services.FileUploadWeaveService.handleFileUpload(..))")
    public Object auditHandleFileUpload(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint, methodName(FileUploadController.class, joinPoint), "FileUpload", null);
    }
*/    
    private Object handle(ProceedingJoinPoint joinPoint, Object function) throws Throwable {
        return handle(joinPoint, null, function, null);
    }

    private Object handle(ProceedingJoinPoint joinPoint, String action, Object function, String result) throws Throwable {
        return handle(joinPoint, action, function, result, null);
    }
    
    private Object handle(ProceedingJoinPoint joinPoint, String action, Object function, String result, String resource) throws Throwable {
        if (auditManager != null) {
            AuditInfo ai = new AuditInfo();
            if (StringUtils.isNotBlank(action)) {
                ai.setAction(action);
            }

            if (function != null) {
                if (function instanceof FunctionType) {
                    FunctionType type = (FunctionType) function;
                    ai.setObject(type.getSimpleName());
                    
                } else if (function instanceof Enum) {
                    @SuppressWarnings("rawtypes")
                    Enum en = (Enum) function;
                    ai.setObject(en.name());
                    
                } else {
                    ai.setObject(ObjectUtils.toString(function));
                }
            }
            
            if (StringUtils.isNotBlank(result)) {
                ai.setResource(result);
            }
            
            ai.setResource(resource);

            return auditManager.handleAudit(joinPoint, ai);
        }

        return joinPoint.proceed();
    }
    
    private String methodName(Class<?> clazz, String name) {
        if (clazz == null) {
            return "";
        }
        try {
            return  clazz.getName() +'.' + name;
        } catch (Throwable ignored) {
        }
        return "";    
    }
    
    private String methodName(Class<?> clazz, ProceedingJoinPoint joinPoint) {
        if (clazz == null) {
            return "";
        }
        try {
            Signature signature = joinPoint.getSignature();
            return  clazz.getName() +'.' + signature.getName();
        } catch (Throwable ignored) {
        }
        return "";    
    }
    
    private String methodName(ProceedingJoinPoint joinPoint) {
        try {
            Signature signature = joinPoint.getSignature();
            return signature.getName();
        } catch (Throwable ignored) {
        }
        return "";
    }
}

