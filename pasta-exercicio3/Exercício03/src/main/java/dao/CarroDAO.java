package dao;

import model.Carro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO {
    private Connection conn;

    public CarroDAO(Connection conn) {
        this.conn = conn;
    }

    public void inserir(Carro carro) throws SQLException {
        String sql = "INSERT INTO carro (modelo, marca, ano, preco) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, carro.getModelo());
            stmt.setString(2, carro.getMarca());
            stmt.setInt(3, carro.getAno());
            stmt.setDouble(4, carro.getPreco());
            stmt.executeUpdate();
        }
    }

    public List<Carro> listarTodos() throws SQLException {
        List<Carro> carros = new ArrayList<>();
        String sql = "SELECT * FROM carro";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Carro carro = new Carro(
                    rs.getInt("id"),
                    rs.getString("modelo"),
                    rs.getString("marca"),
                    rs.getInt("ano"),
                    rs.getDouble("preco")
                );
                carros.add(carro);
            }
        }
        return carros;
    }
}
