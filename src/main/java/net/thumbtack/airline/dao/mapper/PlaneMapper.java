package net.thumbtack.airline.dao.mapper;

import net.thumbtack.airline.model.Plane;

import java.util.List;

public interface PlaneMapper {
    List<Plane> getAll();

    Plane get(String planeName);
}
