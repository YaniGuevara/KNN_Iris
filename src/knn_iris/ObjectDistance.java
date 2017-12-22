/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knn_iris;

/**
 *
 * @author yanir
 */
public class ObjectDistance implements Comparable<ObjectDistance>{

    private double distance;
    private String species;
    
    public ObjectDistance(double distance, String species) {
        this.distance = distance;
        this.species = species;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistancia(double distance) {
        this.distance = distance;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }    
    /**
     * Si la distancia es igual a la del argumento retorna 0
     * Si la distancia es menor que la del argumento retorna -1
     * Si la distancia es mas grande que la del argumento retorna 1
     * @param obj objeto a comparar
     * @return -1, 0, 1
     */
    @Override
    public int compareTo(ObjectDistance obj) {
        
        if(this.distance > obj.getDistance()){
            return 1;
        }
        else if(this.distance == obj.getDistance()){
            return 0;
        }
        else{
            return -1;
        }
    }
    
}
