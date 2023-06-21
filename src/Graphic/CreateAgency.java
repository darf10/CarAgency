package Graphic;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

import System.Agency;
import Vehicles.PlayingGlider;

public class CreateAgency extends JFrame{
    private Agency agency = new Agency();
    public CreateAgency() {
        setTitle("Create Agency");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JLabel label = new JLabel("<html><h1><strong><i>Choose type of vehicle to add:</i></strong></h1><hr></html>");
        label.setHorizontalAlignment(JLabel.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton jeep_button = new JButton("Jeep");
        jeep_button.setFocusable(false);
        JButton frigate_button = new JButton("Frigate");
        frigate_button.setFocusable(false);
        JButton spyingGlider_button = new JButton("Spying Glider");
        spyingGlider_button.setFocusable(false);
        JButton playingGlider_button = new JButton("Playing Glider");
        playingGlider_button.setFocusable(false);
        JButton amphibious_button = new JButton("Amphibious");
        amphibious_button.setFocusable(false);
        JButton bicycle_button = new JButton("Bicycle");
        bicycle_button.setFocusable(false);
        JButton cruiseShip_button = new JButton("Cruise Ship");
        cruiseShip_button.setFocusable(false);
        JButton hybridAirplane_button = new JButton("Hybrid Airplane");
        hybridAirplane_button.setFocusable(false);
        JButton electricBicycle_button = new JButton("Electric Bicycle");
        electricBicycle_button.setFocusable(false);
        buttonPanel.add(bicycle_button);
        buttonPanel.add(jeep_button);
        buttonPanel.add(playingGlider_button);
        buttonPanel.add(spyingGlider_button);
        buttonPanel.add(frigate_button);
        buttonPanel.add(cruiseShip_button);
        buttonPanel.add(amphibious_button);
        buttonPanel.add(hybridAirplane_button);
        buttonPanel.add(electricBicycle_button);
        JPanel bottomPanel = new JPanel();
        JButton submit_button = new JButton("Submit");
        submit_button.setFocusable(false);
        bottomPanel.add(submit_button);
        add(label, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        jeep_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateVehicle(agency, 1);
            }
        });

        frigate_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateVehicle(agency, 2);
            }
        });

        spyingGlider_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateVehicle(agency, 3);
            }
        });

        playingGlider_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateVehicle(agency, 4);
            }
        });

        amphibious_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateVehicle(agency, 5);
            }
        });

        bicycle_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateVehicle(agency, 6);
            }
        });

        cruiseShip_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateVehicle(agency,7 );
            }
        });
        hybridAirplane_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { new CreateVehicle(agency,8 );}
        });
        electricBicycle_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { new CreateVehicle(agency,9);}
        });
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Updating database... Please wait");
                submit_button.setVisible(false);
                Executors.newSingleThreadScheduledExecutor().schedule(() -> {
                    JOptionPane.showMessageDialog(null, "Vehicles added successfully");
                    dispose();
                    CreateVehicle.setState();
                    MainMenu.getInstance(agency);
                }, ThreadLocalRandom.current().nextInt(3, 9), TimeUnit.SECONDS);
            }
        });
        setVisible(true);
    }
}