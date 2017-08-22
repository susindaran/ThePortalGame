package com.betadevels;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class PlayerRocket
{
	private Random random;
	public int x;
	public int y;
	public boolean landed;
	public boolean crashed;
	private int speedAccelerating;
	private int speedStopping;
	public int topLandingSpeed;
	private int speedX;
	public int speedY;
	private BufferedImage rocketImg;
	private BufferedImage rocketLandedImg;
	private BufferedImage rocketCrashedImg;
	private BufferedImage rocketFireImg;
	public int rocketImgWidth;
	public int rocketImgHeight;

	public PlayerRocket()
	{
		Initialize();
		LoadContent();
		this.x = this.random.nextInt( Framework.frameWidth - this.rocketImgWidth );
	}

	private void Initialize()
	{
		this.random = new Random();
		ResetPlayer();
		this.speedAccelerating = 2;
		this.speedStopping = 1;
		this.topLandingSpeed = 5;
	}

	private void LoadContent()
	{
		try
		{
			URL rocketImgUrl = getClass().getResource( "rocket.png" );
			this.rocketImg = ImageIO.read( rocketImgUrl );
			this.rocketImgWidth = this.rocketImg.getWidth();
			this.rocketImgHeight = this.rocketImg.getHeight();

			URL rocketLandedImgUrl = getClass().getResource( "rocket_landed.png" );
			this.rocketLandedImg = ImageIO.read( rocketLandedImgUrl );

			URL rocketCrashedImgUrl = getClass().getResource( "rocket_crashed.png" );
			this.rocketCrashedImg = ImageIO.read( rocketCrashedImgUrl );

			URL rocketFireImgUrl = getClass().getResource( "rocket_fire.png" );
			this.rocketFireImg = ImageIO.read( rocketFireImgUrl );
		}
		catch( IOException ex )
		{
			Logger.getLogger( PlayerRocket.class.getName() ).log( Level.SEVERE, null, ex );
		}
	}

	public void ResetPlayer()
	{
		this.landed = false;
		this.crashed = false;

		this.x = this.random.nextInt( Framework.frameWidth - this.rocketImgWidth );
		this.y = 10;

		this.speedX = 0;
		this.speedY = 0;
	}

	public void Update()
	{
		if( Canvas.keyboardKeyState( 87 ) )
		{
			this.speedY -= this.speedAccelerating;
		}
		else
		{
			this.speedY += this.speedStopping;
		}

		if( Canvas.keyboardKeyState( 65 ) )
		{
			this.speedX -= this.speedAccelerating;
		}
		else if( this.speedX < 0 )
		{
			this.speedX += this.speedStopping;
		}

		if( Canvas.keyboardKeyState( 68 ) )
		{
			this.speedX += this.speedAccelerating;
		}
		else if( this.speedX > 0 )
		{
			this.speedX -= this.speedStopping;
		}

		this.x += this.speedX;
		this.y += this.speedY;
	}

	public void Draw( Graphics2D g2d )
	{
		g2d.setColor( Color.white );
		g2d.drawString( "Rocket coordinates: " + this.x + " : " + this.y, 5, 15 );


		if( this.landed )
		{
			g2d.drawImage( this.rocketLandedImg, this.x, this.y, null );

		}
		else if( this.crashed )
		{
			g2d.drawImage( this.rocketCrashedImg, this.x,
					this.y + this.rocketImgHeight - this.rocketCrashedImg.getHeight(), null );

		}
		else
		{

			if( Canvas.keyboardKeyState( 87 ) )
			{
				g2d.drawImage( this.rocketFireImg, this.x + 12, this.y + 66, null );
			}
			g2d.drawImage( this.rocketImg, this.x, this.y, null );
		}
	}
}