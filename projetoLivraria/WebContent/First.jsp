<%@page import="classes.User"%>
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
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");	
		String acao = request.getParameter("acao");
		if (acao != null){
			if (Integer.valueOf(acao) == 1){
				User user = new User(email, senha);
				if (user.checkLogin()){
					session.setAttribute("niv", user.getNivel());
					session.setAttribute("logine", email);
					session.setAttribute("logins", senha);
					if(user.getNivel().equals("1")){
						response.sendRedirect("User01.jsp");	
					}else if(user.getNivel().equals("2")){
						response.sendRedirect("User02.jsp");	
					}else if(user.getNivel().equals("3")){
						response.sendRedirect("User03.jsp");	
					}
				}else if (user.checkLogin() == false){
					response.getWriter().write("Email ou senha incorretos, por favor tente novamente");
				}
			}else if(Integer.valueOf(acao) == 2){
				response.sendRedirect("Cad.jsp");
			}
		}
	%>
	
	<form action="First.jsp" method="post" class="form-horizontal">
		<h1>Bem Vindo</h1>
		<div class="form-froup">
		<div><label for="email">Email</label>
			<input type="text" id="email" name="email" class="form-control">
		</div><div>
			<label for="senha">Senha</label>
			<input type="password" id="senha" name="senha" class="form-control">
		</div><div>
		<input type="hidden" name="acao" value="0">
		</div><br>
		<button type="button" id="btnUser" class="btn btn-primary" onClick="acao.value='1' ; this.form.submit();">Login</button><br>
		
		<label for="cadastro">Caso nao tenha cadastro: </label><br>
		<button type="button" id="btnUser" class="btn btn-primary" onClick="acao.value='2' ; this.form.submit();">Cadastrar</button>
		</div>
	</form>
</body>
</html>