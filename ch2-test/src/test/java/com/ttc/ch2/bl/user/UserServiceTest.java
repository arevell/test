package com.ttc.ch2.bl.user;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.dao.security.UserGuiDAO;
import com.ttc.ch2.domain.user.UserGui;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml", "classpath:/META-INF/spring/testCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class UserServiceTest extends BaseTest{

	@Inject
	private UserService userService;
	
	@Inject
	private UserGuiDAO userGuiDAO;
	
	@Test
	@Transactional
	public void negativeChgPasswordOldPasswordEmpty() {
		
		//prepare
//		UserGui userGui=new UserGui();		
//		UserGui userGuiDb = userGuiDAO.findByExample(userGui);
		boolean exceptionExist=false;		
		//test
		try{
		userService.chgPassword("",null, null);
		}
		//check
		catch (UserServiceException e) {
			exceptionExist=true;
			Assert.assertTrue(e.getMessage().contains("oldPassword is empty"));
		}
				
		Assert.assertTrue(exceptionExist);
		
		exceptionExist=false;		
		//test
		try{
			userService.chgPassword(null,null, null);
		}
		//check
		catch (UserServiceException e) {
			exceptionExist=true;
			Assert.assertTrue(e.getMessage().contains("oldPassword is empty"));
		}
		
		Assert.assertTrue(exceptionExist);
	}
	
	@Test
	@Transactional
	public void negativeChgPasswordNewPasswordEmpty() {
		
		//prepare
		boolean exceptionExist=false;		
		//test
		try{
			userService.chgPassword("xxx", null, null);
		}
		//check
		catch (UserServiceException e) {
			exceptionExist=true;
			Assert.assertTrue(e.getMessage().contains("newPassword is empty"));
		}
		
		Assert.assertTrue(exceptionExist);
		
		exceptionExist=false;	
		try{
			userService.chgPassword("xxx", "", null);
		}
		//check
		catch (UserServiceException e) {
			exceptionExist=true;
			Assert.assertTrue(e.getMessage().contains("newPassword is empty"));
		}
		
		Assert.assertTrue(exceptionExist);
	}
	
	
	@Test
	@Transactional
	public void negativeChgPasswordUserIsNull() {
		
		//prepare
		boolean exceptionExist=false;		
		//test
		try{
			userService.chgPassword("xxx", "xxx", null);
		}
		//check
		catch (UserServiceException e) {
			exceptionExist=true;
			Assert.assertTrue(e.getMessage().contains("UserGui is null"));
		}
		
		Assert.assertTrue(exceptionExist);
	}
	
	
	@Test
	@Transactional
	public void negativeChgPasswordThatSamePassword() throws UnsupportedEncodingException {
		
		//prepare
		UserGui userGuiDb = prepare();
		boolean exceptionExist=false;				
		//test
		try{
			userService.chgPassword("abcd123","abcd123", userGuiDb);
		}
		//check
		catch (UserServiceException e) {
			exceptionExist=true;
			Assert.assertTrue(e.getMessage().contains("Password should be different from"));
		}		
		Assert.assertTrue(exceptionExist);
	}
	
	@Test
	@Transactional
	public void negativeChgPasswordIncorrectPswd() throws UnsupportedEncodingException {
		
		//prepare
		UserGui userGuiDb =prepare();
		boolean exceptionExist=false;				
		//test
		try{
			userService.chgPassword("abcd123","abcd123{}*", userGuiDb);
		}
		//check
		catch (UserServiceException e) {
			exceptionExist=true;
			Assert.assertTrue(e.getMessage().contains("Incorrect password"));
		}		
		Assert.assertTrue(exceptionExist);
	}
	
	@Test
	@Transactional
	public void negativeChgPasswordBadOldPassword() throws UnsupportedEncodingException {
		
		//prepare
		UserGui userGuiDb = prepare();
		boolean exceptionExist=false;				
		//test
		try{
			userService.chgPassword("abcd123x","abcd123", userGuiDb);
		}
		//check
		catch (UserServiceException e) {
			exceptionExist=true;
			Assert.assertTrue(e.getMessage().contains("Incorrect password"));
		}		
		Assert.assertTrue(exceptionExist);
	}
	
	@Test
	@Transactional
	public void positiveChgPassword() throws UnsupportedEncodingException {
		
		//prepare
		UserGui userGuiDb = prepare();
		String pswd="abcd123$";
		//test
		userService.chgPassword("abcd123",pswd, userGuiDb);
				
		//check
		userGuiDb = userGuiDAO.find(userGuiDb.getId());
		Assert.assertTrue(userGuiDb.getPassword().equals(new String(DigestUtils.md5DigestAsHex(pswd.getBytes("UTF-8")))));
	}
	
	
	private UserGui prepare() throws UnsupportedEncodingException
	{
		UserGui userGui=new UserGui();	
		userGui.setUsername("gui-usr-adm");
		UserGui userGuiDb = userGuiDAO.findByExample(userGui);
		userGuiDb.setPassword(new String(DigestUtils.md5DigestAsHex("abcd123".getBytes("UTF-8"))));
		userGuiDAO.save(userGuiDb);
		return userGuiDb;
	}
}
