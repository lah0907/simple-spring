package cn.edu.myspring.di.config; 
  
/**  
 * ���ã�
 *
 * @author:	liyazhou   
 * @version:2016��9��24������7:34:14 
 */
public class Property {
	private String name;
	private String value;
	private String ref;
	
	public Property() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	@Override
	public String toString() {
		return "Property [name=" + name + ", value=" + value + ", ref=" + ref + "]";
	}
	
}
   