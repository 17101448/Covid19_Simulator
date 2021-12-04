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

if(i==0 && j==0){
    if(index ==0)
    {
        naver[index] = people[size+y][size-1];    
    }
    else if(index==2 || index==3)
    {
        naver[index] = people[size+y][j+x];
    }
    else if(index==4){
        x=0; // index4가 되었을 때 +0 상대위치를 강제로 뛰어넘음
    }
    else if(index ==8)
    {
            index =0;
            break; 
    }
    else{
        naver[index] = people[i+y][j+x];
    }
    index++;
}
else if(i==0 && j==size-1)
{
    if(index ==2 || index ==4 || index==7)
    {
        naver[index] = people[size+y][0];    
    }
    else if(index==2 && index==3)
    {
        naver[index] = people[size+y][j+x];
    }
    else if(index==4){
        x=0; // index4가 되었을 때 +0 상대위치를 강제로 뛰어넘음
    }
    else if(index ==8)
    {
            index =0;
            break; 
    }
    else{
        naver[index] = people[i+y][j+x];
    }
    index++;
}
else{ // 일반적인 경우의 이웃 
    naver[index] = people[i+y][j+x];
    index++;
    if(index==4) 
    {
        x=0; // index4가 되었을 때 +0 상대위치를 강제로 뛰어넘음
    }
    else if(index ==8)
    {
            index =0;
            break; 
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
