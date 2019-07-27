package net.sppan.base.controller.web;

import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.WechatLog;
import net.sppan.base.service.IWechatLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController extends BaseController{
	
	@Autowired
	private IWechatLogService wechatLogService;
	
	/**
	 * 接收提交
	 * @param info
	 * @return
	 */
	@RequestMapping(value="/post")
	@ResponseBody
	public String post(WechatLog info){
		try {
			if(info.getAccount() == null || info.getAccount().equals("")) {
				return "fail account cannot be null";
			}
			if(info.getNickName() == null || info.getNickName().equals("")) {
				return "fail nickName cannot be null";
			}
			if(info.getUsername() == null || info.getUsername().equals("")) {
				return "fail username cannot be null";
			}
			wechatLogService.saveOrUpdate(info);
		}catch(Exception e) {
			return "fail" + e;
		}
		return "success";
	}
}
