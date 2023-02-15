package com.switch_proj.api.api.chip.controller;

import com.switch_proj.api.api.chip.dto.Chip;
import com.switch_proj.api.api.chip.dto.ChipExchange;
import com.switch_proj.api.api.chip.service.ChipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chips")
public class ChipController {
    private final ChipService chipService;

    @GetMapping
    public ResponseEntity<List<Chip>>findAllChips(){
        List<Chip> chips = chipService.findAllChips();
        return new ResponseEntity<>(chips, HttpStatus.OK);
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ChipExchange> registerChip(@Valid @RequestBody ChipExchange chipExchange){
        chipService.registerChip(chipExchange);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
