<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.curso.model.Producto"%>
<%@ page import="com.curso.model.Pedido"%>
<%@page import="com.curso.service.PedidoService"%>
<%@page import="com.curso.service.ProductoService"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nuevo Pedido</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12 text-center">
				<form action="NuevoProductoServlet" method="post">
					<select class="form-select" name="pedidoNuevo" aria-label="Default select example">
						
							<%
					for (Producto p : ProductoService.getProductos()) {
					%>
						<option value="<%=p.getId()%>"><%=p.getNombre()%>-<%=p.getStock() %> Uds (disponibles)</option>
					<%
					}
					%>
					</select>
					
					
						<input type="number" class="form-control" name="unidades"
							placeholder="Cantidad">
				

					<button type="submit" class="btn btn-primary">Realizar
						pedido</button>


				</form>




			</div>
		</div>
	</div>
</body>
</html>