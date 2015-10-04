package fileHandling;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileLockSharedRead {

	public static void main (String [] args)
			throws IOException, InterruptedException {

		Path path = Paths.get("resources/file.txt");
		FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ);
		System.out.println("File channel opened for read. Acquiring lock...");

		FileLock lock = fileChannel.lock(0, Long.MAX_VALUE, true);
		System.out.println("Lock acquired: " + lock.isValid());
		System.out.println("Lock is shared: " + lock.isShared());
		
		ByteBuffer buffer = ByteBuffer.allocate(20);
		int noOfBytesRead = fileChannel.read(buffer);
		System.out.println("Buffer contents: ");	

		while (noOfBytesRead != -1) {

			buffer.flip();
			System.out.print("    ");
			
			while (buffer.hasRemaining()) {
				
				System.out.print((char) buffer.get());                
			}

			System.out.println(" ");
                
			buffer.clear();
			Thread.sleep(1000);
			noOfBytesRead = fileChannel.read(buffer);
		}

		fileChannel.close(); // also releases the lock
		System.out.print("Closing the channel and releasing lock.");
    }
}