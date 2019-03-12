package inf112.skeleton.app.server;

import inf112.skeleton.app.core.interfaces.IAction;

/**
 * Class for two way communication between server and client.
 *
 * Client should instantiate an implementation of this class
 * that takes as argument an implementation of the
 * {@link IAction IAction} interface.
 *
 * Implementation of this class should handle errors and try
 * to resolve them automatically. It should also call the
 * {@link IAction#handleERROR(String) handleERROR} method
 * with the status of the errors.
 *
 * Client actions should be implemented as methods in this
 * class. The methods should only return void.
 *
 * The implementation of this class should handle
 * communication with a server. you implement handling of the
 * server responses in the {@link IAction IAction.handleX()}
 * methods.
 */
public abstract class API {
    /**
     * Handler for events raised by the server. For example
     * when #getBoard() is called, the method should call the
     * related handler when the board is retrieved.
     */
    public IAction handler;

    public API(IAction handler) {
        this.handler = handler;
    }

    public abstract void getBoard();
    public abstract void drawCard();

    public abstract void createRoom();
    public abstract void joinRoom(String id);

    public abstract void connect() throws InterruptedException;
}
