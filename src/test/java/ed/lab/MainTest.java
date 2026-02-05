package ed.lab;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MainTest {

    /*
     * Ejercicio 1:
     *
     * En el metodo main se busco probar el rendimiento del algoritmo quick sort.
     * Se genero un arreglo ordenado, uno invertido y uno aleatorio.
     * Cada arreglo se ordeno usando distintos tipos de pivote.
     * Al final se imprimieron los tiempos para poder compararlos.
     *
     * En la clase SortingTester la idea fue medir cuanto tiempo tarda quick sort
     * en ordenar un arreglo. Se repitio muchas veces usando copias del mismo arreglo
     * para obtener resultados mas estables.
     *
     * Para calcular el promedio se utilizo programacion funcional con Streams.
     * Se uso mapToLong y average para obtener el tiempo promedio.
     *
     * Para obtener la sumatoria se agrego otro Stream usando mapToLong y sum.
     *
     * Al ejecutar el programa inicialmente fallaba porque los generadores y quick sort
     * estaban en null. Despues de reemplazarlos por lambdas y referencias a metodos
     * el programa corrio correctamente.
     */

    /*
     * Ejercicio 2:
     *
     * ArrayGenerator es una interfaz funcional porque solo tiene un metodo abstracto.
     * Tambien es generica porque permite definir el tipo del arreglo que se va a generar.
     *
     * En Main se reemplazaron los null por expresiones lambda.
     *
     * El generador ordenado crea un arreglo del tamano indicado y asigna los valores
     * de forma ascendente.
     *
     * La lambda implementa directamente el metodo generate de ArrayGenerator.
     *
     * El tipo concreto que retorna generate depende del generico usado en Main,
     * no de la interfaz.
     *
     * El generador invertido crea el arreglo en orden descendente.
     *
     * El generador aleatorio crea el arreglo sin ningun orden especifico.
     *
     * Todas las implementaciones recorren el arreglo una sola vez,
     * por eso su complejidad de tiempo es O(n) y su espacio es O(n).
     *
     * Luego de estos cambios todas las pruebas unitarias pasaron correctamente.
     */

    @Test
    void testSortedArrayGenerator() {
        final var sortedArrayGenerator = Main.getSortedArrayGenerator();
        assertThat(sortedArrayGenerator).isNotNull();

        final String[] array = sortedArrayGenerator.generate(10);

        assertThat(array).isNotNull().hasSize(10);

        for (int i = 1; i < array.length; i++) {
            assertThat(array[i]).isNotNull();
            assertThat(array[i]).isGreaterThanOrEqualTo(array[i - 1]);
        }
    }

    @Test
    void testInvertedArrayGenerator() {
        final var invertedArrayGenerator = Main.getInvertedArrayGenerator();
        assertThat(invertedArrayGenerator).isNotNull();

        final String[] array = invertedArrayGenerator.generate(10);

        assertThat(array).isNotNull().hasSize(10);

        for (int i = 1; i < array.length; i++) {
            assertThat(array[i]).isNotNull();
            assertThat(array[i]).isLessThanOrEqualTo(array[i - 1]);
        }
    }

    @Test
    void testRandomGenerator() {
        final var randomArrayGenerator = Main.getRandomArrayGenerator();
        assertThat(randomArrayGenerator).isNotNull();

        final String[] array = randomArrayGenerator.generate(10);

        assertThat(array).isNotNull().hasSize(10);

        for (String s : array) {
            assertThat(s).isNotNull();
        }
    }

    /*
     * Ejercicio 3:
     *
     * QuickSort es una interfaz funcional porque solo define un metodo abstracto.
     *
     * La diferencia con ArrayGenerator es que uno crea datos y el otro los procesa.
     *
     * Se creo la clase SortingAlgorithms para colocar los metodos de quick sort.
     * Esta clase no hereda ni implementa ninguna interfaz.
     *
     * El metodo highPivotQuickSort usa el ultimo elemento como pivote.
     *
     * El metodo lowPivotQuickSort usa el primer elemento como pivote.
     *
     * El metodo randomPivotQuickSort usa un pivote aleatorio.
     *
     * Todos los metodos son estaticos y usan genericos con Comparable.
     *
     * En el peor caso quick sort tiene complejidad O(n^2).
     * En el mejor caso tiene complejidad O(n log n).
     * En promedio tambien se comporta como O(n log n).
     */

    @Test
    void testHighPivotQuickSort() {
        final var highPivotQuickSort = Main.getHighPivotQuickSort();
        assertThat(highPivotQuickSort).isNotNull();

        final var randomArrayGenerator = Main.getRandomArrayGenerator();
        assertThat(randomArrayGenerator).isNotNull();

        final String[] array = randomArrayGenerator.generate(10);
        assertThat(array).isNotNull().hasSize(10);

        highPivotQuickSort.sort(array);

        for (int i = 1; i < array.length; i++) {
            assertThat(array[i]).isNotNull().isGreaterThanOrEqualTo(array[i - 1]);
        }
    }

    @Test
    void testLowPivotQuickSort() {
        final var lowPivotQuickSort = Main.getLowPivotQuickSort();
        assertThat(lowPivotQuickSort).isNotNull();

        final var randomArrayGenerator = Main.getRandomArrayGenerator();
        assertThat(randomArrayGenerator).isNotNull();

        final String[] array = randomArrayGenerator.generate(10);
        assertThat(array).isNotNull().hasSize(10);

        lowPivotQuickSort.sort(array);

        for (int i = 1; i < array.length; i++) {
            assertThat(array[i]).isNotNull().isGreaterThanOrEqualTo(array[i - 1]);
        }
    }

    @Test
    void testRandomPivotQuickSort() {
        final var randomPivotQuickSort = Main.getRandomPivotQuickSort();
        assertThat(randomPivotQuickSort).isNotNull();

        final var randomArrayGenerator = Main.getRandomArrayGenerator();
        assertThat(randomArrayGenerator).isNotNull();

        final String[] array = randomArrayGenerator.generate(10);
        assertThat(array).isNotNull().hasSize(10);

        randomPivotQuickSort.sort(array);

        for (int i = 1; i < array.length; i++) {
            assertThat(array[i]).isNotNull().isGreaterThanOrEqualTo(array[i - 1]);
        }
    }

    /*
     * Ejercicio 4:
     *
     * En Main se usaron referencias a metodos para enlazar SortingAlgorithms.
     * Al ejecutar el programa se imprimieron tiempos promedio y sumatorias.
     * Los tiempos fueron distintos porque el orden inicial del arreglo y el pivote
     * afectan como se divide el arreglo en quick sort.
     * El pivote aleatorio suele dar mejores resultados en promedio.
     */

    /*
     * Ejercicio 5:
     *
     * Se creo la rama feature string arrays.
     * El codigo se adapto para trabajar con arreglos de tipo String.
     * No fue necesario cambiar el algoritmo porque String implementa Comparable.
     * Esto permitio reutilizar el mismo quick sort sin duplicar codigo.
     */

    /*
     * Ejercicio 6:
     *
     * Quick sort es eficiente en promedio pero puede degradarse en el peor caso.
     * Los genericos ayudan a reutilizar codigo y evitan errores de casteo.
     */
}
