package com.kylecorry.bb1102.trash;

public class Classification {

    private double probability;
    private String name;

    public Classification(String name, double probability) {
        this.probability = probability;
        this.name = name;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s: %.3f", name, probability);
    }
}
