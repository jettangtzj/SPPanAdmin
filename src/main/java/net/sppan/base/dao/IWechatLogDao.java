package net.sppan.base.dao;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.WechatLog;

@Repository
public interface IWechatLogDao extends IBaseDao<WechatLog, Integer> {

	public Page<WechatLog> findAllByUsernameAndAccountContainingOrderByCreateTimeDescIdDesc(String searchText1, String searchText2,  Pageable pageable);

	public WechatLog findOneByUsernameAndAccountAndNickNameAndCreateTime(String username, String account, String nickName, Date createTime);



}
