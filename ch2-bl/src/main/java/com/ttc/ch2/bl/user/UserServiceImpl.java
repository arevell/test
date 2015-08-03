package com.ttc.ch2.bl.user;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.ttc.ch2.common.predicates.FindEntityByIdPredicate;
import com.ttc.ch2.common.predicates.FindGroupByNamePredicate;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.FunctionDAO;
import com.ttc.ch2.dao.SellingCompanyDAO;
import com.ttc.ch2.dao.security.GroupDAO;
import com.ttc.ch2.dao.security.UserCCAPIDAO;
import com.ttc.ch2.dao.security.UserGuiDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.Function;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.auth.CCAPIAuthority;
import com.ttc.ch2.domain.auth.Group;
import com.ttc.ch2.domain.auth.GroupNames;
import com.ttc.ch2.domain.user.User;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.domain.user.UserGui;

@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Inject
	private UserGuiDAO userGuiDAO;
	
	@Inject
	private UserCCAPIDAO userCCAPIDAO;
	
	@Inject
	private BrandDAO brandDAO;
	
	@Inject
	private GroupDAO groupDAO;
	
	@Inject
	private FunctionDAO functionDAO;
	
	@Inject
	private SellingCompanyDAO sellingCompanyDAO;
	
	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public void mergeUserGui(UserGui u,boolean clearPswd) throws UserServiceException {
		try {
			
			UserGui localUser=null;
			if(u.getId()!=null)
			{
				localUser=userGuiDAO.find(u.getId());
				localUser.setUsername(u.getUsername());
				localUser.setEnabled(u.getEnabled());				
				localUser.setEmail(u.getEmail());	
				//add brand
				Set<Brand> brandsToRemove=Sets.newHashSet();
				for (Brand b : localUser.getBrands()) {				
					Optional<Brand> findedBrand=Iterables.tryFind(u.getBrands(), new FindEntityByIdPredicate(b.getId()));
					if(findedBrand.isPresent())
					{
						u.getBrands().remove(findedBrand.get());
						continue;
					}
					else
					{
						brandsToRemove.add(b);
					}
				}
				localUser.getBrands().removeAll(brandsToRemove);
				
				for (Brand b  : u.getBrands()) {
					localUser.getBrands().add(brandDAO.find(b.getId()));
				}
				
				//add group
				Set<Group> groupsToRemove=Sets.newHashSet();
				for (Group g : localUser.getGroups()) {				
					Optional<Group> findedGroup=Iterables.tryFind(u.getGroups(), new FindEntityByIdPredicate(g.getId()));
					if(findedGroup.isPresent())
					{
						u.getGroups().remove(findedGroup.get());
						continue;
					}
					else
					{
						groupsToRemove.add(g);						
					}
				}
				localUser.getGroups().removeAll(groupsToRemove);
				
				for (Group g : u.getGroups()) {
					localUser.getGroups().add(groupDAO.find(g.getId()));
				}
			}
			else
			{
				localUser=u;
				localUser.setCountInvalidLog(0);
				Set<Brand> brands=Sets.newHashSet();
				for (Brand b  : u.getBrands()) {
					brands.add(brandDAO.find(b.getId()));
				}
				localUser.setBrands(brands);
				
				Set<Group> groups=Sets.newHashSet();
				for (Group g : u.getGroups()) {					
					groups.add(groupDAO.find(g.getId()));
				}
				localUser.setGroups(groups);
			}	
			
			//user will log by ldap
			if (clearPswd) {
				localUser.setPassword(null);
				localUser.setFirstLog(false);
			}
			else {
				if(StringUtils.isNotEmpty(u.getPassword())){
					localUser.setPassword(new String(DigestUtils.md5DigestAsHex(u.getPassword().getBytes("UTF-8"))));
					localUser.setFirstLog(true);
				}
			}
			
			userGuiDAO.save(localUser);
			
		} catch (Exception e) {
			throw new UserServiceException(e);
		}
	}
	
	
	@Override
	public void mergeUserCCAPI(UserCCAPI u) throws UserServiceException {
		
		
		Session session=sessionFactory.getCurrentSession();
		List<Function> allFunctions=functionDAO.findAll(); 
		List<SellingCompany> allCompanies=sellingCompanyDAO.findAll(); 
		UserCCAPI localUser=null;
		if(u.getId()==null)
		{			
			localUser=u;
			Group gr=Iterables.find(groupDAO.findAll(), new FindGroupByNamePredicate(GroupNames.EXTERNALS_CLIENT.toString()));	
			localUser.getGroups().add(gr);
			
			Set<CCAPIAuthority> auths=Sets.newHashSet();
			for (CCAPIAuthority auth  : localUser.getCcapiAuthorities()) {
				CCAPIAuthority a=new CCAPIAuthority();
				a.setSellingCompany(Iterables.find(allCompanies, new FindEntityByIdPredicate(auth.getSellingCompany().getId())));
				a.setFunction(Iterables.find(allFunctions, new FindEntityByIdPredicate(auth.getFunction().getId())));
				a.setUserCcapi(localUser);
				auths.add(a);
			}
			localUser.setCcapiAuthorities(auths);
		}
		else
		{
			localUser=userCCAPIDAO.find(u.getId());
			localUser.setEmail(u.getEmail());
			localUser.setAddress(u.getAddress());
			localUser.setEnabled(u.getEnabled());
			localUser.setToken(u.getToken());
			localUser.setUsername(u.getUsername());
						
			//add CCAPIAuthority
			Set<CCAPIAuthority> authsToRemove=Sets.newHashSet();
			for(CCAPIAuthority auth : localUser.getCcapiAuthorities()){				
				Optional<CCAPIAuthority> findedAuths=Iterables.tryFind(u.getCcapiAuthorities(), new FindCCAPIAuthority(auth.getFunction(),auth.getSellingCompany()));
				if(findedAuths.isPresent()){					
					Iterables.removeIf(u.getCcapiAuthorities(),  new FindCCAPIAuthority(findedAuths.get().getFunction(),findedAuths.get().getSellingCompany()));
					continue;
				}
				else{
					authsToRemove.add(auth);						
				}
			}
			
//			authsToRemove
			for (CCAPIAuthority auth : authsToRemove) {
				
				Optional<CCAPIAuthority> oAuth=Iterables.tryFind(localUser.getCcapiAuthorities(),  new FindCCAPIAuthority(auth.getFunction(),auth.getSellingCompany()));
				if(oAuth.isPresent())
				{
					localUser.getCcapiAuthorities().remove(oAuth.get());
					session.delete(oAuth.get());
				}
			}
//			authsToAdd			
			for (CCAPIAuthority auth  : u.getCcapiAuthorities()) {
				localUser.getCcapiAuthorities().add(auth);
				auth.setUserCcapi(localUser);
			}			
		}
		
		userCCAPIDAO.save(localUser);
//		sessionFactory.getCache().evictEntity(UserCCAPI.class, localUser.getId());	
//		sessionFactory.getCache().evictQueryRegion("UserRegion");	
	}
	
	
	public UserCCAPI findUserCCAPI(Long id)throws UserServiceException
	{
		try {
			return userCCAPIDAO.find(id);
		} catch (Exception e) {
			throw new UserServiceException(e);
		}
	}
	

	@Override
	public UserGui findUserGui(Long id) throws UserServiceException {
		try {
			return userGuiDAO.find(id);
		} catch (Exception e) {
			throw new UserServiceException(e);
		}
	}

	@Override
	public void deleteUserGui(Long id) throws UserServiceException {
		try {
			UserGui u=userGuiDAO.find(id);
			setupUserNameforDel(u);			
			u.setDelFlag(true);
			u.setEnabled(false);
			userGuiDAO.save(u);
			
//			u.getBrands().clear();
//			u.getGroups().clear();
//			userGuiDAO.remove(u);
		} catch (Exception e) {
			throw new UserServiceException(e);
		}
	}
	
	
	class FindCCAPIAuthority implements Predicate<CCAPIAuthority>
	{
		private Function function=null;
		private SellingCompany sellingCompany=null;
		
		public FindCCAPIAuthority(Function function,SellingCompany sellingCompany) {
					super();
					this.function = function;
					this.sellingCompany = sellingCompany;
				}
		@Override
		public boolean apply(CCAPIAuthority input) {
			
			return input.getSellingCompany().getId().equals(sellingCompany.getId()) && input.getFunction().getId().equals(function.getId());
		}		
	}

	@Override
	public void deleteUserCCAPI(Long id) throws UserServiceException {
		try {
			Session session=sessionFactory.getCurrentSession();
			UserCCAPI u=userCCAPIDAO.find(id);			
			setupUserNameforDel(u);
			u.setDelFlag(true);
			u.setEnabled(false);
			userCCAPIDAO.save(u);
			
//			u.getGroups().clear();
//			for (CCAPIAuthority auth : u.getCcapiAuthorities()) {
//				auth.setUserCcapi(null);
//				session.delete(auth);
//			}
//			u.getCcapiAuthorities().clear();
//			userCCAPIDAO.remove(u);
		} catch (Exception e) {
			throw new UserServiceException(e);
		}		
	}
	
	@Override
	public void chgPassword(String oldPassword,String newPassword,UserGui u)throws UserServiceException
	{
		logger.trace("UserServiceImpl:chgPassword-start");
		try {
			Preconditions.checkArgument(StringUtils.isNotBlank(oldPassword),"UserServiceImpl->chgPassword arg oldPassword is empty");
			Preconditions.checkArgument(StringUtils.isNotBlank(newPassword),"UserServiceImpl->chgPassword arg newPassword is empty");
			Preconditions.checkArgument(u!=null,"UserServiceImpl->chgPassword arg UserGui is null");
			
			if(newPassword.matches(UserService.passwordPattern)==false){
				throw new UserServiceException("Incorrect password");
			}
			
			if(new String(DigestUtils.md5DigestAsHex(oldPassword.getBytes("UTF-8"))).equals(u.getPassword())==false){
				throw new UserServiceException("Incorrect password");
			}

			if(new String(DigestUtils.md5DigestAsHex(oldPassword.getBytes("UTF-8"))).equals(new String(DigestUtils.md5DigestAsHex(newPassword.getBytes("UTF-8"))))){
				throw new UserServiceException("Password should be different from the previous please correct new password");
			}
			
			UserGui guiLocal=userGuiDAO.find(u.getId());
			guiLocal.setPassword(new String(DigestUtils.md5DigestAsHex(newPassword.getBytes("UTF-8"))));
			guiLocal.setFirstLog(false);
			userGuiDAO.save(guiLocal);
			logger.trace("UserServiceImpl:chgPassword-end");
		} catch (UnsupportedEncodingException e){
			logger.trace("UserServiceImpl:chgPassword-end");
			throw new UserServiceException(e);
		}
		catch (Exception e) {
			logger.trace("UserServiceImpl:chgPassword-end");
			throw new UserServiceException(e);
		}		
	}

	
	public void invalidLoginOperation(String login) throws UserServiceException{
		logger.trace("UserServiceImpl:invalidLoginOperation-start");
		Preconditions.checkArgument(StringUtils.isNotBlank(login),"UserServiceImpl->invalidLoginOperation arg login is null");
		try{
			
			UserGui exUser=new UserGui();
			exUser.setUsername(login);
			exUser.setEnabled(true);
			UserGui localUserGui=userGuiDAO.findByExample(exUser);
			if(localUserGui!=null){
				int invalidCountLog=localUserGui.getCountInvalidLog();
				invalidCountLog++;
				if(invalidCountLog>=maxInvalidCountLogin){
					localUserGui.setEnabled(false);
					localUserGui.setCountInvalidLog(0);
				}
				else{
					localUserGui.setCountInvalidLog(invalidCountLog);
				}		
				userGuiDAO.save(localUserGui);
			}
		}catch(Exception e)
		{
			logger.trace("UserServiceImpl:invalidLoginOperation-end");
			throw new UserServiceException(e);
		}
		logger.trace("UserServiceImpl:invalidLoginOperation-end");
	}
	
	public void resetInvalidCountLogin(String login) throws UserServiceException{
		logger.trace("UserServiceImpl:resetInvalidCountLogin-start");
		Preconditions.checkArgument(StringUtils.isNotBlank(login),"UserServiceImpl->invalidLoginOperation arg login is null");
		try{
			
			UserGui exUser=new UserGui();
			exUser.setUsername(login);
			exUser.setEnabled(true);
			UserGui localUserGui=userGuiDAO.findByExample(exUser);
			if(localUserGui!=null){
				localUserGui.setCountInvalidLog(0);		
				userGuiDAO.save(localUserGui);
			}
		}catch(Exception e)
		{
			logger.trace("UserServiceImpl:resetInvalidCountLogin-end");
			throw new UserServiceException(e);
		}
		logger.trace("UserServiceImpl:resetInvalidCountLogin-end");
	}
	

	@Override
	public User findUserByName(String name) throws UserServiceException {
		Session session=sessionFactory.getCurrentSession();		
		Query q=session.createQuery(" from User where username=:userName");
		q.setParameter("userName", name);		
		return (User) q.uniqueResult();
	}
	
	private void setupUserNameforDel(User u){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");			
		if(u.getUsername().length() > 28)
			u.setUsername(u.getUsername().substring(0, 28)+sdf.format(new Date()));
		else
			u.setUsername(u.getUsername()+sdf.format(new Date()));		
	}
}
