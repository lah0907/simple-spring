package cn.edu.myspring.di.bean; 
  
/**  
 * ���ã�
 *
 * @author:	liyazhou   
 * @version:2016��9��24������7:33:45 
 */
public class C {
	private String name;
	private B b;
	
	public C(){
		super();
		System.out.println("����һ��C����.");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}

	@Override
	public String toString(){
		return "C["+ name + ", " + b +"]";
	}
}
   