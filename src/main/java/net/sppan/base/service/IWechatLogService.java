package net.sppan.base.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import net.sppan.base.entity.WechatLog;
import net.sppan.base.service.support.IBaseService;

/**
 * <p>
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
public interface IWechatLogService extends IBaseService<WechatLog,Integer> {

	public void saveOrUpdate(WechatLog wechatLog);
	/**
	 * 
	 * 根据关键字查询分页
	 * @param searchText
	 * @param pageRequest
	 * @return
	 */
	Page<WechatLog> findAllByUsernameAndAccountLike(String searchText1, String searchText2, PageRequest pageRequest);
	
}
