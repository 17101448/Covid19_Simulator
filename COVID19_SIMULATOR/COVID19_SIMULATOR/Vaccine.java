package COVID19_SIMULATOR;
import java.util.List;

import COVID19_SIMULATOR.Person.State;

public class Vaccine {

    Person[][] people; 
    private int size;
    private int[] randOrder;

    private List<Integer[]> history;
    int k=0;
    public Vaccine(int size)
    {  
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
        System.out.println("백신 생성자에서 이웃 넣어줌");
        // 이웃 몇몇 부분에서는 *이 .으로 표시됨 
        // 조건 각각 할당해서 도넛 월드 구현해 줘야함
       for(int i=1; i<size-1; i++)
        {
            for(int j=1; j<size-1; j++)
            {
                Person[] naver = new Person[8];
                
                System.out.println("setNeighbors"+i+j);
                naver[0] = people[i-1][j-1];
                naver[1] = people[i-1][j];
                naver[2] = people[i-1][j+1];
                naver[3] = people[i][j-1];
                naver[4] = people[i][j+1];
                naver[5] = people[i+1][j-1];
                naver[6] = people[i+1][j];
                naver[7] = people[i+1][j+1];
                people[i][j].setNeighbors(naver);
                System.out.println("어디로 접근하는가?"+people[i][j]);
                for(int k=0; k<8; k++)
                {
                    System.out.print("naver"+k+":"+naver[k]  +" / ");
                    System.out.print("getN()["+k+"]:"+people[i][j].getNeighbors()[k]+" ");
                }
                System.out.println("setNeighbors 종료"+i+j);
            }   
            
        }
     

    }
//이웃설정 

    
        //initialInfection(); // 초기 감염자를 넣는다.  
 // 백신 생성자 
        

    

    public Vaccine(int size, double vaccineRatio){
        this(size); 
        vaccinate(vaccineRatio); 
        initialInfection(); // 초기 감염자를 넣는다. 
        /*
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                System.out.println(i+","+j+","+people[i][j]);
            }
        }\
        for(int i=0; i<8; i++)
        {
            System.out.println("1 1 : "+i+" : "+people[1][2].getNeighbors()[i]);
        }*/
       
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
       for(int i=1; i<size-1; i++)
        {
            for(int j=1; j<size-1; j++)
            {//peoplestate가 감염상태가 아니여도 들어가버림 
                if(people[i][j].state == State.INFECTIOUS);
                {   
                    System.out.println("감염이라 들어옴"); 
                    System.out.print(i+"i"+","+j+"j" +" : "+people[i][j]+" ");
                    people[i][j].infectNeighbors(infectionRate); 
                }
            }
        }
        
        printPeople(); 
    }

  //  public Integer[] countStates(){}

    public State[][] getPeopleState(){
        State peopleState[][] = new State[size][size];
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {   
                if(people[i][j].isSuseceptible())
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
        System.out.print(" "); 
        for(int i=0; i<people.length; i++)
        {
            System.out.print(i);
        }
        System.out.println(); 
        for(int i=0; i<people.length; i++)
        {   System.out.print(i);
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
