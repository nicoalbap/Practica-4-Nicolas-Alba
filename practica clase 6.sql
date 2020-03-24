use cuarentena;

Select nombres, apellidos from persona
inner join paciente on persona.CI = paciente.personaID;

Select nro, nombres, apellidos from persona
inner join doctor on persona.CI = doctor.ID
inner join consultorio on consultorio.ID = doctor.consultorioID;

Select * from doctor;
Select * from consultorio;
Select * from paciente;

