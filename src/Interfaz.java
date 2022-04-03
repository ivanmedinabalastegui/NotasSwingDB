import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Interfaz {

    public void creaInterfaz(){

        //Base de Datos
        BaseDeDatos BD = new BaseDeDatos();

        //Marco
        JFrame marco = new JFrame("Ejercicio PEP3T4 Java");

        //Etiquetas
        JLabel etiquetatitulo = new JLabel("GESTIÓN DE LA TABLA NOTAS");
        JLabel etiquetacodigo = new JLabel("Código Matrícula");
        JLabel etiquetanombre = new JLabel("Nombre Asignatura");
        JLabel etiquetanota1 = new JLabel("Nota 1");
        JLabel etiquetanota2 = new JLabel("Nota 2");
        JLabel etiquetamensaje = new JLabel("");

        //Botones
        JButton botConsultar = new JButton("Consultar");
        JButton botBorrar = new JButton("Borrar");
        JButton botInsertar = new JButton("Insertar");
        JButton botModificar = new JButton("Modificar");
        JTextField codTxt = new JTextField(3);
        JTextField nomTxt = new JTextField(16);
        JTextField nota1Txt = new JTextField(5);
        JTextField nota2Txt = new JTextField(5);

        //Paneles
        JPanel panelnotas = new JPanel();
        JPanel paneltitulo = new JPanel();
        JPanel panelcodigo = new JPanel();
        JPanel panelnombre = new JPanel();
        JPanel panelbotones = new JPanel();
        JPanel panelmensaje = new JPanel();

        //Tamaño del marco y posición
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setSize(400, 400);
        marco.setLocation(500, 350);

        //Panel título
        paneltitulo.add(etiquetatitulo);

        //Panel código matrícula
        panelcodigo.add(etiquetacodigo);
        panelcodigo.add(codTxt);

        //Panel nombre
        panelnombre.add(etiquetanombre);
        panelnombre.add(nomTxt);

        //Panel notas
        panelnotas.add(etiquetanota1);
        panelnotas.add(nota1Txt);
        panelnotas.add(etiquetanota2);
        panelnotas.add(nota2Txt);

        //Panel botones
        panelbotones.add(botInsertar);
        panelbotones.add(botConsultar);
        panelbotones.add(botBorrar);
        panelbotones.add(botModificar);

        //Panel mensaje inferior
        panelmensaje.add(etiquetamensaje);

        //Añadir paneles al marco
        marco.setLayout(new GridLayout(6,1));
        marco.add(paneltitulo);
        marco.add(panelcodigo);
        marco.add(panelnombre);
        marco.add(panelnotas);
        marco.add(panelbotones);
        marco.add(panelmensaje);

        //Acciones
        ActionListener botones = e -> {

            //Consultar registros de la bd
            if (e.getSource() == botConsultar){
                ResultSet r = BD.introRegistros("SELECT * FROM notas WHERE cod_matricula = " + codTxt.getText());
                try{
                if (r.next()){
                    nomTxt.setText(r.getString("nom_asignatura"));
                    nota1Txt.setText(r.getString("nota1"));
                    nota2Txt.setText(r.getString("nota2"));
                    etiquetamensaje.setText("Registro encontrado");
                }
                else {
                    System.out.println("No se ha encontrado el registro solicitado");
                }
                }catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            //Borrar registros de la bd
            if (e.getSource() == botBorrar){
                try {
                    BD.actuRegistros("DELETE FROM notas WHERE cod_matricula ='"+codTxt.getText()+"'");
                    etiquetamensaje.setText("Registro eliminado");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            //Insertar registros en la bd
            if (e.getSource() == botInsertar){
                try {
                    BD.actuRegistros("INSERT INTO notas (cod_matricula, nom_asignatura, nota1, nota2) VALUES ('"+codTxt.getText()+"', '"+nomTxt.getText()+"', '"+nota1Txt.getText()+"', '"+nota2Txt.getText()+"')");
                    etiquetamensaje.setText("Registro insertado");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            //Modificar registros de la bd
            if (e.getSource() == botModificar){
                try {
                    BD.actuRegistros("UPDATE notas SET nom_asignatura = '"+nomTxt.getText()+"', nota1 = '"+nota1Txt.getText()+"', nota2 = '"+nota2Txt.getText()+"' where cod_matricula ='"+codTxt.getText()+"'");
                    etiquetamensaje.setText("Registro modificado");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        };

        //Asignar función a los botones
        botConsultar.addActionListener(botones);
        botBorrar.addActionListener(botones);
        botInsertar.addActionListener(botones);
        botModificar.addActionListener(botones);

        //Hacer visibles todos los elementos
        marco.setVisible(true);
        panelnotas.setVisible(true);
        etiquetatitulo.setVisible(true);
        panelbotones.setVisible(true);
        etiquetacodigo.setVisible(true);
        etiquetanombre.setVisible(true);
        etiquetanota1.setVisible(true);
        etiquetanota2.setVisible(true);
        codTxt.setVisible(true);
        nomTxt.setVisible(true);
        nota1Txt.setVisible(true);
        nota2Txt.setVisible(true);
        botBorrar.setVisible(true);
        botConsultar.setVisible(true);
        botInsertar.setVisible(true);
        botInsertar.setVisible(true);
    }
    public static void main(String[] args) {
            Interfaz ventana = new Interfaz();
            ventana.creaInterfaz();
        }
    }
