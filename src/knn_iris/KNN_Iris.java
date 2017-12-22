/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knn_iris;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author yanir
 */
public class KNN_Iris {
    
    
    public static ArrayList<Iris> cjtoIris = new ArrayList<>(); // conjunto iris
    public static ArrayList<Iris> train = new ArrayList<>(); // lista de entrenamiento
    public static ArrayList<Iris> test = new ArrayList<>(); // lista de prueba
    public static ArrayList<ObjectDistance> distances = new ArrayList<>(); // 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        readFile();
        
        cjtoIris = disarray(cjtoIris);
        int indice;
        indice = (cjtoIris.size() - 15);
        
        for(int i = 0; i<indice; i++){
            train.add(cjtoIris.get(i));
        }
        for(int i = indice; i<cjtoIris.size(); i++){
            test.add(cjtoIris.get(i));
        }
        int count = 0;
        for(int i = 0; i<test.size(); i++){
            for(int j = 0; j<train.size(); j++){
                double distance = calculateDistance( test.get(i), train.get(j) );
                distances.add(new ObjectDistance( distance, train.get(j).getSpecies()) );
            }
            Collections.sort(distances);
            String prom = calculateSpecies(distances.get(0).getSpecies(), distances.get(1).getSpecies(), distances.get(2).getSpecies());
            distances = new ArrayList<>();
            if(test.get(i).getSpecies().equals(prom)){
                count++;
            }            
        }
        double result = (double) count/test.size() * 100;
        System.out.println("Efectividad = " + result + " %");
    }
    
    public static void readFile(){
        String fileToParse = "ConjuntoIris2.csv";
        BufferedReader fileReader;

        final String DELIMITER = ";";
        try
        {
            String line;         
            fileReader = new BufferedReader(new FileReader(fileToParse));
            fileReader.readLine(); 
            while ((line = fileReader.readLine()) != null) 
            {
                String[] tokens = line.split(DELIMITER);
                    double sepal_length = Double.parseDouble(tokens[0]);
                    double sepal_width = Double.parseDouble(tokens[1]);
                    double petal_length = Double.parseDouble(tokens[2]);
                    double petal_width = Double.parseDouble(tokens[3]);
                    String auxSpecies = (tokens[4]);
                    Iris iris = new Iris(sepal_length, sepal_width, petal_length, petal_width, auxSpecies);
                    cjtoIris.add(iris);
            }
        } 
        catch (IOException | NumberFormatException e) {} 
    }    
    
    private static ArrayList<Iris> disarray(ArrayList<Iris> cjtoIris) {
        int total = cjtoIris.size();
        Random r = new Random();
        Iris auxI;
        int index1 = r.nextInt(total);
        int index2 = r.nextInt(total);    
        for(int i=0; i<total; i++){
            do{
                index1 = r.nextInt(total);
                index2 = r.nextInt(total); 
            }while(index1 == index2);
            auxI = cjtoIris.get(index1);
            cjtoIris.set(index1, cjtoIris.get(index2));
            cjtoIris.set(index2, auxI);               
        }
        return cjtoIris;
    }    
    
    
        public static String calculateSpecies(String s1, String s2, String s3){

        if(s1.equals(s2) && s2.equals(s3)){
            return s1;
        }
        else if( (s1.equals(s2)) && (!s2.equals(s3))){
            return s1;
        }
        else if( (s2.equals(s3)) && (!s2.equals(s1))){
            return s2;
        }
        else if( (s1.equals(s3)) && (!s1.equals(s2))){
            return s1;
        }
              
        return (s1);
    }
    
    
    private static double calculateDistance(Iris flower1, Iris flower2)
    {
        double sepal_length = flower1.getSepal_length() - flower2.getSepal_length();
        double sepal_width = flower1.getSepal_width() - flower2.getSepal_width();
        double petal_length = flower1.getPetal_length() - flower2.getPetal_length();
        double petal_width = flower1.getPetal_width() - flower2.getPetal_width();

        double param1 = Math.pow(sepal_length, 2);
        double param2 = Math.pow(sepal_width, 2);
        double param3 = Math.pow(petal_length, 2);
        double param4 = Math.pow(petal_width, 2);
        
        return Math.sqrt(param1 + param2 + param3 + param4);
        
    }    
    
    
}
