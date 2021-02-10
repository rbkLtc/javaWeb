package db;

import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {
	
	private Statement statement =  null;
	private String    tabela 	= "";
	private String[]  coluna 	= new String[]{};
	private String    chave  	= "";
	private int		  keyFieldIndex = -1;
	
	
	public DBQuery() {
		try {
			this.statement = new DBConnection().getConnection().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public DBQuery(	String tabela, String coluna,  String chave) {
		this.setTabela	(tabela);
		this.setColuna	(coluna);
		this.setChave	(chave); 
		try {
			this.statement = new DBConnection().getConnection().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String[] dontInjectionStrings(String[] values) {
		String[] tempValues = values;
		for (int i = 0; i < tempValues.length; i++) {
			
		}
		return null;
	}
	
	private int whereIsKeyField() {
		for ( int i =0; i < this.coluna.length; i ++ ){
			if( this.coluna[i].equals(this.chave) ){
				return(i);
			}
		}
		return(-1);
	}
	
	public String joinElements (String[] elements, String separator){
		String out = "";
		for (int i=0; i< elements.length; i++) {
			out +=  elements[i].trim() +  ((i == elements.length-1) ? "" : separator );
		}
		return (out);
	}
	
	public ResultSet query(String sql) {
		try {
			ResultSet rs = statement.executeQuery(sql);
			return (rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int execute(String sql) {
		try {
			int rs = statement.executeUpdate(sql);
			return (rs);
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		return 0;
	}
	
	public int insere(String[] values) {
		if ( values.length == this.coluna.length){
			String sql = "INSERT INTO "+this.tabela+" ( "+  this.joinElements(this.coluna, ", ");
			sql += ") VALUES ('"+joinElements(values, "','")+"')";
			return ( this.execute(sql));
		}else{
			System.out.print("Error: provavelmente incompatibilidades entre valores e colunas");
		}	
		return 0;
	}
	
	public int deleta(String[] values) {
		String sql = "DELETE FROM "+this.tabela+" ";
		if ( this.keyFieldIndex < 0 ){
			return(0);
		}else
		sql += "WHERE "+ this.chave +" = '"+ values[this.keyFieldIndex] +"'";
		return ( this.execute(sql) );
	}
	
	public ResultSet ler (String where) {
		String sql = "SELECT "+  this.joinElements(this.coluna, ", ") + " FROM " + this.tabela;
		sql += (( where!="") ? " WHERE "+ where : "" );
		return this.query(sql);
	}
	
	public int gravaUp(String[] values) {
		if (values.length != this.coluna.length){
			System.out.println("Error: colunas e valores nao correspondem!");
			return ( 0 );
		}
		String sql = "UPDATE "+this.tabela+" SET ";
		for( int i=0; i <  values.length; i++){
			sql += "\n\t "+
				this.coluna[i] + " = '"+values[i].trim()+"'" 
				+  ((i == values.length-1) ? "" : ", ");
		}
		if ( this.keyFieldIndex < 0 ){
			return(0);
		}
		sql += " WHERE "+ this.chave +" = '"+ values[this.keyFieldIndex] +"'";
		return ( this.execute(sql) );
	}
		
	public int nivel(String id, String niv) {
		String sql = "UPDATE "+this.tabela+" SET "+this.coluna[4]+" = "+niv+" WHERE "+this.chave+" = "+id+" ";
		int rs;
		try {
			this.statement = new DBConnection().getConnection().createStatement();
			rs = statement.executeUpdate(sql);
			return (rs);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}
	
	public String getTabela() {
		return tabela;
	}


	public void setTabela(String tabela) {
		this.tabela = tabela;
	}


	public String[] getColuna() {
		return coluna;
	}


	public void setColuna(String coluna) {
		this.coluna	= coluna.split(",");
		for (int i=0;  i< this.coluna.length; i++) {
			this.coluna[i] = this.coluna[i].trim();
		};
	}
	
	public String getChave() {
		return chave;
	}


	public void setChave(String chave) {
		this.chave = chave;
		this.keyFieldIndex = whereIsKeyField();

	}

	public int getKeyFieldIndex() {
		return keyFieldIndex;
	}

	public void setKeyFieldIndex(int keyFieldIndex) {
		this.keyFieldIndex = keyFieldIndex;
	}

}
