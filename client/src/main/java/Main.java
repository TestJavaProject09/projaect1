import request.AnswerReader;
import request.RequestSender;
import utility.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InetAddress serverAddress = null;
        InetAddress clientAddress = null;
        int port = 9898;
        try {
            serverAddress = InetAddress.getByName("0.0.0.0");
            clientAddress = InetAddress.getByName("0.0.0.0");
            if (args.length != 0 && args[0].contains(":")) {
                serverAddress = InetAddress.getByName(args[0].split(":")[0]);
                port = Integer.parseInt(args[0].split(":")[1]);
            } else {
                System.out.println("Server IP wasn't found. Default value localhost:9898 will be used.");
            }
        } catch (UnknownHostException exception) {
            exception.printStackTrace();
            System.out.println(exception);
            return;
        } catch (NumberFormatException exception) {
            System.out.println("Incorrect format of port.");
            System.out.println(exception);
        }
        SocketAddress socketAddress = new InetSocketAddress(serverAddress, port);
        DatagramChannel datagramChannel;
        try {
            datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(false);
            System.out.println(datagramChannel.getLocalAddress());
        } catch (IOException exception) {
            System.out.println(exception);
            return;
        }
        RequestSender requestSender = new RequestSender(datagramChannel, socketAddress);
        AnswerReader answerReader = new AnswerReader(datagramChannel, socketAddress);
        Scanner scanner = new Scanner(System.in);
        Console console = new Console(scanner, answerReader);
        Invoker invoker = new Invoker();
        invoker.initMap(datagramChannel, socketAddress, console, invoker);
        CommandReader commandReader = new CommandReader(console, invoker, answerReader);
        commandReader.activeMode();
    }
}
