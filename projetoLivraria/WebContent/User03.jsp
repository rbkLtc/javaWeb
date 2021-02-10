<%@page import="classes.User"%>
<%@page import="classes.Livro"%>

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
	if(nivel == null){
		response.sendRedirect("Error.html");
	}else if (nivel.equals("3")){	
		String email   = (String) session.getAttribute("logine");
		String senha   = (String) session.getAttribute("logins");
		String acao    = request.getParameter("acao");
		String id_user = request.getParameter("id_user");
		String novon   = request.getParameter("novon");
	
		User user = new User(email, senha);	
		User use = new User(id_user);
		
		if (user.checkLogin()){
			String saida = new User().lerTodos();
			response.getWriter().write(saida);
			if (acao != null){
				if (Integer.valueOf(acao) == 1){
					use.deleta(); response.sendRedirect("User03.jsp");
				}else if(Integer.valueOf(acao) == 3){
					use.nivel(novon); response.sendRedirect("User03.jsp");					
				}
			}else if (acao == null){
				response.getWriter().write("");
			}
		}else if (nivel.equals("1") || nivel.equals("2")){
			response.sendRedirect("Error.html");
		}
	}

%>
	
	<form action="User03.jsp" method="post" class="form-horizontal">
		<h3>Gerenciar usuarios</h3>
		<div class="form-froup">
			<div><label for="id_user">Id usuario</label>
			<br><input type="text" id="id_user" name="id_user" class="form-control">
			</div><br><input type="hidden" name="acao" value="0">
			<div><a>Excluir o perfil</a>
			<button type="button" id="btnUser" class="btn btn-primary" onClick="acao.value='1' ; this.form.submit();">Excluir</button>
			</div><br>
			<div><label for="novon">Niveis</label>
			<select name="novon">
 			 <option value="1">Somente leitura</option>
 			 <option value="2">Gerenciamento de dados</option>
 			 <option value="3">Gerenciamento de users</option>
			</select>
			<br><p>Alterar o nivel de acesso
			<button type="button" id="btnUser" class="btn btn-primary" onClick="acao.value='3' ; this.form.submit();">Alterar</button>
			</p></div><br>
			<a>Ir para gerenciamento de livros</a>
			<a href = "User02.jsp">Livros</a>
		</div>
	</form>
</body>
</html>