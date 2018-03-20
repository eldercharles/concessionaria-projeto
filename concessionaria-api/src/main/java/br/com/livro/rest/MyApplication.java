package br.com.livro.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Application;

public class MyApplication extends Application {     // CLASSE PARA UTILIZAR O JERSEY, QUE O CONFIGURA
//	@Override
//	public Set<Object> getSingletons() {             // <----- RESPONSAVEL POR ADICIONAR FUNCIONALIDADES AO JERSEY.
//		Set<Object> singletons = new HashSet<>();
//		// Driver do Jettison para gerar JSON.	
//		singletons.add(new JettisonFeature());        // <-- RETORNAR EM FORMATO JETTISON
//		return singletons;
//	}

	@Override
	public Map<String, Object> getProperties() {    // <--- INDICA QUAL AO JERSEY EM QUAL PACOTE ESTAO AS CLASSES DOS WEB SERVICES.
		Map<String, Object> properties = new HashMap<>();
		// Configura o pacote para fazer scan das classes com anotações REST.
		properties
				.put("jersey.config.server.provider.packages", "br.com.livro");
		return properties;
	}
}
