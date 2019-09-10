package com.example.scrapvend.DatabaseConnectivity;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connect{

    public static void main(String args[]) {

        try
        {
            Connect connection = new Connect();

            Connection conn = connection.getMySqlConnection();

            Statement statement = conn.createStatement();

            ResultSet results = statement.executeQuery("SELECT * FROM contact_us");

            while (results.next()) {

                // Get the data from the current row using the column index - column data are in the VARCHAR format

                String data = results.getString(1);

                System.out.println("Fetching data by column index for row " + results.getRow() + " : " + data);

                // Get the data from the current row using the column name - column data are in the VARCHAR format

                data = results.getString("Author");

                System.out.println("Fetching data by column name for row " + results.getRow() + " : " + data);

            }

            /*

             * Please note :

             * ResultSet API provides appropriate methods for retrieving data

             * based on each column data type e.g.

             * boolean bool = rs.getBoolean("test_col");

             * byte b = rs.getByte("test_col");

             * short s = rs.getShort("test_col");

             * int i = rs.getInt("test_col");

             * long l = rs.getLong("test_col");

             * float f = rs.getFloat("test_col");

             * double d = rs.getDouble("test_col");

             * BigDecimal bd = rs.getBigDecimal("test_col");

             * String str = rs.getString("test_col");

             * Date date = rs.getDate("test_col");

             * Time t = rs.getTime("test_col");

             * Timestamp ts = rs.getTimestamp("test_col");

             * InputStream ais = rs.getAsciiStream("test_col");

             * InputStream bis = rs.getBinaryStream("test_col");

             * Blob blob = rs.getBlob("test_col");

             * You can use the connection object to do any insert, delete, query or update action to the mysql server.

             * Do not forget to close the database connection after use, this can release the database connection.*/
            conn.close();
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /* This method return java.sql.Connection object from MySQL server. */
    public static Connection getMySqlConnection()    {
        /* Declare and initialize a sql Connection variable. */
        java.sql.Connection ret = null;

        try
        {

            /* Register for jdbc driver class. */
            Class.forName("com.mysql.jdbc.Driver");

            /* Create connection url. */
            String mysqlConnUrl = "jdbc:mysql://localhost:3306/mydb";

            /* db user name. */
            String mysqlUserName = "root";

            /* db password. */
            String mysqlPassword = "admin";

            /* Get the Connection object. */
            ret = DriverManager.getConnection(mysqlConnUrl, mysqlUserName , mysqlPassword);

            /* Get related meta data for this mysql server to verify db connect successfully.. */
            DatabaseMetaData dbmd = ret.getMetaData();

            String dbName = dbmd.getDatabaseProductName();

            String dbVersion = dbmd.getDatabaseProductVersion();

            String dbUrl = dbmd.getURL();

            String userName = dbmd.getUserName();

            String driverName = dbmd.getDriverName();

            System.out.println("Database Name is " + dbName);

            System.out.println("Database Version is " + dbVersion);

            System.out.println("Database Connection Url is " + dbUrl);

            System.out.println("Database User Name is " + userName);

            System.out.println("Database Driver Name is " + driverName);

        }catch(Exception ex)
        {
            ex.printStackTrace();
        }finally
        {
            return ret;
        }
    }

}