<%@page import="classes.User"%>
<%@page import="classes.Sendmail"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
	form{
	width: 80%;
	margin-left: 10%;
	}
</style>
<meta charset="ISO-8859-1">
<title>Bem vindo</title>
</head>
<body>
	<%
		String acao = request.getParameter("acao");
		
		if (acao != null){
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			if (Integer.valueOf(acao) == 1){
				String senha = new Sendmail().GenerateCode();
				User user = new User (nome, email, senha);
				user.salvar();
				session.setAttribute("verific", senha);
				session.setAttribute("nome", nome);
				session.setAttribute("email", email);
				
				Sendmail sendmail = new Sendmail (email, senha);
				sendmail.enviar();
				response.sendRedirect("Verifica.jsp");
			}
		}
	%>
	
	<form action="Cad.jsp" method="post" class="form-horizontal">
		<h3>Cadastro</h3>
		<div class="form-froup">
			<div><label for="nome">Nome</label>
			<input type="text" id="nome" name="nome" class="form-control">
			</div><div>
			<label for="email">Email</label>
			<input type="text" id="email" name="email" class="form-control">
			</div><br><div>
			<input type="hidden" name="acao" value="0">
			<button type="button" id="btnUser" class="btn btn-primary" onClick="acao.value='1' ; this.form.submit();">Cadastrar</button>
			</div>
		</div>
	</form>
	

</body>
</html>