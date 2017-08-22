package com.betadevels;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Window extends JFrame
{
	private Window()
	{
		setTitle( "The Portal" );
		setSize( 906, 618 );
		setLocationRelativeTo( null );
		setResizable( false );
		setDefaultCloseOperation( 3 );
		setContentPane( new Framework() );
		setVisible( true );
	}

	public static void main( String[] args )
	{
		SwingUtilities.invokeLater( Window::new );
	}
}