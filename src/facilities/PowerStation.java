package facilities;
import enums.FacilityType;
import resources.Resources;
import enums.ResourceType;

public class PowerStation extends Facility {
PowerStation(String facilityName){
    super(FacilityType.POWER_STATION, facilityName);
}

public void generatePower(int fuelAmount){

    if (this.resources.getAmount(ResourceType.FUEL)>= fuelAmount){
    this.resources.consumeAmount(ResourceType.FUEL, fuelAmount);
    System.out.println("Power generation has consumed " + fuelAmount + " units of fuel.");
}

    else {
        System.out.println("Not enough fuel to generate power.");
    }
}
}
