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

    StaffManagement management = new StaffManagement(scanner);
    management.processInputData();

    System.out.println("Output:");
    management.printStaffPermissions();
    management.processCommands();
  }
}
