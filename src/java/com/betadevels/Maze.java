package com.betadevels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Maze
{
	public static boolean touched;
	public static int level;
	public static List<Rectangle2D> rectArray;
	public static List<Rectangle2D> finishArray;
	public static List<Rectangle2D> borderArray;
	public static List<Rectangle2D> blastArray;
	private BufferedImage BGImg;
	private BufferedImage boxImg;
	private BufferedImage blastBoxImg;
	private BufferedImage finalBoxImg;
	private Rectangle2D rect;

	public Maze()
	{
		System.out.println( "Initializing Maze components" );
		initialize();
		loadComponents();
	}

	public void initialize()
	{
		level = 1;
		rectArray = new ArrayList<>();
		finishArray = new ArrayList<>();
		borderArray = new ArrayList<>();
		blastArray = new ArrayList<>();
	}

	public void loadComponents()
	{
		try
		{
			this.BGImg = ImageIO.read( getClass().getClassLoader().getResource( "subtle_texture.jpg" ) );
			this.boxImg = ImageIO.read( getClass().getClassLoader().getResource( "box_texture.png" ) );
			this.blastBoxImg = ImageIO.read( getClass().getClassLoader().getResource( "blast_box.png" ) );
			this.finalBoxImg = ImageIO.read( getClass().getClassLoader().getResource( "final_box.png" ) );
		}
		catch( IOException ex )
		{
			Logger.getLogger( Maze.class.getName() ).log( Level.SEVERE, null, ex );
		}
	}

	public void resetMaze()
	{
		rectArray.clear();
		finishArray.clear();
		borderArray.clear();
		blastArray.clear();
	}

	public void draw( Graphics2D g2d, Point mousePosition )
	{
		g2d.drawImage( this.BGImg, 0, 0, Framework.frameWidth, Framework.frameHeight, null );
		switch( level )
		{
			case 1:
				drawLevel1( g2d );
				break;

			case 2:
				drawLevel2( g2d );
		}

	}

	private void drawLevel1( Graphics2D g2d )
	{
		rectArray.clear();
		finishArray.clear();
		borderArray.clear();
		blastArray.clear();

		int y = 0, x = 0;
		for( x = 0; x < Framework.frameWidth; x += 31 )
		{
			this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
			borderArray.add( this.rect );
			g2d.draw( this.rect );
		}

		x = 0;
		for( y = 0; y < Framework.frameHeight; y += 31 )
		{
			this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
			borderArray.add( this.rect );
			g2d.draw( this.rect );
		}

		x = 868;
		for( y = 0; y < Framework.frameHeight; y += 31 )
		{
			this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
			borderArray.add( this.rect );
			g2d.draw( this.rect );
		}

		y = 558;
		for( x = 0; x < Framework.frameWidth; x += 31 )
		{
			this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
			borderArray.add( this.rect );
			g2d.draw( this.rect );
		}
		g2d.setColor( Color.red );

		x = 217;
		y = 279;
		rectArray.add( new Rectangle2D.Double( x, y, 30.0D, 30.0D ) );

		g2d.drawImage( this.boxImg, x, y, 30, 30, null );

		x = 186;
		y = 155;
		rectArray.add( new Rectangle2D.Double( x, y, 30.0D, 30.0D ) );
		g2d.drawImage( this.boxImg, x, y, 30, 30, null );

		y = 217;
		rectArray.add( new Rectangle2D.Double( x, y, 30.0D, 30.0D ) );
		g2d.drawImage( this.boxImg, x, y, 30, 30, null );

		for( y = 341; y <= 372; y += 31 )
		{
			this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
			rectArray.add( this.rect );

			g2d.drawImage( this.boxImg, x, y, 30, 30, null );
		}
		y = 403;
		x = 124;
		this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
		rectArray.add( this.rect );

		g2d.drawImage( this.boxImg, x, y, 30, 30, null );

		x = 155;
		y = 279;
		this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
		rectArray.add( this.rect );

		g2d.drawImage( this.boxImg, x, y, 30, 30, null );

		x = 217;
		y = 434;
		this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
		rectArray.add( this.rect );

		g2d.drawImage( this.boxImg, x, y, 30, 30, null );

		y = 465;
		this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
		rectArray.add( this.rect );

		g2d.drawImage( this.boxImg, x, y, 30, 30, null );

		y = 124;
		for( x = 248; x <= 279; x += 31 )
		{
			this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
			g2d.drawImage( this.boxImg, x, y, 30, 30, null );
			rectArray.add( this.rect );
		}

		for( x = 341; x <= 434; x += 31 )
		{
			this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
			g2d.drawImage( this.boxImg, x, y, 30, 30, null );
			rectArray.add( this.rect );
		}

		x = 372;
		y = 155;
		this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
		g2d.drawImage( this.boxImg, x, y, 30, 30, null );
		rectArray.add( this.rect );


		y = 434;
		x = 341;
		this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
		g2d.drawImage( this.boxImg, x, y, 30, 30, null );
		rectArray.add( this.rect );


		g2d.setColor( Color.blue );
		y = 434;
		x = 403;
		this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
		g2d.drawImage( this.finalBoxImg, x, y, 30, 30, null );
		finishArray.add( this.rect );


		x = 434;
		y = 186;
		this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
		rectArray.add( this.rect );

		g2d.drawImage( this.boxImg, x, y, 30, 30, null );

		y = 217;
		this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
		rectArray.add( this.rect );

		g2d.drawImage( this.boxImg, x, y, 30, 30, null );

		x = 527;
		y = 155;
		this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
		rectArray.add( this.rect );

		g2d.drawImage( this.boxImg, x, y, 30, 30, null );

		y = 310;
		x = 434;
		rectArray.add( new Rectangle2D.Double( x, y, 30.0D, 30.0D ) );
		g2d.drawImage( this.boxImg, x, y, 30, 30, null );

		for( x = 496; x <= 589; x += 31 )
		{
			this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
			rectArray.add( this.rect );

			g2d.drawImage( this.boxImg, x, y, 30, 30, null );
		}

		x = 403;
		y = 248;
		this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
		rectArray.add( this.rect );

		g2d.drawImage( this.boxImg, x, y, 30, 30, null );

		y = 62;
		for( x = 310; x <= 372; x += 31 )
		{
			rectArray.add( new Rectangle2D.Double( x, y, 30.0D, 30.0D ) );
			g2d.drawImage( this.boxImg, x, y, 30, 30, null );
		}

		x = 124;
		for( y = 93; y <= 155; y += 31 )
		{
			rectArray.add( new Rectangle2D.Double( x, y, 30.0D, 30.0D ) );
			g2d.drawImage( this.boxImg, x, y, 30, 30, null );
		}

		x = 93;
		for( y = 248; y <= 310; y += 31 )
		{
			rectArray.add( new Rectangle2D.Double( x, y, 30.0D, 30.0D ) );
			g2d.drawImage( this.boxImg, x, y, 30, 30, null );
		}

		y = 527;
		for( x = 155; x <= 248; x += 31 )
		{
			rectArray.add( new Rectangle2D.Double( x, y, 30.0D, 30.0D ) );
			g2d.drawImage( this.boxImg, x, y, 30, 30, null );
		}

		for( x = 341; x <= 403; x += 31 )
		{
			rectArray.add( new Rectangle2D.Double( x, y, 30.0D, 30.0D ) );
			g2d.drawImage( this.boxImg, x, y, 30, 30, null );
		}

		y = 496;
		x = 496;
		rectArray.add( new Rectangle2D.Double( x, y, 30.0D, 30.0D ) );
		g2d.drawImage( this.boxImg, x, y, 30, 30, null );

		y = 217;
		x = 465;
		rectArray.add( new Rectangle2D.Double( x, y, 30.0D, 30.0D ) );
		g2d.drawImage( this.boxImg, x, y, 30, 30, null );
	}

	private void drawLevel2( Graphics2D g2d )
	{
		rectArray.clear();
		finishArray.clear();
		borderArray.clear();
		blastArray.clear();


		int y = 0, x = 0;
		for( x = 0; x < Framework.frameWidth; x += 31 )
		{
			this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
			borderArray.add( this.rect );
			g2d.draw( this.rect );
		}

		x = 0;
		for( y = 0; y < Framework.frameHeight; y += 31 )
		{
			this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
			borderArray.add( this.rect );
			g2d.draw( this.rect );
		}

		x = 868;
		for( y = 0; y < Framework.frameHeight; y += 31 )
		{
			this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
			borderArray.add( this.rect );
			g2d.draw( this.rect );
		}

		y = 558;
		for( x = 0; x < Framework.frameWidth; x += 31 )
		{
			this.rect = new Rectangle2D.Double( x, y, 30.0D, 30.0D );
			borderArray.add( this.rect );
			g2d.draw( this.rect );
		}
		g2d.setFont( new Font( "Arial", 1, 26 ) );

		g2d.drawString( "Level 2 Coming SOON!", 360, 300 );
	}
}