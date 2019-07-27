package net.sppan.base.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

import net.sppan.base.entity.support.BaseEntity;

/**
 * <p>
 * 微信记录表
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
@Entity
@Table(name = "tb_wechatlog")
public class WechatLog extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer id;

	/**
	 * 微信账号
	 */
	private String account;

	/**
	 * 加人者昵称
	 */
	private String nickName;

	/**
	 * 创建日期
	 */
	@JSONField(format = "yyyy-MM-dd")
	private Date createTime;

	private String username;//对应的用户

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
