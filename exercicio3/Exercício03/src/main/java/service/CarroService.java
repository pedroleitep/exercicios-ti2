package service;

import dao.CarroDAO;
import model.Carro;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.List;

public class CarroService {
    private CarroDAO carroDAO;
    private Gson gson = new Gson();

    public CarroService(CarroDAO carroDAO) {
        this.carroDAO = carroDAO;
    }

    public Object add(Request request, Response response) {
        try {
            Carro carro = gson.fromJson(request.body(), Carro.class);
            carroDAO.inserir(carro);
            response.status(201);
            return gson.toJson(carro);
        } catch (SQLException e) {
            response.status(500);
            return "Erro ao inserir carro: " + e.getMessage();
        }
    }

    public Object get(Request request, Response response) {
        try {
            int id = Integer.parseInt(request.params(":id"));
            List<Carro> carros = carroDAO.listarTodos();
            return gson.toJson(carros.stream().filter(c -> c.getId() == id).findFirst().orElse(null));
        } catch (Exception e) {
            response.status(500);
            return "Erro ao buscar carro: " + e.getMessage();
        }
    }

    public Object getAll(Request request, Response response) {
        try {
            List<Carro> carros = carroDAO.listarTodos();
            return gson.toJson(carros);
        } catch (SQLException e) {
            response.status(500);
            return "Erro ao buscar carros: " + e.getMessage();
        }
    }

    public Object update(Request request, Response response) {
        response.status(501); // Não implementado ainda
        return "Update ainda não implementado!";
    }

    public Object remove(Request request, Response response) {
        response.status(501); // Não implementado ainda
        return "Remoção ainda não implementada!";
    }
}
