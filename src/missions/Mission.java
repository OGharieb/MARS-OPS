package missions;

import agents.Agent;
import colony.MarsColony;
import enums.MissionState;
import java.util.List;

public abstract class Mission {
   protected String location;
   protected List<String> requiredSkills;
   protected int elapsedTime;
   protected int duration;
   protected MissionState state;
   protected List<Agent> assignedAgents;

    public Mission(String location,List<String> requiredSkills,int duration) {
        this.location=location;
        this.requiredSkills=requiredSkills;
        this.duration=duration;
        this.elapsedTime=0;
        this.state=null;
        this.assignedAgents =null;
    }

    

    public void assign(Agent agent){
        this.assignedAgents.add(agent);
    }
    
    public List<Agent> getAgents(){
    return this.assignedAgents;
   }
    

    public List<String> getRequiredSkills(){
        return this.requiredSkills;
    }
    public MissionState getState(){
        return this.state;
    }

    public void setState(MissionState state){
         this.state = state;
    }

    public abstract boolean hasSufficientRecourses(MarsColony colony);


    

    
}
