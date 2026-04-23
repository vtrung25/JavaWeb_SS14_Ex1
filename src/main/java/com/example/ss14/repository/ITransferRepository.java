package com.example.ss14.repository;

public interface ITransferRepository {
    void banking(Long senderId, Long receiverId, Double amount);
}
