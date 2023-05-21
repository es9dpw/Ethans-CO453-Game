import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x600 cells with a cell size of 1x1 pixels.
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
    
    public void removeText()
    {
        showText(null, 300, 10);
        showText(null, 300, 30);
        showText(null, 300, 50);
    }
    
    public void closeStart()
    {
        closedStarterSquare = new ClosedStarterSquare();
        addObject(closedStarterSquare, 160, 160);
    }
    
    public void openStart()
    {
        removeObject(closedStarterSquare);
    }
    
    public void minusLife()
    {
        if (powerups < 3){
            lives--;
            showText("Lives: " + lives, 500, 80);
            if (lives <= 0){
            endGame();
            }
        }
    }
    
    public void power()
    {
        openStart();
        setBackground(powered);
        showText("Powerups: " + powerups + "/3", 470, 100);
        powerTime = 10;
        showText("Powered Up For " + powerTime, 440, 180);
    }
    
    public void updatePowerTime()
    {
        showText("Powered Up For " + powerTime, 440, 180);
    }
    
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
    
    public void endGame()
    {
        showText("Game Over: You have Lost!", 300, 300);
        Greenfoot.stop();
    }
    
    public void wonGame()
    {
        showText(null, 440, 180);
        showText("Congratulations, you have won!", 300, 300);
        Greenfoot.stop();
    }
}
