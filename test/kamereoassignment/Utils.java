package kamereoassignment;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author duvo
 */
public class Utils {
  public static Map<String, StaffInfo> createStaffInfos() {
    Map<String, StaffInfo> staffInfos = new HashMap<>();

    StaffInfo ceo = new StaffInfo("CEO", null);
    ceo.addPermission(new String[]{"A", "B", "C"});

    StaffInfo first = new StaffInfo("1", "CEO");
    first.addPermission(new String[]{"A", "C"});

    StaffInfo second = new StaffInfo("2", "CEO");
    second.addPermission(new String[]{"B"});

    StaffInfo third = new StaffInfo("3", "1");
    third.addPermission(new String[]{"A"});

    staffInfos.put("CEO", ceo);
    staffInfos.put("1", first);
    staffInfos.put("2", second);
    staffInfos.put("3", third);
    return staffInfos;
  }
}
