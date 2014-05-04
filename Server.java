/**
 * Created by Сергей on 03.05.14.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Random;

public class Server {
    public static void main(String[] args) throws IOException
    {
        Socket socket=null;
        Scanner scanner=new Scanner(System.in); // ввод
        try
        {
            ServerSocket server;
            server = new ServerSocket(8025); //задаем порт
            socket=server.accept();          //принимаем
            PrintStream outStream=new PrintStream(socket.getOutputStream()); //печатаем и отправляем наружу
            BufferedReader bufRead=new BufferedReader(new InputStreamReader(socket.getInputStream())); //читаем из буфера

            String infoMessage="The 21 game: you are to get the numbers from 1 to 10 until you stop or you're out of range. To win you must get 21. Press 'y' to get another number or any key to stop.";
            outStream.println(infoMessage);    //1
            System.out.println(infoMessage);
            outStream.flush(); //очистка

            int temp=0;
            int current=0;
            Boolean game=true;
            while(game)
            {
                Random rand = new Random();
                outStream.println("Do you want to get a number?");  //2
                infoMessage=bufRead.readLine();
                if (infoMessage.equalsIgnoreCase("y"))
                {
                    temp = rand.nextInt(9)+1;
                    current = current + temp;
                    if (current > 21)
                    {
                        outStream.println("You get " + Integer.toString(temp) + ". Your current number is " + Integer.toString(current)+"\n"+"You lose!");  //3
                        outStream.flush();
                        game = false;
                        outStream.println("stop");
                        outStream.flush();
                        //4
                    }
                    else if(current==21)
                    {
                        outStream.println("You win!"); //3
                        outStream.flush();
                        game = false;
                        outStream.println("stop");
                        //4
                    }
                    else
                    {
                        outStream.println("You get " + Integer.toString(temp) + ". Your current number is " + Integer.toString(current)); //3
                        outStream.flush();
                        outStream.println("continue");
                        //4
                    }
                }
                    else
                    {
                        outStream.println("You get " + Integer.toString(current) + " Almost good! Try again! :D"); //3
                        game=false;
                        outStream.println("stop"); //4
                    }
            }
            outStream.close();
        }
        catch (IOException e){
            System.out.println("error"+e);
        }
        finally {
            if(socket!=null)
                socket.close();
        }
    }
}

