package com.kj.product;

import com.kj.product.dto.ProductInputDto;
import com.kj.product.dto.ProductUpdateDto;
import com.kj.product.entity.Product;
import com.kj.productCategory.ProductCategoryService;
import com.kj.productCategory.dto.ProductCategoryfindDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;
// ================상품 등록 관련========================
    @GetMapping("/insert")
    public String InsertProduct(
            Model model
    ) {
        int result = productService.findByMaxProductId();
        List<ProductCategoryfindDto> productCategories =productCategoryService.findAllProductCategory();
        log.info("result ===>> {}", result);
        model.addAttribute("productMax", result);
        model.addAttribute("productCategory",productCategories);
        return "/product/insert";
    }

    @PostMapping("/insert")
    public String InsertProductProcess(
            @ModelAttribute ProductInputDto productInputDto
    ) throws IOException {
        log.info("insertProdudtProcess===>> {} ", productInputDto);
        productService.insertProduct(productInputDto);
        return "redirect:/product/insert";
    }

    @PostMapping("/detailimage/{no}")
    @ResponseBody
    public Map<String, Object> ckEditorDetailImage(
            @RequestParam("upload") MultipartFile productDetailImage,
            @PathVariable int no
    ) {
        Map<String, Object> data = new HashMap<>();
        try{
            String s3Url = productService.S3UploadProductDetailImage(productDetailImage, no);
            data.put("uploaded", true);
            data.put("url", s3Url);
            return data;
        }catch (IOException e){
            data.put("uploaded", false);
            return data;
        }
    }
    // ================상품 업데이트 관련========================

    @GetMapping("/update/{no}")
    public String updateProduct(
            Model model,
            @PathVariable int no
    ){
        log.info("번호번호번호 ===>>>>> {}" , no);
        ProductUpdateDto productUpdateDto = productService.findByProductId(no);
        model.addAttribute("productUpdateDto", productUpdateDto);
        model.addAttribute("list",productUpdateDto.getProductTags());
//        return "product/findlist";
        return "product/update";
    }

    @PostMapping("/update/{no}")
    public String updateProductProcess(
            @PathVariable int no,
            @ModelAttribute ProductInputDto productInputDto
    ){
        log.info("no ===>>> {}", no);
        log.info("productInputDto ===>>> {}", productInputDto);
        return "redirect:/product/update" + no;
    }


    @GetMapping("/findlist")
    public String findListProduct(){
        return "/product/findlist";
    }


}