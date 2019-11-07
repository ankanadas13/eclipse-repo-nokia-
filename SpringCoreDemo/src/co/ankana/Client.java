package co.ankana;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


public class Client {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

       // Object Creation | done by Developers		
		Employee eRef=new Employee();
		eRef.setEid(101);
		eRef.setEname("John Watson");
		eRef.setEaddress("Redwood Shores");
		
		System.out.println("Employee Details: "+eRef);
		
		//Spring Way | IOC (Inversion of Control)
		Resource resource=new ClassPathResource("employee.xml");
		BeanFactory factory=new XmlBeanFactory(resource);//Spring Container which shall parse the XML file to construct objects
		
		Employee e1=(Employee)factory.getBean("emp1");
		Employee e2=(Employee)factory.getBean("emp2",Employee.class);
		
		System.out.println("Employee 1 Details :"+e1);
		System.out.println("Employee 2 Details :"+e2);
		
		
		
	}

}

