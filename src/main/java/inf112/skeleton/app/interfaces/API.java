package inf112.skeleton.app.interfaces;

/**
 * Class for two way communication between server and client.
 *
 * Client should instantiate an implementation of this class
 * that takes as argument an implementation of the
 * #IAction interface.
 *
 * Implementation of this class should handle errors and try
 * to resolve them automatically. It should also call
 * #IAction.handleX() method with status of the errors.
 *
 * Client actions should be implemented as methods in this
 * class. The methods should only return void.
 *
 * The implementation of this class should handle
 * communication with a server. you implement client handling
 * of the server responses in the #IAction.handleX() methods.
 */
public interface API {
    void getBoard();
}
