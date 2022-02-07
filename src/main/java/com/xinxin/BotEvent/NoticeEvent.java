package com.xinxin.BotEvent;


import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class NoticeEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private String Json;//原始文本
    private String self_id;//机器人qq
    private String sub_type;//事件子类型
    private String notice_type;//通知类型
    private String operator_id;//操作者QQ
    private String duration;//被禁言时长
    private String message_id;//消息ID、被撤回的消息ID
    private String file_name;//上传文件名字
    private String file_size;//上传文件大小
    private String target_id;//被戳、运气王QQ


    public NoticeEvent(String Json,String self_id,String sub_type,String notice_type,String operator_id,String duration,String message_id,String file_name,String file_size,String target_id) {
        this.Json = Json;
        this.self_id = self_id;
        this.sub_type = sub_type;
        this.notice_type = notice_type;
        this.operator_id = operator_id;
        this.duration = duration;
        this.message_id = message_id;
        this.file_name = file_name;
        this.file_size = file_size;
        this.target_id = target_id;
    }

    public String getJson() {
        return this.Json;
    }
    public String getSelfId() {
        return this.self_id;
    }
    public String getSubType() {
        return this.sub_type;
    }
    public String getNoticeType() {
        return this.notice_type;
    }
    public String getOperatorId() {
        return this.operator_id;
    }
    public String getDuration() {
        return this.duration;
    }
    public String getMessageId() {
        return this.message_id;
    }
    public String getFile_Name() {
        return this.file_name;
    }
    public String getFile_Size() {
        return this.file_size;
    }
    public String getTargetId() {
        return this.target_id;
    }


    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
