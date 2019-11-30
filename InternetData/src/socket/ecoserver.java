/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssasit
 */
public class ecoserver extends Thread {

    public static void main(String[] args) throws IOException {

        eserver t = new eserver();
        Thread tt = new Thread(t);
        tt.start();

    }

}

class eserver implements Runnable {

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(6657);
            Socket s1 = ss.accept();
            OutputStream os = s1.getOutputStream();
            InputStream is = s1.getInputStream();
            PrintStream ps = new PrintStream(os);
            while (true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str=br.readLine();
                System.out.print(str);
                ps.println(str);
              

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
