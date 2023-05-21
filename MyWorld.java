import greenfoot.*;

/**
 * The MyWorld class is the world for the game.
 */
public class MyWorld extends World
{
    GreenfootImage unPowered = new GreenfootImage("bluerock.jpg");
    GreenfootImage powered = new GreenfootImage("space.jpg");
    private Wall wall;
    private Square square;
    private StarterSquare starterSquare;
    private ClosedStarterSquare closedStarterSquare;
    private Player player;
    private OuterEnemy outerEnemy;
    private InnerEnemy innerEnemy;
    private Item item;
    private int side;
    private int spawn;
    public int lives = 3;
    public int powerups = 0;
    public int wave = 1;
    public int powerTime;
    public int enemies;
    public int outerEnemySpeed = 1;
    public int innerEnemySpeed = 1;
    public int itemNumber = 1;
    public int itemOne = 5;
    public int itemTwo = 5;
    public boolean sideRepeat;
    
    /**
     * Constructor for objects of class MyWorld. Creates all the game objects such as the walls and player as well as displaying text to the player.
     */
    public MyWorld()
    {    
        super(600, 600, 1);
        
        wall = new Wall();
        addObject(wall, 300, 0);
        
        wall = new Wall();
        addObject(wall, 300, 600);
        
        wall = new Wall();
        addObject(wall, 0, 300);
        wall.turn(90);
        
        wall = new Wall();
        addObject(wall, 600, 300);
        wall.turn(90);
        
        square = new Square();
        addObject(square, 440, 160);
        
        square = new Square();
        addObject(square, 160, 440);
        
        square = new Square();
        addObject(square, 440, 440);
        
        starterSquare = new StarterSquare();
        addObject(starterSquare, 160, 75);
        
        starterSquare = new StarterSquare();
        addObject(starterSquare, 160, 245);
        
        starterSquare = new StarterSquare();
        addObject(starterSquare, 75, 160);
        starterSquare.turn(90);
        
        player = new Player();
        addObject(player, 160, 160);
        
        createEnemies();
        createItem();
        
        showText("Control the character with the arrowkeys to avoid", 300, 10);
        showText("the enemies and walls, grab the items to", 300, 30);
        showText("power up and then kill the enemies!", 300, 50);
        
        showText("Lives: " + lives, 500, 80);
        showText("Wave: " + wave + "/5", 490, 120);
    }
    
    /**
     * This is the createEnemies method which will add enemies to the world at the start of the game and everytime all current enemies are killed
     */
    public void createEnemies(){
        outerEnemy = new OuterEnemy();
        addObject(outerEnemy, 24, 24);
        
        outerEnemy = new OuterEnemy();
        addObject(outerEnemy, 24, 576);
        
        outerEnemy = new OuterEnemy();
        addObject(outerEnemy, 576, 24);
        
        outerEnemy = new OuterEnemy();
        addObject(outerEnemy, 576, 576);
        
        innerEnemy = new InnerEnemy();
        addObject(innerEnemy, 300, 300);
        
        enemies = 5;
    }
    
    /**
     * This is the createItem method which will create and item in the world at the start of the game and when an item is collected. The mthod picks a
     * coordinate for the item to spawnb on and then picks a random number to determine which side the item will spawn on, and then that side cannot
     * be picked again for the current wave.
     */
    public void createItem(){
        spawn = (Greenfoot.getRandomNumber(504)) + 48;
        sideRepeat = true;
        item = new Item();
        while (sideRepeat == true){
            side = Greenfoot.getRandomNumber(4);
            
            if ((side == 0) && (itemOne != 0) && (itemTwo != 0)){
                addObject(item, spawn, 24);
                sideRepeat = false;
            }
            if ((side == 1) && (itemOne != 1) && (itemTwo != 1)){
                addObject(item, 24, spawn);
                sideRepeat = false;
            }
            if ((side == 2) && (itemOne != 2) && (itemTwo != 2)){
                addObject(item, 576, spawn);
                sideRepeat = false;
            }
            if ((side == 3) && (itemOne != 3) && (itemTwo != 3)){
                addObject(item, spawn, 576);
                sideRepeat = false;
            }
        }
        
        if (itemNumber == 1){
            itemOne = side;
        }
        else if (itemNumber == 2){
            itemTwo = side;
        }
        showText("Powerups: " + powerups + "/3", 470, 100);
        itemNumber++;
    }
    
    /**
     * This is the removeText method which will remove the explantion text from the top of the screen once the player has left the starting area.
     */
    public void removeText()
    {
        showText(null, 300, 10);
        showText(null, 300, 30);
        showText(null, 300, 50);
    }
    
    /**
     * This is the closeStart method which will add an object ovber the starting area to close it off from the player once they have left the start area.
     */
    public void closeStart()
    {
        closedStarterSquare = new ClosedStarterSquare();
        addObject(closedStarterSquare, 160, 160);
    }
    
    /**
     * This is the openStart method which will remove the object covering the starting area to open it to the player once they have been hit or power up.
     */
    public void openStart()
    {
        removeObject(closedStarterSquare);
    }
    
    /**
     * This is the minusLife method which will remove a life each time the player gets hit and will also call the endGame method when the player runs
     * out of lives.
     */
    public void minusLife()
    {
        openStart();
        if (powerups < 3){
            lives--;
            showText("Lives: " + lives, 500, 80);
            if (lives <= 0){
                endGame();
            }
            else {
                Greenfoot.playSound("Hit.mp3");
            }
        }
    }
    
    /**
     * This is the power method which will change the background image and siplay the intial powered up time once the player powers up.
     */
    public void power()
    {
        openStart();
        setBackground(powered);
        showText("Powerups: " + powerups + "/3", 470, 100);
        powerTime = 10;
        showText("Powered Up For " + powerTime, 440, 180);
    }
    
    /**
     * This is the updatePowerTimer method which will change the power up timer every second when called from the Player class.
     */
    public void updatePowerTime()
    {
        showText("Powered Up For " + powerTime, 440, 180);
    }
    
    /**
     * This is the unPower method which will call the return the game to its unpowered state, calling openStart and changing the background image back.
     */
    public void unPower(){
        openStart();
        setBackground(unPowered);
        powerups = 0;
        showText("Powerups: " + powerups + "/3", 470, 100);
        showText(null, 440, 180);
        itemNumber = 1;
        itemOne = 5;
        itemTwo = 5;
        createItem();
    }
    
    /**
     * This is the nextWave method which will increase the speed of the enemies after they have all been killed, and then spawn more enemies.
     */
    public void nextWave(){
        wave++;
        showText("Wave: " + wave + "/5", 490, 120);
        
        if (wave == 2){
            outerEnemySpeed = 2;
        }
        if (wave == 3){
            innerEnemySpeed = 2;
        }
        if (wave == 4){
            outerEnemySpeed = 3;
        }
        if (wave == 5){
            innerEnemySpeed = 3;
        }
        
        createEnemies();
    }
    
    /**
     * This is the endGame method which will end the game once the player has lost all their lives.
     */
    public void endGame()
    {
        Greenfoot.playSound("Lose.mp3");
        showText("Game Over: You have Lost!", 300, 300);
        Greenfoot.stop();
    }
    
    /**
     * This is the wonGame method which will end the game once the player has cleared all 5 waves.
     */
    public void wonGame()
    {
        showText(null, 440, 180);
        Greenfoot.playSound("Win.mp3");
        showText("Congratulations, you have won!", 300, 300);
        Greenfoot.stop();
    }
}