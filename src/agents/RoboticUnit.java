package agents;

import enums.AgentRole;

public abstract class RoboticUnit implements Agent {
    
    protected String id;
    protected String location;
    protected boolean available;
    protected int batteryLevel;
    protected boolean functional;
    protected AgentRole role;

    public RoboticUnit(String id, String location,AgentRole role){
        this.id=id;
        this.location=location;
        this.batteryLevel=100;
        this.functional=true;
        this.available=true;
        this.role=role;
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
        return this.available&&this.functional&&batteryLevel>0;
    }
    @Override 
    public void setAvailable(boolean available){
        this.available=available;
    }

    public void repair(){
        this.functional=true;
    }
    public void recharge(){
        this.batteryLevel=100;
    }
    public void setFunctional(boolean functional){
        this.functional=functional;
    }
    public boolean isFunctional(){
        return this.functional;
    }
    public int getBatteryLevel(){
        return this.batteryLevel;
    }
    public void drainBattery(int amount){
        this.batteryLevel=Math.max(0,this.batteryLevel-amount);
    }


    
}
