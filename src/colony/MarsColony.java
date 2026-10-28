package colony;

import facilities.Facility;
import java.util.ArrayList;
import java.util.List;

public class MarsColony {
    List<Facility> facilities;

    public MarsColony(){
        this.facilities= new ArrayList<>();
    }

    public List<Facility> getFacilities() {
        return facilities;
    }
    public void addFacility(Facility facility){
        facilities.add(facility);
    }

    public int getTotalOxygen(){
        int total=0;
        for(Facility facility:this.facilities){
            if(facility.isIsOperational())
                total+= facility.getResources().getOxygen();
        }
        return total;
    }

    public int getTotalWater(){
        int total=0;
        for(Facility facility:this.facilities){
            if(facility.isIsOperational())
                total+= facility.getResources().getWater();
        }
        return total;
    }

    public int getTotalFood(){
        int total=0;
        for(Facility facility:this.facilities){
            if(facility.isIsOperational())
                total+= facility.getResources().getFood();
        }
        return total;
    }

    public int getTotalFuel(){
        int total=0;
        for(Facility facility:this.facilities){
            if(facility.isIsOperational())
                total+= facility.getResources().getFuel();
        }
        return total;
    }

    public int getTotalBatteryPower(){
        int total=0;
        for(Facility facility:this.facilities){
            if(facility.isIsOperational())
                total+= facility.getResources().getBatteryPower();
        }
        return total;
    }

    public int getTotalMedicalSupplies(){
        int total=0;
        for(Facility facility:this.facilities){
            if(facility.isIsOperational())
                total+= facility.getResources().getMedicalSupplies();
        }
        return total;
    }
    public int getTotalSpareParts(){
        int total=0;
        for(Facility facility:this.facilities){
            if(facility.isIsOperational())
                total+= facility.getResources().getSpareParts();
        }
        return total;
    }
    

    }

    
    

