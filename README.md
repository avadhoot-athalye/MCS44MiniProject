# MCS44 - Mini Project

## Steps to get the project running in the system
1.  Switch to master branch if not already on it
2.  Download the zip file or use git clone
3.  Open the project in eclipse
4.  First open UDPServer.java and select "Run as" -> "Run Configurations"
5.  Under Java application section select UDPServer java file and in argument tab enter port number on which UDPServer needs to run eg "44055"
6.  Click on Run
7.  Open UDPClient.java and select "Run as" -> "Run Configurations"
8.  Under Java application section select UDPClient.java and in argument tab enter 2 port numbers with space between.
9.  First port number is of UDPClient to run on and second port number is where UDPServer is running eg "44033 44055"
10. Click on Run
11. Send messages from UDPClient. UDPServer will receive messages and send resultant string.
