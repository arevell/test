package com.ttc.ch2.ui.audit;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.common.base.Joiner;
import com.travcorp.contenthub.tour_data._2010._11._1.TourDetailsFull;
import com.travelcorp.ccapi.ArrayOfString;
import com.travelcorp.ccapi.SearchTours;
import com.ttc.ch2.api.ccapi.v3.GetBrochureRequest;
import com.ttc.ch2.api.ccapi.v3.GetContinentsAndCountriesVisitedRequest;
import com.ttc.ch2.api.ccapi.v3.GetTourCategoriesRequest;
import com.ttc.ch2.api.ccapi.v3.GetTourDetailsFullRequest;
import com.ttc.ch2.api.ccapi.v3.SearchToursBaseRequest;
import com.ttc.ch2.api.ccapi.v3.UploadTourInfoRequest;
import com.ttc.ch2.audit.HttpResolver;
import com.ttc.ch2.audit.Struct;
import com.ttc.ch2.audit.annotations.AuditInfo;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.domain.auth.UserCCAPIDetails;
import com.ttc.ch2.domain.auth.UserGuiDetails;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.domain.user.UserGui;

public class ChResolver extends HttpResolver {
    private static Logger logger = LoggerFactory.getLogger(ChResolver.class);
    
    @Override
    public Struct resolve(JoinPoint joinPoint, AuditInfo ai) {
        Struct struct = super.resolve(joinPoint, ai);
        
        struct.token = token();
        struct.user = user();
        
        if (StringUtils.isBlank(struct.resource)) {
            struct.resource = sellingCompanies(joinPoint);
        }
        
        return struct;
    }

    private String user() {
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            if (context == null) {
                return null;
            }

            Authentication authentication = context.getAuthentication();
            if (authentication == null) {
                return null;
            }

            Object principal = authentication.getPrincipal();

            if (principal instanceof UserGuiDetails) {
                UserGuiDetails userDetails = (UserGuiDetails) principal;
                UserGui user = userDetails.getUserDb();
                return user.getUsername();
            }

            if (principal instanceof UserCCAPIDetails) {
                UserCCAPIDetails userDetails = (UserCCAPIDetails) principal;
                UserCCAPI user = userDetails.getUserDb();
                return user.getUsername();
            }
        } catch (Throwable ignored) {
            if (isVerbose()) {
                logger.warn("", ignored);
            }
        }
        return null;
    }
    
    private String sellingCompanies(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            String value = sellingCompanies(arg);
            if (StringUtils.isNotBlank(value)) {
                return value;
            }
        }
        
        return null;
    }

    private String sellingCompanies(Object arg) {
        if (arg instanceof GetContinentsAndCountriesVisitedRequest) {
            GetContinentsAndCountriesVisitedRequest request = (GetContinentsAndCountriesVisitedRequest) arg;
            List<String> list = request.getSellingCompanies();
            String result = Joiner.on(',').join(list);
            return result;
        }
        
        if (arg instanceof GetTourCategoriesRequest) {
            GetTourCategoriesRequest request = (GetTourCategoriesRequest) arg;
            String result = request.getSellingCompany();
            return result;
        }
        
        if (arg instanceof SearchToursBaseRequest) {
            SearchToursBaseRequest request = (SearchToursBaseRequest) arg;
            List<String> list = request.getSellingCompanies();
            String result = Joiner.on(',').join(list);
            return result;
        }

        if (arg instanceof GetBrochureRequest) {
            GetBrochureRequest request = (GetBrochureRequest) arg;
            String result = request.getSellingCompanyCode();
            return result;
        }   
        
        if (arg instanceof GetTourDetailsFullRequest) {
            GetTourDetailsFullRequest request = (GetTourDetailsFullRequest) arg;
            String result = request.getSellingCompany();
            return result;
        }
        
        if (arg instanceof SearchTours) {
            SearchTours request = (SearchTours) arg;
            ArrayOfString array = request.getSellingCompanyCodes();
            if (array == null) {
                return null;
            }
            
            List<String> list = array.getString();
            String result = Joiner.on(',').join(list);
            return result;
        }
        
        if (arg instanceof TourDetailsFull) {
            TourDetailsFull request = (TourDetailsFull) arg;
            String result = request.getSellingCompanyCode();
            return result;
        }

        return null;
    }

    protected String token() {
        String token = SecurityHelper.getTokenSilent();
        return token;
    }

}
