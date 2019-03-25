package kamereoassignment.commands;

import java.util.Map;
import kamereoassignment.StaffInfo;

/**
 *
 * @author vodongdu
 */
public interface PermissionCommand {

  public void execute(Map<String, StaffInfo> staffInfos, String staffId, String ...permissions);
}
