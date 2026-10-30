package agents;

import enums.AgentRole;
import java.util.ArrayList;
import java.util.List;

public class  Scientist extends CrewMember{

    public Scientist(String id,String location, String name) {
        super(id,location,name,AgentRole.SCIENTIST);
        this.skills= new ArrayList<>( List.of("Research","Analysis"));
    }



    


    

    
}
