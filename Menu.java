import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; 

public class Menu {

    public static void main(String[] args)
    {
        List<String[]> mainlist = new LinkedList<String[]>();
        Scanner scanner = new Scanner(System.in);
        String input;
        Integer input2;
        
        boolean run = true;

        readFile(mainlist);
        
        while (run == true)
        {
        

            System.out.println("== University Student Registry System ==\n");
            System.out.println("========================================\n");
            System.out.println("=== Please enter an existing option: ===\n");
            System.out.println("\n");
            System.out.println("1. Enter a student registry number...\n");
            System.out.println("2. Create student registry w/ number...\n");
            System.out.println("3. Print all registries...\n");
            System.out.println("4. Quit...\n");
            System.out.println("========================================\n");
            
            
            input = scanner.nextLine();
            
            // == points to a location in memory while .equals() compares the value of two objects
            // Search for student
            if (input.equals("1"))
            {
                System.out.println("Please enter student registry number...\n");
                input = scanner.nextLine();
                int registryIndex = 0;
                for (int i = 0; i < mainlist.size(); i++)
                {
                    String studentNumber = mainlist.get(i)[8];
                    if (studentNumber.equals(input))
                    {
                        registryIndex = i;
                        break;
                    }
                }

                System.out.printf("Student View of: %S %S\n", mainlist.get(registryIndex)[0], mainlist.get(registryIndex)[1]);
                System.out.printf("To view...\nEnter...\n1 - First Name\n2 - Last Name\n3-6 - Street Address, City, State, Zip\n7 - Major\n8 - GPA\n9 - Email\n10 - Student Registry Number\n11 - See Options\n12 - Exit\n", mainlist.get(registryIndex)[0], mainlist.get(registryIndex)[1]);
                
                boolean studentView = true;
                while (studentView)
                {
                    input = scanner.nextLine();
                    input2 = Integer.parseInt(input);
                    input2 = input2 - 1;

                    if (input.equals("12"))
                    {
                        studentView = false;
                    }
                    else if (input.equals("11"))
                    {
                        System.out.printf("Student View of: %S %S\n", mainlist.get(registryIndex)[0], mainlist.get(registryIndex)[1]);
                        System.out.printf("To view...\nEnter...\n1 - First Name\n2 - Last Name\n3-6 - Street Address, City, State, Zip\n7 - Major\n8 - GPA\n9 - Email\n10 - Student Registry Number\n11 - See Options\n12 - Exit\n", mainlist.get(registryIndex)[0], mainlist.get(registryIndex)[1]);        
                    }
                    else if (input.equals("9"))
                    {
                        System.out.printf("%S\nWould you like to send an email? (y/n)\n", mainlist.get(registryIndex)[input2]);
                        input = scanner.nextLine();
                        if (input.equals("y"))
                        {
                            System.out.printf("Enter your email:\n");
                            input = scanner.nextLine();
                            System.out.printf("Email sent...\n");
                        }
                    }
                    else
                    {
                        System.out.printf("%S\n", mainlist.get(registryIndex)[input2]);
                    }
                }   
            }
            // Create a student registry
            else if (input.equals("2"))
            {  
                createRegistry(scanner);

                readFile(mainlist);

            }
            // Print entire student registry
            else if (input.equals("3"))
            {
                for (String[] element : mainlist)
                {
                    System.out.println(Arrays.toString(element));
                    System.out.println();
                }
                System.out.println("Press enter to continue");
                System.out.println();
                scanner.nextLine();

            }
            // Quit
            else if (input.equals("4"))
            {
                System.out.println("Goodbye!");
                run = false;
            }
        }
        scanner.close();
    }

    public static String[] createRegistry(Scanner scanner)
    {    
        String[] collectData;
        collectData = new String[10];
        Random rand = new Random();
        Integer studentNumberInt = rand.nextInt(100000000);
        String studentNumber = studentNumberInt.toString();
        
        System.out.println("First Name:");
        collectData[0] = scanner.nextLine();
        System.out.println("\n");
        System.out.println("Last Name");
        collectData[1] = scanner.nextLine();
        System.out.println("\n");
        System.out.println("Street Address (No City or State Here!):");
        collectData[2] = scanner.nextLine();
        System.out.println("\n");
        System.out.println("City:");
        collectData[3] = scanner.nextLine();
        System.out.println("\n");
        System.out.println("State (Abreviated):");
        collectData[4] = scanner.nextLine();
        System.out.println("\n");
        System.out.println("Zip:");
        collectData[5] = scanner.nextLine();
        System.out.println("\n");

        System.out.println("Declared Major:");
        collectData[6] = scanner.nextLine();
        System.out.println("\n");
        System.out.println("GPA:");
        collectData[7] = scanner.nextLine();
        System.out.println("\n");
        System.out.println("Email:");
        collectData[8] = scanner.nextLine();
        System.out.println("\n");

        System.out.println("Please Write Out The Corresponding Student's Registry Number");
        System.out.println(studentNumber);
        collectData[9] = studentNumber;
        System.out.println("\n");

        Writing(collectData[0], collectData[1], collectData[2], collectData[3], collectData[4], collectData[5], collectData[6], collectData[7], collectData[8], collectData[9]);
        return collectData;
    }

    public static void Writing(String nameFirst, String nameLast, String streetAdd, String city, String state, String zip, String major, String gpa, String email, String studentNumber) {
        try {
          FileWriter myWriter = new FileWriter("StudentRegistries.txt");
          myWriter.write(String.format("%S, %S, %S, %S, %S, %S, %S, %S, %S, %S", nameFirst, nameLast, streetAdd, city, state, zip, major, gpa, email, studentNumber));
          myWriter.close();
          System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
    }
    public static void readFile(List<String[]> mainList) {
        try {
          File myObj = new File("StudentRegistries.txt");
          Scanner myReader = new Scanner(myObj);
          
          while (myReader.hasNextLine()) {
            String[] data = myReader.nextLine().split(" ,");
            mainList.add(data);
          }
          myReader.close();
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
      }
}
