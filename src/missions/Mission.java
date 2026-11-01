
package missions;
 
import agents.Agent;
import agents.ExplorationRover;
import colony.MarsColony;
import enums.AgentRole;
import enums.MissionState;
import enums.MissionType;
import enums.SkillType;
import equipments.Equipment;
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
   protected List<Equipment> assignedEquipment;
   protected String failureReason="" ;
 
    public Mission(String location,SkillType requiredSkill,int duration,MissionType missionType) {
        this.location=location;
        this.requiredSkill=requiredSkill;
        this.duration=duration;
        this.elapsedTime=0;
        this.state= MissionState.PENDING;
        this.assignedAgents = new ArrayList<>();
        this.assignedEquipment = new ArrayList<>();
        this.missionType=missionType;
    }
 
    public MissionType getMissionType() {
        return missionType;
    }
 
    public String getLocation() {
        return location;
    }
 
    public void assign(Agent agent){
        this.assignedAgents.add(agent);
        agent.setAvailable(false);
        if(agent.getRole()==AgentRole.EXPLORATION_ROVER){
            ((ExplorationRover) agent).assignExplorationMission(this);
            agent.setLocationToMartianSurface();
        }
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public String getFailureReason() {
        return failureReason;
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
 

    public void setState(MissionState newState){
        if(this.state == MissionState.COMPLETED || this.state == MissionState.FAILED){
            return;
        }
        this.state = newState;
        if(newState == MissionState.COMPLETED || newState == MissionState.FAILED){
            releaseTeamAndEquipment();
        }
    }
 
    private void releaseTeamAndEquipment(){
        for(Agent agent : assignedAgents){
            agent.setAvailable(true);
            if(agent.getRole()==AgentRole.EXPLORATION_ROVER){
                ExplorationRover rover = (ExplorationRover) agent;
                if(rover.assignedMission == this){
                    rover.assignedMission = null;
                }
            }
        }
        for(Equipment eq : assignedEquipment){
            eq.setAssigned(false);
        }
        assignedEquipment.clear();
    }
 
   
    public void advanceTime(){
        if(this.state == MissionState.ASSIGNED){
            this.state = MissionState.IN_PROGRESS;
        }
        if(this.state != MissionState.IN_PROGRESS){
            return;
        }
        this.elapsedTime++;

        for (Agent agent : assignedAgents) {
            if (agent.isHuman()) {
                agents.CrewMember crew = (agents.CrewMember) agent;
                crew.setFatigue(crew.getFatigue() + 10);
            } else if (agent.isRobot()) {
                agents.RoboticUnit robot = (agents.RoboticUnit) agent;
                robot.drainBattery(10);
                if (robot.getBatteryLevel() <= 0) {
                    this.setFailureReason("insufficient battery");
                    this.setState(MissionState.FAILED);
                    return;
                }
            }
        }

        if(this.elapsedTime>=this.duration){
            this.setState(MissionState.COMPLETED);
            System.out.println("Mission at " + location + " completed.");
        }
    }
    public abstract void assignEquipment(MarsColony colony);
    public abstract void consumeResources(MarsColony colony);
    public abstract boolean  assembleTeam(List<Agent> availableAgents);
    public abstract boolean hasSufficientRecourses(MarsColony colony);
    public abstract boolean hasRequiredEquipment(MarsColony colony);
}