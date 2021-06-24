SELECT * FROM Ruler;

insert into Person (id, name, family_name, fathers_name, wealth, sex, race, birth_date)
values
    (1,'Ayrenn','of Sommerset','Illiendil',3450,1,0,PARSEDATETIME('1875-15-09','YYYY-DD-MM')),
    (2,'Emeric','of HighRock','Radiled',2354,0,1,PARSEDATETIME('1973-15-09','YYYY-DD-MM')),
    (3,'Jorunn','of Skyrim','Eigar',2334,0,1,PARSEDATETIME('1984-15-09','YYYY-DD-MM')),
    (4,'Elisif','of Solitude','Svalgrim',1000,1,1,PARSEDATETIME('1984-15-09','YYYY-DD-MM')),
    (5,'Razum','Dar','Kediz',324,0,1,PARSEDATETIME('1984-15-09','YYYY-DD-MM')),
    (6,'Elrond','of Rivendell','Tranduil',1123,1,1,PARSEDATETIME('1784-15-09','YYYY-DD-MM')),
    (7,'Balgruf','of Whihterun','Jorvuld',1345,0,1,PARSEDATETIME('1984-15-09','YYYY-DD-MM'));

insert into Ruler (id,start_Ruling)
values
    (1,PARSEDATETIME('1895-20-10','YYYY-DD-MM')),
    (2,PARSEDATETIME('1994-20-10','YYYY-DD-MM')),
    (3,PARSEDATETIME('2000-20-10','YYYY-DD-MM'));

insert into Governor (id, council_id)
values
    (4)

