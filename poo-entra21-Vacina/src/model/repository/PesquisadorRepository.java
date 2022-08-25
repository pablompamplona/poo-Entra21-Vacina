package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Banco;
import model.entidade.Pesquisador;
import model.entidade.Vacina;

public class PesquisadorRepository {

	public Pesquisador inserir(Pesquisador pesquisador) {
		Connection conexao = Banco.getConnection();
		
		String query = " INSERT INTO Pesquisador(nome, cpf, matricula) VALUES (?, ?, ?) ";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, query);
		try {
			stmt.setString(1, pesquisador.getNome());
			stmt.setString(2, pesquisador.getCpf());
			stmt.setString(3, pesquisador.getMatricula());
			stmt.execute();
			
			ResultSet chavesGeradas = stmt.getGeneratedKeys();
			if(chavesGeradas.next()) {
				pesquisador.setIdPesquisador(chavesGeradas.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir pesquisador.\nCausa: " + e.getMessage());
		}finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}
		
		return pesquisador;
	}
	
	
	public Pesquisador pesquisarPorId(int id) {
		Pesquisador pesquisador = null;
		Connection conexao = Banco.getConnection();
		String query = " SELECT * FROM pesquisador WHERE idPesquisador = ? ";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, query);
		try {
			stmt.setInt(1, id);
			ResultSet resultado = stmt.executeQuery();
			if(resultado.next()) {
				pesquisador = new Pesquisador();
				pesquisador.setIdPesquisador(resultado.getInt("idPesquisador"));
				pesquisador.setNome(resultado.getString("nome"));
				pesquisador.setCpf(resultado.getString("cpf"));
				pesquisador.setMatricula("matricula");
								
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar pesquisador com idPesquisador = " + id + " .\nCausa: "+ e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}
		return pesquisador;
	}
	
	public ArrayList<Pesquisador> listarPesquisadores(){
		ArrayList<Pesquisador> pesquisadores = new ArrayList();
		Pesquisador pesquisador = null;
		Connection conexao = Banco.getConnection();
		String query = " SELECT * FROM pesquisador ";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, query);
		try {
			ResultSet resultado = stmt.executeQuery();
			
			while(resultado.next()) {
				pesquisador = new Pesquisador();
				pesquisador.setIdPesquisador(resultado.getInt("idPesquisador"));
				pesquisador.setNome(resultado.getString("nome"));
				pesquisador.setCpf(resultado.getString("cpf"));
				pesquisador.setMatricula(resultado.getString("matricula"));
				
				pesquisadores.add(pesquisador);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar pesquisadores.\nCausa: "+ e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}
		
		return pesquisadores;
	}
	
	public boolean atualizar(Pesquisador pesquisador) {
		Connection conexao = Banco.getConnection();
		boolean atualizou = false;
		
		String query = " UPDATE pesquisador SET nome = ?, cpf = ?, matricula = ?, WHERE idPesquisador = ? ";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, query);
		
		try {
			stmt.setString(1, pesquisador.getNome());
			stmt.setString(2, pesquisador.getCpf());
			stmt.setString(4, pesquisador.getMatricula());
						
			int linhasAfetadas = stmt.executeUpdate();
			atualizou = linhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar pesquisador.\nCausa: " + e.getMessage());
		}finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}
		
		return atualizou;
	}
	
	public boolean excluir(int id) {
		boolean excluiu = false;
		Connection conexao = Banco.getConnection();
		String query = " DELETE FROM pesquisador WHERE idPesquisador = ? ";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, query);
		try {
			stmt.setInt(1, id);
			int registrosExcluidos = stmt.executeUpdate();
			excluiu = (registrosExcluidos > 0);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir pesquisador.\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}
		
		return excluiu;
	}
}
