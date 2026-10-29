package colony;

import facilities.Facility;
import java.util.ArrayList;
import java.util.List;

public class MarsColony {
    List<Facility> facilities;
    private int currentDay;

    public MarsColony(){
        this.facilities= new ArrayList<>();
        this.currentDay=1;
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
            if(facility.isOperational())
                total+= facility.getResources().getOxygen();
        }
        return total;
    }

    public int getTotalWater(){
        int total=0;
        for(Facility facility:this.facilities){
            if(facility.isOperational())
                total+= facility.getResources().getWater();
        }
        return total;
    }

    public int getTotalFood(){
        int total=0;
        for(Facility facility:this.facilities){
            if(facility.isOperational())
                total+= facility.getResources().getFood();
        }
        return total;
    }

    public int getTotalFuel(){
        int total=0;
        for(Facility facility:this.facilities){
            if(facility.isOperational())
                total+= facility.getResources().getFuel();
        }
        return total;
    }

    public int getTotalBatteryPower(){
        int total=0;
        for(Facility facility:this.facilities){
            if(facility.isOperational())
                total+= facility.getResources().getBatteryPower();
        }
        return total;
    }

    public int getTotalMedicalSupplies(){
        int total=0;
        for(Facility facility:this.facilities){
            if(facility.isOperational())
                total+= facility.getResources().getMedicalSupplies();
        }
        return total;
    }
    public int getTotalSpareParts(){
        int total=0;
        for(Facility facility:this.facilities){
            if(facility.isOperational())
                total+= facility.getResources().getSpareParts();
        }
        return total;
    }

    public boolean  consumeOxygen(int amount){
        if(getTotalOxygen()<amount){
            return false;
        }
        int remaining = amount;
        for(Facility facility:facilities){
            if(facility.isOperational()&&remaining>0){
            int available=facility.getResources().getOxygen();
            if(available>=remaining){
               facility.getResources().consumeOxygen(remaining);
               remaining=0;
            }
            else if(available>0){
               facility.getResources().consumeOxygen(available);
               remaining-=available;

                
            }
          }
        }
        return true;

    }

    public boolean  consumeWater(int amount){
        if(getTotalWater()<amount){
            return false;
        }
        int remaining = amount;
        for(Facility facility:facilities){
            if(facility.isOperational()&&remaining>0){
            int available=facility.getResources().getWater();
            if(available>=remaining){
               facility.getResources().consumeWater(remaining);
               remaining=0;
            }
            else if(available>0){
               facility.getResources().consumeWater(available);
               remaining-=available;

                
            }
          }
        }
        return true;

    }

    public boolean  consumeFood(int amount){
        if(getTotalFood()<amount){
            return false;
        }
        int remaining = amount;
        for(Facility facility:facilities){
            if(facility.isOperational()&&remaining>0){
            int available=facility.getResources().getFood();
            if(available>=remaining){
               facility.getResources().consumeFood(remaining);
               remaining=0;
            }
            else if(available>0){
               facility.getResources().consumeFood(available);
               remaining-=available;

                
            }
          }
        }
        return true;

    }

    public boolean  consumeFuel(int amount){
        if(getTotalFuel()<amount){
            return false;
        }
        int remaining = amount;
        for(Facility facility:facilities){
            if(facility.isOperational()&&remaining>0){
            int available=facility.getResources().getFuel();
            if(available>=remaining){
               facility.getResources().consumeFuel(remaining);
               remaining=0;
            }
            else if(available>0){
               facility.getResources().consumeFuel(available);
               remaining-=available;

                
            }
          }
        }
        return true;

    }

    public boolean  consumeBatteryPower(int amount){
        if(getTotalBatteryPower()<amount){
            return false;
        }
        int remaining = amount;
        for(Facility facility:facilities){
            if(facility.isOperational()&&remaining>0){
            int available=facility.getResources().getBatteryPower();
            if(available>=remaining){
               facility.getResources().consumeBatteryPower(remaining);
               remaining=0;
            }
            else if(available>0){
               facility.getResources().consumeBatteryPower(available);
               remaining-=available;

                
            }
          }
        }
        return true;

    }
    
    public boolean  consumeMedicalSupplies(int amount){
        if(getTotalMedicalSupplies()<amount){
            return false;
        }
        int remaining = amount;
        for(Facility facility:facilities){
            if(facility.isOperational()&&remaining>0){
            int available=facility.getResources().getMedicalSupplies();
            if(available>=remaining){
               facility.getResources().consumeMedicalSupplies(remaining);
               remaining=0;
            }
            else if(available>0){
               facility.getResources().consumeMedicalSupplies(available);
               remaining-=available;

                
            }
          }
        }
        return true;

    }

    public boolean  consumeSpareParts(int amount){
        if(getTotalSpareParts()<amount){
            return false;
        }
        int remaining = amount;
        for(Facility facility:facilities){
            if(facility.isOperational()&&remaining>0){
            int available=facility.getResources().getSpareParts();
            if(available>=remaining){
               facility.getResources().consumeSpareParts(remaining);
               remaining=0;
            }
            else if(available>0){
               facility.getResources().consumeSpareParts(available);
               remaining-=available;

                
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

    
    

