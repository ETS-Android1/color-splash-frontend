
public class SocketManager {
    private Socket socket; 
    public SocketManager(){
        try {
			socket = IO.socket("https://color-splash.herokuapp.com");
            socket.connect();
            //socket.on(EventsConstants.error, ...);
            //socket.on(EventsConstants.gameCreated, ...);
            //socket.on(EventsConstants.gameInfo, ...);
            //socket.on(EventsConstants.displayColors, ...);
            //socket.on(EventsConstants.roundStarted, ...);
            //socket.on(EventsConstants.timesUp, ...);
            //socket.on(EventsConstants.endRound, ...);
            //socket.on(EventsConstants.gameFinished, ...);
            //socket.on(EventsConstants.gameDeleted, ...);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

    }
}