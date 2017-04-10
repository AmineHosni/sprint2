/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author jamel_pc
 */
public class ftpSave {
    
    
    public void saveFile(File file)  {
        String server = "127.0.0.1";
        int port = 21;
        String user = "jamel";
        String pass = "jamel";
        
        FTPClient ftpClient = new FTPClient();
        
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
 
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        
             InputStream inputStream = new FileInputStream(file);
             boolean done = ftpClient.storeFile(file.getName(), inputStream);
            inputStream.close();
            
            if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
        } catch (IOException ex) {
            Logger.getLogger(ftpSave.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
}
