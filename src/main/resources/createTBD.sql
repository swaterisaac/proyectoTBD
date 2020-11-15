CREATE TABLE "volunteers_skills" (
  "id" SERIAL,
  "id_volunteer" integer,
  "id_skill" integer,
  PRIMARY KEY ("id")
);

CREATE TABLE "emergency_skills" (
  "id" SERIAL,
  "id_emergency" integer,
  "id_skill" integer,
  PRIMARY KEY ("id")
);

CREATE TABLE "emergency_skills_tasks" (
  "id" SERIAL,
  "id_eme_skills" integer,
  "id_task" integer,
  PRIMARY KEY ("id")
);

CREATE TABLE "institutions" (
  "id" SERIAL,
  "name" varchar(100),
  PRIMARY KEY ("id")
);

CREATE TABLE "emergencies" (
  "id" SERIAL,
  "id_status" integer,
  "name" varchar(100),
  "description" varchar(255),
  "start_date" date,
  "final_date" date,
  "id_institution" integer,
  "created_at" timestamp,
  PRIMARY KEY ("id")
);

CREATE TABLE "task_update" (
  "id" SERIAL,
  "created_at" timestamp,
  "description" varchar(255),
  "id_task" integer,
  PRIMARY KEY ("id")
);

CREATE TABLE "skills" (
  "id" SERIAL,
  "name" varchar(25),
  "description" varchar(255),
  PRIMARY KEY ("id")
);

CREATE TABLE "rankings" (
  "id" SERIAL,
  "score" integer,
  "flg_invited" boolean,
  "flg_participates" boolean,
  "id_task" integer,
  "id_volunteer" integer,
  PRIMARY KEY ("id")
);

CREATE TABLE "status" (
  "id" SERIAL,
  "description" varchar(20),
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
  "created_at" timestamp,
  "id_status" integer,
  "id_emergency" integer,
  PRIMARY KEY ("id")
);

CREATE TABLE "users" (
  "id" SERIAL,
  "rut" varchar(10),
  "first_name" varchar(100),
  "email" varchar(100),
  "password" varchar(26),
  "last_name" varchar(100),
  "phone" varchar(10),
  PRIMARY KEY ("id")
);

CREATE TABLE "volunteers" (
  "id" SERIAL,
  "name" varchar(100),
  PRIMARY KEY ("id")
);

