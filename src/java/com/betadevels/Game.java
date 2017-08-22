package com.betadevels;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

		Thread threadForInitGame = new Thread( () ->
		{
			Game.this.initialize();
			Game.this.loadContent();
			Framework.gameState = Framework.GameState.PLAYING;
		} );
		threadForInitGame.start();
	}

	private void initialize()
	{
		this.maze = new Maze();
		this.key = new Key();
	}

	private void loadContent()
	{
		try
		{
			this.backgroundImg = ImageIO.read( getClass().getClassLoader().getResource( "background.jpg" ) );
			this.redBorderImg = ImageIO.read( getClass().getClassLoader().getResource( "red_border.png" ) );
		}
		catch( IOException ex )
		{
			Logger.getLogger( Game.class.getName() ).log( Level.SEVERE, null, ex );
		}
	}


	public void restartGame()
	{
		this.maze.resetMaze();
		this.key.ResetPlayer();
	}

	public void updateGame( long gameTime, Point mousePosition )
	{
		this.key.Update();
	}

	public void draw( Graphics2D g2d, Point mousePosition )
	{
		this.maze.draw( g2d, mousePosition );
		this.key.Draw( g2d );
	}

	public void drawGameOver( Graphics2D g2d, Point mousePosition, long gameTime )
	{
		draw( g2d, mousePosition );
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

	public void drawOptionMenu( Graphics2D g2d, Point mousePosition )
	{
		g2d.setBackground( Color.black );
		g2d.setColor( Color.WHITE );
		g2d.setComposite( AlphaComposite.getInstance( 3, 0.2F ) );
		g2d.setFont( new Font( "Arial", 1, 25 ) );
		g2d.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON );
		g2d.drawString( "Resume", 365, 278 );
	}
}