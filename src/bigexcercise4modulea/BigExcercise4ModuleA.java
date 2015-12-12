/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigexcercise4modulea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lauri
 */
public class BigExcercise4ModuleA {

    /**
     * @param args the command line arguments
     */
    static ArrayList<String> addresses = new ArrayList();
    
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("You have to provide exactly two arguments: The dividend and the divisor."
                    + "Example: 42 20");
            System.exit(0);
        }
        addresses = parseIPs();
        String[] solutions = getSolutions(addresses, args[0], args[1]);
        System.out.println(solutions.toString());
        while (true) {
            if (CorrectSolutions(solutions)) {
                System.out.println("Correct answer: " + solutions[0]);
            } else {
                solutions = getSolutions(addresses, args[0], args[1]);
            }
        }
    }
    
    private static ArrayList parseIPs() {
        File ips = new File("configuration.txt");
        ArrayList<String> tmpAddresses = new ArrayList();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(ips));
            String line;
            while ((line = reader.readLine()) != null) {
                tmpAddresses.add(line);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No configuration file provided. Aborting.");
            System.exit(1);
        } catch (IOException ex) {
            Logger.getLogger(BigExcercise4ModuleA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmpAddresses;
    }
    
    private static String[] getSolutions(ArrayList<String> addresses, String first, String second) {
        String[] solutions = new String[addresses.size()];
        for (int i = 0; i < addresses.size(); i++) {
            String[] ip = addresses.get(i).split(":");
            try {
                Socket socket = new Socket(ip[0], Integer.parseInt(ip[1]));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.write(first + "%" + second);
                solutions[i] = in.readLine();
                out.close();
                in.close();
                socket.close();
                
            } catch (IOException ex) {
                Logger.getLogger(BigExcercise4ModuleA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return solutions;
    }
    
    private static boolean CorrectSolutions(String[] solutions) {
        for (int i = 1; i < solutions.length; i++) {
            if (!solutions[0].equals(solutions[i])) {
                if (solutions[i].equals("Error!")) {
                    System.out.println("Critical Error!");
                }
                System.exit(1);
            } else {
                return false;
            }
        }
        return true;
    }
    
}
