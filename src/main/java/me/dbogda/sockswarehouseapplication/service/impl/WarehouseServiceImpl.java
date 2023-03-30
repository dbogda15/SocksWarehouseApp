package me.dbogda.sockswarehouseapplication.service.impl;

import me.dbogda.sockswarehouseapplication.model.Socks;
import me.dbogda.sockswarehouseapplication.model.enums.Color;
import me.dbogda.sockswarehouseapplication.model.enums.Size;
import me.dbogda.sockswarehouseapplication.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private final HashMap<Socks, Integer> socksMap = new HashMap<>();

    @Override
    public String addSocksInStock(Socks socks, Integer quantity) {
        if (socks != null) {
            socksMap.put(socks, socksMap.containsKey(socks) ? (socksMap.get(socks) + quantity) : quantity);
            return "Color: " + socks.getColor() + ", size " + socks.getSize() + ", cotton part = " + socks.getCottonPart() + "%, total number = " + socksMap.get(socks);
        }
        return "You tried to add empty info about socks";
    }

    @Override
    public String outputSocks(Socks socks, Integer quantity) {
        if (socksMap.containsKey(socks) && socksMap.get(socks) >= quantity) {
            socksMap.replace(socks, socksMap.get(socks), socksMap.get(socks) - quantity);
            return "Color: " + socks.getColor() + ", size " + socks.getSize() + ", cotton part = " + socks.getCottonPart() + "%, total number = " + socksMap.get(socks);
        }
        return "You cannot enter a quantity less than the total number of socks!";
    }


    @Override
    public HashMap<Socks, Integer> getSocksListWithFilters(Color color, Size size, Integer cottonMin, Integer cottonMax) {

        HashMap <Socks, Integer> sortedSocksMap  = socksMap
                .entrySet()
                .stream()
                .sorted(Comparator.comparingInt(socks -> socks.getKey().getCottonPart()))
                .filter(s -> cottonMax >= s.getKey().getCottonPart() && cottonMin <= s.getKey().getCottonPart()
                )
                .filter(s -> color == s.getKey().getColor() && size==s.getKey().getSize())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
        return sortedSocksMap;
    }
}
