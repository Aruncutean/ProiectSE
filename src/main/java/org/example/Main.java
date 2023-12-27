package org.example;


import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.DoubleGene;
import org.jgap.impl.IntegerGene;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InvalidConfigurationException {
        Configuration conf = new DefaultConfiguration();
        FitnessFunction myFunc = new ChessFitnessFunction();
        conf.setFitnessFunction(myFunc);

        Gene[] sampleGenes = new Gene[8]; // 8 regine
        for (int i = 0; i < sampleGenes.length; i++) {
            sampleGenes[i] = new IntegerGene(conf, 0, 7); // Poziții de la 0 la 7
        }

        IChromosome sampleChromosome = new Chromosome(conf, sampleGenes);
        conf.setSampleChromosome(sampleChromosome);
        conf.setPopulationSize(50); // Mărimea populației

        Genotype population = Genotype.randomInitialGenotype(conf);

        // Rularea algoritmului pentru un număr definit de cicluri
        for (int i = 0; i < 100; i++) {

            population.evolve();
            IChromosome solution = population.getFittestChromosome();
            System.out.println("Fitness: " + solution.getFitnessValue() );

            printSolution(solution);

        }

        // Afișarea celei mai bune soluții găsite
        IChromosome bestSolution = population.getFittestChromosome();
        System.out.println("The best solution has a fitness value of " + bestSolution.getFitnessValue());
    }

    public static void printSolution(IChromosome chromosome) {
        int[] positions = new int[8];
        for (int i = 0; i < 8; i++) {
            positions[i] = (Integer) chromosome.getGene(i).getAllele();
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (positions[i] == j) {
                    System.out.print("Q "); // Q reprezintă o regină
                } else {
                    System.out.print(". "); // . reprezintă un spațiu liber
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}