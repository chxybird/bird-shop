//package com.bird.feign;
//
//import com.bird.common.CommonResult;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//
///**
// * @Author lipu
// * @Date 2020/10/14 8:54
// * @Description
// */
//@RestController
//@FeignClient("bird-file")
//@RequestMapping("/file")
//public interface IFileFeign {
//
//    @PostMapping("/upload")
//    CommonResult upload(@RequestParam("file") MultipartFile file);
//
//    @GetMapping("/download")
//    ResponseEntity download(@RequestParam("fileName") String fileName);
//
//}
