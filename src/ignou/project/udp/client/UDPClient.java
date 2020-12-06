package ignou.project.udp.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDPClient {
  private static DatagramSocket udpSocket;
  
  private InetAddress serverAddress;
  
  private int port;
  
  private Scanner scanner;
  
  public UDPClient(int port, int destinationPort) throws UnknownHostException, SocketException {
    this.serverAddress = InetAddress.getLocalHost();
    this.port = destinationPort;
    udpSocket = new DatagramSocket(port);
    this.scanner = new Scanner(System.in);
  }
  
  private void start() throws Exception {
    for (int i = 1; i < 3; i++) {
      System.out.print("Enter message " + i + " : ");
      String in = this.scanner.nextLine();
      DatagramPacket packet = new DatagramPacket(in.getBytes(), (in.getBytes()).length, 
          this.serverAddress, this.port);
      udpSocket.send(packet);
    } 
    byte[] buf = new byte[256];
    DatagramPacket packet2 = new DatagramPacket(buf, buf.length);
    udpSocket.receive(packet2);
    String message1 = (new String(packet2.getData())).trim();
    System.out.println("Message from " + packet2.getAddress().getHostAddress() + ":" + 
        packet2.getPort() + 
        " : " + message1);
  }
  
  public static void main(String[] args) throws Exception {
    UDPClient client = new UDPClient(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    System.out.println(" -- Running UDP Client at " + InetAddress.getLocalHost() + ":" + 
        udpSocket.getLocalPort() + " -- ");
    while (true)
      client.start(); 
  }
}
