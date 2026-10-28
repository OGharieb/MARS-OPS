package agents;

import java.util.ArrayList;
import java.util.List;

public class  Scientist extends CrewMember{

    public Scientist(String id,String location, String name) {
        super(id,location,name);
        this.skills= new ArrayList<>( List.of("Research","Analysis"));
    }

    


    

    
}
