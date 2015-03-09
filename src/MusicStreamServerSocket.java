import sun.audio.AudioStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class MusicStreamServerSocket {
	int port_number;
	ServerSocket server_socket;
	Socket client_socket;
	PrintWriter output;
	BufferedReader input;
    AudioStream audioOut;
	
	// Sets up server on a specific port number
	public MusicStreamServerSocket( int port ) throws IOException {
		port_number = port;
		server_socket = new ServerSocket( port_number );	
	}
	
	// Waits for client connection and stores music file name
	public void listen() throws IOException {
		client_socket = server_socket.accept();
		
		output = new PrintWriter( client_socket.getOutputStream(), true );
		input = new BufferedReader( new InputStreamReader(client_socket.getInputStream()) );
		
		output.println("Connected to server...");
		
		String musicFile = input.readLine();
		System.out.println( "Requested music file from client: " + musicFile );
		
		output.println("Playing music file " + musicFile);


		
		client_socket.close();
	}
}