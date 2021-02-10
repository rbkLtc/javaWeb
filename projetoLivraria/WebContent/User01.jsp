<%@page import="classes.User"%>
<%@page import="classes.Livro"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="db.DBQuery"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Bem vindo</title>
</head>
<body>
	<%
		String nivel = (String) session.getAttribute("niv");
		if (nivel != null){
			String email = (String) session.getAttribute("logine");
			String senha = (String) session.getAttribute("logins");
			String filtrocol = request.getParameter("filtrocol");
			String chave = request.getParameter("chave");
			String acao = request.getParameter("acao");		
			User user = new User(email, senha);
			if (user.checkLogin()){
				if (acao == null){
					response.getWriter().write("Livros:");
					String saida = new Livro().lerTodos();
					response.getWriter().write(saida);
				}else
				if (acao != null){
					if (Integer.valueOf(acao) == 1){
						ResultSet rs = new Livro().lerPor(filtrocol, chave);
					
						String exibe = "";
						exibe+="<table border =1>";
						exibe+="<td>" + ("Titulo") + "</td>";
						exibe+="<td>" + ("Autor") + "</td>";
						exibe+="<td>" + ("Ano") + "</td>";
						exibe+="<td>" + ("Genero") + "</td>";
						while( rs.next()){
							exibe += "<tr>"; 
							exibe += "<td>" + rs.getString("titulo") + "</td>";
							exibe += "<td>" + rs.getString("autor") + "</td>";
							exibe += "<td>" +rs.getString("ano") + "</td>";
							exibe += "<td>" + rs.getString("categ") + "</td>";
							exibe += "</tr><br>";
						}
						exibe += "</table>";
						response.getWriter().write(exibe);
					}
				}
			}else if (!(user.checkLogin())){
				response.sendRedirect("Error.html");
			}
		}else
			response.sendRedirect("Error.html");
	%>
</body>
<form action="User01.jsp" method="post" class="form-horizontal">
		<h1>Bem Vindo</h1>
		<div class="form-froup">
		<div><label for="filtrocol">Filtro</label>
			<select name="filtrocol">
 			 <option value="autor">Autor</option>
 			 <option value="ano">Ano</option>
 			 <option value="categ">Genero</option>
			</select></div>
		<div><label for="chave">Palavra chave</label>
		<input type="text" id="chave" name="chave" class="form-control">
		</div><div>
		<input type="hidden" name="acao" value="0">
		</div><br>
		<button type="button" id="btnUser" class="btn btn-primary" onClick="acao.value='1' ; this.form.submit();">Buscar</button><br>
		</div>
	</form>
</html>