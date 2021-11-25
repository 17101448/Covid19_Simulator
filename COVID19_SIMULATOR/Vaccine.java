package COVID19_SIMULATOR;
import java.util.List;

import COVID19_SIMULATOR.Person.State;

public class Vaccine {

    static Person[][] people; 
    private int size;
    private int[] randOrder;

    private List<Integer[]> history;

    public Vaccine(int size){
        
        this.size = size; 
        System.out.println("size : "+size);
        this.people = new Person[size][size];

        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                this.people[i][j] = new Person();
            }   
        }
    }
    

    public Vaccine(int size, double vaccineRatio){
        this(size); 
        vaccinate(vaccineRatio); 
        initialInfection(); // 초기 감염자를 넣는다.  
    }

    private void initialInfection(){
        int centerX = this.size/2;
        int centerY = this.size/2; 
        this.people[centerX][centerY].setState(State.INFECTIOUS);

        for(int i= -1; i<=1;i++)
        {
         for(int j=-1; j<=1;j++)
         {
            this.people[centerX-i][centerY-j].setState(State.INFECTIOUS);
         }
        }
    }

    public void vaccinate(double vaccineRatio){
        double numberOfVaccinations = vaccineRatio*size*size;
        randPerm((int)numberOfVaccinations);
        

        for(int i=0; i<this.randOrder.length; i++)
        {
                this.people[this.randOrder[i]/size][this.randOrder[i]%size].setState(State.VACCINATED);
        }
    }

    int[] randPerm(int n){
        this.randOrder = new int[n];
        for(int i=0; i<n; i++)
        {
            this.randOrder[i] =(int) (Math.random()*size*size); 
        }
        return this.randOrder;
    }

    public void step(double infectionRate, double recoveryRate){}

  //  public Integer[] countStates(){}

    public State[][] getPeopleState(){
        State peopleState[][] = new State[size][size];
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                peopleState[i][j] = people[i][j].state; 
            }
        }

        return peopleState; 
    }

    //public List<Integer[]> getHistory(){  }

   /*public void printStep(int n){
       System.out.println("===== " + n + " ===== ("+randOrder.length/(size*size)+"/"+)
   }*/ 
  
    
}
