<%@page import="com.curso.service.PedidoService"%>
<%@page import="com.curso.enums.Cat"%>
<%@page import="com.curso.service.ProductoService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.curso.model.Producto"%>
<%@ page import="com.curso.model.Pedido"%>

<!DOCTYPE html>
<!-- PÁGINA INDEX- GESTIÓN DE CRUD SOBRE PRODUCTO -->
<html>
<head>
<meta charset="UTF-8">
<title>Gestión de almacén</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-4">
				<h4>Dar de alta nuevo producto</h4>
				<form action="index.jsp" method="post">

					<div class="form-group">

						<label for="nombre">Nombre</label> <input type="text" id="nombre"
							class="form-control" name="nombre"
							placeholder="Nombre del producto">


					</div>

					<!-- CATEGORIA ALIMENTO -->
					<div class="form-group">

						<label for="alimento">Alimento</label> <input type="radio"
							class="form-check-input" id="alimento" name="cat"
							value="Alimento" checked="checked">


					</div>

					<div class="form-group">

						<label for="hogar">Hogar</label> <input type="radio"
							class="form-check-input" id="hogar" value="Hogar" name="cat">


					</div>

					<div class="form-group">

						<label for="ocio">Ocio</label> <input type="radio"
							class="form-check-input" id="ocio" value="Ocio" name="cat">


					</div>


					<div class="form-check">

						<input type="number" class="form-control" name="precio"
							placeholder="Introduce precio">

					</div>

					<div class="form-check">

						<input type="number" class="form-control" name="stock"
							placeholder="Introduce stock">

					</div>

					<button type="submit" class="btn btn-primary">Agregar</button>

				</form>

				<!-- VALIDAMOS LOS DATOS EN EL MISMO INDEX ANTES DE REDIRIGIR A SERVLET -->
				<%
				if ("POST".equalsIgnoreCase(request.getMethod())) {
					// Hemos enviado la solicitud post del formulario y podemos tratar los datos
					// Recogemos parámetros
					String nombre = request.getParameter("nombre");
					String cat = request.getParameter("cat");
					String precio = request.getParameter("precio");
					String stock = request.getParameter("stock");
					if ((nombre != null && !nombre.isBlank()) && (cat != null && !cat.isBlank())
					&& (precio != null && !precio.isBlank()) && (stock != null && !stock.isBlank())) {
						Double precioP = Double.valueOf(precio);
						int stockP = Integer.valueOf(stock);
						if (precioP <= 0 || stockP <= 0) {
					out.println("<div class='text-danger'>*No puede hacer cantidades negativas</div>");

						} else {
					// Procedemos a crear producto
					Cat cate = Cat.valueOf(cat.toUpperCase());
					Producto p = new Producto(nombre, cate, precioP, stockP);
					// Añadir a la lista
					ProductoService.getProductos().add(p);

						}

					} else {
						out.println("<div class='text-danger'>*No dejes campos vacios</div>");
					}
				}
				%>
			</div>
			<div class="col-8">
				<h3>Gestor de productos</h3>
				<table class="table table-striped">
					<tr>
						<th>Id</th>
						<th>Nombre</th>
						<th>Categoría</th>
						<th>Precio</th>
						<th>Stock</th>
						<th>Acciones</th>
						<th></th>
					</tr>
					<%
					for (Producto p : ProductoService.getProductos()) {
					%>
					<tr>
						<td><%=p.getId()%></td>
						<td><%=p.getNombre()%></td>
						<td><%=p.getCat().toString()%></td>
						<td><%=p.getPrecio()%></td>
						<td><%=p.getStock()%></td>
						<td><a class="btn btn-info"
							href="FrontController?opcion=modificar&id=<%=p.getId()%>">Modificar</a></td>
						<td><a class="btn btn-danger"
							href="FrontController?opcion=eliminar&id=<%=p.getId()%>">Eliminar</a></td>
					</tr>
					<%
					}
					%>
				</table>
			</div>
			<div class="col-12">
				<br>
				<!-- SECCIÓN DE PEDIDOS -->
				<h2>Pedidos</h2>
				<a class="btn btn-warning" href="FrontController?opcion=nuevopedido">Nuevo
					pedido</a>
				<table class="table table-bordered">
					<thead>
						<tr>
							<td>Ref</td>
							<td>Producto</td>
							<td>Unidades</td>
							<td>Fecha y hora</td>
						</tr>
					</thead>
					<%
					for (Pedido pe : PedidoService.getListPedidos()) {
					%>
					<tr>
						<td><%=pe.getRef()%></td>
						<td><%=pe.getProducto().getNombre()%></td>
						<td><%=pe.getUnidades()%></td>
						<td><%=pe.getFechayHora().toString()%></td>
						
					</tr>
					<%
					}
					%>
				</table>
			</div>
		</div>
	</div>
</body>
</html>