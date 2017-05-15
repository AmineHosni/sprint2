/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author AmineHosni
 */
public class FtpUtil {

    private static FTPClient client;
    

    public static void main(String[] args) {

    }

    public static InputStream getFile(String fileName) throws IOException {
        InputStream is;
        try {
            //is = null;
            client = FTPConnection.getClient();
            //is = new FileInputStream("/htdocs/pidev/" + fileName);
            System.out.println("start downloading file !"+fileName);
            is = (client.retrieveFileStream("/htdocs/pidev/" + fileName));
            //System.out.println(is);
            
            return is;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void storeFile(File f, String fileName) throws IOException {
        InputStream inputStream = null;
        try {
            client = FTPConnection.getClient();
            inputStream = new FileInputStream(f);
            System.out.println("Start uploading file");
            boolean done = client.storeFile("/htdocs/pidev/" + fileName, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The file is uploaded successfully.");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FtpUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(FtpUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
