import java.io.*;
import java.util.*;
import javax.mail.*;

public class ReadEmail {
	public static void main(String args[]) throws IOException {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		try {
			int h = 0;
			for(int i = 0; i < 999999; i++){
				Session session = Session.getDefaultInstance(props, null);
				Store store = session.getStore("imaps");
				store.connect("imap.gmail.com", "slradoozle@gmail.com", "");
				System.out.println(store);

				Folder inbox = store.getFolder("Inbox");
				inbox.open(Folder.READ_ONLY);
				Message messages[] = inbox.getMessages();
				for(Message message:messages) {
					System.out.println(message.getContent());
					PrintWriter out = new PrintWriter("order" + h + ".txt");
					out.print(message.getContent());
					out.close();
					h++;
				}
				Folder trash = store.getFolder("[Gmail]/Trash");
				for(Message message:messages) {
					inbox.copyMessages(new Message[] {message}, trash);
				}
				try{
					Thread.sleep(15000);
				}
				catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
			}
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (MessagingException e) {
			e.printStackTrace();
			System.exit(2);
		}
	}
}