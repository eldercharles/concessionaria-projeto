package br.com.livro.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO extends BaseDAO {                             // CLASSE FILHA DA BaseDAO PARA HERDAR O METODO (getConnection()).
	public Carro getCarroByCod_veiculo(Long cod_veiculo) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from tab_veiculo where cod_veiculo=?");  // (PreparedStatement) Um objeto que representa uma declaração SQL pré-compilada.
			stmt.setLong(1, cod_veiculo);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Carro c = createCarro(rs);
				rs.close();
				return c;
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

	public List<Carro> findByModelo(String modelo) throws SQLException {
		List<Carro> carros = new ArrayList<Carro>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from tab_veiculo where lower(modelo) like ?");
			stmt.setString(1, "%" + modelo.toLowerCase() +"%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Carro c = createCarro(rs);
				carros.add(c);
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
		return carros;
	}

	public List<Carro> findByFabricante(String fabricante) throws SQLException {
		List<Carro> carros = new ArrayList<Carro>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from tab_veiculo where fabricante = ?");
			stmt.setString(1, fabricante);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Carro c = createCarro(rs);
				carros.add(c);
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
		return carros;
	}

	public List<Carro> getCarros() throws SQLException {
		List<Carro> carros = new ArrayList<Carro>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from tab_veiculo");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Carro c = createCarro(rs);
				carros.add(c);
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
		return carros;
	}
   
	public List<String> getFabricantes() throws SQLException {
		List<String> fabricantes = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select fabricante from tab_veiculo " + 
					" group by fabricante");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
			fabricantes.add(rs.getString("fabricante"));
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
		return fabricantes;
	}
	
	public List<String> getModelosByFabricante(String fabricante) throws SQLException {
		List<String> modelos = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select modelo from tab_veiculo where fabricante = ? group by modelo");
			stmt.setString(1, fabricante);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				modelos.add(rs.getString("modelo"));
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
		return modelos;
	}


	
	
	public Carro createCarro(ResultSet rs) throws SQLException {
		Carro c = new Carro();
		c.setCod_veiculo(rs.getLong("cod_veiculo"));
		c.setFabricante(rs.getString("fabricante"));
		c.setModelo(rs.getString("modelo"));
		c.setEspecificacoes(rs.getString("especificacoes"));
		c.setAnoFabricacao(rs.getString("ano_Fabricacao"));
		c.setAnoModelo(rs.getString("ano_Modelo"));
		c.setData(rs.getDate("data_cadastro"));
		c.setTipoCombustivel(rs.getString("tipo_combustivel"));
		c.setValor(rs.getString("valor"));
		c.setPlaca(rs.getString("placa"));
		c.setCor(rs.getString("cor"));
		
		return c;
	}

	public void save(Carro c) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if (c.getCod_veiculo() == null) {
				stmt = conn
						.prepareStatement(
								"insert into tab_veiculo (modelo,especificacoes,ano_Fabricacao,ano_Modelo,data_cadastro,tipo_combustivel,fabricante,valor,placa,cor) VALUES(?,?,?,?,NOW(),?,?,?,?,?)",
								Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn
						.prepareStatement("update tab_veiculo set modelo=?,especificacoes=?,ano_Fabricacao=?,ano_Modelo=?,tipo_combustivel=?,fabricante=?,valor=?,placa=?,cor=? where cod_veiculo=?");
			}
			stmt.setString(1, c.getModelo());
			stmt.setString(2, c.getEspecificacoes());
			stmt.setString(3, c.getAnoFabricacao());
			stmt.setString(4, c.getAnoModelo());
			stmt.setString(5, c.getTipoCombustivel());
			stmt.setString(6, c.getFabricante());
			stmt.setString(7, c.getValor());
			stmt.setString(8, c.getPlaca());
			stmt.setString(9, c.getCor());
			if (c.getCod_veiculo() != null) {
				// Update
				stmt.setLong(10, c.getCod_veiculo());
			}
			int count = stmt.executeUpdate();
			
			if (count == 0) {
				throw new SQLException("Erro ao inserir o carro");
			}
			
			// Se inseriu, ler o id auto incremento
			if (c.getCod_veiculo() == null) {
				Long cod_veiculo = getGeneratedCod_veiculo(stmt);
				c.setCod_veiculo(cod_veiculo);
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

	// codigo gerado com o campo auto incremento
	public static Long getGeneratedCod_veiculo(Statement stmt) throws SQLException {
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			Long cod_veiculo = rs.getLong(1);
			return cod_veiculo;
		}
		return 0L;
	}

	public boolean delete(Long cod_veiculo) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("delete from tab_veiculo where cod_veiculo=?");
			stmt.setLong(1, cod_veiculo);
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