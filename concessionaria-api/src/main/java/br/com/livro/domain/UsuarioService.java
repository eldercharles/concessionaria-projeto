 package br.com.livro.domain;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
	private UsuarioDAO db = new UsuarioDAO();            // INTERMEDIADOR ENTRE A CAMADA WEB E A CAMADA DE PERSISTENCIA.

	// Lista todos os usuarios do banco de dados
	public List<Usuario> getUsuarios() {
		try {
			List<Usuario> usuarios = db.getUsuarios();
			return usuarios;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Usuario>();
		}
	}

	// Busca um usuario pelo id
	public Usuario getUsuario(Long id) {
		try {
			return db.getUsuarioById(id);
		} catch (SQLException e) {
			return null;
		}
	}
	
	public Usuario autenticar(Usuario usuario) {
		try {
			// encriptografar a senha
			usuario.setSenha(getMD5(usuario.getSenha()));
			return db.Autenticacao(usuario);
		} catch (SQLException e) {
			return null;
		}
	}

	// Deleta o usuario pelo id
	public boolean delete(Long id) {
		try {
			return db.delete(id);
		} catch (SQLException e) {
			return false;
		}
	}

	// Salva ou atualizar o usuario
	public boolean save(Usuario usuario) {
		try {
			usuario.setSenha(getMD5(usuario.getSenha()));
			db.save(usuario);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
