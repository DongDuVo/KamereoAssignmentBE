package kamereoassignment.commands;

import java.util.Map;
import kamereoassignment.StaffInfo;

/**
 *
 * @author vodongdu
 */
public class AddPermissionCommand implements PermissionCommand {
  
  private final Map<String, StaffInfo> staffInfos;
  
  public AddPermissionCommand(Map<String, StaffInfo> staffInfos) {
    this.staffInfos = staffInfos;
  }

  @Override
  public void execute(String staffId, String ...permissions) {
    StaffInfo staff = staffInfos.get(staffId);
    if (staff == null) return;
    staff.addPermission(permissions);

    String managerId = staff.getManager();
    StaffInfo manager;
    while (managerId != null) {
      manager = staffInfos.get(managerId);
      if (manager == null) break;
      manager.addPermission(permissions);
      managerId = manager.getManager();
    }
  }

}
