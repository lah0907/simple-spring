package cn.edu.myspring.di.bean; 
  
/**  
 * ���ã�
 *
 * @author:	liyazhou   
 * @version:2016��9��24������7:33:31 
 */
public class A {
	private String name;
	private Integer age;
	
	public A() {
		super();
		System.out.println("����һ��A����.");
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "A [name=" + name + ", age=" + age + "]";
	}
	
}
   