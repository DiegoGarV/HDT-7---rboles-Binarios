/*
* Autores:
* Diego García 22404
* Mónica Salvatierra 22249 
* Fecha: 19/03/2023
* Hoja de Trabajo #7
* Main
*/

import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Diccionario dictionary = new Diccionario();
        Lector lector = new Lector();

        // Imprimir inorden
        lector.lecturaDic(dictionary);
        List<String> lines = dictionary.getInOrderTraversal();
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("                       Imprimir Diccionario     ");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("                             IN ORDER           ");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("%-20s | %-20s| %-20s |%-20s \n", "       Word       ", "       Spanish       ",
                "   English       ",
                "       French       ");
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println("------------------------------------------------------------------------------------");

        // Pedir idioma de origen y destino
        System.out.println("Ingrese el idioma de origen (english/spanish/french):");
        String sourceLanguage = scanner.nextLine().trim().toLowerCase();
        System.out.println("Ingrese el idioma de destino (english/spanish/french):");
        String targetLanguage = scanner.nextLine().trim().toLowerCase();

        System.out.println(lector.lectura(dictionary, sourceLanguage, targetLanguage));
        scanner.close();
    }
}
