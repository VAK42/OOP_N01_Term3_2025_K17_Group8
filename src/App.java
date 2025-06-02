public class App {
  public static void main(String[] args) {
    System.out.println("=== Category Tests ===");
    CategoryTest.runTest();
    CategoryTest.testAdd();
    CategoryTest.testEdit();
    CategoryTest.testDelete();
    System.out.println();

    System.out.println("=== Product Tests ===");
    ProductTest.runTest();
    ProductTest.testAdd();
    ProductTest.testEdit();
    ProductTest.testDelete();
    System.out.println();

    System.out.println("=== User Tests ===");
    UserTest.runTest();
    UserTest.testAdd();
    UserTest.testEdit();
    UserTest.testDelete();
    System.out.println();

    System.out.println("=== Export Tests ===");
    ExportTest.runTest();
    ExportTest.testAdd();
    ExportTest.testEdit();
    ExportTest.testDelete();
    System.out.println();

    System.out.println("=== Import Tests ===");
    ImportTest.runTest();
    ImportTest.testAdd();
    ImportTest.testEdit();
    ImportTest.testDelete();
    System.out.println();

    System.out.println("=== Report Tests ===");
    ReportTest.runTest();
    ReportTest.testAdd();
    ReportTest.testEdit();
    ReportTest.testDelete();
    System.out.println();
  }
}