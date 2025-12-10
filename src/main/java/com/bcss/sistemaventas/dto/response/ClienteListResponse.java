package com.bcss.sistemaventas.dto.response;

import java.util.List;

public record ClienteListResponse(
    List<ClienteBasicResponse> clientes
) {}