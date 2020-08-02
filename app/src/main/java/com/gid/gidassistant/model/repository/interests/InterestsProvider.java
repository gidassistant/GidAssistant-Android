package com.gid.gidassistant.model.repository.interests;

import com.gid.gidassistant.model.entities.Interest;

import java.util.List;


//Базовый интерфейс, который описывает методы получения списка интересов
public interface InterestsProvider {
    List<Interest> getAll();
}
