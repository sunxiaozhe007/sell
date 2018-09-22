package com.sun.sell.service.impl;

import com.sun.sell.dao.ProductInfoDao;
import com.sun.sell.domain.ProductInfo;
import com.sun.sell.dto.CarDto;
import com.sun.sell.enums.ProductStatusEnum;
import com.sun.sell.enums.ResultEnum;
import com.sun.sell.exception.SellException;
import com.sun.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author sunxiaozhe
 * @time 2018/8/2 14:58
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDao.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save(productInfo);
    }

    @Override
    @Transactional
    public void increateStock(List<CarDto> carDtoList) {
        for (CarDto carDto : carDtoList){
            ProductInfo productInfo = productInfoDao.findById(carDto.getProductId()).get();
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + carDto.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoDao.save(productInfo);
        }


    }

    @Override
    @Transactional
    public void decreateStock(List<CarDto> carDtoList) {
        for (CarDto carDto : carDtoList){
            ProductInfo productInfo = productInfoDao.findById(carDto.getProductId()).get();
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - carDto.getProductQuantity();
            if (result < 0){
                throw  new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoDao.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = productInfoDao.findById(productId).get();
        if (productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());

        return productInfoDao.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {

        ProductInfo productInfo = productInfoDao.findById(productId).get();
        if (productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());

        return productInfoDao.save(productInfo);
    }
}
