
package ui;

import model.LibrarySystem;
import model.Genre;
import model.Category;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibrarySystem controller = new LibrarySystem();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Registrar Libro");
            System.out.println("3 Registrar revista");
            System.out.println("4. Eliminar material");
            System.out.println("5. Actualizar material");
            System.out.println("6. Comprar libro o suscribirse a una revista ");
            System.out.println("7.Simular sesion de lectura");
            System.out.println("8.mostrar libreria en pantalla");
            System.out.println("9. mostrar informe de las paginas leidas totales en los libros y revistas");
            System.out.println("10.Generar reporte de genero de ibro mas leido");
            System.out.println("11.Generar reporte de categoria de revista mas leida");
            System.out.println("12.listar los 5 libros mas leidos");
            System.out.println("13. Mostrar ganancias de libros por cada genero");
            System.out.println("14. Mostrar reporte de ganancia por categoria de revistas");
            System.out.println("15.Salir del sistema");
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                System.out.println("Ingrese el tipo de usuario (Regular/Premium):");
                String userType = scanner.nextLine();
                System.out.println("Ingrese el nombre:");
                String name = scanner.nextLine();
                System.out.println("Ingrese la cédula:");
                String id = scanner.nextLine();
                System.out.println("Ingrese la fecha de vinculación:");
                String joinDate = scanner.nextLine();
                controller.registerUser(userType, name, id, joinDate);

                // ...
            } else if (option == 2) {
                if (option == 1) {
                    // ...
                } else if (option == 2) {
                    System.out.print("Ingrese el ID del libro: ");
                    String bookId = scanner.nextLine();
                    System.out.print("Ingrese el nombre del libro: ");
                    String bookName = scanner.nextLine();
                    System.out.print("Ingrese el número de páginas del libro: ");
                    int bookNumPages = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese una reseña corta del libro: ");
                    String bookShortReview = scanner.nextLine();
                    System.out.print("Ingrese la fecha de publicación del libro: ");
                    String bookPublicationDate = scanner.nextLine();
                    System.out.print("Ingrese el género del libro: ");
                    String bookGenreString = scanner.nextLine();

                    Genre bookGenre = Genre.valueOf(bookGenreString);

                    System.out.print("Ingrese la URL de la portada del libro: ");
                    String bookCoverUrl = scanner.nextLine();
                    System.out.print("Ingrese el valor del libro: ");
                    double bookValue = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Ingrese el número de libros vendidos: ");
                    int bookNumSoldOrSubscriptions = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el número total de páginas leídas: ");
                    int bookTotalPagesRead = scanner.nextInt();
                    scanner.nextLine();

                    // Llamar al método registerBook con los datos ingresados
                    controller.registerBook(bookId, bookName, bookNumPages, bookShortReview,
                            bookPublicationDate, bookGenre, bookCoverUrl,
                            bookValue, bookNumSoldOrSubscriptions,
                            bookTotalPagesRead);

                    // ...
                }

            } else if (option == 3) {

                System.out.print("Ingrese el ID de la revista: ");
                String magazineId = scanner.nextLine();
                System.out.print("Ingrese el nombre de la revista: ");
                String magazineName = scanner.nextLine();
                System.out.print("Ingrese el número de páginas de la revista: ");
                int magazineNumPages = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Ingrese la fecha de publicación de la revista: ");
                String magazinePublicationDate = scanner.nextLine();
                System.out.print("Ingrese la categoría de la revista: ");
                String magazineCategoryString = scanner.nextLine();
                Category magazineCategory = Category.valueOf(magazineCategoryString);
                System.out.print("Ingrese la URL de la portada de la revista: ");
                String magazineCoverUrl = scanner.nextLine();
                System.out.print("Ingrese el valor de suscripción de la revista: ");
                double magazineSubscriptionValue = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Ingrese la periodicidad de emisión de la revista: ");
                String magazineEmissionPeriodicity = scanner.nextLine();
                System.out.print("Ingrese el número de suscripciones activas de la revista: ");
                int magazineNumActiveSubscriptions = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Ingrese el número total de páginas leídas: ");
                int magazineTotalPagesRead = scanner.nextInt();
                scanner.nextLine();

                // Llamar al método registerMagazine con los datos ingresados
                controller.registerMagazine(magazineId, magazineName, magazineNumPages,
                        magazinePublicationDate, magazineCategory, magazineCoverUrl,
                        magazineSubscriptionValue, magazineEmissionPeriodicity,
                        magazineNumActiveSubscriptions, magazineTotalPagesRead);

                // ...
            } else if (option == 4) {

                System.out.println("Ingrese el identificador del material que desea eliminar:");
                String id = scanner.nextLine();
                controller.deleteMaterial(id);

                // ...
            } else if (option == 5) {

                System.out.println("Ingrese el identificador del material que desea actualizar:");
                String id = scanner.nextLine();
                System.out.println("Ingrese el nuevo nombre:");
                String name = scanner.nextLine();
                System.out.println("Ingrese el nuevo número de páginas:");
                int numPages = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Ingrese la nueva reseña corta (deje en blanco si no aplica):");
                String shortReview = scanner.nextLine();
                System.out.println("Ingrese la nueva fecha de publicación:");
                String publicationDate = scanner.nextLine();
                System.out.println("Ingrese la nueva URL de la portada:");
                String coverUrl = scanner.nextLine();
                System.out.println("Ingrese el nuevo valor de venta o suscripción:");
                double value = scanner.nextDouble();
                scanner.nextLine();
                System.out.println(
                        "Ingrese el nuevo número de ejemplares vendidos o suscripciones activas (deje en blanco si no aplica):");
                int numSoldOrSubscriptions = 0;
                try {
                    numSoldOrSubscriptions = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    // Ignorar
                }
                controller.updateMaterial(id, name, numPages, shortReview, publicationDate, coverUrl, value,
                        numSoldOrSubscriptions, 0);

            } else if (option == 6) {

                System.out.println("Ingrese el ID del usuario:");
                String userId = scanner.nextLine();
                System.out.println("Ingrese el ID del material:");
                String materialId = scanner.nextLine();
                controller.buyOrSubscribe(userId, materialId);

            } else if (option == 7) {

                System.out.println("Ingrese el ID del usuario:");
                String userId = scanner.nextLine();
                System.out.println("Ingrese el ID del material:");
                String materialId = scanner.nextLine();
                controller.simulateReadingSession(userId, materialId);

            } else if (option == 8) {

                System.out.println("Ingrese su id para mostrar su biblioteca actual");
                String userID = scanner.nextLine();

                controller.printUserProductMatrix(userID);

            } else if (option == 9) {

                String pageReads = controller.generateReadingReport();
                System.out.println(pageReads);

            } else if (option == 10) {

                controller.generateGenreReadingReport();
            } else if (option == 11) {

                controller.generateCategoryReadingReport();

            } else if (option == 12) {

                controller.printTop5MostRead();

            } else if (option == 13) {
                controller.showMoneyByGenre();

            } else if (option == 14) {

                controller.showMoneyByCategory();

            } else if (option == 15) {

                break;
            }

        }
    }
}