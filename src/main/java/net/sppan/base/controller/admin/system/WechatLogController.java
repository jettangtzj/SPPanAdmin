package net.sppan.base.controller.admin.system;

import net.sppan.base.entity.StaticsByDate;
import net.sppan.base.entity.StaticsByUsername;
import net.sppan.base.entity.User;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.WechatLog;
import net.sppan.base.service.IWechatLogService;

import java.util.List;

@Controller
@RequestMapping("/admin/wechatLog")
public class WechatLogController extends BaseController {

	@Autowired
	private IWechatLogService wechatLogService;

	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "admin/wechatLog/index";
	}

	@RequestMapping(value = { "/list" })
	@ResponseBody
	public Page<WechatLog> list(
			@RequestParam(value="searchText",required=false) String searchText
			) {
		User currentUser = (User)SecurityUtils.getSubject().getPrincipal();
		if(searchText == null)
			searchText = "%";
		Page<WechatLog> page = wechatLogService.findAllByUsernameAndAccountContaining(currentUser.getUserName(), searchText, getPageRequest());
		return page;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable Integer id,ModelMap map) {
		try {
			wechatLogService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}



	@RequestMapping(value = { "/accountlist" })
	public String accountList(ModelMap map) {
		User currentUser = (User)SecurityUtils.getSubject().getPrincipal();
		map.put("LIST", wechatLogService.getCountByAccount(currentUser.getUserName()));
		return "admin/wechatLog/accountList";
	}


	@RequestMapping(value = { "/datelist" })
	public String dateList(ModelMap map) {
		User currentUser = (User)SecurityUtils.getSubject().getPrincipal();
		map.put("LIST", wechatLogService.getCountByDate(currentUser.getUserName()));
		return "admin/wechatLog/dateList";
	}

	@RequestMapping(value = { "/datelistStat" })
	@ResponseBody
	public JsonResult dateListStat() {
		User currentUser = (User)SecurityUtils.getSubject().getPrincipal();
		if(SecurityUtils.getSubject().hasRole("administrator")) {
			List<StaticsByUsername> list = wechatLogService.getCountByUsername();
			return JsonResult.success(list);
		}else{
			List<StaticsByDate> list = wechatLogService.getCountByDate(currentUser.getUserName());
			return JsonResult.success(list);
		}
	}
	
}
