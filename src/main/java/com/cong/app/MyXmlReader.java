package com.cong.app;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.cong.entity.Student;

/**
 * 利用dom4j对xml文件操作
 * 		1、获取文件路径
 * 		2、解析文件
 * @author cong
 *
 */
@SuppressWarnings("unchecked")
public class MyXmlReader {
	
	public static void main(String[] args) throws DocumentException {
		//读取arthas.xml文件
		List<String> list = parse("arthas.xml");
		System.out.println(list);
		
		//读取data.xml文件
		List<Student> list1 = parse1("data.xml");
		System.out.println(list1);
		
	}
	/**
	 * 读取arthas.xml文件中的base-package的值
	 * @param name
	 * @return
	 * @throws DocumentException
	 */
	public static List<String> parse(String name) throws DocumentException{
		List<String> list = new ArrayList<String>();
		//1、获取文件路径
		String filePath = MyXmlReader.class.getClassLoader().getResource(name).getPath();
		File file = new File(filePath);
		
		//2、解析文件
		SAXReader render = new SAXReader();
		Document document = render.read(file);//装载整个xml文件
		Element rootEle = document.getRootElement();//获取根节点
		Iterator<Element> iterator = rootEle.elementIterator();//遍历所有节点
		while(iterator.hasNext()) {
			Element element = iterator.next();
			if(element.getName().equals("action")) {
				list.add(element.attributeValue("base-package"));
			}
		}
		return list;
	}
	
	/**
	 * 读取data.xml文件中student节点下的数据
	 * @param name
	 * @return
	 * @throws DocumentException
	 */
	public static List<Student> parse1(String name) throws DocumentException{
		List<Student> list = new ArrayList<Student>();
		//1、读取文件
		String filePath = MyXmlReader.class.getClassLoader().getResource(name).getPath();
		File file = new File(filePath);
		
		//2、解析文件
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);//装载整个xml文件
		Element root = document.getRootElement();//获取根节点
		Iterator<Element> iterator = root.elementIterator("student");//遍历节点为student的内容
		while(iterator.hasNext()) {
			Student student = new Student();
			Element element = iterator.next();
			student.setId(element.elementText("id"));
			student.setName(element.elementText("name"));
			student.setAddr(element.elementText("addr"));
			list.add(student);
		}
		return list;
	}


}
