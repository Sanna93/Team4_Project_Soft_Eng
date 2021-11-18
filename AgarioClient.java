package agario;

import java.io.IOException;
import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;

import ocsf.client.AbstractClient;

public class AgarioClient extends AbstractClient
{
	
	Lambda playerCallBack;
	Lambda setID;
	Long id;
	//public Player player;
	
	public AgarioClient(String host, int port) 
	{
		super(host, port);
	}
	
	public void SetPlayersCallBack(Lambda callBack)
	{
		playerCallBack = callBack;
		
	}
	
	public void SetIDCallBack(Lambda callBack)
	{
		
	}
	@Override
	
	protected void handleMessageFromServer(Object arg0) 
	{
		
		//System.out.println("7) " + (arg0 instanceof Player[]));		
		
		if(arg0 instanceof Player[])
		{
			playerCallBack.log((Player[])arg0);
		}
		else if(arg0 instanceof Long)
		{
			id = (Long)arg0;
			
		}
		else if(arg0 instanceof HashMap)
		{
			/*HashMap dic = ((HashMap)arg0);
			if(dic.containsKey("grow"))
			{
				
			}*/
		}
		else if(arg0 instanceof String)
		{
			String str = (String)arg0;
			System.out.println("Im Melting");
			if(str.equals("Die"))
			{
				try {
					this.closeConnection();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		/*
		 if(arg0 == (Player[])arg0)
		{
			
			System.out.println("Gotcha");
			if(playerCallBack != null)
			{
				playerCallBack.log(arg0);
			}
			
		}
		else
		{
			System.out.println("Dougie " + arg0.getClass());
		}
		*/
	}
	
	Long getId()
	{
		return id;
	}
	
	protected void connectionException()
	{
		System.out.println(" Exception");
	}
	protected void connectionClosed()
	{
		System.exit(0);
	}
	
	protected void connectionEstablished()
	{
		//Player = new Player();
	    System.out.println("Client Connected");
	}
}