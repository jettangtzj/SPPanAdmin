package net.sppan.base.service;


import java.util.Date;
import java.util.List;

import net.sppan.base.entity.StaticsByAccount;
import net.sppan.base.entity.StaticsByDate;
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
	public Page<WechatLog> findAllByUsernameAndAccountContaining(String searchText1, String searchText2, PageRequest pageRequest);
	
	/**
	 * 查询是否重复
	 * @param username
	 * @param account
	 * @param nickName
	 * @param createTime
	 * @return
	 */
	public WechatLog findOneByUsernameAndAccountAndNickNameAndCreateTime(String username, String account, String nickName, Date createTime);

	/**
	 * Group by account查询
	 * @return
	 */
	public List<StaticsByAccount> getCountByAccount(String username);

	/**
	 * group by createtime查询
	 * @return
	 */
	public List<StaticsByDate> getCountByDate(String username);
}
