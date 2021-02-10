<%@page import="classes.User"%>
<%@page import="classes.Sendmail"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		
		String acao = request.getParameter("acao");
		String senha   = request.getParameter("senha");
		String codigo  = request.getParameter("codigo");
		if (acao != null){	
			String verific = (String) session.getAttribute("verific");
			if (verific.equals(codigo)){
				String nome    = (String) session.getAttribute("nome");
				String email   = (String) session.getAttribute("email");
				User user = new User(nome, email, senha);
				user.verificaEmail();
				session.setAttribute("logine", email);
				session.setAttribute("logins", senha);
				session.setAttribute("niv", "1");
				response.sendRedirect("User01.jsp");
			}else if (!(verific.equals(codigo))){
				response.getWriter().write("Codigo incorreto, por favor tente novamente!");
			}
		}
		
	%>
	
	<form action="Verifica.jsp" method="post" class="form-horizontal">
		<h3>Bem vindo</h3>
		<div class="form-froup">	
			<div><label for="codigo">Codigo de verificacao</label>
			<input type="text" id="codigo" name="codigo" class="form-control">
			</div><div>
			<label for="senha">Nova senha</label>
			<input type="password" id="senha" name="senha" class="form-control">
			<input type="hidden" name="acao" value="0">
			</div><br>
			<button type="button" id="btnUser" class="btn btn-primary" onClick="acao.value='1' ; this.form.submit();">Verificar</button>
		</div>
	</form>
</body>
</html>