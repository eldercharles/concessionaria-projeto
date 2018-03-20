
package br.com.livro.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarroService {
	private CarroDAO db = new CarroDAO();            // INTERMEDIADOR ENTRE A CAMADA WEB E A CAMADA DE PERSISTENCIA.

	// Lista todos os carros do banco de dados
	public List<Carro> getCarros() {
		try {
			List<Carro> carros = db.getCarros();
			return carros;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Carro>();
		}
	}
	
	// Lista todos os fabricantes
	public List<String> getFabricantes() {
		try {
			List<String> fabricantes= db.getFabricantes();
			return fabricantes;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}
	}
	
	// Lista todos os modelos com fabricantes
		public List<String> getModelosByFabricante(String fabricante) {
			try {
				List<String> modelos= db.getModelosByFabricante(fabricante);
				return modelos;
			} catch (SQLException e) {
				e.printStackTrace();
				return new ArrayList<String>();
			}
		}

	// Busca um carro pelo cod_veiculo
	public Carro getCarro(Long cod_veiculo) {
		try {
			return db.getCarroByCod_veiculo(cod_veiculo);
		} catch (SQLException e) {
			return null;
		}
	}

	// Deleta o carro pelo cod_veiculo
	public boolean delete(Long cod_veiculo) {
		try {
			return db.delete(cod_veiculo);
		} catch (SQLException e) {
			return false;
		}
	}

	// Salva ou atualizar o carro
	public boolean save(Carro carro) {
		try {
			db.save(carro);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Busca o carro pelo modelo
	public List<Carro> findByModelo(String modelo) {
		try {
			return db.findByModelo(modelo);
		} catch (SQLException e) {
			return null;
		}
	}

	public List<Carro> findByFabricante(String fabricante) {
		try {
			return db.findByFabricante(fabricante);
		} catch (SQLException e) {
			return null;
		}
	}
}

