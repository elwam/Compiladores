use db_compiladores;

-- C1
select palabra from(select concat_ws('',t1.ds_raiz,t2.ds_terminacion) as palabra from raiz t1 inner join clasificacion_raiz t2 on (t1.id_raiz = t2.id_raiz)) tabla where tabla.palabra = palabra;

-- C2

select palabra, ds_raiz, ds_terminacion, ds_naturaleza from(select t1.ds_raiz, case when t2.ds_terminacion = '' then 'Sin terminacion' else t2.ds_terminacion end ds_terminacion, t3.ds_naturaleza, concat_ws('',t1.ds_raiz,t2.ds_terminacion) as palabra from raiz t1 inner join clasificacion_raiz t2 on (t1.id_raiz = t2.id_raiz) inner join naturaleza t3 on (t1.id_naturaleza = t3.id_naturaleza)) tabla where tabla.palabra = 'el';


-- C3

select palabra, ds_genero, ds_numero, ds_naturaleza from(select t4.ds_genero, t5.ds_numero, t3.ds_naturaleza, concat_ws('',t1.ds_raiz,t2.ds_terminacion) as palabra from raiz t1 inner join clasificacion_raiz t2 on (t1.id_raiz = t2.id_raiz) inner join naturaleza t3 on (t1.id_naturaleza = t3.id_naturaleza) inner join genero t4 on (t2.id_genero = t4.id_genero) inner join numero t5 on (t2.id_numero = t5.id_numero)) tabla where tabla.palabra = 'el';


