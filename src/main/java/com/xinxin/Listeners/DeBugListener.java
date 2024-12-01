package com.xinxin.Listeners;

import com.xinxin.BotEvent.ExtendsEvents.*;
import com.xinxin.BotEvent.*;
import com.xinxin.PluginBasicTool.BotData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DeBugListener implements Listener {
    @EventHandler
    public void GroupMessageEvent(GroupMessageEvent event){
        if(BotData.isDeBug()){
            System.out.println("【触发群聊事件】获取到以下参数：");
            outMessage(event);
            System.out.println("getGroup_id: "+event.getGroup_id());
        }
    }
    @EventHandler
    public void PrivateMessageEvent(PrivateMessageEvent event){
        if(BotData.isDeBug()){
            System.out.println("【触发私聊事件】获取到以下参数：");
            outMessage(event);
        }
    }
    @EventHandler
    public void FriendRequestEvent(FriendRequestEvent event){
        if(BotData.isDeBug()) {
            System.out.println("【触发好友请求事件】获取到以下参数：");
            System.out.println("getTime: "+event.getTime());
        }
    }
    @EventHandler
    public void GroupRequestEvent(GroupRequestEvent event) {
        if (BotData.isDeBug()) {
            System.out.println("【触发群申请事件】获取到以下参数：");
            outRequest(event);
            System.out.println("getGroup_id: "+event.getGroup_id());
            System.out.println("getUser_id: "+event.getUser_id());
        }
    }
    @EventHandler
    public void FriendAddEvent(FriendAddEvent event){
        if(BotData.isDeBug()) {
            System.out.println("【触发新增好友事件】获取到以下参数：");
            System.out.println("getTime: "+event.getTime());
            System.out.println("getSelf_id: "+event.getSelf_id());
            System.out.println("getPost_type: "+event.getPost_type());
            System.out.println("getNotice_type: "+event.getNotice_type());
            System.out.println("getUser_id: "+event.getUser_id());
        }
    }
    @EventHandler
    public void GroupBanEvent(GroupBanEvent event){
        if(BotData.isDeBug()) {
            System.out.println("【触发群禁言事件】获取到以下参数：");
            outGroupNotice(event);
            System.out.println("getOperator_id: "+event.getOperator_id());
            System.out.println("getDuration: "+event.getDuration());
        }
    }
    @EventHandler
    public void GroupCardEvent(GroupCardEvent event){
        if(BotData.isDeBug()) {
            System.out.println("【触发群名片更改事件】获取到以下参数：");
            outGroupNotice(event);
            System.out.println("getCard_new: "+event.getCard_new());
            System.out.println("getCard_old: "+event.getCard_old());
        }
    }
    @EventHandler
    public void GroupMessageDeleteEvent(GroupMessageDeleteEvent event){
        if(BotData.isDeBug()) {
            System.out.println("【触发群消息撤回事件】获取到以下参数：");
            outGroupNotice(event);
            System.out.println("getOperator_id: "+event.getOperator_id());
            System.out.println("getMessage_id: "+event.getMessage_id());
        }
    }
    @EventHandler
    public void GroupPokeEvent(GroupPokeEvent event){
        if(BotData.isDeBug()) {
            System.out.println("【触发群戳一戳事件】获取到以下参数：");
            outGroupNotice(event);
            System.out.println("target_id: "+event.getTarget_id());
        }
    }
    @EventHandler
    public void GroupUploadFileEvent(GroupUploadFileEvent event){
        if(BotData.isDeBug()) {
            System.out.println("【触发群文件上传事件】获取到以下参数：");
            outGroupNotice(event);
            System.out.println("getFile_busid: "+event.getFile_busid());
            System.out.println("getFile_id: "+event.getFile_id());
            System.out.println("getFile_size: "+event.getFile_size());
            System.out.println("getFile_name: "+event.getFile_name());
        }
    }
    @EventHandler
    public void GroupTitleEvent(GroupTitleEvent event){
        if(BotData.isDeBug()) {
            System.out.println("【触发群头衔更改事件】获取到以下参数：");
            outGroupNotice(event);
            System.out.println("getTitle: "+event.getTitle());
        }
    }
    @EventHandler
    public void GroupUserChangesEvent(GroupUserChangesEvent event){
        if(BotData.isDeBug()) {
            System.out.println("【触发成员变更事件】获取到以下参数：");
            outGroupNotice(event);
            System.out.println("getOperator_id: "+event.getOperator_id());
        }
    }
    @EventHandler
    public void PrivateMessageDeleteEvent(PrivateMessageDeleteEvent event){
        if(BotData.isDeBug()) {
            System.out.println("【触发私聊消息撤回事件】获取到以下参数：");
            outPrivateNotice(event);
            System.out.println("getMessage_id: "+event.getMessage_id());
        }
    }
    @EventHandler
    public void PrivatePokeEvent(PrivatePokeEvent event){
        if(BotData.isDeBug()) {
            System.out.println("【触发私聊戳一戳事件】获取到以下参数：");
            outPrivateNotice(event);
            System.out.println("getTarget_id: "+event.getTarget_id());
        }
    }

    public void outMessage(MessageEvent event){
        System.out.println("getTime: "+event.getTime());
        System.out.println("getSelf_id: "+event.getSelf_id());
        System.out.println("getPost_type: "+event.getPost_type());
        System.out.println("getMessage_type: "+event.getMessage_type());
        System.out.println("getSub_type: "+event.getSub_type());
        System.out.println("getUser_id: "+event.getUser_id());
        System.out.println("getMessage_id: "+event.getMessage_id());
        System.out.println("getMessage: "+event.getMessage());
        System.out.println("getArrayJsonMessage: "+event.getArrayJsonMessage());
        System.out.println("getFont: "+event.getFont());
        System.out.println("getSender_nickname: "+event.getSender_nickname());
        System.out.println("getSender_card: "+event.getSender_card());
        System.out.println("getSender_role: "+event.getSender_role());
    }
    public void outRequest(RequestEvent event){
        System.out.println("getTime: "+event.getTime());
        System.out.println("getSelf_id: "+event.getSelf_id());
        System.out.println("getPost_type: "+event.getPost_type());
        System.out.println("getRequest_type: "+event.getRequest_type());
        System.out.println("getSub_type: "+event.getSub_type());
        System.out.println("getComment: "+event.getComment());
        System.out.println("getFlag: "+event.getFlag());
    }


    public void outNotice(NoticeEvent event){
        System.out.println("getTime: "+event.getTime());
        System.out.println("getSelf_id: "+event.getSelf_id());
        System.out.println("getPost_type: "+event.getPost_type());
        System.out.println("getNotice_type: "+event.getNotice_type());
        System.out.println("getSub_type: "+event.getSub_type());
    }

    public void outGroupNotice(GroupNoticeEvent event){
        outNotice(event);
        System.out.println("getGroup_id: "+event.getGroup_id());
        System.out.println("getUser_id: "+event.getUser_id());
    }
    public void outPrivateNotice(PrivateNoticeEvent event){
        outNotice(event);
        System.out.println("getUser_id: "+event.getUser_id());
    }
}
