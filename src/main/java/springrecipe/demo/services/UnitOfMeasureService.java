package springrecipe.demo.services;

import springrecipe.demo.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUnitOfMeasure();
}
