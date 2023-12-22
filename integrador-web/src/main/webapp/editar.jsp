<%@page import="com.codoacodo.model.Orador"%>
<html>
    <head>
            <%@include file="head.jsp" %>	
    </head>

    <body>
        <main>
            <!-- ACA VA EL NAVBAR  -->
            <%@include file="navbar.jsp" %>
            <div class="container">
                <section>
                    <% 
                        Orador orador = (Orador)request.getAttribute("producto");
                    %>
                    <h1>Editar orador ID=<%=orador.getIdOrador()%></h1>
                    <!--  JSP -->
                    <form method="POST"
                            action="<%=request.getContextPath()%>/api/EditarController">
                            <div class="mb-3">
                              <label for="exampleFormControlInput1" class="form-label">Nombre</label>
                              <input name="nombre"
                                    value="<%=orador.getNombre()%>"  
                                    type="text" class="form-control" id="exampleFormControlInput1" placeholder="Nombre" maxlength="50">
                            </div>
                            <div class="mb-3">
                              <label for="exampleFormControlInputl" class="form-label">Apellido</label>
                              <input name="apellido"
                                    value="<%=orador.getApellido()%>" 
                                    type="text" class="form-control" id="exampleFormControlInput1">
                            </div>
                            <div class="my-3 mx-3">
                                <label for="mail" class="form-label">Mail</label>
                                <input value="<%=orador.getMail()%>" name="mail" type="text" class="form-control" id="MailTextarea" placeholder="Mail" maxlength="50">
                            </div>
                            <div class="mb-3">
                              <label for="exampleFormControlInputl" class="form-label">Tema</label>
                              <input name="tema"
                                    readonly="readonly"
                                    value="<%=orador.getTema()%>" 
                                    type="text" class="form-control" id="exampleFormControlInput1" maxlength="50">
                            </div>
                            <button class="btn btn-primary">
                                Grabar
                            </button>
                    </form>
                </section>
            </div>
        </main>
    </body>	
</html>