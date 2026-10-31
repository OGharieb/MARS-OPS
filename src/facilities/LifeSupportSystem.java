package facilities;
import java.util.ArrayList;
import java.util.List;
import agents.Agent;
import resources.Resources;

public class LifeSupportSystem {
    private List<Agent> agents;
    private Resources resources;

    public LifeSupportSystem(List<Agent> agents,Resources resources){
        this.agents=agents;
        this.resources=resources;   
    }
    public boolean isHabitable(){

        if (resources.getAmount(enums.ResourceType.OXYGEN) > agents.size() && resources.getAmount(enums.ResourceType.WATER) > agents.size() && resources.getAmount(enums.ResourceType.FOOD) > agents.size()) {
            return true;
        } else {
            return false;
        }
    }
    
}
