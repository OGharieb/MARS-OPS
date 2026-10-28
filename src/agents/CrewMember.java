package agents;

import java.util.List;

public abstract  class CrewMember implements Agent {
    protected String id;
    protected String name;
    protected String location;
    protected boolean available;
    protected List<String> skills;
    protected int fatigueLevel;
    protected int healthStatus;

    public CrewMember(String id,String location,String name){
        this.id=id;
        this.name=name;
        this.location=location;
        this.available=true;
        this.healthStatus=100;
        this.fatigueLevel=0;

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
    public String getName(){
        return this.name;
    }
    public int getHealthStatus(){
        return this.healthStatus;
    }
    public void setHealthStatus(int healthStatus){
        this.healthStatus=Math.max(0, Math.min(100,healthStatus));
    }
    public int getFatigueLevel(){
        return this.fatigueLevel;
    }
    public void incrementFatigue(int value){
        this.fatigueLevel=Math.min(100,this.fatigueLevel+value); 
    }
    public void rest(){
        this.fatigueLevel=0;
    }



    public abstract void performDuty();
 



    
}
