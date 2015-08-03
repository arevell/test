package com.ttc.ch2.hibernate;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.common.enums.SystemDirection;
import com.ttc.ch2.common.predicates.Ch2ConfigValueFinder;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.Ch2ConfigValueDAO;
import com.ttc.ch2.dao.messages.EmailAddressDAO;
import com.ttc.ch2.dao.security.UserCCAPIDAO;
import com.ttc.ch2.dao.security.UserGuiDAO;
import com.ttc.ch2.dao.transfer.TourInfoTransferDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.Ch2ConfigValue;
import com.ttc.ch2.domain.Function;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.auth.Authority;
import com.ttc.ch2.domain.auth.CCAPIAuthority;
import com.ttc.ch2.domain.auth.Group;
import com.ttc.ch2.domain.auth.GroupAuthority;
import com.ttc.ch2.domain.auth.GroupNames;
import com.ttc.ch2.domain.auth.Role;
import com.ttc.ch2.domain.messages.EmailAddress;
import com.ttc.ch2.domain.messages.EmailAddress.AddressType;
import com.ttc.ch2.domain.transfer.TourInfoTransfer;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.domain.user.UserGui;

@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class DbUpdaterComponent {

	@Inject
	private SessionFactory sessionFactory;

	@Inject
	private BrandDAO brandDAO;

	@Inject
	private UserCCAPIDAO userCCAPIDAO;

	@Inject
	private UserGuiDAO userDao;

	@Inject
	private TourInfoTransferDAO tourInfoTransferDAO;

	@Inject
	private EmailAddressDAO emailAddressDAO;
	
	@Inject
	private Ch2ConfigValueDAO ch2ConfigValueDAO;

	@Inject
	@Named("dataSource")
	private DataSource ds;


	public void updateStart(boolean production) throws UnsupportedEncodingException
	{		
		initConfiguration();
		initFunction();
		initBrandAndSellingCompanies();
		initTourInfoTransfer();
		initEmailAddresses();
		initGroupAuthority();
		
		initUserAdm();		
		if(production==false){		
			initExternalUserCH();
			initExternalUserBV();
			initExternalUserAllAuth();
			initUserGui();
			initUserGuiAllBrands();				
		}
	}
	
	private void initConfiguration()
	{
		List<Ch2ConfigValue> cfg=ch2ConfigValueDAO.findAll();
		
		Optional<Ch2ConfigValue> result=Iterables.tryFind(cfg, new Ch2ConfigValueFinder(Ch2ConfigValue.PropName.SystemDirection));
		if(result.isPresent()==false){
			Ch2ConfigValue value=new Ch2ConfigValue();
			value.setPropertyName(Ch2ConfigValue.PropName.SystemDirection.toString());
			value.setPropertyValue(SystemDirection.HABS.toString());
			ch2ConfigValueDAO.save(value);
		}
		
		
	}

	private void initFunction()
	{
		Session session = sessionFactory.getCurrentSession();				
		Query q=session.createQuery(" from Function");
		q.setMaxResults(10);
		if (q.list().size() == 0) {
			for (int i = 0; i < FunctionType.values().length; i++) {
				Function f=new Function();
				f.setName(FunctionType.values()[i].getSimpleName());
				f.setInfo(FunctionType.values()[i].getInfo());
				session.save(f);
			}			
		}		
	}

	private void initGroupAuthority()
	{
		Session session = sessionFactory.getCurrentSession();				
		Query q1=session.createQuery(" from Group");
		q1.setMaxResults(10);
		if(q1.list().size()>0)
			return;
		
		Query q2=session.createQuery(" from Authority");
		q2.setMaxResults(10);
		if(q2.list().size()>0)
			return;
		
		Group admins=getGroup(GroupNames.ADMINS.toString());		
		Group brands=getGroup(GroupNames.BRANDS.toString());
		Group externals=getGroup(GroupNames.EXTERNALS_CLIENT.toString());
				
		GroupAuthority grAdmin=new GroupAuthority();
		grAdmin.setAuthority(Role.ADMINISTRATOR.getName());
		grAdmin.setGroup(admins);
		admins.getGroupAuthorities().add(grAdmin);
		
		GroupAuthority grBrand=new GroupAuthority();
		grBrand.setAuthority(Role.BRAND.getName());
		grBrand.setGroup(brands);
		brands.getGroupAuthorities().add(grBrand);
		
		GroupAuthority grBrandUpload=new GroupAuthority();
		grBrandUpload.setAuthority(Role.UPLOAD.getName());
		grBrandUpload.setGroup(brands);
		brands.getGroupAuthorities().add(grBrandUpload);
				
		GroupAuthority grExternal=new GroupAuthority();
		grExternal.setAuthority(Role.CCAPI.getName());
		grExternal.setGroup(externals);
		externals.getGroupAuthorities().add(grExternal);
		
		
		session.save(admins);
		session.save(brands);
		session.save(externals);
		
//		session.save(grAdmin);
//		session.save(grBrand);
//		session.save(grBrandUpload);
//		session.save(grExternal);		
	}

	private void initExternalUserCH()
	{						
		Session session = sessionFactory.getCurrentSession();
		
		Query q1=session.createQuery(" from UserCCAPI u  where u.username like 'ccapi-usr%' or u.token='token-usr' ");
		q1.setMaxResults(10);
		if(q1.list().size()>0)
			return;
		
		UserCCAPI u=new UserCCAPI();
		u.setUsername("ccapi-usr");	
		u.setEnabled(true);	
		u.setAddress("n\\a");
		u.setEmail("n\\a");
		u.setToken("token-usr");
				
		Group g=(Group) session.createQuery("from Group g where g.groupName='EXTERNALS_CLIENT'").list().get(0);
		u.getGroups().add(g);
//		g.getUsers().add(u);
		
		List<Function> listFun=session.createQuery("from Function").list();				
		
		List<Brand> listBrand=session.createQuery("from Brand b where b.code='CH'").list();
		Brand brand=listBrand.get(0);
		
		for (Function function : listFun) {
			
			for (SellingCompany sellingCompany : brand.getSellingCompanies()) {
				
				CCAPIAuthority auth= new CCAPIAuthority();
				auth.setFunction(function);
				auth.setSellingCompany(sellingCompany);
				auth.setUserCcapi(u);
				u.getCcapiAuthorities().add(auth);				
			}
		}						
		userCCAPIDAO.save(u);
	}

	private void initExternalUserBV()
	{						
		Session session = sessionFactory.getCurrentSession();
		
		Query q1=session.createQuery(" from UserCCAPI u  where u.username like 'ccapi-usr-bv%' or u.token='token-bv'");
		q1.setMaxResults(10);
		if(q1.list().size()>0)
			return;
		
		UserCCAPI u=new UserCCAPI();
		u.setUsername("ccapi-usr-bv");	
		u.setEnabled(true);	
		u.setAddress("n\\a");
		u.setEmail("n\\a");
		u.setToken("token-bv");
		
		Group g=(Group) session.createQuery("from Group g where g.groupName='EXTERNALS_CLIENT'").list().get(0);
		u.getGroups().add(g);
//		g.getUsers().add(u);
		
		List<Function> listFun=session.createQuery("from Function").list();				
		
		List<Brand> listBrand=session.createQuery("from Brand b where b.code='BV'").list();
		Brand brand=listBrand.get(0);
		
		for (Function function : listFun) {
			
			for (SellingCompany sellingCompany : brand.getSellingCompanies()) {
				
				CCAPIAuthority auth= new CCAPIAuthority();
				auth.setFunction(function);
				auth.setSellingCompany(sellingCompany);
				auth.setUserCcapi(u);
				u.getCcapiAuthorities().add(auth);				
			}
		}
		userCCAPIDAO.save(u);
	}

	private void initExternalUserAllAuth()
	{						
		Session session = sessionFactory.getCurrentSession();
		
		Query q1=session.createQuery(" from UserCCAPI u  where u.username like 'ccapi-usr-all%' or u.token='token-xxx'");
		q1.setMaxResults(10);
		if(q1.list().size()>0)
			return;
		
		UserCCAPI u=new UserCCAPI();
		u.setUsername("ccapi-usr-all");	
		u.setEnabled(true);	
		u.setAddress("n\\a");
		u.setEmail("n\\a");
		u.setToken("token-xxx");
				
		Group g=(Group) session.createQuery("from Group g where g.groupName='EXTERNALS_CLIENT'").list().get(0);
		u.getGroups().add(g);
//		g.getUsers().add(u);
		
		List<Function> listFun=session.createQuery("from Function").list();				
		List<SellingCompany> listSellingCompanies=session.createQuery("from SellingCompany").list();
		
		for (Function function : listFun) {
			
			for (SellingCompany sellingCompany : listSellingCompanies) {
				
				CCAPIAuthority auth= new CCAPIAuthority();
				auth.setFunction(function);
				auth.setSellingCompany(sellingCompany);
				auth.setUserCcapi(u);
				u.getCcapiAuthorities().add(auth);				
			}
		}
		userCCAPIDAO.save(u);
	}

	private void initUserGui() throws UnsupportedEncodingException
	{						
		Session session = sessionFactory.getCurrentSession();
		
		Query q1=session.createQuery(" from UserGui u where u.username like 'gui-usr-brand%'");
		q1.setMaxResults(10);
		if(q1.list().size()>0)
			return;
		
		UserGui u=new UserGui();
		u.setUsername("gui-usr-brand");
		u.setPassword(new String(DigestUtils.md5DigestAsHex("abcd123".getBytes("UTF-8"))));	
		u.setEnabled(true);		
		u.setEmail("n\\a");
		u.setFirstLog(true);
		u.setCountInvalidLog(0);
				
		List<Brand> listBrand=session.createQuery("from Brand b where b.code='CH'").list();
		u.getBrands().addAll(listBrand);
		
		Authority authority=new Authority();
		authority.setAuthority(Role.BRAND.getName());		
		u.getAuthorities().add(authority);
		authority.setUser(u);
		
		Authority authority2=new Authority();
		authority2.setAuthority(Role.UPLOAD.getName());		
		u.getAuthorities().add(authority2);
		authority2.setUser(u);
					
		userDao.save(u);
	}

	private void initUserGuiAllBrands() throws UnsupportedEncodingException
	{						
		Session session = sessionFactory.getCurrentSession();
		
		Query q1=session.createQuery(" from UserGui u where u.username like 'gui-usr-all-brand%'");
		q1.setMaxResults(10);
		if(q1.list().size()>0)
			return;
		
		UserGui u=new UserGui();
		u.setUsername("gui-usr-all-brand");
		u.setPassword(new String(DigestUtils.md5DigestAsHex("abcd123".getBytes("UTF-8"))));	
		u.setEnabled(true);		
		u.setEmail("n\\a");
		u.setFirstLog(true);
		u.setCountInvalidLog(0);
				
		List<Brand> listBrand=session.createQuery("from Brand b").list();
		u.getBrands().addAll(listBrand);
		
		Authority authority=new Authority();
		authority.setAuthority(Role.BRAND.getName());		
		u.getAuthorities().add(authority);
		authority.setUser(u);
		
		Authority authority2=new Authority();
		authority2.setAuthority(Role.UPLOAD.getName());		
		u.getAuthorities().add(authority2);
		authority2.setUser(u);
					
		userDao.save(u);
	}

	private void initUserAdm() throws UnsupportedEncodingException
	{						
		Session session = sessionFactory.getCurrentSession();
		
		Query q1=session.createQuery(" from UserGui u where u.username like 'gui-usr-adm%'");
		q1.setMaxResults(10);
		if(q1.list().size()>0)
			return;
		
		UserGui u=new UserGui();
		u.setUsername("gui-usr-adm");
		u.setPassword(new String(DigestUtils.md5DigestAsHex("abcd123".getBytes("UTF-8"))));	
		u.setEnabled(true);		
		u.setEmail("n\\a");
		u.setFirstLog(true);
		u.setCountInvalidLog(0);
				
//		List<Brand> listBrand=session.createQuery("from Brand").list();
//		u.getBrands().addAll(listBrand);
		
		Authority authority=new Authority();
		authority.setAuthority(Role.ADMINISTRATOR.getName());		
		u.getAuthorities().add(authority);
		authority.setUser(u);
		
		userDao.save(u);
	}	

	private void initBrandAndSellingCompanies() {
		
		Session session = sessionFactory.getCurrentSession();				
		Query q=session.createQuery(" from Brand");
		q.setMaxResults(10);
		if(q.list().size()>0)
			return;
				
		{
			Brand brand = new Brand();
			brand.setCode("CH");
			brand.setBrandName("Contiki");
			Set<SellingCompany> sellingCompanies = new HashSet<SellingCompany>();
			brand.setSellingCompanies(sellingCompanies);
			
			String sellingCompaniesArray[] = {
			"CHAKLS", "Contiki Company 1", 
			"CHCANS", "Contiki Company 2",
			"CHEUOS", "Contiki Company 3",
			"CHJBGS", "Contiki Company 4",
			"CHSINS", "Contiki Company 5",
			"CHSYDS", "Contiki Company 6",
			"CHUKLS", "Contiki Company 7",
			"CHUSAS", "Contiki Company 8",
			};
	
			for(int i=0; i<sellingCompaniesArray.length/2;i++) {
				SellingCompany s = new SellingCompany();
				s.setBrand(brand);
				s.setCode(sellingCompaniesArray[i*2]);
				s.setName(sellingCompaniesArray[i*2 + 1]);
				sellingCompanies.add(s);
			}
			brandDAO.save(brand);
		}
		
		{
			Brand brand = new Brand();
			brand.setCode("BV");
			brand.setBrandName("Brendan Vacations ");
			Set<SellingCompany> sellingCompanies = new HashSet<SellingCompany>();
			brand.setSellingCompanies(sellingCompanies);
			
			String sellingCompaniesArray[] = {
			"BVUSAS", "Brendan Vacations Company 1", 			
			};
	
			for(int i=0; i<sellingCompaniesArray.length/2;i++) {
				SellingCompany s = new SellingCompany();
				s.setBrand(brand);
				s.setCode(sellingCompaniesArray[i*2]);
				s.setName(sellingCompaniesArray[i*2 + 1]);
				sellingCompanies.add(s);
			}
			brandDAO.save(brand);
		}

		{
			Brand brand = new Brand();
			brand.setCode("IV");
			brand.setBrandName("Insight Vacations");
			Set<SellingCompany> sellingCompanies = new HashSet<SellingCompany>();
			brand.setSellingCompanies(sellingCompanies);
			
			int x=1;
			String companyName="Insight Vacations ";
			String sellingCompaniesArray[] = {
					"IVAKLS", companyName+" Company "+x++, 			
					"IVCANS", companyName+" Company "+x++, 			
					"IVEUOS", companyName+" Company "+x++, 			
					"IVGSAS", companyName+" Company "+x++,					
					"IVJBGS", companyName+" Company "+x++, 			
					"IVSINS", companyName+" Company "+x++, 			
					"IVSYDS", companyName+" Company "+x++, 			
					"IVUKLS", companyName+" Company "+x++, 			
					"IVUSAS", companyName+" Company "+x++, 			
			};
			
			for(int i=0; i<sellingCompaniesArray.length/2;i++) {
				SellingCompany s = new SellingCompany();
				s.setBrand(brand);
				s.setCode(sellingCompaniesArray[i*2]);
				s.setName(sellingCompaniesArray[i*2 + 1]);
				sellingCompanies.add(s);
			}
			brandDAO.save(brand);
		}
		
		{
			Brand brand = new Brand();
			brand.setCode("TT");
			brand.setBrandName("Trafalgar");
			Set<SellingCompany> sellingCompanies = new HashSet<SellingCompany>();
			brand.setSellingCompanies(sellingCompanies);
			
			int x=1;
			String companyName="Trafalgar ";
			String sellingCompaniesArray[] = {
					"TTAKLS", companyName+" Company "+x++, 			
					"TTCANS", companyName+" Company "+x++, 			
					"TTEUOS", companyName+" Company "+x++, 			
					"TTGSAS", companyName+" Company "+x++, 			
					"TTJBGS", companyName+" Company "+x++, 			
					"TTSINS", companyName+" Company "+x++, 			
					"TTSYDS", companyName+" Company "+x++, 			
					"TTUKLS", companyName+" Company "+x++, 			
					"TTUSAS", companyName+" Company "+x++, 			
					"TTOTHS", companyName+" Company "+x++, 			
			};
			
			for(int i=0; i<sellingCompaniesArray.length/2;i++) {
				SellingCompany s = new SellingCompany();
				s.setBrand(brand);
				s.setCode(sellingCompaniesArray[i*2]);
				s.setName(sellingCompaniesArray[i*2 + 1]);
				sellingCompanies.add(s);
			}
			brandDAO.save(brand);
		}		 
	}

	private void initTourInfoTransfer() {

		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(" from TourInfoTransfer");
		query.setMaxResults(10);

		if (query.list().size() > 0) {
			return;
		}

		List<Brand> brandList = brandDAO.findAll();

		for (Brand brand : brandList) {

			TourInfoTransfer tourInfoTransfer = new TourInfoTransfer();

			tourInfoTransfer.setBrand(brand);
			tourInfoTransfer.setIsDownloadEnabled(true);
			tourInfoTransfer.setIsUploadEnabled(false);

			tourInfoTransferDAO.save(tourInfoTransfer);
		}
	}

	private void initEmailAddresses() {

		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(" from EmailAddress");
		query.setMaxResults(10);

		if (query.list().size() > 0) {
			return;
		}

		List<Brand> brandList = brandDAO.findAll();

		for (Brand brand : brandList) {

			for (String addressText : new String[] { "pawel.mosz@travcorpuk.com", "dalia.blimke-deren@travcorpuk.com", "piotr.siedlinski@travcorpuk.com" }) {

				EmailAddress emailAddress = new EmailAddress();

				emailAddress.setBrand(brand);
				emailAddress.setAddressType(AddressType.ConsolidationReportRecipient);
				emailAddress.setAddressText(addressText);

				emailAddressDAO.save(emailAddress);

				emailAddress = new EmailAddress();

				emailAddress.setBrand(brand);
				emailAddress.setAddressType(AddressType.ItineraryReportRecipient);
				emailAddress.setAddressText(addressText);

				emailAddressDAO.save(emailAddress);
			}
		}
	}

	private Group getGroup(String name)
	{
		Group g=new Group();
		g.setGroupName(name);
		return g;
	}
	
	
	
	
}

