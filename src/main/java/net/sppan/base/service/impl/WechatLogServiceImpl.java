package net.sppan.base.service.impl;

import java.util.Date;

import net.sppan.base.dao.IWechatLogDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.WechatLog;
import net.sppan.base.service.IWechatLogService;
import net.sppan.base.service.support.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * <p>
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
@Service
public class WechatLogServiceImpl extends BaseServiceImpl<WechatLog, Integer> implements IWechatLogService {

	@Autowired
	private IWechatLogDao wechatLogDao;
	
	@Override
	public IBaseDao<WechatLog, Integer> getBaseDao() {
		return this.wechatLogDao;
	}

	@Override
	public Page<WechatLog> findAllByUsernameAndAccountContaining(String username, String account, PageRequest pageRequest) {
		return wechatLogDao.findAllByUsernameAndAccountContainingOrderByCreateTimeDesc(username, account, pageRequest);
	}

	@Override
	public void saveOrUpdate(WechatLog wechatLog) {
		wechatLog.setCreateTime(new Date());
		save(wechatLog);
	}
	
	@Override
	public WechatLog findOneByUsernameAndAccountAndNickNameAndCreateTime(String username, String account, String nickName, Date createTime) {
		return wechatLogDao.findOneByUsernameAndAccountAndNickNameAndCreateTime(username, account, nickName, createTime);
	}
	
}
