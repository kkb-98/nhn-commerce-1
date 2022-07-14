package com.nhn.commerce.controller

import com.nhn.commerce.model.Product
import com.nhn.commerce.repository.ProductRepository
import com.nhn.commerce.service.ProductService
import org.apache.ibatis.annotations.Param
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@Controller
class ProductController(
    @Autowired
    private val productRepository: ProductRepository?= null,
    private val productService: ProductService,
) {
    @GetMapping("/product")
    fun getProductList(model: Model): String {
        model.addAttribute("productList", productService.findProductList())
        return "product"
    }

    // TODO (상품 상세 조회 기능 + Exception 처리)


    @GetMapping("/product/{productNo}")
    fun getProduct(@PathVariable productNo: Int,
    model : Model): String {
        if(productRepository!!.findByproductNo(productNo) == null){
            throw Exception("존재하지 않는 회원번호 입니다.")
        }else{
            val product: Product? = productService.findByproductNo(productNo)
            model.addAttribute("product",product)
            return "detail-product"
        }
    }
    // TODO (상품 추가 기능)
    @GetMapping("/create-product")
    fun createPage():String{
        return "create-product"
    }

    @PostMapping("/product/create")
    fun createProduct(@RequestParam productName: String,
                      @RequestParam salePrice: String
    ): String {
        var de:String = "defaultValue"
        var se:Int = 1500
        if (productName != null) de = productName else de
        if (salePrice != null) se = salePrice as Int else se
        var result = productService.createProduct(de, se)
        if(result == "ok"){
            return "redirect:/product"
        }else{
        return "redirect:/product"
        }
    }
    // TODO (상품 수정 기능 + Exception 처리)

@GetMapping("/product/find-product/{productNo}")
fun modifyPage(@PathVariable("productNo")productNo: Int,
model: Model):String{
    val productOne = productService.findByproductNo(productNo)
    println(productOne)
    model.addAttribute("product2",productOne)
    return "update-product"
}
    @PostMapping("/product/modify/{productNo}")
    fun updateProduct(@PathVariable productNo: Int,
                      @RequestParam productName: String,
                      @RequestParam salePrice: Int,
    model: Model):String{
        productService.updateProduct(productNo,productName,salePrice)
        return "redirect:/product"
    }

    // TODO (상품 삭제 기능 + Exception 처리)
    @GetMapping("/product/delete/{productNo}")
    fun deleteProduct(@PathVariable productNo: Int):String{
        productService.deleteProduct(productNo = productNo)
        return "redirect:/product"
    }
}
