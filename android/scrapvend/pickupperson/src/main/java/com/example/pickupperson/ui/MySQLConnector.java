package com.example.pickupperson.ui;

import android.util.Log;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class MySQLConnector {

    private static final String  TAG = "MySQLConnector";

    public static Connection getMySqlConnection()    {
        /* Declare and initialize a sql Connection variable. */
        Connection ret = null;

        try
        {

            Log.d(TAG, "before com.mysql.jdbc.Driver");
            /* Register for jdbc driver class. */
            Class.forName("com.mysql.jdbc.Driver");

            Log.d(TAG, "after com.....");

            /* Create connection url. */

            /*connection url for remotemysql.com database free database host service*/

            String mysqlConnUrl = "jdbc:mysql://db4free.net:3306/scrapvend";
            String mysqlUserName = "scrapvenduser";
            String mysqlPassword = "nompooaissai123";

            /*connection url for remotemysql.com database free database host service*/

//            String mysqlConnUrl = "jdbc:mysql://remotemysql.com:3306/bvYGbRyIOE";       //mobile
//            String mysqlUserName = "bvYGbRyIOE";
//            String mysqlPassword = "5DAMZUK3Qk";

            /*connection url for mohammadnoman.co database */


//            String mysqlConnUrl = "jdbc:mysql://mohammadnoman.co:3306/mohammad_Scrapvend";       //mobile
//            String mysqlUserName = "mohammad_user1";
//            String mysqlPassword = "nompooaissai123";

            /*connection url for mobile hotspot and my laptop server. */

//            String mysqlConnUrl = "jdbc:mysql://10.0.2.2:3306/test_sv_2";         //emulator
//            String mysqlConnUrl = "jdbc:mysql://192.168.43.115:3306/mohammad_scrapvend";       //mobile
//            String mysqlUserName = "root";
//            String mysqlPassword = "admin";


            /*connection url for poornima laptop's server. */

//            String mysqlConnUrl = "jdbc:mysql://192.168.43.197:3306/scrap_vend";        //PoornimaPC
//            String mysqlUserName = "root";
//            String mysqlPassword = "root";


            /*connection url for hostal hotspot and my laptop server. */

//            String mysqlConnUrl = "jdbc:mysql://10.0.2.2:3306/test_sv_2";         //emulator
//            String mysqlConnUrl = "jdbc:mysql://172.17.2.189:3306/test_sv_2";
//            String mysqlUserName = "root";
//            String mysqlPassword = "admin";


            /* Get the Connection object. */
            ret = DriverManager.getConnection(mysqlConnUrl + "?autoReconnect=true&useSSL=false", mysqlUserName , mysqlPassword);

            Log.d(TAG, "after getConnection");

            /* Get related meta data for this mysql server to verify db connect successfully.. */
            DatabaseMetaData dbmd = ret.getMetaData();

            String dbName = dbmd.getDatabaseProductName();

            String dbVersion = dbmd.getDatabaseProductVersion();

            String dbUrl = dbmd.getURL();

            String userName = dbmd.getUserName();

            String driverName = dbmd.getDriverName();

            Log.d(TAG, "Database Name is " + dbName);

            Log.d(TAG, "Database Version is " + dbVersion);

            Log.d(TAG, "Database Connection Url is " + dbUrl);

            Log.d(TAG, "Database User Name is " + userName);

            Log.d(TAG, "Database Driver Name is " + driverName);

        }catch(Exception ex)
        {
            ex.printStackTrace();
        }finally
        {
            Log.d(TAG, "inside Finally block");
            return ret;
        }
    }
}
