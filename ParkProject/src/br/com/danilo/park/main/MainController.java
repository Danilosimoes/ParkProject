package br.com.danilo.park.main;

import br.com.danilo.park.domain.Controller;
import br.com.danilo.park.service.ServiceController;
import br.com.danilo.park.view.ViewController;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MainController {
    public static void main(String[] args) throws SQLException {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ViewController vc = new ViewController();
                vc.setVisible(true);
            }
        });
    }

}


/*ServiceController sc = new ServiceController();

        sc.controllerIn(new Controller(
                0,
                "XTPO6666",
                "FUSCA",
                LocalDateTime.now(),
                LocalDateTime.now()
        ));

        sc.getAll().forEach(controller -> {
            System.out.println(controller.getDateIn());
        });*/