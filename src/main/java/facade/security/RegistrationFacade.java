package facade.security;

import view.dto.request.register.AuthDto;

public interface RegistrationFacade {

    void register(AuthDto authDto);
}
