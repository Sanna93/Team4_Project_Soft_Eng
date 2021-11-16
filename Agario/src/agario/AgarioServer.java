package agario;
import java.io.IOException;
import java.util.*;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class AgarioServer extends AbstractServer
{
	public HashMap<Long, Player> connections = new HashMap<>();
	Timer timer;
	
	public AgarioServer() 
	{
		super(80);	
	}
	
	
	
	public void serverStarted()
	{
		System.out.println("Server Started");
	}
	public void clientConnected(ConnectionToClient con)
	{
		System.out.println("Client Connected");
		try {
			con.sendToClient(con.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clientDisconnected(ConnectionToClient con)
	{
		connections.remove(con.getId());		
		System.out.println( getConnectionFromId(con.getId()).isAlive() + " Disconnected Client");
	}
	
	@Override
	protected void handleMessageFromClient(Object arg0, ConnectionToClient arg1) 
	{		
		
		if(arg0 instanceof Player)
		{
			connections.put(arg1.getId(),(Player)arg0);		
			//System.out.println(connections.size() + " size");
		}	
		else if(arg0 instanceof String)
		{
			if(arg0.equals("EndMe"))
			{
				try {
					arg1.close();
					connections.remove(arg1.getId());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else if(((String)arg0).contains("Kill"))
			{
				Long id = Long.parseLong(((String)arg0).split(",")[1]);
				ConnectionToClient c = getConnectionFromId(id);
				
				try {
					c.sendToClient("Die");
					connections.remove(id);
					//System.out.println("Killing " + c.getId());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
			//System.out.println((String)arg0 + " String");
		}
		else
		{
			System.out.println(arg0);
		}
		
	}
	
	ConnectionToClient getConnectionFromId(Long id)
	{
		for(Object i : getClientConnections())
		{
			if(((ConnectionToClient)i).getId() == id)
			{
				return (ConnectionToClient) i;
			}
		}
		return null;
	}
}