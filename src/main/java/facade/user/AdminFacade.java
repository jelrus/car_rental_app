package facade.user;

public interface AdminFacade extends BaseUserFacade {

    boolean muteUser(Long userId);
}
