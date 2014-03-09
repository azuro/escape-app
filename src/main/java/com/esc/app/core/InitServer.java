package com.esc.app.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class InitServer implements Runnable{
	Socket c;

	InitServer(Socket s){
		this.c = s;
	}
	
	public void run() {
		try {
			BufferedReader read = new BufferedReader(new InputStreamReader(c.getInputStream()));
			String line;
			while((line = read.readLine())!=""){
				if(line==null)
					break;
				System.out.println(line);
			}
			
			OutputStream o = c.getOutputStream();
			o.write(("HTTP/1.1 200 OK\n"+
					"Content-Type:text/html\n"+
					"\n"+
					"<html><body><h1>Hello, World</h1></body></html>").getBytes());
			o.close();
		} catch (IOException e) {
		}
	}
	
	public static void main(String[] args){
		try {
			ServerSocket ss = new ServerSocket(1234);
			while(true){
				new Thread(new InitServer(ss.accept())).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
