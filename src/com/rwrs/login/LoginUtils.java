package com.rwrs.login;

import java.util.List;

import org.apache.logging.log4j.util.Strings;

import com.hibernate.utils.HibernateDataDaoSupport;
import com.rwrs.hibernate.entity.Subject;

public class LoginUtils {
	
	public static List getSubjectList(String whereStr){
		
		String hql="from "+Subject.class.getName();
	    if(Strings.isNotEmpty(whereStr)){
	    	
	    	hql=hql+" "+whereStr;
	    	    	
	    }
	    
	    hql=hql+" order by userName";
	    
	    List list=HibernateDataDaoSupport.getHqlQueryResult(hql);
		
		System.out.println("hql==="+hql);
		
		return list;
	
	}
	
	// return a single (first) subject that fits the where statement, or null if the subject does not exist
	public static Subject getSingleSubject(String whereStr) {
		String hql = "from " + Subject.class.getName();
		if(Strings.isNotEmpty(whereStr)) {
			hql = hql + " " +  whereStr;
		}
		
		hql = hql + " order by userName";
		
		List<Subject> list=HibernateDataDaoSupport.getHqlQueryResult(hql);
		
		if(list==null) {
			return null;
		} else if (!list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}
	
	
	public static boolean passwordValidation(String password) {
		String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
		return password.matches(pattern);
	}
	
	
	public static void main(String args[]) {
		Subject newListOfItems = getSingleSubject("where userName = 'A'");
		System.out.println(newListOfItems.toString());
	}

}
