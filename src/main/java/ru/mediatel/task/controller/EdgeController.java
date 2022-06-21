package ru.mediatel.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.mediatel.task.model.Edge;
import ru.mediatel.task.service.EdgeService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/edges")
public class EdgeController {
    EdgeService edgeService;

    @Autowired
    public void setEdgeService(EdgeService edgeService) {
        this.edgeService = edgeService;
    }


    @GetMapping("/{id}")
    public Edge getEdgeById(@PathVariable Integer id) {
        return edgeService.findById(id);
    }

    @GetMapping("/all")
    public List<Edge> findAll() {
        return edgeService.findAll();
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Edge saveNewEdge(@RequestParam(name = "first_user_id") Integer firstUserId, @RequestParam(name = "second_user_id") Integer secondUserId) {
        return edgeService.addEdge(firstUserId, secondUserId);
    }

}
