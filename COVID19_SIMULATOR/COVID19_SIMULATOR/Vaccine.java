package COVID19_SIMULATOR;
import java.util.ArrayList;
import java.util.List;

import COVID19_SIMULATOR.Person.State;

public class Vaccine {

    Person[][] people; 
    private int size;
    private int[] randOrder;

    private List<Integer[]> history;
    private int index=0;
    
    public Vaccine(int size)
    {  
        this.size = size; 
        //System.out.println("size : "+size);
        this.people = new Person[size][size];

        
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                this.people[i][j] = new Person();
            }   
        }
        //System.out.println("백신 생성자에서 이웃 넣어줌");
        // 이웃 몇몇 부분에서는 *이 .으로 표시됨 
        // 조건 각각 할당해서 도넛 월드 구현해 줘야함
        

        /*for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                System.out.print(people[i][j].hashCode()+" / ");
            }   
            System.out.println("");
        }*/
        //도넛 월드 구현 / 각 객체에 대해 각각의 이웃을 가져온다. 
       for(int y=0; y<size; y++)
        {
            for(int x=0; x<size; x++)
            {
                
                //System.out.println("setNeighbors"+y+x);
                while(index!=8)
                {Person[] naver = new Person[8];
                    //delY, delX는 기준위치 [Y][X]부터의 변화량을 의미한다. 
                    for(int delY=-1; delY<=1; delY++)
                    {
                        for(int delX=-1; delX<=1; delX++)
                        {
                            if(index ==4){delX=1;}
                            int realX = delX+x;
                            int realY = delY+y;

                            if(realY<0){realY=size-1; }
                            if(realX<0){realX=size-1;}
                            if(realX>size-1){realX=0;}
                            if(realY>size-1){realY=0;}

                            //System.out.println("realY : "+realY+" realX : "+realX);
                            naver[index] = people[realY][realX];
                            index++; 
                        } // end x증분 
                    } //end y증분 
                    people[y][x].setNeighbors(naver);
                } //end 인덱스 반복 
            
                index=0;
                //System.out.println("["+y+"]["+x+"]");
                
                /*이웃의 해시코드 제대로 저장되었는지 확인 
                for(int m=0; m<8; m++)
                {
                    System.out.println("getN()["+m+"]:"+people[y][x].getNeighbors()[m].hashCode()+" ");
                }*/
                
            }//end x좌표 
        }//end y좌표 
        initialInfection(); // 초기 감염자를 넣는다. 
        /*for(int i=0; i<size; i++)
        {System.out.println("생성자 마지막에서 해시코드 확인");
            for(int j=0; j<size; j++){
                System.out.println("["+i+"]["+j+"]");
                for(int m=0; m<8; m++)
                {
                    System.out.println("getN()["+m+"]:"+people[i][j].getNeighbors()[m].hashCode()+" ");
                }
            }
        }*/
      }//백신 생성자 
//이웃설정 

    
        //initialInfection(); // 초기 감염자를 넣는다.  
 // 백신 생성자 
        

    

    public Vaccine(int size, double vaccineRatio){
        this(size); 
        vaccinate(vaccineRatio); 
        initialInfection();
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
    //1. 각각의 감염된 사람들은 이웃을 감염시킬 수 있다. 
    //2. 사람들의 각각의 상태를 업데이트 시킨다. 
    public void step(double infectionRate, double recoveryRate){

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
                if(people[i][j].isInfectious());
                {   
                    //System.out.println("감염이라 들어옴"); 
                   // System.out.print(i+"i"+","+j+"j" +" : "+people[i][j]+" ");
                    people[i][j].infectNeighbors(infectionRate); 
                }
            }
        }

        //System.out.println("step"); 
        //감염이 먼저 와야하는가 회복이 먼저와야하는가 

      
        
        /*for(int i=0; i<size; i++)
        {System.out.println("step이후 해시코드 확인");
            for(int j=0; j<size; j++){
                for(int m=0; m<8; m++)
                {
                    System.out.println("getN()["+m+"]:"+people[i][j].getNeighbors()[m].hashCode()+" ");
                }
            }
        }*/
        
        
        printPeople(); 
    }

  

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

    public Integer[] countStates(){
        Integer[] countStates = new Integer[3];

        int numberOfVaccination =0;
       int numberOfInfector =0; 
       int numberOfSuception =0; 

        State[][] state = getPeopleState();

       for(int i=0; i<size; i++)
       {
           for(int j=0; j<size; j++)
           {
               if(state[i][j]==State.VACCINATED)
               {
                   numberOfVaccination ++; 
               }else if(state[i][j]==State.INFECTIOUS)
               {
                   numberOfInfector ++; 
               }
               else{
                   numberOfSuception ++;}
           }
       }

       countStates[0] = numberOfSuception;
       countStates[1] = numberOfInfector;
       countStates[2] = numberOfVaccination;

       return countStates; 
    }

    public List<Integer[]> getHistory(){  
        ArrayList<Integer[]> history = new ArrayList<>();
        history.add(countStates());
        return history; 
    }

    public void printPeople(){
        //System.out.print(" "); 
        /*for(int i=0; i<people.length; i++)
        {
            System.out.print(i);
        }
        System.out.println(); */
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
       
    int numberOfSuception = countStates()[0];
    int numberOfInfector = countStates()[1];
    int numberOfVaccination = countStates()[2];

       
       double ratioOfVaccine = (double)numberOfVaccination/(size*size)*100f; 
       double ratioOfInfector =(double)numberOfInfector/(size*size)*100f; 
       System.out.println("===== " + n + " ===== ("+ ratioOfVaccine+ "/" +ratioOfInfector+")");
   }
  
    
}
