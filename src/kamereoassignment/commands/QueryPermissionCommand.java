package kamereoassignment.commands;

import java.util.Map;
import kamereoassignment.StaffInfo;

/**
 *
 * @author vodongdu
 */
public class QueryPermissionCommand implements PermissionCommand {

  @Override
  public void execute(Map<String, StaffInfo> staffInfos, String staffId, String ...permissions) {
    StaffInfo staff = staffInfos.get(staffId);
    if (staff == null) return;
    System.out.println(staff.getPermissions());
  }

}
