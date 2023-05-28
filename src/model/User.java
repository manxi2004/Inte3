package model;

import java.util.ArrayList;
import java.util.Date;

public abstract class User {
    protected String nombre;
    protected String cedula;
    protected String fechaVinculacion;
    protected ArrayList<int[][]> materialMatrices;

    public User(String nombre, String cedula, String fechaVinculacion) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.fechaVinculacion = fechaVinculacion;
        this.materialMatrices = new ArrayList<>();
        this.materialMatrices.add(new int[5][5]); // Inicializar con la primera matriz vacía
    }

    public Object getId() {
        return cedula;
    }

    public ArrayList<int[][]> getMaterialMatrices() {
        return materialMatrices;
    }

    public void setLastReadPage(String materialId, int page) {
        int[][] currentMatrix = materialMatrices.get(materialMatrices.size() - 1);
        boolean matrixFull = isMatrixFull(currentMatrix);

        if (matrixFull) {
            currentMatrix = createNewMatrix();
            materialMatrices.add(currentMatrix);
        }

        int rowIndex = -1;
        int columnIndex = -1;
        boolean pageFound = false;

        for (int i = 0; i < currentMatrix.length; i++) {
            for (int j = 0; j < currentMatrix[i].length; j++) {
                if (currentMatrix[i][j] == 0) {
                    rowIndex = i;
                    columnIndex = j;
                    currentMatrix[i][j] = Integer.parseInt(materialId);
                    pageFound = true;
                    break;
                }
            }
            if (pageFound) {
                break;
            }
        }

        System.out.println("Página " + page + " del material " + materialId +
                " guardada en la matriz del usuario " + getId());

        if (!matrixFull) {
            System.out.println("Presione 'a' para pasar a la siguiente matriz");
        }
    }

    private boolean isMatrixFull(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] createNewMatrix() {
        return new int[5][5];
    }

    public int getLastReadPage(String materialId) {
        for (int i = materialMatrices.size() - 1; i >= 0; i--) {
            int[][] matrix = materialMatrices.get(i);
            for (int j = 0; j < matrix.length; j++) {
                for (int k = 0; k < matrix[j].length; k++) {
                    if (matrix[j][k] == Integer.parseInt(materialId)) {
                        return (i * 25) + (j * 5) + k + 1;
                    }
                }
            }
        }
        return 1; // Si no se encuentra el material en ninguna matriz
    }

}
