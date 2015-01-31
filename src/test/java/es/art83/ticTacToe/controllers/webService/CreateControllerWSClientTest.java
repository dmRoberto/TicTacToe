package es.art83.ticTacToe.controllers.webService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.art83.ticTacToe.controllers.LogoutController;
import es.art83.ticTacToe.controllers.webService.ControllerFactoryWebService;
import es.art83.ticTacToe.controllers.webService.CreateControllerWebService;
import es.art83.ticTacToe.models.entities.PlayerEntity;
import es.art83.ticTacToe.webService.utils.WS;
import es.art83.ticTacToe.webService.utils.WebServicesManager;

public class CreateControllerWSClientTest {

    private CreateControllerWebService create;

    private PlayerEntity playerEntity;

    private LogoutController logout;

    @Before
    public void before() {
        ControllerFactoryWebService factory = new ControllerFactoryWebService();
        this.create = (CreateControllerWebService) factory.getCreateGameControler();
        this.logout = factory.getLogoutController();
        this.playerEntity = new PlayerEntity("u", "pass");
        factory.getLoginController().register(playerEntity);
    }

    @Test
    public void testCreateGame() {
        this.create.createGame();
    }

    @After
    public void after() {
        this.logout.logout();
        new WebServicesManager<>(WS.PATH_SESSIONS, this.create.getSessionId())
                .delete();
        new WebServicesManager<>(WS.PATH_PLAYERS, this.playerEntity.getUser())
                .delete();
    }
}
