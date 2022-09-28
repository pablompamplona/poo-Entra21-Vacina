package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Banco;
import model.entidade.Pesquisador;
import model.entidade.Vacina;

public class VacinaRepository {
	
	public Vacina inserir(Vacina novaVacina) {
		Connection conexao = Banco.getConnection();
		
		String query = " INSERT INTO vacina (paisOrigem, estagioPesquisa, dataInicioPesquisa, idPesquisador) "
				     + " VALUES (?, ?, ?, ?) ";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, query);
		try {
			stmt.setString(1, novaVacina.getPaisOrigem());
			stmt.setInt(2, novaVacina.getEstagioPesquisa());
			stmt.setDate(3, new java.sql.Date(novaVacina.getDataInicioPesquisa().getTime()));
			stmt.setInt(4, novaVacina.getPesquisador().getIdPesquisador());
			stmt.execute();
			
			ResultSet chavesGeradas = stmt.getGeneratedKeys();
			if(chavesGeradas.next()) {
				novaVacina.setId(chavesGeradas.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir vacina.\nCausa: " + e.getMessage());
		}finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}
		
		return novaVacina;
	}
		
	public Vacina pesquisarPorId(int id) {
		Vacina vacinaBuscada = null;
		Pesquisador pesquisador = null;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM VACINA WHERE idVacina = ? ";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		try {
			stmt.setInt(1, id);
			ResultSet resultado = stmt.executeQuery();
			if(resultado.next()) {
				vacinaBuscada = new Vacina();
				vacinaBuscada.setId(resultado.getInt("idVacina"));
				vacinaBuscada.setEstagioPesquisa(resultado.getInt("estagioPesquisa"));
				vacinaBuscada.setPaisOrigem(resultado.getString("paisOrigem"));
				vacinaBuscada.setDataInicioPesquisa(resultado.getDate("dataInicioPesquisa"));
								
				int idPesquisador = resultado.getInt("idPesquisador");
				PesquisadorRepository pr = new PesquisadorRepository();
				pesquisador = pr.pesquisarPorId(idPesquisador);
				vacinaBuscada.setPesquisador(pesquisador);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar vacina com id = " + id + " .\nCausa: "+ e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}
		return vacinaBuscada;
	}
	
	public ArrayList<Vacina> pesquisarTodas(){
		ArrayList<Vacina> vacinas = new ArrayList();
		Vacina vacinaBuscada = null;
		Pesquisador pesquisador = null;
		Connection conexao = Banco.getConnection();
		String query = " SELECT * FROM vacina ";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, query);
		try {
			ResultSet resultado = stmt.executeQuery();
			
			while(resultado.next()) {
				vacinaBuscada = new Vacina();
				vacinaBuscada.setId(resultado.getInt("idVacina"));
				vacinaBuscada.setEstagioPesquisa(resultado.getInt("estagioPesquisa"));
				vacinaBuscada.setPaisOrigem(resultado.getString("paisOrigem"));
				vacinaBuscada.setDataInicioPesquisa(resultado.getDate("dataInicioPesquisa"));
						
				int idPesquisador = resultado.getInt("idPesquisador");
				PesquisadorRepository pr = new PesquisadorRepository();
				pesquisador = pr.pesquisarPorId(idPesquisador);
				vacinaBuscada.setPesquisador(pesquisador);
				
				vacinas.add(vacinaBuscada);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar vacinas.\nCausa: "+ e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}
		
		return vacinas;
	}
	
	public boolean atualizar(Vacina vacina) {
		Connection conexao = Banco.getConnection();
		boolean atualizou = false;
		
		String query = " UPDATE VACINA SET paisOrigem = ?, estagioPesquisa = ?, dataInicioPesquisa = ?, IdPesquisador = ? "
				   + " WHERE ID = ? ";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, query);
		
		try {
			stmt.setString(1, vacina.getPaisOrigem());
			stmt.setInt(2, vacina.getEstagioPesquisa());
			stmt.setDate(3, new java.sql.Date(vacina.getDataInicioPesquisa().getTime()));
			stmt.setInt(4, vacina.getPesquisador().getIdPesquisador());
			stmt.setInt(5, vacina.getId());
			
			int linhasAfetadas = stmt.executeUpdate();
			atualizou = linhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar vacina.\nCausa: " + e.getMessage());
		}finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}
		
		return atualizou;
	}
	
	public List<Vacina> pesquisarPorIdResponsavel(int idResponsavel){
		List<Vacina> vacinasDoPesquisador = new ArrayList<Vacina>();
		Vacina vacinaBuscada = null;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM VACINA WHERE ID_RESPONSAVEL = ? ";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		try {
			stmt.setInt(1, idResponsavel);
			ResultSet resultado = stmt.executeQuery();
			if(resultado.next()) {
				vacinaBuscada = new Vacina();
				vacinaBuscada.setId(resultado.getInt("id"));
				vacinaBuscada.setEstagioPesquisa(resultado.getInt("estagio_pesquisa"));
				vacinaBuscada.setPaisOrigem(resultado.getString("pais_origem"));
				vacinaBuscada.setDataInicioPesquisa(resultado.getDate("data_inicio_pesquisa"));
				
				PesquisadorRepository pesquisadorRepository = new PesquisadorRepository();
				Pesquisador responsavelBuscado = pesquisadorRepository.pesquisarPorId(idResponsavel);
				
				vacinaBuscada.setPesquisador(responsavelBuscado);
				vacinasDoPesquisador.add(vacinaBuscada);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar vacinas do pesquisador = " + idResponsavel + " .\nCausa: "+ e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}
		return vacinasDoPesquisador;
	}
	
	
	//Delete
		public boolean excluir(int id) {
			boolean excluiu = false;
			Connection conexao = Banco.getConnection();
			String sql = " DELETE FROM VACINA WHERE idVacina = ? ";
			//Obter o preparedStatement
			PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
			try {
				stmt.setInt(1, id);
				int registrosExcluidos = stmt.executeUpdate();
				excluiu = (registrosExcluidos > 0);
			} catch (SQLException e) {
				System.out.println("Erro ao excluir vacina.\nCausa: " + e.getCause());
			} finally {
				Banco.closePreparedStatement(stmt);
				Banco.closeConnection(conexao);
			}
			
			return excluiu;
		}
}
