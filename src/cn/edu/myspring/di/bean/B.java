package cn.edu.myspring.di.bean; 
  
/**  
 * ���ã�
 *
 * @author:	liyazhou   
 * @version:2016��9��24������7:33:38 
 */
public class B {
	private String name;
	private A objA;
	
	public B(){
		super();
		System.out.println("����һ��B����.");
	}
	
	public void setObjA(A objA){
		this.objA = objA;
	}
	
	public A getObjA(){
		return this.objA;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "B [name=" + name + ", objA=" + objA + "]";
	}
}
   