package com.ttc.ch2.ui.moduls.tour.services;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.bl.view.TourMatchingViewException;
import com.ttc.ch2.bl.view.TourMatchingViewService;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.common.EntityBase;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.user.UserGui;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;
import com.ttc.ch2.ui.common.exceptions.PermissionException;
import com.ttc.ch2.ui.common.security.UserContext;

@Service
public class TourMatchStatusWeaveService {
	
	@Inject
	private TourMatchingViewService tourMachingViewService;
	
	@Inject
	private ContentRepositoryService contentRepositoryService;
	
	@Inject
	private MessageSource messageSource;
	
    public  String generateView(HttpServletRequest request, String id, String brandCode ) {			
		try {
			String tourCode=id;
			validate(tourCode,brandCode, request);
			return tourMachingViewService.getTransformateContentView(tourCode,brandCode);
		} catch (TourMatchingViewException e) {
			throw new CH2Exception(e.getMessage(),e);
		}
    } 
	
	private void validate(String tourCode,String brandCode,HttpServletRequest request)
	{						
		UserGui userGui=(UserGui) SessionHelper.getAttributeFromUserContext(request,UserContext.UserContextStaticName.LOGED_USER);
		ContentRepository cr=contentRepositoryService.findByTourCode(tourCode,brandCode);
		boolean exist=false;
			if(cr!=null){
				for (Brand userBrand : userGui.getBrands()) {			
					if(Iterables.any(cr.getBrands(), new ContainsEntityBase(userBrand.getId()))){
						exist=true;
						break;
					}
				}
				
				if(exist==false){
					String msg=messageSource.getMessage("tourMatchStatusViewControler.crRow.noPermision",new Object[]{tourCode}, Locale.getDefault());
					throw new PermissionException(msg);
				}
			}			
		else{
			String msg=messageSource.getMessage("tourMatchStatusViewControler.crRow.notFound",new Object[]{tourCode,brandCode}, Locale.getDefault());
			throw new CH2Exception(msg);
		}
	}

	class ContainsEntityBase implements Predicate<EntityBase>
	{
		private long searchId; 			
		public ContainsEntityBase(long searchId) {
			super();
			this.searchId = searchId;
		}
		@Override
		public boolean apply(EntityBase input) {		
			return searchId==input.getId();
		}		
	}
	
}
