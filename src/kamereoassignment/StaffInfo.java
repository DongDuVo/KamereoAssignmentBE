package kamereoassignment;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author vodongdu
 */
public class StaffInfo {

  private final String id;
  private final Set<String> permissions = new HashSet<>();
  private final String manager;
  private static final String DELIMETER = ", ";

  public StaffInfo(String id, String manager) {
    this.id = id;
    this.manager = manager;
  }

  public String getManager() {
    return manager;
  }

  public void addPermission(String ...permissions) {
    Collections.addAll(this.permissions, permissions);
  }

  public void removePermission(String permission) {
    this.permissions.remove(permission);
  }

  public String getPermissions() {
    String[] temps = this.permissions.toArray(new String[0]);
    Arrays.sort(temps);
    return String.join(DELIMETER, temps);
  }

  public String getId() {
    return id;
  }
  
  public boolean hasPermission(String permission) {
    return permissions.contains(permission);
  }
}
