package cn.edu.myspring.di.main; 
  
/**  
 * ���ã�
 *
 * @author:	liyazhou   
 * @version:2016��9��24������7:34:52 
 */
public interface BeanFactory {
	// ����bean��id��ȡ��Bean����
	Object getBean(String id);
}
   