package kamereoassignment;

/**
 *
 * @author vodongdu
 */
public enum COMMANDS {
  ADD, REMOVE, QUERY;

  public static boolean find(String command) {
    for (COMMANDS value : COMMANDS.values()) {
      if (value.name().equals(command)) {
        return true;
      }
    }
    return false;
  }
}
