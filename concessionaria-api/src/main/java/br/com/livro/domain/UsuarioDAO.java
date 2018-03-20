package br.com.livro.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends BaseDAO {
	public Usuario getUsuarioById(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from usuario where id=?"); 
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Usuario u = createUsuario(rs);
				rs.close();
				return u;
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public Usuario Autenticacao(Usuario usuario) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from usuario where email = ? and senha = ?"); 
			
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Usuario u = createUsuario(rs);
				rs.close();
				return u;
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public List<Usuario> getUsuarios() throws SQLException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from usuario");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Usuario u = createUsuario(rs);
				usuarios.add(u);
			}
			rs.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return usuarios;
	}

	public Usuario createUsuario(ResultSet rs) throws SQLException {
		Usuario u = new Usuario();
		u.setId(rs.getLong("id"));
		u.setNome(rs.getString("nome"));
		u.setEmail(rs.getString("email"));
		u.setSenha(rs.getString("senha"));
		return u;
	}
	
	// codigo gerado com o campo auto incremento
		public static Long getGeneratedId(Statement stmt) throws SQLException {
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				Long id = rs.getLong(1);
				return id;
			}
			return 0L;
		}
	
	public void save(Usuario u) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if (u.getId() == null) {
				stmt = conn
						.prepareStatement(
								"insert into usuario (nome,email,senha) VALUES(?,?,?)",
								Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn
						.prepareStatement("update usuario set nome=?,email=?,senha=? where id=?");
			}
			stmt.setString(1, u.getNome());
			stmt.setString(2, u.getEmail());
			stmt.setString(3, u.getSenha());
			if (u.getId() != null) {
				// Update
				stmt.setLong(4, u.getId());
			}
			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir o carro");
			}
			
			// Se inseriu, ler o id auto incremento
			if (u.getId() == null) {
				Long id = getGeneratedId(stmt);
				u.setId(id);
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public boolean delete(Long id) throws SQLException {   // Deletar usuario
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("delete from usuario where id=?");
			stmt.setLong(1, id);
			int count = stmt.executeUpdate();
			boolean ok = count > 0;
			return ok;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
}
