package com.example.ss14.controller;

import com.example.ss14.repository.ITransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class TransferController {
    private final ITransferRepository transferRepository;

    @GetMapping
    public String formBanking() {
        return "mama_bank";
    }

    @GetMapping("/history")
    public String history() {
        return "history";
    }

    @PostMapping("/banking")
    public String banking(@RequestParam(name = "senderId") Long senderId,
                          @RequestParam(name = "receiverId") Long receiverId,
                          @RequestParam(name = "amount") Double amount) {
        transferRepository.banking(senderId, receiverId, amount);
        return "redirect:/history";
    }
}
