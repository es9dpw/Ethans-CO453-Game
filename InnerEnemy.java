import greenfoot.*;

/**
 * The InnerEnemy class is the enemy that moves around in the middle of the world.
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
     * This is the act method which will run everytime the game acts, its main job is to call other methods.
     */
    public void act()
    {
        changeDirection();
        move();
        image();
    }
    
    /**
     * This is the changeDirection method which will check if the enemy has reached the middle of the world so it can turn and move away again.
     */
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
    
    /**
     * This is the move method which will check which way the enemy should be moving, and then move it in that direction by the speed from the world.
     * It will also update the imageState variable to allow the image of the enemy to be correctly changed.
     */
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
    
    /**
     * This is the image method which will change the enemy image depending on which direction they are going, and if the player is powered up or not.
     */
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