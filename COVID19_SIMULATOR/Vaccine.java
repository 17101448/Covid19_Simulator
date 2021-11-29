package COVID19_SIMULATOR;
import java.util.List;

import COVID19_SIMULATOR.Person.State;

public class Vaccine {

    private Person[][] people; 
    private int size;
    private int[] randOrder;

    private List<Integer[]> history;
   
    public Vaccine(int size)
    {  
        this.size = size; 
        System.out.println("size : "+size);
        this.people = new Person[size][size];
        int k=0; 
         Person[] neighbors = new Person[8];
    
         for(int i = 0; i < neighbors.length; i++){
            neighbors[i] = new Person();
        }
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                this.people[i][j] = new Person();
            }   
        }
        /*시작점 문제, 도넛문제, 자기자신을 넣어버리는 문제 고쳐야함*/ 
        for(int x=1; x<5; x++)
        {
            for(int y=1; y<5; y++)
            {   
                System.out.println("객체를 생성");
               
                for(int i = -1; i <=1; i++){
                    
                    for(int j = -1; j<=1; j++)
                    {
                        System.out.println("k : "+ k+", x : "+x+" , i : "+i +", y : "+y+", j: "+j);
                        k++;
                        System.out.println("배열에 넣어줌");
                        neighbors[k] = this.people[i+x][j+y]; 
                                                              //이웃을  neighbor 배열에 넣어준다. 
                    } 
                }
                System.out.println("완성된 배열을 setNeigbors를 이용하여 클래스 변수에 넣어줌");
                people[x][y].setNeighbors(neighbors);
                k=0;
                
                for(int i=0; i<8; i++)
                {
                    System.out.println(people[1][1].getNeighbors()[i]);
                }
                
                
            }   
        }// 이 과정을 모든 person 객체에 대해 실행한다.

        initialInfection(); // 초기 감염자를 넣는다.  
} // 백신 생성자 
        

    

    public Vaccine(int size, double vaccineRatio){
        this(size); 
        vaccinate(vaccineRatio); 
        initialInfection(); // 초기 감염자를 넣는다.  
        people[1][1].getNeighbors(); 
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

    public void step(double infectionRate, double recoveryRate){
        //감염이 먼저 와야하는가 회복이 먼저와야하는가 
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
               people[i][j].update(recoveryRate);
            }
        }
       for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
               people[i][j].infectNeighbors(infectionRate); 
            }
        }
        
        printPeople(); 

        //TEST!!! 
       // people[1][1].test();
    }

  //  public Integer[] countStates(){}

    public State[][] getPeopleState(){
        State peopleState[][] = new State[size][size];
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {   
                if(people[i][j].isSusceptible())
                {
                    peopleState[i][j] = State.SUSCEPTIBLE;
                }
                else if(people[i][j].isInfectious())
                {
                    peopleState[i][j] = State.INFECTIOUS;
                }
                else if(people[i][j].isVaccinated())
                {
                    peopleState[i][j] = State.VACCINATED;
                }
                
            }
        }

        return peopleState; 
    }

    //public List<Integer[]> getHistory(){  }

    public void printPeople(){
        for(int i=0; i<people.length; i++)
        {
            for(int j=0; j<people[0].length; j++)
            {
                System.out.print(people[i][j]);
            }
            System.out.println(); 
        }
    }

   public void printStep(int n){
       int numberOfVaccine =0;
       int numberOfInfector =0; 
       
        State[][] state = getPeopleState();

       for(int i=0; i<size; i++)
       {
           for(int j=0; j<size; j++)
           {
               if(state[i][j]==State.VACCINATED)
               {
                   numberOfVaccine ++; 
               }
           }
       }

       for(int i=0; i<size; i++)
       {
           for(int j=0; j<size; j++)
           {
               if(state[i][j]==State.INFECTIOUS)
               {
                   numberOfInfector ++; 
               }
           }
       }
       double ratioOfVaccine = (double)numberOfVaccine/(size*size)*100f; 
       double ratioOfInfector =(double)numberOfInfector/(size*size)*100f; 
       System.out.println("===== " + n + " ===== ("+ ratioOfVaccine+ "/" +ratioOfInfector+")");
   }
  
    
}
