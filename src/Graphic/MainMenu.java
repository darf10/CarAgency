package Graphic;

import DesignPatterns.MementoCaretaker;
import DesignPatterns.VehicleDecorator;
import DesignPatterns.VehicleMemento;
import System.Agency;
import Vehicles.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MainMenu extends JFrame implements ActionListener {
    private JButton addButton, resetButton, changeFlagButton, inventoryButton, testDriveButton, buyButton, exitButton, saveButton, loadButton;
    private JPanel mainPanel, imagePanel, optionPanel;
    private JComboBox<ImageIcon> flagButton;
    private JScrollPane scrollBar;
    private Vector<JToggleButton> images;
    private Agency agency;
    private VehicleDecorator curVehicle;
    private ExecutorService ex;
    private MementoCaretaker mementoCaretaker;

    private VehicleMemento vehicleMemento;

    public MainMenu(Agency agency) {
        mementoCaretaker = new MementoCaretaker();
        ex = Executors.newFixedThreadPool(6);
        this.agency = agency;
        imagePanel = new JPanel(new FlowLayout());
        setTitle("Your Agency");
        setSize(840, 680);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        SetButtons();
        try {
            BuildImagePanel(agency);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        BuildOptionPanel();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(imagePanel, BorderLayout.CENTER);
        mainPanel.add(optionPanel, BorderLayout.SOUTH);
        add(mainPanel);
        setVisible(true);
    }

    private void BuildImagePanel(Agency agency) throws IOException {
        images = new Vector<>();
        for (int i = 0; i < agency.getSize(); i++) {
            images.add(new JToggleButton(agency.getVehicleAt(i).getImage()));
            images.get(i).setPreferredSize(new Dimension(130, 90));
            switch (agency.getVehicleAt(i).getColor()) {
                case "White" -> images.get(i).setBorder(new LineBorder(Color.WHITE, 5));
                case "Black" -> images.get(i).setBorder(new LineBorder(Color.BLACK, 5));
                case "Red" -> images.get(i).setBorder(new LineBorder(Color.RED, 5));
                case "Blue" -> images.get(i).setBorder(new LineBorder(Color.BLUE, 5));
                case "Green" -> images.get(i).setBorder(new LineBorder(Color.GREEN, 5));
                case "Yellow" -> images.get(i).setBorder(new LineBorder(Color.YELLOW, 5));
            }
            images.get(i).setToolTipText("<html>" + agency.getVehicleAt(i).toString() + "</html>");
            images.get(i).addActionListener(this);
        }
        for (JToggleButton image : images) {
            imagePanel.add(image);
        }
        revalidate();
        repaint();
    }

    private void BuildOptionPanel() {
        optionPanel = new JPanel(new FlowLayout());
        optionPanel.add(addButton);
        optionPanel.add(inventoryButton);
        optionPanel.add(resetButton);
        optionPanel.add(changeFlagButton);
        optionPanel.add(testDriveButton);
        optionPanel.add(buyButton);
        optionPanel.add(saveButton);
        optionPanel.add(loadButton);
        optionPanel.add(exitButton);
    }

    private void SetButtons() {
        addButton = new JButton("Add");
        addButton.setFocusable(false);
        addButton.addActionListener(this);
        resetButton = new JButton("Reset");
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        changeFlagButton = new JButton("Change Flag");
        changeFlagButton.setFocusable(false);
        changeFlagButton.addActionListener(this);
        inventoryButton = new JButton("Current Vehicles");
        inventoryButton.setFocusable(false);
        inventoryButton.addActionListener(this);
        testDriveButton = new JButton("Test Drive");
        testDriveButton.setFocusable(false);
        testDriveButton.addActionListener(this);
        buyButton = new JButton("Buy");
        buyButton.setFocusable(false);
        buyButton.addActionListener(this);
        exitButton = new JButton("Exit");
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);
        buyButton.setVisible(false);
        testDriveButton.setVisible(false);
        saveButton = new JButton("Save");
        saveButton.setFocusable(false);
        saveButton.addActionListener(this);
        loadButton = new JButton("Load");
        loadButton.setFocusable(false);
        loadButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            int choice;
            String[] types = {"", "Jeep", "Frigate", "Spying Glider", "Playing Glider", "Amphibious", "Bicycle", "Cruise Ship", "Hybrid Airplane", "Electric Bicycle"};
            JComboBox<String> comboBox = new JComboBox<String>(types);
            JOptionPane.showMessageDialog(this, comboBox, "Choose Vehicle Type", JOptionPane.QUESTION_MESSAGE);
            choice = comboBox.getSelectedIndex();
            if (choice != 0) {
                new CreateVehicle(agency, choice, this);
            }
        }
        if (e.getSource() == resetButton) {
            ex.submit(new Worker(this, "Reset", curVehicle));
        }

        if (e.getSource() == changeFlagButton) {
            new Worker(this, "ChangeFlag", curVehicle).execute();
        }

        if (e.getSource() == inventoryButton) {
            new InventoryFrame(agency);
        }

        if (e.getSource() == testDriveButton) {
            if (!curVehicle.getLock().isLocked()) {
                new Worker(this, "TestDrive", curVehicle).execute();
                buyButton.setVisible(false);
                testDriveButton.setVisible(false);
                refresh();
            } else
                JOptionPane.showMessageDialog(null, "Vehicle is not available");

        }
        if (e.getSource() == buyButton) {
            if (!curVehicle.getLock().isLocked()) {
                new Worker(this, "Buy", curVehicle).execute();
                buyButton.setVisible(false);
                testDriveButton.setVisible(false);
                refresh();
            } else
                JOptionPane.showMessageDialog(null, "Vehicle is not available");

        }
        if (e.getSource() == exitButton) {
            new Worker(this, "Exit", curVehicle).execute();
        }

        if (e.getSource() == saveButton) {
            vehicleMemento = agency.createMemento();
            mementoCaretaker.saveMemento(vehicleMemento);
            JOptionPane.showMessageDialog(null, "Saved");

            refresh();
        }
        if (e.getSource() == loadButton) {
            if (mementoCaretaker.getAmount() != 0) {
                vehicleMemento = mementoCaretaker.getMemento();
                agency.restoreMemento(vehicleMemento);
                JOptionPane.showMessageDialog(null, "Loaded");
            }
            else {
                JOptionPane.showMessageDialog(null, "No saves to load.");
            }

            refresh();
        }
        for (int i = 0; i < images.size(); i++) {
            if (agency.getSize() == 0)
                break;
            if (e.getSource() == images.get(i)) {
                curVehicle = agency.getVehicleAt(i);
                buyButton.setVisible(true);
                testDriveButton.setVisible(true);
                revalidate();
                repaint();
                for (int j = 0; j < images.size(); j++) {
                    if (j != i)
                        images.get(j).setSelected(false);
                }
            }
        }
    }

    public Image SetFlagsSize(ImageIcon flag) {
        Image fixedFlag = flag.getImage();
        return fixedFlag.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    }

    public void refresh() {
        imagePanel.removeAll();
        try {
            BuildImagePanel(agency);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Agency getAgency() {
        return agency;
    }
}
