import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InnerEnemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InnerEnemy extends Actor
{
    private int direction = 0;
    private int time = 0;
    /**
     * Act - do whatever the InnerEnemy wants to do. This method is called whenever
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
        
        if((x == 300) && (y == 300)){
            direction++;
            time = 0;
        }
        
        if(direction == 5){
            direction = 1;
        }
    }
    
    public void move()
    {
        int x = getX();
        int y = getY();
        
        if (direction == 1){
            if (time < (240 / ((MyWorld) getWorld()).innerEnemySpeed)){
                setLocation(x, y-((MyWorld) getWorld()).innerEnemySpeed);
            }
            else{
                setLocation(x, y+((MyWorld) getWorld()).innerEnemySpeed);
            }
        }
        if (direction == 3){
            if (time < (240 / ((MyWorld) getWorld()).innerEnemySpeed)){
                setLocation(x, y+((MyWorld) getWorld()).innerEnemySpeed);
            }
            else{
                setLocation(x, y-((MyWorld) getWorld()).innerEnemySpeed);
            }
        }
       
        if (direction == 4){
            if (time < (240 / ((MyWorld) getWorld()).innerEnemySpeed)){
                move(((MyWorld) getWorld()).innerEnemySpeed);
            }
            else{
                move(-((MyWorld) getWorld()).innerEnemySpeed);
            }
        }
       
        if (direction == 2){
            if (time < (240 / ((MyWorld) getWorld()).innerEnemySpeed)){
                move(-((MyWorld) getWorld()).innerEnemySpeed);
            }
            else{
                move(((MyWorld) getWorld()).innerEnemySpeed);
            }
        }
        
        time++;
    }
}
