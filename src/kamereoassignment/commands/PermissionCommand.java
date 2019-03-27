package kamereoassignment.commands;

/**
 *
 * @author vodongdu
 */
public interface PermissionCommand {

  public void execute(String staffId, String ...permissions);
}
