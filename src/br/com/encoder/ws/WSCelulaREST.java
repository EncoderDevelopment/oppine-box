package br.com.encoder.ws;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import android.util.Log;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoPreferencias;
import br.com.encoder.unidade.UnidadeAvaliacao;
import br.com.encoder.unidade.UnidadeCategoriaEmpresa;
import br.com.encoder.unidade.UnidadeEmpresa;
import br.com.encoder.unidade.UnidadeFncionario;
import br.com.encoder.unidade.UnidadeFncionarioRanking;
import br.com.encoder.unidade.UnidadeSugestaoEmpresa;

public class WSCelulaREST {
	
	// EX: 200.153.36.140:8070
	private static String URL = "http://www.oppinebox.com.br/oppbox/ws/consulta.php";

	/**
	 * 
	 * Método responsável por fazer chamada ao web service e buscar as
	 * informações(json)atraves da URI.
	 */

	public List<UnidadeFncionario> getFuncionariosPorEmpresa(int id) throws Exception {

		ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
	    valores.add(new BasicNameValuePair("acao", "listarFuncionarios"));
	    valores.add(new BasicNameValuePair("emprId", Integer.toString(id)));
	    valores.add(new BasicNameValuePair("chave", "2d97d228ffea1b32f7531910ebfdd35a"));

	    // Array de String que recebe o JSON do Web Service
		String[] json = new WS().post(URL, valores);

		List<UnidadeFncionario> funcionarios = new ArrayList<UnidadeFncionario>();

		if (json[0].equals("200")) {

			Gson gson = new Gson();

			JsonParser parser = new JsonParser();

			// Fazendo o parse do JSON para um JsonArray
			JsonArray array = parser.parse(json[1]).getAsJsonArray();

			for (int i = 0; i < array.size(); i++) {

				// Adicionando na lista a posicao atual do JsonArray
				funcionarios.add(gson.fromJson(array.get(i), UnidadeFncionario.class));

			}
			// retornado a lista que consumiu do WS
			return funcionarios;

		} else {

		}
		throw new Exception(json[1]);

	}


	public List<UnidadeEmpresa> postEmpresasPorIdCategoria(int id) throws Exception {

		ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
	    valores.add(new BasicNameValuePair("acao", "consultaEmpresa"));
	    valores.add(new BasicNameValuePair("categoria", Integer.toString(id)));
	    valores.add(new BasicNameValuePair("chave", "2d97d228ffea1b32f7531910ebfdd35a"));

	    // Array de String que recebe o JSON do Web Service
		String[] json = new WS().post(URL, valores);

		List<UnidadeEmpresa> empresas = new ArrayList<UnidadeEmpresa>();

		if (json[0].equals("200")) {

			Gson gson = new Gson();

			JsonParser parser = new JsonParser();

			// Fazendo o parse do JSON para um JsonArray
			JsonArray array = parser.parse(json[1]).getAsJsonArray();

			for (int i = 0; i < array.size(); i++) {

				// Adicionando na lista a posicao atual do JsonArray
				empresas.add(gson.fromJson(array.get(i), UnidadeEmpresa.class));
				//String e = gson.toJson(empresas);
				//ConfiguracaoPreferencias.salvaPreferencias(e);
				

			}
			// retornado a lista que consumiu do WS
			return empresas;

		} else {

		}
		throw new Exception(json[1]);

	}

	public List<UnidadeEmpresa> getEmpresasPorNomeEmpresa(String nome) throws Exception {

		ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
	    valores.add(new BasicNameValuePair("acao", "consultaEmpresa"));
	    valores.add(new BasicNameValuePair("nome", nome));
	    valores.add(new BasicNameValuePair("chave", "2d97d228ffea1b32f7531910ebfdd35a"));

	    // Array de String que recebe o JSON do Web Service
		String[] json = new WS().post(URL, valores);

		List<UnidadeEmpresa> empresas = new ArrayList<UnidadeEmpresa>();

		if (json[0].equals("200")) {

			Gson gson = new Gson();

			JsonParser parser = new JsonParser();

			// Fazendo o parse do JSON para um JsonArray
			JsonArray array = parser.parse(json[1]).getAsJsonArray();

			for (int i = 0; i < array.size(); i++) {

				// Adicionando na lista a posicao atual do JsonArray
				empresas.add(gson.fromJson(array.get(i), UnidadeEmpresa.class));

			}
			// retornado a lista que consumiu do WS
			return empresas;

		} else {

		}
		
		throw new Exception(json[1]);

	}
	
	
	public List<UnidadeFncionarioRanking> getRankingPorIdFuncionario(int idFuncionario) throws Exception {

		ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
		 valores.add(new BasicNameValuePair("chave", "2d97d228ffea1b32f7531910ebfdd35a"));
	    valores.add(new BasicNameValuePair("acao", "resultadoParcial"));
	    valores.add(new BasicNameValuePair("idFunc", Integer.toString(idFuncionario)));
	   
	    // Array de String que recebe o JSON do Web Service
		String[] json = new WS().post(URL, valores);

		List<UnidadeFncionarioRanking> funcionarios = new ArrayList<UnidadeFncionarioRanking>();

		if (json[0].equals("200")) {

			Gson gson = new Gson();

			JsonParser parser = new JsonParser();

			// Fazendo o parse do JSON para um JsonArray
			JsonArray array = parser.parse(json[1]).getAsJsonArray();

			for (int i = 0; i < array.size(); i++) {

				// Adicionando na lista a posicao atual do JsonArray
				funcionarios.add(gson.fromJson(array.get(i), UnidadeFncionarioRanking.class));

			}
			// retornado a lista que consumiu do WS
			return funcionarios;

		} else {

		}
		throw new Exception(json[1]);

	}

	public List<UnidadeCategoriaEmpresa> getCategoriasEmpreas() throws Exception {

		// Array de String que recebe o JSON do Web Service
		String[] json = new WS().get(URL + "?acao=listarCategorias&chave=2d97d228ffea1b32f7531910ebfdd35a");

		List<UnidadeCategoriaEmpresa> categoriaEmpresas = new ArrayList<UnidadeCategoriaEmpresa>();

		if (json[0].equals("200")) {

			Gson gson = new Gson();

			JsonParser parser = new JsonParser();

			// Fazendo o parse do JSON para um JsonArray
			JsonArray array = parser.parse(json[1]).getAsJsonArray();

			for (int i = 0; i < array.size(); i++) {

				// Adicionando na lista a posicao atual do JsonArray
				categoriaEmpresas.add(gson.fromJson(array.get(i), UnidadeCategoriaEmpresa.class));

			}
			// retornado a lista que consumiu do WS
			return categoriaEmpresas;

		} else {

		}
		throw new Exception(json[1]);

	}

	public void getGravarAvaliacao(UnidadeAvaliacao avaliacao) throws Exception {
			
		ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
	    valores.add(new BasicNameValuePair("chave", "2d97d228ffea1b32f7531910ebfdd35a"));
	    valores.add(new BasicNameValuePair("acao", "gravarVotacao"));
	    valores.add(new BasicNameValuePair("idFunc", Integer.toString(avaliacao.getIdFuncionario())));
	    
	    valores.add(new BasicNameValuePair("atends", avaliacao.getComSorriso() + ","
				+ avaliacao.getComRapidez() + "," + avaliacao.getComAtencao() + "," + avaliacao.getComEmpatia() + ","
				+ avaliacao.getComRespeito() + "," + avaliacao.getComEducacao() + ","
				+ avaliacao.getComInformacoesCorretas() + "," + avaliacao.getComPontualidade()));
	    
	    valores.add(new BasicNameValuePair("nota", Integer.toString(avaliacao.getNota())));
	    valores.add(new BasicNameValuePair("coment", avaliacao.getComentario()));

		String[] json = new WS().post(URL, valores);

		if (json[0].equals("200")) {
			System.out.println("Votação enviada com sucesso");
		} 
		throw new Exception(json[1]);

	}

	public void postGravarSugestaoEmpresa(UnidadeSugestaoEmpresa sugestao) throws Exception {
			
		ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
	    valores.add(new BasicNameValuePair("acao", "sugerirEmpresa"));
	    valores.add(new BasicNameValuePair("chave", "2d97d228ffea1b32f7531910ebfdd35a"));
	    valores.add(new BasicNameValuePair("empr", sugestao.getNomeEmpresa()));
	    valores.add(new BasicNameValuePair("telef", sugestao.getFoneEmpresa()));
	    valores.add(new BasicNameValuePair("cliente", Integer.toString(sugestao.getCliente())));
	    valores.add(new BasicNameValuePair("func", sugestao.getNomeFuncionario()));
	    valores.add(new BasicNameValuePair("coment", sugestao.getComentariosObservacoes()));

		String[] json = new WS().post(URL, valores);
		
		if (json[0].equals("200")) {
			System.out.println("Sugestão de empresa enviada com sucesso");
			Log.d("post", "Sugestão de empresa enviada com sucesso");
		}
		throw new Exception(json[1]);

	}
}
