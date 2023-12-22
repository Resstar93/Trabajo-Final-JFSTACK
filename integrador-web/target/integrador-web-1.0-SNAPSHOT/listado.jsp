<%@page import="java.util.List"%>
<%@page import="com.codoacodo.model.Orador"%>
<!DOCTYPE html>
<html lang="es-ar" data-bs-theme="light">
    <head>
        <%@include file="head.jsp" %>
        <title>
          Listado
        </title>	
    </head>
	
    <body>
        <!-- ACA VA EL NAVBAR  -->
        <%@include file="navbar.jsp" %>
        <main>
            <div class="container">
                
                <section class="bg-secondary-subtle border rounded-5 m-4 px-2 pt-2 pb-3 col-12 mx-auto text-center">
      <h2 class="my-4">Listado</h2>
      <table class="table table-sm table-striped table-hover border border-success">
                      <thead>
                        <tr>
                          <th scope="col">ID</th>
                          <th scope="col">NOMBRE</th>
                          <th scope="col">APELLIDO</th>
                          <th scope="col">MAIL</th>
                          <th scope="col">TEMA</th>
                          <th scope="col">ACCIONES</th>
                        </tr>
                      </thead>
          <% 
                //codigo java
                //obtener el listado desde el request
                //se guardo bajo el nombre de "listado"
            List<Orador> listado = (List<Orador>)request.getAttribute("listado");
          %>
        <tbody>
            <%
                 for( Orador  unOrador : listado) {
            %>

             <tr>
                <th scope="row"> <%=unOrador.getIdOrador()%> </th>
                <td><%=unOrador.getNombre() %></td>
                <td><%=unOrador.getApellido() %></td>
                <td><%=unOrador.getMail() %></td>
                <td><%=unOrador.getTema() %></td>
                <td>
                  <a class="btn btn-danger" href="<%=request.getContextPath()%>/api/EliminarController?id=<%=unOrador.getIdOrador()%>" role="button">Eliminar</a> | 
                  <a class="btn btn-secondary" href="<%=request.getContextPath()%>/api/EditarController?id=<%=unOrador.getIdOrador()%>" role="button">Editar</a>
                </td>
              </tr>
             <%
                  }
             %>

        </tbody>
      </table>
    </section>

                      
            </div>
        </main>
    </body>	
</html>