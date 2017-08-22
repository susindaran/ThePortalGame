package com.betadevels;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Key
{
	private BufferedImage keyImg;
	public int x;
	public int y;
	public int i;
	public static boolean landed;
	public Rectangle2D rect;

	public Key()
	{
		initialize();
		load_content();
	}

	public void initialize()
	{
		this.x = 310;
		this.y = 217;
	}

	public void load_content()
	{
		URL keyImgUrl = getClass().getClassLoader().getResource( "key_player.png" );
		try
		{
			this.keyImg = ImageIO.read( keyImgUrl );
		}
		catch( IOException ex )
		{
			Logger.getLogger( Key.class.getName() ).log( Level.SEVERE, null, ex );
		}
	}

	public void ResetPlayer()
	{
		landed = false;
		Maze.touched = false;
		switch( Maze.level )
		{
			case 1:
				this.x = 310;
				this.y = 217;
				break;
			case 2:
				this.x = 434;
				this.y = 248;
		}


		Canvas.setIsAKeySet( false );
		Canvas.setIsSKeySet( false );
		Canvas.setIsDKeySet( false );
		Canvas.setIsWKeySet( false );
	}

	public void Draw( Graphics2D g2d )
	{
		g2d.setColor( Color.red );


		g2d.drawImage( this.keyImg, this.x, this.y, 30, 30, null );
	}

	public void Update()
	{
		boolean collide = false;
		if( Canvas.getIsAKeySet() )
		{
			this.rect = new Rectangle2D.Double( this.x, this.y, 30.0D, 30.0D );
			for( this.i = 0; this.i < Maze.rect_array.size(); this.i += 1 )
			{
				if( ( ( Rectangle2D ) Maze.rect_array.get( this.i ) ).getY() == this.y )
				{
					if( this.rect.intersects( ( Rectangle2D ) Maze.rect_array.get( this.i ) ) )
					{
						this.x = ( ( int ) ( ( ( Rectangle2D ) Maze.rect_array.get( this.i ) ).getX() + 31.0D ) );
						Canvas.setIsAKeySet( false );
						collide = true;
					}
				}
			}
			if( !collide )
			{
				for( this.i = 0; this.i < Maze.finish_array.size(); this.i += 1 )
				{
					if( this.rect.intersects( ( Rectangle2D ) Maze.finish_array.get( this.i ) ) )
					{
						Canvas.setIsAKeySet( false );
						collide = true;
						landed = true;
						this.x = ( ( int ) ( ( Rectangle2D ) Maze.finish_array.get( this.i ) ).getX() );
						Framework.gameState = Framework.GameState.GAMEOVER;
					}
				}
				for( this.i = 0; this.i < Maze.border_array.size(); this.i += 1 )
				{
					if( this.rect.intersects( ( Rectangle2D ) Maze.border_array.get( this.i ) ) )
					{
						Canvas.setIsAKeySet( false );
						collide = true;
						landed = false;
						Framework.gameState = Framework.GameState.GAMEOVER;
					}
				}
			}
			if( !collide )
			{

				this.x -= 2;
			}
		}
		else if( Canvas.getIsSKeySet() )
		{
			this.rect = new Rectangle2D.Double( this.x, this.y, 30.0D, 30.0D );
			for( this.i = 0; this.i < Maze.rect_array.size(); this.i += 1 )
			{
				if( ( ( Rectangle2D ) Maze.rect_array.get( this.i ) ).getX() == this.x )
				{
					if( this.rect.intersects( ( Rectangle2D ) Maze.rect_array.get( this.i ) ) )
					{

						this.y = ( ( int ) ( ( ( Rectangle2D ) Maze.rect_array.get( this.i ) ).getY() - 31.0D ) );
						Canvas.setIsSKeySet( false );
						collide = true;
					}
				}
			}

			if( !collide )
			{
				for( this.i = 0; this.i < Maze.finish_array.size(); this.i += 1 )
				{
					if( this.rect.intersects( ( Rectangle2D ) Maze.finish_array.get( this.i ) ) )
					{
						Canvas.setIsSKeySet( false );
						collide = true;
						landed = true;

						this.y = ( ( int ) ( ( Rectangle2D ) Maze.finish_array.get( this.i ) ).getY() );
						Framework.gameState = Framework.GameState.GAMEOVER;
					}
				}
				for( this.i = 0; this.i < Maze.border_array.size(); this.i += 1 )
				{
					if( this.rect.intersects( ( Rectangle2D ) Maze.border_array.get( this.i ) ) )
					{
						Canvas.setIsSKeySet( false );
						collide = true;
						landed = false;
						Framework.gameState = Framework.GameState.GAMEOVER;
					}
				}
			}
			if( !collide )
			{
				this.y += 2;
			}
		}
		else if( Canvas.getIsDKeySet() )
		{
			this.rect = new Rectangle2D.Double( this.x, this.y, 30.0D, 30.0D );
			for( this.i = 0; this.i < Maze.rect_array.size(); this.i += 1 )
			{
				if( ( ( Rectangle2D ) Maze.rect_array.get( this.i ) ).getY() == this.y )
				{
					if( this.rect.intersects( ( Rectangle2D ) Maze.rect_array.get( this.i ) ) )
					{

						this.x = ( ( int ) ( ( ( Rectangle2D ) Maze.rect_array.get( this.i ) ).getX() - 31.0D ) );
						Canvas.setIsDKeySet( false );
						collide = true;
					}
				}
			}
			if( !collide )
			{
				for( this.i = 0; this.i < Maze.finish_array.size(); this.i += 1 )
				{
					if( this.rect.intersects( ( Rectangle2D ) Maze.finish_array.get( this.i ) ) )
					{
						Canvas.setIsDKeySet( false );
						collide = true;
						landed = true;
						this.x = ( ( int ) ( ( Rectangle2D ) Maze.finish_array.get( this.i ) ).getX() );
						Framework.gameState = Framework.GameState.GAMEOVER;
					}
				}
				for( this.i = 0; this.i < Maze.border_array.size(); this.i += 1 )
				{
					if( this.rect.intersects( ( Rectangle2D ) Maze.border_array.get( this.i ) ) )
					{
						Canvas.setIsDKeySet( false );
						collide = true;
						landed = false;
						Framework.gameState = Framework.GameState.GAMEOVER;
					}
				}
			}


			if( !collide )
			{
				this.x += 2;
			}
		}
		else if( Canvas.getIsWKeySet() )
		{
			this.rect = new Rectangle2D.Double( this.x, this.y, 30.0D, 30.0D );
			for( this.i = 0; this.i < Maze.rect_array.size(); this.i += 1 )
			{
				if( ( ( Rectangle2D ) Maze.rect_array.get( this.i ) ).getX() == this.x )
				{
					if( this.rect.intersects( ( Rectangle2D ) Maze.rect_array.get( this.i ) ) )
					{

						this.y = ( ( int ) ( ( ( Rectangle2D ) Maze.rect_array.get( this.i ) ).getY() + 31.0D ) );
						Canvas.setIsWKeySet( false );
						collide = true;
					}
				}
			}
			if( !collide )
			{
				for( this.i = 0; this.i < Maze.finish_array.size(); this.i += 1 )
				{
					if( this.rect.intersects( ( Rectangle2D ) Maze.finish_array.get( this.i ) ) )
					{
						Canvas.setIsWKeySet( false );
						collide = true;
						landed = true;
						this.y = ( ( int ) ( ( Rectangle2D ) Maze.finish_array.get( this.i ) ).getY() );
						Framework.gameState = Framework.GameState.GAMEOVER;
					}
				}
				for( this.i = 0; this.i < Maze.border_array.size(); this.i += 1 )
				{
					if( this.rect.intersects( ( Rectangle2D ) Maze.border_array.get( this.i ) ) )
					{
						Canvas.setIsWKeySet( false );
						collide = true;
						landed = false;
						Framework.gameState = Framework.GameState.GAMEOVER;
					}
				}
			}
			if( !collide )
			{
				this.y -= 2;
			}
		}
	}
}