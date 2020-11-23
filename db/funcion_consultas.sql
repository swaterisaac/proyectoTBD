
--Versión antigua
CREATE OR REPLACE FUNCTION public.volunteer_gt_age(IN entryage integer DEFAULT 200)
    RETURNS bigint
    LANGUAGE 'sql'
    VOLATILE

    COST 100
AS $BODY$
SELECT COUNT(*)
FROM volunteers v
INNER JOIN users u
ON v.id = u.id
WHERE u.age >= entryage AND v.deleted = false;
$BODY$;


--Versión nueva

CREATE FUNCTION public.volunteer_gt_age(IN emid bigint DEFAULT 1, IN entryage integer DEFAULT 200)
    RETURNS bigint
    LANGUAGE 'sql'

AS $BODY$
SELECT COUNT(*)
FROM volunteers v,users u, emergencies e, status s, rankings r, tasks ta

WHERE u.age >= entryage
AND e.id = emid AND e.id_status = s.id AND s.id = 1 --El status tiene que tener id 1 para estar 'activo'
AND emid = ta.id_emergency
AND ta.id = r.id_task AND r.id_volunteer = v.id
AND v.id = u.id
AND v.deleted = false AND u.deleted = false
AND e.deleted = false AND s.deleted = false
AND r.deleted = false AND ta.deleted = false;
$BODY$;

ALTER FUNCTION public.volunteer_gt_age(bigint, integer)
    OWNER TO tbd;




--consulta de funcion
SELECT COUNT(*)
FROM volunteers v,users u, emergencies e, status s, rankings r, tasks ta

WHERE u.age >= 20
AND e.id = 1 AND e.id_status = s.id AND s.id = 1 --El status tiene que tener id 1 para estar 'activo'
AND 1 = ta.id_emergency
AND ta.id = r.id_task AND r.id_volunteer = v.id
AND v.id = u.id
AND v.deleted = false AND u.deleted = false
AND e.deleted = false AND s.deleted = false
AND r.deleted = false AND ta.deleted = false;


--consulta servicio REST
SELECT COUNT(*),ta.name
FROM volunteers v,users u, emergencies e, status s, rankings r, tasks ta

WHERE u.age >= :age AND e.id = :emid
AND e.id_status = s.id AND s.id = 1 --El status 1 es activo.
AND :emid = ta.id_emergency AND ta.id = r.id_task
AND r.id_volunteer = v.id AND v.id = u.id
AND v.deleted = false AND u.deleted = false
AND e.deleted = false AND s.deleted = false
AND r.deleted = false AND ta.deleted = false
GROUP BY ta.name ORDER BY ta.name ASC;