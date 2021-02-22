package com.ideas2it.employee.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ideas2it.employee.model.Employee;
import com.ideas2it.util.common.Constants;
import com.ideas2it.util.exception.EmployeeManagementSystemException;
import com.ideas2it.util.logger.EmployeeManagementSystemLogger;

/**
 * @description: class that implements EmployeeDAO for CRUD operations
 * @author     : Sivabhagya Jawahar
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {	
	
	@Autowired
    private SessionFactory sessionFactory;
		
	/**
	 * @description: Method to add employee details
	 * @author     : Sivabhagya Jawahar
	 * @param      : employee Employee
	 * @return     : employee 
	 */
	public void addEmployee(Employee employee) throws EmployeeManagementSystemException{
		sessionFactory.getCurrentSession().saveOrUpdate(employee);
	}
	
	/**
	 * @description: Method to update employee details
	 * @author     : Sivabhagya Jawahar
	 * @param      : employee Employee
	 * @return     : true/false boolean
	 * @throws EmployeeManagementSystemException 
	 */
	public boolean updateEmployee(Employee employee) throws EmployeeManagementSystemException{
		sessionFactory.getCurrentSession().update(employee);
        return true;
	}
	
	/**
	 * @description: Method to delete employee details
	 * @author     : Sivabhagya Jawahar
	 * @param      : empId String
	 * @return     : true/false boolean
	 * @throws EmployeeManagementSystemException 
	 */
	public boolean deleteEmployee(String empId) throws EmployeeManagementSystemException {
		Session session = this.sessionFactory.getCurrentSession();
		Query<?> query = session.createQuery("update Employee set isDeleted = 1 where empId = :empId");  
		query.setParameter("empId", Integer.parseInt(empId));
		int status = query.executeUpdate();  
		if (status == 1) {
			return true;	
		}
		return false;
	}
	
	/**
	 * @description: Method to restore employee details
	 * @author     : Sivabhagya Jawahar
	 * @param      : empId String
	 * @return     : true/false boolean
	 * @throws EmployeeManagementSystemException 
	 */
	public boolean restoreEmployee(String empId) throws EmployeeManagementSystemException {
		Session session = this.sessionFactory.getCurrentSession();
		Query<?> query = session.createQuery("update Employee set isDeleted = 0 where empId = :empId");  
		query.setParameter("empId", Integer.parseInt(empId));
		int status = query.executeUpdate();  
		if (status == 1) {
			return true;	
		}
		return false;
	}
	
	/**
	 * @description: Method to get employee details by id
	 * @author     : Sivabhagya Jawahar
	 * @param      : empId String
	 * @return     : employee Employee
	 * @throws EmployeeManagementSystemException 
	 */
	public Employee getEmployeeById(String empId) throws EmployeeManagementSystemException {
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Employee> employeeList = session.createQuery("From Employee where isDeleted=0 and empId = :empId").setParameter("empId", Integer.parseInt(empId)).getResultList();
		return employeeList.get(0);
	}
	
	/**
	 * @description: Method to get employee details by regNo
	 * @author     : Sivabhagya Jawahar
	 * @param      : regNo String
	 * @param      : status String
	 * @return     : List<Employee> employeeList
	 * @throws EmployeeManagementSystemException 
	 */
	public List<Employee> getEmployeeByRegNo(String regNo, String status) throws EmployeeManagementSystemException{
		Session session = this.sessionFactory.getCurrentSession();
		String queryString = "From Employee";
		if (status != null && status.equals("inactive")) {
			queryString += " WHERE isDeleted = 1";
		} else {
			queryString += " WHERE isDeleted = 0";
		}
		queryString += " and regNo = :regNo";
		@SuppressWarnings("unchecked")
		List<Employee> employeeList = session.createQuery(queryString).setParameter("regNo", regNo).getResultList();
		return employeeList;
	}

	/**
	 * @description: Method to get employee details by status
	 * @author     : Sivabhagya Jawahar
	 * @param      : status String
	 * @return     : List<Employee> employeeList
	 * @throws EmployeeManagementSystemException 
	 */
	public List<Employee> getEmployeeByStatus(String status) throws EmployeeManagementSystemException{
		Session session = this.sessionFactory.getCurrentSession();
		String queryString = "From Employee";
		if (status != null && status.equals("inactive")) {
			queryString += " WHERE isDeleted = 1";
		} else {
			queryString += " WHERE isDeleted = 0";
		}
		@SuppressWarnings("unchecked")
		List<Employee> employeeList = session.createQuery(queryString).getResultList();
		return employeeList;
	}
}
