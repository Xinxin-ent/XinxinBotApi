package com.xinxin.GroupData;

import net.sf.json.JSONObject;

public class GroupInfo {

    private long group_id;
    private String group_name;
    private String group_memo;
    private long group_create_time;
    private long group_level;
    private long member_count;
    private long max_member_count;

    public GroupInfo(JSONObject data) {
        group_id = data.getLong("group_id");
        group_name = data.getString("group_name");
        group_memo = data.has("group_memo") ? data.getString("group_memo"):null;
        group_create_time = data.has("group_create_time") ? data.getLong("group_create_time") : 0;
        group_level = data.has("group_level") ? data.getLong("group_level") : 0;
        member_count = data.getLong("member_count");
        max_member_count = data.getLong("max_member_count");
    }

    public void deBug(){
        System.out.println("group_id: "+group_id);
        System.out.println("group_name: "+group_name);
        System.out.println("group_memo: "+group_memo);
        System.out.println("group_create_time: "+group_create_time);
        System.out.println("group_level: "+group_level);
        System.out.println("member_count: "+member_count);
        System.out.println("max_member_count: "+max_member_count);
    }

    public String getGroup_name() {
        return group_name;
    }

    public long getGroup_create_time() {
        return group_create_time;
    }

    public long getGroup_id() {
        return group_id;
    }

    public long getGroup_level() {
        return group_level;
    }

    public long getMax_member_count() {
        return max_member_count;
    }

    public long getMember_count() {
        return member_count;
    }

    public String getGroup_memo() {
        return group_memo;
    }
}
