package br.com.danilo.park.service;

import br.com.danilo.park.database.RepositoryController;
import br.com.danilo.park.domain.Controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ServiceController {

    private final RepositoryController repository;

    public ServiceController() {
        repository = new RepositoryController();
    }

    public List<Controller> getAll() throws SQLException {
        return repository.getAll();
    }

    public void in(String license, String model) throws SQLException {
        // preciso verficiar se existe um in em andamento
        repository.save(new Controller(
                0,
                license.toUpperCase(),
                model.toUpperCase(),
                LocalDateTime.now(),
                null
        ));
    }

    public double out(String license) throws SQLException {
        //
        var dateIn = LocalDateTime.now().minusHours(3);
        var dateOut = LocalDateTime.now();
        var totalHours = dateIn.until(dateOut, ChronoUnit.HOURS) * 10;
        repository.update(license.toUpperCase(), Timestamp.valueOf(dateOut));
        return totalHours;
    }


}
