import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class OuterEnemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OuterEnemy extends Actor
{
    private String direction;
    private int speed = 1;
    /**
     * Act - do whatever the OuterEnemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        changeDirection();
        move();
    }
    
    public void changeDirection()
    {
        int x = getX();
        int y = getY();
        
        if((x == 24) && (y == 24)){
            direction = "right";
        }
        else if((x == 576) && (y == 24)){
            direction = "down";
        }
        else if((x == 576) && (y == 576)){
            direction = "left";
        }
        else if((x == 24) && (y == 576)){
            direction = "up";
        }
    }
    
    public void move()
    {
        int x = getX();
        int y = getY();
        
        if (direction == "up"){
            setLocation(x, y-speed);
        }
       
        if (direction == "down"){
            setLocation(x, y+speed);
        }
       
        if (direction == "right"){
            move(speed);
        }
       
        if (direction == "left"){
            move(-speed);
        }
    }
}
