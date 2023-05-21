import greenfoot.*;

/**
 * The Player class is the class that the player will control.
 */
public class Player extends Actor
{
    private boolean start = true;
    GreenfootImage unPowered = new GreenfootImage("Player.png");
    GreenfootImage powered = new GreenfootImage("PoweredPlayer.png");
    private boolean isPowered = false;
    public int timer;
    /**
     * This is the act method which will run everytime the game acts, its main job is to call other methods.
     */
    public void act()
    {
        move();
        collision();
        start();
        
        if (isPowered == true){
            Countdown();
        }
    }
    
    /**
     * This is the move method which will allow the player to move ip, down, left and right.
     */
    public void move(){
        int x = getX();
        int y = getY();
        
        if (Greenfoot.isKeyDown("up")){
            setLocation(x, y-2);
        }
       
        if (Greenfoot.isKeyDown("down")){
            setLocation(x, y+2);
        }
       
        if (Greenfoot.isKeyDown("right")){
            move(2);
        }
       
        if (Greenfoot.isKeyDown("left")){
            move(-2);
        }
    }
    
    /**
     * This is the collision method which will check if the player is colliding with the walls, enemies or items and will put them back to the start
     * and call the minusLife method if they hit a wall or enemy when unpowered, collect items and trigger the powered mode when 3 items have been
     * collected, and kill enemies when in powered mode.
     */
    public void collision(){
        Actor Wall = getOneIntersectingObject(Wall.class);
        if(Wall != null)  
        {       
            ((MyWorld) getWorld()).minusLife();
            setLocation(160, 160);
            start = false;
        }
        
        Actor Square = getOneIntersectingObject(Square.class);
        if(Square != null)  
        {       
            ((MyWorld) getWorld()).minusLife();
            setLocation(160, 160);
            start = true;
        }
        
        Actor StarterSquare = getOneIntersectingObject(StarterSquare.class);
        if(StarterSquare != null)  
        {       
            ((MyWorld) getWorld()).openStart();
            setLocation(160, 160);
            start = true;
        }
        
        Actor ClosedStarterSquare = getOneIntersectingObject(ClosedStarterSquare.class);
        if(ClosedStarterSquare != null)  
        {       
            ((MyWorld) getWorld()).minusLife();
            setLocation(160, 160);
            start = true;
        }
        
        Actor Item = getOneIntersectingObject(Item.class);
        if(Item != null)  
        {       
            getWorld().removeObject(Item);
            ((MyWorld) getWorld()).powerups++;
            if (((MyWorld) getWorld()).powerups < 3){
                Greenfoot.playSound("ItemGrab.mp3");
                ((MyWorld) getWorld()).createItem();
            }
            else if (((MyWorld) getWorld()).powerups >= 3){
                Greenfoot.playSound("PowerUp.mp3");
                ((MyWorld) getWorld()).power();
                setImage(powered);
                setLocation(160, 160);
                start = true;
                isPowered = true;
                timer = 60;
                ((MyWorld) getWorld()).powerTime = 10;
            }
        }
        
        if (((MyWorld) getWorld()).powerups < 3){
            Actor OuterEnemy = getOneIntersectingObject(OuterEnemy.class);
            if(OuterEnemy != null)  
            {       
                ((MyWorld) getWorld()).minusLife();
                setLocation(160, 160);
                start = true;
            }
        
            Actor InnerEnemy = getOneIntersectingObject(InnerEnemy.class);
            if(InnerEnemy != null)  
            {       
                ((MyWorld) getWorld()).minusLife();
                setLocation(160, 160);
                start = true;
            }
        }
        else if (((MyWorld) getWorld()).powerups >= 3){
            Actor OuterEnemy = getOneIntersectingObject(OuterEnemy.class);
            if(OuterEnemy != null)  
            {       
                Greenfoot.playSound("EnemyKill.mp3");
                getWorld().removeObject(OuterEnemy);
                ((MyWorld) getWorld()).enemies--;
            }
        
            Actor InnerEnemy = getOneIntersectingObject(InnerEnemy.class);
            if(InnerEnemy != null)  
            {       
                Greenfoot.playSound("EnemyKill.mp3");
                getWorld().removeObject(InnerEnemy);
                ((MyWorld) getWorld()).enemies--;
            }
        }
    }
    
    /**
     * This is the start method which will check if the player has left the starting area, and then call the closeStart and removeText method if it has.
     */
    public void start()
    {
        int x = getX();
        int y = getY();
        
        if((x >= 285) && (start == true))  
        {       
           ((MyWorld) getWorld()).closeStart();
           start = false;
           ((MyWorld) getWorld()).removeText();
        }
    }
    
    /**
     * This is the countdown method which will check if all the enemies have been killed while powered up, and start a 10 second countdown when entering
     * the powered state, and then ending the powered state if all the enemies are killed or if the countdown reaches 0.
     */
    public void Countdown(){
        if (((MyWorld) getWorld()).enemies <= 0) {
            if (((MyWorld) getWorld()).wave >= 5){
                ((MyWorld) getWorld()).wonGame();
            }
            else{
                Greenfoot.playSound("PowerDown.mp3");
                ((MyWorld) getWorld()).unPower();
                setImage(unPowered);
                setLocation(160, 160);
                start = true;
                isPowered = false;
                ((MyWorld) getWorld()).nextWave();
            }
        }
        else if (((MyWorld) getWorld()).powerTime <= 0){
            Greenfoot.playSound("PowerDown.mp3");
            ((MyWorld) getWorld()).unPower();
            setImage(unPowered);
            setLocation(160, 160);
            start = true;
            isPowered = false;
        }
        else if (timer > 0){
            timer--;
        }
        else if (((MyWorld) getWorld()).powerTime > 0){
            ((MyWorld) getWorld()).powerTime--;
            ((MyWorld) getWorld()).updatePowerTime();
            timer = 60;
        }
    }
}