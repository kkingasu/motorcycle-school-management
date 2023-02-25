package main.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
public class SQLPrinter {
    public static void printResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        int[] columnWidths = new int[columnsNumber];

        // Get column widths
        for (int i = 1; i <= columnsNumber; i++) {
            String columnName = rsmd.getColumnName(i);
            columnWidths[i - 1] = Math.max(columnName.length(), 10);
            for (int j = 1; j <= rs.getRow(); j++) {
                rs.absolute(j);
                String columnValue = rs.getString(i);
                columnWidths[i - 1] = Math.max(columnWidths[i - 1], columnValue.length());
            }
        }

        // Print column headers
        for (int i = 1; i <= columnsNumber; i++) {
            String columnName = rsmd.getColumnName(i);
            System.out.printf("%-" + columnWidths[i - 1] + "s", columnName);
            if (i != columnsNumber) {
                System.out.print(" | ");
            }
        }
        System.out.println();

        // Print separator
        for (int i = 1; i <= columnsNumber; i++) {
            for (int j = 0; j < columnWidths[i - 1]; j++) {
                System.out.print("-");
            }
            if (i != columnsNumber) {
                System.out.print("-+-");
            }
        }
        System.out.println();

        // Print rows
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = rs.getString(i);
                System.out.printf("%-" + columnWidths[i - 1] + "s", columnValue);
                if (i != columnsNumber) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
        System.out.println("\n====================================================================");
    }
}
