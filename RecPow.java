/**
* Writes to output file the power of the base by the exponent
*
* @author  Van Nguyen
* @version 1.0
* @since   2024-04-19
*/

import java.io.*;
import java.util.Scanner;

public class RecPow {
    public static int power(int base, int exponent) {
        // Base case: if exponent is 0, return 1
        if (exponent == 0) {
            return 1;
        } else {
            // Recursive case: multiply base by power of base with reduced exponent
            return base * power(base, exponent - 1);
        }
    }

    public static void main(String[] args) {
        // File paths
        String in = "./input.txt";
        String out = "./output.txt";
        File input = new File(in);
        File output = new File(out);

        try {
            // Scanner + Writer
            Scanner scanner = new Scanner(input);
            FileWriter writer = new FileWriter(output);

            // Input from input file
            while (scanner.hasNextLine()) {
                // Reading base and exponent from file
                String line = scanner.nextLine().trim();
                
                // Check if the line is empty
                if (line.isEmpty()) {
                    continue; // Skip empty lines
                }

                Scanner lineScanner = new Scanner(line);
                
                // Check if there are exactly two integers in the line
                if (!lineScanner.hasNextInt()) {
                    System.out.println("Error: Line does not contain an integer: " + line);
                    lineScanner.close();
                    continue; // Skip this line and move to the next one
                }
                int base = lineScanner.nextInt();
                
                if (!lineScanner.hasNextInt()) {
                    System.out.println("Error: Line does not contain a second integer: " + line);
                    lineScanner.close();
                    continue; // Skip this line and move to the next one
                }
                int exponent = lineScanner.nextInt();

                // Check if there are any extra tokens
                if (lineScanner.hasNext()) {
                    System.out.println("Error: Extra tokens in the line: " + line);
                    lineScanner.close();
                    continue; // Skip this line and move to the next one
                }
                lineScanner.close();

                // Calculating result
                int result = power(base, exponent);

                // Writing result to output file
                writer.write("Result: " + result + "\n");

                // Prints result to console
                System.out.println("Result: " + result);
            }

            // Closing resources
            scanner.close();
            writer.close();

            System.out.println("Results have been written to " + out);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
