package br.com.rdfc.test_practice.controller;

import br.com.rdfc.test_practice.controller.vo.SampleVo;
import br.com.rdfc.test_practice.dto.SampleDto;
import br.com.rdfc.test_practice.service.SampleService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sample")
public class SampleController {

    private final SampleService service;

    public SampleController(SampleService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void save(@RequestBody SampleDto sampleDto) {
        service.save(sampleDto);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public SampleVo getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping
    @ResponseBody
    public List<SampleVo> getAll() {
        return service.findAll();
    }
}
