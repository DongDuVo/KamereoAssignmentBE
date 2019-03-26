package kamereoassignment.commands;

import java.util.HashMap;
import java.util.Map;
import kamereoassignment.StaffInfo;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author vodongdu
 */
public class AddPermissionCommandTest {

  public AddPermissionCommandTest() {
  }

  private Map createStaffInfos() {
    Map<String, StaffInfo> staffInfos = new HashMap<>();

    StaffInfo ceo = new StaffInfo("CEO", null);
    ceo.addPermission(new String[]{"A", "B", "C"});

    StaffInfo first = new StaffInfo("1", "CEO");
    first.addPermission(new String[]{"A", "C"});

    StaffInfo second = new StaffInfo("2", "CEO");
    first.addPermission(new String[]{"B"});

    StaffInfo third = new StaffInfo("3", "1");
    first.addPermission(new String[]{"A"});

    staffInfos.put("CEO", ceo);
    staffInfos.put("1", first);
    staffInfos.put("2", second);
    staffInfos.put("3", third);
    return staffInfos;
  }

  @Test
  public void testExecuteWithCEO() {
    Map<String, StaffInfo> staffInfos = createStaffInfos();
    String staffId = "CEO";
    String[] permissions = new String[]{"E"};
    AddPermissionCommand instance = new AddPermissionCommand();
    instance.execute(staffInfos, staffId, permissions);

    Assert.assertTrue(staffInfos.get("CEO").hasPermission("E"));
    Assert.assertFalse(staffInfos.get("1").hasPermission("E"));
    Assert.assertFalse(staffInfos.get("2").hasPermission("E"));
    Assert.assertFalse(staffInfos.get("3").hasPermission("E"));
  }

  @Test
  public void testExecuteWithStaff() {
    Map<String, StaffInfo> staffInfos = createStaffInfos();
    String staffId = "3";
    String[] permissions = new String[]{"E", "F"};
    AddPermissionCommand instance = new AddPermissionCommand();
    instance.execute(staffInfos, staffId, permissions);

    Assert.assertTrue(staffInfos.get("CEO").hasPermission("E"));
    Assert.assertTrue(staffInfos.get("CEO").hasPermission("F"));
    Assert.assertTrue(staffInfos.get("1").hasPermission("E"));
    Assert.assertTrue(staffInfos.get("1").hasPermission("F"));
    Assert.assertFalse(staffInfos.get("2").hasPermission("E"));
    Assert.assertFalse(staffInfos.get("2").hasPermission("F"));
    Assert.assertTrue(staffInfos.get("3").hasPermission("E"));
    Assert.assertTrue(staffInfos.get("3").hasPermission("F"));
  }
}
