package com.cqrs.cqrs.example.employepoc.command.domain;

import com.cqrs.cqrs.example.employepoc.command.commands.CreateHardwareCommand;
import com.cqrs.cqrs.example.employepoc.command.commands.DeleteHardwareCommand;
import com.cqrs.cqrs.example.employepoc.command.commands.UpdateHardwareCommand;
import com.cqrs.cqrs.example.employepoc.command.events.HardwareCreatedEvent;
import com.cqrs.cqrs.example.employepoc.command.events.HardwareDeletedEvent;
import com.cqrs.cqrs.example.employepoc.command.events.HardwareUpdatedEvent;
import com.hydatis.cqrsref.domain.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class HardwareAggregate extends AggregateRoot {
        private String id;


        public HardwareAggregate(CreateHardwareCommand command) {
            raiseEvent(HardwareCreatedEvent.builder()
                    .identifier(command.getId())
                    .hardware(command.getHardware())
                    .build());
        }

        public void apply(HardwareUpdatedEvent event) {
            this.id = event.getIdentifier();
        }

        public HardwareAggregate(UpdateHardwareCommand command) {
            raiseEvent(HardwareUpdatedEvent.builder()
                    .identifier(command.getId())
                    .hardware(command.getHardware())
                    .build());
        }

        public void apply(HardwareDeletedEvent event) {
            this.id = event.getIdentifier();
        }

        public HardwareAggregate(DeleteHardwareCommand command) {
            raiseEvent(HardwareDeletedEvent.builder()
                    .identifier(command.getId())
                    .hardware(command.getHardware())
                    .build());
        }

        public void apply(HardwareCreatedEvent event) {
            this.id = event.getIdentifier();
        }


    }


