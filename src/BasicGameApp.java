//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable, KeyListener {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
	public Image astroPic;
	public Image background;
	public Image astroPic2;
	public Image astroPic3;
	public Image asteroidPic;
	public Image asteroidPic2;
	public Image asteroidPic3;
	public Image spaceshipPic;
	public Image starPic;
	public Image starPic2;
	public Image starPic3;
	public Image moonPic;
	public Image earthPic;
	public Image spacedawgPic;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	private Astronaut astro;
	private Astronaut astro2;
	private Astronaut astro3;
	private Astronaut asteroid;
	private Astronaut asteroid2;
	private Astronaut asteroid3;
	private Astronaut spaceship;
	private Astronaut shootingstar;
	private Astronaut shootingstar2;
	private Astronaut shootingstar3;
	private Astronaut moon;
	private Astronaut earth;
	private Astronaut spacedawg;
	public Astronaut[] astros = new Astronaut[100];



   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();
       
      //variable and objects
      //create (construct) the objects needed for the game and load up 
		astroPic = Toolkit.getDefaultToolkit().getImage("astronaut.png"); //load the picture
		astro = new Astronaut((int)(Math.random())*940,(int)(Math.random()*700));

		astroPic2 = Toolkit.getDefaultToolkit().getImage("astronaut.png"); //load the picture
		astro2 = new Astronaut((int)(Math.random())*940,(int)(Math.random()*700));

		astroPic3 = Toolkit.getDefaultToolkit().getImage("astronaut.png"); //load the picture
		astro3 = new Astronaut((int)(Math.random())*940,(int)(Math.random()*700));


		// more

		asteroidPic = Toolkit.getDefaultToolkit().getImage("Asteroid.png"); //load the picture
		asteroid = new Astronaut((int)(Math.random()*940),(int)(Math.random()*700));

		asteroidPic2 = Toolkit.getDefaultToolkit().getImage("Asteroid.png"); //load the picture
		asteroid2 = new Astronaut((int)(Math.random()*940),(int)(Math.random()*700));

		asteroidPic3 = Toolkit.getDefaultToolkit().getImage("Asteroid.png"); //load the picture
		asteroid3 = new Astronaut((int)(Math.random()*940),(int)(Math.random()*700));

		spaceshipPic = Toolkit.getDefaultToolkit().getImage("Spaceship.jpg"); //load the picture
		spaceship = new Astronaut((int)(Math.random()*940),(int)(Math.random()*700));

		starPic = Toolkit.getDefaultToolkit().getImage("ShootingStar.png"); //load the picture
		shootingstar = new Astronaut((int)(Math.random()*940),(int)(Math.random()*700));

		starPic2 = Toolkit.getDefaultToolkit().getImage("ShootingStar.png"); //load the picture
		shootingstar2 = new Astronaut((int)(Math.random()*940),(int)(Math.random()*700));

		starPic3 = Toolkit.getDefaultToolkit().getImage("ShootingStar.png"); //load the picture
		shootingstar3 = new Astronaut((int)(Math.random()*940),(int)(Math.random()*700));

		moonPic = Toolkit.getDefaultToolkit().getImage("Moon.jpg"); //load the picture
		moon = new Astronaut((int)(Math.random()*940),(int)(Math.random()*700));

		earthPic = Toolkit.getDefaultToolkit().getImage("Earth.jpg"); //load the picture
		earth = new Astronaut((int)(Math.random()*940),(int)(Math.random()*700));

		spacedawgPic = Toolkit.getDefaultToolkit().getImage("SpaceDawg.png"); //load the picture
		spacedawg = new Astronaut((int)(Math.random()*940),(int)(Math.random()*700));

		background = Toolkit.getDefaultToolkit().getImage("night sky stars.jpg"); //load the picture

		// array

		for(int z = 0; z < astros.length; z++){
			astros[z] = new Astronaut((int)(Math.random()*940),(int)(Math.random()*700));

		}


		//spacedawg = new Astronaut((int)(Math.random())*940,(int)(Math.random()*700));
	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(15); // sleep for 10 ms
		}
	}


	public void moveThings()
	{
      //calls the move( ) code in the objects
		astro.WrapAndControl();
		astro2.bounce();
		astro3.bounce();
		astro3.isAlive = false;

		asteroid.bounce();
		asteroid2.bounce();
		asteroid3.bounce();

		spaceship.wrap();

		shootingstar.wrap();
		shootingstar2.wrap();
		shootingstar3.wrap();

		moon.bounce();
		earth.bounce();

		spacedawg.bounce();

		for(int x = 0; x < astros.length; x++) {
			astros[x].bounce();
		}

		if(astro.rec.intersects(astro2.rec) && astro.isCrashing == false) {
			System.out.println("Crash");
			astro2.Collision();
			astro.Collision();
//			astro.height = astro.height + 5;
//			astro.width = astro.width + 5;
//			astro.isCrashing = true;
//
		}
		if(astro.rec.intersects(astro3.rec) && astro.isCrashing == false) {
			System.out.println("Crash");
			astro3.Collision();
			astro.Collision();
//			astro.height = astro.height - 5;
//			astro.width = astro.width - 5;
//			astro.isCrashing = true;
			}

		if(astro2.rec.intersects(astro3.rec) && astro2.isCrashing == false) {
			System.out.println("Crash");
			astro3.Collision();
			astro2.Collision();
		}



		if(astro.rec.intersects(astro3.rec) == false){
			astro.isCrashing = false;
		}

	}
	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
	  canvas.addKeyListener(this);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);

      //draw the image of the astronaut
		g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
		g.drawImage(astroPic, astro.xpos, astro.ypos, astro.width, astro.height, null);
		g.drawImage(astroPic2, astro2.xpos, astro2.ypos, astro2.width, astro2.height, null);
		g.drawImage(astroPic3, astro3.xpos, astro3.ypos, astro3.width, astro3.height, null);

		// more

		g.drawImage(asteroidPic, asteroid.xpos, asteroid.ypos, asteroid.width, asteroid.height, null);
		g.drawImage(asteroidPic2, asteroid2.xpos, asteroid2.ypos, asteroid2.width, asteroid2.height, null);		g.drawImage(astroPic3, astro3.xpos, astro3.ypos, astro3.width, astro3.height, null);
		g.drawImage(asteroidPic3, asteroid3.xpos, asteroid3.ypos, asteroid3.width, asteroid3.height, null);

		g.drawImage(spaceshipPic, spaceship.xpos, spaceship.ypos, spaceship.width, spaceship.height, null);

		g.drawImage(starPic, shootingstar.xpos, shootingstar.ypos, shootingstar.width, shootingstar.height, null);
		g.drawImage(starPic2, shootingstar2.xpos, shootingstar2.ypos, shootingstar2.width, shootingstar2.height, null);
		g.drawImage(starPic3, shootingstar3.xpos, shootingstar3.ypos, shootingstar3.width, shootingstar3.height, null);

		g.drawImage(moonPic, moon.xpos, moon.ypos, moon.width, moon.height, null);
		g.drawImage(earthPic, earth.xpos, earth.ypos, earth.width, earth.height, null);

		g.drawImage(spacedawgPic, spacedawg.xpos, spacedawg.ypos, spacedawg.width, spacedawg.height, null);

		for(int a = 0; a < astros.length; a++){
			g.drawImage(astroPic, astros[a].xpos, astros[a].ypos, astros[a].width, astros[a].height, null);
		}

		g.dispose();

		bufferStrategy.show();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		if(e.getKeyCode() == 87){
			System.out.println("Going Up");
//			astro.dx = 0;
//			astro.dy = -5;
			astro.isNorth = true;
		}
		if(e.getKeyCode() == 65){
			System.out.println("Going Left");
//			astro.dx = -5;
//			astro.dy = 0;
			astro.isEast = true;
		}
		if(e.getKeyCode() == 83){
			System.out.println("Going Down");
//			astro.dx = 0;
//			astro.dy = 5;
			astro.isSouth = true;
		}
		if(e.getKeyCode() == 68){
			System.out.println("Going Right");
//			astro.dx = 5;
//			astro.dy = 0;
			astro.isWest = true;
		}


	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 87){
			System.out.println("Going Up");
			astro.isNorth = false;
		}
		if(e.getKeyCode() == 65){
			System.out.println("Going left");
			astro.isEast = false;
		}
		if(e.getKeyCode() == 83){
			System.out.println("Going down");
			astro.isSouth = false;
		}
		if(e.getKeyCode() == 68){
			System.out.println("Going right");
			astro.isWest = false;
		}

	}
}