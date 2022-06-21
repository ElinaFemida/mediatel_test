package ru.mediatel.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mediatel.task.exception.ResourceNotFoundException;
import ru.mediatel.task.model.Edge;
import org.springframework.stereotype.Service;
import ru.mediatel.task.repository.EdgeRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EdgeService {
    EdgeRepository edgeRepository;

    @Autowired
    public void setEdgeService(EdgeRepository edgeRepository) {
        this.edgeRepository = edgeRepository;
    }

    public Edge save(Edge edge) {
        return edgeRepository.save(edge);
    }

    public List<Edge> findAll() {
        return edgeRepository.findAll();
    }

    public Edge findById(Integer id) {
        Edge edge = edgeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find edge with id: " + id));
        return edge;
    }

    public Edge addEdge(Integer firstUser, Integer secondUser) {
        Edge edge = new Edge();
        edge.setFirstUser(firstUser);
        edge.setSecondUser(secondUser);
        return save(edge);
    }

}