package test;


import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.util.Strings;

import com.hibernate.utils.HibernateDataDaoSupport;
import com.rwrs.hibernate.entity.Subject;

public class SubjectDataUtils {
	public static List getSubjectList(String whereStr){
		
		String hql=" from "+Subject.class.getName();
	    if(Strings.isNotEmpty(whereStr)){
	    	
	    	hql=hql+" "+whereStr;
	    	    	
	    }
	    
	    hql=hql+" order by userId";
	    
	    List list=HibernateDataDaoSupport.getHqlQueryResult(hql);
		
		System.out.println("hql==="+hql);
		
		return list;
	
	}


	
	
	
	
	
	public static void main(String[] args) {

		//String whereStr=" where userId in ('4','6')";
		
		/**String[] x={"1","2"};
	    
		String whereStr=" where userId in "	+Arrays.toString(x).replace("[", "(").replace("]", ")");
		
		List list=getSubjectList(whereStr);
	    System.out.println(list.size());
	**/
	String hql="from com.rwrs.hibernate.entity.Subject order by userId where userId in ('10')";
	List list=HibernateDataDaoSupport.getHqlQueryResult(hql);
	
	System.out.println(list.size());
	
	}




}
