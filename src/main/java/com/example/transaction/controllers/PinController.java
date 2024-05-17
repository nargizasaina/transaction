package com.example.transaction.controllers;

import com.example.transaction.models.dtos.AccountPinDto;
import com.example.transaction.models.dtos.BalanceStatusDto;
import com.example.transaction.services.PinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "pin")
@RequestMapping("/api/pin")
public class PinController {

    private final PinService pinService;

    public PinController(PinService pinService) {
        this.pinService = pinService;
    }

    @PostMapping("/check")
    @Operation(summary="check pin for correctness")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Выполнено",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BalanceStatusDto.class))}),
    })
    public BalanceStatusDto checkPin(@RequestBody AccountPinDto accountPinDto) {
        return pinService.checkPin(accountPinDto);
    }
}
