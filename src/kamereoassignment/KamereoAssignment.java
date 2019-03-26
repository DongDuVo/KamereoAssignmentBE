package kamereoassignment;

import java.util.Scanner;

/**
 *
 * @author vodongdu
 */
public class KamereoAssignment {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    System.out.println("Please enter your input data:");
    Scanner scanner = new Scanner(System.in);
    int numberOfStaff = scanner.nextInt();

    StaffManagement management = new StaffManagement(scanner, numberOfStaff);
    management.readInputData();

    System.out.println("Output:");
    management.buildStaffInfos();
    management.printStaffPermissions();
    management.processCommands();
  }
}
