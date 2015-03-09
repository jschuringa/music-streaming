import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


public class MusicStreamClientSocket {
	int port_number;
    InetAddress hostname;
    Socket client_socket;
    PrintWriter output;
    BufferedReader input;
    String musicFileName;
    AudioStream audioIn;
    AudioPlayer player;
    
    public MusicStreamClientSocket( int port ) throws IOException {
        port_number = port;
        hostname = InetAddress.getLoopbackAddress();
        client_socket = new Socket( hostname, port );
        output = new PrintWriter( client_socket.getOutputStream(), true );
		input = new BufferedReader( new InputStreamReader(client_socket.getInputStream()) );
        audioIn = new AudioStream( client_socket.getInputStream());
    }
    
    // Sends music file name to server and receives confirmation (playing) for now
    public void play( String fileName ) throws IOException {
    	musicFileName = fileName;
    	output.println(musicFileName);
    	String response = input.readLine();
    	System.out.println(response);
    	System.out.println( "Attempting to play " + musicFileName );


    	
    	String confirmation = input.readLine();
    	System.out.println(confirmation);

        player.start(audioIn);
    }
    
    public void closeSocket() throws IOException {
        client_socket.close();
    }
}