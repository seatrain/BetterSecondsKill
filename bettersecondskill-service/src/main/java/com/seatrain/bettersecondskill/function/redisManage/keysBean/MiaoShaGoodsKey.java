package com.seatrain.bettersecondskill.function.redisManage.keysBean;

import com.seatrain.bettersecondskill.function.redisManage.BasePrefix;

public class MiaoShaGoodsKey extends BasePrefix {

  private static final String GOODS_INFO_PREFIX = "gi:";
  private static final String GOODS_LIST_PREFIX = "gl:";

  private static final int GOODS_EXPIRES = 60;

  public MiaoShaGoodsKey(String prefix, int expireSeconds) {
    super(prefix, expireSeconds);
  }

  public static MiaoShaGoodsKey goodsInfo() {
    return new MiaoShaGoodsKey(GOODS_INFO_PREFIX, GOODS_EXPIRES);
  }

  public static MiaoShaGoodsKey goodsList() {
    return new MiaoShaGoodsKey(GOODS_LIST_PREFIX, GOODS_EXPIRES);
  }
}
