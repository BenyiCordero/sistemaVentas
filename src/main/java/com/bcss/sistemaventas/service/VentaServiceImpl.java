package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.*;
import com.bcss.sistemaventas.dto.request.VentaRequest;
import com.bcss.sistemaventas.exception.CancelledSellException;
import com.bcss.sistemaventas.exception.NotFoundException;
import com.bcss.sistemaventas.repository.ClienteRepository;
import com.bcss.sistemaventas.repository.SucursalRepository;
import com.bcss.sistemaventas.repository.TrabajadorRepository;
import com.bcss.sistemaventas.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository repository;
    private final SucursalRepository sucursalRepository;
    private final TrabajadorRepository trabajadorRepository;
    private final ClienteRepository clienteRepository;

    public VentaServiceImpl(VentaRepository repository,
                            ClienteRepository clienteRepository,
                            TrabajadorRepository trabajadorRepository,
                            SucursalRepository sucursalRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.trabajadorRepository = trabajadorRepository;
        this.sucursalRepository = sucursalRepository;
    }

    @Override
    public List<Venta> getAll(EnumEstadoVenta estado) {
        List<Venta> ventas;
        if (estado != null) ventas = repository.findByEstado(estado);
        else ventas = repository.findAll();
        return ventas;
    }


    @Override
    public Venta getById(Integer id) {
        Optional<Venta> venta = repository.findById(id);
        if (venta.isPresent()) return venta.get();
        else throw new NotFoundException("No hay venta con id " + id);
    }

    @Override
    public Venta save(VentaRequest request) {
        Sucursal sucursal = sucursalRepository.findById(request.idSucursal())
                .orElseThrow(() -> new NotFoundException("Sucursal no encontrada"));
        Cliente cliente = clienteRepository.findById(request.idCliente())
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
        Trabajador trabajador = trabajadorRepository.findById(request.idTrabajador())
                .orElseThrow(() -> new NotFoundException("Trabajador no encontrado"));

        Venta venta = Venta.builder()
                .sucursal(sucursal)
                .cliente(cliente)
                .trabajador(trabajador)
                .fechaVenta(LocalDate.now())
                .totalVenta(request.totalVenta())
                .descuento(request.descuento())
                .impuesto(request.impuesto())
                .estado(EnumEstadoVenta.PENDIENTE)
                .notas(request.notas())
                .metodoPago(request.metodoPago())
                .build();

        return repository.save(venta);
    }

    @Override
    public Venta update(Integer id, VentaRequest request) {
        Venta venta = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venta no encontrada"));
        if (request.idSucursal() != null &&
                !request.idSucursal().equals(venta.getSucursal().getIdSucursal())) {
            Sucursal sucursal = sucursalRepository.findById(request.idSucursal())
                    .orElseThrow(() -> new NotFoundException("Sucursal no encontrada"));
            venta.setSucursal(sucursal);
        }
        if (request.idCliente() != null &&
                !request.idCliente().equals(venta.getCliente().getIdCliente())) {

            Cliente cliente = clienteRepository.findById(request.idCliente())
                    .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
            venta.setCliente(cliente);
        }
        if (request.idTrabajador() != null &&
                !request.idTrabajador().equals(venta.getTrabajador().getIdUsuario())) {

            Trabajador trabajador = trabajadorRepository.findById(request.idTrabajador())
                    .orElseThrow(() -> new NotFoundException("Trabajador no encontrado"));
            venta.setTrabajador(trabajador);
        }

        if (request.totalVenta() != null)
            venta.setTotalVenta(request.totalVenta());

        if (request.descuento() != null)
            venta.setDescuento(request.descuento());

        if (request.impuesto() != null)
            venta.setImpuesto(request.impuesto());

        if (request.notas() != null)
            venta.setNotas(request.notas());

        if (request.metodoPago() != null)
            venta.setMetodoPago(request.metodoPago());

        return repository.save(venta);
    }


    @Override
    public void delete(Integer id) {
        Venta venta = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venta no encontrada"));

        if (venta.getEstado() == EnumEstadoVenta.CANCELADA) {
            throw new CancelledSellException("La venta ya está cancelada");
        }

        venta.setEstado(EnumEstadoVenta.CANCELADA);

        repository.save(venta);
    }

    @Override
    public List<Venta> getBySucursal(Integer idSucursal) {
        List<Venta> ventas = repository.findBySucursalIdSucursal(idSucursal);

        if (ventas.isEmpty()) throw new NotFoundException("No hay ventas registradas para la sucursal " + idSucursal);

        return ventas;
    }

    @Override
    public List<Venta> getByCliente(Integer idCliente) {
        List<Venta> ventas = repository.findByClienteIdCliente(idCliente);

        if (ventas.isEmpty()) throw new NotFoundException("No hay ventas registradas para la sucursal " + idCliente);

        return ventas;
    }

    @Override
    public List<Venta> getByTrabajador(Integer idTrabajador) {
        List<Venta> ventas = repository.findByTrabajadorIdUsuario(idTrabajador);

        if (ventas.isEmpty()) throw new NotFoundException("No hay ventas registradas para la sucursal " + idTrabajador);

        return ventas;
    }

    @Override
    public Venta changeStatus(Integer id, EnumEstadoVenta estado) {
        Venta venta = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No hay venta con id " + id));
        venta.setEstado(estado);
        return repository.save(venta);
    }

    @Override
    public Double getTotalVentasMes() {
        return repository.sumTotalMesActual();
    }

}
