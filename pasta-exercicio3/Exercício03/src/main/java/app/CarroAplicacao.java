package app;

import static spark.Spark.*;

import service.CarroService;
import dao.CarroDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CarroAplicacao {
    private static CarroService carroService;

    public static void main(String[] args) {
        port(6789);

        Connection conn = conectarBanco();
        if (conn != null) {
            carroService = new CarroService(new CarroDAO(conn));

            post("/carro", (request, response) -> carroService.add(request, response));
            get("/carro/:id", (request, response) -> carroService.get(request, response));
            get("/carro/update/:id", (request, response) -> carroService.update(request, response));
            get("/carro/delete/:id", (request, response) -> carroService.remove(request, response));
            get("/carro", (request, response) -> carroService.getAll(request, response));
        }
    }

    private static Connection conectarBanco() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/seu_banco", "seu_usuario", "sua_senha");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco: " + e.getMessage());
            return null;
        }
    }
}
