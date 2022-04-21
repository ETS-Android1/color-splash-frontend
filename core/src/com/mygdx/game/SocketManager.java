
public class SocketManager {
    private Socket socket; 
    public SocketManager(){
        try {
			socket = IO.socket("https://color-splash.herokuapp.com");
            socket.connect();
            
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

    }
}