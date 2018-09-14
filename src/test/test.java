package test;

import java.util.List;

import com.hibernate.utils.HibernateDataDaoSupport;
import com.rwrs.hibernate.entity.Subject;

public class test {

	 public static String  listAllSubject() {
			
			
			String hql=" from "+Subject.class.getName();
			List list=HibernateDataDaoSupport.getHqlQueryResult(hql);
			System.out.println(list.size());
		
			return null;
		}

	
	
	
	public static void main(String [ ] args)
	{
		listAllSubject();
		
	
	}


}
