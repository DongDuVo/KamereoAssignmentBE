package kamereoassignment.commands;

import java.util.Map;
import kamereoassignment.StaffInfo;

/**
 *
 * @author vodongdu
 */
public class RemovePermissionCommand implements PermissionCommand {

  @Override
  public void execute(Map<String, StaffInfo> staffInfos, String staffId, String ...permissions) {
    StaffInfo staff = staffInfos.get(staffId);
    if (staff == null) return;
    String permission = permissions[0];
    staff.removePermission(permission);

    String managerId = staff.getManager();
    StaffInfo manager;
    while (managerId != null) {
      String mId = managerId;
      long numberOfStaff = staffInfos.values().stream().filter(s -> !s.getId().equals(staffId)
          && mId.equals(s.getManager())
          && s.hasPermission(permission)
      ).count();
      if (numberOfStaff > 0) return;
      manager = staffInfos.get(managerId);
      if (manager == null) return;
      manager.removePermission(permission);
      managerId = manager.getManager();
    }
  }

}
