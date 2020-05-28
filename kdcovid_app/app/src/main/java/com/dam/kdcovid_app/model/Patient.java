package com.dam.kdcovid_app.model;

import java.io.Serializable;

public class Patient implements Serializable {
    // Symptoms
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
    // Symptoms duration
    private boolean duration1to3Days;
    private boolean duration4to7Days;
    private boolean duration8to10Days;
    private boolean duration11to14Days;
    private boolean duration14PlusDays;
    // Last 14 days activities
    private boolean wentOutOfCity;
    private boolean hadContactWithOutsider;
    private boolean hadContactWithInfected;
    private boolean hadLast14DaysNOA;

    public Patient(boolean hasSymptom) {
        this.hasSymptom = hasSymptom;
    }

    public boolean getWentOutOfCity() {
        return wentOutOfCity;
    }

    public void setWentOutOfCity(boolean wentOutOfCity) {
        this.wentOutOfCity = wentOutOfCity;
    }

    public boolean getHadContactWithOutsider() {
        return hadContactWithOutsider;
    }

    public void setHadContactWithOutsider(boolean hadContactWithOutsider) {
        this.hadContactWithOutsider = hadContactWithOutsider;
    }

    public boolean getHadContactWithInfected() {
        return hadContactWithInfected;
    }

    public void setHadContactWithInfected(boolean hadContactWithInfected) {
        this.hadContactWithInfected = hadContactWithInfected;
    }

    public boolean getHadLast14DaysNOA() {
        return hadLast14DaysNOA;
    }

    public void setHadLast14DaysNOA(boolean hadLast14DaysNOA) {
        this.hadLast14DaysNOA = hadLast14DaysNOA;
    }

    public boolean isDuration1to3Days() {
        return duration1to3Days;
    }

    public void setDuration1to3Days(boolean duration1to3Days) {
        this.duration1to3Days = duration1to3Days;
    }

    public boolean isDuration4to7Days() {
        return duration4to7Days;
    }

    public void setDuration4to7Days(boolean duration4to7Days) {
        this.duration4to7Days = duration4to7Days;
    }

    public boolean isDuration8to10Days() {
        return duration8to10Days;
    }

    public void setDuration8to10Days(boolean duration8to10Days) {
        this.duration8to10Days = duration8to10Days;
    }

    public boolean isDuration11to14Days() {
        return duration11to14Days;
    }

    public void setDuration11to14Days(boolean duration11to14Days) {
        this.duration11to14Days = duration11to14Days;
    }

    public boolean isDuration14PlusDays() {
        return duration14PlusDays;
    }

    public void setDuration14PlusDays(boolean duration14PlusDays) {
        this.duration14PlusDays = duration14PlusDays;
    }

    public boolean getHasSymptom() {
        return hasSymptom;
    }

    public void setHasSymptom(boolean hasSymptom) {
        this.hasSymptom = hasSymptom;
    }

    public boolean getHasFever() {
        return hasFever;
    }

    public void setHasFever(boolean hasFever) {
        this.hasFever = hasFever;
    }

    public boolean getHasRunningNose() {
        return hasRunningNose;
    }

    public void setHasRunningNose(boolean hasRunningNose) {
        this.hasRunningNose = hasRunningNose;
    }

    public boolean getHasTiredness() {
        return hasTiredness;
    }

    public void setHasTiredness(boolean hasTiredness) {
        this.hasTiredness = hasTiredness;
    }

    public boolean getHasCough() {
        return hasCough;
    }

    public void setHasCough(boolean hasCough) {
        this.hasCough = hasCough;
    }

    public boolean getHasBreathProblem() {
        return hasBreathProblem;
    }

    public void setHasBreathProblem(boolean hasBreathProblem) {
        this.hasBreathProblem = hasBreathProblem;
    }

    public boolean getHasPurpleMouth() {
        return hasPurpleMouth;
    }

    public void setHasPurpleMouth(boolean hasPurpleMouth) {
        this.hasPurpleMouth = hasPurpleMouth;
    }

    public boolean getHasSoreThroat() {
        return hasSoreThroat;
    }

    public void setHasSoreThroat(boolean hasSoreThroat) {
        this.hasSoreThroat = hasSoreThroat;
    }

    public boolean getHasChestPressure() {
        return hasChestPressure;
    }

    public void setHasChestPressure(boolean hasChestPressure) {
        this.hasChestPressure = hasChestPressure;
    }

    public boolean getHasNOASymptom() {
        return hasNOASymptom;
    }

    public void setHasNOASymptom(boolean hasNOASymptom) {
        this.hasNOASymptom = hasNOASymptom;
    }
}
