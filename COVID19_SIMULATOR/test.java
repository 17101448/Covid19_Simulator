int k=0; 


for(int x=0; x<size; x++)
    {
      for(int y=0; y<size; y++)
      {
          for(int i=-1; i<=1; i++)
          {
            for(int j=-1; j<=1; j++)
            {

                if(i==0 && j==0){k+=0;}
                else{int g = i+x;
                    if(i+x<0){g = size-1;}

                    if(g>size-1){g=0;}

                    int m = j+y;

                    if(m<0){
                        m = size-1;
                    }

                    if(m>size-1){
                        m = 0;
                    }
                    people[x][y].getNeighbors()[k] = this.people[g][m]; 
                    
                    k++;
                    if(k==8){
                        k=0;
                        break;
                    }
            } 
        }
    }
}

    for(int x=0; x<size; x++)
    {
      for(int y=0; y<size; y++)
      {
          for(int i=-1; i<=1; i++)
          {
            for(int j=-1; j<=1; j++)
            {

                if(i==0 && j==0){k+=0;}
                else{int g = i+x;
                    if(i+x<0){g = size-1;}

                    if(g>size-1){g=0;}

                    int m = j+y;

                    if(m<0){
                        m = size-1;
                    }

                    if(m>size-1){
                        m = 0;
                    }
                    getNeighbors()[k] = people[x][y].people[g][m]; 
                    
                    k++;
                    if(k==8){
                        k=0;
                        break;
                    }
            } 
        }
    }
