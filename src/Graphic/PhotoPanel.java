package Graphic;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.Objects;

public class PhotoPanel extends JPanel implements ActionListener{
    private JPanel buttonPanel;
    private JToggleButton[] buttons;
    private ImageIcon selectedImage;
    public PhotoPanel() {
        selectedImage = new ImageIcon();
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Choose Photo"));
        buttons = new JToggleButton[7];
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(2,10,2,10));
        try{
            setButtons();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        JButton uploadButton = new JButton("Upload Photo");
        uploadButton.addActionListener(new UploadButtonListener());
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(uploadButton, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttons[0]) {
            for(int i = 1; i <7 ; i++)
                if(buttons[i].isSelected())
                    buttons[i].setSelected(false);
            selectedImage = new ImageIcon("images/jeep.png");
            Image resizedImage = selectedImage.getImage().getScaledInstance(120, 90, Image.SCALE_SMOOTH);
            selectedImage = new ImageIcon(resizedImage);
        }
        if(e.getSource() == buttons[1]) {
            for(int i = 0; i <7 ; i++)
                if(buttons[i].isSelected() && i != 1)
                    buttons[i].setSelected(false);
            selectedImage = new ImageIcon("images/frigate.png");
            Image resizedImage = selectedImage.getImage().getScaledInstance(120, 90, Image.SCALE_SMOOTH);
            selectedImage = new ImageIcon(resizedImage);
        }
        if(e.getSource() == buttons[2]) {
            for(int i = 0; i <7 ; i++)
                if(buttons[i].isSelected() && i != 2)
                    buttons[i].setSelected(false);
            selectedImage = new ImageIcon("images/playing glider.png");
            Image resizedImage = selectedImage.getImage().getScaledInstance(120, 90, Image.SCALE_SMOOTH);
            selectedImage = new ImageIcon(resizedImage);
        }
        if(e.getSource() == buttons[3]) {
            for(int i = 0; i <7 ; i++)
                if(buttons[i].isSelected() && i != 3)
                    buttons[i].setSelected(false);
            selectedImage = new ImageIcon("images/spying glider.png");
            Image resizedImage = selectedImage.getImage().getScaledInstance(120, 90, Image.SCALE_SMOOTH);
            selectedImage = new ImageIcon(resizedImage);
        }
        if(e.getSource() == buttons[4]) {
            for(int i = 0; i <7 ; i++)
                if(buttons[i].isSelected() && i != 4)
                    buttons[i].setSelected(false);
            selectedImage = new ImageIcon("images/amphibious.png");
            Image resizedImage = selectedImage.getImage().getScaledInstance(120, 90, Image.SCALE_SMOOTH);
            selectedImage = new ImageIcon(resizedImage);
        }
        if(e.getSource() == buttons[5]) {
            for(int i = 0; i <7 ; i++)
                if(buttons[i].isSelected() && i != 5)
                    buttons[i].setSelected(false);
            selectedImage = new ImageIcon("images/bicycle.png");
            Image resizedImage = selectedImage.getImage().getScaledInstance(120, 90, Image.SCALE_SMOOTH);
            selectedImage = new ImageIcon(resizedImage);
        }
        if(e.getSource() == buttons[6]) {
            for(int i = 0; i <7 ; i++)
                if(buttons[i].isSelected() && i != 6)
                    buttons[i].setSelected(false);
            selectedImage = new ImageIcon("images/cruiseship.png");
            Image resizedImage = selectedImage.getImage().getScaledInstance(120, 90, Image.SCALE_SMOOTH);
            selectedImage = new ImageIcon(resizedImage);
        }

    }

    private class UploadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(PhotoPanel.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                selectedImage = new ImageIcon(file.getPath());
                Image resizedImage = selectedImage.getImage().getScaledInstance(120, 90, Image.SCALE_SMOOTH);
                selectedImage = new ImageIcon(resizedImage);
                JToggleButton uploadedImage = new JToggleButton(selectedImage);
                uploadedImage.setSelected(true);
                buttonPanel.add(uploadedImage,new FlowLayout(FlowLayout.LEFT));
                revalidate();
                repaint();
            }
        }
    }

    private void setButtons() throws IOException {
        ImageIcon image = new ImageIcon("images/jeep.png");
        Image resizedImage = image.getImage().getScaledInstance(90, 60, Image.SCALE_SMOOTH);
        buttons[0] = new JToggleButton(new ImageIcon(resizedImage));
        buttons[0].setFocusable(false);
        buttons[0].setToolTipText("Jeep");
        buttons[0].addActionListener(this);
        buttonPanel.add(buttons[0]);

        image = new ImageIcon("images/frigate.png");
        resizedImage = image.getImage().getScaledInstance(90, 60, Image.SCALE_SMOOTH);
        buttons[1] = new JToggleButton(new ImageIcon(resizedImage));
        buttons[1].setFocusable(false);
        buttons[1].setToolTipText("Frigate");
        buttons[1].addActionListener(this);
        buttonPanel.add(buttons[1]);

        image = new ImageIcon("images/playing glider.png");
        resizedImage = image.getImage().getScaledInstance(90, 60, Image.SCALE_SMOOTH);
        buttons[2] = new JToggleButton(new ImageIcon(resizedImage));
        buttons[2].setFocusable(false);
        buttons[2].setToolTipText("Playing Glider");
        buttons[2].addActionListener(this);
        buttonPanel.add(buttons[2]);

        image = new ImageIcon("images/spying glider.png");
        resizedImage = image.getImage().getScaledInstance(90, 60, Image.SCALE_SMOOTH);
        buttons[3] = new JToggleButton(new ImageIcon(resizedImage));
        buttons[3].setFocusable(false);
        buttons[3].setToolTipText("Spying Glider");
        buttons[3].addActionListener(this);
        buttonPanel.add(buttons[3]);

        image = new ImageIcon("images/amphibious.png");
        resizedImage = image.getImage().getScaledInstance(90, 60, Image.SCALE_SMOOTH);
        buttons[4] = new JToggleButton(new ImageIcon(resizedImage));
        buttons[4].setFocusable(false);
        buttons[4].setToolTipText("Amphibious");
        buttons[4].addActionListener(this);
        buttonPanel.add(buttons[4]);

        image = new ImageIcon("images/bicycle.png");
        resizedImage = image.getImage().getScaledInstance(90, 60, Image.SCALE_SMOOTH);
        buttons[5] = new JToggleButton(new ImageIcon(resizedImage));
        buttons[5].setFocusable(false);
        buttons[5].setToolTipText("Bicycle");
        buttons[5].addActionListener(this);
        buttonPanel.add(buttons[5]);

        image = new ImageIcon("images/cruiseship.png");
        resizedImage = image.getImage().getScaledInstance(90, 60, Image.SCALE_SMOOTH);
        buttons[6] = new JToggleButton(new ImageIcon(resizedImage));
        buttons[6].setFocusable(false);
        buttons[6].setToolTipText("Cruise Ship");
        buttons[6].addActionListener(this);
        buttonPanel.add(buttons[6]);
    }
    public ImageIcon getSelectedImage(){ return selectedImage;}
}