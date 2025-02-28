import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Ventana extends JFrame {
    private JFileChooser fileChooser;
    private JColorChooser colorChooser;
    private JButton btnArchivo;
    private JButton btnColor;
    private JButton btnDialog;
    private Color colorSelecccionado;
    private File archivoSeleccionado;


    public Ventana() {
        setTitle("Ejemplo de Dialogs");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        fileChooser = new JFileChooser();
        colorChooser = new JColorChooser();
        btnArchivo = new JButton("Seleccionar Imagen");
        btnColor = new JButton("Seleccionar Color");
        btnDialog = new JButton("Mostrar imagen y color");

        btnArchivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarArchivo();
            }
        });

        btnColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarColor();
            }
        });

        btnDialog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDialogo();
            }
        });

        add(btnArchivo);
        add(btnColor);
        add(btnDialog);
    }

    private void seleccionarArchivo() {
        int dialogEstado = fileChooser.showOpenDialog(this);
        if (dialogEstado == JFileChooser.APPROVE_OPTION) {
            archivoSeleccionado = fileChooser.getSelectedFile();
        }
    }

    private void seleccionarColor() {
        colorSelecccionado = JColorChooser.showDialog(this, "Seleccionar Color", Color.BLACK);
    }

    private void mostrarDialogo() {
        if (archivoSeleccionado != null && colorSelecccionado != null) {
            try {
                BufferedImage imagen = ImageIO.read(archivoSeleccionado);
                mostrarImagenDialog(imagen, archivoSeleccionado.getName(), colorSelecccionado);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una imagen y un color.");
        }
    }

    private void mostrarImagenDialog(BufferedImage imagen, String fileName, Color color) {
        JDialog dialog = new JDialog(this, "Imagen y color seleccionados", true);
        dialog.setSize(500, 500);
        dialog.setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel(new ImageIcon(imagen));
        JLabel textLabel = new JLabel(fileName);
        textLabel.setForeground(color);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);

        dialog.add(imageLabel, BorderLayout.CENTER);
        dialog.add(textLabel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
}
