package cmpe343group4project1;
import java.util.Scanner;
import java.util.InputMismatchException;

public class group4project1 {
    public static void main(String[] args) {
        drawASCIIArt();
        Scanner scanner = new Scanner(System.in);
        boolean working = true;
        while (working) {
            printMenu();
            String choice = scanner.nextLine().toUpperCase();
            switch (choice) {
                case "A":
                    statisticalInformation();
                    break;
                case "B":
                    matrixOperations();
                    clearConsole();
                    break;
                case "C":
                    textEncryptionDecryption(scanner);
                    clearConsole();
                    break;
                case "D":
                    ticTacToe();
                    break;
                case "E":
                    System.out.println("Terminating the console. Goodbye!");
                    working = false;
                    break;
                default:
                    System.out.println("This option is invalid, please try another option.");
            }
        }
        scanner.close();
    }

    public static void printMenu() {
        System.out.println("Welcome to the Console Application of Group 4.");
        System.out.println("Please select the operation: ");
        System.out.println("[A] Statistical Information about an array.");
        System.out.println("[B] Matrix Operations");
        System.out.println("[C] Text Encryption/Decryption");
        System.out.println("[D] Tic-Tac-Toe Game");
        System.out.println("Press E to terminate the console.");
    }

    public static void statisticalInformation() {
        clearConsole();
  Scanner scanner = new Scanner(System.in);
        System.out.println("You chose the Statistical Information about an array. \n");
    boolean continueCalculating = true;
  int size = 0;
  while(continueCalculating) {
    
  while(true){
    try{
      System.out.println("How many numbers you want to enter: ");
      size = scanner.nextInt();
      if(size > 0)
        break;
      else
        System.out.println("The size of the array must be a positive number. Please try again: ");
    } catch (InputMismatchException e) { 
      System.out.println("You should enter a number. Please try a positive number: ");
      scanner.next();
  }
    }
  double[] array = new double[size];
  for(int i = 0; i < size; i++){
    while(true){
      try{
        System.out.println("Enter the element " + (i+1) + ": ");
        array[i] = scanner.nextDouble();
        break;
      } catch(InputMismatchException e){
        System.out.println("You must enter a number. Try again. ");
        scanner.next();
      }
    }
  }

  double median = calculateMedian(array);
  double arithmeticMean = calculateArithmeticMean(array);

  System.out.printf("\nMedian: %.2f\n", median);
        System.out.printf("Arithmetic Mean: %.2f\n", arithmeticMean);
    
  if (allPositive(array)) {
            double geometricMean = calculateGeometricMean(array);
            System.out.printf("Geometric Mean: %.5f\n", geometricMean);
        } else {
            System.out.println("Geometric mean cannot be calculated because there are non-positive numbers in the array.");
        }

  if (allPositive(array)) {
            double harmonicMean = calculateHarmonicMean(array, size);
            System.out.printf("Harmonic Mean: %.5f\n", harmonicMean);
        } else {
            System.out.println("Harmonic mean cannot be calculated because there are non-positive numbers in the array.");
        }
  scanner.nextLine();
  System.out.println("\nPress A to calculate another array. Press B to return to main menu.");
  String choice = scanner.nextLine().toUpperCase();
  
  if(choice.equals("A")) {
      clearConsole();
    continueCalculating = true;
  }else if(choice.equals("B")) {
    System.out.println("\nReturning to main menu. Thanks for using.\n");
    continueCalculating = false;
  }else {
    System.out.println("Invalid input. Returning to main menu.");
    continueCalculating = false;
  }
  }
}

public static void sortArray(double[] array){
  int n = array.length;
  double temp;
  for(int i = 0; i < n -1 ; i++){
    for(int j = 0; j < n - i -1; j++){
      if(array[j+1] < array[j]){
        temp = array[j];
        array[j] = array[j+1];
        array[j+1] = temp;
        }
      }
    }
}

public static double calculateMedian(double[] array){
  sortArray(array);
  int size = array.length;
  if(size % 2 == 0)
    return (array[size/2] + array[size/2 - 1]) / 2.0;
  else
    return array[size/2];
}

public static double calculateArithmeticMean(double[] array){
  double sum = 0.0;
  int size = array.length;
  for(int i = 0; i < size; i++)
    sum += array[i];
  return sum / size;
}

public static boolean allPositive(double[] array){
  for(double number : array){
    if(number <= 0)
      return false;
  }
  return true;
}

public static double calculateGeometricMean(double[] array){
  int size = array.length;
  double product = 1;
  for(double num: array)
    product *= num;

  return Math.pow(product, 1.0 / size);
}

public static double calculateHarmonicMean(double[] array, int size) {
    double sumOfReciprocals = harmonicMeanHelper(array, size, 0);
    return size / sumOfReciprocals;
}

public static double harmonicMeanHelper(double[] array, int size, int index) {
    if (index == size) return 0;
    return (1.0 / array[index]) + harmonicMeanHelper(array, size, index + 1);
}



    public static void matrixOperations() {
        clearConsole();
        System.out.println("Matrix Operations.");

        boolean inSubmenu = true;
        Scanner scan = new Scanner(System.in);

        while (inSubmenu) {
            
            System.out.println("Please select an operation");
            System.out.println("Enter 'A' for Transpose");
            System.out.println("Enter 'B' for Inverse");
            System.out.println("Enter 'C' for Matrix Multiplication");
            System.out.println("Enter 'D' for Element-Wise Matrix Multiplication");
            System.out.println("Press 'E' to return to the Main Menu");

            String submenu = scan.nextLine().toUpperCase();
            switch (submenu) {
                case "A":
                    TransposeMatrix();
                    break;
                case "B":
                    MatrixInverse();
                    break;
                case "C":
                    MatrixMultip();
                    break;
                case "D":
                    ElementWiseMatrixMultip();
                    break;
                case "E":
                    inSubmenu = false;
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid operation.");
                    break;
            }
        }
    }

    public static void TransposeMatrix() {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter the number of rows for your matrix:");
        int rows = -1;
        while (rows <= 0) {
            try {
                rows = input.nextInt();
                if (rows <= 0) {
                    System.out.println("You must enter a positive integer. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. You must enter a number. Try again.");
                input.next();
            }
        }

        System.out.println("Enter the number of columns for your matrix:");
        int cols = -1;
        while (cols <= 0) {
            try {
                cols = input.nextInt();
                if (cols <= 0) {
                    System.out.println("You must enter a positive integer. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. You must enter a number. Try again.");
                input.next();
            }
        }

        double[][] matrix = new double[rows][cols];
        double[][] transposedMatrix = new double[cols][rows];

        System.out.println("Please enter the elements for your matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean validInput;
                do {
                  try {
                    matrix[i][j] = input.nextDouble();
                    validInput=true;
                  } catch(InputMismatchException e) {
                    System.out.println("You must enter a double number. Try again. ");
                    input.next();
                    validInput = false;
                  }
                } while (!validInput);
            }
        }


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposedMatrix[j][i] = matrix[i][j];
            }
        }

        System.out.println("The transposed matrix is:");
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                System.out.print(transposedMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void MatrixInverse() {

                Scanner scanner = new Scanner(System.in);
        int rows = 0;
        int columns = 0;
        boolean validInput;

        System.out.println("Matrix's Row:");
        do {
            try {
                rows = scanner.nextInt();
                if (rows > 0) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a positive number.");
                    validInput = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("You must enter a positive integer number. Try again.");
                scanner.next();
                validInput = false;
            }
        } while (!validInput);

        System.out.println("Matrix's Column:");
        do {
            try {
                columns = scanner.nextInt();
                if (columns <= 0) {
                    System.out.println("Please enter a positive number.");
                    validInput = false;
                } else if (columns != rows) {
                    System.out.println("The column number must be equal to the row number. Try again.");
                    validInput = false;
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer number.");
                scanner.next();
                validInput = false;
            }
        } while (!validInput);
        
        float[][] matrix = new float[rows][columns];
    
    if (rows != columns) {
            System.out.println("Only square matrix is allowed for inverse calculation. Please enter a square matrix.");
        }else{
      
      System.out.println("Enter elements for the first matrix:");
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
          do {
              try {
                matrix[i][j] = scanner.nextFloat();
                validInput=true;
              } catch(InputMismatchException e) {
                System.out.println("You must enter a double number. Try again. ");
                scanner.next();
                validInput = false;
              }
            } while (!validInput);
        }
      }
        
      float[][] adjointMatrix = adjoint(matrix); 
      float determinantMatrix = determinant(matrix, rows);
      float[][] inverseMatrix = divide(adjointMatrix,determinantMatrix);
      System.out.println("Inverse Matrix:");
      for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    System.out.print(inverseMatrix[i][j] + " ");
                }
                System.out.println();
            }
    }
    }
  
  public static float[][] adjoint(float[][] matrix) {
        float[][] adjoint = new float[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                adjoint[i][j] = determinant(cofactor(matrix, i, j), (matrix.length) -1);
                if ((i + j) % 2 == 1) {
                    adjoint[i][j] = -adjoint[i][j];
                    }
                }
            }
        return transpose(adjoint);
    }

    public static float[][] cofactor(float matrix[][], int row, int column) {
        float[][] temp = new float[matrix.length - 1][matrix.length - 1];
        int i = 0, j = 0;

        for (int r = 0; r < matrix.length; r++) {
            if (r == row) continue;
            for (int c = 0; c < matrix.length; c++) {
                if (c == column) continue;
                temp[i][j++] = matrix[r][c];
                if (j == matrix.length - 1) {
                    j = 0;
                    i++;
                }
            }
        }
        return temp;
    }

    public static float determinant(float matrix[][], int d) {
        if (d == 1) {
            return matrix[0][0];
            }
        float determinantResult = 0;
        int sign = 1;

        for (int i = 0; i < d; i++) {
            float[][] temp = new float[d - 1][d - 1];
            determinantResult += sign * matrix[0][i] * determinant(cofactor(matrix, 0, i), d - 1);
            sign = -sign;
        }
        return determinantResult;
    }

    public static float[][] divide(float[][] matrix1, float scalar) {
        int rows1 = matrix1.length;
        int columns1 = matrix1[0].length;
        float[][] result = new float[rows1][columns1];

        for (int i = 0; i < rows1; i++) {
          for (int j = 0; j < columns1; j++) {
              result[i][j] += (matrix1[i][j]) / (scalar);
                
          }
        }

        return result;
        
    }
    public static float[][] transpose(float[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        float[][] result = new float[columns][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[j][i] = matrix[i][j];
            }
        }

        return result;
        
    }


public static void MatrixMultip() {
      Scanner input = new Scanner(System.in);
      
      System.out.println("According to the rules of matrix multiplaction the column size of the first matrix and the row size of the second matrix must be same.");
      
      int rows=0;
      int columns=0;
      int columns2=0;
      
      do {
        try {
          System.out.println("Enter the First Matrix's Row Number : ");
          rows = input.nextInt();
          if(rows>0) {
            break;
          }
          else {
            System.out.println("Please enter a positive number ");
          }
          
        } catch(InputMismatchException e) {
          System.out.println("Invalid input please try again");
          input.next();
        }
      } while(rows <= 0);
      
      do {
        try {
          System.out.println("Enter First Matrix's Column and Second Matrix's Row Numbers: ");
          columns = input.nextInt();
          if(columns>0) {
            break;
          }
          else {
            System.out.println("Please enter a positive number");
          }
        } catch(InputMismatchException e) {
          System.out.println("Invalid input please try again");
          input.next();
        }
      } while(columns <= 0);
      
      do {
        try {
          System.out.println("Enter Second Matrix's Column Number: ");
          columns2 = input.nextInt();
          if(columns2>0) {
            break;
          }
          else {
            System.out.println("Please enter a positive number!");
          }
        } catch(InputMismatchException e) {
          System.out.println("Invalid input please try again");
          input.next();
        }
      } while (columns2 <= 0);
      
      double[][] matrix1 = new double[rows][columns];
          double[][] matrix2 = new double[columns][columns2];
          
          System.out.println("Please enter the elements for your first matrix");
          for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
              boolean validInput;
              do {
                try {
                  matrix1[i][j] = input.nextDouble();
                  validInput=true;
                } catch(InputMismatchException e) {
                  System.out.println("You must enter a double number. Try again. ");
                  input.next();
                  validInput = false;
                }
              } while (!validInput);
            }
              
            
          }
          System.out.println("Your first matrix is: ");
          for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
              System.out.print(matrix1[i][j] + " ");
            }
            System.out.println();
            
          }
          
          System.out.println("Please enter the elements for your second matrix");
          for(int i=0; i<columns; i++) {
            for(int j=0; j<columns2; j++) {
              boolean validInput;
              do {
                try {
                  matrix2[i][j] = input.nextDouble();
                  validInput=true;
                } catch(InputMismatchException e) {
                  System.out.println("You must enter a double number. Try again. ");
                  input.next();
                  validInput = false;
                }
              } while(!validInput);
            }
          }
          
          System.out.println("Your second matrix is: ");
          for(int i=0; i<columns; i++) {
            for(int j=0; j<columns2; j++) {
              
              System.out.print(matrix2[i][j] + " ");
            }
            System.out.println();
            
          }
          

          double[][] resultMatrix = multiply(matrix1,matrix2);
          
          System.out.println("Resultant Matrix:");
          for (int i = 0; i < rows; i++) {
              for (int j = 0; j < columns2; j++) {
                  System.out.print(resultMatrix[i][j] + " ");
              }
              System.out.println();
          }
          
          
    }
    
    public static double[][] multiply(double[][] matrix1, double[][] matrix2) {
          int rows1 = matrix1.length;
          int columns1 = matrix1[0].length;
          int columns2 = matrix2[0].length;
          double[][] result = new double[rows1][columns2];

          for (int i = 0; i < rows1; i++) {
              for (int j = 0; j < columns2; j++) {
                  result[i][j] = 0;
                  for (int k = 0; k < columns1; k++) {
                      result[i][j] += (matrix1[i][k]) * (matrix2[k][j]);
                  }
              }
          }

          return result;
          
      }

    public static void ElementWiseMatrixMultip() {
        Scanner input = new Scanner(System.in);

        
        int rows1 = -1;
        while (rows1 <= 0) {
            try {
                System.out.println("Enter a positive integer row number for your matrices:");
                rows1 = input.nextInt();
                if (rows1 <= 0) {
                    System.out.println("You must enter a positive integer. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. You must enter a number. Try again.");
                input.next();
            }
        }
        int rows2 = rows1;
        int rows3 = rows1;

       
        int cols1 = -1;
        while (cols1 <= 0) {
            try {
                System.out.println("Please enter a positive integer column number for your matrices:");
                cols1 = input.nextInt();
                if (cols1 <= 0) {
                    System.out.println("You must enter a positive integer. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. You must enter a number. Try again.");
                input.next();
            }
        }

        int cols2 = cols1;
        int cols3 = cols1;

        double[][] matrix1 = new double[rows1][cols1];
        double[][] matrix2 = new double[rows2][cols2];
        double[][] resultMatrix = new double[rows3][cols3];

        System.out.println("Please enter the elements for your first matrix");
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols1; j++) {
                while (true) {
                    try {
                        matrix1[i][j] = input.nextDouble();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("You must enter a double number. Try again. ");
                        input.next();
                    }
                }
            }

        }
        System.out.println("Your first matrix is: ");
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols1; j++) {
                System.out.print(matrix1[i][j] + " ");
            }
            System.out.println();

        }

        System.out.println("Please enter the elements for your second matrix");
        for (int i = 0; i < rows2; i++) {
            for (int j = 0; j < cols2; j++) {
                while (true) {
                    try {
                        matrix2[i][j] = input.nextDouble();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("You must enter a double number. Try again. ");
                        input.next();
                    }
                }
            }
        }

        System.out.println("Your second matrix is: ");
        for (int i = 0; i < rows2; i++) {
            for (int j = 0; j < cols2; j++) {
                System.out.print(matrix2[i][j] + " ");
            }
            System.out.println();

        }

        for (int i = 0; i < rows3; i++) {
            for (int j = 0; j < cols3; j++) {
                resultMatrix[i][j] = matrix1[i][j] * matrix2[i][j];
            }
        }

        System.out.println("The result of element-wise matrix multiplication is:");
        for (int i = 0; i < rows3; i++) {
            for (int j = 0; j < cols3; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.println();
        }

    }

   public static void textEncryptionDecryption(Scanner scanner) {
       clearConsole();
        boolean submenuActive = true;
        while (submenuActive) {
            System.out.println("\nText Encryption/Decryption.");
            System.out.println("Please select the operation:");
            System.out.println("[1] Encrypt Text");
            System.out.println("[2] Decrypt Text");
            System.out.println("[3] Return to the Main Menu");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    System.out.print("Enter the shift value (-26 to 26): ");
                    int shiftEncrypt = getShiftValue(scanner);
                    if (shiftEncrypt != Integer.MIN_VALUE) {
                        System.out.print("Enter the text to encrypt: ");
                        String textToEncrypt = scanner.nextLine();
                        String encryptedText = encrypt(textToEncrypt, shiftEncrypt);
                        System.out.println("Encrypted Text: " + encryptedText);
                    }
                    break;
                case "2":
                    System.out.print("Enter the shift value (-26 to 26): ");
                    int shiftDecrypt = getShiftValue(scanner);
                    if (shiftDecrypt != Integer.MIN_VALUE) {
                        System.out.print("Enter the text to decrypt: ");
                        String textToDecrypt = scanner.nextLine();
                        String decryptedText = decrypt(textToDecrypt, shiftDecrypt);
                        System.out.println("Decrypted Text: " + decryptedText);
                    }
                    break;
                case "3":
                    submenuActive = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    public static String encrypt(String message, int shiftKey) {
        final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
        String cipherText = "";
        for (int ii = 0; ii < message.length(); ii++) {
            char currentChar = message.charAt(ii);
            boolean isUpperCase = Character.isUpperCase(currentChar); 
            currentChar = Character.toLowerCase(currentChar);
            if (ALPHABET.indexOf(currentChar) != -1) { 
                int charPosition = ALPHABET.indexOf(currentChar);
                int keyVal = (shiftKey + charPosition) % 26;
                if (keyVal < 0) {
                    keyVal = ALPHABET.length() + keyVal; 
                }
                char replaceVal = ALPHABET.charAt(keyVal);
                cipherText += isUpperCase ? Character.toUpperCase(replaceVal) : replaceVal;
            } else {
                cipherText += currentChar;
            }
        }
        return cipherText;
    }
    public static String decrypt(String cipherText, int shiftKey) {
        return encrypt(cipherText, -shiftKey);
    }
    public static int getShiftValue(Scanner scanner) {
        try {
            int shift = scanner.nextInt();
            scanner.nextLine(); 
            if (shift < -26 || shift > 26) {
                 System.out.println("Error: Please enter a valid integer shift value in the range [-26, 26].");
                return Integer.MIN_VALUE; 
            }
            return shift;
        } catch (Exception e) {
             System.out.println("Error: Please enter a valid integer shift value in the range [-26, 26].");
            scanner.nextLine(); 
            return Integer.MIN_VALUE;
        }
    }

        public static void ticTacToe() {
            clearConsole();
        System.out.println("Tic-Tac-Toe Game.");
        Scanner sc = new Scanner(System.in);
        boolean oyunBittimi = false;
        boolean oyunDevam = true;
        char tahta[][]=new char[4][4];
        int turSayisi = 0;
        tahta[0][0]='+';
        tahta[1][0]='1';
        tahta[2][0]='2';
        tahta[3][0]='3';
        tahta[0][1]='1';
        tahta[0][2]='2';
        tahta[0][3]='3';
        char kullanici = 'X'; 
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                tahta[i][j] = '-'; 
            }
        }
        while(!oyunBittimi){
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.print(tahta[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("Player " + kullanici + ", Enter rows and columns (1-3):");
            int row = sc.nextInt();
            int col = sc.nextInt();
            if(row>=1 && row<=3 && col>=1 && col<=3){
                if(tahta[row][col] == '-'){
                    tahta[row][col] = kullanici;
                    turSayisi++;
                    if(kontrolKazanc(tahta,kullanici)){
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                System.out.print(tahta[i][j] + " ");
                            }
                            System.out.println();
                        }
                        System.out.println("Congratulations! Player " + kullanici + " win!");
                        System.out.println("There have been " + turSayisi + " turns" );
                        oyunBittimi = true;
                    }else if(kontrolBeraberlik(tahta)){
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                System.out.print(tahta[i][j] + " ");
                            }
                            System.out.println();
                        }
                        System.out.println("The game is a draw!");
                        System.out.println("There have been " + turSayisi + " turns" );
                        oyunBittimi = true;
                    }else {
                        if(kullanici == 'X'){
                            kullanici = 'O';
                        }else{
                            kullanici = 'X';
                        }
                    }
                }else {
                    System.out.println("This cell is full! Select another cell.");
                }
            }
            else{
                System.out.println("Invalid value entered.");
            }
        }
    
    }
    public static boolean kontrolKazanc(char tahta[][],char kullanici) {
        for (int i = 1; i < 4; i++) {
            if (tahta[i][1] == kullanici && tahta[i][2] == kullanici && tahta[i][3] == kullanici) {
                return true;
            }
            if (tahta[1][i] == kullanici && tahta[2][i] == kullanici && tahta[3][i] == kullanici) {
                return true;
            }
        }
        if (tahta[1][1] == kullanici && tahta[2][2] == kullanici && tahta[3][3] == kullanici) {
            return true;
        }
        if (tahta[1][3] == kullanici && tahta[2][2] == kullanici && tahta[3][1] == kullanici) {
            return true;
        }
        return false;
    }
    public static boolean kontrolBeraberlik(char tahta[][]) {
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (tahta[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Console couldn't be cleared.");
        }
    }

    public static void drawASCIIArt() {
      System.out.println(" Group 4\n "
          + "Serkan Açar\n "
          + "Aleyna İslamoğlu\n "
          + "Irmak Koç\n "
          + "Burak Arslan\n "
          + "Eren Can Günel\n "
          +"ASCII Robot Welcomes You!");
        System.out.println("            __ __ __ __");
        System.out.println("            |          |");
        System.out.println("            |   O  O   |");
        System.out.println("            |          |");
        System.out.println("            |   ----   |");
        System.out.println("            |          |");
        System.out.println("            |____vv____|");
        System.out.println("            |          |");
        System.out.println("            |          |");
        System.out.println("0-----------|          |--------------0");
        System.out.println("            |          |");
        System.out.println("            |          |");
        System.out.println("            |          |");
        System.out.println("            ------------");
        System.out.println("              |      | ");
        System.out.println("              |      | ");
        System.out.println("              |      | ");
        System.out.println("              |      | ");
        System.out.println("              |      | ");
        System.out.println("              |      | ");
        System.out.println("              -      - ");
    }

}
