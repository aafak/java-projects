package fileHandling;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileLockExclusiveWrite {

	public static void main (String [] args)
			throws IOException {

		String input = "* end of file.";
		System.out.println("Input string: " + input);
		ByteBuffer buffer = ByteBuffer.wrap(input.getBytes());
		
		String filePath = "resources/file.txt";
		Path path = Paths.get(filePath);
		FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.WRITE,
		StandardOpenOption.APPEND);
		System.out.println("File channel opened for write. Acquiring lock...");
		fileChannel.position(fileChannel.size() - 1); // positions at the end of file
		
		FileLock lock = fileChannel.lock(); // gets an exclusive lock
		System.out.println("Lock is shared: " + lock.isShared());
		
		fileChannel.write(buffer);
		fileChannel.close();
		System.out.println("Write complete. Closing the channel and releasing lock.");
		FilePrint.print(filePath);
    }
}