package com.rwrs.graphics;

import java.util.List;

import org.apache.logging.log4j.util.Strings;

import com.hibernate.utils.HibernateDataDaoSupport;
import com.rwrs.hibernate.entity.Subject;

public class GraphicsUtils {
	
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
