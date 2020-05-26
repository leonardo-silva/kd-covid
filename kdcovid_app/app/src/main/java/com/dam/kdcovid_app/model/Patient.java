package com.dam.kdcovid_app.model;

import java.io.Serializable;

public class Patient implements Serializable {
    private boolean hasSymptom;
    private boolean hasFever;
    private boolean hasRunningNose;
    private boolean hasTiredness;
    private boolean hasCough;
    private boolean hasBreathProblem;
    private boolean hasPurpleMouth;
    private boolean hasSoreThroat;
    private boolean hasChestPressure;
    private boolean hasNOASymptom;

    public Patient(boolean hasSymptom) {
        this.hasSymptom = hasSymptom;
    }

    public boolean isHasSymptom() {
        return hasSymptom;
    }

    public void setHasSymptom(boolean hasSymptom) {
        this.hasSymptom = hasSymptom;
    }

    public boolean isHasFever() {
        return hasFever;
    }

    public void setHasFever(boolean hasFever) {
        this.hasFever = hasFever;
    }

    public boolean isHasRunningNose() {
        return hasRunningNose;
    }

    public void setHasRunningNose(boolean hasRunningNose) {
        this.hasRunningNose = hasRunningNose;
    }

    public boolean isHasTiredness() {
        return hasTiredness;
    }

    public void setHasTiredness(boolean hasTiredness) {
        this.hasTiredness = hasTiredness;
    }

    public boolean isHasCough() {
        return hasCough;
    }

    public void setHasCough(boolean hasCough) {
        this.hasCough = hasCough;
    }

    public boolean isHasBreathProblem() {
        return hasBreathProblem;
    }

    public void setHasBreathProblem(boolean hasBreathProblem) {
        this.hasBreathProblem = hasBreathProblem;
    }

    public boolean isHasPurpleMouth() {
        return hasPurpleMouth;
    }

    public void setHasPurpleMouth(boolean hasPurpleMouth) {
        this.hasPurpleMouth = hasPurpleMouth;
    }

    public boolean isHasSoreThroat() {
        return hasSoreThroat;
    }

    public void setHasSoreThroat(boolean hasSoreThroat) {
        this.hasSoreThroat = hasSoreThroat;
    }

    public boolean isHasChestPressure() {
        return hasChestPressure;
    }

    public void setHasChestPressure(boolean hasChestPressure) {
        this.hasChestPressure = hasChestPressure;
    }

    public boolean isHasNOASymptom() {
        return hasNOASymptom;
    }

    public void setHasNOASymptom(boolean hasNOASymptom) {
        this.hasNOASymptom = hasNOASymptom;
    }
}
