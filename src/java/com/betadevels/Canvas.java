package com.betadevels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class Canvas extends JPanel implements KeyListener, MouseListener
{
	private static boolean[] keyboardState = new boolean[ 'ȍ' ];
	private static boolean[] mouseState = new boolean[ 3 ];
	private static boolean isAKeySet;
	private static boolean isWKeySet;
	private static boolean isDKeySet;
	private static boolean isSKeySet;

	public Canvas()
	{
		setDoubleBuffered( true );
		setFocusable( true );
		setBackground( Color.black );
		addKeyListener( this );
		addMouseListener( this );
	}

	public abstract void draw( Graphics2D paramGraphics2D );

	public void paintComponent( Graphics g )
	{
		Graphics2D g2d = ( Graphics2D ) g;
		super.paintComponent( g2d );
		draw( g2d );
	}


	public static boolean keyboardKeyState( int key )
	{
		return keyboardState[ key ];
	}

	public void keyPressed( KeyEvent e )
	{
		keyboardState[ e.getKeyCode() ] = true;
		if( ( ( e.getKeyChar() == 'a' ) || ( e
				.getKeyCode() == 37 ) ) && ( !isWKeySet ) && ( !isSKeySet ) && ( !isDKeySet ) )
		{
			isAKeySet = true;
		}
		else if( ( ( e.getKeyChar() == 's' ) || ( e
				.getKeyCode() == 40 ) ) && ( !isAKeySet ) && ( !isWKeySet ) && ( !isDKeySet ) )
		{
			isSKeySet = true;
		}
		else if( ( ( e.getKeyChar() == 'w' ) || ( e
				.getKeyCode() == 38 ) ) && ( !isAKeySet ) && ( !isSKeySet ) && ( !isDKeySet ) )
		{
			isWKeySet = true;
		}
		else if( ( ( e.getKeyChar() == 'd' ) || ( e
				.getKeyCode() == 39 ) ) && ( !isAKeySet ) && ( !isWKeySet ) && ( !isSKeySet ) )
		{
			isDKeySet = true;
		}
	}

	public static boolean getIsAKeySet()
	{
		return isAKeySet;
	}

	public static void setIsAKeySet( boolean val )
	{
		isAKeySet = val;
	}

	public static boolean getIsWKeySet()
	{
		return isWKeySet;
	}

	public static void setIsWKeySet( boolean val )
	{
		isWKeySet = val;
	}

	public static boolean getIsDKeySet()
	{
		return isDKeySet;
	}

	public static void setIsDKeySet( boolean val )
	{
		isDKeySet = val;
	}

	public static boolean getIsSKeySet()
	{
		return isSKeySet;
	}

	public static void setIsSKeySet( boolean val )
	{
		isSKeySet = val;
	}

	public void keyReleased( KeyEvent e )
	{
		keyboardState[ e.getKeyCode() ] = false;
		keyReleasedFramework( e );
	}

	public void keyTyped( KeyEvent e )
	{
	}

	public abstract void keyReleasedFramework( KeyEvent paramKeyEvent );

	public static boolean mouseButtonState( int button )
	{
		return mouseState[ ( button - 1 ) ];
	}

	private void setMouseKeyStatus( MouseEvent e, boolean status )
	{
		if( e.getButton() == 1 )
		{
			mouseState[ 0 ] = status;
		}
		else if( e.getButton() == 2 )
		{
			mouseState[ 1 ] = status;
		}
		else if( e.getButton() == 3 )
		{
			mouseState[ 2 ] = status;
		}
	}

	public void mousePressed( MouseEvent e )
	{
		setMouseKeyStatus( e, true );
	}

	public void mouseReleased( MouseEvent e )
	{
		setMouseKeyStatus( e, false );
	}

	public void mouseClicked( MouseEvent e )
	{
	}

	public void mouseEntered( MouseEvent e )
	{
	}

	public void mouseExited( MouseEvent e )
	{
	}
}