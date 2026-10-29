package colony;

import enums.ResourceType;
import facilities.Facility;
import java.util.ArrayList;
import java.util.List;

public class MarsColony2 {
    List<Facility> facilities;
    private int currentDay;

    public MarsColony2(){
        this.facilities= new ArrayList<>();
        this.currentDay=1;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }
    public void addFacility(Facility facility){
        facilities.add(facility);
    }

    public int getTotal(ResourceType type) {
        int total = 0;
        for (Facility facility : this.facilities) {
            if (facility.isOperational()) {
                total += facility.getResources2().getAmount(type);
            }
        }
        return total;
    }
    
    public boolean consume(ResourceType type, int amount) {
        if (getTotal(type) < amount) {
            return false;
        }
        
        int remaining = amount;
        for (Facility facility : facilities) {
            if (facility.isOperational() && remaining > 0) { 
                int available = facility.getResources2().getAmount(type);
                
                if (available >= remaining) {
                    facility.getResources2().consumeAmount(type, remaining);
                    remaining = 0;
                } else if (available > 0) {
                    facility.getResources2().consumeAmount(type, available);
                    remaining -= available;
                }
            }
        }
        return true;
    }

    
    //end of resources functionssss

    public void advanceDay(){
        this.currentDay++;
        //add mission advance code here l8r

    }



    }

    
    

