package cn.edu.myspring.di.config;

import java.util.ArrayList;
import java.util.List;

/**  
 * ���ã�
 *
 * @author:	liyazhou   
 * @version:2016��9��24������7:34:01 
 */
public class Bean {
	private String id;
	private String className;
	
	// ֻ���� singleton��prototype��������
	private String scope;
	
	private List<Property> properties = new ArrayList<Property>();
	
	public Bean() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public List<Property> getProperties() {
		return properties;
	}

	@Override
	public String toString() {
		return "Bean [id=" + id + ", className=" + className + ", scope=" + scope + "]";
	}
	
}
   