package com.betadevels;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Framework extends Canvas
{
	public static int frameWidth;
	public static int frameHeight;
	public static final long secInNanosec = 1000000000L;
	public static final long milisecInNanosec = 1000000L;
	private int GAME_FPS = 16;

	private long GAME_UPDATE_PERIOD = 1000000000L / this.GAME_FPS;
	public static GameState gameState;
	private long gameTime;
	private long lastTime;

	public static enum GameState
	{
		STARTING, VISUALIZING, GAME_CONTENT_LOADING, MAIN_MENU, OPTIONS, PLAYING, GAMEOVER, DESTROYED, DISPLAY, CHOOSE_LEVEL;

		GameState()
		{
		}
	}

	private Game game;
	private FontMetrics fontMetrics;
	private int i = 1;
	private float alpha = 0.5F;
	private BufferedImage thePortalMenuImg;
	private BufferedImage candidCoderzImg;

	public Framework()
	{
		gameState = GameState.VISUALIZING;
		Thread gameThread = new Thread( Framework.this::gameLoop );
		gameThread.start();
	}

	private void initialize()
	{
	}

	private void loadContent()
	{
		try
		{
			this.thePortalMenuImg = ImageIO.read( getClass().getClassLoader().getResource( "the_portal_menu.png" ) );
			this.candidCoderzImg = ImageIO.read( getClass().getClassLoader().getResource( "candid_coderz.jpg" ) );
		}
		catch( IOException ex )
		{
			Logger.getLogger( Framework.class.getName() ).log( Level.SEVERE, null, ex );
		}
	}

	private void gameLoop()
	{
		long visualizingTime = 0L;
		long lastVisualizingTime = System.nanoTime();

		while( true )
		{
			long beginTime = System.nanoTime();
			switch( gameState )
			{
				case PLAYING:
					this.gameTime += System.nanoTime() - this.lastTime;
					this.game.updateGame( this.gameTime, mousePosition() );
					this.lastTime = System.nanoTime();
					break;

				case GAMEOVER:
					break;

				case MAIN_MENU:
					break;

				case OPTIONS:
					break;

				case CHOOSE_LEVEL:
					break;

				case GAME_CONTENT_LOADING:
					break;

				case STARTING:
					initialize();
					loadContent();
					gameState = GameState.DISPLAY;
					lastVisualizingTime = System.nanoTime();
					visualizingTime = 0L;
					break;

				case DISPLAY:
					if( visualizingTime > 900000000L )
					{
						this.GAME_FPS = 250;
						this.GAME_UPDATE_PERIOD = ( 1000000000L / this.GAME_FPS );
						gameState = GameState.MAIN_MENU;
						try
						{
							Thread.sleep( 2000L );
						}
						catch( InterruptedException ex )
						{
							Logger.getLogger( Framework.class.getName() ).log( Level.SEVERE, null, ex );
						}

					}
					else
					{
						visualizingTime += System.nanoTime() - lastVisualizingTime;
						lastVisualizingTime = System.nanoTime();
					}
					break;

				case VISUALIZING:
					if( ( getWidth() > 1 ) && ( visualizingTime > 1500000000L ) )
					{
						frameWidth = getWidth();
						frameHeight = getHeight();
						gameState = GameState.STARTING;
					}
					else
					{
						visualizingTime += System.nanoTime() - lastVisualizingTime;
						lastVisualizingTime = System.nanoTime();
					}

					break;
			}

			repaint();

			long timeTaken = System.nanoTime() - beginTime;
			long timeLeft = ( this.GAME_UPDATE_PERIOD - timeTaken ) / 1000000L;

			if( timeLeft < 10L )
			{
				timeLeft = 10L;
			}
			try
			{
				Thread.sleep( timeLeft );
			}
			catch( InterruptedException ex )
			{
				Logger.getLogger( getClass().getName() ).log( Level.SEVERE, ex.getMessage() );
			}
		}
	}

	public void draw( Graphics2D g2d )
	{
		AlphaComposite composite;

		switch( gameState )
		{
			case PLAYING:
				this.game.draw( g2d, mousePosition() );
				break;

			case GAMEOVER:
				this.game.drawGameOver( g2d, mousePosition(), this.gameTime );
				break;

			case DISPLAY:
				int type = 3;
				composite = AlphaComposite.getInstance( type, this.alpha );
				g2d.setComposite( composite );
				g2d.drawImage( this.candidCoderzImg, 0, 0, frameWidth, frameHeight, null );
				this.alpha += 0.01F;
				break;

			case MAIN_MENU:
				g2d.drawImage( this.thePortalMenuImg, 0, 0, frameWidth, frameHeight, null );

				composite = AlphaComposite.getInstance( 3, 0.4F );
				g2d.setComposite( composite );
				g2d.setColor( Color.cyan );
				Font textFont = new Font( "Arial", 1, 20 );
				g2d.setFont( textFont );
				g2d.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON );
				g2d.drawRect( 390, 390, 145, 25 );
				g2d.drawRect( 380, 425, 165, 25 );
				g2d.drawString( "Start Game", 400, 410 );
				g2d.drawString( "Choose Level", 388, 445 );
				this.i += 1;
				break;

			case OPTIONS:
				this.game.drawOptionMenu( g2d, mousePosition() );
				break;

			case CHOOSE_LEVEL:
				g2d.setBackground( Color.black );
				g2d.setColor( Color.WHITE );
				g2d.setComposite( AlphaComposite.getInstance( 3, 0.2F ) );
				g2d.setFont( new Font( "Arial", 1, 25 ) );
				g2d.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON );
				g2d.drawString( "COMING SOON!", 365, 278 );
				g2d.drawRect( 390, 388, 100, 25 );
				g2d.drawString( "BACK", 400, 410 );
				break;

			case STARTING:
				g2d.setColor( Color.white );
				textFont = new Font( "Arial", 1, 16 );
				g2d.setFont( textFont );
				g2d.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON );
				g2d.drawString( "Loading Contents", 550, 525 );
				break;

			case GAME_CONTENT_LOADING:
				g2d.setColor( Color.white );
				g2d.drawString( "GAME is LOADING", frameWidth / 2 - 50, frameHeight / 2 );
				break;

			case VISUALIZING:
				String text = "Game Frame Window is Visualizing";
				g2d.setColor( Color.white );
				textFont = new Font( "Arial", 1, 16 );
				g2d.setFont( textFont );
				g2d.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON );
				g2d.drawString( text, 525, 525 );
		}

	}

	private void newGame()
	{
		this.gameTime = 0L;
		this.lastTime = System.nanoTime();
		Maze.level = 1;
		this.game = new Game();
	}

	private void restartGame()
	{
		this.gameTime = 0L;
		this.lastTime = System.nanoTime();

		this.game.restartGame();


		gameState = GameState.PLAYING;
	}

	private Point mousePosition()
	{
		try
		{
			Point mp = getMousePosition();

			if( mp != null )
			{
				return getMousePosition();
			}
			return new Point( 0, 0 );
		}
		catch( Exception e )
		{
			Logger.getLogger( getClass().getName() ).log( Level.WARNING, e.getMessage() );
		}

		return new Point( 0, 0 );
	}

	public void keyReleasedFramework( KeyEvent e )
	{
		switch( gameState )
		{
			case MAIN_MENU:
				newGame();
				break;
			case GAMEOVER:
				if( ( e.getKeyCode() == 32 ) || ( e.getKeyCode() == 10 ) )
				{
					if( Key.landed )
					{
						Maze.level += 1;
					}
					restartGame();
				}

				break;
			case PLAYING:
				if( e.getKeyCode() == 27 )
				{
					gameState = GameState.OPTIONS;
				}


				break;
		}

	}

	public void mouseClicked( MouseEvent e )
	{
		if( gameState == GameState.OPTIONS )
		{
			if( ( getMousePosition().getX() > 200.0D ) && ( getMousePosition().getY() > 200.0D ) )
			{
				gameState = GameState.PLAYING;
			}
		}
		else if( gameState == GameState.MAIN_MENU )
		{
			if( ( getMousePosition().getX() > 390.0D ) && ( getMousePosition().getY() > 390.0D ) && ( getMousePosition()
					.getX() < 550.0D ) && ( getMousePosition().getY() < 415.0D ) )
			{
				newGame();
			}

			if( ( getMousePosition().getX() > 380.0D ) && ( getMousePosition().getY() > 425.0D ) && ( getMousePosition()
					.getX() < 525.0D ) && ( getMousePosition().getY() < 450.0D ) )
			{
				gameState = GameState.CHOOSE_LEVEL;
			}
		}
		else if( gameState == GameState.CHOOSE_LEVEL )
		{
			if( ( getMousePosition().getX() > 390.0D ) && ( getMousePosition().getY() > 390.0D ) && ( getMousePosition()
					.getX() < 510.0D ) && ( getMousePosition().getY() < 415.0D ) )
			{
				gameState = GameState.MAIN_MENU;
			}
		}
	}
}