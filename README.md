# CryoNotes: A web-based science note-taking app for cryoEM experiments

## Overview
CryoEM is a widely used technique for protein structure determination, and it has become in recent times the preferred
method for structural studies in our and other labs. Despite monumental advances in data collection and sample
processing methods, the data processing workflows used for specific projects vary greatly, even within the same lab.
<br/>
<br/>
The goal of this project is to create a web-based app to keep track of data collection parameters, data processing
parameters, and results of different cryoEM experiments. My expectation is that having that information in a central
location will aid in planning and executing cryoEM experiments.

## Features
User authentication: A new user can create an account with their information. A valid user must log in to access the app.<br/>
Update user profile: An authenticated user can change their names, email, and/or password by.<br/>
Record information: Different steps of a cryoEM experiment are separated into pages. The user can access those pages and record information related to each step.<br/>
View and edit information: The user has a sortable summary view with all the entries in table form. The number of rows can be changed. The user can select one entry to view additonal information for that entry and, if desired, edit it.<br/> 
Compare information: The user can select multiple entries from the table view and compare all the data side by side.<br/>
Search: The user can search a keyword across any field, or use the advance search feature where they can look for specific keywords in chosen fields.<br/> 

## Technologies
Java<br/>
Spring Boot <br/>
Thymeleaf<br/>
HTML<br/>
CSS<br/>
Bootstrap
mySQL<br/>
Microsoft Azure
