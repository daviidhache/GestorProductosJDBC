<%@page import="com.curso.service.ProductoService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.curso.model.Producto"%>
<%@page import="com.curso.enums.Cat"%>
<%
// Buscar el producto
Producto p = null;
try {
	//	Long id = Long.valueOf(request.getParameter("id"));
	Long id = (Long)request.getAttribute("idProducto");
	p = ProductoService.obtenerProductoById(id);

} catch (Exception e) {
	out.println("<p style='color: red;'>Error al obtener producto</p>");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<title>Modificar producto</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12 text-center">
				<h4>Modificar producto</h4>
				<form action="ModificarProductoServlet" method="post">

					<div class="form-group">

						<label for="nombre">Nombre</label> <input type="text" id="nombre"
							class="form-control" name="nombre"
							placeholder="Nombre del producto"
							value="<%=(p != null ? p.getNombre() : "")%>">


					</div>

					<!-- CATEGORIA ALIMENTO -->
					<div class="form-group">

						<label for="alimento">Alimento</label> <input type="radio"
							class="form-check-input" id="alimento" name="cat"
							value="Alimento"
							<%=(p != null && p.getCat().toString().equals("ALIMENTO") ? "checked='checked'" : "")%>>


					</div>
					<div class="form-group">

						<label for="hogar">Hogar</label> <input type="radio"
							class="form-check-input" id="hogar" value="Hogar" name="cat"
							<%=(p != null && p.getCat().toString().equals("HOGAR") ? "checked='checked'" : "")%>>
					</div>

					<div class="form-group">
						<label for="ocio">Ocio</label> <input type="radio"
							class="form-check-input" id="ocio" value="Ocio" name="cat"
							<%=(p != null && p.getCat().toString().equals("OCIO") ? "checked='checked'" : "")%>>
					</div>

					<div class="form-check">

						<input type="number" class="form-control" name="precio"
							placeholder="Introduce precio"
							value="<%=(p != null ? p.getPrecio() : "")%>">

					</div>

					<div class="form-check">

						<input type="number" class="form-control" name="stock"
							placeholder="Introduce stock"
							value="<%=(p != null ? p.getStock() : "")%>">

					</div>

					<button type="submit" name="valorId" value="<%=p.getId()%>"
						class="btn btn-success">Modificar</button>
					<a class="btn btn-info" href="index.jsp">Volver a gestión</a>

				</form>

			</div>
		</div>
	</div>
</body>
</html>