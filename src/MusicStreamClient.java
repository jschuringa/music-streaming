import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MusicStreamClient {

	public static void main(String[] args) {
		if ( args.length != 1 )
        {
            System.out.println( "Usage: MusicStreamClient <port number>" );
            System.out.println( "   Music file names are read from standard input" );
            System.out.println( "   Type one of the following commands after entering a music file name" );
            System.out.println( "   [PLAY] - play music file" );
            System.out.println( "   [PAUSE] - pause current music file" );
            System.out.println( "   [STOP] - stop playing music file" );
            System.out.println( "   [EXIT] - exit music service" );
            return;
        }
		
		int port = Integer.valueOf( args[0] );
		String request;
		BufferedReader reader= new BufferedReader(
                new InputStreamReader(System.in) );
		
		try{
			while(!( request = reader.readLine()).equals("EXIT")) {						//Checks for [EXIT] command
				MusicStreamClientSocket client = new MusicStreamClientSocket( port );
				System.out.println(request);
				client.play( request );
			}
			
			// If user types "EXIT" the client disconnects from music Server
			MusicStreamClientSocket client = new MusicStreamClientSocket( port );
			client.closeSocket();
			System.out.println("Exiting music server.");
		} catch ( IOException e ) {
            e.printStackTrace();
        }
	}
}