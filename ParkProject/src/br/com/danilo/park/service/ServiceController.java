package br.com.danilo.park.service;

import br.com.danilo.park.database.RepositoryController;
import br.com.danilo.park.domain.Controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
//Onde está toda a lógica

public class ServiceController {

    private final RepositoryController repository;

    public ServiceController() {
        repository = new RepositoryController();
    }

    // método para buscar todos os registros
    public List<Controller> getAll() throws SQLException {
        return repository.getAll();
    }

    //Método para inserir uma entrada
    public void in(String license, String model) throws SQLException, ControllerException {
        //Encontrando a placa
        var controller= repository.find(license);

        //Verificando se há alguma entrada sem saída
        if( controller != null){
            throw new ControllerException("out exist");
        }


        repository.save(new Controller(
                0,
                license.toUpperCase(),
                model.toUpperCase(),
                LocalDateTime.now(),
                null,
                0
        ));
    }

    //Método para realizar uma saída
    public double out(String license) throws SQLException {
        //Buscando pelas datas com o find
        var controller = repository.find(license);
        var dateIn = controller.getDateIn();
        var dateOut = LocalDateTime.now();
        //Fazendo a soma de entrada e saída
        var totalHours = dateIn.until(dateOut, ChronoUnit.HOURS) * 10;
        repository.update(license.toUpperCase(), Timestamp.valueOf(dateOut), totalHours);
        return totalHours;
    }

    //Método para realizar a busca por datas
    public List<Controller> byDate(String before, String after) throws SQLException {
        return repository.findAll(before, after);
    }



}
