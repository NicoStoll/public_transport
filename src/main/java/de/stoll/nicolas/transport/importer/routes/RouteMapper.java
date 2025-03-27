package de.stoll.nicolas.transport.importer.routes;

import de.stoll.nicolas.transport.data.Route;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RouteMapper {

    @Mapping(target = "routeId", source = "routeId")
    @Mapping(target = "routeShortName", source = "routeShortName")
    @Mapping(target = "routeLongName", source = "routeLongName")
    @Mapping(target = "routeDesc", source = "routeDesc")
    @Mapping(target = "routeType", source = "routeType")
    @Mapping(target = "routeUrl", source = "routeUrl")
    @Mapping(target = "routeColor", source = "routeColor")
    @Mapping(target = "routeTextColor", source = "routeTextColor")
    Route toRoute(RouteDTO routeDTO);
}
