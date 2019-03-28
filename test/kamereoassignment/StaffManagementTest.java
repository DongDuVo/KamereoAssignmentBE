package kamereoassignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author duvo
 */
public class StaffManagementTest {
  
  public StaffManagementTest() {
  }

  @Test
  public void testProcessInputData() throws FileNotFoundException {
    Scanner scanner = new Scanner(new File("testdata.txt"), "utf-8");
    StaffManagement instance = new StaffManagement(scanner);
    instance.processInputData();
    
    Map<String, StaffInfo> staffInfos = instance.getStaffInfos();
    List<String[]> commands = instance.getCommands();
    Assert.assertEquals(7, staffInfos.size());
    Assert.assertEquals(6, commands.size());
    
    Assert.assertNull(staffInfos.get("CEO").getManager());
    Assert.assertEquals("A, B, C, D, E, F", staffInfos.get("CEO").getPermissions());
    Assert.assertTrue(staffInfos.get("CEO").getManagingStaffs().containsAll(Arrays.asList("1", "2")));
    
    Assert.assertEquals("CEO", staffInfos.get("1").getManager());
    Assert.assertEquals("A, B, C, D", staffInfos.get("1").getPermissions());
    Assert.assertTrue(staffInfos.get("1").getManagingStaffs().containsAll(Arrays.asList("3", "4", "5")));
    
    Assert.assertEquals("CEO", staffInfos.get("2").getManager());
    Assert.assertEquals("A, B, C, E", staffInfos.get("2").getPermissions());
    Assert.assertTrue(staffInfos.get("2").getManagingStaffs().contains("6"));
    
    Assert.assertEquals("1", staffInfos.get("3").getManager());
    Assert.assertEquals("A", staffInfos.get("3").getPermissions());
    Assert.assertTrue(staffInfos.get("3").getManagingStaffs().isEmpty());
    
    Assert.assertEquals("1", staffInfos.get("4").getManager());
    Assert.assertEquals("D", staffInfos.get("4").getPermissions());
    Assert.assertTrue(staffInfos.get("4").getManagingStaffs().isEmpty());
    
    Assert.assertEquals("1", staffInfos.get("5").getManager());
    Assert.assertEquals("A, C", staffInfos.get("5").getPermissions());
    Assert.assertTrue(staffInfos.get("5").getManagingStaffs().isEmpty());
    
    Assert.assertEquals("2", staffInfos.get("6").getManager());
    Assert.assertEquals("A, B", staffInfos.get("6").getPermissions());
    Assert.assertTrue(staffInfos.get("6").getManagingStaffs().isEmpty());
  }
  
  @Test
  public void testReadPermissionsOfStaffs() {
    String input = "A F\nA B\nA C E\nA\nD\nA C\nA B";
    Scanner scanner = new Scanner(input);
    StaffManagement instance = new StaffManagement(scanner);
    List<String[]> permissionsOfStaffs = instance.readPermissionsOfStaffs(6);
    
    Assert.assertEquals(7, permissionsOfStaffs.size());
    Assert.assertArrayEquals(new String[]{"A", "F"}, permissionsOfStaffs.get(0));
    Assert.assertArrayEquals(new String[]{"A", "B"}, permissionsOfStaffs.get(1));
    Assert.assertArrayEquals(new String[]{"A", "C", "E"}, permissionsOfStaffs.get(2));
    Assert.assertArrayEquals(new String[]{"A"}, permissionsOfStaffs.get(3));
    Assert.assertArrayEquals(new String[]{"D"}, permissionsOfStaffs.get(4));
    Assert.assertArrayEquals(new String[]{"A", "C"}, permissionsOfStaffs.get(5));
    Assert.assertArrayEquals(new String[]{"A", "B"}, permissionsOfStaffs.get(6));
  }
  
  @Test
  public void testReadCommands() {
    String input = "ADD 2 X\nQUERY 2\nQUERY CEO\nREMOVE 2 X\nQUERY 2\nQUERY CEO";
    Scanner scanner = new Scanner(input);
    StaffManagement instance = new StaffManagement(scanner);
    List<String[]> commands = instance.readCommands();
    
    Assert.assertEquals(6, commands.size());
    Assert.assertArrayEquals(new String[]{"ADD", "2", "X"}, commands.get(0));
    Assert.assertArrayEquals(new String[]{"QUERY", "2"}, commands.get(1));
    Assert.assertArrayEquals(new String[]{"QUERY", "CEO"}, commands.get(2));
    Assert.assertArrayEquals(new String[]{"REMOVE", "2", "X"}, commands.get(3));
    Assert.assertArrayEquals(new String[]{"QUERY", "2"}, commands.get(4));
    Assert.assertArrayEquals(new String[]{"QUERY", "CEO"}, commands.get(5));
  }
  
  @Test
  public void testReadMangerOfStaffs() {
    String input = "CEO\nCEO\n1\n2\n1";
    Scanner scanner = new Scanner(input);
    StaffManagement instance = new StaffManagement(scanner);
    List<String> mangerOfStaffs = instance.readMangerOfStaffs(5);
    
    Assert.assertEquals(5, mangerOfStaffs.size());
    Assert.assertArrayEquals(new String[]{"CEO", "CEO", "1", "2", "1"}, mangerOfStaffs.toArray());
  }
}
