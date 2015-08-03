package com.ttc.ch2.ui.moduls.tour.mvc;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.crypto.NoSuchPaddingException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.common.AuthorityHelper;
import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.common.SortCondition.Direction;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;
import com.ttc.ch2.ui.common.exceptions.NoFoundResourceException;
import com.ttc.ch2.ui.common.rest.BaseRest;
import com.ttc.ch2.ui.moduls.tour.common.CrHtmlRenderer;
import com.ttc.ch2.ui.moduls.tour.common.CrXmlRenderer;
import com.ttc.ch2.ui.moduls.tour.common.Format;
import com.ttc.ch2.ui.moduls.tour.common.Renderer;
import com.ttc.ch2.ui.moduls.tour.common.TypeFile;
import com.ttc.ch2.ui.mvc.ExceptionXmlConverter;
import com.ttc.common.params.ParamsUtils;
import com.ttc.common.utils.HttpResponseHelper;
import com.ttc.util.messages.Severity;


@Controller
public class ListContentRepositoryCtrl extends BaseRest {
			
	private static final Logger logger = LoggerFactory.getLogger(ListContentRepositoryCtrl.class);
    		
	
	@Inject
	protected ContentRepositoryService contentRepositoryService;
	
	@Inject
	protected BrandDAO brandDao;
	
	@RequestMapping(value = "/tour_info/{BRAND}/list",params = {"token","format"}, method = RequestMethod.GET)
	@ResponseBody
    public  void getTourInfoFile(HttpServletRequest request,HttpServletResponse response,
    							@PathVariable("BRAND") String brandCode,    							
    							@RequestParam(value = "token") String token, 
								@RequestParam(value = "format") String format) {
	    		
		logger.trace("ListContentRepositoryXmlCtrl:getTourInfoFile-start");		
		ContentRepository filter=getFilter(request,brandCode, FunctionType.CR_VIEW_V1);
		QueryCondition conditions;
		try {
			conditions=getSortCondition(request,TypeFile.TOUR_INFO_OLD);
		}catch(Exception e) {
			throw new CH2Exception(e);
		}
		List<ContentRepository> rowData=contentRepositoryService.getContentRepositoriesList(conditions, filter,RepositoryStatus.TIandTD,RepositoryStatus.TourInfoOnly);
		Format localformat=Format.getFormatByParamName(format);
		Renderer renderer=buildRenderer(localformat, rowData, TypeFile.TOUR_INFO_OLD, request, brandCode, conditions.getSortConditions().get(0).getDirection());
		try {
			HttpResponseHelper.writeOutput(response, renderer.generate(), localformat.getContentType());
		} catch (Exception e) {
				logger.trace("ListContentRepositoryXmlCtrl:getTourInfoFile-end");
				throw new CH2Exception(e);
		}
		logger.trace("ListContentRepositoryXmlCtrl:getTourInfoFile-end");
    }
	


	@RequestMapping(value = "/tour_info/{BRAND}/{VERSION}/list",params = {"token","format"}, method = RequestMethod.GET)
	@ResponseBody
	public void getTourInfoFileWithVersion(HttpServletRequest request,HttpServletResponse response,
			@PathVariable("BRAND") String brandCode,
			@PathVariable("VERSION") String version,
			@RequestParam(value = "token") String token,
			@RequestParam(value = "format",required=true) String format) {
		
		
		if(StringUtils.isNotEmpty(version)){
			if(version.equals("V3")){
				ContentRepository filter=getFilter(request,brandCode, FunctionType.CR_VIEW_V3);
				QueryCondition conditions;
				try {
					conditions=getSortCondition(request,TypeFile.TOUR_INFO_NEW);
				}catch(Exception e) {
					throw new CH2Exception(e);
				}
				Format localformat=Format.getFormatByParamName(format);
				List<ContentRepository> rowData=contentRepositoryService.getContentRepositoriesList(conditions, filter,RepositoryStatus.TIandTD,RepositoryStatus.TourInfoOnly);
				Renderer renderer=buildRenderer(localformat, rowData, TypeFile.TOUR_INFO_NEW, request, brandCode, conditions.getSortConditions().get(0).getDirection());
				
				try {
					HttpResponseHelper.writeOutput(response, renderer.generate(), localformat.getContentType());
				} catch (Exception e) {

						throw new CH2Exception(e);
				}	
			}else if(version.equals("V1")){
				getTourInfoFile(request, response, brandCode, token,format);		
			}
			else{
				throw new CH2Exception("Unsupported version:"+version);
			}
		}   				
	}
	
	@RequestMapping(value = "/tour_departure/{BRAND}/list",params = {"token","format"}, method = RequestMethod.GET)
	@ResponseBody
	public  void getTourDepartureFile(HttpServletRequest request,	HttpServletResponse response,	
			@PathVariable("BRAND") String brandCode,
			@RequestParam(value = "token") String token,
			@RequestParam(value = "format",required=true) String format) {
	    
		ContentRepository filter=getFilter(request,brandCode, FunctionType.CR_VIEW_V1);
		QueryCondition conditions;
		try {
			conditions = getSortCondition(request,TypeFile.TOUR_DEPARTURE_OLD);
		} catch (Exception e) {
			throw new CH2Exception(e);
		} 
		Format localformat=Format.getFormatByParamName(format);
		List<ContentRepository> rowData=contentRepositoryService.getContentRepositoriesList(conditions, filter,RepositoryStatus.TIandTD,RepositoryStatus.TourDepartureOnly);
		Renderer renderer=buildRenderer(localformat, rowData,TypeFile.TOUR_DEPARTURE_OLD, request, brandCode, conditions.getSortConditions().get(0).getDirection());
		try {
			HttpResponseHelper.writeOutput(response, renderer.generate(), localformat.getContentType());
		} catch (Exception e) {
				throw new CH2Exception(e);
		}	
	} 
	
	@RequestMapping(value = "/tour_departure/{BRAND}/{VERSION}/list",params = {"token","format"}, method = RequestMethod.GET)
	@ResponseBody
	public  void getTourDepartureWithVersion(HttpServletRequest request,	HttpServletResponse response,	
			@PathVariable("BRAND") String brandCode,
			@PathVariable("VERSION") String version,
			@RequestParam(value = "token") String token,
			@RequestParam(value = "format",required=true) String format) {
	    
		if(StringUtils.isNotEmpty(version)){
			if(version.equals("V3")){
				ContentRepository filter=getFilter(request,brandCode, FunctionType.CR_VIEW_V3);
				QueryCondition conditions;
				try {
					conditions = getSortCondition(request,TypeFile.TOUR_DEPARTURE_NEW);
				} catch (Exception e) {
					throw new CH2Exception(e);
				} 
				Format localformat=Format.getFormatByParamName(format);
				List<ContentRepository> rowData=contentRepositoryService.getContentRepositoriesList(conditions, filter,RepositoryStatus.TIandTD,RepositoryStatus.TourDepartureOnly);	
				Renderer renderer=buildRenderer(localformat, rowData,TypeFile.TOUR_DEPARTURE_NEW, request, brandCode, conditions.getSortConditions().get(0).getDirection());
				try {
					HttpResponseHelper.writeOutput(response, renderer.generate(), localformat.getContentType());
				} catch (Exception e) {

						throw new CH2Exception(e);
				}	
			}else if(version.equals("V1")){
				getTourDepartureFile(request, response, brandCode, token,format);		
			}
			else{
				throw new CH2Exception("Unsupported version:"+version);
			}
		}   		
	} 
		
	@ExceptionHandler({NoFoundResourceException.class})
	public String sourceNoExist(HttpServletResponse response,Exception e) {	
		try {
			
			String content=new ExceptionXmlConverter().convertToXmlByJaxb(HttpServletResponse.SC_NOT_FOUND,e.getMessage(),Severity.ERROR);
			writeOutput(response,content,HttpServletResponse.SC_NOT_FOUND);		
		} catch (Exception e1) {
			throw new CH2Exception(e);
		}
		return null;
	}
	
	
	private ContentRepository getFilter(HttpServletRequest request,String brandCode,FunctionType functionType)
	{
		Brand brand=brandDao.findByBrandCode(brandCode);
		ContentRepository filter=new ContentRepository();
		filter.getBrands().add(brand);		
		UserCCAPI user=SecurityHelper.getUserCCAPIPrincipal().getUserDb();		
		filter.getSellingCompanies().addAll(AuthorityHelper.transformAuthorityforUserCcapi(user).get(functionType));		
		return filter;
	}
	
	private QueryCondition getSortCondition(HttpServletRequest request,TypeFile requestType) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {

		QueryCondition queryCondition=new QueryCondition();
		String param =request.getParameter("param");
		if (StringUtils.isNotBlank(param)) {
			Map<String,String>params = ParamsUtils.getInstance(request).decodeAllParam(param);			
			if(params.containsKey("sort") && params.containsKey("direction")){				
				String column="";
				String sortParam=params.get("sort");
				switch (sortParam) {
				case "TC":					
					column="tourCode";
					break;
				case "S":					
					switch (requestType) {
						case TOUR_INFO_NEW: column="tourInfoXMLSize"; break;
						case TOUR_INFO_OLD: column="oldTourInfoXMLSize"; break;
						case TOUR_DEPARTURE_NEW: column="tourDepartureXMLSize"; break;
						case TOUR_DEPARTURE_OLD: column="oldTourDepartureXMLSize"; break;						
					default:
						break;
					}
					break;
				case "LM":					
					switch (requestType) {
						case TOUR_INFO_NEW: column="tourInfoModified"; break;
						case TOUR_INFO_OLD: column="tourInfoModified"; break;
						case TOUR_DEPARTURE_NEW: column="tourDepartureModified"; break;
						case TOUR_DEPARTURE_OLD: column="tourDepartureModified"; break;						
					default:
						break;
					}		
					break;
				default:
					column="tourCode";
					break;
				}
				String directionParam=params.get("direction");
				Direction direction=Direction.valueOf(directionParam).invert();				
				SortCondition sortCondition=new SortCondition(StringUtils.defaultIfBlank(column, "tourCode"),direction);								
				queryCondition.setSortConditions(SortCondition.getSortConditionList(sortCondition));				
			}
		}else{
			SortCondition sortCondition=new SortCondition("tourCode",Direction.ASC);								
			queryCondition.setSortConditions(SortCondition.getSortConditionList(sortCondition));
		}		
		return queryCondition;
	}
	
	
	private Renderer buildRenderer(Format format,List<ContentRepository> rowData,TypeFile requestType ,HttpServletRequest request,String brandCode,Direction direction){
		
		Renderer renderer=null;
		switch (format) {
		case UI_PLAIN:
				renderer=new CrHtmlRenderer(rowData,requestType,request,brandCode,direction, SecurityHelper.getUserCCAPIPrincipal().getUserDb().getUsername());
			break;
		case XML:
				renderer=new CrXmlRenderer(rowData,requestType,SecurityHelper.getUserCCAPIPrincipal().getUserDb().getUsername());
			break;			
		default:
			break;
		}
		return renderer;
	}
}
