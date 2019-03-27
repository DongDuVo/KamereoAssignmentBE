package kamereoassignment.commands;

import java.util.Map;
import java.util.Set;
import kamereoassignment.StaffInfo;

/**
 *
 * @author vodongdu
 */
public class RemovePermissionCommand implements PermissionCommand {
  
  private final Map<String, StaffInfo> staffInfos;
  
  public RemovePermissionCommand(Map<String, StaffInfo> staffInfos) {
    this.staffInfos = staffInfos;
  }

  @Override
  public void execute(String staffId, String ...permissions) {
    StaffInfo staff = staffInfos.get(staffId);
    if (staff == null) return;
    String permission = permissions[0];
    staff.removePermission(permission);

    removePermissionForManager(staff, permission);
    removePermissionForManagingStaffs(staff.getManagingStaffs(), permission);
  }

  private void removePermissionForManagingStaffs(Set<String> staffIds, String permission) {
    if (staffIds == null || staffIds.isEmpty()) return;
    staffIds.forEach(staffId -> {
      StaffInfo staff = staffInfos.get(staffId);
      if (staff != null && staff.hasPermission(permission)) {
        staff.removePermission(permission);
        removePermissionForManagingStaffs(staff.getManagingStaffs(), permission);
      }
    });
  }
  
  private void removePermissionForManager(StaffInfo staff, String permission) {
    String managerId = staff.getManager();
    if (managerId == null) return;
    StaffInfo manager = staffInfos.get(managerId);
    if (manager == null) return;
    boolean cannotRemove = manager.getManagingStaffs().stream().anyMatch(staffId -> !staffId.equals(staff.getId())
        && staffInfos.get(staffId).hasPermission(permission));
    if (cannotRemove) return;
    manager.removePermission(permission);
    removePermissionForManager(manager, permission);
  }
}
