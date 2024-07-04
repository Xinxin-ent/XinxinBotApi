package com.xinxin.GroupData;

import net.sf.json.JSONObject;

public class GroupMemberInfo {
    private long group_id;
    private long user_id;
    private String nickname;
    private String card;
    private String sex;
    private long age;
    private String area;
    private long join_time;
    private long last_sent_time;
    private String level;
    private String role;
    private boolean unfriendly;
    private String title;
    private long title_expire_time;
    private boolean card_changeable;
    private long shut_up_timestamp;

    public GroupMemberInfo(JSONObject data){
        group_id = data.getLong("group_id");
        user_id = data.getLong("user_id");
        nickname = data.getString("nickname");
        card = data.getString("card");
        sex = data.getString("sex");
        age = data.getLong("age");
        area = data.has("area") ? data.getString("area") : null;
        join_time = data.getLong("join_time");
        last_sent_time = data.getLong("last_sent_time");
        level = data.getString("level");
        role = data.getString("role");
        unfriendly = data.getBoolean("unfriendly");
        title = data.has("title") ? data.getString("title") : null;
        title_expire_time = data.has("title_expire_time") ? data.getLong("title_expire_time") : 0;
        card_changeable = data.getBoolean("card_changeable");
        shut_up_timestamp = data.getLong("shut_up_timestamp");
    }

    public void deBug(){
        System.out.println("group_id: "+group_id);
        System.out.println("user_id: "+user_id);
        System.out.println("nickname: "+nickname);
        System.out.println("card: "+card);
        System.out.println("sex: "+sex);
        System.out.println("age: "+age);
        System.out.println("area: "+area);
        System.out.println("join_time: "+join_time);
        System.out.println("last_sent_time: "+last_sent_time);
        System.out.println("level: "+level);
        System.out.println("unfriendly: "+unfriendly);
        System.out.println("title: "+title);
        System.out.println("title_expire_time: "+title_expire_time);
        System.out.println("card_changeable: "+card_changeable);
        System.out.println("shut_up_timestamp: "+shut_up_timestamp);
    }

    public boolean isCard_changeable() {
        return card_changeable;
    }

    public boolean isUnfriendly() {
        return unfriendly;
    }

    public long getGroup_id() {
        return group_id;
    }

    public String getTitle() {
        return title;
    }

    public long getAge() {
        return age;
    }

    public long getJoin_time() {
        return join_time;
    }

    public long getLast_sent_time() {
        return last_sent_time;
    }

    public long getShut_up_timestamp() {
        return shut_up_timestamp;
    }

    public long getTitle_expire_time() {
        return title_expire_time;
    }

    public long getUser_id() {
        return user_id;
    }

    public String getArea() {
        return area;
    }

    public String getCard() {
        return card;
    }

    public String getNickname() {
        return nickname;
    }

    public String getRole() {
        return role;
    }

    public String getSex() {
        return sex;
    }
}
