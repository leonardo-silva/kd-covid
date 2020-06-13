package com.dam.kdcovid_app.model;

import java.io.Serializable;

public class Patient implements Serializable {
    // Symptoms
    private boolean hasSymptom;
    private boolean hasFever;
    private boolean hasSmellTasteLoss;
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
    // Age Range
    private boolean age1to15Years;
    private boolean age16to30Years;
    private boolean age31to45Years;
    private boolean age46to60Years;
    private boolean age60PlusYears;
    // Prior Diseases
    private boolean hasDiabetes;
    private boolean hasHeartProblem;
    private boolean hasChronicKidney;
    private boolean hasChronicRespiratory;
    private boolean hasHighPressure;
    private boolean hasCancer;
    private boolean dontHavePriorDisease;
    private boolean priorDiseasesDWA;
    // Gender
    private boolean male;
    private boolean female;
    private boolean otherGender;
    // City
    private boolean citySalinas;
    private boolean cityAracuai;
    private boolean cityTaiobeiras;
    private boolean cityCoronelMurta;
    private boolean citySaoJoaoDoParaiso;
    private boolean cityJanauba;
    private boolean cityPorteirinha;
    private boolean cityMontesClaros;
    private boolean otherCity;
    // Neighborhood
    private String neighborhoodName;
    // Zip Code
    private String zipCode;
    // Full Name
    private boolean fullNameDWA; // DWA = Dont Want to Answer
    private String fullName;
    // Phone and Email
    private String phone;
    private String email;
    // Android ID
    private String android_id;

    public Patient(boolean hasSymptom) {
        this.hasSymptom = hasSymptom;
    }

    public boolean[] generateCovid19Input() {
        boolean[] answers = {
                // Symptoms - 0 a 8
                this.getHasFever(),
                this.getHasSmellTasteLoss(),
                this.getHasRunningNose(),
                this.getHasTiredness(),
                this.getHasCough(),
                this.getHasBreathProblem(),
                this.getHasPurpleMouth(),
                this.getHasSoreThroat(),
                this.getHasChestPressure(),
                this.getHasNOASymptom() || !this.getHasSymptom(),
                // SymptomsDuration - 9 a 13
                this.isDuration1to3Days(),
                this.isDuration4to7Days(),
                this.isDuration8to10Days(),
                this.isDuration11to14Days(),
                this.isDuration14PlusDays(),
                // Prior Diseases - 14 a 20
                this.hasDiabetes(),
                this.hasHeartProblem(),
                this.hasChronicKidney(),
                this.hasChronicRespiratory(),
                this.hasHighPressure(),
                this.hasCancer(),
                this.getDontHavePriorDisease() || this.getPriorDiseasesDWA(),
                // Last14Days - 21 a 24
                this.getWentOutOfCity(),
                this.getHadContactWithOutsider(),
                this.getHadContactWithInfected(),
                this.getHadLast14DaysNOA(),
                // Age range
                this.isAge1to15Years(),
                this.isAge16to30Years(),
                this.isAge31to45Years(),
                this.isAge46to60Years(),
                this.isAge60PlusYears()
        };

        return answers;
    }

    public String getAndroid_id() {
        return android_id;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public boolean getFullNameDWA() {
        return fullNameDWA;
    }

    public void setFullNameDWA(boolean fullNameDWA) {
        this.fullNameDWA = fullNameDWA;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNeighborhoodName() {
        return neighborhoodName;
    }

    public void setNeighborhoodName(String neighborhoodName) {
        this.neighborhoodName = neighborhoodName;
    }

    public boolean isCitySalinas() {
        return citySalinas;
    }

    public void setCitySalinas(boolean citySalinas) {
        this.citySalinas = citySalinas;
    }

    public boolean isCityAracuai() {
        return cityAracuai;
    }

    public void setCityAracuai(boolean cityAracuai) {
        this.cityAracuai = cityAracuai;
    }

    public boolean isCityTaiobeiras() {
        return cityTaiobeiras;
    }

    public void setCityTaiobeiras(boolean cityTaiobeiras) {
        this.cityTaiobeiras = cityTaiobeiras;
    }

    public boolean isCityCoronelMurta() {
        return cityCoronelMurta;
    }

    public void setCityCoronelMurta(boolean cityCoronelMurta) {
        this.cityCoronelMurta = cityCoronelMurta;
    }

    public boolean isCitySaoJoaoDoParaiso() {
        return citySaoJoaoDoParaiso;
    }

    public void setCitySaoJoaoDoParaiso(boolean citySaoJoaoDoParaiso) {
        this.citySaoJoaoDoParaiso = citySaoJoaoDoParaiso;
    }

    public boolean isCityJanauba() {
        return cityJanauba;
    }

    public void setCityJanauba(boolean cityJanauba) {
        this.cityJanauba = cityJanauba;
    }

    public boolean isCityPorteirinha() {
        return cityPorteirinha;
    }

    public void setCityPorteirinha(boolean cityPorteirinha) {
        this.cityPorteirinha = cityPorteirinha;
    }

    public boolean isCityMontesClaros() {
        return cityMontesClaros;
    }

    public void setCityMontesClaros(boolean cityMontesClaros) {
        this.cityMontesClaros = cityMontesClaros;
    }

    public boolean isOtherCity() {
        return otherCity;
    }

    public void setOtherCity(boolean otherCity) {
        this.otherCity = otherCity;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public boolean isFemale() {
        return female;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }

    public boolean isOtherGender() {
        return otherGender;
    }

    public void setOtherGender(boolean otherGender) {
        this.otherGender = otherGender;
    }

    public boolean hasDiabetes() {
        return hasDiabetes;
    }

    public void setHasDiabetes(boolean hasDiabetes) {
        this.hasDiabetes = hasDiabetes;
    }

    public boolean hasHeartProblem() {
        return hasHeartProblem;
    }

    public void setHasHeartProblem(boolean hasHeartProblem) {
        this.hasHeartProblem = hasHeartProblem;
    }

    public boolean hasChronicKidney() {
        return hasChronicKidney;
    }

    public void setHasChronicKidney(boolean hasChronicKidney) {
        this.hasChronicKidney = hasChronicKidney;
    }

    public boolean hasChronicRespiratory() {
        return hasChronicRespiratory;
    }

    public void setHasChronicRespiratory(boolean hasChronicRespiratory) {
        this.hasChronicRespiratory = hasChronicRespiratory;
    }

    public boolean hasHighPressure() {
        return hasHighPressure;
    }

    public void setHasHighPressure(boolean hasHighPressure) {
        this.hasHighPressure = hasHighPressure;
    }

    public boolean hasCancer() {
        return hasCancer;
    }

    public void setHasCancer(boolean hasCancer) {
        this.hasCancer = hasCancer;
    }

    public boolean getDontHavePriorDisease() {
        return dontHavePriorDisease;
    }

    public void setDontHavePriorDisease(boolean dontHavePriorDisease) {
        this.dontHavePriorDisease = dontHavePriorDisease;
    }

    public boolean getPriorDiseasesDWA() {
        return priorDiseasesDWA;
    }

    public void setPriorDiseasesDWA(boolean priorDiseasesDWA) {
        this.priorDiseasesDWA = priorDiseasesDWA;
    }

    public boolean isAge1to15Years() {
        return age1to15Years;
    }

    public void setAge1to15Years(boolean age1to15Years) {
        this.age1to15Years = age1to15Years;
    }

    public boolean isAge16to30Years() {
        return age16to30Years;
    }

    public void setAge16to30Years(boolean age16to30Years) {
        this.age16to30Years = age16to30Years;
    }

    public boolean isAge31to45Years() {
        return age31to45Years;
    }

    public void setAge31to45Years(boolean age31to45Years) {
        this.age31to45Years = age31to45Years;
    }

    public boolean isAge46to60Years() {
        return age46to60Years;
    }

    public void setAge46to60Years(boolean age46to60Years) {
        this.age46to60Years = age46to60Years;
    }

    public boolean isAge60PlusYears() {
        return age60PlusYears;
    }

    public void setAge60PlusYears(boolean age60PlusYears) {
        this.age60PlusYears = age60PlusYears;
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

    public boolean getHasSmellTasteLoss() {
        return hasSmellTasteLoss;
    }

    public void setHasSmellTasteLoss(boolean hasSmellTasteLoss) {
        this.hasSmellTasteLoss = hasSmellTasteLoss;
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
