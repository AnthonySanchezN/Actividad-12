/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package addressbook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 *
 * @author Intel
 */
public class AddressBook {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, String> AgendaContactos = new HashMap<>();
        
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int decision = 0;
        load(AgendaContactos);
        while(!salir){
            System.out.println("Que opcion desea");
            System.out.println("1. Ver Agenda \n2. Crear Contacto \n3. Eliminar Contacto \n4. Salir");
            
            try{
                decision = sn.nextInt();
                
                switch(decision){
                    case 1: 
                        list(AgendaContactos);
                        break;
                        
                    case 2:
                        create(AgendaContactos);
                        break;
                        
                    case 3: 
                        delete(AgendaContactos);
                        break;
                        
                    case 4:
                        salir = true;
                        break;
                        
                    default:
                        System.out.println("opcion no valida");
                }
            }catch (InputMismatchException e){
                System.out.println("Debes inserta un numero dentro del 1 al 4");
                sn.nextInt();
            }
            
        }
    }
    //Método List: Mostrar contactos.
    public static void list(HashMap AgendaContactos){
        System.out.println("Agenda");
        for(Iterator<Entry<String, String>> entries = AgendaContactos.entrySet().iterator(); entries.hasNext();){
            Map.Entry<String, String> entry = entries.next();
            String output = String.format("%s:%s", entry.getKey(), entry.getValue());
            System.out.println(output);
        }
    }
    //Método Create: Crear un nuevo contacto
    public static void create(HashMap AgendaContactos){
        BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
        String phone = null;
        String name = null;
        System.out.println("Ingrese el numero de telefono: ");
        
        try{
           phone = leer.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
        
        System.out.println("Ingrese el nombre del contacto: ");
        try{
            name = leer.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
        
        if(phone != null && name != null)
            AgendaContactos.put(phone, name);
    }
    //Método Delete: Eliminar un nuevo contacto
    public static void delete(HashMap AgendaContactos){
        BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
        String phone = null;
        System.out.println("Ingrese el numero de telefono: ");
        
        try{
            phone = leer.readLine();
        } catch(IOException e) {
            e.printStackTrace();
        }
        AgendaContactos.remove(phone);
    }
    
    //Método Save: cargar los contactos del archivo
    public static void save(HashMap AgendaContactos) throws IOException{
        String outputFilename = "agenda.txt";
                
        BufferedWriter bufferedWriter = null;
        try{
            bufferedWriter = new BufferedWriter(new FileWriter(outputFilename));
            for(Iterator<Entry<String, String>> entries = AgendaContactos.entrySet().iterator(); entries.hasNext();){
                Map.Entry<String, String> entry = entries.next();
                String output = String.format("%s,%s", entry.getKey(), entry.getValue() + "\r\n");
                bufferedWriter.write(output);
            }
        }catch (IOException e){
            System.out.println("IOException catched while reading: " + e.getMessage());
        }finally{
            try{
                if(bufferedWriter != null){
                    bufferedWriter.close();
                }
            }catch (IOException e){
                System.out.println("IOEXception catched while closing: " + e.getMessage());
            }
        }
    }
    
    //Método Load: carga los contactos del archivo
    public static void load(HashMap AgendaContactos) throws FileNotFoundException{
        String inputFilename = "Escritorio/agenda.txt";
        BufferedReader bufferedReader = null;
        String phone = " ";
        String name = " ";
        try{
            bufferedReader = new BufferedReader(new FileReader(inputFilename));
            String line;
            
            while((line = bufferedReader.readLine()) != null){
                int coma = line.indexOf(',');
                phone = line.substring(0, coma);
                name = line.substring(coma+1, line.length());
                AgendaContactos.put(phone, name);
            }
        }catch(IOException e){
            System.out.println("IOException catched while reading: " + e.getMessage());
        }finally {
            try{
                if(bufferedReader != null){
                    bufferedReader.close();
                }
            }catch(IOException e){
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }
}
