package br.com.livro.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	public BaseDAO() {
		try { 
			// Necessario para utilizar o driver JDBC do MySQL                           // CONECTAR BANCO DE DADOS COM MYSQL
			Class.forName("com.mysql.jdbc.Driver");   
		} catch (ClassNotFoundException e) {
			// Erro de driver JDBC (adicione o driver .jar do MySQL em
			// /WEB-INF/lib)
			e.printStackTrace();
		}
	}

	protected Connection getConnection() throws SQLException {
		// URL de conexao com o banco de dados
		String url = "jdbc:mysql://localhost/dbveiculos";
		// Conecta utilizando a URL, usuario e senha.
		Connection conn = DriverManager.getConnection(url, "root","");
		return conn;
	}

	public static void main(String[] args) throws SQLException {
		BaseDAO db = new BaseDAO();
		// Testa a conexao
		Connection conn = db.getConnection();
		System.out.println(conn);
	}
}
