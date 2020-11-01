These artefacts is for the second assignment of Mobile System Development.

**01_Refined Design Document.pdf** <br />
In the amended document, there are mainly three parts refined such as the role in the process of development, the deployment design and evaluation criteria. <br />
1> The role is changed. I focus on system architecture, UI design and testing rather than developing since I have no developing experience.<br />
2> I do not separate the whole system into mobile client, web server and database but integrate all of them into the client.<br />
3> The extension of evaluation criteria.<br />

**02_Software Requirements Specification.pdf**<br />
In this project, SRS can be separated into four segments including introduction, users, functional and non-functional requirements.<br />

**03_Project Report**<br />
The report mainly illustrates the overview of projects, presents the relevent artefacts, shows the evaluation outcomes and refect on the utilied techonology during the process of development.<br />

**04_Project**<br />
The VTL application is used to effectively control the traffic flow, congestion and even reduce traffic accidents without the physicsal infrastucture. This project is achieved by using the MVVM design pattern, which includes view, view model, repository, model and DB helper.<br />
1> In View, only three pages are defined including registering, log in and main screen.<br />
2> View model has three corresponding XML file to define the navigational logic to the next page, acquire data from view and transfer information to repository.<br />
3> Repository is used to achieve the core algorithm in the VTL application including collision detection, repository (leader election and signal generation). <br />
4> Model defines the information of vehicles in terms of the speed and location and user data including email and password.<br />
5> DB helper establishes the user table in the database by applying SQLiteDatabse.<br />

**The way to executing:**<br />
Step1: Download Android Studio<br />
Step2: Select a simulator (Pixel 2 API 29)<br />
Step3: choose to run<br />
