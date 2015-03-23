package tests;

import java.io.IOException;
import com.sun.squawk.VM;
import com.sun.squawk.io.mailboxes.Channel;
import com.sun.squawk.io.mailboxes.Envelope;
import com.sun.squawk.io.mailboxes.ByteArrayEnvelope;
import sics.port.PluginPPort;
import sics.port.PluginRPort;
import sics.plugin.PlugInComponent;

public class Platoon extends PlugInComponent {
	private PluginPPort spWriter;
	private PluginRPort spReader;
	
	public Platoon(String[] args) {
		super(args);
	}
	
	public static void main(String[] args) {
		VM.println("Platoon.main()\r\n");
		Platoon platoon = new Platoon(args);
		platoon.init();
		platoon.doFunction();
		VM.println("Platoon-main done\r\n");
	}

	@Override
	public void init() {
		// Initiate PluginPPort
		spWriter = new PluginPPort(this, "spWriter");
		spReader = new PluginRPort(this, "spReader");
	}
	
	public void run() {}

	public void doFunction() {
		String data;
		for (int i = 0; i < 1000; i++) {
			VM.println("[Platoon is running]");

			String speedDataStr = (String)spReader.receive();
			VM.print("speed from TCU:");
			VM.println(speedDataStr);
//			spWriter.write(speedData);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				VM.println("Interrupted.\r\n");
			}

		}
	}
}