package classes;
import java.util.Random;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;


public class Sendmail {
	
	private String code;
	private String email;
	private String senha;
	private String meuEmail = "usertestyy@gmail.com";
	private String minhaSenha = "pkltrewssqjk";
	
	public Sendmail() {	
	}
	
	public Sendmail (String email, String senha) {
		this.setEmail(email);
		this.setSenha(senha);
	}
	
	public void enviar() {
		
		SimpleEmail config = new SimpleEmail();
		config.setHostName("smtp.gmail.com");
		config.setSmtpPort(465);
		config.setAuthenticator(new DefaultAuthenticator(this.meuEmail, this.minhaSenha));
		config.setSSLOnConnect(true);
		
		try {
			config.setFrom(this.meuEmail);
			config.setSubject("Codigo de verificacao");
			config.setMsg("A seguir o codigo para verificacao de cadastro: " + this.senha);
			config.addTo(this.email);
			config.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String GenerateCode () {
		Random random = new Random();
        int aux = random.nextInt(1000000000);
        this.code = String.valueOf(aux);
		return (this.code);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getMeuEmail() {
		return meuEmail;
	}

	public void setMeuEmail(String meuEmail) {
		this.meuEmail = meuEmail;
	}

	public String getMinhaSenha() {
		return minhaSenha;
	}

	public void setMinhaSenha(String minhaSenha) {
		this.minhaSenha = minhaSenha;
	}

}