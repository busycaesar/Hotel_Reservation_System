# Hotel Reservation System

## Description

This hotel reservation management system for Hotel Blue Queen offers a comprehensive solution for managing bookings and reservations. Customers can easily book rooms after viewing detailed information about the available room types, the number of rooms available, and their respective prices per day. The user-friendly interface ensures a seamless booking experience, allowing customers to make informed decisions about their stay.

The system also includes a robust admin panel, providing hotel administrators with full control over room availability and bookings. Admins can monitor the number of available rooms by type, view all current bookings, and generate receipts for any ongoing reservations. Additionally, the admin panel allows for the application of discounts when generating receipts. Admins have access to a complete history of all bookings, both current and past, and can manage customer details, including checking out specific reservations. This comprehensive functionality ensures efficient and effective management of hotel operations, enhancing the overall guest experience.

## System

### Tech Stack

<img src="https://skillicons.dev/icons?i=java,sqlite,eclipse" />

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
