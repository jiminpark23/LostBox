package com.example.lostbox;

public class ChatRoom {
    private String roomName;
    private String lastMessage;
    private int timestamp;

    private int profileImage;

    // 생성자

    /*
    public ChatRoom(int profileImage, String roomName, String lastMessage, int unreadCount, int timestamp) {
        this.roomName = roomName;
        this.lastMessage = lastMessage;
        this.unreadCount = unreadCount;
        this.timestamp = timestamp;
        this.profileImage = profileImage;
    }
    */

    // getter 메소드
    public int getProfileImage(){
        return profileImage;
    }
    public void setProfileImage(int profileImage){
        this.profileImage = profileImage;
    }

    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName){
        this.roomName = roomName;
    }

    public String getLastMessage() {
        return lastMessage;
    }
    public void setLastMessage(String lastMessage) {this.lastMessage = lastMessage; }


    public int getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(int timestamp){
        this.timestamp = timestamp;
    }


}