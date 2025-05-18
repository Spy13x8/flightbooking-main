package com.flightBooking.flightBooking.controller;

import com.flightBooking.flightBooking.services.UserTickets;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    private Map<String, UserTickets> ticketMap = new HashMap<>();

    @PostMapping("/book")
    public String bookTicket(@RequestBody UserTickets ticket){
        ticketMap.put(ticket.getTicketId(), ticket);
        return "Ticket booked!";
    }

    @GetMapping("/TicketId/{ticketId}")
    public UserTickets getTicketDetails(@PathVariable String ticketId){
        return ticketMap.getOrDefault(ticketId, null);
    }

    @DeleteMapping("/cancel/{ticketId}")
    public String cancelTicket(@PathVariable String ticketId){
        if(ticketMap.containsKey(ticketId)){
            ticketMap.remove(ticketId);
            return "Ticket cancelled!";
        }
        return "Ticket not found!";
    }
}

