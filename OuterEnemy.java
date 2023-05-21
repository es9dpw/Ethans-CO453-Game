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
    GreenfootImage unPoweredUp = new GreenfootImage("EnemyUp.png");
    GreenfootImage unPoweredDown = new GreenfootImage("EnemyDown.png");
    GreenfootImage unPoweredLeft = new GreenfootImage("EnemyLeft.png");
    GreenfootImage unPoweredRight = new GreenfootImage("EnemyRight.png");
    GreenfootImage poweredUp = new GreenfootImage("ScaredEnemyUp.png");
    GreenfootImage poweredDown = new GreenfootImage("ScaredEnemyDown.png");
    GreenfootImage poweredLeft = new GreenfootImage("ScaredEnemyLeft.png");
    GreenfootImage poweredRight = new GreenfootImage("ScaredEnemyRight.png");
    /**
     * Act - do whatever the OuterEnemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        changeDirection();
        image();
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
            setLocation(x, y-((MyWorld) getWorld()).outerEnemySpeed);
        }
       
        if (direction == "down"){
            setLocation(x, y+((MyWorld) getWorld()).outerEnemySpeed);
        }
       
        if (direction == "right"){
            move(((MyWorld) getWorld()).outerEnemySpeed);
        }
       
        if (direction == "left"){
            move(-((MyWorld) getWorld()).outerEnemySpeed);
        }
    }
    
    public void image(){
        if (((MyWorld) getWorld()).powerups <= 2){
            if (direction == "up"){
                setImage(unPoweredUp);
            }
            else if (direction == "down"){
                setImage(unPoweredDown);
            }
            else if (direction == "left"){
                setImage(unPoweredLeft);
            }
            else if (direction == "right"){
                setImage(unPoweredRight);
            }
        }
        else if (((MyWorld) getWorld()).powerups >= 3){
            if (direction == "up"){
                setImage(poweredUp);
            }
            else if (direction == "down"){
                setImage(poweredDown);
            }
            else if (direction == "left"){
                setImage(poweredLeft);
            }
            else if (direction == "right"){
                setImage(poweredRight);
            }
        }
    }
}
