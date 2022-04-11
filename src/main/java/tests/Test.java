package tests;

import app.data.datafile.Datafile;

public class Test {

    public static void main(String[] args) {
        String[] data = {"COL1", "COL2", "COL3"};
        System.out.println(Datafile.dataToCSV(data));

        String csv = "COL1,COL2,COL3";
        String[] data2 = Datafile.csvToData(csv);

        for (String col: data2) {
            System.out.println(col);
        }

    }

}
