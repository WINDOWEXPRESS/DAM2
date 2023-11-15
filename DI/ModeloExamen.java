/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.diseniointerfaz;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_CANCEL_OPTION;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;

/**
 *
 * @author Usuario
 */
public class ModeloExamen {
    public static void main(String[] args) {
        Map<String,Color> colorMap = new HashMap<>();
        colorMap.put("rojo", Color.RED);
        colorMap.put("azul", Color.BLUE);
        colorMap.put("verde", Color.GREEN);
        colorMap.put("amarillo", Color.YELLOW);
        
        JLabel textoIntroducirTitulo = new JLabel("Introducir el titulo:");
        JButton cambiar = new JButton("Cambiar!");
        JTextField txtTitulo = new JTextField(20);
        JButton minimizar = new JButton("Minimizar");
        JButton cerrar = new JButton("Cerrar");
        JButton mensaje = new JButton("Mensaje");
        JCheckBox chkRefrescoCola = new JCheckBox("cola");
        JCheckBox chkRefrescoFresa = new JCheckBox("fresa");
        JCheckBox chkRefrescoLimon = new JCheckBox("limon");
        JRadioButton rButtonHombre = new JRadioButton("Hombre");
        JRadioButton rButtonMujer = new JRadioButton("Mujer");
        JTextArea txtArea = new JTextArea(10, 20);
        
        DefaultListModel model = new DefaultListModel();
        
        
        JList<String> lista = new JList<>(model);
        
        
        //JList <String> lista = new JList();
                
        
        ButtonGroup sexo = new ButtonGroup();
        sexo.add(rButtonHombre);
        sexo.add(rButtonMujer);
        
        JComboBox listaColor = new JComboBox();
        listaColor.addItem("verde");
        listaColor.addItem("amarillo");
        listaColor.addItem("azul");
        
        
        
        JFrame frame = new JFrame("Titulo");
       
        JPanel panel = new JPanel();
        
        panel.add(textoIntroducirTitulo);
        panel.add(txtTitulo);
        panel.add(cambiar);
        panel.add(listaColor);
        panel.add(minimizar);
        panel.add(cerrar);
        panel.add(mensaje);
        panel.add(chkRefrescoCola);
        panel.add(chkRefrescoFresa);
        panel.add(chkRefrescoLimon);
        panel.add(rButtonHombre);
        panel.add(rButtonMujer);
        panel.add(txtArea);
        panel.add(lista);
        
        //panel.setBackground(Color.red);
        
        frame.add(panel);
        frame.setSize(600, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        listaColor.addActionListener((var e) -> {
            panel .setBackground(colorMap.get((String)listaColor.getSelectedItem()));
        });
        minimizar.addActionListener((e) -> {
            frame.setExtendedState(JFrame.ICONIFIED);
            cambiar.setEnabled(false);
        });
        cerrar.addActionListener((e) -> {
            System.exit(0);
        });
        mensaje.addActionListener((e) -> {
            JOptionPane.showConfirmDialog(panel, "Este examen es demasiado sencillo", "Seleccionar una Opcion",YES_NO_CANCEL_OPTION);
           // JOptionPane.showMessageDialog(panel, "Este examen es demasiado sencillo", "Seleccionar una Opcion",0);
            
        });
        cambiar.addActionListener((e) -> {
            if(!txtTitulo.getText().isEmpty()){
                frame.setTitle(txtTitulo.getText());
            }else{
                JOptionPane.showMessageDialog(panel, "No ha introducido ningun titulo", "Seleccionar una Opcion",0);
            }
            
        });

    }
}
