import models.Business;
import models.Hashtable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hashtable<String, Business> hashtableSimpleHash = new Hashtable<>(true);
        Hashtable<String, Business> hashtableMultiplicativeHash = new Hashtable<>(false);
        String line = "";
        String splitBy = ",";
        int id = 1;

        try {
            BufferedReader br = new BufferedReader(new FileReader("bussines.csv"));
            while ((line = br.readLine()) != null) {
                String[] businessData = line.split(splitBy);
                Business business = new Business(businessData[0], businessData[1], businessData[2], businessData[3], businessData[4]);
                hashtableSimpleHash.put(business.getId(), business);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader("bussines.csv"));
            while ((line = br.readLine()) != null) {
                String[] businessData = line.split(splitBy);
                Business business = new Business(businessData[0], businessData[1], businessData[2], businessData[3], businessData[4]);
                hashtableMultiplicativeHash.put(business.getId(), business);
            }
        } catch (IOException e) {
            e.printStackTrace();
        };

        boolean flag = true;
        while (flag) {
            System.out.println("1) Imprimir todo con simpleHash");
            System.out.println("2) Imprimir todo con multiplicativehash");
            System.out.println("3) Buscar por ID de negocio con simpleHash");
            System.out.println("4) Buscar por ID de negocio con multiplicativehash");
            System.out.println("5) Terminar programa");
            String response = scanner.nextLine();
            switch (response) {
                case "1":
                    long timeTakenSimple = hashtableSimpleHash.printAll(new Hashtable.Callback<Business>() {
                        @Override
                        public void call(Business business) {
                            System.out.println(business);
                        }
                    });
                    System.out.println("Tiempo tomado para imprimir todo con simpleHash: " + String.format("%.6f", timeTakenSimple / 1e9) + " segundos");
                    break;
                case "2":
                    long timeTakenMultiplicative = hashtableMultiplicativeHash.printAll(new Hashtable.Callback<Business>() {
                        @Override
                        public void call(Business business) {
                            System.out.println(business);
                        }
                    });
                    System.out.println("Tiempo tomado para imprimir todo con multiplicativehash: " + String.format("%.6f", timeTakenMultiplicative / 1e9) + " segundos");
                    break;
                case "3":
                    System.out.print("Ingrese el ID del negocio a buscar: ");
                    String searchIdSimple = scanner.nextLine();
                    long startTimeSimple = System.nanoTime();

                    Business searchResultSimple = hashtableSimpleHash.get(searchIdSimple, new Business(searchIdSimple, "", "", "", ""));
                    long endTimeSimple = System.nanoTime();

                    if (searchResultSimple != null) {
                        System.out.println("Encontrado: " + searchResultSimple);
                    } else {
                        System.out.println("Negocio no encontrado.");
                    }
                    System.out.println("Tiempo tomado para buscar con simpleHash: " + String.format("%.6f", (endTimeSimple - startTimeSimple) / 1e9) + " segundos");
                    break;
                case "4":
                    System.out.print("Ingrese el ID del negocio a buscar: ");
                    String searchIdMultiplicative = scanner.nextLine();
                    long startTimeMultiplicative = System.nanoTime();

                    Business searchResultMultiplicative = hashtableMultiplicativeHash.get(searchIdMultiplicative, new Business(searchIdMultiplicative, "", "", "", ""));
                    long endTimeMultiplicative = System.nanoTime();

                    if (searchResultMultiplicative != null) {
                        System.out.println("Encontrado: " + searchResultMultiplicative);
                    } else {
                        System.out.println("Negocio no encontrado.");
                    }
                    System.out.println("Tiempo tomado para buscar con multiplicativehash: " + String.format("%.6f", (endTimeMultiplicative - startTimeMultiplicative) / 1e9) + " segundos");
                    break;
                case "5":
                    flag = false;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intente nuevamente.");
            }
        }
    }
}
