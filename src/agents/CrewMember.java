package agents;

import enums.AgentRole;
import enums.SkillType;
import java.util.List;

public abstract  class CrewMember implements Agent {
    protected String id;
    protected String name;
    protected String location;
    protected boolean available;
    protected List<SkillType> skills;
    protected int healthStatus;
    AgentRole role;

    public CrewMember(String id,String location,String name,AgentRole role,List<SkillType> skills){
        this.id=id;
        this.name=name;
        this.location=location;
        this.available=true;
        this.healthStatus=100;
        this.role= role;
        this.skills=skills;
    }
    
    @Override 
    public AgentRole getRole() {
        return role;
    }

    @Override 
    public String getId(){
        return this.id;
    }
    @Override 
    public String getLocation(){
        return this.location;
    }
    @Override
    public void setLocation(String location){
        this.location=location;
    }
    @Override 
    public boolean isAvailable(){
        return this.available&&this.healthStatus>0;
    }
    @Override 
    public void setAvailable(boolean available){
        this.available=available;
    }
    @Override 
    public boolean isRobot(){
        return false;
    }
    @Override 
    public boolean isHuman(){
        return true;
    }
    public String getName(){
        return this.name;
    }
    public int getHealthStatus(){
        return this.healthStatus;
    }
    public void setHealthStatus(int healthStatus){
        this.healthStatus=Math.max(0, Math.min(100,healthStatus));
    }
    
 



    
}
