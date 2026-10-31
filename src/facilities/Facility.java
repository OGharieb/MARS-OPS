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

     public void medicalEmergencyOccurs(int agentNumber){
        if (agentNumber < 0 || agentNumber >= this.agents.size()) {
            System.out.println("Invalid agent number.");
            return;
        }
        this.agents.get(agentNumber).setAvailable(false);
     }
    public void looseOxygen(int amount){
        boolean success = this.resources.consumeAmount(enums.ResourceType.OXYGEN, amount);
        if (!success){
            this.resources.consumeAll(enums.ResourceType.OXYGEN);
        }
        if (this.resources.isEmpty(enums.ResourceType.OXYGEN)){
        System.out.println("Warning: " + this.facilityName + " has no oxygen left!!!!!!");
        }
    }

    
    

    
    
}
