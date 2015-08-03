package com.ttc.ch2.dao.security;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;
import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.dao.SellingCompanyDAO;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.auth.Authority;
import com.ttc.ch2.domain.auth.Group;
import com.ttc.ch2.domain.auth.GroupAuthority;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.user.User;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.domain.user.UserGui;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/testOnlyCtx.xml","classpath:/META-INF/spring/daoCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class SecurityTest {

	@Inject
	private AuthorityDAO authorityDAO;
	
	@Inject
	private GroupDAO groupDAO;
	
	@Inject
	private UserGuiDAO userGuiDAO;
	
	@Inject 
	private UserCCAPIDAO userCCAPIDAO;
	
	@Inject
	private ContentRepositoryDAO contentRepositoryDAO;
	
	@Inject
	private SellingCompanyDAO sellingCompanyDAO;
	
	
	@Test
	@Transactional
	public void testAuthorityDAO() {
		assertNotNull(authorityDAO);
		assertNotNull(groupDAO);
		assertNotNull(userGuiDAO);
		
		UserGui user = new UserGui();
		user.setEnabled(true);
		user.setPassword("change me");
		user.setUsername("Some user");
		Set<Authority> authorities = new HashSet<Authority>();
		user.setAuthorities(authorities);
		
		Authority auth = new Authority();
		auth.setAuthority("AUTH1");
		authorities.add(auth);
		auth.setUser(user);
		auth = new Authority();
		auth.setAuthority("AUTH2");
		authorities.add(auth);
		auth.setUser(user);
		
		Group group = new Group();
		group.setGroupName("some group name");
		Set<GroupAuthority> groupAuthorities = new HashSet<GroupAuthority>();
		group.setGroupAuthorities(groupAuthorities);
		
		GroupAuthority ga = new GroupAuthority(); 
		ga.setAuthority("GAUTH1");
		groupAuthorities.add(ga);
		ga.setGroup(group);
		
		ga = new GroupAuthority(); 
		ga.setAuthority("GAUTH2");
		groupAuthorities.add(ga);
		ga.setGroup(group);
		
		Set<Group> gset = new HashSet<Group>();
		gset.add(group);
		
		Set<User> uset = new HashSet<User>();
		uset.add(user);
		user.setGroups(gset);
		
		groupDAO.save(group);
		Assert.assertNotNull(group.getId());
		
		SellingCompany sc = new SellingCompany();
		sc.setCode("code1");
		sc.setName("scname");
		
		UserCCAPI uc = new UserCCAPI();
		uc.setAddress("addr1");
		uc.setEmail("noreply@ttc.com");
		uc.setEnabled(true);
		uc.setToken("sometoken");
		uc.setUsername("authuser1");
				
		ContentRepository cr = new ContentRepository();
		cr.setTiFileName("tifilename");
		cr.setTdFileName("tdfilename");
		cr.setSellingCompanies(Sets.newHashSet(sc));
		
		sellingCompanyDAO.save(sc);
		
		Assert.assertNotNull(sc.getId());
		
	}
	
	
	@Test
	@Transactional
	public void testAuthorities(){
		UserGui user = new UserGui();
		user.setUsername("ome u");
		user.setEnabled(true);
		System.out.println("users_count: "+ userGuiDAO.getUsersCount(user));
		SortCondition sc = new SortCondition("username", SortCondition.Direction.ASC);
		QueryCondition conditions = new QueryCondition(0, 1, sc);
		List<UserGui> users = userGuiDAO.getUsers(conditions, user);
		for(User u: users) {
			Set<GrantedAuthority> out = authorityDAO.getAllGrantedAuthoritiesForUser(u.getId());
			for(GrantedAuthority a: out) {
				System.out.println(a.getAuthority());
			}
		}
		System.out.println("S_END2");
		
	}
	@Test
	@Transactional
	public void testVerification(){
		
		Search search = new Search();
		search.addFilterEqual("username", "authuser1");
		UserCCAPI user = userCCAPIDAO.searchUnique(search);
		System.out.println("CheckPermissionToTIFile: " + contentRepositoryDAO.checkPermissionToFile(user, "tifilename","CH",FunctionType.CR_VIEW_V1));
		System.out.println("CheckPermissionToTDFile: " + contentRepositoryDAO.checkPermissionToFile(user, "tdfilename","CH",FunctionType.CR_VIEW_V1));		
		System.out.println("CheckPermissionToTIFile_fake: " + contentRepositoryDAO.checkPermissionToFile(user, "fakefile","CH",FunctionType.CR_VIEW_V1));
		System.out.println("S_END3");
	}
}
