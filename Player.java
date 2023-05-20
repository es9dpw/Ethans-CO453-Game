import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private boolean start = true;
    GreenfootImage unPowered = new GreenfootImage("Player.png");
    GreenfootImage powered = new GreenfootImage("PoweredPlayer.png");
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move();
        collision();
        start(); 
    }
    
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
    
    public void collision(){
        Actor Wall = getOneIntersectingObject(Wall.class);
        if(Wall != null)  
        {       
            ((MyWorld) getWorld()).openStart();
            ((MyWorld) getWorld()).minusLife();
            setLocation(160, 160);
            start = false;
        }
        
        Actor Square = getOneIntersectingObject(Square.class);
        if(Square != null)  
        {       
            ((MyWorld) getWorld()).openStart();
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
            ((MyWorld) getWorld()).openStart();
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
                ((MyWorld) getWorld()).createItem();
            }
            else if (((MyWorld) getWorld()).powerups >= 3){
                ((MyWorld) getWorld()).power();
                setImage(powered);
                setLocation(160, 160);
                start = true;
            }
        }
        
        if (((MyWorld) getWorld()).powerups < 3){
            Actor OuterEnemy = getOneIntersectingObject(OuterEnemy.class);
            if(OuterEnemy != null)  
            {       
                ((MyWorld) getWorld()).openStart();
                ((MyWorld) getWorld()).minusLife();
                setLocation(160, 160);
                start = true;
            }
        
            Actor InnerEnemy = getOneIntersectingObject(InnerEnemy.class);
            if(InnerEnemy != null)  
            {       
                ((MyWorld) getWorld()).openStart();
                ((MyWorld) getWorld()).minusLife();
                setLocation(160, 160);
                start = true;
            }
        }
        else if (((MyWorld) getWorld()).powerups >= 3){
            Actor OuterEnemy = getOneIntersectingObject(OuterEnemy.class);
            if(OuterEnemy != null)  
            {       
                getWorld().removeObject(OuterEnemy);
                ((MyWorld) getWorld()).enemies--;
            }
        
            Actor InnerEnemy = getOneIntersectingObject(InnerEnemy.class);
            if(InnerEnemy != null)  
            {       
                getWorld().removeObject(InnerEnemy);
                ((MyWorld) getWorld()).enemies--;
            }
        }
    }
    
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
}
