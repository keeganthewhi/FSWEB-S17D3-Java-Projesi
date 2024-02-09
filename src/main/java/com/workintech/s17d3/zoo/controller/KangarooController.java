package com.workintech.s17d3.zoo.controller;

import com.workintech.s17d3.zoo.entity.Kangaroo;
import com.workintech.s17d3.zoo.exceptions.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos = new HashMap<>(){{
        put(1, new Kangaroo(1, "Münir", 175, 54, "M", true));
        put(2, new Kangaroo(2, "Ayşe", 196, 43, "F", false));
        put(3, new Kangaroo(3, "Arda", 145, 74, "M", true));
    }};

    @GetMapping("/workintech/kangaroos")
    public ArrayList<Kangaroo> getAll() {
        if(kangaroos.isEmpty())
            throw new ZeroUserException("Hiçbir kanguru kaydı bulunamadı.");

        return new ArrayList<>(kangaroos.values());
    }

    @GetMapping("/workintech/kangaroos/{id}")
    public Kangaroo getById(@PathVariable("id") Integer id){
        if(kangaroos.get(id) == null)
            throw new UserNotFoundException(id + " id'li kanguru bulunamadı.");

        return kangaroos.get(id);
    }

    @PostMapping("/workintech/kangaroos")
    public void addKangaroo(@RequestBody Kangaroo k){
        if(kangaroos.containsKey(k.getId()))
            throw new CouldNotAddException(k.getId() + " olduğundan dolayı yeni kanguru eklenemedi.");

        kangaroos.put(k.getId(), k);
    }

    @PutMapping("/workintech/kangaroos/{id}")
    public void updateKangaroo(@PathVariable("id") Integer id, @RequestBody Kangaroo k){

        if(k.getName().isEmpty() || k.getGender().isEmpty() || !kangaroos.containsKey(k.getId()))
            throw new CouldNotUpdateException("Güncelleme yapılamadı");

        kangaroos.get(id).setName(k.getName());
        kangaroos.get(id).setGender(k.getGender());
        kangaroos.get(id).setWeight(k.getWeight());
        kangaroos.get(id).setHeight(k.getHeight());
        kangaroos.get(id).setAggressive(k.isAggressive());
    }

    @DeleteMapping("/workintech/kangaroos/{id}")
    public void deleteKangaroo(@PathVariable("id") Integer id){
        if(!kangaroos.containsKey(id))
            throw new CouldNotDeleteException(id + " listede olmadığından dolayı silinemedi.");
        kangaroos.remove(id);
    }

}
