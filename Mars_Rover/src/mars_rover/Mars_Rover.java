/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mars_rover;
import java.util.*;
/**
 *
 * @author user
 */
public class Mars_Rover {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
     Mars p = new Mars(1,2,"NORTH");
     
    // PART I
     System.out.println("Part1  ");
     p.direction("FRFFFLFBBLFF");
     
     
     
//      PART II
     System.out.println("Part2  ");
     int [][]obstacle = {{1,4},{-1,0},{0,-1},{4,3},{7,4},{0,-3}};
    p.direction("FRFFFLFBBLFF" , obstacle);
    
    

//     part III
       System.out.println("Part3  ");
       int [][]obstacle1 = {{1,4},{-1,0},{0,-1},{2,3},{7,4},{0,-3}}; 
//     arguments is (heading x, heading y, obstacles)  
       String commands =  p.generate_command(5, 3 , obstacle1);
      System.out.println(commands);
       
        
        
   
    }
    
}
class Mars {
    int x ;
    int y;
    String direction;
    public Mars(int x , int y , String direction) {
        
      this.x = x;
      this.y = y;
      this.direction = direction;
              
    }
    // part I
    public void direction(String commands){
        for (int i = 0 ; i < commands.length(); i++){
            
            if(commands.charAt(i) == 'F'){
               position_forward();
              }
            else if(commands.charAt(i) == 'B'){
               position_backword();
            }
            else if(commands.charAt(i) == 'L'){
               rotate_left();
            }
             else if(commands.charAt(i) == 'R'){
                
                rotate_right();
            }
             else{
                 System.out.println("Not Found");
             }
            
        
    }
        print();
        
    }
    
    
    
    //part II
    public void direction(String commands,int [][]obs){
        int check = 0;
        for (int i = 0 ; i < commands.length(); i++){
            
            if(commands.charAt(i) == 'F'){
                check = check_obstacle(obs,'F');
               
               if(check == -1){
                   print_stop();
                   break;
               }
               else{
                   position_forward();
               }
                   
             
            }
            else if(commands.charAt(i) == 'B'){
                check = check_obstacle(obs,'B');
                if(check == -1){
                   print_stop();
                   break;
               }
                else{
                    position_backword();
                }
                
            }
            else if(commands.charAt(i) == 'L'){
                rotate_left();
            }
             else if(commands.charAt(i) == 'R'){
                rotate_right();
            }
             else{
                 System.out.println("Not Found");
             }
            
            
            
        }
        if(check != -1)
             print();
        
    }
    private void position_forward(){
        
              if (direction == "NORTH"){
                   y++;
               }
              else if (direction == "SOUTH"){
                   y--;
               }
              else if(direction == "EAST"){
                  x++;
              }
              else if(direction == "WEST"){
                  x--;
              }
              else{
                   System.out.println("Invalid direction");
              }
        
    }
    
    private void position_backword(){
        
        if (direction == "NORTH"){
                   y--;
               }
              else if (direction == "SOUTH"){
                   y++;
               }
              else if(direction == "EAST"){
                  x--;
              }
              else if(direction == "WEST"){
                  x++;
              }
              else{
                   System.out.println("Invalid direction");
              }
        
    }
    
    private int check_obstacle(int[][]obs, char dir){
       for(int i = 0 ; i < obs.length;i++){
          
              if(dir == 'F' ){
                 if(direction == "NORTH" && x == obs[i][0] && y+1 == obs[i][1]){
                     return -1;
                 }
                 else if(direction == "SOUTH" && x == obs[i][0] && y-1 == obs[i][1]){
                     return -1;
                 }
                 else if(direction == "EAST" && x+1 == obs[i][0] && y == obs[i][1]){
                     return -1;
                 }
                 else if(direction == "WEST" && x-1 == obs[i][0] && y == obs[i][1]){
                     return -1;
                 }
                    
                  
              }
              else if(dir == 'B'){
                   if(direction == "NORTH" && x == obs[i][0] && y-1 == obs[i][1]){
                     return -1;
                 }
                 else if(direction == "SOUTH" && x == obs[i][0] && y+1 == obs[i][1]){
                     return -1;
                 }
                 else if(direction == "EAST" && x-1 == obs[i][0] && y == obs[i][1]){
                     return -1;
                 }
                 else if(direction == "WEST" && x+1 == obs[i][0] && y == obs[i][1]){
                     return -1;
                 }
              }
              else{
                  System.out.println("please enter correct direction");
              }
     }  
        return 1;
        
    }
    
     private void rotate_left(){
       if(direction == "NORTH"){
           direction = "WEST";
       }
       else if(direction == "WEST"){
            direction = "SOUTH";
       }
        else if(direction == "SOUTH"){
            direction = "EAST";
       }
        else if(direction == "EAST"){
            direction = "NORTH";
       }
        else{
            System.out.println("Not Found");
        }
       
    }
   
    private void rotate_right(){
        if(direction == "NORTH"){
           direction = "EAST";
       }
       else if(direction == "EAST"){
            direction = "SOUTH";
       }
        else if(direction == "SOUTH"){
            direction = "WEST";
       }
        else if(direction == "WEST"){
            direction = "NORTH";
       }
        else{
            System.out.println("Not Foumd");
        }
    }
    // part III
    public String generate_command(int heading_x, int heading_y , int [][]obs){
            String commands= "";
            int ch = 0;
            while(true){
                
                if(x == heading_x && y == heading_y){
                    return commands;
                }
                
                else{
                    if(direction == "NORTH"){
                        if (ch== -1){
                            ch = check_obstacle(obs, 'F');
                           if(ch!= -1){
                               commands+='F';
                               position_forward();
                               commands += 'R';
                               rotate_right();
                           }
                           else{
                               commands+='B';
                                position_backword();
                                commands += 'R';
                                rotate_right();
                           }
                        }
                        if(heading_y > y){
                           ch= check_obstacle(obs, 'F');
                           if(ch!= -1){
                               commands+='F';
                              position_forward();
                           }
                           else{
                               if(heading_x >= x){
                                   commands+='R';
                                   rotate_right();
                               }
                               else if(x > heading_x){
                                   commands+='L';
                                   rotate_left();
                               }
                              
                           }
                        }
                        else if(y > heading_y) {
                            ch= check_obstacle(obs, 'B');
                             if(ch!= -1){
                               commands+='B';
                               position_backword();
                           }
                           else{
                               if(heading_x >= x){
                                   commands +='R';
                                   rotate_right();
                               }
                               else if(x > heading_x){
                                   commands+='L';
                                   rotate_left();
                               }
                              
                           }
                        }
                        else if(heading_x > x){
                            commands+='R';
                            rotate_right();
                        }
                        else{
                            commands+='L';
                            rotate_left();
                        }
                   
                }
                   
                    else if(direction == "SOUTH"){
                        if (ch== -1){
                            ch = check_obstacle(obs, 'F');
                           if(ch!= -1){
                               commands+='F';
                               position_forward();
                               commands += 'R';
                               rotate_right();
                           }
                           else{
                               commands+='B';
                                position_backword();
                                commands += 'R';
                                rotate_right();
                           }
                        }
                         if(y > heading_y){
                           ch= check_obstacle(obs, 'F');
                           if(ch!= -1){
                               commands+='F';
                               position_forward();
                           }
                           else{
                               if(heading_x >= x){
                                   commands+='R';
                                   rotate_right();
                               }
                               else if(x > heading_x){
                                   commands+='L';
                                   rotate_left();
                               }
                              
                           }
                        }
                        else if(heading_y > y) {
                            ch= check_obstacle(obs, 'B');
                             if(ch!= -1){
                               commands+='B';
                               position_backword();
                           }
                           else{
                               if(heading_x >= x){
                                   commands +='R';
                                   rotate_right();
                               }
                               else if(x > heading_x){
                                   commands+='L';
                                   rotate_left();
                               }
                              
                           }
                        }
                        else if(heading_x > x){
                            commands+='L';
                            rotate_left();
                        }
                        else{
                            commands+='R';
                            rotate_right();
                        }
                    }   
                    
                    else if(direction == "EAST"){
                        if (ch== -1){
                            ch = check_obstacle(obs, 'F');
                           if(ch!= -1){
                               commands+='F';
                               position_forward();
                               commands += 'R';
                               rotate_right();
                           }
                           else{
                               commands+='B';
                                position_backword();
                                commands += 'R';
                                rotate_right();
                           }
                        }
                         if(heading_x > x){
                           ch= check_obstacle(obs, 'F');
                           if(ch!= -1){
                               commands+='F';
                               position_forward();
                           }
                           else{
                               if(heading_y < y){
                                   commands+='R';
                                   rotate_right();
                               }
                               else if(heading_y >= y){
                                   commands+='L';
                                   rotate_left();
                               }
                              
                           }
                        }
                        else if(x > heading_x) {
                            ch= check_obstacle(obs, 'B');
                             if(ch!= -1){
                               commands+='B';
                               position_backword();
                           }
                           else{
                               if(heading_y < y){
                                   commands +='R';
                                   rotate_right();
                               }
                               else if(heading_y >= y){
                                   commands+='L';
                                   rotate_left();
                               }
                              
                           }
                        }
                        else if(heading_y > y){
                            commands+='L';
                            rotate_left();
                        }
                        else {
                            commands+='R';
                            rotate_right();
                        }
                         
                    }  
                    else if(direction == "WEST"){
                        if (ch== -1){
                            ch = check_obstacle(obs, 'F');
                           if(ch!= -1){
                               commands+='F';
                               position_forward();
                               commands += 'R';
                               rotate_right();
                           }
                           else{
                               commands+='B';
                                position_backword();
                                commands += 'R';
                                rotate_right();
                           }
                        }
                        else if(heading_x < x ){
                           ch = check_obstacle(obs, 'F');
                           if(ch!= -1){
                               commands+='F';
                               position_forward();
                           }
                           else{
                               if(heading_y >= y ){
                                   commands+='R';
                                   rotate_right();
                               }
                               else if(heading_y < y){
                                   commands+='L';
                                   rotate_left();
                               }
                              
                           }
                        }
                        else if(heading_x > x) {
                            ch= check_obstacle(obs, 'B');
                             if(ch!= -1){
                               commands+='B';
                               position_backword();
                           }
                           else{
                               if(heading_y >= y ){
                                   commands +='R';
                                   rotate_right();
                               }
                               else if(heading_y < y){
                                   commands+='L';
                                   rotate_left();
                               }
                              
                           }
                        }
                         else if(heading_y > y){
                            commands+='R';
                            rotate_right();
                        }
                        else {
                             commands+='L';
                            rotate_left();
                        }
                        
                         
                        
                        
                    }
                    else{
                        System.out.println("invalid direction");
                    }
                    
                    
                    
                    
                    
                }
            }
    
    
    
    
     
    
}
    
    
    private void print(){
        System.out.printf("(%d,%d) %s %n", x,y,direction);
        
    }
    private void print_stop(){
        System.out.printf("(%d,%d) %s stopped %n", x,y,direction);
        
    }
    
    
    
    
}
