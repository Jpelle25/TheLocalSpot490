# Senior Project (CSC490) Section 2 

## TheLocalSpot ##

## Team Members ##

Eric Nguyen

Luong Luu

Joshua Pellergino

## Goal ##

Simplification and ease-of-use of a ticket management service. This project aims to mimic TicketMaster but on a smaller scale for local businesses and events. We aim to provide clear authentication of registered events and tickets.

## Why is our project needed? ##

This is needed to help customers get tickets ahead of time without worrying about being late. It also helps local event coordinators to sell tickets to their potential buyers and customers for small-scale events. In terms of tickets, it helps any facilitator to keep track of event popularity, and ticket management.

##  Project Information ##

In order to run this application, clone the repo, install all dependencies, and run application.

This applicaiton uses a framework named Vaadin for all web development needs. A tutorial link is provided below for basic Vaadin usage. 

## Recommendations ##

IntelliJ

We used IntelliJ for the IDE of choice when running this application. Please download respective IntelliJ download for your operating system following this link: https://www.jetbrains.com/idea/download

Vaadin Familiarity

Vaadin Tutorial Link: https://www.youtube.com/watch?v=bxy2JgqqKDU

## User Manual ##

** 1. Sign in ** 

When the application runs, you will be prompted to click "Sign in with Google" in order to input your Google credentials

2. Registration

If your account is not yet registered with the application, you will be prompted to register for a given client (General User, Coordinator, Host)

3. General User

- Welcome Page

This is just a screen that appears after entering the application.

- Browse Events

When clicking on this view you will be presented with a list of events of which are considered "live" and after clicking on a respective event you are given the ability to purchase a ticket.

Disclaimer: You have to click on one event at a time and purchase that ticket in its current version.

- Tickets

In this view you are given two options (Transfer Ticket, Purchase History). Each will give proper instructions and display. 

4. Coordinator

- Welcome Page

This is just a screen that appears after entering the application.

- Pending Events

In this view is a grid displaying all "pending" events created to your respective account. In order to create a pending event click on "Add Event" and fill out the form that follows

- Approved Events

In this view it will display all events that are considered "live" and pertain to your account.

- Denied Events

In this view it will display all events that are denied by the host of choice and that also pertain to your account.

5. Host

- Welcome Page

This is just a screen that appears after entering the application.

- Places

In this view is a grid displaying all of your account's places of business. In order to add another place of business, click on the button titled "Add Place" and fill out the respective form.

- Pending Events

In this view is a grid displaying all "pending" events requested by a coordinator through one of your registered places of business. Clicking on the given requests will prompt you to provide feedback and a decision with either approving or denying the given request.

6. Admin

In the current build, admin functionalities are not fully functional. In order to view admin capabilities, you have to manually navigate to "http://localhost:8080/admin-welcome" and will be presented with three views(Coordinator View, General User View, and Host View). These views each display the registered clients with the respective roles across the entire application. 

7. Miscelaneous
- Account Details

This button displays brief account info and then the ability to go back to the your given welcome screen.

- Logout

Sends you back to homepage.