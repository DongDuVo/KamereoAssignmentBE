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
  private List<String[]> commands = new LinkedList<>();
  private final Scanner scanner;
  private int numberOfStaff;
  private final Map<String, StaffInfo> staffInfos = new LinkedHashMap<>();
  private final Map<String, PermissionCommand> commandExecutors = new HashMap<>();

  public StaffManagement(Scanner scanner) {
    this.scanner = scanner;
    commandExecutors.put(COMMANDS.ADD.name(), new AddPermissionCommand(staffInfos));
    commandExecutors.put(COMMANDS.QUERY.name(), new QueryPermissionCommand(staffInfos));
    commandExecutors.put(COMMANDS.REMOVE.name(), new RemovePermissionCommand(staffInfos));
  }
  
  public List<String[]> getCommands() {
    return commands;
  }

  public Map<String, StaffInfo> getStaffInfos() {
    return staffInfos;
  }

  public void processInputData() {
    numberOfStaff = Integer.parseInt(scanner.nextLine());
    
    List<String[]> permissionsOfStaffs = readPermissionsOfStaffs();
    List<String> mangerOfStaffs = readMangerOfStaffs();
    commands = readCommands();
    buildStaffInfos(permissionsOfStaffs, mangerOfStaffs);
  }

  protected void buildStaffInfos(List<String[]> permissionsOfStaffs, List<String> managerOfStaffs) {
    PermissionCommand addCommand = commandExecutors.get(COMMANDS.ADD.name());
    StaffInfo ceo = new StaffInfo("CEO", null);
    ceo.addPermission(permissionsOfStaffs.get(0));
    staffInfos.put("CEO", ceo);
    addCommand.execute("CEO", permissionsOfStaffs.get(0));

    for (int i = 1; i <= numberOfStaff; i++) {
      String managerId = managerOfStaffs.get(i - 1);
      String id = String.valueOf(i);
      StaffInfo staff = new StaffInfo(id, managerId);
      staffInfos.put(id, staff);
      
      StaffInfo manager = staffInfos.get(managerId);
      manager.addManagingStaffs(id);

      addCommand.execute(id, permissionsOfStaffs.get(i));
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
      commandExecutor.execute(staffId, permissions);
    }
  }

  protected List<String[]> readPermissionsOfStaffs() {
    List<String[]> permissionsOfStaffs = new LinkedList<>();
    for (int i = 0; i <= numberOfStaff; i++) {
      String[] permisstions = scanner.nextLine().split(TOKEN);
      permissionsOfStaffs.add(permisstions);
    }
    return permissionsOfStaffs;
  }

  protected List<String> readMangerOfStaffs() {
    List<String> managerOfStaffs = new LinkedList<>();
    for (int i = 0; i < numberOfStaff; i++) {
      String manager = scanner.nextLine();
      managerOfStaffs.add(manager);
    }
    return managerOfStaffs;
  }

  protected List<String[]> readCommands() {
    List<String[]> extraCommands = new LinkedList<>();
    while (scanner.hasNextLine()) {
      String[] command = scanner.nextLine().split(TOKEN);
      if (commandExecutors.get(command[0]) != null) {
        extraCommands.add(command);
      } else {
        break;
      }
    }
    return extraCommands;
  }
}
