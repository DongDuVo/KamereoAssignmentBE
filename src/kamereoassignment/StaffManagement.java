package kamereoassignment;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import kamereoassignment.commands.AddPermissionCommand;
import kamereoassignment.commands.PermissionCommand;

/**
 *
 * @author vodongdu
 */
public class StaffManagement {

  private static final String TOKEN = "\\s";
  private final List<String[]> permissionsOfStaffs = new LinkedList<>();
  private final List<String> managerOfStaffs = new LinkedList<>();
  private final List<String[]> operations = new LinkedList<>();
  private final Scanner scanner;
  private final int numberOfStaff;
  private final Map<String, StaffInfo> staffInfos = new LinkedHashMap<>();

  public StaffManagement(Scanner scanner, int numberOfStaff) {
    this.scanner = scanner;
    this.numberOfStaff = numberOfStaff;
  }

  public void readInputData() {
    readPermissionsOfStaffs();
    readMangerOfStaffs();
    readCommands();
  }

  protected void buildStaffInfos() {
    PermissionCommand addPermissionCommand = new AddPermissionCommand();
    StaffInfo ceo = new StaffInfo("CEO", null);
    ceo.addPermission(permissionsOfStaffs.get(0));
    staffInfos.put("CEO", ceo);
    addPermissionCommand.execute(staffInfos, "CEO", permissionsOfStaffs.get(0));

    for (int i = 1; i <= numberOfStaff; i++) {
      String managerId = managerOfStaffs.get(i - 1);
      String id = String.valueOf(i);
      StaffInfo staff = new StaffInfo(id, managerId);
      staffInfos.put(id, staff);

      addPermissionCommand.execute(staffInfos, id, permissionsOfStaffs.get(i));
    }
  }

  protected void processCommands() {
    for (Iterator<String[]> iterator = operations.iterator(); iterator.hasNext();) {
      String[] command = iterator.next();
      
    }
  }

  protected void readPermissionsOfStaffs() {
    for (int i = 0; i <= numberOfStaff; i++) {
      String[] permisstions = scanner.nextLine().split(TOKEN);
      permissionsOfStaffs.add(permisstions);
    }
  }

  protected void readMangerOfStaffs() {
    for (int i = 0; i < numberOfStaff; i++) {
      String manager = scanner.nextLine();
      managerOfStaffs.add(manager);
    }
  }

  protected void readCommands() {
    while (scanner.hasNextLine()) {
      String[] commands = scanner.nextLine().split(TOKEN);
      if (COMMANDS.find(commands[0])) {
        operations.add(commands);
      } else {
        break;
      }
    }
  }
}
