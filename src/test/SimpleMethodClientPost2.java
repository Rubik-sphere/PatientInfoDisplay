package test;

/*
SimpleMethodClient.java

Description:

This simple Java class uses the Apache HttpClient class library to connect as a client to the
Medidata Rave Web Service (Inbound) over SSL and enables an ODM 1.3.x data file to be loaded
into Rave versions 5.6.3.

Build Details:

   Requires the Apache class libraries,
   commons-codec-1.3.jar
   commons-httpclient-3.1.jar
   commons-logging-1.1.1.jar

   Compiled using Sun SDK EE5 update 5

   NOTE: Modify the user and host details accordingly before compiling.

Usage:
   Simply run from the command line after successfully compiling.
   The program expects to find an ODM 1.3 data file named 'odm13data.xml' in the path.

Copyright Medidata Solutions 2008
*/


import java.io.File;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.transaction.SystemException;

import java.io.BufferedReader;

import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.FileRequestEntity;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.hibernate.Hibernate;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.hibernate.utils.HibernateDataDaoSupport;
import com.rwrs.hibernate.entity.Subject;

import antlr.collections.List;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import static org.quartz.SimpleScheduleBuilder.*;

/**
SimpleMethodClient class
Acts as a simple web service client to enable loading of ODM 1.3 data into Rave
**/
public class SimpleMethodClientPost2{


	public static void main(String[] args) throws Exception{
		
		Subject getById = (Subject)HibernateDataDaoSupport.getObjectById(Subject.class, 6);	
		System.out.println("Item 6 is: " + getById.getUserName());
		
		ArrayList<Subject> getAllList = HibernateDataDaoSupport.getAll(Subject.class, null, null);
		System.out.println("Get all items: " + getAllList.toString());
		
	}



}





