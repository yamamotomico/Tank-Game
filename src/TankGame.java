package src;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class TankGame extends Canvas implements Runnable {

    //initialize width and height for easy use
    public static int Width = 1000;
    public static int Height = 563;
    //**************************************************
    public static int tank1healthBar;
    public static int tank1Lives;
    public static int tank2healthBar;
    public static int tank2Lives;
    //**************************************************
    private boolean isRunning = false;
    private Thread thread;
    private Handler gameHandler;
    //**************************************************
    private CameraView camTankOne;
    private CameraView camTankTwo;
    private SpriteSheet pictureLoader;
    boolean endgameText = false;
    String textFile;
    //**************************************************
    private BufferedImage level;
    private BufferedImage floor;
    private BufferedImage lives;
    private BufferedImage s_sheet;
    private imageBackground imageBackground;
    private BufferedImage BackgroundImage;

    //trial
    SoundPlayer sounds;

    public TankGame(){
        new GameWindow(Width, Height, "Tank Game!", this);
        start();

        gameHandler = new Handler();
        camTankOne = new CameraView(0,0);
        camTankTwo = new CameraView(0,0);
        this.addKeyListener(new KeyInput(gameHandler));
        sounds = new SoundPlayer("/Music.mid");

        //loads level made from Paint
        BackgroundLevel loader = new BackgroundLevel();
        level = loader.loadImage("/tank_level.png");

        //puts the background3
        imageBackground = new imageBackground();
        floor = imageBackground.getBackground();
        BackgroundImage = floor.getSubimage(0, 0, Width, Height);

        //loads picture for box
        s_sheet = loader.loadImage("/images.png");
        pictureLoader = new SpriteSheet(s_sheet);

        //loads picture for the lives
        lives = loader.loadImage("/heart.png");

        loadLevel(level);

    }

    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop(){
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        //taken from Nash, the developer of minecraft
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                delta--;
            }

            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
        //******************************************************************
    }
    //updates everything in the game
    public void tick(){

        for (int i = 0; i < gameHandler.object.size(); i++) {
            if (gameHandler.object.get(i).getId() == gameID.Player1) {
                camTankOne.cameraTankOne(gameHandler.object.get(i));
                tank1healthBar = gameHandler.object.get(i).getHealthBar();
                tank1Lives = gameHandler.object.get(i).getLives();
                if (tank1healthBar <= 0 && tank1Lives == 0) {
                    endgameText = true;
                    textFile = "Winner: Player 2";
                }
            }
        }
        for (int i = 0; i < gameHandler.object.size(); i++) {
            if (gameHandler.object.get(i).getId() == gameID.Player2) {
                camTankTwo.cameraTankTwo(gameHandler.object.get(i));
                tank2healthBar = gameHandler.object.get(i).getHealthBar();
                tank2Lives = gameHandler.object.get(i).getLives();
                if (tank2healthBar <= 0 && tank2Lives == 0) {
                    endgameText = true;
                    textFile = "Winner: Player 1";
                }
            }
        }
        gameHandler.tick();
    }

    //renders everything in the game
    public void render(){
        BufferStrategy buffer = this.getBufferStrategy();   //starts of as null
        if (buffer == null) {
            this.createBufferStrategy(3);   //creates 3 argument
            return;
        }
        Graphics graphics = buffer.getDrawGraphics();
        Graphics2D graphics2D = (Graphics2D) graphics;

        //Start of drawing
        //*************************************************************************

        graphics.setColor(Color.darkGray);
        graphics.fillRect(0,0, Width, Height);

        //puts the background2
        graphics.drawImage(floor, 0, 0, null);

        //Tank one camera focus
        graphics2D.translate(-camTankOne.getX(),-camTankOne.getY());
        gameHandler.renderTankOne(graphics, camTankOne);
        graphics2D.translate(camTankOne.getX(),camTankOne.getY());

        //Tank two camera focus
        graphics2D.translate(-camTankTwo.getX(),-camTankTwo.getY());
        gameHandler.renderTankTwo(graphics, camTankTwo);
        graphics2D.translate(camTankTwo.getX(),camTankTwo.getY());

        //minimap
        graphics.drawImage(BackgroundImage, 380, 324, Width / 4, Height / 2, null);
        Handler.miniMap(graphics, 380, 324);

        //Tank one health bar
        graphics.setColor(Color.gray);
        graphics.fillRect(10,500,100,20);
        graphics.setColor(Color.red);
        graphics.fillRect(10,500, tank1healthBar,20);
        graphics.setColor(Color.black);
        graphics.drawRect(10,500,100,20);

        //Tank one lives
        for (int i = 0; i < tank1Lives; i++){
            graphics.drawImage(lives, 115 + 30 * i, 491, 34, 34, null);
        }

        //Tank two health bar
        graphics.setColor(Color.darkGray);
        graphics.fillRect(645,500,100,20);
        graphics.setColor(Color.green);
        graphics.fillRect(645,500, tank2healthBar,20);
        graphics.setColor(Color.black);
        graphics.drawRect(645,500,100,20);

        //Tank two lives
        for (int i = 0; i < tank2Lives; i++){
            graphics.drawImage(lives, 750 + 30 * i, 491, 34, 34, null);
        }

        if (endgameText) {
            Font font = new Font("Broadway", Font.BOLD, 20);

            FontMetrics position = graphics.getFontMetrics(font);
            int xPosition = (Width- position.stringWidth(textFile)) / 2;
            int yPosition = (Height - position.getHeight()) / 2 + position.getAscent();
            graphics.setFont(font);

            graphics.setColor(Color.darkGray);
            graphics.fillRect(0, 0, Width, Height);
            graphics.setColor(Color.orange);
            graphics.drawString(textFile, xPosition, yPosition - 15);


        }
        //*************************************************************************
        graphics.dispose();
        buffer.show();

    }

    private void loadLevel(BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int p = image.getRGB(i, j);

                int red = (p >> 16) & 0xff;
                int green = (p >> 8) & 0xff;
                int blue = (p) & 0xff;

                if (green == 255) {
                    gameHandler.addObject(new UnbreakableBlock(i*32,j*32,gameID.UnbreakableBlock, pictureLoader));
                }

                if (red == 255) {
                    gameHandler.addObject(new BreakableBlock(i*32,j*32,gameID.BreakableBlock, pictureLoader));
                }

                if (blue == 255) {
                    gameHandler.addObject(new TankOne(i* 32, j * 32, 0, gameID.Player1, gameHandler));
                }

                if (blue == 150) {
                    gameHandler.addObject(new TankTwo(i* 32, j * 32, 0, gameID.Player2, gameHandler));
                }
                if (red == 150) {
                    gameHandler.addObject(new Upgrade(i* 32, j * 32, gameID.Upgrade));
                }
                if (red == 100) {
                    gameHandler.addObject(new SuperUpgrade(i* 32, j * 32, gameID.SuperUpgrade));
                }

            }
        }

    }


    public static void main(String[] args){
        new TankGame();
    }

}
