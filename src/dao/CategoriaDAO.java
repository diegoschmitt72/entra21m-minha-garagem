package dao;

import database.Conexao;
import database.Utilitarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Categoria;

/**
 *
 * @author Alunos
 */
public class CategoriaDAO {

    public int inserir(Categoria categoria) {
        int codigoInserido = Utilitarios.NAO_FOI_POSSIVEL_INSERIR;
        String sql = "INSERT INTO categorias (nome, descricao, ativo)";
        sql += "\nVALUE (?,?,?)";
        try {
            /*
            * Classe Utilizada para criar o sql substituido a interrogação
            * RETURN_GENERATED_KEYS -> utilizado patra informar o PreparedStatement que deve ser 
            * retornado o Id gerado pelo banco de dado
            */
            
            PreparedStatement ps = Conexao.conectar().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            // substitui as interrogações
            ps.setString(1, categoria.getNome());
            ps.setString(2, categoria.getDescricao());
            ps.setBoolean(3, categoria.isAtivo());
            // executa o comando no Banco de Dados
            ps.execute();
            
            /*
            * classe utilizada para trabalhar com as informações que o banco de dados retorna
            */
            
            
            ResultSet resultado = ps.getGeneratedKeys();
            if (resultado.next()) {
                codigoInserido = resultado.getInt(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao inserir a CategoriaDAO", JOptionPane.ERROR_MESSAGE);
        }finally{
            Conexao.desconectar();
        }
        return codigoInserido;
    }

    public int alterar(Categoria categoria) {
        int codigoAlterado = Utilitarios.NAO_FOI_POSSIVEL_ALTERAR;
        String sql = "UPDATE categorias SET";
        sql += "\nnome = ?,";
        sql += "\ndescricao = ?,";
        sql += "\nativo = ?";
        sql += "\n WHERE id = ?";
        try{
            PreparedStatement ps = Conexao.conectar().prepareStatement(sql);
            ps.setString(1, categoria.getNome());
            ps.setString(2, categoria.getDescricao());
            ps.setBoolean(3, categoria.isAtivo());
            ps.setInt(4, categoria.getId());
            codigoAlterado = ps.executeUpdate();
        }catch (SQLException sqle){
            JOptionPane.showMessageDialog(null, sqle.getMessage(), "Erro ao alterar CategoriaDAO",JOptionPane.ERROR_MESSAGE );
        }finally{
            Conexao.desconectar();
        }
        
        return codigoAlterado;
    }

    public int excluir(int id) {
        int codigoExcluido = Utilitarios.NAO_FOI_POSSIVEL_EXCLUIR;
        String sql = "DELETE FROM categorias WHERE id = ?";
        try{
            PreparedStatement ps = Conexao.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            codigoExcluido = ps.executeUpdate();
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, sqle.getMessage(),"Erro ao excluir a CategoriaDAO", JOptionPane.ERROR_MESSAGE);
        }finally{
            Conexao.desconectar();
        }
        return codigoExcluido;
    }

    public ArrayList<Categoria> retornarListagemCategorias() {
        ArrayList<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT id, nome, descricao, ativo FROM categorias";
        try{
            Statement stmt = Conexao.conectar().createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {                
                Categoria c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));
                c.setAtivo(rs.getBoolean("ativo"));
                categorias.add(c);
            }
        }catch (SQLException sqle){
            JOptionPane.showMessageDialog(null, sqle.getMessage(), "Erro ao alterar CategoriaDAO",JOptionPane.ERROR_MESSAGE );
        }finally{
            Conexao.desconectar();
        }
        return categorias;
    }

    public Categoria buscarCategoriaPorId(int codigo) {
        Categoria categoria = null;
        String sql = "SELECT nome, descricao, ativo FROM categorias";
        sql += "\nWHERE id = ?";
        try{
            PreparedStatement ps = Conexao.conectar().prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.execute();
            ResultSet resultado = ps.getResultSet();
            if (resultado.next()) {
                categoria = new Categoria();
                categoria.setId(codigo);
                categoria.setNome(resultado.getString("nome"));
                categoria.setDescricao(resultado.getString("Descricao"));
                categoria.setAtivo(resultado.getBoolean("ativo"));
               
            }
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, sqle.getMessage(),"Não foi possivel retornar categoria de CategoriaDAO", JOptionPane.ERROR_MESSAGE);
        }finally{
            Conexao.desconectar();
        }
        return categoria;
    }

    public Categoria buscarCategoriaPorNome(String nome) {
        Categoria categoria = null;
        return categoria;
    }

    public ArrayList<Categoria> buscarCategoriaPorStatus(boolean status) {
        ArrayList<Categoria> categorias = new ArrayList<>();
        return categorias;
    }
}
