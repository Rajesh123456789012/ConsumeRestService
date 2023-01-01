package com.info.controller;

import com.info.dto.Employee;
import com.info.exception.BusinessException;
import com.info.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class EmpController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${get.all.emps}")
    private String getAllEmps;

    @Value("${save.emp}")
    private String saveEmp;

    @PostMapping("/save")
    public String saveEmployeeDetails(@RequestBody Employee employee) {
       log.info("START:: save emp:{}", employee);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.valueOf("application/json"));
        HttpEntity<Employee> request = new HttpEntity<>(employee, httpHeaders);
        try {
            log.info("save emp url :{}", saveEmp);
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    saveEmp,
                    HttpMethod.POST, request, String.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                log.info("got success response::");
                return responseEntity.getBody();
            }
            // return empService.addEmployee(employee);
        } catch (HttpClientErrorException ex) {
            System.out.println("Http client exception:" + ex.getMessage());
            com.info.exception.Error error = com.info.exception.Error.builder()
                    .code(ErrorCode.APP1404.getCode())
                    .message(ex.getMessage())
                    .build();
            List<com.info.exception.Error> errors = new ArrayList<>();
            errors.add(error);
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error", errors);

        } catch (ResourceAccessException ex) {
            System.out.println("ResourceAccessException:" + ex.getMessage());

        } catch (Exception ex) {

            System.out.println("Exception :" + ex.getMessage());
        }
        return "try again...";
    }

    @GetMapping("/allEmps")
    public Employee[] getAllEmps() {
        log.info("get all emps url::{}",getAllEmps);
        try {
            return restTemplate.getForObject(getAllEmps,
                    Employee[].class);
        } catch (HttpClientErrorException ex) {
            log.error("Http client exception:" + ex.getMessage());
            com.info.exception.Error error = com.info.exception.Error.builder()
                    .code(ErrorCode.APP1404.getCode())
                    .message(ex.getMessage())
                    .build();
            List<com.info.exception.Error> errors = new ArrayList<>();
            errors.add(error);
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error", errors);

        } catch (ResourceAccessException ex) {
            log.error("ResourceAccessException:" + ex.getMessage());
            log.error("Http client exception:" + ex.getMessage());

            com.info.exception.Error error = com.info.exception.Error.builder()
                    .code(ErrorCode.APP1404.getCode())
                    .message(ex.getMessage())
                    .build();
            List<com.info.exception.Error> errors = new ArrayList<>();
            errors.add(error);
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error", errors);

        } catch (Exception ex) {
            log.error("Exception :" + ex.getMessage());
            com.info.exception.Error error = com.info.exception.Error.builder()
                    .code(ErrorCode.APP1404.getCode())
                    .message(ex.getMessage())
                    .build();
            List<com.info.exception.Error> errors = new ArrayList<>();
            errors.add(error);
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error", errors);

        }
    }
}
