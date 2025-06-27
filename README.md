# Trabajo Final Programación II - Sistema de Gestión de Emergencias en un Hospital
**Materia**: Programación II

**Comisión**: Turno noche, martes

**Integrantes**:

- Carballo, Martin Pablo - 1175190
- Mareco, Melissa Oriana - 1148938

# Identificación del Problema

La atención rápida y priorizada de emergencias médicas es fundamental para salvar vidas en los hospitales. Sin un sistema organizado, existe el riesgo de atender casos menos urgentes antes que los críticos, lo que puede generar consecuencias graves.

Para enfrentar esta problemática, se propone un sistema que organiza la llegada y atención de emergencias según su nivel de gravedad. Esta solución permite registrar pacientes, priorizar automáticamente las emergencias, visualizar el historial de atenciones, deshacer acciones recientes y buscar pacientes, ofreciendo así una gestión eficiente y ordenada.

# Diseño de la Solución

El sistema se basa en la utilización de tres Tipos de Datos Abstractos (TDAs) propios: una Cola con Prioridad para manejar las emergencias en espera, una Lista Doble Enlazada para almacenar el historial de pacientes atendidos, y una Pila que permite deshacer la última atención realizada.

El flujo principal es el siguiente: al registrar un paciente, su emergencia se inserta en la cola de acuerdo a la gravedad. Durante la atención, se extrae el paciente con mayor prioridad, quien es registrado en el historial y guardado en la pila por si se necesita revertir la acción. El sistema también ofrece funcionalidades para buscar pacientes tanto en la cola como en el historial, y para deshacer atenciones, restaurando al paciente en la cola.

# Implementación

El desarrollo se realizó en Java, con una estructura modular para facilitar la organización y mantenimiento del código. El archivo principal `Simulador.java` contiene el flujo y el menú de opciones. Las clases que modelan pacientes y emergencias están en el paquete `modelo`. Los TDAs propios — ColaPrioridad, ListaDoble y Pila — se implementan en el paquete `tda` y sus interfaces en `tda/interfaces`. Todo el código está documentado con comentarios claros que explican su funcionamiento.

# Pruebas

Se ejecutaron diversas pruebas para garantizar el correcto funcionamiento del sistema. Se verificó el registro de emergencias con diferentes niveles de gravedad, la atención conforme a la prioridad, la gestión del historial, la funcionalidad de deshacer la última atención y la búsqueda de pacientes por DNI. Además, se evaluaron casos límite, como el sistema vacío, la búsqueda de pacientes inexistentes y la acción de deshacer sin historial previo.

Se cargaron emergencias predeterminadas ya atendidas al iniciar la aplicación para facilitar las pruebas, y se validó la correcta visualización tanto de la cola como del historial.

# Análisis

En términos de eficiencia, la ColaPrioridad tiene un costo de inserción O(n) y extracción O(1), la ListaDoble permite inserciones y eliminaciones en extremos en O(1), y la Pila opera con inserción y extracción en O(1). Para mejorar el rendimiento en inserciones, podría implementarse una cola con prioridad basada en montículos u otras estructuras más avanzadas.

La complejidad global del sistema es adecuada para la cantidad de datos que maneja un simulador de este tipo, proporcionando tiempos razonables para las operaciones frecuentes.

# Conclusión

El sistema diseñado y desarrollado cumple con el objetivo de gestionar emergencias hospitalarias de manera eficiente y ordenada, priorizando la atención según la gravedad y manteniendo un historial fiable de pacientes atendidos. Gracias al uso adecuado de estructuras de datos abstractas propias y una implementación modular en Java, se logra un equilibrio entre funcionalidad, rendimiento y mantenimiento. Este trabajo final no solo refleja los conocimientos adquiridos en la materia, sino que también proporciona una base sólida para futuras mejoras y ampliaciones.
