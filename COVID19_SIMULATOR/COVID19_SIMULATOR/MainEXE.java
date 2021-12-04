package COVID19_SIMULATOR;


public class MainEXE {

   
    public static void main(String[] args){
        int testX = 1; 
        int testY = 1;

        int size = 21; 
        double infectionRate = 0.25; 
        double recoveryRate = 0.50;
        double vaccineRatio = 0.10;

        Vaccine sim = new Vaccine(size, vaccineRatio); //
        System.out.println(); 
        System.out.println("메인문 "+ 0 +"번째 단계"); 
        sim.printPeople();
        sim.printStep(0);
        System.out.println("people["+testX+"]["+testY+"]의 이웃 / 메인에서 실행");    

        /*for(int i=0; i<8; i++)
        {
            
            System.out.println(i+" : "+sim.people[testX][testY].getNeighbors()[i]);
            System.out.println("getN()["+i+"]:"+sim.people[testX][testY].getNeighbors()[i].hashCode()+" ");
        
        }/*

        /*for(int i=0; i<25; i++)
        {
            for(int j=0; j<25; j++)
            {
                System.out.print(sim.getPeopleState()[i][j]);
            }
            System.out.println("");
        }*/
        System.out.println(); 
        for(int n=0; n< 5; n++)
        {
            System.out.println("메인문 "+ (n+1) +"번째 단계"); 
            sim.step(infectionRate, recoveryRate); // 이후에 감염된 사람이 없어지면 정상 
            sim.printStep(n+1);
            for(int i=0; i<8; i++)
            {
                System.out.println("people["+testX+"]["+testY+"]의 이웃 / step"+n+"이후 실행");    
                System.out.println(i+" : "+sim.people[testX][testY].getNeighbors()[i]);
            }
            System.out.println();
        }


    
        //다른 클래스에서 호출 시에도 정상적인 참조변수로 저장되어 있는지 
        /*State[][] state = sim.getPeopleState();
        for(int i=0; i<25; i++)
        {
            for(int j=0; j<25; j++)
            {
                System.out.println(state[i][j]);
            }
        }
        System.out.println();*/

    }
    
}
