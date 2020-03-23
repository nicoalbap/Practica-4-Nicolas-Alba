use bookstore;

Select nombre, apellido, usuario from empleado
left join usuario on empleado.idempleado = usuario.idempleado;

Select email from empleado
inner join usuario on empleado.idempleado = usuario.idempleado
where usuario.activo = 1;

Select Count(autor) as autor from publicacion
where autor = 'Eric G. Coronel Castillo';

Select Sum(cantidad) as cantidad from venta 
inner join empleado on venta.idempleado = empleado.idempleado
where empleado.nombre = 'Emilio';

Select * from venta;
Select * from empleado;

