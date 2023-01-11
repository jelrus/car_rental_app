package service.interaction;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import persistence.dao.interaction.ActionDao;
import persistence.entity.interaction.Action;
import service.interaction.impl.ActionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ActionServiceImplTest {

    @Mock
    ActionDao actionDao;

    @InjectMocks
    ActionServiceImpl actionService;

    @Test
    public void create() {
        Action action = new Action();
        action.setId(1L);
        action.setMessage("tst");
        action.setIdentifier("tst1");
        action.setEnabled(true);
        System.out.println(actionService);
        System.out.println(actionDao);
    }
}