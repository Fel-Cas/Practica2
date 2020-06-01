/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2teoria;

import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class Gramatica {
    private ArrayList<String> terminal;
    private ArrayList<String> noTerminal;
    private ArrayList<Object> primeros;
    private ArrayList<String> siguientes;
    private ArrayList<String> seleccion;
    private ArrayList<String> ladoDerecho;
    private ArrayList<String> ladoIzquierdo;
    
    public ArrayList primerosProduccion(){
        for(int i=0; i<ladoDerecho.size(); i++){
            String simbolo= ladoDerecho.get(0);
            char esTerminal = simbolo.charAt(0);
            if(Character.isLowerCase(esTerminal)){
                this.primeros.set(i, esTerminal);
            }
        }
        return primeros;
    }
    
    /*public boolean primerosDisyuntos(String dato){
        for(int i=0, i<terminal.size(); i++){ 
            
        }
    }*/
    
    public boolean esS(){
        for(int i=0; i<ladoDerecho.size(); i++){
            String simbolo= ladoDerecho.get(0);
            char esTerminal = simbolo.charAt(0);
            if(!Character.isLowerCase(esTerminal)){
                return false;
            }
        }
        return true;
    }
    
    
}
