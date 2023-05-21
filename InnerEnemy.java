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
    private String imageState;
    GreenfootImage unPoweredUp = new GreenfootImage("EnemyUp.png");
    GreenfootImage unPoweredDown = new GreenfootImage("EnemyDown.png");
    GreenfootImage unPoweredLeft = new GreenfootImage("EnemyLeft.png");
    GreenfootImage unPoweredRight = new GreenfootImage("EnemyRight.png");
    GreenfootImage poweredUp = new GreenfootImage("ScaredEnemyUp.png");
    GreenfootImage poweredDown = new GreenfootImage("ScaredEnemyDown.png");
    GreenfootImage poweredLeft = new GreenfootImage("ScaredEnemyLeft.png");
    GreenfootImage poweredRight = new GreenfootImage("ScaredEnemyRight.png");
    /**
     * Act - do whatever the InnerEnemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        changeDirection();
        move();
        image();
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
                imageState = "up";
            }
            else{
                setLocation(x, y+((MyWorld) getWorld()).innerEnemySpeed);
                imageState = "down";
            }
        }
        if (direction == 3){
            if (time < (240 / ((MyWorld) getWorld()).innerEnemySpeed)){
                setLocation(x, y+((MyWorld) getWorld()).innerEnemySpeed);
                imageState = "down";
            }
            else{
                setLocation(x, y-((MyWorld) getWorld()).innerEnemySpeed);
                imageState = "up";
            }
        }
       
        if (direction == 4){
            if (time < (240 / ((MyWorld) getWorld()).innerEnemySpeed)){
                move(((MyWorld) getWorld()).innerEnemySpeed);
                imageState = "right";
            }
            else{
                move(-((MyWorld) getWorld()).innerEnemySpeed);
                imageState = "left";
            }
        }
       
        if (direction == 2){
            if (time < (240 / ((MyWorld) getWorld()).innerEnemySpeed)){
                move(-((MyWorld) getWorld()).innerEnemySpeed);
                imageState = "left";
            }
            else{
                move(((MyWorld) getWorld()).innerEnemySpeed);
                imageState = "right";
            }
        }
        
        time++;
    }
    
    public void image(){
        if (((MyWorld) getWorld()).powerups <= 2){
            if (imageState == "up"){
                setImage(unPoweredUp);
            }
            else if (imageState == "down"){
                setImage(unPoweredDown);
            }
            else if (imageState == "left"){
                setImage(unPoweredLeft);
            }
            else if (imageState == "right"){
                setImage(unPoweredRight);
            }
        }
        else if (((MyWorld) getWorld()).powerups >= 3){
            if (imageState == "up"){
                setImage(poweredUp);
            }
            else if (imageState == "down"){
                setImage(poweredDown);
            }
            else if (imageState == "left"){
                setImage(poweredLeft);
            }
            else if (imageState == "right"){
                setImage(poweredRight);
            }
        }
    }
}
