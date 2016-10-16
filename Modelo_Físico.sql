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