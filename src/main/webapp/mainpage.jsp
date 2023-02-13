<%@page import="com.studentPasssingRate.user.model.Student"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="/js/my.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		if(session.getAttribute("userId")==null){
			response.sendRedirect("login.jsp");
		}
	%>
	<div>
		<h2>Welcome ${userId}</h2>
		<form method="get">
		<table id="table" border ="1" width="500" align="center">
         <tr>
          <th><b>Department</b></th>
          <th><b>Student Id</b></th>
          <th><b>Marks</b></th>
          <th><b>Pass %</b></th>
         </tr>
        <%List<Student> std = (ArrayList<Student>)request.getAttribute("studentList");
        if(std != null){
        	for(Student s:std){%>
            <tr>
                <td><%=s.getDepartment()%></td>
                <td><a href="#" class="student-id" data-name="<%= s.getName() %>"><%= s.getStudentId() %></a></td>
                <td><%=s.getMarks()%></td>
                <td><%
    
                if (s.getPassingRate() % 1 > 0) {
            %>
                <%= String.format("%.2f", s.getPassingRate()) %>
            <%
                } else {
            %>
                <%= String.format("%.0f", s.getPassingRate()) %>
            <%
                }
            %></td>
            </tr>
            <%}}%>
        </table> 
		</form>
	</div>
	
	<script type="text/javascript">
	$(document).ready(function() {
		  $('.student-id').click(function() {
		    alert($(this).data('name'));
		  });
		});
	
    $(document).ready(function () {	
        $('#table').each(function () {
        	var First_Column_number_to_Merge = 4;
            var Previous_TD = null;
            var i = 1;
            $("tbody",this).find('tr').each(function () {
                var Current_td = $(this).find('td:nth-child(' + First_Column_number_to_Merge + ')');
                if (Previous_TD == null) {
                    Previous_TD = Current_td;
                    i = 1;
                } 
                else if (Current_td.text() == Previous_TD.text()) {
                    Current_td.remove();
                    Previous_TD.attr('rowspan', i + 1);
                    i = i + 1;
                } 
                else {
                    Previous_TD = Current_td;
                    i = 1;
                }
            });
        });		
        
        
        $('#table').each(function () {
            var Second_Column_number_to_Merge = 1;
            var Previous_TD = null;
            var i = 1;
            $("tbody",this).find('tr').each(function () {
                var Current_td = $(this).find('td:nth-child(' + Second_Column_number_to_Merge + ')');                
                if (Previous_TD == null) {
                    Previous_TD = Current_td;
                    i = 1;
                } 
                else if (Current_td.text() == Previous_TD.text()) {
                    Current_td.remove();
                    Previous_TD.attr('rowspan', i + 1);
                    i = i + 1;
                } 
                else {
                    Previous_TD = Current_td;
                    i = 1;
                }
            });
        });
    });
</script>
</body>
</html>