package com.lhp.unit;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Customer
{

	public Customer(String name, String age)
	{
		super();
		this.name = name;
		this.age = age;
	}

	private String name;
	private String age;
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getAge()
	{
		return age;
	}
	public void setAge(String age)
	{
		this.age = age;
	}
	
	public String getCity()
	{
		return "beijing";
	}
	
	@JsonIgnore
	public String getBrith()
	{
		return "01-22";
	}
	
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException
	{
		//1.导入jar包(jackson-all-1.7.6.jar)
		//2.创建ObjectMapper对象
		ObjectMapper mapper =new ObjectMapper();
		//3.调用mapper的WriteValueAsString()方法把一个对象转化成json字符串
		Customer customer=new Customer("李营", "20");
		String jsonStr=mapper.writeValueAsString(customer);
		System.out.println(jsonStr);
		//4.注意：JakeSon对象使用get方法来定位JSON对象的属性
		//5.通过添加注解(@JsonIgnore)来忽略某一个get定义的属性
		
		//转集合：
		List<Customer> customers = Arrays.asList(customer,new Customer("yue", "19"));
		jsonStr = mapper.writeValueAsString(customers);
		System.out.println(jsonStr);
	}
}
