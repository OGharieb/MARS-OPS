package facilities;

import agents.Agent;
import enums.FacilityType;
import equipments.Equipment;
import java.util.ArrayList;
import java.util.List;
import resources.Resources;

public abstract  class Facility {
    protected boolean isOperational;
    protected FacilityType type;
    protected Resources resources;
    protected List<Equipment> equipments;
    protected List<Agent> agents ;
    public String facilityName;

    public Facility(FacilityType type, String facilityName) {
        this.isOperational = true;
        this.type = type;
        this.facilityName = facilityName;
        this.resources = new Resources();
        this.equipments = new ArrayList<>();
        this.agents=new ArrayList<>();


    }
    
    
    public void addAgent(Agent agent){
        agent.setLocation(this.facilityName);
        this.agents.add(agent);
    }

    public void addEquipment(Equipment equipment){
        this.equipments.add(equipment);
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setOperational(boolean isOperational) {
        this.isOperational = isOperational;
    }

    public boolean isOperational() {
        return isOperational;
    }

    public FacilityType getType() {
        return type;
    }



    public List<Equipment> getEquipment() {
        return equipments;
    }

    

    public Resources getResources() {
        return resources;
    }

     public void medicalEmergencyOccurs(){
        this.agents.get(0).setAvailable(false);
     }

    
    

    
    
}
