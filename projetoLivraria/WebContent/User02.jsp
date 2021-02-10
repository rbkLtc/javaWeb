<%@page import="classes.User"%>
<%@page import="classes.Livro"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
</style>
<meta charset="ISO-8859-1">
<title>Bem vindo</title>
</head>
<body>
<%
	String nivel = (String) session.getAttribute("niv");
	if (nivel == null){
		response.sendRedirect("Error.html");
	}else if (nivel.equals("2") || nivel.equals("3")){	
		String email = (String) session.getAttribute("logine");
		String senha = (String) session.getAttribute("logins");
		String acao = request.getParameter("acao");
		
		User user = new User(email, senha);
		String id_livro = request.getParameter("id_livro");
		if (user.checkLogin()){
			String titulo = request.getParameter("titulo");
			String autor = request.getParameter("autor");
			String ano = request.getParameter("ano");
			String categ = request.getParameter("categ");		
			
			String saida = new Livro().lerTodos();
			response.getWriter().write(saida);
			if (acao != null){
				if (Integer.valueOf(acao) == 1){
					Livro livro = new Livro (titulo, autor, ano, categ);
					livro.cadastrar(); response.sendRedirect("User02.jsp");
				}else if (Integer.valueOf(acao) == 2){
					Livro livro = new Livro (titulo, autor, ano, categ);
					livro.atualiza(id_livro); response.sendRedirect("User02.jsp");
				}else if(Integer.valueOf(acao) == 3){
					Livro livro = new Livro (id_livro);
					livro.apagar(); response.sendRedirect("User02.jsp");
				}else if(Integer.valueOf(acao) == 4){
					response.getWriter().write("Error");
				}
			}
		}
	}else if (nivel.equals("1")) {
		response.sendRedirect("Error.html");
	}
	%>
	
	<form action="User02.jsp" method="post" class="form-horizontal">
		<h3>Gerenciar livros</h3>
		<div class="form-froup">
			<div><label for="titulo">Titulo</label>
			<input type="text" id="titulo" name="titulo" class="form-control">
			</div>
			<div><label for="autor">Autor</label>
			<input type="text" id="autor" name="autor" class="form-control">
			</div>
			<div><label for="ano">Ano</label>
			<input type="text" id="ano" name="ano" class="form-control">
			</div><div>
			<label for="categ">Categoria</label>
			<input type="text" id="categ" name="categ" class="form-control">
			</div><br><div>
			<input type="hidden" name="acao" value="0">
			<button type="button" id="btnUser" class="btn btn-primary" onClick="acao.value='1' ; this.form.submit();">Cadastrar</button>
			<br><br></div><div><a>Para atualizar ou deletar um livro por favor insira o id do mesmo</a><br>
			<label for="id_user">Id livro:</label><br>
			<input type="text" id="id_livro" name="id_livro" class="form-control"><br>
			<button type="button" id="btnUser" class="btn btn-primary" onClick="acao.value='2' ; this.form.submit();">Atualizar</button>
			<button type="button" id="btnUser" class="btn btn-primary" onClick="acao.value='3' ; this.form.submit();">Deletar</button>
			</div>
		</div>
	</form>
</body>
</html>