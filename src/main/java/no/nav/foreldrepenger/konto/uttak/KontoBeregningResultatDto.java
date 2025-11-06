package no.nav.foreldrepenger.konto.uttak;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public record KontoBeregningResultatDto(@JsonProperty("80") @NotNull KontoBeregningDto åtti,
                                        @JsonProperty("100") @NotNull KontoBeregningDto hundre) {

}
