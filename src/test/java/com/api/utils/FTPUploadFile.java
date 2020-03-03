package com.api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FTPUploadFile extends BaseClass{

    public void uploadReportToFtpServer(){


        String server = "waws-prod-ln1-025.ftp.azurewebsites.windows.net";
        int port = 21;
        String user = "trillium-qa-reports\\$trillium-qa-reports";
        String pass = "rqrMZNj7QneKHSvQB78Dp8cX30piWwZRcownn37ZoukwsZqKBGwmv2oy3Q3v";

        FTPClient ftpClient = new FTPClient();
        try {

            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();



            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.changeWorkingDirectory("\\site\\wwwroot\\Projectx\\reports\\");
            File firstLocalFile = new File("target/ProjectXApiTestingReport"+dateTime+".html");

            String firstRemoteFile = "ProjectXApiTestingReport"+dateTime+".html";
            InputStream inputStream = new FileInputStream(firstLocalFile);

            System.out.println("Start uploading Report");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("report is uploaded successfully.");
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }
}
