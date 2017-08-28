
package dao;

import database.Conexao;
import database.Utilitarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Avioes;
import model.Categoria;

/**
 *
 * @author Alunos
 */
public class AvioesDAO {
    
    public ArrayList<Avioes> retornarListaAviao(){
        ArrayList<Avioes> avioes = new ArrayList<>();
        String sql = "SELECT id, id_categoria, nome FROM avioes";
        try {
            Statement st = Conexao.conectar().createStatement();
            st.execute(sql);
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Avioes aviao = new Avioes();
                aviao.setCodigo(rs.getInt("id"));
                
                int codigoCategoria = rs.getInt("id_categoria");
                Categoria categoria = new CategoriaDAO().buscarCategoriaPorId(codigoCategoria);
                aviao.setCategoria(categoria);
                
                aviao.setNome(rs.getString("nome"));
                avioes.add(aviao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            Conexao.desconectar();
        }
        return avioes;
    }

    public int inserir(Avioes aviao) {
        String sql = "INSERT INTO avioes (id_categoria, nome) VALUES(?,?);";
        int codigoInserido = Utilitarios.NAO_FOI_POSSIVEL_INSERIR;
        try {
            PreparedStatement ps = Conexao.conectar().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, aviao.getCategoria().getId());
            ps.setString(2, aviao.getNome());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                codigoInserido = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            Conexao.desconectar();
        }
        
        
        return codigoInserido;
    }
    
    public int alterar(Avioes aviao){
        String sql = "UPDATE avioes SET id_categoria = ?, nome = ?";
        sql += "\nWHERE id = ?";
        int codigoAlterado = Utilitarios.NAO_FOI_POSSIVEL_ALTERAR;
        try {
            PreparedStatement ps = Conexao.conectar().prepareStatement(sql);
            ps.setInt(1, aviao.getCategoria().getId());
            ps.setString(2, aviao.getNome());
            ps.setInt(3, aviao.getCodigo());
            codigoAlterado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            Conexao.desconectar();
        }
        
        return codigoAlterado;
    }
    
    public boolean exlcuir (int id){
        String sql = "DELETE FROM avioes WHERE id = ?";
        try {
            PreparedStatement ps = Conexao.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            if (buscarAviaoPorId(id) == null) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            Conexao.desconectar();
        }
        return false;
    }
    
        public Avioes buscarAviaoPorId(int id){
            Avioes aviao = null;
            String sql = "SELECT id, id_categoria, nome FROM avioes";
            sql += "\nWHERE id = ?";
            try {
                PreparedStatement ps = Conexao.conectar().prepareStatement(sql);
                ps.setInt(1, id);
                ps.execute();
                ResultSet rs = ps.getResultSet();
                if (rs.next()) {
                    aviao = new Avioes();
                    aviao.setCodigo(rs.getInt("id"));
                    aviao.setCategoria(new CategoriaDAO().buscarCategoriaPorId(rs.getInt("id_categoria")));
                    aviao.setNome(rs.getString("nome"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                Conexao.desconectar();
            }
            return aviao;
        }
        
    
}
