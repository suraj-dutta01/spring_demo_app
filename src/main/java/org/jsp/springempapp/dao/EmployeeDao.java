package org.jsp.springempapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.springempapp.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {
	@Autowired
   private EntityManager entityManager;
	
	
	public Employee saveEmployee(Employee employee) {
		EntityTransaction transection=entityManager.getTransaction();
		entityManager.persist(employee);
		transection.begin();
        transection.commit();
        return employee;
	}
	public Employee updateEmployee(Employee employee) {
		EntityTransaction transaction=entityManager.getTransaction();
	    Employee dbEmployee =entityManager.find(Employee.class,employee.getId());
	    if(dbEmployee!=null) {
	    	dbEmployee.setName(employee.getName());
	    	dbEmployee.setPhone(employee.getPhone());
	    	dbEmployee.setEmail(employee.getEmail());
	    	dbEmployee.setDesignation(employee.getDesignation());
	    	dbEmployee.setSalery(employee.getSalery());
	    	dbEmployee.setPassword(employee.getPassword());
	    	transaction.begin();
            transaction.commit();
            return dbEmployee;
	    }
	    return null;
	}
	public Employee findEmployeeById(int id) {
	return entityManager.find(Employee.class,id);		
	}
	public Employee verifyEmployee(long phone,String password) {
		Query q = entityManager.createQuery("select e from Employee e where e.phone=?1 and e.password=?2");
		q.setParameter(1,phone);
		q.setParameter(2,password);
		try {
		return (Employee) q.getSingleResult();
		
		}catch (NoResultException e) {
			return null;
		}
	}
	public Employee verifyEmployee(String email,String password) {
		Query q = entityManager.createQuery("select e from Employee e where e.email=?1 and e.password=?2");
		q.setParameter(1,email);
		q.setParameter(2,password);
		try {
		return (Employee) q.getSingleResult();
		
		}catch (NoResultException e) {
			return null;
		}
	}
	public Employee verifyEmployee(int id,String password) {
		Query q = entityManager.createQuery("select e from Employee e where e.id=?1 and e.password=?2");
		q.setParameter(1,id);
		q.setParameter(2,password);
		try {
		return (Employee) q.getSingleResult();
		
		}catch (NoResultException e) {
			return null;
		}
	}
	public List<Employee> findEmployeeBySalery(double salery) {
		Query q=entityManager.createQuery("select e from Employee e where e.salery=?1");
		q.setParameter(1,salery);
		List<Employee> employees=q.getResultList();
		if(employees.size()>0) {
			return employees;
		}
		return null;	
	}
	public List<Employee> findEmployeeByDesignation(String designation) {
		Query q=entityManager.createQuery("select e from Employee e where e.designation=?1");
		q.setParameter(1,designation);
		List<Employee> employees=q.getResultList();
		if(employees.size()>0) {
			return employees;
		}
		return null;	
	}
	public List<Employee> findEmployeeByName(String name) {
		Query q=entityManager.createQuery("select e from Employee e where e.name=?1");
		q.setParameter(1,name);
		List<Employee> employees=q.getResultList();
		if(employees.size()>0) {
			return employees;
		}
		return null;	
	}
	public List<Employee> findEmployeeBetweenSaleryrange(double startSalery,double endSalery) {
		Query q=entityManager.createQuery("select e from Employee e where e.salery>=?1 and e.salery<=?2");
		q.setParameter(1,startSalery);
		q.setParameter(2, endSalery);
		List<Employee> employees=q.getResultList();
		if(employees.size()>0) {
			return employees;
		}
		return null;	
	}
}
