package pu.fmi.mediatorandfactory.data.factory;

import pu.fmi.mediatorandfactory.data.User;
import pu.fmi.mediatorandfactory.data.ChatRoom;

public class UserFactory {

    private final ChatRoom chatRoom;

    public UserFactory(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public User createUser(String username) {
        return new User(username, chatRoom);
    }
}