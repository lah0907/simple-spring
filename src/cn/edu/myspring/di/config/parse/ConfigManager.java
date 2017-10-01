package cn.edu.myspring.di.config.parse;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.edu.myspring.di.config.Bean;
import cn.edu.myspring.di.config.Property;

/**  
 * ���ã�
 *
 * @author:	liyazhou   
 * @version:2016��9��24������7:34:36 
 */
public class ConfigManager {
	
	public static Map<String, Bean> getConfig(String path){
		// 1. �������ض���Map����
		// 2. dom4j����applicationContext.xml�����ļ�
			// 2.1  ����������
		  	// 2.2 ���������ļ���document����
		   	// 2.3 ����XPath���ʽ��ȡ�����е�BeanԪ��
			// 2.4 ��BeanԪ�ؽ��б���
		// 3.���� Map����
		
		// 1. �������ض���Map����
		Map<String, Bean> map = new HashMap<String, Bean>();
		
		// 2. dom4j����applicationContext.xml�����ļ�
		// 2.1  ����������
		SAXReader reader = new SAXReader();
		
		// 2.2 ���������ļ���document����
		InputStream in = ConfigManager.class.getResourceAsStream(path);
		Document doc = null;
		try {
			doc = reader.read(in);
		} catch (DocumentException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException("��ȡ�����ļ������쳣��");
		}
		
		// 2.3 ����XPath���ʽ��ȡ�����е�BeanԪ��
		String xpath = "//bean";
		
		// 2.4 ��BeanԪ�ؽ��б���
		@SuppressWarnings("unchecked")
		List<Element> list = doc.selectNodes(xpath);
		if(list!=null){
			for(Element eleBean : list){
				Bean bean = new Bean();
				// ȡ��eleBean�е� name/class/scope����ֵ����װ��Bean��
				String id = eleBean.attributeValue("id");
				String className = eleBean.attributeValue("class");
				String scope = eleBean.attributeValue("scope");
				// name��class������ֵ����String���ͣ�����ֱ��ʹ��setter����
				bean.setId(id);
				bean.setClassName(className);
				// scope����ֵ����Ϊ null
				bean.setScope(scope);
				
				// ȡ����ǰbeanԪ�ص����е�property��Ԫ�أ�������name/value/refװ��װ��Property������
				@SuppressWarnings("unchecked")
				List<Element> children = eleBean.elements("property");
				if(children != null){
					for(Element eleProp : children){
						Property property = new Property();
						
						String pName = eleProp.attributeValue("name");
						String pValue = eleProp.attributeValue("value");
						String pRef = eleProp.attributeValue("ref");
						
						property.setName(pName);
						property.setValue(pValue);
						property.setRef(pRef);
						
						// ��property��Ԫ�ط�װ��bean��
						bean.getProperties().add(property);
					}
				}
				// �������õ�Bean����ŵ�map��
				map.put(id, bean);
			}
		}
		
		// 3.���� Map����
		return map;
	}
}
   