package com.rcp.wxh.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import com.rcp.wxh.pojo.TEmp;

/**
 * �Զ���¼���ƹ�����
 * @author wuxuehong  2011-11-7
 *
 */

public class AutomLoginUtil {
	
	/**
	 * emp�� 
	 * priority    -1��ʾ��   0-��ʾ��ס����   1-��ʾ�Զ���½��¼
	 */
	private String filename = "config.lib"; 
	
	private boolean rePwd;  //�Ƿ��ס����
	private boolean autLogin;  //�Ƿ��Զ���½
	
	
	
	public boolean isRePwd(TEmp emp) {
		if(emp.getPriority()==-1)
			 return false;
		else
			return true;
	}

	public void setRePwd(boolean rePwd,TEmp emp) {
		if(rePwd)
			emp.setPriority(0);
		else
			emp.setPriority(-1);
	}

	public boolean isAutLogin(TEmp emp) {
		if(emp.getPriority()==1)
			return true;
		else
			return false;
	}

	public void setAutLogin(boolean autLogin, TEmp emp) {
		if(autLogin){
			emp.setPriority(1);
		}else{
			emp.setPriority(0);
		}
	}

	/**
	 * ��ָ�����ļ��ж�ȡ��¼�û��Ķ���
	 * @param filename
	 * @return
	 */
	public Object readObject(){
		File file = new File(filename);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			try {
				Object o = ois.readObject();
				return o;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ������д��ָ���ļ�
	 * @param filename
	 * @param obj
	 */
	public void writeObject(Object obj){
		File file = new File(filename);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			oos.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * test
	 * @param args
	 */
	public static void main(String args[]){
		AutomLoginUtil alu = new AutomLoginUtil();
		
//		TEmp emp = new TEmp();
//		emp.setOperatorid("admin");
//		emp.setPassword("123456");
		TEmp emp = (TEmp) alu.readObject(); 	//  emp priority  1-��ʾ
		System.out.println(emp.getOperatorid()+"\t\t"+emp.getPassword());
	}
		

}
