package com.nhn.commerce.service

import com.nhn.commerce.model.Product
import com.nhn.commerce.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProductService(

    @Autowired
    private val productRepository: ProductRepository,
) {
    fun findProductList(): List<Product> = productRepository.findProductList()

    fun findByproductNo(productNo: Int): Product? = productRepository.findByproductNo(productNo)

    fun createProduct(name: String,
    price: Int):String {
        var judgePrice: Int = price
        judgePrice.let {
            if(it.isNagetiveNumber()){
                println("양수가 아닌 값이 들어왔습니다.")
                return "error"
            }
        }
        productRepository.createProduct(productName = name,
        registerYmdt = LocalDateTime.now(),
        salePrice = price)
        return "ok"
    }
    fun Int.isNotNagetiveNumber(): Boolean = this >= 0
    fun Int.isNagetiveNumber(): Boolean = !isNotNagetiveNumber()

    fun updateProduct(no: Int, name: String, price: Int){
            productRepository.updateProduct(productName = name, salePrice = price,
            productNo = no, updateYmdt = LocalDateTime.now())
    }

    fun deleteProduct(productNo: Int){
        productRepository.deleteProduct(productNo = productNo)
    }
}
