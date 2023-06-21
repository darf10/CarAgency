package Graphic;
import System.Agency;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Vector;

public class InventoryFrame extends JFrame {
    private Agency agency;
    private JPanel mainPanel,imagePanel;
    private Vector<JToggleButton> images;
    public InventoryFrame(Agency agency) {
        this.agency = agency;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                refresh();
            }
        });
        imagePanel = new JPanel(new FlowLayout());
        setTitle("Inventory");
        setSize(400, 400);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        try {
            BuildImagePanel(agency);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(imagePanel, BorderLayout.CENTER);
        add(mainPanel);
        setLocationRelativeTo(null);
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
            images.get(i).setToolTipText("<html>"+agency.getVehicleAt(i).toString()+"</html>");
        }
        for (JToggleButton image : images) {
            imagePanel.add(image);
        }
        revalidate();
        repaint();
    }
    private void refresh() {
        imagePanel.removeAll();
        try {
            BuildImagePanel(agency);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
