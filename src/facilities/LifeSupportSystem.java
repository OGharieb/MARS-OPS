package facilities;
import agents.Agent;
import java.util.List;
import resources.Resources;

public class LifeSupportSystem {
    private final List<Agent> agents;
    private final Resources resources;

    public LifeSupportSystem(List<Agent> agents,Resources resources){
        this.agents=agents;
        this.resources=resources;   
    }
    public boolean isHabitable(){

        return resources.getAmount(enums.ResourceType.OXYGEN) > agents.size() &&
         resources.getAmount(enums.ResourceType.WATER) > agents.size() && 
         resources.getAmount(enums.ResourceType.FOOD) > agents.size();
       
    }
    
}
