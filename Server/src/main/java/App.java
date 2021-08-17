import exceptions.IncorrectArgumentException;
import exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import request.AnswerSender;
import request.RequestAcceptor;
import utility.*;

import java.net.*;

public class App {


    public static void main(String[] args) throws ValidationException, IncorrectArgumentException, SocketException, UnknownHostException {
        final String envVariable = "LABA";
        DatagramSocket datagramSocket;
        CollectionManager collectionManager = new CollectionManager();
        FileVehicle fileVehicle = new FileVehicle(collectionManager, envVariable);
        int port = 9898;
        try {
            if (args.length == 1) {
                collectionManager.setCollection(fileVehicle.ReadFile());
                port = Integer.parseInt(args[0]);
            } else {
                System.out.println("Incorrect command line arguments. Please, follow the format: \"file_path port\".");
                return;
            }
        } catch (NumberFormatException exception) {
            System.out.println("Incorrect format of port.");
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> fileVehicle.writeDocument(collectionManager.getCollection())));
        if (port == 9898) {
            System.out.println("Port hasn't been identified. " + port + " will be used.");
        }
        try {
            datagramSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            System.out.println("Failed creating socket. Socket is already used.");
            return;
        }
        InetSocketAddress Host = new InetSocketAddress(InetAddress.getLocalHost(), port);
        datagramSocket.bind(Host);
        System.out.println(datagramSocket.getLocalSocketAddress());
        Logger logger = LoggerFactory.getLogger("Server");
        AnswerSender answerSender = new AnswerSender(logger);
        System.out.println(datagramSocket.getLocalSocketAddress());
        answerSender.setSocketAddress(datagramSocket.getLocalSocketAddress());
        VehicleFactory vehicleFactory = new VehicleFactory();
        vehicleFactory.setStartId(collectionManager.getLastId());
        Receiver receiver = new Receiver(collectionManager, answerSender, vehicleFactory);
        Invoker invoker = new Invoker(receiver);
        invoker.initMap();
        RequestAcceptor requestAcceptor = new RequestAcceptor(vehicleFactory, logger, invoker, answerSender);
        requestAcceptor.acceptRequest(datagramSocket);
        try {
            logger.info("Server started on address " + InetAddress.getLocalHost() + " port: " + port);
            System.out.println("Server started on address " + InetAddress.getLocalHost() + " port: " + port);
        } catch (UnknownHostException exception) {
            exception.printStackTrace();
        }
    }
}
