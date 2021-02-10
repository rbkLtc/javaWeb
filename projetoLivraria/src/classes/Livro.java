package classes;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DBQuery;

public class Livro {
		
		private int id_livro;
		private String titulo;
		private String autor;
		private String ano;
		private String categ;
		
		private String tabela	= "livraria.livros"; 
		private String coluna	= "id_livro, titulo, autor, ano, categ";  
		private String chave	= "id_livro";
		private DBQuery dbQuery = new DBQuery(tabela, coluna, chave);
		
		public Livro () {
		}
		public Livro (int Id_livro) {
			this.setId_livro(id_livro);
		}
		
		public Livro (String id_livro) {
			this.setId_livro(((id_livro==null)?0:Integer.valueOf(id_livro)));
		}
		
		public Livro (String titulo, String autor, String ano, String categ) {
			this.setTitulo(titulo);
			this.setAutor(autor);
			this.setAno(ano);
			this.setCateg(categ);
		}
		
		private String[] toArray() {
			return (
				new String [] {
						
				""+this.getId_livro(),
				""+this.getTitulo(),		
				""+this.getAutor(),
				""+this.getAno(),
				""+this.getCateg()
				}
			);
		}
		
		public void cadastrar() {
			this.dbQuery.insere(this.toArray());
		}
		
		public void atualiza(String id) {
				this.setId_livro(Integer.valueOf(id));
				this.dbQuery.gravaUp(this.toArray());
		}
		
		public void apagar() {
			if( this.getId_livro() != 0) {
				this.dbQuery.deleta(this.toArray());
			}
		}
		
		public String lerTodos() {
			ResultSet rs = this.dbQuery.ler("");
			String saida = "<br>";
			saida+="<table border =1>";
			saida+="<td>" + ("Id") + "</td>";
			saida+="<td>" + ("Titulo") + "</td>";
			saida+="<td>" + ("Autor") + "</td>";
			saida+="<td>" + ("Ano") + "</td>";
			saida+="<td>" + ("Genero") + "</td>";
			try {
				while (rs.next()) {
					saida += "<tr>";
					saida += "<td>" + rs.getString("id_livro") + "</td>";
					saida += "<td>" + rs.getString("titulo") + "</td>";
					saida += "<td>" + rs.getString("autor") + "</td>";
					saida += "<td>" + rs.getString("ano") + "</td>";
					saida += "<td>" + rs.getString("categ") + "</td>";
					saida += "</tr> <br>";
					} 
				}catch (SQLException e) {
					e.printStackTrace();
				}
				saida += "</table>";
			return(saida);
		}

		public ResultSet lerPor( String coluna, String value ) {
			ResultSet rs = this.dbQuery.ler( " "+coluna+"='"+value+"'");
			return(rs);
		}
		
		public ResultSet ler( String where ) {
			ResultSet resultset = this.dbQuery.ler(where);
			return(resultset);
		}
	
		public int getId_livro() {
			return id_livro;
		}

		public void setId_livro(int id_livro) {
			this.id_livro = id_livro;
		}

		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public String getAutor() {
			return autor;
		}

		public void setAutor(String autor) {
			this.autor = autor;
		}

		public String getAno() {
			return ano;
		}

		public void setAno(String ano) {
			this.ano = ano;
		}

		public String getCateg() {
			return categ;
		}

		public void setCateg(String categ) {
			this.categ = categ;
		}

		
}
