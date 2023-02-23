# RegistationManagementSystem
My first Java project

This is an application used to register guests at a particular event. 

Guests are registered using the following fields: last name, first name, email and phone number. The last two fields are unique for every guest.  The first name and last name are unique only tooked together.

The event has a certain capacity, after reaching it, guests are placed on the waiting list. If a confirmed guest withdraws from the event, his place is taken by the first guest on the waiting list.

The application has the following features:

add           - Registers a new person

check         - Verifies if a person is registered for the event

remove        - Removes an existing person from the list

update        - Updates a specific field of a person

guests        - Shows the list of people attending the event

waitlist      - Shows the list of people in the waiting list

available     - Number of available spots at the event

guests_no     - Number of people attending

waitlist_no   - Number of people in the waiting list

registered_no - Total number of people registered

search        - Searches the guests by entering specific text

quit          - Closes the application
