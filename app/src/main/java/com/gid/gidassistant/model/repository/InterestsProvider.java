package com.gid.gidassistant.model.repository;

import com.gid.gidassistant.model.objects.Interest;

import java.util.List;

public interface InterestsProvider {
    List<Interest> getAll();
}
