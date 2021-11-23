package COVID19_SIMULATOR;
import java.util.List;

import COVID19_SIMULATOR.Person.State;

public class Vaccine {

    static Person[][] people; 
    private int size = 25;
    private int[] randOrder;

    private List<Integer[]> history;

    public Vaccine(int size){
        people = new Person[size][size];

        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                people[i][j] = new Person();
            }   
        }
        initialInfection(); 
    }
    

    public Vaccine(int size, double vaccineRatio){
        Vaccine vaccine = new Vaccine(size);

        

    }

    private void initialInfection(){
        int centerX = size/2+1;
        int centerY = size/2+1; 
        people[centerX][centerY].setState(State.INFECTIOUS);

        for(int i= -1; i<1;i++)
        {
         for(int j=-1; j<1;j++)
         {
            people[centerX-i][centerY-j].setState(State.INFECTIOUS);
         }
        }
    }

    public void vaccinate(double vaccineRatio){}

    private int[] randPerm(int n){}

    public void step(double infectionRate, double recoveryRate){}

    public Integer[] countStates(){}

    public State[][] getPeopleState(){}

    public List<Integer[]> getHistory(){

    public void printStep(int n){}
    }
    
}
