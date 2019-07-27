package net.sppan.base.controller.admin.system;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
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
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		String username = (String)session.getAttribute("USERNAME");
		if(searchText == null)
			searchText = "%";
		Page<WechatLog> page = wechatLogService.findAllByUsernameAndAccountLike(username, searchText, getPageRequest());
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
	
}
