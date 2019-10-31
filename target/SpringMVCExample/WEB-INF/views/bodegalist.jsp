<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Listar Usuarios</title>
        <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    </head>

    <body>
        <div class="generic-container">

            <%@include file="authheader.jsp" %>	
            <div class="panel panel-default">
                <!-- Default panel contents -->
            
                <div class="panel-heading"><span class="lead">Lista de Bodegas</span></div>
                <sec:authorize access="hasRole('ADMIN')">
                    <div class="well">
                        <a class="btn btn-success" href="<c:url value='/newbodega' />">Agregar Bodega</a>
                    </div>
                </sec:authorize>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Bodega Id:</th>
                            <th>Ubicación:</th>
                            <th>Descripción:</th>
                            <th>Fecha de Ingreso:</th>

                                <sec:authorize access="hasRole('ADMIN')">
                                <th width="100"></th>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ADMIN')">
                                <th width="100"></th>
                                </sec:authorize>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${bodegas}" var="bodega">
                            <tr>
                                <td>${bodega.id}</td>
                                <td>${bodega.bodegId}</td>
                                <td>${bodega.ubicacion}</td>
                                <td>${bodega.descripcion}</td>
                                <td>${bodega.fechaIngreso}</td>
                                
                              <sec:authorize access="hasRole('ADMIN')">
                                    <td><a href="<c:url value='/edit-bodega-${bodega.bodegId}' />" class="btn btn-warning custom-width">Editar</a></td>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ADMIN')">
                                    <td><a href="<c:url value='/delete-bodega-${bodega.bodegId}' />" class="btn btn-danger custom-width">Eliminar</a></td>
                                </sec:authorize>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
           
    </body>
</html>