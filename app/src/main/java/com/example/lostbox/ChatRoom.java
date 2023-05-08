package com.example.lostbox;

public class ChatRoom {
    private String roomName;
    private String lastMessage;
    private int unreadCount;
    private long timestamp;

    private int profileImage;

    // 생성자
    public ChatRoom(int profileImage, String roomName, String lastMessage, int unreadCount, long timestamp) {
        this.roomName = roomName;
        this.lastMessage = lastMessage;
        this.unreadCount = unreadCount;
        this.timestamp = timestamp;
        this.profileImage = profileImage;
    }

    // getter 메소드
    public int getProfileImage(){
        return profileImage;
    }
    public String getRoomName() {
        return roomName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public long getTime() {
        return timestamp;
    }
}