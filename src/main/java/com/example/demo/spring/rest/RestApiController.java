package com.example.demo.spring.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Rest Api Controller
 */
@RestController
@RequestMapping("/api")
public class RestApiController {
    protected static Log log = LogFactory.getLog(RestApiController.class);
    RestApiProcessor restApiProcessor;

    @GetMapping("/people")
    public ResponseEntity<RestResponse<List<RestPerson>>> getPersons(HttpServletRequest request
            , @RequestParam(value="filter", defaultValue="") String filter) {
        try {
            List<RestPerson> people = restApiProcessor.searchPeople(filter);
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.noCache().mustRevalidate().noTransform())
                    .header("Pragma", "no-cache")
                    .header("Expires", "0")
                    .body(new RestResponse<List<RestPerson>>(people));
        } catch (Throwable e){
            log.error(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RestResponse<List<RestPerson>>(new RestMessage(e.getMessage())));
        }
    }
    @PostMapping("/people")
    ResponseEntity<RestResponse<RestPerson>> newPerson(@RequestBody RestPerson restPerson) {
        try {
            restPerson = restApiProcessor.createPerson(restPerson);
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.noCache().mustRevalidate().noTransform())
                    .header("Pragma", "no-cache")
                    .header("Expires", "0")
                    .body(new RestResponse<RestPerson>(restPerson));
        } catch (Throwable e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RestResponse<>(new RestMessage(e.getMessage())));
        }
    }
    @PutMapping("/people/{id}")
    ResponseEntity<RestResponse<RestPerson>> updatePerson(@RequestBody RestPerson restPerson, @PathVariable Long id) {
        try {
            restPerson.setId(id);
            restPerson = restApiProcessor.updatePerson(restPerson);
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.noCache().mustRevalidate().noTransform())
                    .header("Pragma", "no-cache")
                    .header("Expires", "0")
                    .body(new RestResponse<RestPerson>(restPerson));
        } catch (Throwable e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RestResponse<>(new RestMessage(e.getMessage())));
        }
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<RestResponse<RestMessage>> deletePerson(@PathVariable Long id) {
        try {
            restApiProcessor.deletePerson(id);
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.noCache().mustRevalidate().noTransform())
                    .header("Pragma", "no-cache")
                    .header("Expires", "0")
                    .body(new RestResponse<>(new RestMessage("Изтриването на лицето е успешно.")));
        } catch (Throwable e){
            log.error(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RestResponse<>(new RestMessage(e.getMessage())));
        }
    }
    @GetMapping("/salaries/people")
    public ResponseEntity<RestResponse<List<RestPerson>>> getPersonsByYearAndBiggerThan1000Salaries(HttpServletRequest request
            , @RequestParam(value="year", defaultValue="") Integer year) {
        try {
            List<RestPerson> people = restApiProcessor.findPersonsByYearAndBiggerThan1000Salaries(year);
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.noCache().mustRevalidate().noTransform())
                    .header("Pragma", "no-cache")
                    .header("Expires", "0")
                    .body(new RestResponse<List<RestPerson>>(people));
        } catch (Throwable e){
            log.error(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RestResponse<List<RestPerson>>(new RestMessage(e.getMessage())));
        }
    }
    @GetMapping("/salaries/years")
    public ResponseEntity<RestResponse<List<RestYearSum>>> findSalarySumInYearsByMonth(HttpServletRequest request
            , @RequestParam(value="month", defaultValue="") Integer month) {
        try {
            List<RestYearSum> people = restApiProcessor.findSalarySumInYearsByMonth(month);
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.noCache().mustRevalidate().noTransform())
                    .header("Pragma", "no-cache")
                    .header("Expires", "0")
                    .body(new RestResponse<List<RestYearSum>>(people));
        } catch (Throwable e){
            log.error(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RestResponse<List<RestYearSum>>(new RestMessage(e.getMessage())));
        }
    }
    public RestApiProcessor getRestApiProcessor() {
        return restApiProcessor;
    }
    @Resource
    public void setRestApiProcessor(RestApiProcessor restApiProcessor) {
        this.restApiProcessor = restApiProcessor;
    }
}
