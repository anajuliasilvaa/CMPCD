package DAO;

import DTO.Usuario_PcdDeficiencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.br.cmpcd.dao.util.Conexao;

public class Usuario_PcdDeficienciaDAO {
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

    public void inserirDeficiencia(Usuario_PcdDeficiencia deficiencia) throws SQLException {
        String sql = "INSERT INTO Usuarios_Pcd_Deficiencia (codigo_usuario, tipoDeficiencia, necessidadeAcompanhante, necessidadeEquipamento, explicacao_necessidade_equipamento, necessidadeAdaptacao, explicacao_necessidade_adaptacao, necessidadeAdaptacaoLocalAtendimento, explicacao_necessidade_adaptacao_local_atendimento, necessidadeEducacional, explicacao_necessidade_Educacional) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Conectar();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, deficiencia.getCodigoUsuario());
            stmt.setString(2, deficiencia.getTipoDeficiencia());
            stmt.setBoolean(3, deficiencia.isNecessidadeAcompanhante());
            stmt.setBoolean(4, deficiencia.isNecessidadeEquipamento());
            stmt.setString(5, deficiencia.getExplicacaoNecessidadeEquipamento());
            stmt.setBoolean(6, deficiencia.isNecessidadeAdaptacao());
            stmt.setString(7, deficiencia.getExplicacaoNecessidadeAdaptacao());
            stmt.setBoolean(8, deficiencia.isNecessidadeAdaptacaoLocalAtendimento());
            stmt.setString(9, deficiencia.getExplicacaoNecessidadeAdaptacaoLocalAtendimento());
            stmt.setBoolean(10, deficiencia.getNecessidadeEducacional());
            stmt.setString(11, deficiencia.getExplicacaonecessidadeEducacional());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Desconectar();

        }
    }

    public Usuario_PcdDeficiencia buscarDeficienciaPorCodigoUsuario(int codigoUsuario) throws SQLException {
        String sql = "SELECT * FROM Usuarios_Pcd_Deficiencia WHERE codigo_usuario = ?";
        Usuario_PcdDeficiencia deficiencia = null;
        Conectar();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigoUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                deficiencia = new Usuario_PcdDeficiencia(
                        rs.getInt("codigo_usuario"),
                        rs.getString("tipoDeficiencia"),
                        rs.getBoolean("necessidadeAcompanhante"),
                        rs.getBoolean("necessidadeEquipamento"),
                        rs.getString("explicacao_necessidade_equipamento"),
                        rs.getBoolean("necessidadeAdaptacao"),
                        rs.getString("explicacao_necessidade_adaptacao"),
                        rs.getBoolean("necessidadeAdaptacaoLocalAtendimento"),
                        rs.getString("explicacao_necessidade_adaptacao_local_atendimento"),
                        rs.getBoolean("necessidadeEducacional"),
                        rs.getString("explicacao_necessidade_Educacional"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Desconectar();

        }
        return deficiencia;
    }

    public void atualizarDeficiencia(Usuario_PcdDeficiencia deficiencia) throws SQLException {
        String sql = "UPDATE Usuarios_Pcd_Deficiencia SET tipoDeficiencia = ?, necessidadeAcompanhante = ?, necessidadeEquipamento = ?, explicacao_necessidade_equipamento = ?, necessidadeAdaptacao = ?, explicacao_necessidade_adaptacao = ?, necessidadeAdaptacaoLocalAtendimento = ?, explicacao_necessidade_adaptacao_local_atendimento = ?, necessidadeEducacional = ?, explicacao_necessidade_Educacional = ? WHERE codigo_usuario = ?";
        Conectar();
        try {
            conn.setAutoCommit(false);

            String checkUserExistSql = "SELECT COUNT(*) FROM Usuarios_Pcd WHERE codigo = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkUserExistSql)) {
                checkStmt.setInt(1, deficiencia.getCodigoUsuario());
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    throw new SQLException("Usuário não encontrado.");
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, deficiencia.getTipoDeficiencia());
                stmt.setBoolean(2, deficiencia.isNecessidadeAcompanhante());
                stmt.setBoolean(3, deficiencia.isNecessidadeEquipamento());
                stmt.setString(4, deficiencia.getExplicacaoNecessidadeEquipamento());
                stmt.setBoolean(5, deficiencia.isNecessidadeAdaptacao());
                stmt.setString(6, deficiencia.getExplicacaoNecessidadeAdaptacao());
                stmt.setBoolean(7, deficiencia.isNecessidadeAdaptacaoLocalAtendimento());
                stmt.setString(8, deficiencia.getExplicacaoNecessidadeAdaptacaoLocalAtendimento());
                stmt.setBoolean(9, deficiencia.getNecessidadeEducacional());
                stmt.setString(10, deficiencia.getExplicacaonecessidadeEducacional());
                stmt.setInt(11, deficiencia.getCodigoUsuario());

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    conn.commit();
                } else {
                    throw new SQLException("Nenhuma linha afetada.");
                }
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
                Desconectar();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
