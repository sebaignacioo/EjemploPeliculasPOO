package app.data.datafile;

import java.io.*;
import java.util.ArrayList;

public class Datafile {

    private final File file;
    private static final char sep = ',';

    public Datafile(String name) throws IOException {
        this.file = new File("datafiles/" + name + ".csv");
        if (!this.file.isFile())
            if (!this.file.createNewFile())
                throw new FileNotFoundException();
    }

    private static String getSeparator() {
        return Character.toString(sep);
    }

    public static String dataToCSV(String[] data) {
        String csv = "";
        for (int i = 0; i < data.length; i++) {
            csv = csv.concat(data[i]);
            if (i != data.length - 1)
                csv = csv.concat(getSeparator());
        }
        return csv;
    }

    public static String[] csvToData(String csv) {
        return csv.split(getSeparator());
    }

    public ArrayList<String[]> obtenerDatos() {
        String linea;
        String[] row;
        ArrayList<String[]> data = null;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(this.file));
            data = new ArrayList<>();
            linea = fileReader.readLine();
            while (linea != null) {
                row = csvToData(linea);
                data.add(row);
                linea = fileReader.readLine();
            } ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public String[] obtenerLinea(String id, int posID) {
        String linea;
        String[] data;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(this.file));
            do {
                linea = fileReader.readLine();
                data = csvToData(linea);
                if (data[posID].equals(id)) return data;
            } while (linea != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertarLinea(String csv) {
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(this.file, true));
            salida.println(csv);
            salida.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean actualizarLinea(String id, int posID, String newCSV) {
        String linea;
        String[] data;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(this.file));
            do {
                linea = fileReader.readLine();
                data = csvToData(linea);
                if (data[posID].equals(id)) {
                    eliminarLinea(id, posID);
                    insertarLinea(newCSV);
                    return true;
                }
            } while (linea != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminarLinea(String id, int posID) {
        String linea;
        boolean exists = false;
        File tempFile = new File("__temp");
        String[] data;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(this.file));
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(tempFile, true));
            while ((linea = fileReader.readLine()) != null){
                data = csvToData(linea);
                if (!data[posID].equals(id))
                    fileWriter.write(linea + System.getProperty("line.separator"));
                else
                    exists = true;
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (exists) {
            return this.file.delete() && tempFile.renameTo(this.file);
        }
        return false;
    }

}
