package lab7;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Scanner;

import soap.TCPSender;
import soap.TCPSenderServiceLocator;

public class Main {
	
	Boolean lock = new Boolean(true);
	
	TCPSender tcp;
	
	String name;
	String port;
	
	Thread server;
	
	boolean work = true;

	public static void main(String[] args) {
		Scanner in = null;
		try {
			TCPSenderServiceLocator locator = new TCPSenderServiceLocator();
			TCPSender tcp = locator.getTCPSender();
			in = new Scanner(System.in);
			System.out.println("Proszę podać nazwę klienta");
			String name = in.nextLine();
			System.out.println("Proszę podać port klienta");
			String port = in.nextLine();
			new Main(tcp, name, port);
		} catch (Exception e) {
			return;
		} finally {
			if(in != null)
				in.close();
		}
		
	}
	
	Main(TCPSender tcp, String name, String port) throws NumberFormatException, RemoteException, Exception{
		try {
			this.name = name;
			if(name.equals("brd"))
				throw new Exception();
			this.port = port;
			this.tcp = tcp;
			Integer.parseInt(port);
			if(!tcp.register(port))
				throw new Exception();
			new Thread(() -> serverSocket()).start();
			sendData();
		} catch (Exception e) {
			System.out.println("Couldn't start client");
			tcp.remove(port);
		}
		
	}
	
	private void serverSocket() {

		Socket socket = null;
		ServerSocket serverSocket = null;
		DataInputStream dis = null;
		DataOutputStream dos  = null;
		try {
			serverSocket = new ServerSocket(Integer.parseInt(port));
			while(work) {
				socket = serverSocket.accept();
				dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
				
				String print;
				String read = dis.readUTF();
				if(read.equals("quit")) {
					work = false;
					socket.close();
					serverSocket.close();
					System.out.println("Closed");
					break;
				}
				String[] sRead = read.split(";");
				
				if(sRead[0].equals(name)) {
					if(sRead.length == 3) {
						synchronized(lock) {
							System.out.println("No receiver found");
						}
					} else {
						synchronized(lock) {
							System.out.println("Successful sending");
						}
					}
					continue;
				}
				
				if(sRead[1].equals(name) || sRead[1].equals("brd")) {
					if(sRead.length == 3) {
						read += ";true";
					}
					print = sRead[0] + ": " + sRead[2]; 
					synchronized(lock) {
						System.out.println(print);
					}
				} else {
					synchronized(lock) {
						System.out.println("Not for me - passing forward");
					}
				}
				
				socket.close();
				
				socket = new Socket("127.0.0.1", Integer.parseInt(tcp.getNextStep(port)));
				dos = new DataOutputStream(socket.getOutputStream());
				dos.writeUTF(read);
				
				socket.close();
			}
			
			serverSocket.close();
			tcp.remove(port);
		} catch (Exception e) {
			work = false;
			System.out.println("Fatal error");
			try {
				tcp.remove(port);
				socket.close();
				serverSocket.close();
			} catch (IOException e1) {
				return;
			}
			return;
		}
	}
	
	private void sendData() {
		Socket socket = null;
		Scanner in = null;
		DataOutputStream dos  = null;
		try {
			in = new Scanner(System.in);
			while(work) {
				String send;
				String line = in.nextLine();
				
				if(line.equals("quit")) {
					socket = new Socket("127.0.0.1", Integer.parseInt(port));
					dos = new DataOutputStream(socket.getOutputStream());
					dos.writeUTF("quit");
					socket.close();
					work = false;
					break;
				}
				
				if(line.equals("") || line.equals(null)) {
					continue;
				}
				
				String[] sLine = line.split(";");
				
				if(Array.getLength(sLine) == 1) {
					send = name + ";brd;" + sLine[0];
				}
				else {
					if(sLine[1].equals("") || sLine[1].equals(null)) {
						continue;
					}
					send = name + ";" + sLine[0] + ";" + sLine[1];
				}
				
				socket = new Socket("127.0.0.1", Integer.parseInt(tcp.getNextStep(port)));
				dos = new DataOutputStream(socket.getOutputStream());
				dos.writeUTF(send);
				
				socket.close();
			}
			
			in.close();
		} catch (Exception e) {
			work = false;
			try {
				System.out.println(tcp.getNextStep(port));
				e.printStackTrace();
				socket.close();
				in.close();
				dos.close();
			} catch (Exception e1) {
				return;
			}
			return;
		}
	}

}
