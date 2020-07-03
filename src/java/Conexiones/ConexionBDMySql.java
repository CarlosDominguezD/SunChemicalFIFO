/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Conexiones;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ConexionBDMySql 
{
    private static int Dim;
    public String Bd;
    public String Url;
    public String IpBD;
    public String User;
    public String Pass;
    public File file;
    public static Statement st;
    public static Statement State ;
    public static Statement rs;
    Connection con = null;
    
    public Connection  abrirConexion()
    {
        try 
        {            
            file = new File("SunChemical\\ConBDMySqlWEBFIFO.ini");            
            //file = new File("C:\\Zred\\ConBDMySqlWEB.ini");            
            BufferedReader filein = null;
            if (file != null)
            {
                try 
                {
                    filein = new BufferedReader(new FileReader(file));
                    String Linea;
                    while (filein.ready())
                    {   
                        Linea = filein.readLine();
                        //while ((Linea = filein.readLine()) != null)
                        //{                            
                            StringTokenizer stk = new StringTokenizer(Linea, ";");
                            while (stk.hasMoreTokens())
                            {                                
                                Bd = stk.nextToken();
                                IpBD = stk.nextToken();
                                User = stk.nextToken();
                                Pass = stk.nextToken();                                
                                
                                st = null;
                                try 
                                {
                                    Url = "jdbc:mysql://"+IpBD+"/" + Bd;
                                    Class.forName("org.gjt.mm.mysql.Driver");
                                    con = DriverManager.getConnection(Url, User, Pass);
//                                 if (con != null)
//                                    {
//                                        //JOptionPane.showMessageDialog(null,"conexion Exitosa");                
//                                        st = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//                                    }
//                                    else
//                                    {
//                                        JOptionPane.showMessageDialog(null,"conexion no Exitosa");
//                                    }         
                                } 
                                catch (SQLException | ClassNotFoundException ex) 
                                {
                                    System.out.println(ex);
                                }
                            }
                        //}
                    }
                } 
                catch (IOException | HeadlessException e) 
                {
                    System.out.println(e);
                }
            }
        } catch (Exception e) 
        {
            System.out.println(e);
        }
        return con;        
    }    

    private void CrearArchivo()
    {
        File str = new File("LogZred.txt");
        if (str.exists())
        {
            try 
            {                
                File Archi = new File("");
                FileWriter Escri = new FileWriter(Archi,true);
                Escri.write("hola mi pez \n");
                Escri.close();
            } 
            catch (IOException e) 
            {
                System.out.println(e);
            }
        }
        else
        {
            str.mkdirs();
            try 
            {                
                File Archi = new File("LogZred.txt");
                FileWriter Escri = new FileWriter(Archi,true);
                Escri.write("Hola mi pez\n");
                Escri.close();
            } 
            catch (IOException e) 
            {
                System.out.println(e);
            }
        }
    }
}
