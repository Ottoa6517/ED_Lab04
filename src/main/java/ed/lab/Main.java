package ed.lab;
public class Main {

    private static final ArrayGenerator<String> sortedArrayGenerator = (length) -> {
        String[] array = new String[length];
        for (int i = 0; i < length; i++) {
            array[i] = String.format("%010d", i);
        }
        return array;
    };

    private static final ArrayGenerator<String> invertedArrayGenerator = (length) -> {
        String[] array = new String[length];
        for (int i = 0; i < length; i++) {
            array[i] = String.format("%010d", length - 1 - i);
        }
        return array;
    };

    private static final ArrayGenerator<String> randomArrayGenerator = (length) -> {
        String[] array = new String[length];
        java.util.concurrent.ThreadLocalRandom random = java.util.concurrent.ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            array[i] = String.format("%010d", random.nextInt());
        }
        return array;
    };
    private static final QuickSort<String> highPivotQuickSort = SortingAlgorithms::highPivotQuickSort;
    private static final QuickSort<String> lowPivotQuickSort = SortingAlgorithms::lowPivotQuickSort;
    private static final QuickSort<String> randomPivotQuickSort = SortingAlgorithms::randomPivotQuickSort;

    public static QuickSort<String> getHighPivotQuickSort() {
        return highPivotQuickSort;
    }

    public static QuickSort<String> getLowPivotQuickSort() {
        return lowPivotQuickSort;
    }

    public static QuickSort<String> getRandomPivotQuickSort() {
        return randomPivotQuickSort;
    }

    public static ArrayGenerator<String> getSortedArrayGenerator() {
        return sortedArrayGenerator;
    }

    public static ArrayGenerator<String> getInvertedArrayGenerator() {
        return invertedArrayGenerator;
    }

    public static ArrayGenerator<String> getRandomArrayGenerator() {
        return randomArrayGenerator;
    }

    public static void main(String[] args) {
        final SortingTester<String> tester = new SortingTester<>();

        // Ejercicio 1.i: Paradigma de programación funcional (uso de lambdas y streams).
        // Ejercicio 1.ii: Se implementó la sumatoria de duraciones usando Stream::sum en SortingTester.

        System.out.println("Ordenando un arreglo ordenado:");
        System.out.println("\tUtilizando el último elemento como pivote: ");
        tester.testSorting(sortedArrayGenerator, highPivotQuickSort);
        System.out.println("\tUtilizando el primer elemento como pivote: ");
        tester.testSorting(sortedArrayGenerator, lowPivotQuickSort);
        System.out.println("\tUtilizando un elemento aleatorio como pivote: ");
        tester.testSorting(sortedArrayGenerator, randomPivotQuickSort);
        System.out.println("================================");

        System.out.println("Ordenando un arreglo invertido:");
        System.out.println("\tUtilizando el último elemento como pivote: ");
        tester.testSorting(invertedArrayGenerator, highPivotQuickSort);
        System.out.println("\tUtilizando el primer elemento como pivote: ");
        tester.testSorting(invertedArrayGenerator, lowPivotQuickSort);
        System.out.println("\tUtilizando un elemento aleatorio como pivote: ");
        tester.testSorting(invertedArrayGenerator, randomPivotQuickSort);
        System.out.println("================================");

        System.out.println("Ordenando un arreglo aleatorio:");
        System.out.println("\tUtilizando el último elemento como pivote: ");
        tester.testSorting(randomArrayGenerator, highPivotQuickSort);
        System.out.println("\tUtilizando el primer elemento como pivote: ");
        tester.testSorting(randomArrayGenerator, lowPivotQuickSort);
        System.out.println("\tUtilizando un elemento aleatorio como pivote: ");
        tester.testSorting(randomArrayGenerator, randomPivotQuickSort);
        System.out.println("================================");
    }
}