package net.sppan.base.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.WechatLog;

@Repository
public interface IWechatLogDao extends IBaseDao<WechatLog, Integer> {

	Page<WechatLog> findAllByUsernameAndAccountLikeOrderByCreateTimeDesc(String searchText1, String searchText2,  Pageable pageable);

}
