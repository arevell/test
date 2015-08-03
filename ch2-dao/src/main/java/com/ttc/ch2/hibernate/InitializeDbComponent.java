package com.ttc.ch2.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Sets;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.upload.UploadTourInfoDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.auth.Authority;
import com.ttc.ch2.domain.auth.Group;
import com.ttc.ch2.domain.auth.GroupAuthority;
import com.ttc.ch2.domain.auth.GroupNames;
import com.ttc.ch2.domain.auth.Role;
import com.ttc.ch2.domain.comment.TIComment;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;
import com.ttc.ch2.domain.upload.UploadTourInfoFileSource;
import com.ttc.ch2.domain.upload.UploadTourInfoFileStatus;
import com.ttc.ch2.domain.user.User;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.domain.user.UserGui;

//@Repository
public class InitializeDbComponent {

	@Inject
	private UploadTourInfoDAO uploadTourInfoDAO;
	
	@Inject
	private SessionFactory sessionFactory;
	
	@Inject
	private BrandDAO brandDAO;
	
	@PostConstruct
//	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void init()
	{
//		int index=0;
//		Random r=new Random(new Date().getTime());
//		Brand b=brandSellingCompanyInit();
//		addUsers(index,r,b);
		
//		for (int i = 0; i <5; i++) {
//			UploadTourInfoFile file=getUploadTourInfoFile(index+i, r);
//			uploadTourInfoDAO.save(file);	
//		}
				
//		Brand brand=generateSampleBrand(index);
		//Brand brand=generateSampleBrand(index);
//		addUsers(index,r,brand);
		//addUsers(index,brand,r);
//		addBrands(index, r);

//		brandSellingCompanyInit();
	}
	
	private Brand generateSampleBrand(int index)
	{
		Brand brand=new Brand();
		brand.setBrandName("Brand:"+index);
		brand.setCode("BB");		
		SellingCompany sc1=new SellingCompany();
		sc1.setBrand(brand);
		sc1.setCode("CHUKLS-v");
		sc1.setName("SellingCompany name:1");
		
		SellingCompany sc2=new SellingCompany();
		sc2.setBrand(brand);
		sc2.setCode("CHEURO-v");
		sc2.setName("SellingCompany name:2");
		
		SellingCompany sc3=new SellingCompany();
		sc3.setBrand(brand);
		sc3.setCode("CHSEVR-v");
		sc3.setName("SellingCompany name:3");
		

		brand.setSellingCompanies(Sets.newHashSet(sc1,sc2,sc3));
		return brand;
		
	}
	
	
	private void addBrands(int index,Random r)
	{
		Session s=sessionFactory.openSession();		
		for (int i = 0; i < 5; i++) {
			
			Brand b=new Brand();
			b.setId(i+1l);
			b.setBrandName("BRAND:A"+i);
			s.saveOrUpdate(b);			
		}
		s.flush();
		s.close();
	}
	
	private void addUsers(int index,Random r,Brand ... brandObj )
	{
		Group admins=getGroup(GroupNames.ADMINS.toString());		
		Group brands=getGroup(GroupNames.BRANDS.toString());
		Group externals=getGroup(GroupNames.EXTERNALS_CLIENT.toString());
				
		GroupAuthority grAdmin=new GroupAuthority();
		grAdmin.setAuthority(Role.ADMINISTRATOR.getName());
		grAdmin.setGroup(admins);
		
		GroupAuthority grBrand=new GroupAuthority();
		grBrand.setAuthority(Role.BRAND.getName());
		grBrand.setGroup(brands);
		
		GroupAuthority grBrandUpload=new GroupAuthority();
		grBrandUpload.setAuthority(Role.UPLOAD.getName());
		grBrandUpload.setGroup(brands);
				
		GroupAuthority grExternal=new GroupAuthority();
		grExternal.setAuthority(Role.CCAPI.getName());
		grExternal.setGroup(externals);
		
		
		UserGui adm=getUserGui("adm","xxx",index);
//		Authority admin=getAuthority(Role.ADMINISTRATOR.getName(),adm);
//		Authority ccapi=getAuthority(Role.CCAPI.getName(),adm);		
		adm.setGroups(Sets.newHashSet(admins));
		Set<User> gr_asmin_users=Sets.newHashSet();
		gr_asmin_users.add(adm);
//		admins.setUsers(gr_asmin_users);
// 		adm.setAuthorities(Sets.newHashSet(admin,ccapi));
		
		UserGui markus=getUserGui("markuswww","abcd123",index+1);
//		markus.getBrands().add(brandObj[1]);
		Authority brand=getAuthority(Role.BRAND.getName(),markus);		
		markus.setAuthorities(Sets.newHashSet(brand));
		brand.setUser(markus);
		
		
		UserCCAPI ccapiusr=getUserCCAPI("ccapi-usr",index+1);		
		ccapiusr.setGroups(Sets.newHashSet(externals));
		Set<User> users=Sets.newHashSet();
		users.add(ccapiusr);
//		externals.setUsers(users);
				
		Session s=sessionFactory.openSession();
		Brand b=(Brand) s.get(Brand.class, new Long(1));
		markus.getBrands().add(b);
		adm.getBrands().add(b);
		s.saveOrUpdate(grAdmin);
		s.saveOrUpdate(grBrand);
		s.saveOrUpdate(grBrandUpload);
		s.saveOrUpdate(grExternal);
				
		s.saveOrUpdate(adm);		
		s.saveOrUpdate(markus);
		s.saveOrUpdate(ccapiusr);
		
		s.flush();
		s.close();
	}
	
	
	private UploadTourInfoFile getUploadTourInfoFile(int index,Random random)
	{
		UploadTourInfoFile u=new UploadTourInfoFile();
		u.setDateUpload(new Date());
		u.setName("Upload name:"+index);		
		u.setSourceUploadFile(random.nextBoolean() ? UploadTourInfoFileSource.API: UploadTourInfoFileSource.MANUAL);
		u.setStatus(UploadTourInfoFileStatus.values()[random.nextInt(2)]);	


		List<TIComment> comments=new ArrayList<TIComment>();
		u.setComments(comments);
		
		for (int i = 0; i < 10; i++) {
			TIComment comment=getComment(i, random);
			comment.setUploadTourInfoFile(u);	
			u.getComments().add(comment);			
		}		
		return u; 
	}
	
	private TIComment getComment(int index,Random random)
	{
		TIComment comment=new TIComment();
		comment.setMessage("message:"+index);	
		comment.setModifiedTime(new Date());
		comment.setModifiedBy("xxxx");
		return comment;
	}
	
	
	private Group getGroup(String name)
	{
		Group g=new Group();
		g.setGroupName(name);
		return g;
	}
	
	private Authority getAuthority(String name,User u)
	{
		Authority a=new Authority();
		a.setAuthority(name);		
		a.setUser(u);
		return a;
	}
	
	private UserGui getUserGui(String user,String password,int index)
	{				
		UserGui u=new UserGui();
		u.setUsername(user);
		u.setPassword(password);
		u.setEnabled(true);		
		u.setEmail("xxxx-"+index+"@op.pl");
		return u;
	}
	
	private UserCCAPI getUserCCAPI(String user,int index)
	{		
		UserCCAPI u=new UserCCAPI();
		u.setUsername(user);	
		u.setEnabled(true);	
		u.setAddress("address:"+index);
		u.setEmail("xxxx-"+index+"@op.pl");
		u.setToken("xxx");
		return u;
	}
	
	private Brand brandSellingCompanyInit() {
		
		Brand brand = new Brand();
		brand.setCode("CH");
		brand.setBrandName("Contiki");
		Set<SellingCompany> sellingCompanies = new HashSet<SellingCompany>();
		brand.setSellingCompanies(sellingCompanies);
		
		String sellingCompaniesArray[] = {
			"CHPTPS", "Contiki Holidays (Australia) Pty Ltd", 
			"CHPTPO", "Contiki Holidays (Australia) Pty Ltd",
			"CHSGAO", "Contiki Holidays (Australia) Pty Ltd",
			"CHASAO", "Contiki Holidays Asia Ltd",
			"CHPREO", "Contiki Holidays Ltd",
			"CHAKLS", "Contiki Holidays (NZ) Ltd",
			"CHAPLO", "Contiki Holidays (Australia) Pty Ltd",
			"CHCANS", "Contiki Holidays (Canada) Ltd",
			"CHCANO", "Contiki Holidays (Canada) Ltd",
			"CHEUOS", "Contiki Holidays Limited",
			"CHEURO", "Contiki Holidays Ltd",
			"CHGSAS", "Contiki Tours International Ltd",
			"CHJBGO", "Trafalgar Tours (Proprietary) Ltd",
			"CHJBGS", "Contiki Holidays",
			"CHNZLO", "Contiki Holidays (NZ) Ltd",
			"CHSINO", "The Travel Corporation (2011) Pte Ltd",
			"CHSINS", "The Travel Corporation (2011) Pte Ltd",
			"CHSYDS", "Contiki Holidays (Australia) Pty Ltd",
			"CHUKLS", "Contiki Holidays Limited",
			"CHUSAS", "Contiki US Holdings Inc",
			"CTIL"  , "Contiki - H Co of O co",
			"CTUKLO", "Contiki Travel UK Ltd",
			"CUSHIO", "Contiki US Holdings Inc",
			"CHSGBO", "Contiki Travel UK Ltd",
			"CHSGEO", "Contiki Holidays Ltd",
			"CHSEVR", "Contiki UK/Europe Service Company",
			"AUSEVR", "Australian Service Company",
			"CHCONO", "Contiki Holidays Ltd",
			"CTILH",  "Contiki - H Co of S co",
			"CHLAMO", "Contiki Holidays Latin America Ltd",
			"CHOSTS", "Contiki Holidays - The Travel Corporation Ireland Limited"
		};
		for(int i=0; i<sellingCompaniesArray.length/2;i++) {
			SellingCompany s = new SellingCompany();
			s.setBrand(brand);
			s.setCode(sellingCompaniesArray[i*2]);
			s.setName(sellingCompaniesArray[i*2 + 1]);
			sellingCompanies.add(s);
		}
		brandDAO.save(brand);
		
		return brand;
		
	}
	
}

