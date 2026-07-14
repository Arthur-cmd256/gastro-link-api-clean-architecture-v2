package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.itemcardapio;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.ItemCardapio;
import br.com.fiap.gastro_link_api_clean_architecture.core.factory.ItemCardapioFactory;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IItemCardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class BuscarItensCardapioPorRestauranteUseCaseTest {

    IItemCardapioGateway itemCardapioGateway;

    @BeforeEach
    void setUp() {
        this.itemCardapioGateway = Mockito.mock(IItemCardapioGateway.class);
    }

    @Test
    void buscarItensCardapioPorRestauranteComSucesso() {
        List<ItemCardapio> itensCardapioPorRestaurante = new ArrayList<>();
        Long idRestaurante = 25L;
        itensCardapioPorRestaurante.add(ItemCardapioFactory.criarItemCardapioComIdRestaurante(idRestaurante));
        when(this.itemCardapioGateway.buscarItensCardapioPorRestauranteId(anyLong())).thenReturn(itensCardapioPorRestaurante);

        List<ItemCardapio> itensCardapioBuscados = BuscarItensCardapioPorRestauranteUseCase
                .criar(itemCardapioGateway)
                .processar(idRestaurante);

        verify(this.itemCardapioGateway, times(1)).buscarItensCardapioPorRestauranteId(anyLong());
        for (int i = 0; i < itensCardapioBuscados.size(); i++) {
            assertEquals(itensCardapioPorRestaurante.get(i).getNome(), itensCardapioBuscados.get(i).getNome());
            assertEquals(itensCardapioPorRestaurante.get(i).getNomeRestaurante(), itensCardapioBuscados.get(i).getNomeRestaurante());
        }
    }

}