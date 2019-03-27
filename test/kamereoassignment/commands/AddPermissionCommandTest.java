package kamereoassignment.commands;

import java.util.Map;
import kamereoassignment.StaffInfo;
import kamereoassignment.Utils;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author vodongdu
 */
public class AddPermissionCommandTest {

  @Test
  public void testExecuteWithCEO() {
    String newPermission = "E";
    Map<String, StaffInfo> staffInfos = Utils.createStaffInfos();
    AddPermissionCommand instance = new AddPermissionCommand(staffInfos);
    String staffId = "CEO";
    String[] permissions = new String[]{newPermission};
    instance.execute(staffId, permissions);

    Assert.assertTrue(staffInfos.get("CEO").hasPermission(newPermission));
    Assert.assertEquals("A, B, C, E", staffInfos.get("CEO").getPermissions());
    Assert.assertFalse(staffInfos.get("1").hasPermission(newPermission));
    Assert.assertFalse(staffInfos.get("2").hasPermission(newPermission));
    Assert.assertFalse(staffInfos.get("3").hasPermission(newPermission));
  }

  @Test
  public void testExecuteWithStaff() {
    Map<String, StaffInfo> staffInfos = Utils.createStaffInfos();
    AddPermissionCommand instance = new AddPermissionCommand(staffInfos);
    String staffId = "3";
    String[] permissions = new String[]{"E", "F"};
    instance.execute(staffId, permissions);

    Assert.assertTrue(staffInfos.get(staffId).hasPermissions(permissions));
    Assert.assertEquals("A, E, F", staffInfos.get(staffId).getPermissions());
    Assert.assertTrue(staffInfos.get("CEO").hasPermissions(permissions));
    Assert.assertEquals("A, B, C, E, F", staffInfos.get("CEO").getPermissions());
    Assert.assertTrue(staffInfos.get("1").hasPermissions(permissions));
    Assert.assertEquals("A, C, E, F", staffInfos.get("1").getPermissions());
    Assert.assertFalse(staffInfos.get("2").hasPermissions(permissions));
  }
}
