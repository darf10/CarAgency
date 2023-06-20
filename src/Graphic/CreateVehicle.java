package Graphic;
import System.Agency;
import Vehicles.ElectricBicycle;
import Vehicles.HybridAirplane;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.concurrent.*;

public class CreateVehicle extends JFrame {
    private JPanel main_panel;
    private JPanel panel;
    private PhotoPanel imagePanel;
    private JTextField modelField, passengersField, max_speedField, wheel_countField, fuel_consumptionField, avg_life_spanField, power_sourceField , colorField;
    private JComboBox<String> road_typeField, sail_with_windField;
    private JComboBox<ImageIcon> flagField;
    private final JButton addButton;
    private final JButton cancelButton;
    private static int state;
    private MainMenu mainMenu;
    public CreateVehicle(Agency agency, int choice){
        state = 0;
        setTitle("New Vehicle:");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        main_panel = new JPanel(new BorderLayout());
        panel = new JPanel(new GridLayout(10,2));
        panel.setBorder(new EmptyBorder(3,5,3,5));
        imagePanel = new PhotoPanel();
        main_panel.add(panel,BorderLayout.NORTH);
        main_panel.add(imagePanel,BorderLayout.CENTER);
        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");
        add(main_panel);
        switch (choice) {
            case 1 ->
                    Jeep(agency);
            case 2 ->
                    Frigate(agency);
            case 3 ->
                    SpyingGlider(agency);
            case 4 ->
                    PlayingGlider(agency);
            case 5 ->
                    Amphibious(agency);
            case 6 ->
                    Bicycle(agency);
            case 7 ->
                    CruiseShip(agency);
            case 8 ->
                    HybridAirplane(agency);
            case 9 ->
                    ElectricBicycle(agency);
        }
    }
    public CreateVehicle(Agency agency, int choice, MainMenu parent){
        mainMenu = parent;
        setTitle("New Vehicle:");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        main_panel = new JPanel(new BorderLayout());
        panel = new JPanel(new GridLayout(10,2));
        panel.setBorder(new EmptyBorder(3,5,3,5));
        imagePanel = new PhotoPanel();
        main_panel.add(panel,BorderLayout.NORTH);
        main_panel.add(imagePanel,BorderLayout.CENTER);
        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");
        add(main_panel);
        switch (choice) {
            case 1 ->
                    Jeep(agency);
            case 2 ->
                    Frigate(agency);
            case 3 ->
                    SpyingGlider(agency);
            case 4 ->
                    PlayingGlider(agency);
            case 5 ->
                    Amphibious(agency);
            case 6 ->
                    Bicycle(agency);
            case 7 ->
                    CruiseShip(agency);
            case 8 ->
                    HybridAirplane(agency);
            case 9 ->
                    ElectricBicycle(agency);
        }
    }

    public Image SetFlagsSize(ImageIcon flag){
        Image fixedFlag = flag.getImage();
        return fixedFlag.getScaledInstance(20,20,Image.SCALE_SMOOTH);
    }
    public void SystemButtons(JPanel p){
        p.add(addButton);
        p.add(cancelButton);
    }

    public void Jeep(Agency agency){
        setTitle("Jeep");
        panel.add(new JLabel("Model:"));
        modelField = new JTextField();
        panel.add(modelField);

        panel.add(new JLabel("Color"));
        String[] useTypes = {"White", "Black", "Red", "Blue", "Green", "Yellow"};
        JComboBox<String> colorField = new JComboBox<>(useTypes);
        panel.add(colorField);

        panel.add(new JLabel("Max speed:"));
        max_speedField = new JTextField();
        panel.add(max_speedField);
        panel.add(new JLabel("Fuel consumption:"));
        fuel_consumptionField = new JTextField();
        panel.add(fuel_consumptionField);
        panel.add(new JLabel("Average life span:"));
        avg_life_spanField = new JTextField();
        panel.add(avg_life_spanField);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        SystemButtons(buttonPanel);
        add(buttonPanel,BorderLayout.SOUTH);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String model = modelField.getText();
                String color = (String)colorField.getSelectedItem();
                double max_speed = Double.parseDouble(max_speedField.getText());
                double fuel_consumption = Double.parseDouble(fuel_consumptionField.getText());
                double avg_life_span = Double.parseDouble(avg_life_spanField.getText());
                synchronized (agency) {
                    agency.BuildJeep(model, max_speed, imagePanel.getSelectedImage(), fuel_consumption, avg_life_span, color);
                }
                if(state == 1){
                    updateDataBase();
                }
                dispose();

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void Frigate(Agency agency){
        setTitle("Frigate");

        panel.add(new JLabel("Model:"));
        modelField = new JTextField();
        panel.add(modelField);

        panel.add(new JLabel("Color"));
        String[] useTypes = {"White", "Black", "Red", "Blue", "Green", "Yellow"};
        JComboBox<String> colorField = new JComboBox<>(useTypes);
        panel.add(colorField);

        panel.add(new JLabel("Max speed:"));
        max_speedField = new JTextField();
        panel.add(max_speedField);

        panel.add(new JLabel("Passengers:"));
        passengersField = new JTextField();
        panel.add(passengersField);

        panel.add(new JLabel("Sail with the wind?:"));
        String[] options = {"Yes", "No"};
        sail_with_windField = new JComboBox<>(options);
        panel.add(sail_with_windField);

        panel.add(new JLabel("Flag:"));
        String[] flagString = {"Israel", "USA", "Germany", "Italy", "Somalia", "Pirate"};
        ImageIcon[] flagImages = {
                new ImageIcon("Israel.png"),
                new ImageIcon("USA.png"),
                new ImageIcon("Germany.png"),
                new ImageIcon("Italy.png"),
                new ImageIcon("Somalia.png"),
                new ImageIcon("Pirate.png")
        };
        ImageIcon[] flags = new ImageIcon[flagImages.length];
        for (int i=0; i<flagImages.length;i++)
            flags[i] = new ImageIcon(SetFlagsSize(flagImages[i]));
        flagField = new JComboBox<>(flags);
        flagField.setPreferredSize(new Dimension(25,25));
        panel.add(flagField, BorderLayout.SOUTH);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        SystemButtons(buttonPanel);
        add(buttonPanel,BorderLayout.SOUTH);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String model = modelField.getText();
                String color = (String)colorField.getSelectedItem();
                double max_speed = Double.parseDouble(max_speedField.getText());
                int passengers = Integer.parseInt(passengersField.getText());
                boolean sail_with_wind = Objects.equals(sail_with_windField.getSelectedItem(), "Yes");
                int flagIndex = flagField.getSelectedIndex();
                synchronized (agency) {
                    agency.BuildFrigate(model, passengers, max_speed, imagePanel.getSelectedImage(), sail_with_wind, flagString[flagIndex],color);
                }
                if(state == 1){
                    updateDataBase();
                }
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the form
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void SpyingGlider(Agency agency){
        setTitle("Spying Glider");

        panel.add(new JLabel("Color"));
        String[] useTypes = {"White", "Black", "Red", "Blue", "Green", "Yellow"};
        JComboBox<String> colorField = new JComboBox<>(useTypes);
        panel.add(colorField);

        panel.add(new JLabel("Power source:"));
        power_sourceField = new JTextField();
        panel.add(power_sourceField);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        SystemButtons(buttonPanel);
        add(buttonPanel,BorderLayout.SOUTH);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String color = (String)colorField.getSelectedItem();
                String power_source = power_sourceField.getText();
                agency.BuildSpyingGlider(imagePanel.getSelectedImage(),power_source,color);
                if(state == 1){
                    updateDataBase();
                }
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void PlayingGlider(Agency agency){
        setTitle("Playing Glider");

        panel.add(new JLabel("Color"));
        String[] useTypes = {"White", "Black", "Red", "Blue", "Green", "Yellow"};
        JComboBox<String> colorField = new JComboBox<>(useTypes);
        panel.add(colorField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        SystemButtons(buttonPanel);
        add(buttonPanel,BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                synchronized (agency) {
                    String color = (String)colorField.getSelectedItem();
                    agency.BuildPlayingGlider(imagePanel.getSelectedImage(),color);
                }
                if(state == 1){
                    updateDataBase();
                }
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void Amphibious(Agency agency){
        setTitle("Amphibious");
        panel.add(new JLabel("Model:"));
        modelField = new JTextField();
        panel.add(modelField);

        panel.add(new JLabel("Color"));
        String[] useTypes = {"White", "Black", "Red", "Blue", "Green", "Yellow"};
        JComboBox<String> colorField = new JComboBox<>(useTypes);
        panel.add(colorField);

        panel.add(new JLabel("Max speed:"));
        max_speedField = new JTextField();
        panel.add(max_speedField);

        panel.add(new JLabel("Passengers:"));
        passengersField = new JTextField();
        panel.add(passengersField);

        panel.add(new JLabel("Wheel count:"));
        wheel_countField = new JTextField();
        panel.add(wheel_countField);

        panel.add(new JLabel("Fuel consumption:"));
        fuel_consumptionField = new JTextField();
        panel.add(fuel_consumptionField);

        panel.add(new JLabel("Average life span:"));
        avg_life_spanField = new JTextField();
        panel.add(avg_life_spanField);

        panel.add(new JLabel("Sail with the wind?:"));
        String[] options = {"Yes", "No"};
        sail_with_windField = new JComboBox<>(options);
        panel.add(sail_with_windField);

        panel.add(new JLabel("Flag:"));
        String[] flagString = {"Israel", "USA", "Germany", "Italy", "Somalia", "Pirate"};
        ImageIcon[] flagImages = {
                new ImageIcon("Israel.png"),
                new ImageIcon("USA.png"),
                new ImageIcon("Germany.png"),
                new ImageIcon("Italy.png"),
                new ImageIcon("Somalia.png"),
                new ImageIcon("Pirate.png")
        };
        ImageIcon[] flags = new ImageIcon[flagImages.length];
        for (int i=0; i<flagImages.length;i++)
            flags[i] = new ImageIcon(SetFlagsSize(flagImages[i]));
        flagField = new JComboBox<>(flags);
        flagField.setPreferredSize(new Dimension(25,25));
        panel.add(flagField, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        SystemButtons(buttonPanel);
        add(buttonPanel,BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String model = modelField.getText();
                String color = (String)colorField.getSelectedItem();
                double max_speed = Double.parseDouble(max_speedField.getText());
                int passengers = Integer.parseInt(passengersField.getText());
                int wheel_count = Integer.parseInt(wheel_countField.getText());
                double fuel_consumption = Double.parseDouble(fuel_consumptionField.getText());
                double avg_life_span = Double.parseDouble(avg_life_spanField.getText());
                boolean sail_with_wind = Objects.equals(sail_with_windField.getSelectedItem(), "Yes");
                int flagIndex = flagField.getSelectedIndex();
                synchronized (agency) {
                    agency.BuildAmphibious(model, passengers, max_speed, imagePanel.getSelectedImage(), sail_with_wind, wheel_count, fuel_consumption, avg_life_span, flagString[flagIndex],color);
                }
                if(state == 1){
                    updateDataBase();
                }
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void Bicycle(Agency agency){
        setTitle("Bicycle");
        panel.add(new JLabel("Model:"));
        modelField = new JTextField();
        panel.add(modelField);

        panel.add(new JLabel("Color"));
        String[] colorTypes = {"White", "Black", "Red", "Blue", "Green", "Yellow"};
        JComboBox<String> colorField = new JComboBox<>(colorTypes);
        panel.add(colorField);

        panel.add(new JLabel("Passengers:"));
        passengersField = new JTextField();
        panel.add(passengersField);

        panel.add(new JLabel("Max speed:"));
        max_speedField = new JTextField();
        panel.add(max_speedField);

        panel.add(new JLabel("Road type:"));
        String[] useTypes = {"paved", "dirt"};
        road_typeField = new JComboBox<>(useTypes);
        panel.add(road_typeField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        SystemButtons(buttonPanel);
        add(buttonPanel,BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String model = modelField.getText();
                String color = (String)colorField.getSelectedItem();
                double max_speed = Double.parseDouble(max_speedField.getText());
                int passengers = Integer.parseInt(passengersField.getText());
                String road_type = (String) road_typeField.getSelectedItem();
                synchronized (agency) {
                    agency.BuildBicycle(model, passengers, max_speed, imagePanel.getSelectedImage(), road_type,color);
                }
                if(state == 1){
                    updateDataBase();
                }
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void CruiseShip(Agency agency){
        setTitle("Cruise Ship");
        panel.add(new JLabel("Model:"));
        modelField = new JTextField();
        panel.add(modelField);

        panel.add(new JLabel("Color"));
        String[] useTypes = {"White", "Black", "Red", "Blue", "Green", "Yellow"};
        JComboBox<String> colorField = new JComboBox<>(useTypes);
        panel.add(colorField);

        panel.add(new JLabel("Passengers:"));
        passengersField = new JTextField();
        panel.add(passengersField);

        panel.add(new JLabel("Max speed:"));
        max_speedField = new JTextField();
        panel.add(max_speedField);

        panel.add(new JLabel("Fuel consumption:"));
        fuel_consumptionField = new JTextField();
        panel.add(fuel_consumptionField);

        panel.add(new JLabel("Average life span:"));
        avg_life_spanField = new JTextField();
        panel.add(avg_life_spanField);

        panel.add(new JLabel("Flag:"));
        String[] flagString = {"Israel", "USA", "Germany", "Italy", "Somalia", "Pirate"};
        ImageIcon[] flagImages = {
                new ImageIcon("Israel.png"),
                new ImageIcon("USA.png"),
                new ImageIcon("Germany.png"),
                new ImageIcon("Italy.png"),
                new ImageIcon("Somalia.png"),
                new ImageIcon("Pirate.png")
        };
        ImageIcon[] flags = new ImageIcon[flagImages.length];
        for (int i=0; i<flagImages.length;i++)
            flags[i] = new ImageIcon(SetFlagsSize(flagImages[i]));
        flagField = new JComboBox<>(flags);
        flagField.setPreferredSize(new Dimension(25,25));
        panel.add(flagField, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        SystemButtons(buttonPanel);
        add(buttonPanel,BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String model = modelField.getText();
                String color = (String)colorField.getSelectedItem();
                int passengers = Integer.parseInt(passengersField.getText());
                double max_speed = Double.parseDouble(max_speedField.getText());
                double fuel_consumption = Double.parseDouble(fuel_consumptionField.getText());
                double avg_life_span = Double.parseDouble(avg_life_spanField.getText());
                int flagIndex = flagField.getSelectedIndex();
                synchronized (agency) {
                    agency.BuildCruiseShip(model, passengers, max_speed, imagePanel.getSelectedImage(), fuel_consumption, avg_life_span, flagString[flagIndex],color);
                }
                if(state == 1){
                    updateDataBase();
                }
                dispose();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void HybridAirplane(Agency agency){
        setTitle("Hybrid Airplane");
        panel.add(new JLabel("Model:"));
        modelField = new JTextField();
        panel.add(modelField);

        panel.add(new JLabel("Color"));
        String[] useTypes = {"White", "Black", "Red", "Blue", "Green", "Yellow"};
        JComboBox<String> colorField = new JComboBox<>(useTypes);
        panel.add(colorField);

        panel.add(new JLabel("Max speed:"));
        max_speedField = new JTextField();
        panel.add(max_speedField);

        panel.add(new JLabel("Passengers:"));
        passengersField = new JTextField();
        panel.add(passengersField);

        panel.add(new JLabel("Wheel count:"));
        wheel_countField = new JTextField();
        panel.add(wheel_countField);

        panel.add(new JLabel("Fuel consumption:"));
        fuel_consumptionField = new JTextField();
        panel.add(fuel_consumptionField);

        panel.add(new JLabel("Average life span:"));
        avg_life_spanField = new JTextField();
        panel.add(avg_life_spanField);

        panel.add(new JLabel("Sail with the wind?:"));
        String[] options = {"Yes", "No"};
        sail_with_windField = new JComboBox<>(options);
        panel.add(sail_with_windField);

        panel.add(new JLabel("Flag:"));
        String[] flagString = {"Israel", "USA", "Germany", "Italy", "Somalia", "Pirate"};
        ImageIcon[] flagImages = {
                new ImageIcon("Israel.png"),
                new ImageIcon("USA.png"),
                new ImageIcon("Germany.png"),
                new ImageIcon("Italy.png"),
                new ImageIcon("Somalia.png"),
                new ImageIcon("Pirate.png")
        };
        ImageIcon[] flags = new ImageIcon[flagImages.length];
        for (int i=0; i<flagImages.length;i++)
            flags[i] = new ImageIcon(SetFlagsSize(flagImages[i]));
        flagField = new JComboBox<>(flags);
        flagField.setPreferredSize(new Dimension(25,25));
        panel.add(flagField, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        SystemButtons(buttonPanel);
        add(buttonPanel,BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String model = modelField.getText();
                String color = (String)colorField.getSelectedItem();
                double max_speed = Double.parseDouble(max_speedField.getText());
                int passengers = Integer.parseInt(passengersField.getText());
                int wheel_count = Integer.parseInt(wheel_countField.getText());
                double fuel_consumption = Double.parseDouble(fuel_consumptionField.getText());
                double avg_life_span = Double.parseDouble(avg_life_spanField.getText());
                boolean sail_with_wind = Objects.equals(sail_with_windField.getSelectedItem(), "Yes");
                int flagIndex = flagField.getSelectedIndex();
                synchronized (agency) {
                    agency.BuildHybridAirplane(model, passengers, max_speed, imagePanel.getSelectedImage(), wheel_count, sail_with_wind, flagString[flagIndex], fuel_consumption, avg_life_span,color);
                }
                if(state == 1){
                    updateDataBase();
                }
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the form
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void ElectricBicycle(Agency agency) {
        setTitle("Electric Bicycle");
        panel.add(new JLabel("Model:"));
        modelField = new JTextField();
        panel.add(modelField);

        panel.add(new JLabel("Color"));
        String[] colorTypes = {"White", "Black", "Red", "Blue", "Green", "Yellow"};
        JComboBox<String> colorField = new JComboBox<>(colorTypes);
        panel.add(colorField);

        panel.add(new JLabel("Passengers:"));
        passengersField = new JTextField();
        panel.add(passengersField);

        panel.add(new JLabel("Max speed:"));
        max_speedField = new JTextField();
        panel.add(max_speedField);

        panel.add(new JLabel("Average life span:"));
        avg_life_spanField = new JTextField();
        panel.add(avg_life_spanField);

        panel.add(new JLabel("Road type:"));
        String[] useTypes = {"paved", "dirt"};
        road_typeField = new JComboBox<>(useTypes);
        panel.add(road_typeField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        SystemButtons(buttonPanel);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String model = modelField.getText();
                String color = (String)colorField.getSelectedItem();
                double max_speed = Double.parseDouble(max_speedField.getText());
                int passengers = Integer.parseInt(passengersField.getText());
                String road_type = (String) road_typeField.getSelectedItem();
                double avg_life_span = Double.parseDouble(avg_life_spanField.getText());
                synchronized (agency) {
                    agency.BuildElectricBicycle(model, passengers, max_speed, imagePanel.getSelectedImage(), road_type, avg_life_span,color);
                }
                if (state == 1) {
                    updateDataBase();
                }
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void setState() {
        state = 1;
    }

    public void updateDataBase(){
        JOptionPane.showMessageDialog(null, "Updating database... Please wait");
        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            mainMenu.refresh();
            JOptionPane.showMessageDialog(null, "Database updated.");
        }, ThreadLocalRandom.current().nextInt(3, 9), TimeUnit.SECONDS);
    }
}