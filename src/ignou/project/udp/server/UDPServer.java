package ignou.project.udp.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {
  private DatagramSocket udpSocket;
  
  private int port;
  
  public UDPServer(int port) throws SocketException {
    this.port = port;
    this.udpSocket = new DatagramSocket(this.port);
  }
  
  private void listen() throws Exception {
    System.out.println(" -- Running Server at " + InetAddress.getLocalHost() + ":" + 
        this.port + " -- ");
    StringBuilder finalMsg = new StringBuilder();
    while (true) {
      byte[] buf = new byte[1024];
      DatagramPacket packet = new DatagramPacket(buf, buf.length);
      for (int i = 2; i > 0; i--) {
        this.udpSocket.receive(packet);
        String message1 = new String(packet.getData(), packet.getOffset(), packet.getLength());
        finalMsg.append(message1).append(" ");
        System.out.println("Message from " + packet.getAddress().getHostAddress() + ":" + 
            packet.getPort() + 
            " : " + message1);
      } 
      sendReply(finalMsg.toString(), packet);
      finalMsg.setLength(0);
    } 
  }
  
  private void sendReply(String message, DatagramPacket dp) throws Exception {
    System.out.println("Sending Message to " + dp.getAddress().getHostAddress() + ":" + 
        dp.getPort() + 
        " : " + message);
    DatagramPacket packet = new DatagramPacket(message.getBytes(), (message.getBytes()).length, 
        InetAddress.getLocalHost(), dp.getPort());
    this.udpSocket.send(packet);
  }
  
  public static void main(String[] args) throws Exception {
    UDPServer server = new UDPServer(Integer.parseInt(args[0]));
    server.listen();
  }
}

