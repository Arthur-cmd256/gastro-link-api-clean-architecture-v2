package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.itemcardapio;

import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IItemCardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeletarItemCardapioUseCaseTest {

    IItemCardapioGateway itemCardapioGateway;

    @BeforeEach
    void setUp() {
        this.itemCardapioGateway = Mockito.mock(IItemCardapioGateway.class);
    }

    @DisplayName("Deletar item cardapio com sucesso")
    @Test
    void testDeletarItemCardapioComSucesso() {
        Long itemCardapioId = 1L;

        DeletarItemCardapioUseCase.create(this.itemCardapioGateway).processar(itemCardapioId);

        verify(this.itemCardapioGateway, times(1)).deletarItemCardapio(anyLong());
    }

}