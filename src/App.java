import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int choice;
    do {
      System.out.println("===== Test Menu =====");
      System.out.println("1. Test Category");
      System.out.println("2. Test Product");
      System.out.println("3. Test Export");
      System.out.println("4. Test Import");
      System.out.println("5. Test User");
      System.out.println("6. Test Report");
      System.out.println("0. Exit");
      System.out.print("Enter Choice: ");
      choice = scanner.nextInt();
      switch (choice) {
        case 1:
          CategoryTest.startMenu();
          break;
        case 2:
          ProductTest.startMenu();
          break;
        case 3:
          ExportTest.startMenu();
          break;
        case 4:
          ImportTest.startMenu();
          break;
        case 5:
          UserTest.startMenu();
          break;
        case 6:
          ReportTest.startMenu();
          break;
        case 0:
          System.out.println("Exiting...");
          break;
        default:
          System.out.println("Invalid Choice! Please Try Again!");
      }
      System.out.println();
    } while (choice != 0);
    scanner.close();
  }
}