package kamereoassignment.commands;

import java.util.Map;
import kamereoassignment.StaffInfo;
import kamereoassignment.Utils;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author duvo
 */
public class RemovePermissionCommandTest {

  @Test
  public void testExecuteWithCEO() {
    Map<String, StaffInfo> staffInfos = Utils.createStaffInfos();
    RemovePermissionCommand instance = new RemovePermissionCommand(staffInfos);
    String staffId = "CEO";
    String permission = "A";
    instance.execute(staffId, permission);
    
    Assert.assertFalse(staffInfos.get(staffId).hasPermission(permission));
    Assert.assertEquals("B, C", staffInfos.get(staffId).getPermissions());
  }
  
}
