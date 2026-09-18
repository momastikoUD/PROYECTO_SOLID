package Solucion;

// --- Personal del restaurante ---

// REVISAR (4): una sola interfaz para roles que no hacen lo mismo.
/*
    UPDATE: Se identifico que la interfaz no cumple con el 4 principio de SOLID (Interface Segregation)
    por lo tanto se decidio separar cada uno de los metodos de la interfaz empleado en interfaces separadas.
*/

interface Staff {
    void atenderMesa();
}








