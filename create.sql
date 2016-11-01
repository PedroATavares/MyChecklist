if object_id('TagCheckList') is not null  DROP TABLE TagCheckList
if object_id('Tag') is not null  DROP TABLE Tag
if object_id('Task') is not null  DROP TABLE Task
if object_id('Checklist') is not null  DROP TABLE Checklist
if object_id('TemplateTask') is not null  DROP TABLE TemplateTask
if object_id('Template') is not null  DROP TABLE Template

Create Table Template(
	tid int IDENTITY(1,1) primary key ,
	Name varchar(80) NOT NULL,
	Descrip varchar(200) NOT NULL
)

Create Table TemplateTask(
    tid int references Template,
	TempTskId int IDENTITY(1,1),
	Name varchar(80) NOT NULL,
	Descrip varchar(200) NOT NULL, 
	constraint PK_TemplateTaskTest_Constraint primary key (tid, TempTskId)
)


Create Table Checklist(
	cid int IDENTITY(1,1) primary key ,
	Name varchar(80) NOT NULL,
	Descrip varchar(200) NOT NULL,
	DueDate date default null,
	IsClosed varchar(5) default 'false',
	tid int references Template default null,
	constraint checklist_isClosed_Constraint check (isClosed in ('true','false'))
)

Create Table Task(
	cid int foreign key references Checklist ,
	lid int IDENTITY(1,1) ,
	Name varchar(80) NOT NULL,
	Descrip varchar(200) NOT NULL,
	DueDate date default null,
	IsClosed varchar(5) default 'false',
	constraint PK_Task_Constraint primary key (cid, lid),
	constraint task_isClosed_Constraint check (isClosed in ('true','false'))
)

Create Table Tag(
	gid int IDENTITY(1,1) primary key,
	Name varchar(80) unique,
	Color varchar(80) NOT NULL
)

Create Table TagCheckList(
	gid int references Tag,
	cid int references CheckList,
	constraint tagId primary key ( gid,cid)
)


insert into Template (Name,Descrip) values ('Estou sempre a fazer isto', 'Para resolver esse problema criei um Template')
insert into Template (Name,Descrip) values ('Template 2', 'Para resolver esse problema criei um segundo Template')

insert into TemplateTask (Name,Descrip, tid) values ('Primeiro coisa a fazer','arranjar mais imaginação pra preencher a DB', 1)
insert into TemplateTask (Name,Descrip, tid) values ('Segunda coisa a fazer','arranjar mais imaginação pra preencher a DB 2', 2)

insert into Checklist (Name, Descrip, DueDate, tid) values ('Teste 1','Fazer primeiro teste','2010-10-16', null)
insert into Checklist (Name, Descrip, DueDate, tid) values ('Teste 2','Fazer segundo teste','2010-11-16', 2)
insert into Checklist (Name, Descrip, DueDate, tid) values ('Teste 3','Fazer terceiro teste',null, 1)
insert into Checklist (Name, Descrip, DueDate, tid,IsClosed) values ('Teste 3','Fazer terceiro teste','2010-9-16', null,'true')

insert into Task (Name, Descrip, DueDate, cid, isclosed) values ('Primeiro passo', 'Fazer build','2010-10-15',1,'true')
insert into Task (Name, Descrip, DueDate, cid) values ('Segundo passo', 'Executar testes','2010-10-10',1)
insert into Task (Name, Descrip, DueDate, cid) values ('Terceiro passo', 'Batman','2010-10-12',1)

insert into Task (Name, Descrip, DueDate, cid) values ('Segunda coisa a fazer', 'arranjar mais imaginação pra preencher a DB 2','2010-11-15',2)
insert into Task (Name, Descrip, DueDate, cid) values ('Segundo passo', 'Fazer build','2010-11-10',2)
insert into Task (Name, Descrip, DueDate, cid) values ('Terceiro passo', 'Executar o Gato','2010-11-12',2)

insert into Task (Name, Descrip, DueDate, cid, isclosed) values ('Primeiro coisa a fazer', 'arranjar mais imaginação pra preencher a DB',null,3,'true')
insert into Task (Name, Descrip, DueDate, cid, isclosed) values ('Segundo passo', 'Fazer filme',null,3,'true')
insert into Task (Name, Descrip, DueDate, cid, isclosed) values ('Terceiro passo', 'SUPER-HOMEN',null,3,'false')

insert into Task (Name, Descrip, DueDate, cid, isclosed) values ('Primeiro coisa a fazer', 'arranjar mais imaginação pra preencher a DB','2010-9-15',4,'true')
insert into Task (Name, Descrip, DueDate, cid, isclosed) values ('Segundo passo', 'Fazer filme','2010-9-10',4,'true')
insert into Task (Name, Descrip, DueDate, cid, isclosed) values ('Terceiro passo', 'SUPER-HOMEN','2010-9-12',4,'true')

insert into Tag (Name, Color) values ('Fruta', 'Verde')
insert into Tag (Name, Color) values ('Carne', 'Vermelho')

insert into TagCheckList (gid,cid) values (1, 1)
insert into TagCheckList (gid,cid) values (1, 2)

insert into TagCheckList (gid,cid) values (2, 3)
insert into TagCheckList (gid,cid) values (2, 4)