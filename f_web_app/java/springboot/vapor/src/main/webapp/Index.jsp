<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<html>

<head>
</head>

<body>

      <c:forEach var = "i" items="${personL}">

          ${i.id}      <br>
          ${i.name}    <br>
          ${i.address} <br><br>

      </c:forEach>

<br><br><br>

      ${personL}

      <br>
      <br>

<table class="table table-hover">
  <thead class="thead-dark">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Address</th>
    </tr>
  </thead>

  <tbody>
     <c:forEach var = "i" items="${personL}">
       <tr>
          <td> ${i.id}      </td>
          <td> ${i.name}    </td>
          <td> ${i.address} </td>
       </tr>

     </c:forEach>

   </tbody>

</table>

<form:form method="POST"
          action="/jspViews/addPerson" modelAttribute="person">
             <table>

                <tr>
                    <td> <form:label path="id">   Id   </form:label> </td>
                    <td> <form:input path="id"/>                     </td>
                </tr>

                <tr>
                    <td> <form:label path="name"> Name </form:label> </td>
                    <td> <form:input path="name"/>                   </td>
                </tr>

                <tr>
                    <td> <form:label path="address"> Address </form:label> </td>
                    <td> <form:input path="address"/>                      </td>
                </tr>

                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>

        <br><br>
              Peace be upon you....<br>
              The Fruit is ${name}
              <% for(int i=0; i<5;i++){ %>
              <%=i+" hi" %> <br>
              <% } %>

            <br><br>
                <% int i=5; %>
                <% while(i<10){ %>
                <% if(i%3==0){
                     out.println(i+" is even");
                   }
                   else if(i%4==0){
                     out.println(i+" is odd");
                   }
                   else {
                     out.println("hooooo");
                   }
                %> <br>
                <% i++; %>
                <% } %>

</body>
</html>