package DAO;

import DTO.Responsavel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.br.cmpcd.dao.util.Conexao;

public class ResponsavelDAO {
    private Connection conn;

    private void Conectar() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = Conexao.getConexao();
        }
    }

    private void Desconectar() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    // Método para inserir um responsável no banco de dados
    public void inserirResponsavel(Responsavel responsavel) throws SQLException {
        String sql = "INSERT INTO Usuarios_Pcd_Responsavel (codigo_usuario, nomeCompleto, telefone, email, endereco) VALUES (?, ?, ?, ?, ?)";
        Conectar();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, responsavel.getCodigoUsuario());
            stmt.setString(2, responsavel.getNomeCompleto());
            stmt.setString(3, responsavel.getTelefone());
            stmt.setString(4, responsavel.getEmail());
            stmt.setString(5, responsavel.getEndereco());

            stmt.executeUpdate();
            Desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar um responsável pelo código de usuário
    public Responsavel buscarResponsavelPorCodigoUsuario(int codigoUsuario) throws SQLException {
        String sql = "SELECT * FROM Usuarios_Pcd_Responsavel WHERE codigo_usuario = ?";
        Responsavel responsavel = null;
        Conectar();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codigoUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                responsavel = new Responsavel(
                        rs.getInt("id"),
                        rs.getInt("codigo_usuario"),
                        rs.getString("nomeCompleto"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("endereco"));
            }
            Desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return responsavel;
    }

    // Método para atualizar os dados de um responsável
    public void atualizarResponsavel(Responsavel responsavel) throws SQLException {
        System.out.println(responsavel.getEmail() + responsavel.getNomeCompleto() + responsavel.getEndereco()
                + responsavel.getCodigoUsuario());
        String sql = "UPDATE Usuarios_Pcd_Responsavel SET nomeCompleto = ?, telefone = ?, email = ?, endereco = ? WHERE codigo_usuario = ?";
        Conectar();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, responsavel.getNomeCompleto());
            stmt.setString(2, responsavel.getTelefone());
            stmt.setString(3, responsavel.getEmail());
            stmt.setString(4, responsavel.getEndereco());
            stmt.setInt(5, responsavel.getCodigoUsuario());

            stmt.executeUpdate();
            Desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
