package main.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SQLPrinter {

    public static void printResultSet(ResultSet rs) throws SQLException {
        // Get metadata for ResultSet
        ResultSetMetaData rsmd = rs.getMetaData();
        int numColumns = rsmd.getColumnCount();
        int[] columnWidths = new int[numColumns];

        // Get maximum width of each column based on column name and values
        for (int i = 1; i <= numColumns; i++) {
            String columnName = rsmd.getColumnName(i);
            columnWidths[i-1] = columnName.length();
            rs.beforeFirst();
            while (rs.next()) {
                Object value = rs.getObject(i);
                if (value != null) {
                    int width = value.toString().length();
                    if (width > columnWidths[i-1]) {
                        columnWidths[i-1] = width;
                    }
                }
            }
        }

        // Print table header
        for (int i = 1; i <= numColumns; i++) {
            String columnName = rsmd.getColumnName(i);
            System.out.print(String.format("%-" + columnWidths[i-1] + "s", columnName));
            if (i < numColumns) {
                System.out.print(" | ");
            }
        }
        System.out.println();

        // Print separator row
        for (int i = 1; i <= numColumns; i++) {
            String separator = "-";
            for (int j = 1; j <= columnWidths[i-1]; j++) {
                separator += "-";
            }
            System.out.print(separator);
            if (i < numColumns) {
                System.out.print("-+-");
            }
        }
        System.out.println();

        // Print table data
        rs.beforeFirst();
        while (rs.next()) {
            for (int i = 1; i <= numColumns; i++) {
                Object value = rs.getObject(i);
                System.out.print(String.format("%-" + columnWidths[i-1] + "s", (value != null ? value.toString() : "")));
                if (i < numColumns) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
    }
}