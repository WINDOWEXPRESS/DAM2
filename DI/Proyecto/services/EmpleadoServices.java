package com.proyectodi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectodi.entity.ZChen;
import com.proyectodi.repository.EmpleadoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpleadoServices {
/*  @Autowired se utiliza para inyectar automáticamente una instancia de Empleado Repository en el controlador, 
    lo que nos permite acceder a las operaciones del repositorio.
    */
    @Autowired
    private EmpleadoRepository personaRepository;

    public ZChen crearEmpleado(ZChen empleado){//puede ser void
        return personaRepository.save(empleado);
    }

    public Optional<ZChen> editarEmpleado (Integer id,ZChen empleado){
        Optional<ZChen> empleadoObtenido = personaRepository.findById(id);
        if(empleadoObtenido.isPresent()){
            empleadoObtenido.get().setNombre_apellidos(empleado.getNombre_apellidos());
            empleadoObtenido.get().setPuesto(empleado.getPuesto());
            empleadoObtenido.get().setUbicacion(empleado.getUbicacion());
            empleadoObtenido.get().setSueldo(empleado.getSueldo());
            personaRepository.save(empleadoObtenido.get());
        }
        return empleadoObtenido;
    }

    public Optional<ZChen> buscarEmpleado (Integer id){
        return personaRepository.findById(id);
    }
    
    public boolean borrarEmpleado(Integer id){
        if(personaRepository.findById(id).isPresent()){
            personaRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
            
    }

    public void borrarTodoEmpleado(){

        personaRepository.deleteAll();
            
    }

    public boolean aumentarSueldoEmpleado(Integer id,Float aumento){
        if(personaRepository.findById(id).isPresent()){
            personaRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
            
    }



}
