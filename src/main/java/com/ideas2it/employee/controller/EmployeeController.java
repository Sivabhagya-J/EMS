package com.ideas2it.employee.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.project.model.Project;
import com.ideas2it.util.common.Constants;
import com.ideas2it.util.exception.EmployeeManagementSystemException;
import com.ideas2it.util.logger.EmployeeManagementSystemLogger;

/**
 * @description: Provide the class necessary to create an controller class to communicate with user and the service 
 * @author     : Sivabhagya Jawahar
 */
@Controller
public class EmployeeController{
    
    @Autowired
	EmployeeService employeeService;

    /**
     * @description: This method will get the request from the user to perform delete operation
     * @param request delete
     * @return forward
     * @throws EmployeeManagementSystemException 
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    private ModelAndView deleteAction(HttpServletRequest request) throws EmployeeManagementSystemException {
    	String empId = request.getParameter("empId");
    	employeeService.deleteEmployee(empId);
    	ModelAndView model = new ModelAndView("listemployee");
    	model.addObject("employeeList", employeeService.getEmployeeByStatus("active"));
    	model.addObject("status", "active");
    	return model;
    }
    
    /**
     * @description: This method will get the request from the user to perform restore operation
     * @param request restore
     * @return forward
     * @throws EmployeeManagementSystemException 
     */
    @RequestMapping(value = "/restore", method = RequestMethod.GET)
    private ModelAndView restoreAction(HttpServletRequest request) throws EmployeeManagementSystemException {
    	String empId = request.getParameter("empId");
    	employeeService.restoreEmployee(empId);
    	ModelAndView model = new ModelAndView("listemployee");
    	model.addObject("employeeList", employeeService.getEmployeeByStatus("inactive"));
    	model.addObject("status", "inactive");
    	return model;
    }
    
    /**
     * @description: This method will get the request from the user to perform edit operation
     * @param request edit
     * @return forward
     * @throws EmployeeManagementSystemException 
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    private ModelAndView editAction(HttpServletRequest request) throws EmployeeManagementSystemException {
        String empId = request.getParameter("empId");
        Employee employee = employeeService.getEmployeeById(empId);
        ModelAndView model = new ModelAndView("employee-form");
        model.addObject("employee", employee);
        model.addObject("status", "active");
    	return model;
    }
    
    /**
     * @description: This method will get the request from the user to perform list operation
     * @param request list
     * @return forward
     * @throws EmployeeManagementSystemException 
     */
    @RequestMapping(value = {"/","listemployee"})
    private ModelAndView listAction(ModelAndView model, HttpServletRequest request) throws EmployeeManagementSystemException {
    	model.setViewName("listemployee");
        String status = request.getParameter("status");
        String regNo = request.getParameter("regNo");
        if (regNo != null && !regNo.isEmpty()) {
        	model.addObject("employeeList", employeeService.getEmployeeByRegNo(regNo, status)); 
        	model.addObject("searchValue", regNo);
        } else {
        	model.addObject("employeeList", employeeService.getEmployeeByStatus(status)); 
        }
        model.addObject("status", status);
    	return model;
    }
    
	/**
	 * @description: This method will get the request from the user to perform create operation
	 * @param request create
	 * @return forward
	 * @throws EmployeeManagementSystemException 
	 */
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    private ModelAndView createAction(ModelAndView model) throws EmployeeManagementSystemException {
    	Employee employee = new Employee();
    	model.addObject("employee", employee);
    	return model;
    }
    
    /**
     * @description: This method will get the request from the user to access the basic operations
     * @param      : request Request will be a url which holds the value with the parameter
     * @param      : response Response will be send back as a result
     * @throws EmployeeManagementSystemException 
     */
    @RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
    public ModelAndView saveEmployee(@ModelAttribute Employee employee) throws ServletException, IOException, EmployeeManagementSystemException {
		if (employee.getEmpId() == 0) {
			employeeService.addEmployee(employee);
		}else {
			employeeService.updateEmployee(employee);
		}
		 return new ModelAndView("redirect:/listemployee");
    }
    
    /**
     * @description: method to get selected projects for the employee
     * @param projectArray
     * @return projectList
     * @throws EmployeeManagementSystemException 
     */
    @SuppressWarnings("unused")
	private List<Project> getSelectedProject(String[] projectArray) throws EmployeeManagementSystemException{
    	List<Project> projectList = new ArrayList<Project>();
		if (projectArray != null) {
			for (int index = 0; index < projectArray.length; index++) {
				Project project = getProjectById(projectArray[index]);
				if (project != null) {
					projectList.add(project);
				}
		    }
		}
		return projectList;
    }
    
    /**
     * @description: Method to invoke the employee service for get employee by RegNo 
     * @param regNo
     * @param status
     * @return employee
     * @throws EmployeeManagementSystemException 
     */
    public List<Employee> getEmployeeByRegNo(String regNo, String status) throws EmployeeManagementSystemException{
    	return employeeService.getEmployeeByRegNo(regNo, status);
    }
    
    /**
     * @description: Method to invoke the employee service for get project by id 
     * @param id
     * @return Project
     * @throws EmployeeManagementSystemException 
     */
    public Project getProjectById(String id) throws EmployeeManagementSystemException{
    	return employeeService.getProjectById(id);
    }
    
    /**
 	 * @description: Method to invoke the employee service to get address object
     * @author     : Sivabhagya Jawahar
     * @param      : doorNo String
     * @param      : streetName String
     * @param      : city String
     * @param      : state String
     * @param      : pincode int
     * @return     : Address
     */
    public Address getAddressObject(String addressId, String doorNo, String streetName, String city, String state, int pincode) {
        return employeeService.getAddressObject(addressId, doorNo, streetName, city, state, pincode);
	}

    /**
 	 * @description: Method to invoke the employee service for delete the record
     * @author     : Sivabhagya Jawahar
     * @param      : empId String
     * @return     : true/false boolean
     * @throws EmployeeManagementSystemException 
     */
	public boolean deleteEmployee(String empId) throws EmployeeManagementSystemException{
		return employeeService.deleteEmployee(empId);
    }
	
	/**
 	 * @description: Method to invoke the employee service for restore the record
     * @author     : Sivabhagya Jawahar
     * @param      : empId String
     * @return     : true/false boolean
	 * @throws EmployeeManagementSystemException 
     */
	public boolean restoreEmployee(String empId) throws EmployeeManagementSystemException{
		return employeeService.restoreEmployee(empId);
    }
	
	/**
 	 * @description: Method to invoke the employee service for view the employee record by id
     * @author     : Sivabhagya Jawahar
     * @param      : regNo String
     * @return     : employee Employee
	 * @throws EmployeeManagementSystemException 
     */
	public Employee getEmployeeById(String empId) throws EmployeeManagementSystemException{
        return employeeService.getEmployeeById(empId);
    }
	
	/**
     * @description: Method to get all active project
     * @author     : Sivabhagya Jawahar
     * @return     : projectList List<Project>
	 * @throws EmployeeManagementSystemException 
     */ 
    public List<Project> getAllActiveProject() throws EmployeeManagementSystemException{
    	return employeeService.getAllActiveProject();
    }
    /**
    
     * @description: Method to get employee by status
     * @author     : Sivabhagya Jawahar
     * @return     : List<Employee> Employeelist
     * @throws EmployeeManagementSystemException 
     */ 
    public List<Employee> getEmployeeByStatus(String status) throws EmployeeManagementSystemException{
    	return employeeService.getEmployeeByStatus(status);
    }
    
}