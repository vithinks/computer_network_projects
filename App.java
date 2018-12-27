
package packet_capture.packet_capture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapHandle.TimestampPrecision;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.DnsPacket;
import org.pcap4j.packet.IpV4InternetTimestampOption;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.UdpPacket;
import org.pcap4j.packet.UdpPacket.UdpHeader;
import org.pcap4j.packet.namednumber.SctpPort;
import org.pcap4j.packet.namednumber.TcpPort;
import org.pcap4j.packet.namednumber.UdpPort;
import org.pcap4j.util.NifSelector;

import com.opencsv.CSVWriter;

import org.pcap4j.core.Pcaps;
import org.pcap4j.core.PcapStat;

public class App 
{




	public static void main(String[] args)throws PcapNativeException, NotOpenException {
	PcapHandle handle;
	try {
		
	    handle = Pcaps.openOffline("17.pcap", TimestampPrecision.MICRO);
	} catch (PcapNativeException e) {
	    handle = Pcaps.openOffline("17.pcap");
	}
	
	PacketListener listener = new PacketListener() {
		

		public void gotPacket(Packet packet) {
			
			Inet4Address ipd = packet.get(IpV4Packet.class).getHeader().getDstAddr();
			Inet4Address ips = packet.get(IpV4Packet.class).getHeader().getSrcAddr();
			TcpPort tcpsport = packet.get(TcpPacket.class).getHeader().getSrcPort();
			TcpPort tcpdport = packet.get(TcpPacket.class).getHeader().getDstPort();
			UdpPort udpport = UdpPort.getInstance((short) 60000);
			//UdpPort udpsport = packet.get(UdpPacket.class).getHeader().getSrcPort();
			//SctpPort httpport= SctpPort.HTTP;
			//DnsPort udpsport = packet.get(DnsPacket.class).getHeader().getSrcPort();
			String tcpd= String.valueOf(tcpdport);
			String tcps= String.valueOf(tcpsport);
			String str= String.valueOf(ipd);
			String strs= String.valueOf(ips);
			String udp= String.valueOf(udpport);
			//String ucps= String.valueOf(udpsport);
			//System.out.println(tcpd);
			//System.out.println(udpport);
			//System.out.println(PcapHandle.TimestampPrecision.values());
			
			//Inet4Address ipd = packet.get(IpV4Packet.class).getHeader().getDstAddr();
			//String file = "file.csv";
			 //try { 
			        // create FileWriter object with file as parameter 
			        //FileWriter outputfile = new FileWriter(file); 
			  
			        // create CSVWriter object filewriter object as parameter 
			        //CSVWriter writer = new CSVWriter(outputfile); 
			  
			        // adding header to csv 
			        //String[] header = { "ipdest" }; 
			        //writer.writeNext(header); 
			  
			        // add data to csv 
			      
			       // writer.writeNext(ipd ); 
			       
			  
			        // closing writer connection 
			        //writer.close(); 
			    //} 
			   // catch (IOException e) { 
			        // TODO Auto-generated catch block 
			        //e.printStackTrace(); 
			    //} 
			  
			
			
			
			
try {
				
				
	            // The newInstance() call is a work around for some
	            // broken Java implementations

	            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	        } catch (Exception ex) {
	            // handle the error
	        }
	    


			Connection conn = null;
			Statement stmt = null;
			
			try {
				
				//System.out.println(str);
				
			    conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/packetcapture","root","magic");
			    System.out.println("Database connection established");

			    // Do something with the Connection
			    stmt = conn.createStatement();
			    
			   String sql =("INSERT INTO `packet`(ipdestination,ipsrc,udpport,tcpdest,tcpsrc) VALUE ('"+str+"','"+strs+"','"+udp+"','"+tcpd+"','"+tcps+"')");
		      stmt.executeUpdate(sql);
			     stmt.executeUpdate(sql);
			      System.out.println("Created table in given database...");

			   
			} catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
		
			
				
           
           // int packetCount=9;
			
			
			//System.out.println(packetCount++);
		//}
		}
	};
	/*int x=0; 
	int y;
	Packet packet=handle.getNextPacket();
	String file= "file.txt";
   // System.out.println(packet);
    //writer.print(packet);
    try {
    	FileWriter fileWriter = new FileWriter(file);
		PrintWriter writer = new PrintWriter(fileWriter);
		while(x<8) {
			handle.getNextPacket();
			x++;
		 writer.print( "ip: " + packet.get(IpV4Packet.class).getHeader().getDstAddr());
		//System.out.println("ip: " + packet.get(IpV4Packet.class).getHeader().getSrcAddr());
			//System.out.println("timestamp: " + handle.getTimestamp());	
			System.out.println(x);
		}
		 writer.close();
	} catch ( IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
   
	


	
	
	 try {
         int maxPackets = 150000;
         handle.loop(maxPackets, listener);
        
     } catch (InterruptedException e) {
         e.printStackTrace();
     }
	
	
	


}
}