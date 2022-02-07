package com.xinxin.BotTool;

import java.util.List;

public class MiraiMessage {
	private String Json;//消息原本json文本
	private String type;//消息类型

	//消息数据
	private int id;//消息的messageId
	private int time;//消息时间戳

	//文本数据
	private String text;//消息文本

	//图片数据
	private String imageId;//图片的imageId，群图片与好友图片格式不同。不为空时将忽略url属性
	private String url;//图片的URL，发送时可作网络图片的链接；接收时为腾讯图片服务器的链接，可用于图片下载
	private String path;//图片的路径，发送本地图片，路径相对于 JVM 工作路径（默认是当前路径，可通过 -Duser.dir=...指定），也可传入绝对路径。
	private String base64;//图片的 Base64 编码

	//艾特qq
	private Long target;//at的QQ
	private String display;//At时显示的文字，发送消息时无效，自动使用群名片

	//引用消息
	private Long groupId;//被引用回复的原消息所接收的群号，当为好友消息时为0
	private Long senderId;//被引用回复的原消息的发送者的QQ号
	private Long targetId;//被引用回复的原消息的接收者者的QQ号（或群号）
	private List<MiraiMessage> origin;//被引用回复的原消息的消息链对象

	public MiraiMessage(){
	}

	public String getMessage(){
		if(type.equals("Plain")){
			return text;
		}else

		if(type.equals("Image")){
			return "{xx:image,url="+url+",path="+path+"}";
		}else

		if(type.equals("At")){
			return "{xx:at,qq="+target+"}";
		}
		return "";
	}

	public void deBug(){
		System.out.println("§7[§a§l*§7] §a消息类型: §e"+type);
		if(type.equals("Source")){
			System.out.println("§7[§a§l*§7] §a消息ID: §e"+id);
			System.out.println("§7[§a§l*§7] §a消息时间截: §e"+time);
		}else

		if(type.equals("Plain")){
			System.out.println("§7[§a§l*§7] §a消息文本: §e"+text);
		}else

		if(type.equals("Image")){
			System.out.println("§7[§a§l*§7] §a图片的imageId: §e"+imageId);
			System.out.println("§7[§a§l*§7] §a图片的URL: §e"+url);
			System.out.println("§7[§a§l*§7] §a图片的路径: §e"+path);
			System.out.println("§7[§a§l*§7] §aBase64编码: §e"+base64);
		}else

		if(type.equals("Quote")){
			System.out.println("§7[§a§l*§7] §a消息ID: §e"+id);
			System.out.println("§7[§a§l*§7] §a接收群号: §e"+groupId);
			System.out.println("§7[§a§l*§7] §a发送者QQ: §e"+senderId);
			System.out.println("§7[§a§l*§7] §a接收者QQ/群号: §e"+targetId);
			System.out.println("§c————————————");
			System.out.println("§7[§a§l*§7] §a引用消息: ");
			for(MiraiMessage mm : origin){
				mm.deBug();
			}
			System.out.println("§c————————————");
			return;
		}else

		if(type.equals("At")){
			System.out.println("§7[§a§l*§7] §aAt成员: §e"+target);
			System.out.println("§7[§a§l*§7] §a显示文字: §e"+display);
		}
		System.out.println("§7————————————");
	}










	public List<MiraiMessage> getOrigin() {
		return origin;
	}

	public Long getGroupId() {
		return groupId;
	}

	public Long getSenderId() {
		return senderId;
	}

	public Long getTarget() {
		return target;
	}

	public Long getTargetId() {
		return targetId;
	}

	public String getBase64() {
		return base64;
	}

	public String getDisplay() {
		return display;
	}

	public String getImageId() {
		return imageId;
	}

	public String getPath() {
		return path;
	}

	public String getText() {
		return text;
	}

	public String getUrl() {
		return url;
	}

	public String getJson() {
		return Json;
	}

	public String getType() {
		return type;
	}

	public int getTime() {
		return time;
	}

	public int getId() {
		return id;
	}










	public void setDisplay(String display) {
		this.display = display;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public void setOrigin(List<MiraiMessage> origin) {
		this.origin = origin;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setJson(String json) {
		Json = json;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public void setTarget(Long target) {
		this.target = target;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
