package classes;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DBQuery;

public class User {
		
		private int id_user;
		private String nome;
		private String email;
		private String senha;
		private String nivel = "1";
		
		private String tabela	= "livraria.users"; 
		private String coluna	= "id_user, nome, email, senha, nivel";  
		private String chave	= "id_user";
		private String where 	= "";
		private DBQuery dbQuery = new DBQuery(tabela, coluna, chave);
		
		
		public User () {
		}
		
		public User (int Id_user) {
		this.setId_user(id_user);
		}
		
		public User (String id_user) {
			this.setId_user(((id_user==null)?0:Integer.valueOf(id_user)));	
		}
		
		public User (String email, String senha) {
			this.setEmail(email);
			this.setSenha(senha);
		}
		
		public User (String nome, String email, String senha) {
			this.setNome(nome);
			this.setEmail(email);
			this.setSenha(senha);
		}
		
		private String[] toArray() {
			
			String[] temp =  new String[] {
					this.getId_user() + "",
					this.getNome() + "",
					this.getEmail(),
					this.getSenha(),
					this.getNivel() + "",
			};
			return(temp);
		}
		
		public void salvar() {
			this.dbQuery.insere(this.toArray());
		}
		
		public void deleta() {
			if( this.getId_user() > 0) {
				this.dbQuery.deleta(this.toArray());
			}
		}
		
		public String lerTodos() {
			ResultSet rs = this.dbQuery.ler("");
			String saida = "<br>";
			saida+="<table border =1>";
			saida+="<td>" + ("Id") + "</td>";
			saida+="<td>" + ("Nome") + "</td>";
			saida+="<td>" + ("Email") + "</td>";
			saida+="<td>" + ("Nivel") + "</td>";
			try {
				while (rs.next()) {
					saida += "<tr>";
					saida += "<td>" + rs.getString("id_user") + "</td>";
					saida += "<td>" + rs.getString("nome") + "</td>";
					saida += "<td>" + rs.getString("email") + "</td>";
					saida += "<td>" + rs.getString("nivel") + "</td>";
					saida += "</tr> <br>";
					} 
				}catch (SQLException e) {
					e.printStackTrace();
				}
				saida += "</table>";
			return(saida);
		}
		
		public ResultSet lerPor( String coluna, String value ) {
			ResultSet resultset = this.dbQuery.ler( " "+coluna+"='"+value+"'");
			return(resultset);
		}
		
		public ResultSet ler( String where ) {
			ResultSet resultset = this.dbQuery.ler(where);
			return(resultset);
		}
		
		public boolean checkLogin() {
			if (this.getEmail() != null) {
				try {
					ResultSet resultSet = this.ler(" email='"+ this.getEmail()+ "' AND senha = '"+this.getSenha()+"'");
					while (resultSet.next()) {
						this.setId_user(resultSet.getInt("id_user"));
						this.setNivel(resultSet.getString("nivel")); ;
						return(true);
					}
				}catch (SQLException e) {
					e.printStackTrace();
				}	
			}
			return false;
		}
		
		public void verificaEmail() {
			if (this.getEmail() != null) {
				try {
					ResultSet rs = this.ler(" email='"+ this.getEmail()+ "' ");
					boolean existe = rs.next();
					if ( existe ) {
						this.setId_user(rs.getInt("id_user"));
						this.dbQuery.gravaUp(this.toArray());
					}	
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void nivel(String niv) {
			String id = this.getId_user() + "";
			this.setNivel(niv);
			this.dbQuery.nivel(id, this.nivel);	
		}
		
		public int getId_user() {
			return id_user;
		}

		public void setId_user(int id_user) {
			this.id_user = id_user;
		}


		public String getNome() {
			return nome;
		}


		public void setNome(String nome) {
			this.nome = nome;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getSenha() {
			return senha;
		}


		public void setSenha(String senha) {
			this.senha = senha;
		}
		
		public String getNivel() {
			return nivel;
		}


		public void setNivel(String nivel) {
			this.nivel = nivel;
		}
		
		public String getWhere() {
			return where;
		}

		public void setWhere(String where) {
			this.where = where;
		}
}
