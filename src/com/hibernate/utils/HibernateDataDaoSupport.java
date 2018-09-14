/*
 * Filename: HibernateIDataDaoSupport.java
 *
 * Author: Carmen Ng
 * Created on: 01-18-2009
 * Last Update: 12-29-2010
 *
 * Version: 1.2
 * -
 *
 * Version: 1.1
 * -Fixed the SUM method
 *
 * Copyright (c) 2003-2009 Everest Clinical Research Services, Inc.
 * 675 Cochrane Drive, Suite 408, East Tower, Markham, Ontario, Canada L3R 0B8
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Everest Clinical Research Services, Inc. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement.
 *
 */

package com.hibernate.utils;

import java.util.ArrayList;



import java.util.List;
import java.util.ListIterator;

import javax.persistence.Table;

import org.apache.logging.log4j.util.Strings;
//import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
//import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;

import com.hibernate.HibernateDataUtil;


// TODO: Notice how all the methods in this class closes the sessionFactory? We should find a way to get
//       around this or else the methods in this class cannot be executed consecutively

@SuppressWarnings("unchecked")
public class HibernateDataDaoSupport
{
    // --------------------------------------------------------- Instance Variables

    // get a logger instance

    protected HibernateDataDaoSupport()
    {
    }

	public static String getModelTableName(Class clazz){
		Table table=(Table)clazz.getAnnotation(Table.class); 
    	String tableName = table.name(); 
		return tableName;
	}
    
    
    
    /**
     * Method get
     * @param String ID
     * @param String CLASSNAME
     * @return Object
     */
    public static Object getObjectById(Class clazz,String ID)
    {
        Object object = null;
        Session sess = null;
        
        
        try
        {
        	
            sess = HibernateDataUtil.getSessionFactory().openSession();
            if(sess==null){
            	System.out.println("session is null");
            	
            }
           
          
            object = sess.get(clazz, ID);
            if(object==null){
            	System.out.println("object is null");
            	
            }
           
        }
        catch (HibernateException he)
        {
          
            he.printStackTrace();
        }
        catch (Exception e)
        {
           
            e.printStackTrace();
        }
        finally
        {
            // close the Hibernate Session
            sess.close(); 
//        	HibernateDataUtil.shutdown();
        }

        return object;

    }

    
    
    
    
    
    /**
     * Method get
     * @param Long ID
     * @param String CLASSNAME
     * @return Object
     */
    public static Object  getObjectById(Class clazz,Long ID)
    {
        Object object = null;
        Session sess = null;

        try
        {
            // begin randomization by popping
            // *********************************************************
            //logger.error("ID: " + ID + " | CLASSNAME: "+ CLASSNAME);
            sess = HibernateDataUtil.getSessionFactory().openSession();
            object = sess.get(clazz, ID);

        }
        catch (HibernateException he)
        {
           
            System.out.println(he.getMessage());
        }
        catch (Exception e)
        {
            
            e.printStackTrace();
        }
        finally
        {
            // close the Hibernate Session
        	sess.close();
//        	HibernateDataUtil.shutdown();
        }

        return object;

    }

    /**
     * Method get
     * @param Long ID
     * @param String CLASSNAME
     * @return Object
     */
    public static Object getObjectById(Class clazz,Integer ID)
    {
        Object object = null;
        Session sess = null;

        try
        {
            // begin randomization by popping
            // *********************************************************
            //logger.error("ID: " + ID + " | CLASSNAME: "+ CLASSNAME);
            sess = HibernateDataUtil.getSessionFactory().openSession();
            
            object = sess.get(clazz, ID);

        }
        catch (HibernateException he)
        {
            he.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // close the Hibernate Session
           sess.close();
//        	HibernateDataUtil.shutdown();
        	
        }

        return object;

    }

    /**
     * Method getAll
     * @param String siteId
     * @param String SITES_CLASS_NAME
     * @return Object
     */
    public static ArrayList getAll(Class arg,
            ArrayList criterions, String ordrer)
    {
        ArrayList objList = null;
        Session sess = null;
        Criteria crit = null;

        try
        {
            // begin randomization by popping
            // *********************************************************
            //logger.error("ORDER" + ordrer + " | CLASSNAME: "+ classname);
            sess = HibernateDataUtil.getSessionFactory().openSession();
            crit = sess.createCriteria(arg);

            if (criterions != null){
                ListIterator listIterator = criterions.listIterator();

                while(listIterator.hasNext()){
                    //crit.add(Restrictions.eq("subject", "NU"))
                    crit.add((Criterion)listIterator.next());
                }
            }
            if (ordrer != null && ordrer.length() > 0)
                crit.addOrder(Order.asc(ordrer));

            // Get all studies info of the list
            objList = (ArrayList) crit.list();

        }
        catch (HibernateException he)
        {
          
            he.printStackTrace();
        }
        catch (Exception e)
        {
           
            e.printStackTrace();
        }
        finally
        {
            // close the Hibernate Session
            sess.close();
//        	HibernateDataUtil.shutdown();
        }

        return objList;

    }

    /**
     * Method getAll
     * @param String SITES_CLASS_NAME
     * @param ArrayList criterions
     * @param String order1
     * @param String order2
     * @param String order3
     * @param String order4
     * @return ArrayList
     */
    public static ArrayList getAll(Class arg,
            ArrayList criterions, String order1, String order2, String order3, String order4)
    {
        ArrayList objList = null;
        Session sess = null;
        Criteria crit = null;

        try
        {
            // begin randomization by popping
            // *********************************************************
            //logger.error("ORDER" + ordrer + " | CLASSNAME: "+ classname);
            sess = HibernateDataUtil.getSessionFactory().openSession();
            crit = sess.createCriteria(arg);

            if (criterions != null){
                ListIterator listIterator = criterions.listIterator();

                while(listIterator.hasNext()){
                    //crit.add(Restrictions.eq("subject", "NU"))
                    crit.add((Criterion)listIterator.next());
                }
            }
            if (Strings.isNotEmpty(order1))
                crit.addOrder(Order.asc(order1));

            if (Strings.isNotEmpty(order2))
                crit.addOrder(Order.asc(order2));

            if (Strings.isNotEmpty(order3))
                crit.addOrder(Order.asc(order3));

            if (Strings.isNotEmpty(order4))
                crit.addOrder(Order.asc(order4));

            // Get all studies info of the list
            objList = (ArrayList) crit.list();

        }
        catch (HibernateException he)
        {
        
            he.printStackTrace();
        }
        catch (Exception e)
        {
       
            e.printStackTrace();
        }
        finally
        {
            // close the Hibernate Session
            sess.close();
//        	HibernateDataUtil.shutdown();
        }

        return objList;

    }


    /**
     * Method getAll
     * @param String SITES_CLASS_NAME
     * @param ArrayList criterions
     * @param String order1
     * @param String order2
     * @param String order3
     * @param String order4
     * @return ArrayList
     */
    public static ScrollableResults getAllScrollableResults(Class arg,
            ArrayList criterions, String order1, String order2, String order3, String order4)
    {
        ScrollableResults sr = null;
        Session sess = null;
        Criteria crit = null;

        try
        {
            // begin randomization by popping
            // *********************************************************
            //logger.error("CLASSNAME: "+ classname);
            sess = HibernateDataUtil.getSessionFactory().openSession();
            crit = sess.createCriteria(arg);

            if (criterions != null){
                ListIterator listIterator = criterions.listIterator();

                while(listIterator.hasNext()){
                    crit.add((Criterion)listIterator.next());
                }
            }
            if (Strings.isNotEmpty(order1))
                crit.addOrder(Order.asc(order1));

            if (Strings.isNotEmpty(order2))
                crit.addOrder(Order.asc(order2));

            if (Strings.isNotEmpty(order3))
                crit.addOrder(Order.asc(order3));

            if (Strings.isNotEmpty(order4))
                crit.addOrder(Order.asc(order4));

            // Get all studies info of the ScrollableResults
            //sr = (ScrollableResults) crit.setCacheMode(CacheMode.IGNORE)
            //							 .scroll(ScrollMode.FORWARD_ONLY);
            //sr = (ScrollableResults) crit.scroll(ScrollMode.FORWARD_ONLY);
            sr = (ScrollableResults) crit.scroll();

        }
        catch (HibernateException he)
        {
         
            he.printStackTrace();
        }
        catch (Exception e)
        {
           
            e.printStackTrace();
        }
        finally
        {
            // close the Hibernate Session
            sess.clear();
//        	HibernateDataUtil.shutdown();
        }

        return sr;

    }


    /**
     * Method getSome
     * @param String siteId
     * @param String SITES_CLASS_NAME
     * @return Object
     */
    public static ArrayList getSome(Class arg,
            ArrayList criterions, String ordrer1, String ordrer2, int resultSize)
    {
        ArrayList objList = null;
        Session sess = null;
        Criteria crit = null;

        try
        {
            // begin randomization by popping
            // *********************************************************
            //logger.error("ORDER" + ordrer + " | CLASSNAME: "+ classname);
            sess = HibernateDataUtil.getSessionFactory().openSession();
            crit = sess.createCriteria(arg);

            if (criterions != null){
                ListIterator listIterator = criterions.listIterator();

                while(listIterator.hasNext()){
                    //crit.add(Restrictions.eq("subject", "NU"))
                    crit.add((Criterion)listIterator.next());
                }
            }
            if (ordrer1 != null && ordrer1.length() > 0){
                if (ordrer2 != null && ordrer2.length() > 0){
                    crit.addOrder(Order.asc(ordrer1))
                    .addOrder(Order.asc(ordrer2));
                }
                else{
                    crit.addOrder(Order.asc(ordrer1));
                }
            }



            crit.setMaxResults(resultSize);

            // Get all studies info of the list
            objList = (ArrayList) crit.list();

        }
        catch (HibernateException he)
        {
           
            he.printStackTrace();
        }
        catch (Exception e)
        {
         
            e.printStackTrace();
        }
        finally
        {
            // close the Hibernate Session
           sess.close();
//        	HibernateDataUtil.shutdown();
        }

        return objList;

    }

      
    
    
    
    
    
    
    
    
    
    public static boolean save(Object obj) {

        Session sess = null;
        Transaction tx = null;

        boolean result = true;

        try
        {
            // begin randomization by popping
            // *********************************************************
            //logger.error("CLASSNAME: "+ classname);

            sess = HibernateDataUtil.getSessionFactory().openSession();

            tx = sess.beginTransaction();

            sess.save(obj);

            tx.commit();
        }
        catch (HibernateException he)
        {
            
            tx.rollback();
            he.printStackTrace();
            result = false;
        }
        catch (Exception e)
        {
         
            tx.rollback();
            e.printStackTrace();
            result = false;
        }
        finally
        {
            sess.close();
        	// close the Hibernate Session
//            HibernateDataUtil.shutdown();
        }
        return result;

    }

    public void delete(Object obj, String classname) {

    }

        public static boolean update(Object obj) {

        Session sess = null;
        Transaction tx = null;

        boolean result = true;

        try
        {
            // begin randomization by popping
            // *********************************************************
            //logger.error("CLASSNAME: "+ classname);

            sess = HibernateDataUtil.getSessionFactory().openSession();

            tx = sess.beginTransaction();

            sess.update(obj);

            tx.commit();
        }
        catch (HibernateException he)
        {
           
            tx.rollback();
            he.printStackTrace();
            result = false;
        }
        catch (Exception e)
        {
           
            tx.rollback();
            e.printStackTrace();
            result = false;
        }
        finally
        {
            // close the Hibernate Session
            sess.close();
//        	HibernateDataUtil.shutdown();
        }
        return result;

    }

   
    /**
     * getHqlQueryResult
     * @param hql
     * @return
     */
    public static List getHqlQueryResult(String hql) {

        ArrayList objList = null;
        Session sess = null;
         
        try
        {
                  
           
            sess = HibernateDataUtil.getSessionFactory().openSession();
          
        	
            Query query = sess.createQuery(hql);
            
            objList = (ArrayList) query.list();
            
        }
        catch (HibernateException he)
        {
          System.out.println(he.getMessage());
                      
        }
        catch (Exception ex)
        {
        	       
            
        }
        finally
        {
            // close the Hibernate Session
            sess.close();
        	//HibernateDataUtil.shutdown();
           
        
        }
        return objList;

    }

   
    
    
    
    
    
    public static List getHqlQueryResult(String hql,Session sess) {

        ArrayList objList = null;
     
         
        try
        {
            	
        	
            Query query = sess.createQuery(hql);
           
            objList = (ArrayList) query.list();
            
        }
        catch (HibernateException he)
        {
           
            he.printStackTrace();
            
        }
        catch (Exception e)
        {
            
            e.printStackTrace();
            
        }
        finally
        {
           
//        	HibernateDataUtil.shutdown();
           
        
        }
        return objList;

    }

   
    
    
    public static Object getHqlQueryObject(String hql,Session sess) {
    	
    	Object result=null;
    	List list=getHqlQueryResult(hql,sess);
    	if(list!=null&&list.size()>0){
    	    result=list.get(0);
    		
    	}
    	return  result;
    	    	
    	
    
    }
    
    
    
    
    
    
    
    
    
    
    public static Object getHqlQueryObject(String hql) {
    	Object result=null;
    	List list=getHqlQueryResult(hql);
    	if(list!=null&&list.size()>0){
    	    result=list.get(0);
    		
    	}
    	return  result;
    }
    
    
    
    
    
    
    /**
     * transactionProcess
     * @param objList
     * @return
     */
    /**
     * transactionProcess
     * @param objList
     * @return
     */
    public static boolean saveOrUpdateObjects(List objList) {
        boolean result=true;
        Session sess = null;
        Transaction tx = null;

        try {
            sess = HibernateDataUtil.getSessionFactory().openSession();
            tx = sess.beginTransaction();

            for(Object obj:objList){

                sess.saveOrUpdate(obj);

            }
            tx.commit(); // Flush happens automatically
        }
        catch (RuntimeException re) {
            tx.rollback();
            result=false;
           
            re.printStackTrace();
        }
        catch (Exception e)
        {
            tx.rollback();
            result=false;
           
            e.printStackTrace();

        }
        finally {
            // close the Hibernate Session
            sess.close();
//        	HibernateDataUtil.shutdown();
        }

        return result;

    }


    public static List getObjectList(String hql,Class clazz) {
    	Session sess = null;
        Transaction tx = null;
    	
    	List list= new ArrayList();
    	sess = HibernateDataUtil.getSessionFactory().openSession();
        try {
          
        	Query query = sess.createQuery(hql)
                       .setResultTransformer(
                                     Transformers.aliasToBean(clazz));
        	      
          list = query.list();
             
        }catch (RuntimeException re) {
            tx.rollback();
          
           
            re.printStackTrace();
        }
        catch (Exception e)
        {
            tx.rollback();
           
           
            e.printStackTrace();

        }
        finally {
            // close the Hibernate Session
            sess.close();
//        	HibernateDataUtil.shutdown();
        }

    	
        return list;
    }

    
    
    
    
    
    @SuppressWarnings("unchecked")
    public static List getObjectsList(Class clazz,String whereStr, String orderStr) {
    	List list = null;
    	 
          Session sess = null;
          Transaction tx = null;
    	
    	try {
    		sess = HibernateDataUtil.getSessionFactory().openSession();
    		String hql="from "+ clazz.getName();
    		if(Strings.isNotEmpty(whereStr)){
    			hql=hql+" "+whereStr;
    			
    		}
    		
    		if(Strings.isNotEmpty(orderStr)){
    			hql=hql+" "+orderStr;
    		}
    		
    		list = sess.createQuery(hql).list();
    	} catch (RuntimeException re) {
            tx.rollback();
          
           
            re.printStackTrace();
        }
        catch (Exception e)
        {
            tx.rollback();
           
           
            e.printStackTrace();

        }
        finally {
            // close the Hibernate Session
            sess.close();
//        	HibernateDataUtil.shutdown();
        }

    	
    	
    	
    	return list;
    }


    public static List getObjectListBySql(String sql,Class clazz) {
     boolean result=true;
    	Session sess = null;
         Transaction tx = null;
    	
    	List list= new ArrayList();
      
        try {
        	sess = HibernateDataUtil.getSessionFactory().openSession();
        	Query query = sess.createSQLQuery(sql)
                       .setResultTransformer(
                                     Transformers.aliasToBean(clazz));
        	      
          list = query.list();
             
        }
        catch (RuntimeException re) {
            tx.rollback();
            result=false;
           
            re.printStackTrace();
        }
        catch (Exception e)
        {
            tx.rollback();
            result=false;
           
            e.printStackTrace();

        }
        finally {
            // close the Hibernate Session
            sess.close();
//        	HibernateDataUtil.shutdown();
        }
       
       
        
        
        
        
        return list;
    }

    public static boolean  executeHqlUpdate(String hql){
    	
    	boolean returnValue=false;
		Transaction tx = null;
		Session sess=null;
		
		try {
			sess = HibernateDataUtil.getSessionFactory().openSession();
			tx=sess.getTransaction();
		
			tx = sess.beginTransaction();
			
			Query query = sess.createQuery(hql);
			
			query.executeUpdate();
			
			tx.commit();
			
			returnValue=true;
		} catch (HibernateException ex) {
			
			if (tx != null){
				tx.rollback();
			}
			
		
		}catch(Exception ex){
			
			if (tx != null){
				tx.rollback();
			}
							
          
            ex.printStackTrace();
			
		}finally {
			 sess.close();
//	         HibernateDataUtil.shutdown();
		
		}
					
		return returnValue;
		
		
	}

    public static long getMaxColValue(String colName, String className){
    	String hql="select max("+colName+") from "+className;
    	long x=0;
    	try{
    			x=(long)HibernateDataDaoSupport.getHqlQueryObject(hql);
    	}catch(Exception ex){}
    	
    	return x;
    }





}