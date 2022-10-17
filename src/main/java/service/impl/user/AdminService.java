package service.impl.user;

public interface AdminService extends BaseUserService {

    boolean muteUser(Long userId);
}
