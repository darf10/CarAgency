package Graphic;

import DesignPatterns.VehicleDecorator;
import Vehicles.*;
import System.*;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;

public class Worker extends SwingWorker<Void, String> {
    private JFrame frame;
    private JPanel panel, secondaryPanel;
    private MainMenu menu;
    private String type;
    private long distance;
    public VehicleDecorator curVehicle;
    private final CountDownLatch cdl;

    public Worker(MainMenu menu, String type, VehicleDecorator curVehicle) {
        cdl = new CountDownLatch(1);
        this.distance = 0;
        this.menu = menu;
        this.type = type;
        this.curVehicle = curVehicle;
    }

    public void buildFrame() {
        frame = new JFrame();
        panel = new JPanel();
        secondaryPanel = new JPanel();
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setBuyFrame() {
        buildFrame();
        frame.setTitle("Buy");
        JLabel label = new JLabel("Are You sure you want to buy this vehicle?");
        JButton yesButton = new JButton("Yes");
        yesButton.addActionListener(e -> {
            menu.getAgency().DeleteVehicle(menu.getAgency().FindIndex(curVehicle));
            frame.dispose();
            JOptionPane.showMessageDialog(null, "Updating database... Please wait");
            Executors.newSingleThreadScheduledExecutor().schedule(() -> {
                menu.refresh();
                cdl.countDown();
                JOptionPane.showMessageDialog(null, "Database updated.");
            }, ThreadLocalRandom.current().nextInt(3, 9), TimeUnit.SECONDS);
        });
        JButton noButton = new JButton("No");
        noButton.addActionListener(e -> {
            frame.dispose();
            cdl.countDown();
        });
        panel.setLayout(new FlowLayout());
        panel.add(label, BorderLayout.NORTH);
        panel.add(yesButton);
        panel.add(noButton);
        frame.add(panel);
        frame.pack();
        yesButton.setVisible(false);
        noButton.setVisible(false);
        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            yesButton.setVisible(true);
            noButton.setVisible(true);
        }, ThreadLocalRandom.current().nextInt(5, 11), TimeUnit.SECONDS);


    }

    private void setChangeFlagFrame() {
        buildFrame();
        frame.setTitle("Change Flag");
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
        for (int i = 0; i < flagImages.length; i++)
            flags[i] = new ImageIcon(SetFlagsSize(flagImages[i]));
        JComboBox<ImageIcon> flagButton = new JComboBox<>(flags);
        flagButton.setPreferredSize(new Dimension(25, 25));
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            int flagIndex = flagButton.getSelectedIndex();
            for (int i = 0; i < menu.getAgency().getSize(); i++) {
                if (menu.getAgency().getVehicleAt(i).getVehicle() instanceof SeaVehicleI)
                    ((SeaVehicleI) menu.getAgency().getVehicleAt(i).getVehicle()).setFlag(flagString[flagIndex]);
            }
            frame.dispose();
            JOptionPane.showMessageDialog(null, "Updating database... Please wait");
            Executors.newSingleThreadScheduledExecutor().schedule(() ->{
                menu.refresh();
                JOptionPane.showMessageDialog(null, "Database updated.");
            }, ThreadLocalRandom.current().nextInt(3, 9), TimeUnit.SECONDS);


        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> frame.dispose());
        frame.setLayout(new GridLayout(2, 1));
        panel.setLayout(new BorderLayout());
        panel.add(flagButton, BorderLayout.NORTH);
        secondaryPanel.setLayout(new GridLayout(1, 2));
        secondaryPanel.add(confirmButton);
        secondaryPanel.add(cancelButton);
        frame.add(panel);
        frame.add(secondaryPanel);
        frame.pack();
    }

    public Image SetFlagsSize(ImageIcon flag) {
        Image fixedFlag = flag.getImage();
        return fixedFlag.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    }

    private void setResetFrame() {
        buildFrame();
        frame.setTitle("Reset Distance");
        JLabel label = new JLabel("Are You sure want to reset all vehicles distance traveled?");
        JButton yesButton = new JButton("Yes");
        yesButton.addActionListener(e -> {
            for (int i = 0; i < menu.getAgency().getSize(); i++) {
                menu.getAgency().getVehicleAt(i).reset_distance();
            }
            frame.dispose();
            JOptionPane.showMessageDialog(null, "Updating database... Please wait");
            Executors.newSingleThreadScheduledExecutor().schedule(() ->{
                menu.refresh();
                JOptionPane.showMessageDialog(null, "Database updated.");
            }, ThreadLocalRandom.current().nextInt(3, 9), TimeUnit.SECONDS);
        });
        JButton noButton = new JButton("No");
        noButton.addActionListener(e -> frame.dispose());
        panel.setLayout(new FlowLayout());
        panel.add(label, BorderLayout.NORTH);
        panel.add(yesButton);
        panel.add(noButton);
        frame.add(panel);
        frame.pack();
    }
    private void setTestDriveFrame() throws InterruptedException {
        buildFrame();
        frame.setTitle("Test Drive");
        JLabel label = new JLabel("What distance would you like to travel?");
        JTextField textField = new JTextField(20);
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            if (curVehicle.getLock().tryLock()) {
                curVehicle.setStatus("Performing a test drive");
                menu.refresh();
                distance = Long.parseLong(textField.getText());
                moveVehicle(distance);
                frame.dispose();
            }else {
                JOptionPane.showMessageDialog(null, "Vehicle currently unavailable for test drive");
                cdl.countDown();
                frame.dispose();
            }
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            cdl.countDown();
            frame.dispose();
        });
        panel.setLayout(new GridLayout(2, 2));
        panel.add(label);
        panel.add(textField);
        panel.add(confirmButton);
        panel.add(cancelButton);
        frame.add(panel);
        frame.pack();

    }

    private void moveVehicle(long distance) {
        if(StaticLocks.tryRegister()) {
            curVehicle.move(distance);
            Executors.newSingleThreadScheduledExecutor().schedule(() -> {
                StaticLocks.unregister();
                cdl.countDown();
                JOptionPane.showMessageDialog(null, "Test drive completed");
                curVehicle.setStatus("Available");
                menu.refresh();
            }, distance*100, TimeUnit.MILLISECONDS);

        }
        else {
            JOptionPane.showMessageDialog(null, "There are no test drivers available");
            cdl.countDown();
        }
    }

    private boolean checkExit() {
        int registered = StaticLocks.getRegistered();
        if(registered != 0)
            return false;
        for (int i = 0; i < menu.getAgency().getSize(); i++) {
            if (menu.getAgency().getVehicleAt(i).getLock().isLocked())
                return false;
        }
        return true;
    }

    @Override
    protected Void doInBackground() throws InterruptedException {
        switch (type) {
            case "TestDrive" -> {
                setTestDriveFrame();
                cdl.await();
                curVehicle.getLock().unlock();
            }
            case "Buy" -> {
                if (curVehicle.getLock().tryLock())
                    try {
                        setBuyFrame();
                        curVehicle.setStatus("In buying process");
                        menu.refresh();
                    } finally {
                        cdl.await();
                        curVehicle.getLock().unlock();
                        curVehicle.setStatus("Available");
                        menu.refresh();
                    }
            }
            case "ChangeFlag" -> setChangeFlagFrame();
            case "Reset" -> setResetFrame();
            case "Exit" -> {
                if (checkExit()) {
                    JOptionPane.showMessageDialog(null, "Exit successfully");
                    menu.dispose();
                } else
                    JOptionPane.showMessageDialog(null, "Cant exit program, close all opened windows.");
            }
        }
        return null;
    }

    @Override
    protected void done() {
        if(curVehicle != null)
            if (curVehicle.getLock().isLocked())
                curVehicle.getLock().unlock();
    }
}
