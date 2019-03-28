package kamereoassignment.commands;

import java.util.Map;
import kamereoassignment.StaffInfo;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author duvo
 */
public class RemovePermissionCommandTest {

  @Test
  public void removePermisionOnlyGrantedForCEO() {
    Map<String, StaffInfo> staffInfos = Utils.createStaffInfos();
    RemovePermissionCommand instance = new RemovePermissionCommand(staffInfos);
    String staffId = "CEO";
    String permission = "D";
    instance.execute(staffId, permission);
    
    Assert.assertFalse(staffInfos.get(staffId).hasPermission(permission));
    Assert.assertEquals("A, B, C", staffInfos.get(staffId).getPermissions());
    Assert.assertEquals("A, C", staffInfos.get("1").getPermissions());
    Assert.assertEquals("B, C", staffInfos.get("2").getPermissions());
    Assert.assertEquals("A", staffInfos.get("3").getPermissions());
  }
  
  @Test
  public void removePermisionOnlyGrantedForStaffAndManger() {
    Map<String, StaffInfo> staffInfos = Utils.createStaffInfos();
    RemovePermissionCommand instance = new RemovePermissionCommand(staffInfos);
    String staffId = "2";
    String permission = "B";
    instance.execute(staffId, permission);
    
    Assert.assertFalse(staffInfos.get(staffId).hasPermission(permission));
    Assert.assertEquals("C", staffInfos.get(staffId).getPermissions());
    Assert.assertEquals("A, C", staffInfos.get("1").getPermissions());
    Assert.assertFalse(staffInfos.get("CEO").hasPermission(permission));
    Assert.assertEquals("A, C, D", staffInfos.get("CEO").getPermissions());
  }
  
  @Test
  public void removePermisionGrantedForStaffsHaveSameManger() {
    Map<String, StaffInfo> staffInfos = Utils.createStaffInfos();
    RemovePermissionCommand instance = new RemovePermissionCommand(staffInfos);
    String staffId = "2";
    String permission = "C";
    instance.execute(staffId, permission);
    
    Assert.assertFalse(staffInfos.get(staffId).hasPermission(permission));
    Assert.assertEquals("B", staffInfos.get(staffId).getPermissions());
    Assert.assertEquals("A, C", staffInfos.get("1").getPermissions());
    Assert.assertTrue(staffInfos.get("CEO").hasPermission(permission));
    Assert.assertEquals("A, B, C, D", staffInfos.get("CEO").getPermissions());
  }
  
  @Test
  public void removePermisionGrantedForStaffAndMangerAndMangingStaffs() {
    Map<String, StaffInfo> staffInfos = Utils.createStaffInfos();
    RemovePermissionCommand instance = new RemovePermissionCommand(staffInfos);
    String staffId = "1";
    String permission = "A";
    instance.execute(staffId, permission);
    
    Assert.assertFalse(staffInfos.get(staffId).hasPermission(permission));
    Assert.assertEquals("C", staffInfos.get(staffId).getPermissions());
    Assert.assertTrue(staffInfos.get("3").getPermissions().isEmpty());
    Assert.assertFalse(staffInfos.get("CEO").hasPermission(permission));
    Assert.assertEquals("B, C, D", staffInfos.get("CEO").getPermissions());
  }
  
  @Test
  public void removePermisionGrantedForStaffAndMangingStaffs() {
    Map<String, StaffInfo> staffInfos = Utils.createStaffInfos();
    RemovePermissionCommand instance = new RemovePermissionCommand(staffInfos);
    String staffId = "CEO";
    String permission = "C";
    instance.execute(staffId, permission);
    
    Assert.assertFalse(staffInfos.get(staffId).hasPermission(permission));
    Assert.assertEquals("A, B, D", staffInfos.get(staffId).getPermissions());
    Assert.assertFalse(staffInfos.get("1").hasPermission(permission));
    Assert.assertEquals("A", staffInfos.get("1").getPermissions());
    Assert.assertFalse(staffInfos.get("2").hasPermission(permission));
    Assert.assertEquals("B", staffInfos.get("2").getPermissions());
  }
}
