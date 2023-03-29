package me.dbogda.sockswarehouseapplication.service.impl;

import me.dbogda.sockswarehouseapplication.model.Socks;
import me.dbogda.sockswarehouseapplication.model.enums.Color;
import me.dbogda.sockswarehouseapplication.model.enums.Size;
import me.dbogda.sockswarehouseapplication.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private final HashMap<Socks, Integer> socksList = new HashMap<>();

    @Override
    public String addSocksInStock(Socks socks, Integer quantity) {
        if (socks != null) {
            socksList.put(socks, socksList.containsKey(socks) ? (socksList.get(socks) + quantity) : quantity);
            return "Color: " + socks.getColor() + ", size " + socks.getSize() + ", cotton part = " + socks.getCottonPart() + "%, total number = " + socksList.get(socks);
        }
        return "You tried to add empty info about socks";
    }

    @Override
    public String outputSocks(Socks socks, Integer quantity) {
        if (socksList.containsKey(socks) && socksList.get(socks) >= quantity) {
            socksList.replace(socks, socksList.get(socks), socksList.get(socks) - quantity);
            return "Color: " + socks.getColor() + ", size " + socks.getSize() + ", cotton part = " + socks.getCottonPart() + "%, total number = " + socksList.get(socks);
        }
        return "You cannot enter a quantity less than the total number of socks!";
    }


    @Override
    public HashMap<Socks, Integer> getSocksListWithFilters(Color color, Size size, Integer cottonMin, Integer cottonMax) {

        HashMap <Socks, Integer> sortedMap  = socksList
                .entrySet()
                .stream()
                .sorted((socks1, socks2) -> {
                    if (socks1.getKey().getCottonPart() > socks2.getKey().getCottonPart()) {
                        return 1;
                    } else if (socks1.getKey().getCottonPart() < socks2.getKey().getCottonPart()) {
                        return -1;
                    }
                    return 0;
                })
                .filter(s -> cottonMax >= s.getKey().getCottonPart() && cottonMin <= s.getKey().getCottonPart()
                )
                .filter(s -> color == s.getKey().getColor() && size==s.getKey().getSize())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));

        return sortedMap;
    }
}
