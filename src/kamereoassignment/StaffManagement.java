package kamereoassignment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import kamereoassignment.commands.AddPermissionCommand;
import kamereoassignment.commands.COMMANDS;
import kamereoassignment.commands.PermissionCommand;
import kamereoassignment.commands.QueryPermissionCommand;
import kamereoassignment.commands.RemovePermissionCommand;

/**
 *
 * @author vodongdu
 */
public class StaffManagement {

  private static final String TOKEN = "\\s";
  private final List<String[]> permissionsOfStaffs = new LinkedList<>();
  private final List<String> managerOfStaffs = new LinkedList<>();
  private final List<String[]> commands = new LinkedList<>();
  private final Scanner scanner;
  private final int numberOfStaff;
  private final Map<String, StaffInfo> staffInfos = new LinkedHashMap<>();
  private final Map<String, PermissionCommand> commandExecutors = new HashMap<>();

  public StaffManagement(Scanner scanner, int numberOfStaff) {
    this.scanner = scanner;
    this.numberOfStaff = numberOfStaff;
    commandExecutors.put(COMMANDS.ADD.name(), new AddPermissionCommand());
    commandExecutors.put(COMMANDS.QUERY.name(), new QueryPermissionCommand());
    commandExecutors.put(COMMANDS.REMOVE.name(), new RemovePermissionCommand());
  }

  public void readInputData() {
    readPermissionsOfStaffs();
    readMangerOfStaffs();
    readCommands();
  }

  public void buildStaffInfos() {
    PermissionCommand addCommand = commandExecutors.get(COMMANDS.ADD.name());
    StaffInfo ceo = new StaffInfo("CEO", null);
    ceo.addPermission(permissionsOfStaffs.get(0));
    staffInfos.put("CEO", ceo);
    addCommand.execute(staffInfos, "CEO", permissionsOfStaffs.get(0));

    for (int i = 1; i <= numberOfStaff; i++) {
      String managerId = managerOfStaffs.get(i - 1);
      String id = String.valueOf(i);
      StaffInfo staff = new StaffInfo(id, managerId);
      staffInfos.put(id, staff);

      addCommand.execute(staffInfos, id, permissionsOfStaffs.get(i));
    }
  }
  
  public void printStaffPermissions() {
    staffInfos.values().stream().forEach(s -> System.out.println(s.getPermissions()));
  }

  public void processCommands() {
    PermissionCommand commandExecutor;
    for (String[] command : commands) {
      String operator = command[0];
      String staffId = command[1];
      String[] permissions = Arrays.copyOfRange(command, 2, command.length);
      commandExecutor = commandExecutors.get(operator);
      commandExecutor.execute(staffInfos, staffId, permissions);
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
      String[] command = scanner.nextLine().split(TOKEN);
      if (commandExecutors.get(command[0]) != null) {
        commands.add(command);
      } else {
        break;
      }
    }
  }
}
