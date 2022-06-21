package ru.mediatel.task.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mediatel.task.model.Edge;
import ru.mediatel.task.model.User;
import ru.mediatel.task.repository.EdgeRepository;
import ru.mediatel.task.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GraphService {

    UserRepository userRepository;
    EdgeRepository edgeRepository;
    ArrayList<User> vertices = new ArrayList<>();
    ArrayList<ArrayList<Edge>> edges = new ArrayList<>();

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEdgeRepository(EdgeRepository edgeRepository) {
        this.edgeRepository = edgeRepository;
    }


    public GraphService(List<User> vertices) {
        userRepository.findAll().add((User) vertices);
        for (User vertex : vertices) {
            edges.add(new ArrayList<>());
        }
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public int getEdgeCount() {
        return edges.stream().mapToInt(ArrayList::size).sum();
    }

    public int maxEdgeCount(){
        int max = 1;
        for (User vertex : vertices) {
            if (max <= edges.size()){
                max = edges.size();
            }
        }
        return max;
    }

    public int minEdgeCount(){
        int min = vertices.size();
        for (User vertex : vertices) {
            if (min > edges.size()){
                min = edges.size();
            }
        }
        return min;
    }

    public int averageEdgeCount(){
        int average = getEdgeCount() / getVertexCount();
        return average;
    }

    public int addVertex(User vertex) {
        vertices.add(vertex);
        edges.add(new ArrayList<>());
        return getVertexCount() - 1;
    }

    public User vertexAt(int index) {
        return vertices.get(index);
    }

    public int indexOf(User vertex) {
        return vertices.indexOf(vertex);
    }

    public List<User> neighborsOf(int index) {


        return edgeRepository.findEdgeById(index).stream()
                .map(edge -> vertexAt(index))
                .collect(Collectors.toList());
    }

    public List<User> neighborsOf(User vertex) {
        return neighborsOf(indexOf(vertex));
    }

    public Optional<Edge> edgesOf(int index) {
        return edgeRepository.findEdgeById(index);
    }

    public Optional<Edge> edgesOf(User vertex) {
        return edgesOf(indexOf(vertex));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getVertexCount(); i++) {
            sb.append(vertexAt(i));
            sb.append(" -> ");
            sb.append(Arrays.toString(neighborsOf(i).toArray()));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }



}
