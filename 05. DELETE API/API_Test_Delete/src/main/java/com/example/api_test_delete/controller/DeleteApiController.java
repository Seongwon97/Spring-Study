package com.example.api_test_delete.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeleteApiController {

    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable String userId, @RequestParam String account) {
        // @RequestParam은 get에서 사용했던 것으로 parameter를 받을 수 있다.

        System.out.println(userId);
        System.out.println(account);

        // 삭제가 완료되면 200이라는 결과가 나온다.(API test상에서!!)
        // 데이터가 원래 없는 상태여도 결과적으로는 그 데이터를 없앤 거와 같기에 200이 나온다.

    }
}
