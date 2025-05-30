package org.web2.addisyardsale.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.web2.addisyardsale.repository.WatchListRepository;
import org.web2.addisyardsale.model.WatchList;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/watchlist")
public class WatchListController {
    @Autowired
    private WatchListRepository watchlistRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addToWatchlist(@RequestBody WatchList watchlist) {
        if (watchlistRepository.findByUserIdAndProductId(watchlist.getUserId(), watchlist.getProductId()).isEmpty()) {
            return ResponseEntity.ok(watchlistRepository.save(watchlist));
        }
        return ResponseEntity.badRequest().body("Already in watchlist");
    }

    @Transactional
    @DeleteMapping("/remove")
    public ResponseEntity<?> removeFromWatchlist(@RequestParam Long userId, @RequestParam Long productId) {
        watchlistRepository.deleteByUserIdAndProductId(userId, productId);
        return ResponseEntity.ok("Removed from watchlist");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getWatchlist(@PathVariable Long userId) {
        return ResponseEntity.ok(watchlistRepository.findByUserId(userId));
    }
}
