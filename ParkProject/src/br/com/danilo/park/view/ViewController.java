package br.com.danilo.park.view;

import br.com.danilo.park.service.ServiceController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class ViewController extends JFrame {
    private JTextField textLicense;
    private JTextField textModel;
    private JButton btnIn;
    private JButton btnOut;
    private JPanel panel;

    private final ServiceController serviceController;

    public ViewController() {
        serviceController = new ServiceController();
        add(panel);
        setSize(400, 200);
        setTitle("Park Control");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        btnIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    serviceController.in(
                            textLicense.getText(),
                            textModel.getText()
                    );
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                try {
                    serviceController.getAll().forEach(controller -> {
                        System.out.println(controller.getDateIn());
                    });
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


        btnOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   var total =  serviceController.out(textLicense.getText());
                   System.out.println(total);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

}




