/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigexcercise4moduleb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author Lauri
 */
public class BigExcercise4ModuleB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
            int port = Integer.parseInt(args[0]);
          System.out.println("Portnumber is " + port);
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                System.out.println("Client connected!");
                String[] values = in.readLine().split("%");
                String solution = solveModulo(values[0], values[1]);
                out.write(solution);
                out.flush();
            }
    }

    private static String solveModulo(String dividend, String divisor) {
       int dend = Integer.parseInt(dividend);
       int sor = Integer.parseInt(divisor);
       int solution;
       Random rn = new Random();
       int seed = rn.nextInt(10) + 1;
       if (seed == 1) {
           return "Error!";
                   }
       else if (seed == 1) {
          solution = modulo(dend, sor) + 1;
       }
       else {
           solution = modulo(dend,sor);
       }
       return Integer.toString(solution);
    }

    private static int modulo(int dend, int sor) {
        return dend%sor; //To change body of generated methods, choose Tools | Templates.
    }
}
