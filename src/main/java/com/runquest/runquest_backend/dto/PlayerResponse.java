package com.runquest.runquest_backend.dto;

public class PlayerResponse {

    private long id;
    private String nickname;
    private String email;
    private long gold;
    private long xp;
    private Integer level;

    public PlayerResponse(long id, String nickname, String email, long gold, long xp, Integer level) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.gold = gold;
        this.xp = xp;
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public long getXp() {
        return xp;
    }

    public void setXp(long xp) {
        this.xp = xp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}