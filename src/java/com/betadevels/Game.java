package com.betadevels;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Game
{
	private PlayerRocket playerRocket;
	private LandingArea landingArea;
	private Maze maze;
	private Key key;
	private BufferedImage backgroundImg;
	private BufferedImage redBorderImg;

	public Game()
	{
		Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;

		Thread threadForInitGame = new Thread()
		{
			public void run()
			{
				Game.this.Initialize();

				Game.this.LoadContent();

				Framework.gameState = Framework.GameState.PLAYING;
			}
		};
		threadForInitGame.start();
	}


	private void Initialize()
	{
		this.maze = new Maze();
		this.key = new Key();
	}


	private void LoadContent()
	{
		try
		{
			URL backgroundImgUrl = getClass().getClassLoader().getResource( "background.jpg" );
			this.backgroundImg = ImageIO.read( backgroundImgUrl );

			URL redBorderImgUrl = getClass().getClassLoader().getResource( "red_border.png" );
			this.redBorderImg = ImageIO.read( redBorderImgUrl );
		}
		catch( IOException ex )
		{
			Logger.getLogger( Game.class.getName() ).log( Level.SEVERE, null, ex );
		}
	}


	public void RestartGame()
	{
		this.maze.ResetMaze();
		this.key.ResetPlayer();
	}


	public void UpdateGame( long gameTime, Point mousePosition )
	{
		this.key.Update();
	}


	public void Draw( Graphics2D g2d, Point mousePosition )
	{
		this.maze.Draw( g2d, mousePosition );
		this.key.Draw( g2d );
	}


	public void DrawGameOver( Graphics2D g2d, Point mousePosition, long gameTime )
	{
		Draw( g2d, mousePosition );

		g2d.drawString( "Press space or enter to restart.", Framework.frameWidth / 2 - 100,
				Framework.frameHeight / 3 + 70 );

		if( Key.landed )
		{
			g2d.drawString( "You have reached the portal!", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 );
			g2d.drawString( "You have reched in " + gameTime / 1000000000L + " seconds.",
					Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 20 );
		}
		else
		{
			g2d.setColor( Color.red );
			g2d.drawString( "You have lost your portal key!", Framework.frameWidth / 2 - 95,
					Framework.frameHeight / 3 );
			g2d.drawImage( this.redBorderImg, 0, 0, Framework.frameWidth, Framework.frameHeight, null );
		}
	}

	public void DrawOptionMenu( Graphics2D g2d, Point mousePosition )
	{
		g2d.setBackground( Color.black );
		g2d.setColor( Color.WHITE );


		g2d.setComposite( AlphaComposite.getInstance( 3, 0.2F ) );
		g2d.setFont( new Font( "Arial", 1, 25 ) );
		g2d.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON );
		g2d.drawString( "Resume", 365, 278 );
		System.out.println( mousePosition );
	}
}