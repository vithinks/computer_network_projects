
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
import java.sql.Timestamp;

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

public class App2 
{




	public static void main(String[] args)throws PcapNativeException, NotOpenException {
	PcapHandle handle;
	try {
		
	    handle = Pcaps.openOffline("17.pcap", TimestampPrecision.MICRO);
	} catch (PcapNativeException e) {
	    handle = Pcaps.openOffline("17.pcap");
	}
	
	
		

		
			Packet packet = handle.getNextPacket();
			  
			
			int x=0; 
			int y;
			
			//String file= "file.txt";
			// System.out.println(packet);
			//writer.print(packet);
			//try {
				//FileWriter fileWriter = new FileWriter(file);
				//PrintWriter writer = new PrintWriter(fileWriter);
				while(x<10000) {
					handle.getNextPacket();
					x++;
				 Inet4Address dst = packet.get(IpV4Packet.class).getHeader().getDstAddr();
				 Inet4Address src = packet.get(IpV4Packet.class).getHeader().getSrcAddr();
					Timestamp time = handle.getTimestamp();	
					System.out.println(x);
					//System.out.println(handle.getTimestampPrecision());
			
			String idst =String.valueOf(dst);
			String isrc= String.valueOf(src);
			String timest= String.valueOf(time);
			
			
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
			    
			   String sql =("INSERT INTO `time`(ips,ipd,timestmp) VALUE ('"+isrc+"','"+idst+"','"+timest+"')");
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
	
	
   
	


	
	
	
	
	



	}
}
