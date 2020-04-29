package com.nature.controller.wx;

import java.util.Hashtable;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class WebSocketResin extends HttpServlet {

private static Hashtable map_socket = new Hashtable(50);

 

	public void service(HttpServletRequest req, HttpServletResponse res)

	{

//		String protocol = req.getHeader("Sec-WebSocket-Protocol");
//
//		WebSocketListener listener;
//
//		listener = new MyListener();
//
//		// res.setHeader("Sec-WebSocket-Protocol", "my-protocol");
//
//		WebSocketServletRequest wsReq = (WebSocketServletRequest) req;
//
//		WebSocketContext webSocketContext = wsReq.startWebSocket(listener);
//
//		map_socket.put(webSocketContext.hashCode(), webSocketContext);

	}

//	public static HashtablegetSockList()
//
//	{
//
//		return map_socket;
//
//	}
// 

}
