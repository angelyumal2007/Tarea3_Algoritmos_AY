/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Tarea3;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class BibliotecaDiscos extends javax.swing.JFrame {
    
    String[] titulos = new String[5];
    int[] anios = new int[5];
    int cantidad = 0; 

    private static final String ARCHIVO = "discos.csv";

    public BibliotecaDiscos() {
        initComponents();
        setLocationRelativeTo(null);
        cargarDatos();
    }
        //Lectura
    private void cargarDatos() {
        File archivo = new File(ARCHIVO);
        try (Scanner sc = new Scanner(archivo)) {
            int i = 0;
            while (sc.hasNextLine() && i < titulos.length) {
                String linea = sc.nextLine().trim();
                if (linea.isEmpty()) {
                    continue;
                }
                String[] partes = linea.split(",");
                titulos[i] = partes[0].trim();
                anios[i] = Integer.parseInt(partes[1].trim());
                i++;
            }
            cantidad = i;
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "No se encontró el archivo " + ARCHIVO);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "Formato inválido en " + ARCHIVO);
        }
    }

    //Busqueda
    private int buscarIndice(String titulo) {
        for (int i = 0; i < cantidad; i++) {
            if (titulos[i].equalsIgnoreCase(titulo)) {
                return i;
            }
        }
        return -1;
    }

    // Sobreescritura
    private void guardarArchivo() {
        // false = sobrescribir el archivo por completo
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO, false))) {
            for (int i = 0; i < cantidad; i++) {
                pw.println(titulos[i] + "," + anios[i]);
            }
            JOptionPane.showMessageDialog(this, "Registro actualizado y archivo guardado.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lblAnio = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNuevoAnio = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Biblioteca de Discos Musicales");
        getContentPane().setLayout(new java.awt.FlowLayout());

        jLabel1.setText("Buscar Álbum:");
        getContentPane().add(jLabel1);

        txtBuscar.setColumns(14);
        getContentPane().add(txtBuscar);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar);

        lblAnio.setText("Año actual: -");
        getContentPane().add(lblAnio);

        jLabel2.setText("Nuevo Año:");
        getContentPane().add(jLabel2);

        txtNuevoAnio.setColumns(6);
        getContentPane().add(txtNuevoAnio);

        btnActualizar.setText("Actualizar Registro");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(btnActualizar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String titulo = txtBuscar.getText().trim();
        int indice = buscarIndice(titulo);
        if (indice >= 0) {
            lblAnio.setText("Año actual: " + anios[indice]);
        } else {
            lblAnio.setText("Año actual: -");
            JOptionPane.showMessageDialog(this, "Álbum no encontrado.");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        String titulo = txtBuscar.getText().trim();
        int indice = buscarIndice(titulo);
        if (indice < 0) {
            JOptionPane.showMessageDialog(this, "Álbum no encontrado. Búsquelo primero.");
            return;
        }
        int nuevoAnio;
        try {
            nuevoAnio = Integer.parseInt(txtNuevoAnio.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un año numérico válido.");
            return;
        }

        anios[indice] = nuevoAnio;
        guardarArchivo();
        lblAnio.setText("Año actual: " + anios[indice]);
        txtNuevoAnio.setText("");
    }//GEN-LAST:event_btnActualizarActionPerformed

   public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(() -> new BibliotecaDiscos().setVisible(true));
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblAnio;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtNuevoAnio;
    // End of variables declaration//GEN-END:variables
}
