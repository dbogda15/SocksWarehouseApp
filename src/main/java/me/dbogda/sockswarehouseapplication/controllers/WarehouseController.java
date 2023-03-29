package me.dbogda.sockswarehouseapplication.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import me.dbogda.sockswarehouseapplication.model.Socks;
import me.dbogda.sockswarehouseapplication.model.enums.Color;
import me.dbogda.sockswarehouseapplication.model.enums.Size;
import me.dbogda.sockswarehouseapplication.service.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping("/input")
    @Operation(summary = "Here you can enter information about receiving socks to the warehouse.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Managed to add a supply of socks."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The request parameters are missing or have an incorrect format."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "An error occurred that does not depend on the requester."
            )
    })
    public ResponseEntity<String> inputSocks(@RequestBody Socks socks, @RequestParam Integer quantity) {
        if (socks == null) {
            return ResponseEntity.notFound().build();
        }
        String socksInfo = warehouseService.addSocksInStock(socks, quantity);
        return ResponseEntity.ok(socksInfo);
    }

    @GetMapping("/print")
    @Operation(
            summary = "Here you can print about the stock of socks in the web application",
            description = "You have to put MIN >= 0 and MAX <= 100"
    )

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The request is completed, the result is in the response body."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The request parameters are missing or have an incorrect format."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "An error occurred that does not depend on the requester."
            )
    })
    public ResponseEntity<HashMap<Socks, Integer>> getSocksListWithFilters(@RequestParam Color color, @RequestParam Size size, @RequestParam Integer cottonMin, @RequestParam Integer cottonMax) {
        HashMap<Socks, Integer> socksList = warehouseService.getSocksListWithFilters(color, size, cottonMin, cottonMax);
        if (socksList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(socksList);
    }

    @PutMapping("/output")
    @Operation(summary = "Here you can enter the information about unloading socks from the warehouse")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The socks were moved from the warehouse."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The product is not in stock in the required quantity or the request parameters have an incorrect format."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "An error occurred that does not depend on the requester."
            )
    })
    public ResponseEntity<String> outputSocks(@RequestBody Socks socks, @RequestParam Integer quantity) {
        if (socks == null) {
            return ResponseEntity.notFound().build();
        }
        String socksInfo = warehouseService.outputSocks(socks, quantity);
        return ResponseEntity.ok(socksInfo);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Here you can enter information about socks to be destroyed.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The request is fulfilled, the product is written off from the warehouse"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The request parameters are missing or have an incorrect format."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "An error occurred that does not depend on the requester."
            )
    })
    public ResponseEntity<String> writeOffSocks(@RequestBody Socks socks, @RequestParam Integer quantity) {
        if (socks == null) {
            return ResponseEntity.notFound().build();
        }
        String socksInfo = warehouseService.outputSocks(socks, quantity);
        return ResponseEntity.ok(socksInfo);
    }
}
