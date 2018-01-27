package com.rcp.wxh.utils;

import java.util.Arrays;

import com.rcp.wxh.communicate.CommunicateException;
import com.rcp.wxh.communicate.MachineException;
import com.sun.jna.Native;

/**
 * ��Ʊ������� ͨ�Ź��ߡ���
 * @author wuxuehong  2011-11-17
 *
 */
public class CardComm {
	
	private static CardCommLibrary cml;
	
	public static final String COM = "COM3"; //ͨ�Ŵ���
	
	//�������
	public static final byte SUCCESS = 0;// �ɹ�
	public static final byte FAIL = 1; //ʧ��
	//����ڿ���
	public static final  byte ENTERANCE = 1; //���
	public static final  byte EXITANCE = 2;   //����
	//��ȡ���ŷ�����Ϣ
	public static final int EXCEPTION = 0; //�����쳣
	public static final int GETCARDID = 1; //�����ɹ�
	public static final int CARDTOOLESS = 2; //������ 
	public static final int CARDBLOCK = 3; //�¿�
	public static final int CARDEMPTY = 4; //�����
	public static final int CONNECTFAIL = 5; //���Ӵ���
	
	/**
	 * ����function.dll
	 */
	static{
		Native.setProtected(true);
		cml = (CardCommLibrary) Native.loadLibrary("CardComm", CardCommLibrary.class);
System.out.println("********************************load dll!!");
	}
	
	
	/**
	 * ��ʼ���봮������
	 * true-�ɹ�
	 * @return
	 * @throws CommunicateException 
	 */
	public static boolean init() throws CommunicateException{
		boolean t = cml.InitComm(COM); 
		if(!t) throw new CommunicateException("���ڳ�ʼ��ʧ��!����ͨ�Ŵ�������!");
		return t;
	}
	
	/**
	 * ��ȡ�ն���Ϣ��
	 * b��ǰ�ĸ��ֽڴ洢����
	 * @param data
	 * @return  int   0-�쳣  1-������Ϣ  2-��������ʾ  3-�¿� 4-����� 5-���Ӵ��� 
	 * @throws MachineException 
	 * @throws CommunicateException 
	 */
	public static int read(byte[] data) throws MachineException, CommunicateException{
		int i = cml.WaitForPack(data);
		switch(i){
			case EXCEPTION:
				throw new MachineException("�¿����쳣!");
			case CARDTOOLESS:
				throw new MachineException("�¿����п���̫��!");
			case CARDBLOCK:
				throw new MachineException("�¿����п�Ƭ����!");
			case CARDEMPTY:
				throw new MachineException("�¿�����û����!");
			case CONNECTFAIL:
				throw new CommunicateException("�뿨��ͨ���쳣:��ȡ�ն���Ϣʧ��!");
		}
		return i;
	}
	
	/**
	 * ���ź�̨��֤����
	 * @param carNum
	 * @param sta  0:��֤�ɹ�  1:��֤ʧ��
	 * @param device  1-���  2-����
	 * @return 
	 * @throws CommunicateException 
	 */
	public static boolean write(byte[] carNum, byte sta, byte device) throws CommunicateException{
		boolean t = cml.SendCardNumRep(carNum, sta, device);
		if(!t) throw new CommunicateException("�뿨��ͨ���쳣:��Ƭ��֤����ʧ��!");
		return t;
	}
	
	/**
	 * ����ϵͳʱ��
	 * @param device  1-���  2-����
	 * @return
	 * @throws CommunicateException 
	 */
	public static boolean setTime(byte device) throws CommunicateException{
		boolean t = cml.SetUnixTime(device);
		if(!t) throw new CommunicateException("�뿨��ͨ���쳣:����ϵͳʱ��ʧ��!");
		return t;
	} 
	
	/**
	 * ��բ����
	 * @param device   1-���  2-����
	 * @return
	 * @throws CommunicateException 
	 */
	public static boolean stickUp(byte device) throws CommunicateException{
		boolean t = cml.OpenDoor(device);
		if(!t) throw new CommunicateException("�뿨��ͨ���쳣:֪ͨ�ն˿�բʧ��!");
		return t;
	}
	
	/**
	 * ��բ����
	 * @param device   1-���  2-����
	 * @return
	 * @throws CommunicateException 
	 */
	public static boolean stickDown(byte device) throws CommunicateException{
		boolean t = cml.CloseDoor(device);
		if(!t) throw new CommunicateException("�뿨��ͨ���쳣:֪ͨ�ն���բʧ��!");
		return t;
	}
	
	public static boolean stickStop(byte device) throws CommunicateException{
		boolean t = cml.StopDoor(device);
		if(!t) throw new CommunicateException("�뿨��ͨ���쳣:֪ͨ�ն�ͣբ!");
		return t;
	}
	
	public static boolean setTimeMoney(int minute, int yuan ,int jiao) throws CommunicateException{
System.out.println("ʱ��:"+minute+"\t\t����:"+yuan+"Ԫ\t"+jiao+"����!");
		boolean t = cml.SetTimeMoney(minute, yuan, jiao);
		if(!t) throw new  CommunicateException("�뿨��ͨ���쳣:֪ͨͣ��ʱ�估����!");
		return t;
	}
	
	/**
	 * just for test!!!
	 */
	public static void read(){
		boolean init = cml.InitComm("COM1");
System.out.println(init);
		if(init){
			while(true){
				byte[] b = new byte[100];
				int a = cml.WaitForPack(b);
				switch(a){
				case 0:
					System.out.println("exception");
					break;
				case 1:
					System.out.println(bytes2long(b, 0));
					cml.SendCardNumRep(b, (byte)0,(byte)1);
					break;
				case 2:
					System.out.println("������");
					break;
				case 3:
					System.out.println("�¿����߹���");
					break;
				case 4:
					System.out.println("�����");
					break;
				default:
					System.out.println("δ֪��");
				}
			}
		}
	}
	
	/**
	 * �ֽ�����ת����long
	 * ��ȡǰ�ĸ��ֽ�
	 * @param b
	 * @param startIndex
	 * @return
	 */
	private static long bytes2long(byte[] b,int startIndex){ 
		long re = 0; 
		for(int i=0;i<3;i++){
			re <<= 8;
		}
		re <<= 8;
		re += 1;
		for(int i = startIndex; i < startIndex+4; i++){
			re <<= 8;
			re += b[i]; 
		} 
		return re; 
	 } 
	
	public static void main(String args[]) throws InterruptedException, MachineException{
		try {
			boolean t = init();
		System.out.println(t);
//			byte[] b = new byte[100];
//			int a = read(b);
		} catch (CommunicateException e2) {
			e2.printStackTrace();
		}
//       int count = 0;
//		while(true){
//			try {
//				stickUp(EXITANCE);
//				Thread.sleep(100);
//				stickDown(EXITANCE);
//			} catch (CommunicateException e1) {
//				e1.printStackTrace();
//			}
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		
	}

}
