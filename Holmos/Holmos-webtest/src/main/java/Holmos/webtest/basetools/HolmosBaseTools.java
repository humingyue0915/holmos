package Holmos.basetools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import Holmos.constvalue.ConfigConstValue;
import Holmos.constvalue.ConstValue;
import Holmos.exceptions.HolmosFailedError;
import Holmos.webtest.Allocator;
import Holmos.webtest.element.Element;
import Holmos.webtest.struct.Collection;
import Holmos.webtest.struct.Frame;
import Holmos.webtest.struct.Page;
import Holmos.webtest.struct.SubPage;
import Holmos.webtest.tools.HolmosWindow;
/**
 * @author ������(15857164387)
 * */
public class HolmosBaseTools {
	/**������־�ļ��ĵ�ַ����ӡ��Ҫ����־<br>
	 * �����־�ļ���ַ�Ҳ�������ô���ڿ������Ĭ�ϵĵ�ַ������־�ļ�<br>*/
	public static void configLogProperties(){
		String logDirPath=getLogDirPath();
		File file=new File(logDirPath);
		if(!file.exists()){
			try {
				if(file.mkdirs()){
					new File(logDirPath+"\\log.log").createNewFile();
					new File(logDirPath+"\\error.log").createNewFile();
				}
			} catch (Exception e1) {
				System.out.println("������־�ļ������ڣ��������ļ�ʧ�ܣ�����ϵ��ͥ��");
				e1.printStackTrace();
			}
		}
		PropertyConfigurator.configure(ConfigConstValue.LOGCONFIG);
	}
	/**�����־Ŀ¼��ַ
	 * @return ��־Ŀ¼��ַ
	 * @throws FileNotFoundException 
	 * */
	private static String getLogDirPath(){
		Properties properties=new Properties();
		try{
			InputStream in = new BufferedInputStream (new FileInputStream(ConfigConstValue.HOLMOSCONFFILE));
			properties.load(in);
			return HolmosPropertiesTool.getValue(properties, "logdir");
		}catch(IOException e){
			throw new HolmosFailedError("holmos��������ļ��Ҳ���!"); 
		}
	}
	/**
	 * �������úõĽ�����ŵ�ַ������õ�ַĿ¼�����ڣ��򴴽���������ڣ���������
	 * */
	public static void configScreenShotLocation(){
		String screenShotDir=getScreenShotDirPath();
		ConstValue.screenShotDir=screenShotDir;
		File file=new File(screenShotDir);
		if(!file.exists()){
			try {
				if(file.mkdirs()){
					System.out.println("������ַ�����ɹ�!");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * ��Holmos�����ļ���ȡ�ý�����ַ
	 * */
	public static String getScreenShotDirPath(){
		if(ConstValue.screenShotDir!=null)
			return ConstValue.screenShotDir;
		Properties properties=new Properties();
		try{
			InputStream in = new BufferedInputStream (new FileInputStream(ConfigConstValue.HOLMOSCONFFILE));
			properties.load(in);
			return HolmosPropertiesTool.getValue(properties, "screenShotDir");
		}catch(IOException e){
			throw new HolmosFailedError("holmos��������ļ��Ҳ���!"); 
		}
	}
	/**��ǰ�߳���ϢmiliSeconds����*/
	public static void sleep(int miliSeconds){
		try {
			Thread.sleep(miliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**����target����ҳ�棬����target���ж��Ƿ�����ҳ���*/
	public static void openByTarget(String url,String target){
		if(target==null){
			Allocator.getInstance().currentWindow.open(url);
		}else if(target.equalsIgnoreCase(ConstValue.BLANK)){
			HolmosWindow.openNewWindow(url);
		}else if(target.equalsIgnoreCase(ConstValue.SELF)){
			Allocator.getInstance().currentWindow.open(url);
		}
	}
	/**���Element������ע��˵�����ڷ�������۲��ߵ�ʱ��������������ּӵ�˵������*/
	public static void insertElementName(Element element,String name){
		StringBuilder commentTemp=new StringBuilder(element.getComment());
		commentTemp.insert(1, name);
		element.setComment(commentTemp.toString());
	}
	/**���SubPage������ע��˵�����ڷ�������۲��ߵ�ʱ��������������ּӵ�˵������*/
	public static void insertSubPageName(SubPage subpage,String name){
		StringBuilder commentTemp=new StringBuilder(subpage.getComment());
		commentTemp.insert(1, name);
		subpage.setComment(commentTemp.toString());
	}
	/**���Page������ע��˵�����ڷ�������۲��ߵ�ʱ��������������ּӵ�˵������*/
	public static void insertPageName(Page page,String name){
		StringBuilder commentTemp=new StringBuilder(page.getComment());
		commentTemp.insert(1, name);
		page.setComment(commentTemp.toString());
	}
	/**���Frame������ע��˵�����ڷ�������۲��ߵ�ʱ��������������ּӵ�˵������*/
	public static void insertFrameName(Frame frame,String name){
		StringBuilder commentTemp=new StringBuilder(frame.getComment());
		commentTemp.insert(1, name);
		frame.setComment(commentTemp.toString());
	}
	/**���Collection������ע��˵�����ڷ�������۲��ߵ�ʱ��������������ּӵ�˵������*/
	public static void insertCollectionName(Collection collection,String name){
		StringBuilder commentTemp=new StringBuilder(collection.getComment());
		commentTemp.insert(1, name);
		collection.setComment(commentTemp.toString());
	}
	
}
