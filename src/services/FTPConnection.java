/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author AmineHosni
 */
public class FTPConnection {

    private static FTPClient client;

    public static FTPClient getClient() {
       
            try {
                /*String server = "ftp-manouba.alwaysdata.net";
                int port = 21;
                String user = "manouba";
                String pass = "Butterfly0";*/
                String server = "ftp.0fees.us";
                int port = 21;
                String user = "0fe_15929099";
                String pass = "chidori1990chidori";

                client = new FTPClient();

                client.connect(server, port);
                client.login(user, pass);
                client.enterLocalPassiveMode();
                //client.changeWorkingDirectory("/pidev/");

                client.setFileType(2);
            } catch (IOException ex) {
                Logger.getLogger(FtpUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return client;
    }

}
