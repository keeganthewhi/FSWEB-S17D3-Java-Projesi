package com.workintech.s17d3.zoo.controller;

import com.workintech.s17d3.zoo.entity.Kangaroo;
import com.workintech.s17d3.zoo.entity.Koala;
import com.workintech.s17d3.zoo.exceptions.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class KoalaController {
    private Map<Integer, Koala> koalas = new HashMap<>(){{
        put(1, new Koala(1, "Münir", 75, 18, "M"));
        put(2, new Koala(2, "Aliye", 47, 15, "F"));
        put(3, new Koala(3, "Ahmet", 65, 20, "M"));
    }};

    @GetMapping("/workintech/koalas")
    public ArrayList<Koala> getAll() {
        if(koalas.isEmpty())
            throw new ZeroUserException("Hiçbir kanguru kaydı bulunamadı.");
        return new ArrayList<>(koalas.values());
    }

    @GetMapping("/workintech/koalas/{id}")
    public Koala getById(@PathVariable("id") Integer id){
        if(koalas.get(id) == null)
            throw new UserNotFoundException(id + " id'li kanguru bulunamadı.");
        return koalas.get(id);
    }

    @PostMapping("/workintech/koalas")
    public void addKoala(@RequestBody Koala k){
        if(koalas.containsKey(k.getId()))
            throw new CouldNotAddException(k.getId() + " olduğundan dolayı yeni kanguru eklenemedi.");
        koalas.put(k.getId(), k);
    }

    @PutMapping("/workintech/koalas/{id}")
    public void updateKoala(@PathVariable("id") Integer id, @RequestBody Koala k){

        if(k.getName().isEmpty() || k.getGender().isEmpty() || !koalas.containsKey(k.getId()))
            throw new CouldNotUpdateException("Güncelleme yapılamadı");
        koalas.get(id).setName(k.getName());
        koalas.get(id).setGender(k.getGender());
        koalas.get(id).setWeight(k.getWeight());
        koalas.get(id).setSleepHour(k.getSleepHour());
    }

    @DeleteMapping("/workintech/koalas/{id}")
    public void deleteKoala(@PathVariable("id") Integer id){
        if(!koalas.containsKey(id))
            throw new CouldNotDeleteException(id + " listede olmadığından dolayı silinemedi.");
        koalas.remove(id);
    }


}
