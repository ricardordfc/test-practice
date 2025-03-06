package br.com.rdfc.test_practice.controller;

import br.com.rdfc.test_practice.controller.vo.FollowUpVo;
import br.com.rdfc.test_practice.dto.FollowUpDto;
import br.com.rdfc.test_practice.service.FollowUpService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/follow-up")
public class FollowUpController {

    private final FollowUpService service;

    public FollowUpController(FollowUpService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void save(@RequestBody FollowUpDto followUpDto) {
        service.save(followUpDto);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public FollowUpVo getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping
    @ResponseBody
    public List<FollowUpVo> getAll() {
        return service.findAll();
    }
}
