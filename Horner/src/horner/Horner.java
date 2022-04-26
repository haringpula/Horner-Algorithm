/* Naming Conventions: * Package names are in lowercase*
 * Class/Interfaces names are in PascalCase * Method/Instances names are in camelCase*
 * Variable names are in camelCase (typeName) * Constants are in SNAKE_CASE*
 * Temporary variable names: i,j,k,m,n for int; c,d,e for char (else follow variable names)*
 * Sapere Aude */
package horner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Dianne April Gudio
 * @author Robbie Mondia
 * @author Angelo Navarro
 * @author King Red Sanchez
 */
public class Horner {

    static int FUNCT_DIVISOR = 6;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String strWord, strFinal, strTryAgain;
        Scanner in = new Scanner(System.in);
        contentChange(); //Change whole word bank
        do {
            System.out.print("Enter String: ");
            strWord = in.nextLine();
            strFinal = hornerAlgo(strWord);
            //functWait(3000);
            System.out.println(strFinal);
            System.out.print("Enter y to try again: ");
            strTryAgain = in.nextLine();
        } while ("y".equals(strTryAgain));
        System.out.println("ty.");
    }

    /**
     * @param intMSec the time to wait in milliseconds
     */
    public static void functWait(int intMSec) {
        try {
            Thread.sleep(intMSec);
        } catch (InterruptedException exInterrupt) {
            Logger.getLogger(Horner.class.getName()).log(Level.SEVERE, null, exInterrupt);
        }
    }

    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void contentChange() throws FileNotFoundException, IOException {
        String strLine, strOut;
        try (
                BufferedReader readTestSheet = new BufferedReader(new InputStreamReader(new FileInputStream("D:/Harry/Word Test.txt")));) {
            while ((strLine = readTestSheet.readLine()) != null) {
                strOut = hornerAlgo(strLine);
                System.out.println(strOut);
                //System.out.print(strLine + " ");
            }

        } catch (Exception e) {
        }
    }

    /**
     * Computes the ascii values of input then computes the horner digit using
     * synthetic division
     *
     * @param strWord the input string
     * @return 
     */
    public static String hornerAlgo(String strWord) {
        int tempNum, i, intTempVar;
        String strHorner;
        LinkedList<Integer> listCoefficient = new LinkedList<>();
        LinkedList<Integer> listPolynomial = new LinkedList<>();
        listCoefficient.clear();
        listPolynomial.clear();
        for (i = 0; i < strWord.length(); i++) {
            //num = Character.getNumericValue(word.charAt(i));  //edited to add numerical sampels
            tempNum = (int) strWord.charAt(i);
            listCoefficient.add(tempNum);
        }
        //System.out.println("The List is: " + listCoefficient);
        intTempVar = listCoefficient.get(0);
        listPolynomial.add(0, intTempVar);
        for (i = 1; i < listCoefficient.size(); i++) {
            intTempVar = listPolynomial.get(i - 1) * FUNCT_DIVISOR;
            listPolynomial.add(i, listCoefficient.get(i) + intTempVar);
        }
        //System.out.println("The List is: " + listPolynomial);
        strHorner = String.valueOf(listPolynomial.getLast());
        return strHorner;

    }
}
