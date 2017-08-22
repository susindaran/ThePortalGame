package com.betadevels;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class LandingArea
{
	public int x;
	public int y;
	private BufferedImage landingAreaImg;
	public int landingAreaImgWidth;

	public LandingArea()
	{
		initialize();
		loadContent();
	}

	private void initialize()
	{
		this.x = ( ( int ) ( Framework.frameWidth * 0.46D ) );

		this.y = ( ( int ) ( Framework.frameHeight * 0.88D ) );
	}

	private void loadContent()
	{
		try
		{
			URL landingAreaImgUrl = getClass().getResource( "landing_area.png" );
			this.landingAreaImg = ImageIO.read( landingAreaImgUrl );
			this.landingAreaImgWidth = this.landingAreaImg.getWidth();
		}
		catch( IOException ex )
		{
			Logger.getLogger( LandingArea.class.getName() ).log( Level.SEVERE, null, ex );
		}
	}

	public void draw( Graphics2D g2d )
	{
		g2d.drawImage( this.landingAreaImg, this.x, this.y, null );
	}
}