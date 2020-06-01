/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2teoria;

import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author andres
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private javafx.scene.control.Button button,aceptar,terminar;
    @FXML
    private TextField hilera,hilera2,hilera3,hilera4;
    private Stage stage=new Stage();
    @FXML
    Button terminal,noTerminal, secuenciaNula, ingresar,mostrarGramatica,mostrarAutomata;
    static private int j=0,longitud=0,contador=0;
    static private Object[][] gramatica;
    static private ArrayList<String> ladoDerecho;
    @FXML
    TextArea textoGramatica,textoAutomata;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    /**
     * Método encargado de manejar la interfaz gráfica dónde se le pide al usuario la longitud de la gramática,
     * además este es el encargado de inicializar la matriz en la que se guarda la gramática.
     **/
     public void entrarLongitud () throws IOException{
        String entrada=hilera4.getText();
        if(!"".equals(entrada)){
            System.out.println(entrada);
            longitud=Integer.parseInt(entrada);
            gramatica=new Object[longitud][2];
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            Stage stage =(Stage) aceptar.getScene().getWindow();
            stage.hide();
        }else{
            JOptionPane.showMessageDialog(null, "Debe ingresar un numero para poder continuar");
        }
     }
    /**
     * Método utilizado para que solo entrén datos númericos en el campo de texto 
     * en el cuál se le pide al usuario la longitud de la gramática.
     */
    public void soloNumeros(javafx.scene.input.KeyEvent keyEvent){
        char car = keyEvent.getCharacter().charAt(0);
        if ((car < '0' || car > '9') ) {
                    keyEvent.consume();
                }
    }
     /***
     * Método encargado de pedirle a usuario el contenido del no terminal del lado izquierdo de la gramática 
     * y ponerlo en la matriz.
     **/
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        String entrada=hilera.getText();
        System.out.println(entrada);
        if(!"".equals(entrada)){
            String entradaMayuscula=entrada.toUpperCase();
            gramatica[contador][0]=entradaMayuscula;
            ladoDerecho=new ArrayList<String>();
            System.out.println(entradaMayuscula);
            Parent root = FXMLLoader.load(getClass().getResource("Gramatica.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            Stage stage =(Stage) button.getScene().getWindow();
            stage.hide();
        }else{
            JOptionPane.showMessageDialog(null, "Debe ingresar una letra para poder continuar");
        }
        
    }
     /***
     * Método encargado de que sólo se entre un carácter cuando se va ingresar 
     * un no terminal en el lado izquierdo de la gramática.
     **/
    public void  noTerminalIzquierda (javafx.scene.input.KeyEvent keyEvent) {
        char car = keyEvent.getCharacter().charAt(0);
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') ) {
            keyEvent.consume();
        }
        if(hilera.getText().length()>=1 ){ 
                    keyEvent.consume();
        } 
    }
     /***
     * Método encargado de que sólo se entre un carácter cuando se va ingresar 
     * un no terminal en el lado derecho de la gramática.
     **/
    public void  noTerminalDerecha (javafx.scene.input.KeyEvent keyEvent) {
        char car = keyEvent.getCharacter().charAt(0);
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') ) {
            keyEvent.consume();
        }
        if(hilera2.getText().length()>=1 ){ 
                    keyEvent.consume();
        } 
    }
     /***
     *Método encargado de que el usuario sólo pueda ingresar un carácter a la hora de entrar 
     * un terminal en el lado derecho.
     **/
    public void soloUnCaracter (javafx.scene.input.KeyEvent keyEvent) {
        char car = keyEvent.getCharacter().charAt(0);           
        if(hilera3.getText().length()>=1){ 
                    keyEvent.consume();
        }          
         
    }
     /***
     * Método encargado de habilitar el campo para ingresar el terminal, además habilita el botón de ingresar.
     **/
    public void ingresoTerminal(){
        hilera3.setVisible(true);
        terminal.setDisable(true);
        noTerminal.setDisable(true);
        secuenciaNula.setDisable(true);
        ingresar.setDisable(false);
        terminar.setDisable(true);
        j=1;
    }
    /***
     * Método encargado de habilitar el campo para ingresar el no  terminal, además habilita el botón de ingresar.
     **/
    public void ingresoNoTerminal(){
        hilera2.setVisible(true);
        terminal.setDisable(true);
        noTerminal.setDisable(true);
        secuenciaNula.setDisable(true);
        ingresar.setDisable(false);
        terminar.setDisable(true);
        j=2;
    }
    /**
     * Método encargado de agregar el no terminal o el terminal a un ArrayList.
     **/
    public void ingresar(){
        String entrada="";
        if(j==1){
            entrada=hilera3.getText();
            ladoDerecho.add(entrada.toLowerCase());
            System.out.println(entrada.toLowerCase());
            hilera3.setVisible(false);
            terminal.setDisable(false);
            noTerminal.setDisable(false);
            ingresar.setDisable(true);
        }else{
            entrada=hilera2.getText();
            ladoDerecho.add(entrada.toUpperCase());
            System.out.println(entrada.toUpperCase());
            hilera2.setVisible(false);
            terminal.setDisable(false);
            noTerminal.setDisable(false);
            ingresar.setDisable(true);
        }
        terminar.setDisable(false);
        
    }
    /**
     * Método encargado de agregar el ArrayList en la columna correspondiente.
     **/
    public void terminarGramatica() throws IOException{
        if(contador==(longitud-1)){
            gramatica[contador][1]=ladoDerecho;
            System.out.println(mostrar());
            Parent root = FXMLLoader.load(getClass().getResource("AnalisisGramatica.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            Stage stage =(Stage) terminar.getScene().getWindow();
            stage.hide();
        }else{
            gramatica[contador][1]=ladoDerecho;
            contador++;
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            Stage stage =(Stage) terminar.getScene().getWindow();
            stage.hide();
        }
    }
    /**
     * Método encargado de agregar el simbolo de secuencia nula (#)  a un ArrayList.
     **/
    public void secuenciaNula(){
        ladoDerecho.add("#");
        terminal.setDisable(true);
        noTerminal.setDisable(true);
        secuenciaNula.setDisable(true);
        terminar.setDisable(false);
    }
    /**
     * Método encargado de recorrer la matiriz y devolver un String con los datos entrados.
     **/
    public String mostrar(){
        ArrayList<String>datos=null;
        String letra="";
        String mensaje="";
        for(int i=0;i<longitud;i++){
            for(int j=0;j<2;j++){
                if(j==0){
                    mensaje+="<"+gramatica[i][j] +">"+" ---------->";
                }else{
                    datos=(ArrayList<String>) gramatica[i][j];
                    int k=0;
                    while(k<datos.size()){
                        letra=datos.get(k);
                        char simbolo=letra.charAt(0);
                        if(Character.isUpperCase(simbolo)){
                            mensaje+="<"+letra+">";
                        }else{
                            mensaje+=letra;
                        }
                        k++;
                    }
                }
            }
            mensaje+="\n";
        }
        return mensaje;
    }
    /**
     * Método encargado de mostrar l gramatica en un textbox.
     */
    public void mostrarGramaticaCompleta(){
        textoGramatica.setText(mostrar());
    }
    
}
