package resources;

import enums.ResourceType;
import java.util.EnumMap;
import java.util.Map;

public class resources2{
    private Map<ResourceType,Integer> quantities;

    private resources2(){
        this.quantities= new EnumMap<>(ResourceType.class);
        for(ResourceType resource : ResourceType.values()){
            this.quantities.put(resource, 0);
        }
    }

    public int getAmount(ResourceType type){
        return this.quantities.get(type);
    }

    public void addAmount(ResourceType type, int amount){
        this.quantities.put(type, amount+this.quantities.get(type));

    }

    public boolean consumeAmount(ResourceType type,int amount){
        int current= this.quantities.get(type);
        if(current<amount){
            return false;
        }
        else{
            this.quantities.put(type, current-amount);
            return true;
        }

        
    }

}