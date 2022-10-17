package facade.security.impl;

import facade.security.RegistrationFacade;
import persistence.entity.user.impl.User;
import service.impl.user.UserService;
import service.impl.user.impl.UserServiceImpl;
import view.dto.request.register.AuthDto;

public class RegistrationFacadeImpl implements RegistrationFacade {

    private final UserService userService;

    public RegistrationFacadeImpl() {
        this.userService = new UserServiceImpl();
    }

    @Override
    public void register(AuthDto authDto) {
        User standardUser = new User();
        standardUser.setUsername(authDto.getUsername());
        standardUser.setPassword(authDto.getPassword());
        standardUser.setProfilePic("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__340.png");
        standardUser.setFirstName("");
        standardUser.setLastName("");
        standardUser.setDescription("");
        userService.create(standardUser);
    }
}
