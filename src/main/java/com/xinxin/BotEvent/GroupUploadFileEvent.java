package com.xinxin.BotEvent;


import com.xinxin.BotEvent.ExtendsEvents.GroupNoticeEvent;

public final class GroupUploadFileEvent extends GroupNoticeEvent {


    private String file_id;
    private String file_name;
    private long file_size;
    private long file_busid;

    public GroupUploadFileEvent(String json, long time, long self_id, String post_type, String notice_type, long group_id, long user_id
            ,String file_id,String file_name,long file_size,long file_busid) {
        super(json, time, self_id, post_type, notice_type, group_id, user_id);

        this.file_id = file_id;
        this.file_name = file_name;
        this.file_size = file_size;
        this.file_busid = file_busid;
    }


    public long getFile_busid() {
        return file_busid;
    }

    public String getFile_id() {
        return file_id;
    }

    public long getFile_size() {
        return file_size;
    }

    public String getFile_name() {
        return file_name;
    }

}
