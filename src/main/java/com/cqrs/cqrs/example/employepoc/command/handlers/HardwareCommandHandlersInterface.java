package com.cqrs.cqrs.example.employepoc.command.handlers;

import com.cqrs.cqrs.example.employepoc.command.commands.CreateHardwareCommand;
import com.cqrs.cqrs.example.employepoc.command.commands.DeleteHardwareCommand;
import com.cqrs.cqrs.example.employepoc.command.commands.UpdateHardwareCommand;

public interface HardwareCommandHandlersInterface {
    void handle (CreateHardwareCommand createHardwareCommand);
    void handle (UpdateHardwareCommand updateHardwareCommand);
    void handle (DeleteHardwareCommand deleteHardwareCommand);

}
