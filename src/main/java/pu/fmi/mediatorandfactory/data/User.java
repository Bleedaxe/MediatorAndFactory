package pu.fmi.mediatorandfactory.data;

public class User {

    private final String username;
    private final ChatRoom chatRoom;

    public User(String username, ChatRoom chatRoom) {
        this.username = username;
        this.chatRoom = chatRoom;
    }

    public String getUsername() {
        return username;
    }

    public void writeMessage(String message) {
        chatRoom.writeMessage(username, message);
    }
}
