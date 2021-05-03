import java.io.*;
import java.net.*;

public class HttpClient {
    void exec(String urlStr) {
        URI uri=null;
        Socket socket=null;
        try {
            uri = new URI(urlStr);
        }
        catch (URISyntaxException e1) {
            System.err.println("invalid URL");
            System.exit(0);
        }
        try {
            String host = uri.getHost();
            String path = uri.getRawPath();
            socket = new Socket(host, 80); // Connessione
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out=new PrintWriter(socket.getOutputStream());
            // Richiesta oggetto
            out.print(  "GET " + path + " HTTP/1.1\r\n" +
                    "Host: " + host + "\r\n" +
                    "Connection: close\r\n\r\n");
            out.flush( );
            // Risposta
            String messageString = "";
            String line;
            System.out.println("*** MESSAGE From " + host + ":\n");
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                /*if(line.equals("")){
                    System.out.println("<fine header>");
                    break;
                }*/
            }
        } catch (IOException e) {
            System.err.println("I/O problems: terminating");
            System.exit(0);
        }
        finally {
            System.out.println("*** closing...");
            try { socket.close(); } catch (IOException e) { }
        }
    }
    public static void main(String[] args) {
        new HttpClient().exec(args[0]);
    }
}
