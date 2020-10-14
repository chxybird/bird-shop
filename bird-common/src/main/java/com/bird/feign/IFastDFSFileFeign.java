//package com.bird.feign;
//
//import com.bird.common.CommonResult;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//
///**
// * @Author lipu
// * @Date 2020/10/14 8:59
// * @Description
// */
//@RestController
//@FeignClient("bird-file")
//@RequestMapping("/fast")
//public interface IFastDFSFileFeign {
//
//    @GetMapping("/getFile")
//    CommonResult getFile(
//            @RequestParam("groupName") String groupName,
//            @RequestParam("fileName") String fileName
//    );
//
//    @GetMapping("/getTracker")
//    CommonResult getTracker();
//
//    @GetMapping("/getStorage")
//    CommonResult getStorage();
//
//    @PostMapping("/upload")
//    CommonResult upload(@RequestParam("file") MultipartFile file);
//
//    @DeleteMapping("/deleteFile")
//    CommonResult deleteFile(
//            @RequestParam("groupName") String groupName,
//            @RequestParam("fileName") String fileName
//    );
//
//}
