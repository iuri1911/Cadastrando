/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Pessoa;

/**
 *
 * @author theoz
 */
public class PessoaDAO {

    public void create(Pessoa p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO pessoa (nome,endereco,telefone,cpf)VALUES(?,?,?,?)");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getEndereco());
            stmt.setString(3, p.getTelefone());
            stmt.setString(4, p.getCpf());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar! :" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Pessoa> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Pessoa> pessoas = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM pessoa ");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Pessoa pessoa = new Pessoa();

                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("Nome"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoas.add(pessoa);

            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return pessoas;
    }
    public void update(Pessoa p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE pessoa SET nome = ?,endereco = ?,telefone = ?,cpf = ? WHERE id =?");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getEndereco());
            stmt.setString(3, p.getTelefone());
            stmt.setString(4, p.getCpf());
            stmt.setInt(5, p.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar! :" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public void delete(Pessoa p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM pessoa WHERE id =?");
            stmt.setInt(1, p.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Deletar! :" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

}
