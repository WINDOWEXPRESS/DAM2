package com.proyectodi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectodi.entity.ZChen;

/*  Utilizamos la interfaz JpaRepository proporcionada por Spring Data JPA. 
    Esta interfaz ya tiene implementadas las operaciones comunes de un repositorio, 
    como guardar, eliminar, buscar, etc. 
    */
/*  Definimos EmpleadoRepository para que maneje la entidad "Empleados" y 
    su clave primaria es de tipo Integer. */
@Repository
public interface EmpleadoRepository extends JpaRepository<ZChen,Integer>{
    
}
