package cn.com.liandisys.shopmall.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import cn.com.liandisys.shopmall.dao.GoodsDao;
import cn.com.liandisys.shopmall.dao.GoodsTypeDao;
import cn.com.liandisys.shopmall.entity.Goods;
import cn.com.liandisys.shopmall.entity.GoodsType;
import cn.com.liandisys.shopmall.enumerat.IsVipEnum;
import cn.com.liandisys.shopmall.enumerat.SellStatusEnum;
import cn.com.liandisys.shopmall.enumerat.IsDeletedEnum;
import cn.com.liandisys.shopmall.response.form.GoodsTypeResult;

@Service
public class GoodsService extends BaseService{
  
  //顶层id
  private final static String TOPPARENTID = "0";
  
  @Autowired
  private GoodsTypeDao goodsTypeDao;

  @Autowired
  private GoodsDao goodsDao;
  
  /**
   * 加载首页商品列表
   * @return 类别商品列表
   */
  public List<GoodsTypeResult> indexGoodsType(){
    List<GoodsTypeResult> goodsTypeResultList = new ArrayList<GoodsTypeResult>();
    //全部商品
    List<Goods> goodsList = findAllGoods();
    //一级分类
    List<GoodsType> goodsTypeList = findGoodsTypeByLevelAndParentId(TOPPARENTID,1);
    if(Objects.nonNull(goodsTypeList)) {
      for(GoodsType goodsType : goodsTypeList) {
        //设置返回index页面info
        GoodsTypeResult goodsTypeResult = new GoodsTypeResult();
        goodsTypeResult.setId(goodsType.getId());
        goodsTypeResult.setName(goodsType.getName());
        goodsTypeResult.setLevel(goodsType.getLevel());
        goodsTypeResult.setParentId(goodsType.getParentId());
        
        List<GoodsType> goodsTypeChildrenList = findGoodsTypeByLevelAndParentId(goodsType.getId());
        List<GoodsTypeResult> childrenGoodsTypeResult= setChildrenGoodsTypeResult(goodsTypeChildrenList,goodsList);
        goodsTypeResult.setGoodsTypeList(childrenGoodsTypeResult);
        
        goodsTypeResultList.add(goodsTypeResult);
      }
    }
    return goodsTypeResultList;
  }

  /**
   * 根据商品id获取商品信息
   * @param goodsId 商品id
   * @return 商品信息
   */
  public Goods findGoodsById(String goodsId) {
    Optional<Goods> optional = goodsDao.findById(goodsId);
    if(optional.isPresent()) {
      return optional.get();
    }
    return null;
  }
  
  /**
   * 判断商品是否是vip商品
   * @param goods 商品信息
   * @return
   */
  public boolean isVipGoods(Goods goods) {
    String goodsTypeId = goods.getGoodsTypeId();
    Optional<GoodsType> optional =  goodsTypeDao.findById(goodsTypeId);
    if(optional.isPresent()) {
      GoodsType goodstype = optional.get();
      if(IsVipEnum.VIP.getEnumValue() == goodstype.getIsVip()) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * 根据商品类别id 获取商品类别
   * @param goodsTypeId 商品类别id
   * @return
   */
  public GoodsType findGoodsTypeById(String goodsTypeId) {
    Optional<GoodsType> optional =  goodsTypeDao.findById(goodsTypeId);
    if(optional.isPresent()) {
      return optional.get();
    }
    return null;
  }
  
  /**
   * 
   * @param parentId 父id
   * @param level 层级
   * @return
   */
  public List<GoodsType> findGoodsTypeByLevelAndParentId(String parentId,Integer... level){
    GoodsType goodsTypeFilter = new GoodsType();
    if(Objects.nonNull(level) && level.length>0) {
      goodsTypeFilter.setLevel(level[0]);
    }
    goodsTypeFilter.setParentId(parentId);
    goodsTypeFilter.setIsDeleted(IsDeletedEnum.NOTDEL.getEnumValue());
    Example<GoodsType> example = Example.of(goodsTypeFilter);
    return goodsTypeDao.findAll(example);
  }
  
  public List<Goods> findAllGoods(){
    Goods goodsFilter = new Goods();
    goodsFilter.setSellStatus(SellStatusEnum.UP.getEnumValue());
    Example<Goods> example = Example.of(goodsFilter);
    return goodsDao.findAll(example);
  }
  
  public List<Goods> findGoodsByLevelAndParentId(String goodsTypeId){
    Goods goodsFilter = new Goods();
    goodsFilter.setGoodsTypeId(goodsTypeId);
    goodsFilter.setSellStatus(SellStatusEnum.UP.getEnumValue());
    Example<Goods> example = Example.of(goodsFilter);
    return goodsDao.findAll(example);
  }

  public Goods insertGoods() {
    Goods goods = new Goods();
    goods.setId(getUUID());
    return goodsDao.save(goods);
  }
  
  public GoodsType insertGoodsType() {
    GoodsType goodsType = new GoodsType();
    goodsType.setId(getUUID());
    return goodsTypeDao.save(goodsType);
  }
  
  private List<GoodsTypeResult> setChildrenGoodsTypeResult(List<GoodsType> goodsTypeList,List<Goods> goodsList){
    if(Objects.nonNull(goodsTypeList)) {
      List<GoodsTypeResult> goodsTypeResultList = new ArrayList<>();
      for(GoodsType goodsType : goodsTypeList) {
        GoodsTypeResult goodsTypeResult = new GoodsTypeResult();
        goodsTypeResult.setId(goodsType.getId());
        goodsTypeResult.setName(goodsType.getName());
        goodsTypeResult.setLevel(goodsType.getLevel());
        goodsTypeResult.setParentId(goodsType.getParentId());
        //获取商品
        List<Goods> curGoodsList = goodsList.stream().filter(obj -> obj.getGoodsTypeId().endsWith(goodsType.getId())).collect(Collectors.toList());
        goodsTypeResult.setGoodsList(curGoodsList);
        goodsTypeResultList.add(goodsTypeResult);
      }
      return goodsTypeResultList;
    }
    return null;
  }
}
