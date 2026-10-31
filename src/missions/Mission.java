package missions;

import agents.Agent;
import colony.MarsColony;
import enums.MissionState;
import enums.MissionType;
import enums.SkillType;
import java.util.ArrayList;
import java.util.List;

public abstract class Mission {
   protected String location;
   protected SkillType requiredSkill;
   protected MissionType missionType;
   protected int elapsedTime=0;
   protected int duration;
   protected MissionState state;
   protected List<Agent> assignedAgents;

    public Mission(String location,SkillType requiredSkill,int duration,MissionType missionType) {
        this.location=location;
        this.requiredSkill=requiredSkill;
        this.duration=duration;
        this.elapsedTime=0;
        this.state= MissionState.PENDING;
        this.assignedAgents = new ArrayList<>();
        this.missionType=missionType;
    }

    public MissionType getMissionType() {
        return missionType;
    }


    public void assign(Agent agent){
        this.assignedAgents.add(agent);
    }
    
    public List<Agent> getAgents(){
    return this.assignedAgents;
   }
    

    public SkillType getRequiredSkill(){
        return this.requiredSkill;
    }
    public MissionState getState(){
        return this.state;
    }

    public void setState(MissionState state){
        this.state = state;
        if(state == MissionState.COMPLETED || state == MissionState.FAILED){
            for(Agent agent : assignedAgents){
                agent.setAvailable(true);
            }
        }
    }

    public void advanceTime(){
        if(this.state.equals(MissionState.IN_PROGRESS)){
            this.elapsedTime++;
        }
        if(this.elapsedTime>=this.duration){
            this.setState(MissionState.COMPLETED);
        }
    }
    public abstract void assignEquipment(MarsColony colony);
    public abstract void consumeResources(MarsColony colony);
    public abstract boolean  assembleTeam(List<Agent> availableAgents);
    public abstract boolean hasSufficientRecourses(MarsColony colony);
    public abstract boolean hasRequiredEquipment(MarsColony colony);


    

    
}
