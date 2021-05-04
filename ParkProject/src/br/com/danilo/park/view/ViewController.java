package br.com.danilo.park.view;

import br.com.danilo.park.service.ControllerException;
import br.com.danilo.park.service.ServiceController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import static java.lang.String.valueOf;

public class ViewController extends JFrame {
    private JTextField textLicense;
    private JTextField textModel;
    private JButton btnIn;
    private JButton btnOut;
    private JPanel panel;
    private JTextField textTotal;
    private JLabel labelError;
    private JTextField textBefore;
    private JTextField textAfter;
    private JButton btnSearch;

    private final ServiceController serviceController;

    public ViewController(){
        serviceController = new ServiceController();
        add(panel);
        setSize(400, 200);
        setTitle("Park Control");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        btnIn.addActionListener(new ActionListener() {
            //Realizando a entrada dos carros
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    try {
                        serviceController.in(
                                textLicense.getText(),
                                textModel.getText()
                        );
                    } catch (ControllerException ex) {
                        labelError.setText(ex.getMessage());
                    }
                    // Busca por um novo registro e imprimindo todos

                    serviceController.getAll().forEach(controller -> {
                        System.out.println(controller.getDateIn());
                    });

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


        btnOut.addActionListener(new ActionListener() {
            // Realizando a saída dos carros
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    var total = serviceController.out(textLicense.getText());
                    textTotal.setText(String.valueOf(total));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            //Realizando a busca por períodos
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    serviceController.byDate(
                            textBefore.getText(),
                            textAfter.getText()).forEach(controller -> {
                                //Imprimindo a placa e a data das saídas

                                System.out.println("Licence: " + controller.getLicense()+ " | "
                                        + " Date Time: " + controller.getDateIn());
                });

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

    }


}




