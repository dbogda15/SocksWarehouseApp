package me.dbogda.sockswarehouseapplication.service;

import me.dbogda.sockswarehouseapplication.model.Socks;
import me.dbogda.sockswarehouseapplication.model.enums.Color;
import me.dbogda.sockswarehouseapplication.model.enums.Size;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface WarehouseService {
    String addSocksInStock(Socks socks, Integer quantity);

    String outputSocks(Socks socks, Integer quantity);

    HashMap<Socks, Integer> getSocksListWithFilters(Color color, Size size, Integer cottonMin, Integer cottonMax);
}
