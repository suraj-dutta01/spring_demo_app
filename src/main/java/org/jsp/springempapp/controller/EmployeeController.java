package org.jsp.springempapp.controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import org.jsp.springempapp.EmployeeConfig;
import org.jsp.springempapp.dao.EmployeeDao;
import org.jsp.springempapp.dto.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

public class EmployeeController {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		ApplicationContext context=new AnnotationConfigApplicationContext(EmployeeConfig.class);
		EmployeeDao dao=context.getBean(EmployeeDao.class);
		System.out.println("1.Save Employee");
		System.out.println("2.Update Employee");
		System.out.println("3.Find employee by Id");
		System.out.println("4.Verify Employee by phone and password");
		System.out.println("5.Verify Employee by email and password");
		System.out.println("6.Verify Employee by id and password");
		System.out.println("7.Find employee by salery");
		System.out.println("8.Find employee by designation");
		System.out.println("9.Find employee by name");
		System.out.println("10.Find employee between a salery range");
		switch(sc.nextInt()) {
		case 1:{
			Employee e=new Employee();
			System.out.println("please enter the name,phone,email,designation,salery,password");
			e.setName(sc.next());
			e.setPhone(sc.nextLong());
			e.setEmail(sc.next());
			e.setDesignation(sc.next());
			e.setSalery(sc.nextDouble());
			e.setPassword(sc.next());
			e=dao.saveEmployee(e);	
			System.out.println("Employee saved with id "+e.getId());
			break;
		}
        case 2:{
        	Employee e=new Employee();
			System.out.println("please enter the id,name,phone,email,designation,salery,password");
			e.setId(sc.nextInt());
			e.setName(sc.next());
			e.setPhone(sc.nextLong());
			e.setEmail(sc.next());
			e.setDesignation(sc.next());
			e.setSalery(sc.nextDouble());
			e.setPassword(sc.next());
			e=dao.updateEmployee(e);	
			if(e!=null)
			System.out.println("Employee updated with id "+e.getId());
			else 
			System.err.println("unable to update");	
			break;			
		}
        case 3:{
        	System.out.println("please enter employee to find");
        	Employee emp=dao.findEmployeeById(sc.nextInt());
        	if(emp!=null) {
        		System.out.println(emp);
        	}else {
				System.err.println("Not found any employee with this id");
			}
	        break;
        }
        case 4:{
        	System.out.println("please enter employee phone and password");
        	long phone=sc.nextLong();
        	String password=sc.next();
        	Employee emp=dao.verifyEmployee(phone, password);
        	if(emp!=null) {
        		System.out.println(emp);
        	}else {
				System.out.println("No employe present with this details");
			}
	        break;
        }
        case 5:{
        	System.out.println("please enter employee email and password");
        	String email=sc.next();
        	String password=sc.next();
        	Employee emp=dao.verifyEmployee(email, password);
        	if(emp!=null) {
        		System.out.println(emp);
        	}else {
				System.out.println("No employe present with this details");
			}
	        break;
        }
        case 6:{
        	System.out.println("please enter employee id and password");
        	int id=sc.nextInt();
        	String password=sc.next();
        	Employee emp=dao.verifyEmployee(id, password);
        	if(emp!=null) {
        		System.out.println(emp);
        	}else {
				System.out.println("No employe present with this details");
			}
	        break;
	
        }
        case 7:{
	         System.out.println("please enter the salery to find employee");
	         double salery=sc.nextDouble();
	         List<Employee> employees=dao.findEmployeeBySalery(salery);
	         if(employees!=null) {
	        	 for(Employee emp:employees) {
	        		 System.out.println(emp);
	        	 }
	         }else {
				System.out.println("Not Found eny employee by this salery");
			}
	         break;
        }
        case 8:{
	         System.out.println("please enter the designation to find employee");
	         String designation=sc.next();
	         List<Employee> employees=dao.findEmployeeByDesignation(designation);
	         if(employees!=null) {
	        	 for(Employee emp:employees) {
	        		 System.out.println(emp);
	        	 }
	         }else {
				System.out.println("Not Found eny employee by this designation");
			}
	         break;
	
        }
        case 9:{
	         System.out.println("please enter the name to find employee");
	         String name=sc.next();
	         List<Employee> employees=dao.findEmployeeByName(name);
	         if(employees!=null) {
	        	 for(Employee emp:employees) {
	        		 System.out.println(emp);
	        	 }
	         }else {
				System.out.println("Not Found eny employee by this name");
			}
	         break;
	
        }
        case 10:{
	         System.out.println("please enter the salery range to find employee");
	         System.out.println("start salery");
	         double startSalery=sc.nextDouble();
	         System.out.println("end salery");
	         double endSalery=sc.nextDouble();
	         List<Employee> employees=dao.findEmployeeBetweenSaleryrange(startSalery, endSalery);
	         if(employees!=null) {
	        	 for(Employee emp:employees) {
	        		 System.out.println(emp);
	        	 }
	         }else {
				System.out.println("Not Found eny employee between this salery range");
			}
	         break;
	
        }
        default:{
        	System.out.println("invalid choice");
        }
		}
		
		
	}

}
