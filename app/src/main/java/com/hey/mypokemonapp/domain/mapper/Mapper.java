package com.hey.mypokemonapp.domain.mapper;

import com.hey.mypokemonapp.domain.model.detail.abilities.AbilitiesMain;
import com.hey.mypokemonapp.domain.model.detail.abilities.Ability;
import com.hey.mypokemonapp.domain.model.detail.base.BaseDetailItem;
import com.hey.mypokemonapp.domain.model.detail.move.MovesMain;
import com.hey.mypokemonapp.domain.model.detail.type.TypesMain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static ArrayList<BaseDetailItem> mapAbilitiesMainToBase(List<AbilitiesMain> abilitiesMains) {
        return abilitiesMains.stream()
                .map(a -> a.ability)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<BaseDetailItem> mapMovesMainToBase(List<MovesMain> movesMains) {
        return movesMains.stream()
                .map(a -> a.move)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<BaseDetailItem> mapTypesMainToBase(List<TypesMain> typesMains) {
        return typesMains.stream()
                .map(a -> a.type)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
