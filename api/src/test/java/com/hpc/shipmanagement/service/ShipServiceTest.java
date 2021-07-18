package com.hpc.shipmanagement.service;

import com.hpc.shipmanagement.entity.Ship;
import com.hpc.shipmanagement.exception.NotFoundException;
import com.hpc.shipmanagement.repository.ShipRespository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShipServiceTest {

    @InjectMocks
    private ShipServiceImpl shipService;

    @Mock
    private ShipRespository shipRespository;

    @Test
    public void should_create_ship(){
        Ship ship = Ship.builder().name("test1")
                .code("aaaa-1111-a1").length(12.2).width(23d).build();

        when(shipRespository.save(ship)).thenReturn(ship);

        Assertions.assertEquals(shipRespository.save(ship),ship);

        Mockito.verify(shipRespository).save(ship);
    }

    @Test
    public void should_update_ship(){
        Ship ship = Ship.builder().id(1).name("test1")
                .code("aaaa-1111-a1").length(12.2).width(23d).build();


        when(shipRespository.findById(1L)).thenReturn(Optional.of(ship));
        Ship ship1 = shipService.findById(1L);

        Ship ship2 = Ship.builder().id(ship1.getId()).name("Ship1").code("aaaa-1111-a1")
                .length(12.2).width(23d).build();
        when(shipRespository.save(ship2)).thenReturn(ship2);

        Assertions.assertEquals(shipRespository.save(ship2),ship2);

        Mockito.verify(shipRespository).save(ship2);
    }

    @Test
    public void should_delete_ship(){
        Ship ship = Ship.builder().id(1).name("test1")
                .code("aaaa-1111-a1").length(12.2).width(23d).build();

        when(shipRespository.findById(1L)).thenReturn(Optional.of(ship));
        Ship ship1 = shipService.findById(1L);
        shipRespository.deleteById(ship1.getId());

        verify(shipRespository).deleteById(ship1.getId());

    }

    @Test
    public void should_fetch_ship() {
        List<Ship> ships = mockShipData();

        assertThat(ships).hasSize(1);
        assertThat(ships.get(0).getName()).isEqualTo("Ship1");
    }


    @Test
    public void should_fetch_ship_byId() throws NotFoundException {
        Ship response = Ship.builder()
                .name("ship1").code("AAAA-1111-A1").length(9.5d).width(5).build();

        when(shipRespository.findById(1L)).thenReturn(Optional.of(response));

        Ship ship = shipService.findById(1L);

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(ship.getName()).isEqualTo("ship1");
            softAssertions.assertThat(ship.getCode()).isEqualTo("AAAA-1111-A1");
            softAssertions.assertThat(ship.getLength()).isEqualTo(9.5d);
            softAssertions.assertThat(ship.getWidth()).isEqualTo(5);
        });
    }

    @Test
    void should_throw_exception_when_id_notFound() {
        doThrow(new NotFoundException("Id not found")).when(shipRespository).findById(1L);

        assertThatThrownBy(() ->
                shipService.findById(1L)).isInstanceOf(NotFoundException.class)
                .hasMessage("Id not found");
    }

    private List<Ship> mockShipData() {
        List<Ship> lst = new ArrayList<>();
        Ship ship = Ship.builder()
                .name("Ship1")
                .code("AAAA-1111-A1")
                .length(10.4)
                .width(10)
                .build();
        lst.add(ship);
        return lst;
    }

}
