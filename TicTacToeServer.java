import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class TicTacToeServer
{
	Drawing draw = new Drawing();
	int x = -1, y = -1;
	int[][] board = {{0,0,0},{0,0,0},{0,0,0}};
	boolean next = true;
	PrintStream out = null;
	
	public TicTacToeServer() throws IOException
	{
		JFrame frame = new JFrame("TicTacToe Server");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(draw);
		frame.getContentPane().setBackground(Color.white);
		draw.addMouseListener(new MouseListen());
		frame.setSize(600,600);
		frame.setVisible(true);
		playGame();
	}
	
	class MouseListen extends MouseAdapter
	{
		public void mouseReleased(MouseEvent e)
		{
			if (next)
			{
				x = e.getX() / 200;
				y = e.getY() / 200;
				if (board[x][y] == 0)
				{
					next = false;
					board[x][y] = 1;
					out.println(x);
					out.println(y);
					out.flush();
					draw.repaint();
				}
			}
		}
	}
			
	class Drawing extends JComponent
	{
		public void paint(Graphics g)
		{
			Font f = new Font("Serif", Font.BOLD, 48);
			g.setFont(f);
			g.drawLine(200,0,200,600);
			g.drawLine(400,0,400,600);
			g.drawLine(0,200,600,200);
			g.drawLine(0,400,600,400);
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++)
					if (board[i][j] == 1)
						g.drawString("X", i * 200 + 80, j * 200 + 100);
					else if (board[i][j] == 2)
						g.drawString("O", i * 200 + 80, j * 200 + 100);
		}
	}	
	
	public static void main(String[] args) throws IOException
	{
		new TicTacToeServer();
	}
	
	public void playGame() throws IOException
	{
		System.out.println("Starting server");
		Socket sock;
		ServerSocket servsock = new ServerSocket(4444, 0);
		String s="";
		while (true)
		{
			sock = servsock.accept();
			System.out.println("sock = " + sock);
			out = new PrintStream(sock.getOutputStream());
			BufferedReader in= new BufferedReader(new InputStreamReader(sock.getInputStream()));
			while (true)
			{
				if (in.ready())
				{	
					int xcoord = Integer.parseInt(in.readLine());
					int ycoord = Integer.parseInt(in.readLine());
					System.out.println(xcoord + ", " + ycoord);
					if (board[xcoord][ycoord] == 0)
					{
						board[xcoord][ycoord] = 2;
						next = true;
						draw.repaint();
					}
				}
			}
/*			sock.close();
			next=true;
			s = "";*/
		}
	}
}
