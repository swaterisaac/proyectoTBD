--Drop of tables
DROP TABLE IF EXISTS "volunteers_skills" CASCADE;
DROP TABLE IF EXISTS "emergency_skills" CASCADE;
DROP TABLE IF EXISTS "emergency_skills_tasks" CASCADE;
DROP TABLE IF EXISTS "institutions" CASCADE;
DROP TABLE IF EXISTS "emergencies" CASCADE;
DROP TABLE IF EXISTS "task_update" CASCADE;
DROP TABLE IF EXISTS "skills" CASCADE;
DROP TABLE IF EXISTS "rankings" CASCADE;
DROP TABLE IF EXISTS "status" CASCADE;
DROP TABLE IF EXISTS "tasks" CASCADE;
DROP TABLE IF EXISTS "users" CASCADE;
DROP TABLE IF EXISTS "volunteers" CASCADE;

--Creation of tables
CREATE TABLE "volunteers_skills" (
  "id" SERIAL,
  "id_volunteer" integer NOT NULL,
  "id_skill" integer NOT NULL,
  "deleted" boolean NOT NULL DEFAULT false,
  PRIMARY KEY ("id")
);

CREATE TABLE "emergency_skills" (
  "id" SERIAL,
  "id_emergency" integer NOT NULL,
  "id_skill" integer NOT NULL,
  "deleted" boolean NOT NULL DEFAULT false,
  PRIMARY KEY ("id")
);

CREATE TABLE "emergency_skills_tasks" (
  "id" SERIAL,
  "id_eme_skills" integer NOT NULL,
  "id_task" integer NOT NULL,
  "deleted" boolean NOT NULL DEFAULT false,
  PRIMARY KEY ("id")
);

CREATE TABLE "institutions" (
  "id" SERIAL,
  "name" varchar(100),
  "deleted" boolean NOT NULL DEFAULT false,
  PRIMARY KEY ("id")
);

CREATE TABLE "emergencies" (
  "id" SERIAL,
  "id_status" integer NOT NULL,
  "name" varchar(100),
  "description" varchar(255),
  "start_date" date,
  "final_date" date,
  "id_institution" integer NOT NULL,
  "created_at" timestamp,
  "deleted" boolean NOT NULL DEFAULT false,
  PRIMARY KEY ("id")
);

CREATE TABLE "task_update" (
  "id" SERIAL,
  "created_at" timestamp,
  "description" varchar(255),
  "id_task" integer NOT NULL,
  "deleted" boolean NOT NULL DEFAULT false,
  PRIMARY KEY ("id")
);

CREATE TABLE "skills" (
  "id" SERIAL,
  "name" varchar(25),
  "description" varchar(255),
  "deleted" boolean NOT NULL DEFAULT false,
  PRIMARY KEY ("id")
);

CREATE TABLE "rankings" (
  "id" SERIAL,
  "score" integer,
  "flg_invited" boolean,
  "flg_participates" boolean,
  "id_task" integer NOT NULL,
  "id_volunteer" integer NOT NULL,
  "deleted" boolean NOT NULL DEFAULT false,
  PRIMARY KEY ("id")
);

CREATE TABLE "status" (
  "id" SERIAL,
  "description" varchar(20),
  "deleted" boolean NOT NULL DEFAULT false,
  PRIMARY KEY ("id")
);

CREATE TABLE "tasks" (
  "id" SERIAL,
  "name" varchar(100),
  "description" varchar(255),
  "volunteer_required" integer,
  "volunteer_registered" integer,
  "start_date" date,
  "final_date" date,
  "created_at" timestamp NOT NULL,
  "id_status" integer NOT NULL,
  "id_emergency" integer NOT NULL,
  "deleted" boolean NOT NULL DEFAULT false,
  PRIMARY KEY ("id")
);

CREATE TABLE "users" (
  "id" SERIAL,
  "rut" varchar(10) NOT NULL,
  "first_name" varchar(100) NOT NULL,
  "email" varchar(100) NOT NULL,
  "password" varchar(26) NOT NULL,
  "last_name" varchar(100),
  "phone" varchar(10),
  "age" integer NOT NULL,
  "deleted" boolean NOT NULL DEFAULT false,
  PRIMARY KEY ("id")
);

CREATE TABLE "volunteers" (
  "id" SERIAL,
  "id_user" integer NOT NULL,
  "deleted" boolean NOT NULL DEFAULT false,
  PRIMARY KEY ("id")
);

--Creation of FK
ALTER TABLE "volunteers_skills"
ADD CONSTRAINT volunteer_fk
FOREIGN KEY ("id_volunteer")
REFERENCES volunteers("id")
ON DELETE CASCADE;


ALTER TABLE "volunteers_skills"
ADD CONSTRAINT skill_fk
FOREIGN KEY ("id_skill")
REFERENCES skills("id")
ON DELETE CASCADE;

ALTER TABLE "tasks"
ADD CONSTRAINT emergency_fk
FOREIGN KEY ("id_emergency")
REFERENCES emergencies("id")
ON DELETE CASCADE;

ALTER TABLE "tasks"
ADD CONSTRAINT status_fk
FOREIGN KEY ("id_status")
REFERENCES status("id")
ON DELETE CASCADE;



ALTER TABLE "emergencies"
ADD CONSTRAINT status_fk
FOREIGN KEY ("id_status")
REFERENCES status("id")
ON DELETE CASCADE;


ALTER TABLE "emergencies"
ADD CONSTRAINT institution_fk
FOREIGN KEY ("id_institution")
REFERENCES institutions("id")
ON DELETE CASCADE;

ALTER TABLE "rankings"
ADD CONSTRAINT task_fk
FOREIGN KEY ("id_task")
REFERENCES tasks("id")
ON DELETE CASCADE;

ALTER TABLE "rankings"
ADD CONSTRAINT volunteer_fk
FOREIGN KEY ("id_volunteer")
REFERENCES volunteers("id")
ON DELETE CASCADE;


ALTER TABLE "emergency_skills_tasks"
ADD CONSTRAINT eme_skill_fk
FOREIGN KEY ("id_eme_skills")
REFERENCES emergency_skills("id")
ON DELETE CASCADE;

ALTER TABLE "emergency_skills_tasks"
ADD CONSTRAINT tasks_fk
FOREIGN KEY ("id_task")
REFERENCES tasks("id")
ON DELETE CASCADE;


ALTER TABLE "emergency_skills"
ADD CONSTRAINT emergency_fk
FOREIGN KEY ("id_emergency")
REFERENCES emergencies("id")
ON DELETE CASCADE;

ALTER TABLE "emergency_skills"
ADD CONSTRAINT skill_fk
FOREIGN KEY ("id_skill")
REFERENCES skills("id")
ON DELETE CASCADE;

ALTER TABLE "task_update"
ADD CONSTRAINT task_fk
FOREIGN KEY ("id_task")
REFERENCES tasks("id")
ON DELETE CASCADE;

ALTER TABLE "volunteers"
ADD CONSTRAINT user_fk
FOREIGN KEY ("id_user")
REFERENCES users("id")
ON DELETE CASCADE;


CREATE FUNCTION public.volunteer_gt_age(IN entryage integer DEFAULT 200, IN emid bigint DEFAULT 1)
    RETURNS bigint
    LANGUAGE 'sql'

AS $BODY$
SELECT COUNT(*)
FROM volunteers v,users u, emergencies e, status s, rankings r, tasks ta

WHERE u.age >= entryage AND v.deleted = false
AND e.id = emid AND e.id_status = s.id AND s.description = 'Activo'
AND emid = ta.id_emergency
AND ta.id = r.id_task AND r.id_volunteer = v.id
AND v.id = u.id;
$BODY$;

ALTER FUNCTION public.volunteer_gt_age(integer, bigint)
    OWNER TO tbd;