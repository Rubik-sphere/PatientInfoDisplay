package test;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.util.Strings;

import com.hibernate.utils.HibernateDataDaoSupport;
import com.opensymphony.xwork2.ActionSupport;
import com.rwrs.hibernate.entity.Subject;




public class SubjectAction extends ActionSupport{

	 private static final long serialVersionUID = -6659925652584240539L;
	  
	 private List<Subject> subjectList = new ArrayList<Subject>();
	
	 private List<Subject> subjectDropdownList = new ArrayList<Subject>();
	 
	 private int[] selectId;
	 
	 public String  listAllSubject() {
		
		
		
		subjectList=SubjectDataUtils.getSubjectList(null);
		
	
		return SUCCESS;
	}

	
	 
	 public String  getSelectSubject() {
		 subjectDropdownList=SubjectDataUtils.getSubjectList(null);	
		
		 if(selectId!=null&&selectId.length>0){
		 
		 String whereStr=" where userId in "	+Arrays.toString(selectId).replace("[", "(").replace("]", ")");
			
		 System.out.println("whereStr==="+whereStr); 
		
		
		 subjectList=SubjectDataUtils.getSubjectList(whereStr);
		
		 }
		return SUCCESS;
	}
	 
	 
	 
	 public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}



	public List<Subject> getSubjectDropdownList() {
		return subjectDropdownList;
	}



	public void setSubjectDropdownList(List<Subject> subjectDropdownList) {
		this.subjectDropdownList = subjectDropdownList;
	}



	public int[] getSelectId() {
		return selectId;
	}



	public void setSelectId(int[] selectId) {
		this.selectId = selectId;
	}
	
	
	public static void main(String args[]) {
		List<Subject> a = SubjectDataUtils.getSubjectList(null);
		System.out.println(a.toString());
	}


	
	
	
	
	






}