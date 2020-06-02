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
    private ArrayList<String> noTerminalesAnulables;
    private Object[][] primeros;
    private Object[][] primerosProduccion;
    private Object[][] siguientes;
    private Object[][] seleccion;
    private Object[][] gramatica;
    private int longitud;
    
    public Gramatica(int longitud){
        this.longitud=longitud;
        this.noTerminal=new ArrayList<>();
        this.terminal=new ArrayList<>();
        this.noTerminalesAnulables =new ArrayList<>();
    }

    public ArrayList<String> getTerminal() {
        return terminal;
    }

    public void setTerminal(ArrayList<String> terminal) {
        this.terminal = terminal;
    }

    public ArrayList<String> getNoTerminal() {
        return noTerminal;
    }

    public void setNoTerminal(ArrayList<String> noTerminal) {
        this.noTerminal = noTerminal;
    }

    public Object[][] getGramatica() {
        return gramatica;
    }

    public void setGramatica(Object[][] gramatica) {
        this.gramatica = gramatica;
    }

    public ArrayList<String> getNoTerminalesAnulables() {
        return noTerminalesAnulables;
    }

    public void setNoTerminalesAnulables(ArrayList<String> noTerminalesAnulables) {
        this.noTerminalesAnulables = noTerminalesAnulables;
    }

    public Object[][] getPrimeros() {
        return primeros;
    }

    public void setPrimeros(Object[][] primeros) {
        this.primeros = primeros;
    }

    public Object[][] getSiguientes() {
        return siguientes;
    }

    public void setSiguientes(Object[][] siguientes) {
        this.siguientes = siguientes;
    }

    public Object[][] getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(Object[][] seleccion) {
        this.seleccion = seleccion;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
    
    
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
    public ArrayList primeroDe(String dato){
        String dato1;
        ArrayList<String> mensaje = new ArrayList<>();
        char simbolo=' ';
        int indice=0, tamano=0;
        boolean bandera=true;
        ArrayList entrada=null, mensaje2=null;
        for(int i=0;i<longitud;i++){
            if(gramatica[i][0].equals(dato)){
               entrada=(ArrayList) gramatica[i][1];
               tamano=entrada.size();
               bandera=true;
               indice=0;
               while(bandera && indice<tamano){
                   dato1=(String)entrada.get(indice);
                   simbolo=dato1.charAt(0);
                   if(Character.isLowerCase(simbolo)){
                        if (esRepetido(mensaje,dato1)){
                            mensaje.add(dato1);   
                        }
                        bandera=false;
                    }else if(!dato1.equals(gramatica[i][0])){                     
                       if(this.esAnulable(dato1)){
                           mensaje2=this.primeroDe(dato1);
                           this.unirDatos(mensaje, mensaje2);
                       }else{
                           mensaje2=this.primeroDe(dato1);
                           this.unirDatos(mensaje, mensaje2);
                           bandera=false;
                       }
                   }
                   indice++;
               }
            }
        }
        return mensaje;
    }
    public void unirDatos(ArrayList<String>dato,ArrayList<String>dato1){
        int tamano=dato1.size(),indice=0;
        while(indice<tamano){
            if(esRepetido(dato,dato1.get(indice))){
                dato.add(dato1.get(indice));
            }
            indice++;
        }
    }
    public boolean esRepetido(ArrayList<String> datos, String dato){
        int tamaño=datos.size(),i=0;
        String entrada="";
        while(i<tamaño){
            entrada=datos.get(i);
            if(entrada.equals(dato)){
                return false;
            }
            i++;
        }
        
        return true;
    }
    public void terminales(){
        ArrayList<String>dato=null;
        String entrada="";
        char simbolo=' ';
        int tamano=0,indice=0;
        for(int i=0;i<longitud;i++){
            dato=(ArrayList)gramatica[i][1];
            tamano=dato.size();
            indice=0;
            while(indice<tamano){
                entrada=dato.get(indice);
                simbolo=entrada.charAt(0);
                if(Character.isLowerCase(simbolo)){
                    if(this.esRepetido(terminal, entrada)){
                        terminal.add(entrada);
                    }
                }
                indice++;
            }
            
        }
    }
    public void noTerminales(){
        String dato="";
        for(int i=0;i<longitud;i++){
            dato=(String)gramatica[i][0];
            if(this.esRepetido(noTerminal, dato)){
                noTerminal.add(dato);
            }
            
        }
    }
    public void terminalesAnulables(){
        ArrayList<String>dato=null;
        String entrada="";
        int tamano=0,indice=0;
        
        /**for(int i=0;i<longitud;i++){
            dato=(ArrayList<String>) gramatica[i][1];
            if(dato.get(0).equals("#")){
                if(this.esRepetido(noTerminalesAnulables,(String)gramatica[i][0])){
                    noTerminalesAnulables.add((String)gramatica[i][0]);
                }
            }
        }**/
        boolean bandera=true;
        for(int i=0;i<longitud;i++){
            dato=(ArrayList)gramatica[i][1];
            if(sonTerminales(dato)){
                tamano=dato.size();
                indice=0;
                bandera=true;
                while(bandera && indice<tamano){
                    entrada=dato.get(indice);
                    if(!this.esAnulable(entrada)){
                        bandera=false;
                    }
                    indice++;
                }
                if(tamano==indice){
                    noTerminalesAnulables.add((String)gramatica[i][0]);
                }
            }
        }
    }
    public boolean esAnulable(String dato){
        ArrayList<String> ladoDerecho=null;
        int tamano=0,indice=0;
        String datos="";
        char simbolo=' ';
        boolean bandera=true;
        for(int i=0;i<longitud;i++){
            if(gramatica[i][0].equals(dato)){
                ladoDerecho=(ArrayList<String>) gramatica[i][1];
                tamano=ladoDerecho.size();
                indice=0;
                bandera=true;
                while(bandera && indice<tamano){
                    if(ladoDerecho.get(0).equals("#")){
                        return true;
                    }else{
                        datos=ladoDerecho.get(indice);
                        simbolo=datos.charAt(0);
                        if(Character.isUpperCase(simbolo)){
                            if(!esAnulable(datos)){
                                return false;
                            }
                        }else{
                            bandera= false;
                        }
                    }
                    indice ++;
                }
            }
        }
        return true;
    }
    public boolean sonTerminales(ArrayList<String>datos){
        String entrada="";
        char simbolo=' ';
        int tamano=datos.size(),indice=0;
        while(indice<tamano){
            entrada=datos.get(indice);
            simbolo=entrada.charAt(0);
            if(Character.isLowerCase(simbolo)){
               return false; 
            }
            indice++;
        }
        return true;
    }
    public void primeros(){
        primeros=new Object[noTerminal.size()][2];
        int tamano=noTerminal.size(), indice=0;
        while(indice<tamano){
            primeros[indice][0]=noTerminal.get(indice);
            primeros[indice][1]=this.primeroDe(noTerminal.get(indice));
            indice++;                   
        }
    }
    public void primerosPorPoduccion(){
        primerosProduccion=new Object[longitud][2];
        ArrayList<String> datos=null,entrada2=null;
        int tamano=0,indice=0;
        boolean bandera=true;
        String salida="";
        char simbolo=' ';
        for(int i=0;i<longitud;i++){
            primerosProduccion[i][0]=gramatica[i][0];
            datos=(ArrayList<String>)gramatica[i][1];
            tamano=datos.size();
            indice=0;
            ArrayList<String>entrada=new ArrayList<>();
            bandera=true;
            while(indice<tamano && bandera){
                salida=datos.get(indice);
                simbolo=salida.charAt(0);
                if(Character.isLowerCase(simbolo)){
                    entrada.add(salida);
                    bandera=false;
                }else if(!"#".equals(salida)){
                    if(this.esAnulable(salida)){
                        entrada2=seleccionarPrimeros(salida);
                        this.unirDatos(entrada, entrada2);
                    }else{
                        entrada2=seleccionarPrimeros(salida);
                        this.unirDatos(entrada, entrada2);
                        bandera=false;
                    }
                }
                indice++;
            }
            primerosProduccion[i][1]=entrada;
        }
    }
    public ArrayList<String> seleccionarPrimeros(String dato){
        ArrayList<String> datos=null;
        for(int i=0;i<noTerminal.size();i++){
            if(primeros[i][0].equals(dato)){
                datos=(ArrayList<String>) primeros[i][1];
            }
        }
        return datos;
    }
    public ArrayList siguientesDe(String dato){
        String dato1="";
        ArrayList<String> mensaje = new ArrayList<>();
        char simbolo=' ';
        int indice=0, tamano=0;
        boolean bandera=true;
        ArrayList<String> entrada=null, mensaje2=new ArrayList<String>();
        if(dato.equals(gramatica[0][0])){
                mensaje.add("|");
        }
        for(int i=0;i<longitud;i++){
            entrada=(ArrayList) gramatica[i][1];
            tamano=entrada.size();
            indice=0;
            bandera=true;
            while(indice<tamano && bandera){
                if(dato.equals(entrada.get(indice))){
                    if(indice==(tamano-1) && !dato.equals(gramatica[i][0])){
                        mensaje2=this.siguientesDe((String)gramatica[i][0]);
                        this.unirDatos(mensaje, mensaje2);
                        bandera=false;
                    }else{
                        indice++;
                        if(indice<tamano){
                        dato1=entrada.get(indice);
                        simbolo=dato1.charAt(0);
                        if(Character.isLowerCase(simbolo)){
                            if (esRepetido(mensaje,dato1)){
                                  mensaje.add(dato1);   
                            }
                            bandera=false;
                        }else{
                            if(this.esAnulable(dato1)){
                                mensaje2=this.seleccionarPrimeros(dato1);
                                this.unirDatos(mensaje, mensaje2);
                            }else{
                                mensaje2=this.seleccionarPrimeros(dato1);
                                this.unirDatos(mensaje, mensaje2);
                                bandera=false;
                            }
                        }
                    }
                  }
                }
                indice++;
            }
        }
        return mensaje;
    }
    public void siguientes(){
        siguientes=new Object[noTerminal.size()][2];
        int tamano=noTerminal.size(), indice=0;
        while(indice<tamano){
            siguientes[indice][0]=noTerminal.get(indice);
            siguientes[indice][1]=this.siguientesDe(noTerminal.get(indice));
            indice++;                   
        }
    }
    public ArrayList<String> seleccionarSiguientes(String dato){
        ArrayList<String> datos=null;
        for(int i=0;i<noTerminal.size();i++){
            if(siguientes[i][0].equals(dato)){
                datos=(ArrayList<String>) siguientes[i][1];
            }
        }
        return datos;
    }
    public void seleccion(){
        seleccion=new Object[longitud][2];
        ArrayList<String> datos=null;
        for(int i=0;i<longitud;i++){
            seleccion[i][0]=primerosProduccion[i][0];
            datos=(ArrayList<String>) primerosProduccion[i][1];
            if(datos.size()==0){
                seleccion[i][1]=seleccionarSiguientes((String)seleccion[i][0]);
                this.unirDatos((ArrayList<String>) seleccion[i][1], (ArrayList<String>) primerosProduccion[i][1]);
            }else{
                seleccion[i][1]=primerosProduccion[i][1];
            }
             
        }
    }
    
}
