package org.example;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class ChessFitnessFunction extends FitnessFunction {
    @Override
    protected double evaluate(IChromosome a_subject) {
        int nonAttackingQueens = 0;
        int totalPairs = 0;

        // Verificăm fiecare pereche posibilă de regine
        for (int i = 0; i < 7; i++) {
            for (int j = i + 1; j < 8; j++) {
                int row1 = i;
                int col1 = (Integer) a_subject.getGene(i).getAllele();
                int row2 = j;
                int col2 = (Integer) a_subject.getGene(j).getAllele();

                // Verificăm dacă cele două regine se atacă
                if (row1 == row2 || col1 == col2 || Math.abs(row1 - row2) == Math.abs(col1 - col2)) {
                    // Aceste două regine se atacă reciproc
                    totalPairs++;
                }
            }
        }

        // Numărul total de perechi de regine care nu se atacă reciproc
        nonAttackingQueens = 28 - totalPairs;

        // Fitness-ul este numărul de perechi de regine care nu se atacă reciproc
        return nonAttackingQueens;

    }
}
