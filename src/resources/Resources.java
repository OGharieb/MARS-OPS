package resources;

public class Resources {

    private int oxygen;
    private int water;
    private int food;
    private int fuel;
    private int batteryPower;
    private int medicalSupplies;
    private int spareParts;

    public Resources() {
        this.oxygen=0;
        this.water=0;
        this.food=0;
        this.fuel=0;
        this.batteryPower=0;
        this.medicalSupplies=0;
        this.spareParts=0;
    }
    
    


    public int getOxygen(){
        return this.oxygen;
    }
    public void addOxygen(int value){
        if(value>=0)
            this.oxygen+=value;
    }
    public boolean consumeOxygen(int value){
        if(this.oxygen>=value){
            this.oxygen-=value;
            return true;
        }
        else
            return false;
    }

    public int getWater(){
        return this.water;
    }
    public void addWater(int value){
        if (value>=0)
            this.water+=value;
    }
    public boolean consumeWater(int value){
        if(this.water>=value){
            this.water-=value;
            return true;
        }
        else
            return false;
    }

    public int getFood(){
        return this.food;
    }
    public void addFood(int value){
        if(value>=0)
            this.food+=value;
    }
    public boolean consumeFood(int value){
        if(this.food>=value){
            this.food-=value;
            return true;
        }
        else
            return false;
    }

    public int getFuel(){
        return this.fuel;
    }
    public void addFuel(int value){
        if(value>=0)
            this.fuel+=value;
    }
    public boolean consumeFuel(int value){
        if(this.fuel>=value){
            this.fuel-=value;
            return true;
        }
        else
            return false;
    }

    public int getBatteryPower(){
        return this.batteryPower;
    }
    public void addBatteryPower(int value){
        if(value>=0)
            this.batteryPower+=value;
    }
    public boolean consumeBatteryPower(int value){
        if(this.batteryPower>=value){
            this.batteryPower-=value;
            return true;
        }
        else
            return false;
    }

    public int getMedicalSupplies(){
        return this.medicalSupplies;
    }
    public void addMedicalSupplies(int value){
        if(value>=0)
            this.medicalSupplies+=value;
    }
    public boolean consumeMedicalSupplies(int value){
        if(this.medicalSupplies>=value){
            this.medicalSupplies-=value;
            return true;
        }
        else
            return false;
    }

    public int getSpareParts(){
        return this.spareParts;
    }
    public void addSpareParts(int value){
        if(value>=0)
            this.spareParts+=value;
    }
    public boolean consumeSpareParts(int value){
        if(this.spareParts>=value){
            this.spareParts-=value;
            return true;
        }
        else
            return false;
    }
}
