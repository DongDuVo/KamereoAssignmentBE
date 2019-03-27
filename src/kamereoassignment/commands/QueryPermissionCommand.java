package kamereoassignment.commands;

import java.util.Map;
import kamereoassignment.StaffInfo;

/**
 *
 * @author vodongdu
 */
public class QueryPermissionCommand implements PermissionCommand {
  
  private final Map<String, StaffInfo> staffInfos;
  
  public QueryPermissionCommand(Map<String, StaffInfo> staffInfos) {
    this.staffInfos = staffInfos;
  }

  @Override
  public void execute(String staffId, String ...permissions) {
    StaffInfo staff = staffInfos.get(staffId);
    if (staff == null) return;
    System.out.println(staff.getPermissions());
  }

}
