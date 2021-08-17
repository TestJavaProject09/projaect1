package utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import data.Vehicle;
import request.AnswerSender;
import request.RequestAcceptor;

import java.util.Stack;


public class Receiver {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(RequestAcceptor.class);
    private final CollectionManager collectionManager;
    private final VehicleFactory vehicleFactory;
    private final AnswerSender answerSender;

    public Receiver(CollectionManager collectionManager, AnswerSender answerSender, VehicleFactory vehicleFactory) {
        this.collectionManager = collectionManager;
        this.answerSender = answerSender;
        this.vehicleFactory = vehicleFactory;

    }
    public void removeByEnPower(String arg){
        Integer temp = Integer.parseInt(arg);
        if (collectionManager.removeFromCollection(collectionManager.getByEnginePower(temp))) {
            answerSender.addToAnswer(true, "Elements have been removed.", null, null);
        } else {
            answerSender.addToAnswer(false, "Element not found!", null, null);
        }
        logger.info("Result of command \"remove_by_engine_power\" has been sent to client.");
        answerSender.sendAnswer();
    }
    public void add() {
        Vehicle vehicle = (Vehicle) vehicleFactory.getLoadObject();
        vehicle.setId((vehicleFactory.getId() + 1));
        vehicleFactory.setStartId(vehicle.getId());
        collectionManager.add(vehicle);
        answerSender.addToAnswer(true, "Vehicle was added to the collection.", null, null);
        logger.info("Result of command \"add\" has been sent to client.");
        answerSender.sendAnswer();
    }

    public void addIfMax() {
        Vehicle vehicle = (Vehicle) vehicleFactory.getLoadObject();
        vehicle.setId((vehicleFactory.getId() + 1));
        vehicleFactory.setStartId(vehicle.getId());
        collectionManager.addIfMax(vehicle);
        answerSender.addToAnswer(true, "Vehicle was added to the collection.", null, null);
        logger.info("Result of command \"add_if_max\" has been sent to client.");
        answerSender.sendAnswer();
    }

    public void clear() {
        collectionManager.clear();
        answerSender.addToAnswer(true, "Collection has been cleared.", null, null);
        logger.info("Result of command \"clear\" has been sent to client.");
        answerSender.sendAnswer();
    }

    public void filterContainsName(String arg) {
        String answer = collectionManager.vehicleFilteredInfo(arg);
        answerSender.addToAnswer(true, answer, null, null);
        logger.info("Result of command \"count_less_than_start_date\" has been sent to client.");
        answerSender.sendAnswer();
    }

    public void minByDistanceTravelled() {
        Stack<Vehicle> answer = collectionManager.minByDistanceTravelled();
        if (answer == null) {
            answerSender.addToAnswer(false, "Collection doesn't contains satisfying elements.", null, null);
        } else {
            answerSender.addToAnswer(true, "Command has done successfully.", null, answer);
        }
        logger.info("Result of command \"filter_greater_than_start_date\" has been sent to client.");
        answerSender.sendAnswer();
    }

    public void info() {
        String answer = collectionManager.getInfo();
        answerSender.addToAnswer(true, answer, null, null);
        logger.info("Result of command \"info\" has been sent to client.");
        answerSender.sendAnswer();
    }

    public void removeById(String arg) {
        int id;
        try {
            id = Integer.parseInt(arg);
            CollectionValidator collectionValidator = new CollectionValidator(collectionManager);
            if (collectionValidator.checkExistId(id)) {
                collectionManager.removeById(id);
                answerSender.addToAnswer(true, "Element with id " + id + " has been removed.", null, null);
            } else {
                answerSender.addToAnswer(false, "There is no elements with matched id in the collection.", null, null);
            }
        } catch (NumberFormatException exception) {
            System.out.println(exception.getMessage());
        }
        logger.info("Result of command \"remove_by_id\" has been sent to client.");
        answerSender.sendAnswer();
    }

    public void removeGreater() {
        Vehicle vehicle = (Vehicle) vehicleFactory.getLoadObject();
        if (collectionManager.removeGreater(vehicle)) {
            answerSender.addToAnswer(true, "Elements have been removed.", null, null);
        } else {
            answerSender.addToAnswer(false, "There is no elements in collection which are greater than indicated one.", null, null);
        }
        logger.info("Result of command \"remove_greater\" has been sent to client.");
        answerSender.sendAnswer();
    }

    public void removeFirst() {
        if (collectionManager.removeFromCollection(collectionManager.getFirst())) {
            answerSender.addToAnswer(true, "Elements have been removed.", null, null);
        } else {
            answerSender.addToAnswer(false, "Collection is empty", null, null);
        }
        logger.info("Result of command \"remove_first\" has been sent to client.");
        answerSender.sendAnswer();
    }

    public void show() {
        answerSender.addToAnswer(true, "", null, collectionManager.show());
        logger.info("Result of command \"show\" has been sent to client.");
        answerSender.sendAnswer();
    }

    public void update(String arg) {
        Integer tempId;
        Vehicle vehicle = (Vehicle) vehicleFactory.getLoadObject();
        try {
            tempId = Integer.parseInt(arg);
            CollectionValidator collectionValidator = new CollectionValidator(collectionManager);
            if (collectionValidator.checkExistId(tempId)) {
                collectionManager.update(tempId, vehicle);
                answerSender.addToAnswer(true, "Element has been updated.", null, null);
            } else {
                answerSender.addToAnswer(false, "There is no elements with matched id in the collection.", null, null);
            }
        } catch (NumberFormatException exception) {
            answerSender.addToAnswer(false, "Command is incorrect.", null, null);
        }
        logger.info("Result of command \"update\" has been sent to client.");
        answerSender.sendAnswer();
    }

    public void validateId(String arg) {
        int tempId;
        try {
            tempId = Integer.parseInt(arg);
            CollectionValidator collectionValidator = new CollectionValidator(collectionManager);
            answerSender.addToAnswer(collectionValidator.checkExistId(tempId), "", null, null);
        } catch (NumberFormatException exception) {
            answerSender.addToAnswer(false, "", null, null);
        }
        logger.info("Result of validation has been sent to client.");
        answerSender.sendAnswer();
    }
}