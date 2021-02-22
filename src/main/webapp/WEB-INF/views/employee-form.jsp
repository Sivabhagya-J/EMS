<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add new employee</title>
        <style>
		.text-field {
			margin:15px;
			height:30px;
		}
        </style>
    </head>
    <body>
    	<div align="center">
        	<h1>New/Edit Employee</h1>
        		<form:form action="saveEmployee" method="post" modelAttribute="employee">
			        <table>
			            <form:hidden path="empId"/>
			            <tr>
			                <td>Name:</td>
			                <td><form:input path="name" /></td>
			            </tr>
			            <tr>
			                <td>Register No:</td>
			                <td><form:input path="regNo" /></td>
			            </tr>
			            <tr>
			                <td>Date of join:</td>
			                <td><form:input path="dateofJoin" /></td>
			            </tr>
			            <tr>
			                <td>Role:</td>
			                <td><form:input path="role" /></td>
			            </tr>
			            <tr>
			                <td>Experience:</td>
			                <td><form:input path="experience" /></td>
			            </tr>
			            <tr>
			                <td>Phone:</td>
			                <td><form:input path="phone" /></td>
			            </tr>
			            <tr>
			                <td>Salary:</td>
			                <td><form:input path="salary" /></td>
			            </tr>
			            <tr>
			            	<form:hidden path="employee.addresslist[0].addressId"/>
			            </tr>
			            <tr>
			                <td>Primary Address:</td>
			            </tr>
			            <tr>
			                <td>Door No:</td>
			                <td><form:input path="employee.addresslist[0].doorNo" /></td>
			            </tr>
			            <tr>
			                <td>Street Name:</td>
			                <td><form:input path="employee.addresslist[0].streetName" /></td>
			            </tr>
			            <tr>
			                <td>City:</td>
			                <td><form:input path="employee.addresslist[0].city" /></td>
			            </tr>
			            <tr>
			                <td>State:</td>
			                <td><form:input path="employee.addresslist[0].state" /></td>
			            </tr>
			            <tr>
			                <td>Pincode:</td>
			                <td><form:input path="employee.addresslist[0].pincode" /></td>
			            </tr>
			            <tr>
			                <td colspan="2" align="center"><input type="submit" value="Save"></td>
			            </tr>
			            <tr>
			            	<form:hidden path="employee.addresslist[1].addressId"/>
			            </tr>
			            <tr>
			                <td>Secondary Address:</td>
			            </tr>
			            <tr>
			                <td>Door No:</td>
			                <td><form:input path="employee.addresslist[1].doorNo" /></td>
			            </tr>
			            <tr>
			                <td>Street Name:</td>
			                <td><form:input path="employee.addresslist[1].streetName" /></td>
			            </tr>
			            <tr>
			                <td>City:</td>
			                <td><form:input path="employee.addresslist[1].city" /></td>
			            </tr>
			            <tr>
			                <td>State:</td>
			                <td><form:input path="employee.addresslist[1].state" /></td>
			            </tr>
			            <tr>
			                <td>Pincode:</td>
			                <td><form:input path="employee.addresslist[1].pincode" /></td>
			            </tr>
			            <tr>
			                <td colspan="2" align="center"><input type="submit" value="Save"></td>
			                <td><a href="listEmployee">Cancel</a></td>
			            </tr>
			        </table>
        		</form:form>
   		</div>
    </body>
</html>

