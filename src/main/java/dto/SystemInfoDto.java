package dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class SystemInfoDto implements Serializable, Dto {

    private List<CompleteOrderDto> orderDtoList;

    private Integer availableDriversCount;

    private Integer unavailableDriversCount;

    private Integer availablePlainsCount;

    private Integer unavailablePlainsCount;

    private Integer availableBoatsCount;

    private Integer unavailableBoatsCount;

    private Integer brokenPlainsCount;

    private Integer brokenBoatsCount;


}
