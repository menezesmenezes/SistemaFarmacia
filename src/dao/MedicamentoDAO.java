package dao;

import db.Conexao;
import model.Medicamento;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MedicamentoDAO {

    public void inserir(Medicamento m) {


        String sql = "INSERT INTO medicamentos (nome, quantidade, validade) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, m.getNome());
            stmt.setInt(2, m.getQuantidade());
            stmt.setDate(3, m.getValidade());

            stmt.executeUpdate();

            System.out.println("Salvo no banco!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean remover(String nome) {

        String sql = "DELETE FROM medicamentos WHERE nome = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Medicamento removido do banco!");
                return true;
            } else {
                System.out.println("Medicamento não encontrado!");
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public void listar() {

        String sql = "SELECT * FROM medicamentos";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n=== LISTA DE MEDICAMENTOS ===");

            while (rs.next()) {
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Quantidade: " + rs.getInt("quantidade"));
                System.out.println("Validade: " + rs.getDate("validade"));
                System.out.println("------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
