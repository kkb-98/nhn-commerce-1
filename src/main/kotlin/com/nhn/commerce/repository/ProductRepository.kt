package com.nhn.commerce.repository

import com.nhn.commerce.model.Product
import org.apache.ibatis.annotations.*
import java.time.LocalDateTime

@Mapper
interface ProductRepository{
//   상품 전체 조회
    @Select("SELECT * FROM product")
    fun findProductList(): List<Product>

    //상품 상세 조회
    @Select("SELECT * FROM product where productNo=#{productNo}")
    fun findByproductNo(productNo: Int): Product?

    //상품 추가
    @Insert("INSERT INTO product(productName, registerYmdt ,salePrice) " + "VALUES (#{productName}, #{registerYmdt}, #{salePrice})")
    fun createProduct(productName:String,registerYmdt:LocalDateTime,salePrice:Int)

    //상품 수정
    @Update("Update product set productName=#{productName}, " + "salePrice=#{salePrice}," + "updateYmdt=#{updateYmdt}" + "where productNo=#{productNo}")
    fun updateProduct(productName: String,
                      salePrice: Int,
                      updateYmdt:LocalDateTime,
                      productNo: Int)

    //상품 삭제
    @Delete("delete from product where productNo=#{productNo}")
    fun deleteProduct(productNo: Int)
}
