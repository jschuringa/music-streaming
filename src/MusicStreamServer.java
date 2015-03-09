
public class MusicStreamServer {

	public static void main(String[] args) {
		if ( args.length != 1 )
		{
			System.out.println( "Usage: MusicStreamServer <port number>" );
			return;
		}
		int port_number = Integer.valueOf( args[0] );
		
		try {
			MusicStreamServerSocket musicServer = new MusicStreamServerSocket( port_number );
			System.out.println( "Listening on port " + Integer.toString( port_number ) );
			
			//Currently listens for any requests from the client
			while(true){
				musicServer.listen();
			}
		} catch ( Exception e ) {
			System.out.println( e.getMessage() );
		}
	}
}