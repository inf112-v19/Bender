package inf112.skeleton.app.core.game;

// GAME CLASS to hold game states DEAL, USER and EXEC (maybe more detail?)
public class Game {

    // Where is the state logic, to be able to check if a stage is finished?
    // Where should control of states be, in Game or Board?

    // ENUM for states
    public enum State {
        DEALING, USER, EXECUTE;

        public State state;

        // sets state directly
        public void setState(State newState) {
            this.state=newState;
        }

        // return current state
        public State getState() {
        return this.state;
        }

        // set next state according to getState();
        public void nextState(){
            if (this.state==DEALING) {this.state=USER;}
            else if (this.state==USER) {this.state=EXECUTE;}
            else if (this.state==EXECUTE) {this.state=DEALING;}
        }
    }
}
