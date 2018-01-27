package com.rcp.wxh.utils;

import com.sun.jna.Library;

/**
 * ��Ʊ����ư�ͨѶ  ������
 * @author wuxuehong  2011-11-17
 *
 */
public interface CardCommLibrary extends Library{

	/**
	 * ��ʼ��ͨ�Ŵ���   
	 * @param com   ���ں� ��"COM1","COM2"
	 * @return  false:��ʼ��ʧ��   true:��ʼ���ɹ�
	 */
	public boolean InitComm(String com);
	
	/**
	 * ��ȡ���յ������ݰ�
	 * @param data  ��������ݰ�  b[0]: 1-���   2������
	 * @return  0:�������
	 *          1:������Ϣ  data 1-4�ֽڴ洢����
	 *          2:������
	 *          3:�¿����߹���
	 *          4:�����
	 *          5:����������Ӵ���
	 */
	public int WaitForPack(byte[] data);
	
	/**
	 * ���Ϳ�����֤����
	 * @param carNum   �ĸ��ֽڵĿ���
	 * @param sta  0:��֤�ɹ�  1����֤ʧ��
	 * @param device  1�����   2������
	 * @return  true:���ͳɹ�   false:����ʧ��
	 */
	public boolean SendCardNumRep(byte[] carNum, byte sta, byte device);
	
	/**
	 * ����ϵͳʱ��
	 * @param device  1:���    2������
	 * @return  true:���ͳɹ�   false������ʧ��
	 */
	public boolean SetUnixTime(byte device);
	
	/**
	 * ̧բ
	 * @param device  1����� 2������
	 * @return  true:���ͳɹ�  false:����ʧ��
	 */
	public boolean OpenDoor(byte device);
	
	/**
	 * ��բ
	 * @param device  1�����    2������ 
	 * @return   true:���ͳɹ�     false:����ʧ��
	 */
	public boolean CloseDoor(byte device);
	
	/**
	 * ͣբ
	 * @param device 1�����    2������ 
	 * @return  true:���ͳɹ�     false:����ʧ��
	 */
	public boolean StopDoor(byte device);
	
	/**
	 * ����ͣ��ʱ��ͷ��õ��ն�
	 * @param minute  ����
	 * @param yuan    Ԫ
	 * @param jiao   ��
	 * @return
	 */
	public boolean SetTimeMoney(int minute, int yuan, int jiao);

}
