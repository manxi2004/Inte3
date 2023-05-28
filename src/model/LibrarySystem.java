package model;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LibrarySystem {
    private ArrayList<User> users;
    private ArrayList<MaterialBibliografico> materials;
    List<MoneyGenre> bookSalesByGenre = new ArrayList<>();
    List<MoneyCategory> magazineSalesByCategory = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private static int totalPaginasLeidasLibros;
    private static int totalPaginasLeidasRevistas;
    private int contadorCienciaFiccion;
    private int contadorFantasia;
    private int contadorNovelaHistorica;
    private int contadorVariedades;
    private int contadorDiseno;
    private int contadorCientifica;
    private double moneyFromBookSales = 0;
    private double moneyFromMagazineSubscriptions = 0;
    private String reporte;

    public LibrarySystem() {
        users = new ArrayList<>();
        materials = new ArrayList<>();
        contadorCienciaFiccion = 0;
        contadorFantasia = 0;
        contadorNovelaHistorica = 0;
        contadorVariedades = 0;
        contadorDiseno = 0;
        contadorCientifica = 0;

    }

    /**
     * The function registers a user of either Regular or Premium type and adds them
     * to a list of users.
     * 
     * @param userType A string representing the type of user to be registered. It
     *                 can be either "Regular"
     *                 or "Premium".
     * @param name     The name of the user being registered.
     * @param id       The id parameter is a unique identifier for the user being
     *                 registered. It could be a
     *                 username, email address, or any other identifier that can
     *                 distinguish one user from another.
     * @param joinDate The joinDate parameter is a String that represents the date
     *                 when the user registered
     *                 in the system.
     */

    public void registerUser(String userType, String name, String id, String joinDate) {
        User user;
        if (userType.equals("Regular")) {
            user = new NormalUser(name, id, joinDate);
        } else if (userType.equals("Premium")) {
            user = new PremiumUser(name, id, joinDate);
        } else {
            System.out.println("Tipo de usuario no válido");
            return;
        }
        users.add(user);
        System.out.println("Usuario registrado con éxito");
    }

    /**
     * This Java function deletes a material from a list based on its ID and prints
     * a success message or an
     * error message if the material is not found.
     * 
     * @param id The ID of the material that needs to be deleted from the list of
     *           materials.
     */

    public void deleteMaterial(String id) {
        for (int i = 0; i < materials.size(); i++) {
            if (materials.get(i).getId().equals(id)) {
                materials.remove(i);
                System.out.println("Material eliminado con éxito");
                return;
            }
        }
        System.out.println("No se encontró el material con el ID especificado");
    }

    // The above code is defining two methods, `registerBook` and
    // `registerMagazine`, that add a new book
    // or magazine object to a list of materials. The book and magazine objects are
    // created using the
    // provided parameters and then added to the list. A success message is printed
    // to the console after
    // each object is added.
    public void registerBook(String id, String name, int numPages, String shortReview,
            String publicationDate, Genre genre, String coverUrl,
            double value, int numSoldOrSubscriptions, int totalPagesRead) {
        Book book = new Book(id, name, numPages, shortReview, publicationDate,
                genre, coverUrl, value, numSoldOrSubscriptions,
                totalPagesRead);
        materials.add(book);
        System.out.println("Libro registrado con éxito");
    }

    public void registerMagazine(String id, String name, int numPages,
            String publicationDate, Category category, String coverUrl,
            double subscriptionValue, String emissionPeriodicity,
            int numActiveSubscriptions, int totalPagesRead) {
        Magazine magazine = new Magazine(id, name, numPages, publicationDate,
                category, coverUrl, subscriptionValue,
                emissionPeriodicity, numActiveSubscriptions,
                totalPagesRead);
        materials.add(magazine);
        System.out.println("Revista registrada con éxito");
    }

    /**
     * This function updates the information of a material (book or magazine) in a
     * list of materials
     * based on its ID.
     * 
     * @param id                     The unique identifier of the material to be
     *                               updated.
     * @param name                   The name of the material (book or magazine)
     *                               that needs to be updated.
     * @param numPages               an integer representing the number of pages in
     *                               the material
     * @param shortReview            A brief review or summary of the material,
     *                               typically used for books.
     * @param publicationDate        A string representing the publication date of
     *                               the material.
     * @param coverUrl               a String representing the URL of the cover
     *                               image for the material.
     * @param value                  The value parameter is a double that represents
     *                               the price or value of the material,
     *                               either as a book's selling price or a
     *                               magazine's subscription fee.
     * @param numSoldOrSubscriptions The number of copies sold or subscriptions for
     *                               the material.
     * @param totalPagesRead         The total number of pages that have been read
     *                               for the material.
     */
    public void updateMaterial(String id, String name, int numPages, String shortReview, String publicationDate,
            String coverUrl, double value, int numSoldOrSubscriptions, int totalPagesRead) {
        for (MaterialBibliografico material : materials) {
            if (material.getId().equals(id)) {
                material.setName(name);
                material.setNumPaginas(numPages);
                material.setFechaPublicacion(publicationDate);
                material.setUrlPortada(coverUrl);
                material.setAcumuladoPaginasLeidas(totalPagesRead);
                if (material instanceof Book) {
                    ((Book) material).setResenaCorta(shortReview);
                    ((Book) material).setValorVenta(value);
                    ((Book) material).setNumEjemplaresVendidos(numSoldOrSubscriptions);
                } else if (material instanceof Magazine) {
                    ((Magazine) material).setValorSuscripcion(value);
                    ((Magazine) material).setNumSuscripcionesActivas(numSoldOrSubscriptions);
                }
                System.out.println("Material actualizado con éxito");
                return;
            }
        }
        System.out.println("No se encontró el material con el ID especificado");
    }

    /**
     * The function allows a user to buy or subscribe to a material, updates the
     * user's information and
     * sales data, and adds the material to the user's material matrix.
     * 
     * @param userId     A string representing the ID of the user who wants to buy
     *                   or subscribe to a
     *                   material.
     * @param materialId A String representing the ID of the material (book or
     *                   magazine) being bought
     *                   or subscribed to.
     */
    public void buyOrSubscribe(String userId, String materialId) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("No se encontró el usuario con el ID especificado");
            return;
        }

        MaterialBibliografico material = findMaterialById(materialId);
        if (material == null) {
            System.out.println("No se encontró el material con el ID especificado");
            return;
        }

        if (user instanceof NormalUser) {
            NormalUser normalUser = (NormalUser) user;

            if (material instanceof Book) {
                if (normalUser.getBooksBought().size() >= 5) {
                    System.out.println("El usuario regular ya ha comprado el máximo de 5 libros");
                    return;
                }
                normalUser.getBooksBought().add((Book) material);
                System.out.println("Libro comprado con éxito");
                moneyFromBookSales += ((Book) material).getPrice();
            } else if (material instanceof Magazine) {
                if (normalUser.getMagazinesSubscribed().size() >= 2) {
                    System.out.println("El usuario regular ya se ha suscrito al máximo de 2 revistas");
                    return;
                }
                normalUser.getMagazinesSubscribed().add((Magazine) material);
                System.out.println("Revista suscrita con éxito");
                moneyFromMagazineSubscriptions += ((Magazine) material).getPrice();
            }

            ArrayList<int[][]> materialMatrices = normalUser.getMaterialMatrices();
            int[][] lastMatrix = materialMatrices.get(materialMatrices.size() - 1);
            boolean foundEmptyCell = false;
            for (int i = 0; i < lastMatrix.length; i++) {
                for (int j = 0; j < lastMatrix[i].length; j++) {
                    if (lastMatrix[i][j] == 0) {
                        lastMatrix[i][j] = Integer.parseInt(materialId);
                        foundEmptyCell = true;
                        break;
                    }
                }
                if (foundEmptyCell) {
                    break;
                }
            }

            // Si no se encontró una posición vacía crear una nueva matriz y agregarla a
            // materialMatrices
            if (!foundEmptyCell) {
                int[][] newMatrix = new int[5][5];
                newMatrix[0][0] = Integer.parseInt(materialId);
                materialMatrices.add(newMatrix);
            }
        } else if (user instanceof PremiumUser) {
            PremiumUser premiumUser = (PremiumUser) user;

            if (material instanceof Book) {
                premiumUser.getBooksBought().add((Book) material);
                System.out.println("Libro comprado con éxito");
                moneyFromBookSales += ((Book) material).getPrice();
            } else if (material instanceof Magazine) {
                premiumUser.getMagazinesSubscribed().add((Magazine) material);
                System.out.println("Revista suscrita con éxito");
                moneyFromMagazineSubscriptions += ((Magazine) material).getPrice();
            }

            if (material instanceof Book) {
                Book book = (Book) material;
                // ...
                moneyFromBookSales += book.getPrice();
                MoneyGenre genreSales = bookSalesByGenre.stream()
                        .filter(gs -> gs.getGenre() == book.getGenero())
                        .findFirst()
                        .orElse(null);
                if (genreSales == null) {
                    genreSales = new MoneyGenre(book.getGenero(), 0);
                    bookSalesByGenre.add(genreSales);
                }
                genreSales.addMoney(book.getPrice());
            }

            if (material instanceof Magazine) {
                Magazine magazine = (Magazine) material;
                // ...
                moneyFromMagazineSubscriptions += magazine.getPrice();
                MoneyCategory categorySales = magazineSalesByCategory.stream()
                        .filter(cs -> cs.getCategory() == magazine.getCategoria())
                        .findFirst()
                        .orElse(null);
                if (categorySales == null) {
                    categorySales = new MoneyCategory(magazine.getCategoria(), 0);
                    magazineSalesByCategory.add(categorySales);
                }
                categorySales.addMoney(magazine.getPrice());
            }

            // Guardar ID del material en la matriz materialMatrix del usuario
            ArrayList<int[][]> materialMatrices = premiumUser.getMaterialMatrices();
            int[][] lastMatrix = materialMatrices.get(materialMatrices.size() - 1);
            boolean foundEmptyCell = false;
            for (int i = 0; i < lastMatrix.length; i++) {
                for (int j = 0; j < lastMatrix[i].length; j++) {
                    if (lastMatrix[i][j] == 0) {
                        lastMatrix[i][j] = Integer.parseInt(materialId);
                        foundEmptyCell = true;
                        break;
                    }
                }
                if (foundEmptyCell) {
                    break;
                }
            }
        }
    }

    // The above code is simulating a reading session for a user with a specific
    // material (book or
    // magazine) by allowing the user to navigate through the pages of the material
    // and keeping track
    // of their progress. It also updates the user's last read page and accumulative
    // pages read for the
    // material, as well as incrementing counters for the genre/category and total
    // pages read for
    // books/magazines.
    public void simulateReadingSession(String userId, String materialId) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("No se encontró el usuario con el ID especificado");
            return;
        }

        MaterialBibliografico material = findMaterialById(materialId);
        if (material == null) {
            System.out.println("No se encontró el material con el ID especificado");
            return;
        }

        System.out.println("Sesión de lectura en progreso");
        System.out.println("Leyendo: " + material.getName());

        int currentPage = user.getLastReadPage(materialId);
        int totalPages = material.getNumPaginas();

        while (true) {
            System.out.println("En la página: " + currentPage);
            System.out.println("Presione 'a' para ir a la página siguiente");
            System.out.println("Presione 'b' para ir a la página anterior");
            System.out.println("Presione 'd' para volver a la librería");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equals("a")) {
                if (currentPage < totalPages) {
                    currentPage++;
                    System.out.println("Página siguiente");
                } else {
                    System.out.println("Ya estás en la última página");
                }
            } else if (input.equals("b")) {
                if (currentPage > 1) {
                    currentPage--;
                    System.out.println("Página anterior");
                } else {
                    System.out.println("Ya estás en la primera página");
                }
            } else if (input.equals("d")) {
                break;
            } else {
                System.out.println("Opción inválida, por favor intenta nuevamente");
            }

            if (material instanceof Book) {
                Genre genero = ((Book) material).getGenero();
                incrementarContadorGenero(genero);
            } else if (material instanceof Magazine) {
                Category categoria = ((Magazine) material).getCategoria();
                incrementarContadorCategoria(categoria);
            }

            if (material instanceof Book) {
                Book book = (Book) material;
                int paginasLeidas = currentPage - user.getLastReadPage(materialId);
                book.setAcumuladoPaginasLeidas(book.getAcumuladoPaginasLeidas() + paginasLeidas);
            }

            if (material instanceof Magazine) {
                Magazine magazine = (Magazine) material;
                int paginasLeidas = currentPage - user.getLastReadPage(materialId);
                magazine.setAcumuladoPaginasLeidas(magazine.getAcumuladoPaginasLeidas() + paginasLeidas);
            }

        }

        user.setLastReadPage(materialId, currentPage);
        System.out.println("Sesión de lectura finalizada");

        if (material instanceof Book) {
            totalPaginasLeidasLibros += (currentPage - user.getLastReadPage(materialId));
        } else if (material instanceof Magazine) {
            totalPaginasLeidasRevistas += (currentPage - user.getLastReadPage(materialId));
        }

        if (material instanceof Book) {
            incrementarContadorGenero(((Book) material).getGenero());
        } else if (material instanceof Magazine) {
            incrementarContadorCategoria(((Magazine) material).getCategoria());
        }
    }

    /**
     * This function searches for a user in a list of users by their ID and returns
     * the user object if
     * found, otherwise it returns null.
     * 
     * @param userId The parameter "userId" is a String that represents the unique
     *               identifier of a user.
     *               The method "findUserById" searches for a user in a collection
     *               of users by comparing their IDs with
     *               the given "userId" parameter. If a user with the same ID is
     *               found, the method returns that user
     * @return The method is returning a User object with the specified userId if it
     *         exists in the users
     *         list, otherwise it returns null.
     */

    private User findUserById(String userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    /**
     * This function searches for a material in a list of materials by its ID and
     * returns it if found,
     * otherwise it returns null.
     * 
     * @param materialId a String representing the unique identifier of a material
     *                   in a collection of
     *                   MaterialBibliografico objects.
     * @return The method is returning a MaterialBibliografico object that matches
     *         the given materialId.
     *         If no matching object is found, it returns null.
     */
    private MaterialBibliografico findMaterialById(String materialId) {
        for (MaterialBibliografico material : materials) {
            if (material.getId().equals(materialId)) {
                return material;
            }
        }
        return null;
    }

    /**
     * This function prints the product matrices of a user and allows the user to
     * navigate through them.
     * 
     * @param userId The ID of the user for whom the product matrix needs to be
     *               printed.
     */
    public void printUserProductMatrix(String userId) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("No se encontró el usuario con el ID especificado");
            return;
        }

        if (user instanceof NormalUser || user instanceof PremiumUser) {
            ArrayList<int[][]> materialMatrices;
            if (user instanceof NormalUser) {
                NormalUser normalUser = (NormalUser) user;
                materialMatrices = normalUser.getMaterialMatrices();
            } else {
                PremiumUser premiumUser = (PremiumUser) user;
                materialMatrices = premiumUser.getMaterialMatrices();
            }

            if (materialMatrices.isEmpty()) {
                System.out.println("El usuario no tiene productos registrados");
                return;
            }

            int currentMatrixIndex = 0;
            boolean exitLoop = false;

            while (!exitLoop) {
                int[][] currentMatrix = materialMatrices.get(currentMatrixIndex);
                System.out.println("Matriz " + (currentMatrixIndex + 1) + ":");
                printMatrix(currentMatrix);

                Scanner scanner = new Scanner(System.in);
                System.out.println("Presione 'a' para ir a la siguiente matriz, 'b' para salir: ");
                String input = scanner.nextLine();

                switch (input) {
                    case "a":
                        if (currentMatrixIndex < materialMatrices.size() - 1) {
                            currentMatrixIndex++;
                        } else {
                            System.out.println("Ya estás en la última matriz");
                        }
                        break;
                    case "b":
                        exitLoop = true;
                        break;
                    default:
                        System.out.println("Opción inválida");
                        break;
                }
            }
        } else {
            System.out.println("Este usuario no tiene permiso para acceder a matrices adicionales");
        }
    }
    // The first code snippet is a Java method that takes a 2D integer array
    // (matrix) as input and prints
    // it to the console in a formatted way. The second code snippet is a Java
    // method that generates a
    // reading report as a string, including the total number of pages read in books
    // and magazines.

    private void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.printf("%-5s", String.valueOf(cell));
            }
            System.out.println();
        }
    }

    public static String generateReadingReport() {
        String reporte = "Informe de páginas leídas:\n";
        reporte += "Páginas leídas en libros: " + totalPaginasLeidasLibros + "\n";
        reporte += "Páginas leídas en revistas: " + totalPaginasLeidasRevistas + "\n";
        return reporte;
    }

    /**
     * This function increments a counter for a specific genre based on a switch
     * statement.
     * 
     * @param genero a variable of type Genre, which is an enum representing
     *               different genres of books
     *               (such as science fiction, fantasy, and historical novel).
     */
    private void incrementarContadorGenero(Genre genero) {
        switch (genero) {
            case CIENCIA_FICCION:
                contadorCienciaFiccion++;
                break;
            case FANTASIA:
                contadorFantasia++;
                break;
            case NOVELA_HISTORICA:
                contadorNovelaHistorica++;
                break;
        }
    }

    /**
     * This function increments a counter based on the category provided as input.
     * 
     * @param categoria It is a parameter of type Category, which is likely an enum
     *                  representing
     *                  different categories. The method increments a counter
     *                  variable based on the value of the category
     *                  parameter using a switch statement.
     */
    private void incrementarContadorCategoria(Category categoria) {
        switch (categoria) {
            case VARIEDADES:
                contadorVariedades++;
                break;
            case DISENO:
                contadorDiseno++;
                break;
            case CIENTIFICA:
                contadorCientifica++;
                break;
        }
    }

    public void generateGenreReadingReport() {
        System.out.println("Reporte de Géneros Más Leídos:");
        System.out.println("Ciencia Ficción: " + contadorCienciaFiccion);
        System.out.println("Fantasía: " + contadorFantasia);
        System.out.println("Novela Histórica: " + contadorNovelaHistorica);
    }

    public void generateCategoryReadingReport() {
        System.out.println("Reporte de Categorías Más Leídas:");
        System.out.println("Variedades: " + contadorVariedades);
        System.out.println("Diseño: " + contadorDiseno);
        System.out.println("Científica: " + contadorCientifica);
    }

    /**
     * The function prints the top 5 most read books and magazines from a list of
     * materials, sorted by
     * the accumulated number of pages read.
     */
    public void printTop5MostRead() {
        // Filtrar los libros y revistas de la lista de materiales
        List<Book> books = new ArrayList<>();
        List<Magazine> magazines = new ArrayList<>();
        for (MaterialBibliografico material : materials) {
            if (material instanceof Book) {
                books.add((Book) material);
            } else if (material instanceof Magazine) {
                magazines.add((Magazine) material);
            }
        }

        // Ordenar los libros y revistas por páginas leídas en orden descendente
        books.sort(Comparator.comparingInt(Book::getAcumuladoPaginasLeidas).reversed());
        magazines.sort(Comparator.comparingInt(Magazine::getAcumuladoPaginasLeidas).reversed());

        // Imprimir el top 5 de libros más leídos
        System.out.println("Top 5 de libros más leídos:");
        for (int i = 0; i < Math.min(books.size(), 5); i++) {
            Book book = books.get(i);
            System.out.println(
                    (i + 1) + ". " + book.getNombre() + " - Páginas leídas: " + book.getAcumuladoPaginasLeidas());
        }

        // Imprimir el top 5 de revistas más leídas
        System.out.println("Top 5 de revistas más leídas:");
        for (int i = 0; i < Math.min(magazines.size(), 5); i++) {
            Magazine magazine = magazines.get(i);
            System.out.println(
                    (i + 1) + ". " + magazine.getName() + " - Páginas leídas: " + magazine.getAcumuladoPaginasLeidas());
        }
    }

    // The above code is defining two methods in Java that print out the amount of
    // money earned by genre
    // for books and by category for magazines. The methods iterate through lists of
    // objects that
    // contain the sales data and print out the genre/category name and the
    // corresponding amount of
    // money earned.
    public void showMoneyByGenre() {
        System.out.println("Dinero obtenido por género de libro:");
        for (MoneyGenre genreSales : bookSalesByGenre) {
            System.out.println(genreSales.getGenre() + ": " + genreSales.getMoney());
        }
    }

    /**
     * This Java function prints out the money obtained by category of magazine
     * sales.
     */
    public void showMoneyByCategory() {
        System.out.println("Dinero obtenido por categoría de revista:");
        for (MoneyCategory categorySales : magazineSalesByCategory) {
            System.out.println(categorySales.getCategory() + ": " + categorySales.getMoney());
        }
    }

}
