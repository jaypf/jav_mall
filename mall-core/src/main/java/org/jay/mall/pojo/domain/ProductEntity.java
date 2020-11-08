package org.jay.mall.pojo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 商品表
    */
@ApiModel(value="org-jay-mall-pojo-domain-ProductEntity")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity implements Serializable {
    /**
    * 商品id
    */
    @ApiModelProperty(value="商品id")
    private Integer goodsId;

    /**
    * 分类id
    */
    @ApiModelProperty(value="分类id")
    private Integer catId;

    /**
    * 扩展分类id
    */
    @ApiModelProperty(value="扩展分类id")
    private Integer extendCatId;

    /**
    * 商品编号
    */
    @ApiModelProperty(value="商品编号")
    private String goodsSn;

    /**
    * 商品名称
    */
    @ApiModelProperty(value="商品名称")
    private String goodsName;

    /**
    * 点击数
    */
    @ApiModelProperty(value="点击数")
    private Integer clickCount;

    /**
    * 品牌id
    */
    @ApiModelProperty(value="品牌id")
    private Short brandId;

    /**
    * 库存数量
    */
    @ApiModelProperty(value="库存数量")
    private Short storeCount;

    /**
    * 商品评论数
    */
    @ApiModelProperty(value="商品评论数")
    private Short commentCount;

    /**
    * 商品重量克为单位
    */
    @ApiModelProperty(value="商品重量克为单位")
    private Integer weight;

    /**
    * 市场价
    */
    @ApiModelProperty(value="市场价")
    private BigDecimal marketPrice;

    /**
    * 本店价
    */
    @ApiModelProperty(value="本店价")
    private BigDecimal shopPrice;

    /**
    * 商品成本价
    */
    @ApiModelProperty(value="商品成本价")
    private BigDecimal costPrice;

    /**
    * 商品关键词
    */
    @ApiModelProperty(value="商品关键词")
    private String keywords;

    /**
    * 商品简单描述
    */
    @ApiModelProperty(value="商品简单描述")
    private String goodsRemark;

    /**
    * 商品详细描述
    */
    @ApiModelProperty(value="商品详细描述")
    private String goodsContent;

    /**
    * 商品上传原始图
    */
    @ApiModelProperty(value="商品上传原始图")
    private String originalImg;

    /**
    * 是否为实物
    */
    @ApiModelProperty(value="是否为实物")
    private Byte isReal;

    /**
    * 是否上架
    */
    @ApiModelProperty(value="是否上架")
    private Byte isOnSale;

    /**
    * 是否包邮0否1是
    */
    @ApiModelProperty(value="是否包邮0否1是")
    private Byte isFreeShipping;

    /**
    * 商品上架时间
    */
    @ApiModelProperty(value="商品上架时间")
    private Integer onTime;

    /**
    * 商品排序
    */
    @ApiModelProperty(value="商品排序")
    private Short sort;

    /**
    * 是否推荐
    */
    @ApiModelProperty(value="是否推荐")
    private Byte isRecommend;

    /**
    * 是否新品
    */
    @ApiModelProperty(value="是否新品")
    private Byte isNew;

    /**
    * 是否热卖
    */
    @ApiModelProperty(value="是否热卖")
    private Boolean isHot;

    /**
    * 最后更新时间
    */
    @ApiModelProperty(value="最后更新时间")
    private Integer lastUpdate;

    /**
    * 商品所属类型id，取值表goods_type的cat_id
    */
    @ApiModelProperty(value="商品所属类型id，取值表goods_type的cat_id")
    private Short goodsType;

    /**
    * 商品规格类型，取值表goods_type的cat_id
    */
    @ApiModelProperty(value="商品规格类型，取值表goods_type的cat_id")
    private Short specType;

    /**
    * 购买商品赠送积分
    */
    @ApiModelProperty(value="购买商品赠送积分")
    private Integer giveIntegral;

    /**
    * 积分兑换：0不参与积分兑换，积分和现金的兑换比例见后台配置
    */
    @ApiModelProperty(value="积分兑换：0不参与积分兑换，积分和现金的兑换比例见后台配置")
    private Integer exchangeIntegral;

    /**
    * 供货商ID
    */
    @ApiModelProperty(value="供货商ID")
    private Short suppliersId;

    /**
    * 商品销量
    */
    @ApiModelProperty(value="商品销量")
    private Integer salesSum;

    /**
    * 0 普通订单,1 限时抢购, 2 团购 , 3 促销优惠
    */
    @ApiModelProperty(value="0 普通订单,1 限时抢购, 2 团购 , 3 促销优惠")
    private Boolean promType;

    /**
    * 优惠活动id
    */
    @ApiModelProperty(value="优惠活动id")
    private Integer promId;

    /**
    * 佣金用于分销分成
    */
    @ApiModelProperty(value="佣金用于分销分成")
    private BigDecimal commission;

    /**
    * SPU
    */
    @ApiModelProperty(value="SPU")
    private String spu;

    /**
    * SKU
    */
    @ApiModelProperty(value="SKU")
    private String sku;

    /**
    * 配送物流shipping_area_id,以逗号分隔
    */
    @ApiModelProperty(value="配送物流shipping_area_id,以逗号分隔")
    private String shippingAreaIds;

    private static final long serialVersionUID = 1L;
}