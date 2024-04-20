# Hotel Reservation System

## Description

This is a hotel reservation management system for Hotel Blue Queen. Hotel customers can use the system book the room/s before looking at the details about available room types, number of rooms available and its price per day. Furthermore, it has an admin panel through which admins can check the number of available rooms along with its type, all the current booking, generate receipt for any current booking, offer discount while generating the receipt, all the bookings(both current and previous), all the customers and check out any specific customer's reservation.

## System

### Tech Stack

<img src="https://skillicons.dev/icons?i=java,sqlite" />

### Class Diagram

<img src="./ClassDiagram.jpg" alt="Class Diagram" />

## How it looks?

<a href="https://github.com/busycaesar/Hotel_Reservation_System/blob/Master/ApplicationLooks.md">Application Looks</a>

## Features

### Book Room

Customer as well as admin can book the room/s using book room page. Security features of the book room page are as follows:

- No fields marked with (*) can be empty.
- Email and phone should be in a proper format.
- The minimum check in date is current date and that of check out is check in date plus one.
- Cannot book rooms which accomodates less guests than the number of guests visiting.

The page shows appropriate error in case of any of the above sitution.

### Admin Panel

The admin panel can only be accessed by the authorized personels. There are two credentails for logging in as an admin. Admin panel has the following options:

- **Available Rooms**: Check the number of available rooms along with the room type.
- **Current Bookings**: This allows the admin to check the list of all the current booking. Further, admin can 'Generate Receipt' for any of the current booking along with an option to offer some discount.
- **All Reservations**: Check all the reservations, both current as well as checked out.
- **All Customers**: Check all the customers, both current as well as checked out. Moreover, there is also an option to check the reservation of a specific customer; in addition to the detail if the customer has already paid or not.
