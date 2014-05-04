/**
 * Created by Сергей on 03.05.14.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Random;

public class Client {
    public static void main(String[] args)
    {
        Socket socket=null;
        Scanner scanner=new Scanner(System.in);
        try
        {
            System.out.println("Enter Ip address: ");
            String IP=scanner.nextLine();
            socket=new Socket(IP, 8025);
            PrintStream outStream=new PrintStream(socket.getOutputStream());
            BufferedReader bufRead=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String infoMessage=bufRead.readLine(); //1
            System.out.println(infoMessage);
            System.out.println("Let's play!");

            Boolean game=true;
            while(game)
            {
                infoMessage = bufRead.readLine(); //2
                System.out.println(infoMessage);
                infoMessage=scanner.nextLine();
                outStream.println(infoMessage);
                infoMessage = bufRead.readLine(); //3
                System.out.println(infoMessage);
                infoMessage = bufRead.readLine();
                if(infoMessage.equalsIgnoreCase("stop"))
                    game=false;
            }
            outStream.close();
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

