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
	constraint PK_TemplateTask_Constraint primary key (tid, TempTskId)
)


Create Table Checklist(
	cid int IDENTITY(1,1) primary key ,
	Name varchar(80) NOT NULL,
	Descrip varchar(200) NOT NULL,
	DueDate date default null,
	IsClosed varchar(5) default 'false',
	tid int references Template default null,
	constraint CheckList_isClosed_Constraint check (isClosed in ('true','false'))
)

Create Table Task(
	cid int foreign key references Checklist ,
	lid int IDENTITY(1,1) ,
	Name varchar(80) NOT NULL,
	Descrip varchar(200) NOT NULL,
	DueDate date default null,
	IsClosed varchar(5) default 'false',
	constraint PK_Task_Constraint primary key (cid, lid),
	constraint Task_isClosed_Constraint check (isClosed in ('true','false'))
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