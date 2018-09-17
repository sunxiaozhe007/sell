package com.sun.sell.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.sell.enums.ProductStatusEnum;
import com.sun.sell.util.enumutil.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息表
 * @author sunxiaozhe
 * @time 2018/8/2 14:32
 */
@Entity
@DynamicUpdate
@Data
public class ProductInfo {

    @Id
    private String productId;
    //名字
    private String productName;
    //单价
    private BigDecimal productPrice;
    //库存
    private Integer productStock;
    //描述
    private String productDescription;
    //图片地址
    private String productIcon;
    //状态 0：正常 1：下架
    private Integer productStatus = ProductStatusEnum.UP.getCode();
    //类目编号
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }

}
