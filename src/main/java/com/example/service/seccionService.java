package com.example.service;

import com.example.entities.seccion;

public interface seccionService {
   seccion saveSeccion(seccion s);
   Iterable<seccion> listAllSeccion();
   void deleteSeccion(int id);
   Iterable<seccion> listarSeccionxCiclo(String ciclo);
   seccion GetSeccion(int cseccion);
   Iterable<seccion> listarSeccionIgual(int num);
   Iterable<seccion> listarSeccionMenor(int num);
}
