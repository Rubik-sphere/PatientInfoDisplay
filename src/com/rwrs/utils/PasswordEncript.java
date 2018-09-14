package com.rwrs.utils;

import java.security.MessageDigest;

public class PasswordEncript {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	 String enPassword=	getEncriptPassword("Everest1");
	 System.out.println(enPassword);		
	
	}



	public static String getEncriptPassword(String password){
		String enPassword="";
		try{	
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			enPassword=sb.toString();
		}catch(Exception ex){

		}

		return  enPassword;

	}




}
