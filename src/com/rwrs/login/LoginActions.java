package com.rwrs.login;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.hibernate.transform.ResultTransformer;

import com.hibernate.utils.HibernateDataDaoSupport;
import com.opensymphony.xwork2.ActionSupport;
import com.rwrs.hibernate.entity.Subject;
import com.rwrs.utils.PasswordEncript;

public class LoginActions extends ActionSupport{
	private static final long serialVersionUID = -6659925652584240539L;
	  
	private String userName;
	
	private String password;

	private String email;
	
	 
	public String login() {
		
		if(!Strings.isNotEmpty(userName)) {
			addFieldError("userName", "Please Enter Your Username");
			return "error";
		}
		if(!Strings.isNotEmpty(password)) {
			addFieldError("password", "Please Enter Your Password");
			return "error";
		}
		
		String hql="where userName = '" + userName + "'";
		List<Subject> satisfiedUsers = LoginUtils.getSubjectList(hql);
		System.out.println(satisfiedUsers.toString());
		
		if(!satisfiedUsers.isEmpty()) {
			System.out.println(satisfiedUsers.get(0).getPassword());
			System.out.println(password);
			if(satisfiedUsers.get(0).getPassword().equals(PasswordEncript.getEncriptPassword(password))) {
				return SUCCESS;

			}
		} else {
			addFieldError("userName", "No user detected! Please make a new user!");
			return "error";
		}

		 		
		addFieldError("password", "Wrong Password!");
		return "noUser";
	}
	
	
	public String register() {
		if(!Strings.isNotEmpty(userName)) {
			addFieldError("userName", "Please Enter Your Username");
			return "error";
		} else if(!Strings.isNotEmpty(password)) {
			addFieldError("password", "Please Enter Your Password");
			return "error";
		} else if(!Strings.isNotEmpty(email)){
			addFieldError("email", "Please Enter Your Email");
			return "error";
		}
		
		if(LoginUtils.getSingleSubject("where userName = '" + userName + "';") != null) {
			addFieldError("userName", "The user name already exist! Choose another one!");
			return "error";
		}
		
		
		if(!LoginUtils.passwordValidation(password)) {
			addFieldError("password", "Wrong password format, please ensure that your password is at least 8 digits, has at least a number, at least a upper case char, and at least one special case char");
			return "error";
		}
		
		
		// implement it after connecting authorized_emails to hibernate
//		if(LoginUtils.getSingleSubject("where userName = '" + userName + "';") == null)
		
		
		
		Subject newAccount = new Subject();
		newAccount.setEmail(email);
		newAccount.setPassword(PasswordEncript.getEncriptPassword(password));
		newAccount.setUserName(userName);
		
		HibernateDataDaoSupport.save(newAccount);
		
		return SUCCESS;
	}
	 
	 
	 
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	 
}
