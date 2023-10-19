import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class SpaceSearcher {
    private ArrayList<Cube> frontier;
    private HashSet<Cube> closedSet;

    SpaceSearcher() {
        this.frontier = new ArrayList<>(); // store states of cube
        this.closedSet = new HashSet<>(); // closed set
    }

    

    Cube A_Star(Cube initialstate, int k) {

        if (initialstate.check_one(k)) // if initial state is final return the state
            return initialstate;

        this.frontier.add(initialstate);  // add state to the frontier

        while (!this.frontier.isEmpty()) { // while frontier still has states

            Cube currentState = this.frontier.remove(0); // remove the first state 
            
            if (currentState.check_one(k)) { // if its final
                return currentState;
            }
            if (!closedSet.contains(currentState)) {
                closedSet.add(currentState);
                this.frontier.addAll(currentState.get_children()); // expand children
                Collections.sort(this.frontier); // sort the frontier to get best as first
                
            }
            currentState.set_Child(this.frontier.get(0));
        }
        return null;
    }

}
