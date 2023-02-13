package com.studentPassingRate.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.studentPassingRate.login.dao.LoginDao;
import com.studentPasssingRate.user.model.Student;
import com.studentPasssingRate.user.model.UserModel;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 121313121L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginDao loginDao = new LoginDao();
		String username = request.getParameter("userId");
		String password = request.getParameter("password");
		
		UserModel user = new UserModel();
		user.setUserId(username);
		user.setPassword(password);
		
		if(loginDao.validate(user)) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getUserId());
			
			Student s1 = new Student("Student1","S1","Dep 1",35);
			Student s2 = new Student("Student2","S2","Dep 1",70);
			Student s3 = new Student("Student3","S3","Dep 1",60);
			Student s4 = new Student("Student4","S4","Dep 1",90);
			Student s5 = new Student("Student5","S5","Dep 2",30);
			Student s6 = new Student("Student6","S6","Dep 3",32);
			Student s7 = new Student("Student7","S7","Dep 3",70);
			Student s8 = new Student("Student8","S8","Dep 3",20);
			
			List<Student> studentList = new ArrayList<>();
			studentList.add(s1);
			studentList.add(s2);
			studentList.add(s3);
			studentList.add(s4);
			studentList.add(s5);
			studentList.add(s6);
			studentList.add(s7);
			studentList.add(s8);
			
			studentList = calculatePassingRate(studentList);
			
			request.setAttribute("studentList", studentList);
			
			request.getRequestDispatcher("mainpage.jsp").forward(request, response);
		}else {
			PrintWriter out = response.getWriter(); 
			String errorMessage = "Password incorrect or this user does not exist!";
			out.println("<script type='text/javascript'>");
			out.println("alert(" + "'" + errorMessage + "'" + ");");
			out.println("location='login.jsp';");
			out.println("</script></head><body></body></html>");
			
		}
	}
	
	private List<Student> calculatePassingRate(List<Student> stdList) {
		Map<String,List<Student>> studentByDepartment = new HashMap<>();
		studentByDepartment = stdList.stream().collect(Collectors.groupingBy(Student::getDepartment));
		
		for (Map.Entry<String,List<Student>> entry : studentByDepartment.entrySet())
		{
			int noOfPassStudent = 0;
            List<Student> students = entry.getValue();
            for (Student student : students) {
            	if(student.getMarks()>=40) {
            		noOfPassStudent++;
            	}
            }
   
            double passingRate = ((double)noOfPassStudent/(double)students.size())*100;
    
            for (Student student : students) {
            	student.setPassingRate(passingRate);
            }
		}
        
        List<Map.Entry<String, List<Student>>> entries = new ArrayList<>(studentByDepartment.entrySet());

        entries.sort((e1, e2) -> {
            String department1 = e1.getValue().get(0).getDepartment();
            String department2 = e2.getValue().get(0).getDepartment();
            return department1.compareTo(department2);
        });

        Map<String, List<Student>> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, List<Student>> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        
        List<Student> studentList = new ArrayList<>();
        for (Map.Entry<String, List<Student>> entry : sortedMap.entrySet()) {
            List<Student> students = entry.getValue();
            for (Student student : students) {
                studentList.add(student);
            }
        }
        
		return studentList;
	}

}
