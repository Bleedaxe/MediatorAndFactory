package pu.fmi.mediatorandfactory;

import pu.fmi.mediatorandfactory.data.factory.UserFactory;
import pu.fmi.mediatorandfactory.data.io.ConsoleReader;
import pu.fmi.mediatorandfactory.data.io.ConsoleWriter;
import pu.fmi.mediatorandfactory.data.io.Reader;
import pu.fmi.mediatorandfactory.data.io.Writer;
import pu.fmi.mediatorandfactory.data.ChatRoom;

import java.util.HashSet;
import java.util.Set;

public class Application {

    private static final int DEFAULT_USER_COUNT = 3;

    public static void main(String[] args) {
        Reader reader = new ConsoleReader();
        Writer writer = new ConsoleWriter();

        ChatRoom chatRoom = new ChatRoom(writer);
        UserFactory userFactory = new UserFactory(chatRoom);

        int userCount = getUsersCount(reader, writer);
        getUsernames(userCount, reader, writer).stream()
                .map(userFactory::createUser)
                .forEach(chatRoom::addUser);

        while (!chatRoom.getUsers().isEmpty()) {
            chatRoom.getUsers().forEach(user -> {
                try {
                    writer.write(String.format("[%s]: ", user.getUsername()));
                    String message = reader.readLine();
                    user.writeMessage(message);
                } catch (Exception e) {
                    writer.writeError(e.getMessage());
                }
            });
        }

        writer.writeLine("No one standing in the chat room...");
    }

    private static int getUsersCount(Reader reader, Writer writer) {
        writer.writeLine(String.format("Enter chat room size: [%d]", DEFAULT_USER_COUNT));
        String line = reader.readLine();
        if (line.isEmpty()) {
            return DEFAULT_USER_COUNT;
        } else {
            try {
                int number = Integer.parseInt(line);
                if (number <= 0) {
                    writer.writeLine("Please enter positive number");
                    return getUsersCount(reader, writer);
                }
                return number;
            } catch (NumberFormatException e) {
                writer.writeLine("Invalid number");
                return getUsersCount(reader, writer);
            }
        }
    }

    private static Set<String> getUsernames(int usersCount, Reader reader, Writer writer) {
        Set<String> usernames = new HashSet<>();
        while (usernames.size() < usersCount) {
            String username = getUniqueUsername(usernames, reader, writer);
            usernames.add(username);
        }
        return usernames;
    }

    private static String getUniqueUsername(Set<String> usernames, Reader reader, Writer writer) {
        writer.writeLine("Enter username");
        String username = reader.readLine();
        if (usernames.contains(username)) {
            writer.writeLine("Please enter unique usernames");
            return getUniqueUsername(usernames, reader, writer);
        }
        return username;
    }
}
