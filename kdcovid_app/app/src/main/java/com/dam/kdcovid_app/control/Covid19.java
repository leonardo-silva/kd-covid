package com.dam.kdcovid_app.control;

/*
 * Arquivo: Covid19.java
 * Descrição: Triagem de respostas de possíveis casos suspeitos. O método de
 * Screening recebe como parâmetro um vetor booleano, correspondendo a Symptoms
 * (0-10), tempo dos Symptoms (11-15), comorbidades (16-22), comportamento de Risk
 * (23-26) e AgeRange etária (27 a 31). Para facilitar a identificação das posições
 * foram utilizadas enumerações: Symptoms, SymptomsDuration, Diseases, Last14Days e AgeRange.
 * A resposta também é dada utilizando o enumerador chamado Risk.
 */

public final class Covid19 {
    // Symptoms - 0 a 10
    public enum Symptoms {
        FEVER(0), SMELL_TASTE_LOSS(1), RUNNING_NOSE(2), TIREDNESS(3), COUGH(4), BREATH_PROBLEM(5),
        PURPLE_MOUTH(6), SORE_THROAT(7), CHEST_PRESSURE(8), DIARRHEA(9), NOA(10);

        private final int value;

        Symptoms(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    // SymptomsDuration - 11 a 15
    public enum SymptomsDuration {
        DAYS_1TO3(11), DAYS_4TO7(12), DAYS_8TO10(13), DAYS_11TO14(14), DAYS_14PLUS(15);

        private final int value;

        SymptomsDuration(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    // Diseases - 16 a 22
    public enum Diseases {
        DIABETES(16), HEART_PROBLEM(17), CHRONIC_KIDNEY(18), CHRONIC_RESPIRATORY(19),
        HIGH_PRESSURE(20), CANCER(21), PRIOR_DISEASE_DWA(22);

        private final int value;

        Diseases(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    // Last14Days - 23 a 26
    public enum Last14Days {
        WENT_OUT_OF_CITY(23), CONTACT_WITH_OUTSIDER(24), CONTACT_WITH_INFECTED(25), LAST14_DAYS_NOA(26);

        private final int value;

        Last14Days(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    // AgeRange - 27 a 31
    public enum AgeRange {
        YEARS_1TO15(27), YEARS_16TO30(28), YEARS_31TO45(29), YEARS_46TO60(30), YEARS_46PLUS(31);

        private final int value;

        AgeRange(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    // Risk
    // UNLIKELY (Output 1), LOW (Output 2), MEDIUM_1 (Output 3), MEDIUM_2 (Output 4),
    // HIGH_1 (Output 5), HIGH_2 (Output 6), VERY_HIGH (Output 7)
    public enum Risk {
        UNLIKELY(0), LOW(1), MEDIUM_1(2), MEDIUM_2(3), HIGH_1(4),
        HIGH_2(5), VERY_HIGH(6);

        private final int value;

        Risk(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private static int countSymptoms(boolean[] answers) {
        if (answers[Symptoms.NOA.getValue()]) {
            return 0;
        }

        int start = Symptoms.FEVER.getValue();
        int end = Symptoms.PURPLE_MOUTH.getValue();

        int i, conta = 0;
        for (i = start; i <= end; i++) {
            if (answers[i]) {
                conta++;
            }
        }
        return conta;
    }

    /*
        Método: Screening
        Descrição: retorna o Risk de contaminação associado às respostas de um
            indivíduo.
        Parâmetro:
            respostas -> vetor booleano contendo as respostas.
        Retorno:
            Risk -> value de Risk associado às respostas fornecidas.
    */
    public static Risk Screening(boolean[] answers) {
        if ((answers[Symptoms.NOA.getValue()] && answers[Last14Days.LAST14_DAYS_NOA.getValue()])) {
            // Output 1: bem ou sem Symptoms e sem comportamento de Risk
            return Risk.UNLIKELY;
        } else if (answers[Symptoms.NOA.getValue()]) {
            // Output 2: bem ou sem Symptoms e com comportamento de Risk
            return Risk.LOW;
        }

        int nSymptoms = countSymptoms(answers);

        // Output 7:
        if (answers[AgeRange.YEARS_46PLUS.getValue()] || !answers[Diseases.PRIOR_DISEASE_DWA.getValue()]) {
            // AgeRange de Risk ou Comorbidade
            if (nSymptoms > 2 && answers[Symptoms.FEVER.getValue()]) {
                // Mais de 2 Symptoms, sendo 1 deles FEVER
                return Risk.VERY_HIGH;
            } else if (!answers[Last14Days.LAST14_DAYS_NOA.getValue()]) {
                // Comportamento de Risk
                if (nSymptoms > 2 || ((nSymptoms == 2) && answers[Symptoms.FEVER.getValue()])) {
                    // Mais de dois Symptoms ou FEVER + outro sintoma
                    return Risk.VERY_HIGH;
                }
            }
        }

        // Output 6 e Output 4
        if (answers[SymptomsDuration.DAYS_8TO10.getValue()] || answers[SymptomsDuration.DAYS_11TO14.getValue()]) {
            // Meio do curso
            if (nSymptoms > 2 && answers[Symptoms.FEVER.getValue()]) {
                // Output 6: mais de 2 Symptoms, sendo 1 FEVER
                return Risk.HIGH_2;
            } else if (nSymptoms > 2 || ((nSymptoms == 2) && answers[Symptoms.FEVER.getValue()])) {
                // Mais de 2 Symptoms ou FEVER + outro sintoma
                if (answers[Last14Days.LAST14_DAYS_NOA.getValue()]) {
                    // Output 4: sem comportamento de Risk
                    return Risk.MEDIUM_2;
                } else {
                    // Output 6: com comportamento de Risk
                    return Risk.HIGH_2;
                }
            }
        } // Output 5 e Output 3
        else if (!answers[SymptomsDuration.DAYS_14PLUS.getValue()]) {
            // Meio do Curso
            if (nSymptoms > 2 && answers[Symptoms.FEVER.getValue()]) {
                // Output 5: mais de 2 Symptoms, sendo 1 FEVER
                return Risk.HIGH_1;
            } else if (nSymptoms > 2 || ((nSymptoms == 2) && answers[Symptoms.FEVER.getValue()])) {
                // Mais de 2 Symptoms ou FEVER + outro sintoma
                if (answers[Last14Days.LAST14_DAYS_NOA.getValue()]) {
                    // Output 3: sem comportamento de Risk
                    return Risk.MEDIUM_1;
                } else {
                    // Output 5: com comportamento de Risk
                    return Risk.HIGH_1;
                }
            }
        }
        // If no case matches, the default will be Risk.MEDIUM_1
        return Risk.MEDIUM_1;
    }
}
    

