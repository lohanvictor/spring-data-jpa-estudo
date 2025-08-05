package com.bookstore.jpa.domain.book;

import java.util.List;
import java.util.UUID;

public record BookRequestDTO(
        String title,
        UUID publisherId,
        List<UUID> authorsId,
        String reviewComment
) {
}
