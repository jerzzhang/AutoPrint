import java.io.*;
import java.util.*;
import javax.mail.*;

public class AutoPrint {
	public static void main(String args[]) throws Exception {

		String host = "pop.gmail.com";
		String user = "jzthebomb@gmail.com";
		String password = "";

		// Get system properties
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", user);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(props,null);

		// Get a Store object that implements the specified protocol.
		Store store = session.getStore("pop3s");

		//Connect to the current host using the specified username and password.
		store.connect(host, user, password);

		//Create a Folder object corresponding to the given name.
		Folder folder = store.getFolder("inbox");

		// Open the Folder.
		folder.open(Folder.READ_WRITE);

		Message[] message = folder.getMessages();

		// Display message.
		for (int i = 0; i < message.length; i++) {

			System.out.println("------------ Message " + (i + 1) + " ------------");

			System.out.println("SentDate : " + message[i].getSentDate());
			System.out.println("From : " + message[i].getFrom()[0]);
			System.out.println("Subject : " + message[i].getSubject());
			System.out.print("Message : ");

			InputStream stream = message[i].getInputStream();
			while (stream.available() != 0) {
				System.out.print((char) stream.read());
			}
			System.out.println();
		}

		folder.close(true);
		store.close();
	}
}
