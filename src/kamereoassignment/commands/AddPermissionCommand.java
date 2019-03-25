package kamereoassignment.commands;

import java.util.Map;
import kamereoassignment.StaffInfo;

/**
 *
 * @author vodongdu
 */
public class AddPermissionCommand implements PermissionCommand {

  @Override
  public void execute(Map<String, StaffInfo> staffInfos, String staffId, String ...permissions) {
    StaffInfo staff = staffInfos.get(staffId);
    if (staff == null) return;
    staff.addPermission(permissions);

    String managerId = staff.getManager();
    StaffInfo manager;
    while (managerId != null) {
      manager = staffInfos.get(managerId);
      manager.addPermission(permissions);
      managerId = manager.getManager();
    }
  }

}
