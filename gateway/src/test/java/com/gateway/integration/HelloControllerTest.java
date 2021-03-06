package com.gateway.integration;

import com.common.util.ResultJson;
import com.gateway.integration.config.IntegrationConfig;
import com.gateway.integration.vo.HelloVO;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @className: HelloControlerTest
 * @description:
 * @author: yanchenyang
 * @date: 2021/5/6
 **/
public class HelloControllerTest extends IntegrationConfig {
    @Test
    void should_send_get_request_and_result_data_correctly() {
        ResultJson<HelloVO> resultJson = get("/test/get", HelloVO.class, ImmutableMap.of());
        assertThatResult(resultJson, "get");
    }

    @Test
    void should_send_post_request_and_result_data_correctly() {
        ResultJson<HelloVO> resultJson = post("/test/post", HelloVO.class, ImmutableMap.of());
        assertThatResult(resultJson, "post");
    }

    @Test
    void should_send_put_request_and_result_data_correctly() {
        ResultJson<HelloVO> resultJson = put("/test/put", HelloVO.class, ImmutableMap.of());
        assertThatResult(resultJson, "put");
    }

    @Test
    void should_send_delete_request_and_result_data_correctly() {
        ResultJson<HelloVO> resultJson = delete("/test/delete", HelloVO.class, ImmutableMap.of());
        assertThatResult(resultJson, "delete");
    }

    private void assertThatResult(ResultJson<HelloVO> resultJson, String result) {
        assert resultJson != null;
        assertThat(resultJson.getStatus()).isEqualTo(200);
        assertThat(resultJson.getMessage()).isEqualTo("操作成功");
        assertThat(resultJson.getData().getResult()).isEqualTo(result);
    }
}

@RestController
@RequestMapping("/test")
class HelloController {

    @GetMapping("/get")
    public ResultJson<HelloVO> getRequest() {
        return ResultJson.success(new HelloVO("get"));
    }

    @PostMapping("/post")
    public ResultJson<HelloVO> postRequest() {
        return ResultJson.success(new HelloVO("post"));
    }

    @PutMapping("/put")
    public ResultJson<HelloVO> putRequest() {
        return ResultJson.success(new HelloVO("put"));
    }

    @DeleteMapping("/delete")
    public ResultJson<HelloVO> deleteRequest() {
        return ResultJson.success(new HelloVO("delete"));
    }
}