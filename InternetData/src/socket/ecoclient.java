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
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author ssasit
 */
public class ecoclient {

    public static void main(String[] args) throws IOException {
        String i;
        Socket s = new Socket("192.168.0.101", 6657);
        OutputStream os = s.getOutputStream();
        InputStream is = s.getInputStream();
        Scanner scan = new Scanner(System.in);
        PrintStream pw = new PrintStream(os);
        while (true) {
            BufferedReader br1 = new BufferedReader(new InputStreamReader(is));

            System.out.println("Enter The message:");
            i = scan.next().toString();
            pw.println(i);
            BufferedReader br2 = new BufferedReader(new InputStreamReader(is));
            System.out.println("Message Back From Server\n" + br2.readLine());

        }
    }
}
