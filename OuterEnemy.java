import greenfoot.*;

/**
 * The outerEnemy class is the enemies that move around the edge of the world.
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
     * This is the act method which will run everytime the game acts, its main job is to call other methods.
     */
    public void act()
    {
        changeDirection();
        image();
        move();
    }
    
    /**
     * This is the changeDirection method which will check if the enemy has reached the end of its currect path, and needs to turn to the next.
     */
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
    
    /**
     * This is the move method which will check which way the enemy should be moving, and then move it in that direction by the speed from the world.
     */
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
    
    /**
     * This is the image method which will change the enemy image depending on which direction they are going, and if the player is powered up or not.
     */
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