/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexiones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Statement;
import java.util.StringTokenizer;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class Pool {
    
    public DataSource dataSource;
    
    File file;
    String user = "";
    String pass = "";
    String host = "";
    String bd = "";
    String dbUrl = "";
    Statement st;
    
    public Pool(){  
        leerOpciones();
        inicializaDataSource();                
    }
    
    private void inicializaDataSource(){                   
        
        BasicDataSource basicDataSource = new BasicDataSource();
        //basicDataSource.setDriverClassName("org.gjt.mm.mysql.Driver");
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(pass);
        basicDataSource.setUrl(dbUrl);        
        basicDataSource.setInitialSize(5);
        basicDataSource.setMinIdle(5); 
        basicDataSource.setMaxTotal(10);        
        dataSource = basicDataSource;        
    }
    
    public void leerOpciones(){
        
        try {
            //file = new File("C://Zred//ConBDMySql.ini");
            file = new File("C:\\Zred\\SunChemical\\ConBDMySqlWEB_Fifo.ini"); 
            BufferedReader filein = null;
            if (file != null) {
                try {
                    filein = new BufferedReader(new FileReader(file));
                    String Linea;
                    while (filein.ready()) {
                        
                        Linea = filein.readLine();                          
                        StringTokenizer stk = new StringTokenizer(Linea, ";");
                        while (stk.hasMoreTokens()) {
                            bd = stk.nextToken();
                            host = stk.nextToken();
                            user = stk.nextToken();
                            pass = stk.nextToken();
                        }                        
                    }
                dbUrl = "jdbc:mysql://"+host+"/"+bd+"";    
                } catch (Exception e) {
                    System.out.println("Error en la clase pool conexion: " + e.getMessage());
                    //Tools.LogSQL((SQLException) e);
                }
            }
        } catch (Exception e) {
            System.out.println("Error en la clase pool conexion: " + e.getMessage());
            //Tools.LogSQL((SQLException) e);
        }
    }
}
