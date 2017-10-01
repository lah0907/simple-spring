package cn.edu.myspring.di.main;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import cn.edu.myspring.di.config.Bean;
import cn.edu.myspring.di.config.Property;
import cn.edu.myspring.di.config.parse.ConfigManager;

/**  
 * ���ã�
 *
 * @author:	liyazhou   
 * @version:2016��9��24������7:35:29 
 */
public class ClassPathXmlApplicationContext implements BeanFactory {
	
	
	Map<String, Object> context;
	Map<String, Bean> config;   // �����ļ���Ϣ
	
	public ClassPathXmlApplicationContext(String path) {
		super();
		// 0.��ʼ������
		// 1.�����������ļ�����Ϣ
		// 2.����������Ϣ��ʼ��bean
		
		// 0. ��ʼ������
		context = new HashMap<String, Object>();
		
		// 1.�����������ļ�����Ϣ
		config = ConfigManager.getConfig(path);
		
		// 2.����������Ϣ��ʼ��bean
		if(config!=null){
			for(Map.Entry<String, Bean> entry : config.entrySet()){
				String id = entry.getKey();
				Bean configBean = entry.getValue();
				// ����bean���ã�����Bean����
				Object objBean = createBean(configBean);
				// System.out.println(objBean);
				
				// 3.����ʼ���õ�Bean������뵽������
				// ֻ�� scope�� singleton����ʱ���Ž�������뵽������
				if("singleton".equals(configBean.getScope())){
					context.put(id, objBean);
				}
			}
		}
		
	}

	// ����bean��������Ϣ����obj����
	private Object createBean(Bean configBean){
		// 1.��ȡ��Bean���ֽ�����󣬴���Bean����
		String className = configBean.getClassName();
		@SuppressWarnings("rawtypes")
		Class clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("����"+configBean.getId()+"��class������Ϣ.");
		}
		
		Object objBean = null;
		try {
			objBean = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("�������"+ className +"�Ƿ����޲εĹ��췽��.");
		}
		
		// 2.��ȡBean�����ԣ�����ע�뵽Bean������
		// 2.1 �������value����ע��
		// 2.2 ���������ref����ע�룬��Ҫ��ȡ�������õĶ���
		
		if(!configBean.getProperties().isEmpty()){
			for(Property prop : configBean.getProperties()){
				// 2.1 �������valueֵ��ע��
				if(prop.getValue() != null){
					try {
						// BeanUtils.setProperty()��������ʵ�ֶ�valueֵ������ת��
						BeanUtils.setProperty(objBean, prop.getName(), prop.getValue());
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
						throw new RuntimeException("���� "+ configBean.getId() + " ��������Ϣ.");
					}
				}
				
				// 2.2 ���������refֵ��ע�룬��Ҫ��ȡ�������õĶ���
				if(prop.getRef() != null){
					String refId = prop.getRef();
					
					Object existRef = context.get(refId);
					if(existRef == null){
						existRef = createBean(config.get(refId));
						// �´�������󣬾����Ƿ�����뵽�����У�ʱ����
						// ֻ�е� scope����ֵΪ singletonʱ���Ž�Bean������뵽������
						if("singleton".equals(config.get(refId).getScope())){
							context.put(refId, existRef);
						}
					}
					
					try {
						BeanUtils.setProperty(objBean, prop.getName(), existRef);
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
						throw new RuntimeException("���� "+ configBean.getId() + " ��������Ϣ.");
					}
					
				}
			}
		}
		
		return objBean;
	}
	
	// ����id�������л�ȡ�� Bean����
	@Override
	public Object getBean(String id) {
		Object objBean = context.get(id);
		// ���� scope�� prototype���ͣ��������оͲ�����ڸ�Bean����
		if(objBean == null){
			objBean = createBean(config.get(id));
		}
		
		return objBean;
	}

}
   