# Transit Process Management Application

This repository contains an Android application designed to solve the main problem of the transit process. The application utilizes Firebase's real-time database and authentication database, as well as Google Maps for map services. It aims to streamline the transit process by addressing specific needs of different roles within the organization, including the Engineer's Office, Depo Officer's Office, and Admin's Office.

## Problem Statement

The transit process faced several challenges that needed to be addressed:

- **Engineer's Office**: The confirmation of task completion from the plan was a crucial step, requiring an efficient and reliable system to track and verify the completion of tasks.

- **Depo Officer's Office**: Confirmation of tasks performed by the driver was necessary to ensure accountability and accurate reporting.

- **Admin's Office**: Monitoring the implementation of the plan was essential for overseeing the entire transit process and identifying any potential issues or deviations.

## Solution

To address the challenges faced by different roles in the transit process, a comprehensive Android application was developed. The application provides specific features and access privileges based on the user's role, dividing the functionality into three main roles:

- **Admin**: This role has access to the entire application and can oversee the whole route on the map, including the checkpoints of Duties in their respective locations. The Admin can monitor the progress and performance of the transit process.

- **Duty**: Duties are assigned to monitor specific aspects of the transit process. They have access to the list of drivers and their respective plan pages. The Duty role can review the assigned tasks and ensure their completion.

- **Driver**: Drivers are responsible for executing the tasks assigned to them. They can access the main page of route checkpoints, allowing them to stay updated on their progress and complete each checkpoint efficiently.

## Features

The application includes the following key features:

- Role-based access: Each user is assigned a specific role (Admin, Duty, or Driver) and can only access the relevant parts of the application.

- Firebase real-time database: The application utilizes Firebase real-time database to store and retrieve data related to tasks, checkpoints, and user information.

- Firebase authentication: User authentication is handled through the Firebase authentication database, ensuring secure access to the application.

- Google Maps integration: Google Maps is integrated into the application to provide map services, allowing users to visualize routes, and checkpoints, and track progress.

## Screenshots
### Admin's panel
<img width="206" alt="Admin Screenshot 1" src="https://github.com/Ayamainstream/SilkwayTransit/assets/72498812/316c0699-6b31-4f3c-af7f-0e62bdbea478">
<img width="206" alt="Admin Screenshot 2" src="https://github.com/Ayamainstream/SilkwayTransit/assets/72498812/221d4d98-29f9-4091-bd36-f02534a8d2f5">
<img width="206" alt="Admin Screenshot 3" src="https://github.com/Ayamainstream/SilkwayTransit/assets/72498812/c52b3e8f-bc52-4e73-a15c-579a86c62e57">
<img width="206" alt="Admin Screenshot 4" src="https://github.com/Ayamainstream/SilkwayTransit/assets/72498812/c2aa5058-ce22-4896-9744-9730f86995d5">

### Driver's panel
<img width="206" alt="Driver Screenshot 1" src="https://github.com/Ayamainstream/SilkwayTransit/assets/72498812/316c0699-6b31-4f3c-af7f-0e62bdbea478">
<img width="206" alt="Driver Screenshot 2" src="https://github.com/Ayamainstream/SilkwayTransit/assets/72498812/cd93fadb-f8f0-4869-840f-f99bb508f51d">
<img width="206" alt="Driver Screenshot 3" src="https://github.com/Ayamainstream/SilkwayTransit/assets/72498812/c2aa5058-ce22-4896-9744-9730f86995d5">

### Duty's panel
<img width="206" alt="Duty Screenshot 1" src="https://github.com/Ayamainstream/SilkwayTransit/assets/72498812/316c0699-6b31-4f3c-af7f-0e62bdbea478">
<img width="206" alt="Duty Screenshot 2" src="https://github.com/Ayamainstream/SilkwayTransit/assets/72498812/1689cb46-3ad6-4c18-9e4f-2327d318b569">
<img width="206" alt="Duty Screenshot 3" src="https://github.com/Ayamainstream/SilkwayTransit/assets/72498812/7ee7a5bb-e96c-407e-a548-2dc3726c190f">
<img width="206" alt="Duty Screenshot 4" src="https://github.com/Ayamainstream/SilkwayTransit/assets/72498812/c2aa5058-ce22-4896-9744-9730f86995d5">

## Getting Started

To run the application locally, follow these steps:

1. Clone this repository to your local machine.
```
git clone https://github.com/Ayamainstream/SilkwayTransit
```

2. Set up the Firebase project and enable the real-time database and authentication services. Refer to the Firebase documentation for detailed instructions.

3. Configure the Firebase credentials in the application code. Update the Firebase configuration file with your project's credentials.

4. Build and run the application on an Android device or emulator.

## Technologies Used

The following technologies and tools were used in the development of this application:

- Android SDK
- Java
- Firebase (Real-time Database and Authentication)
- Google Maps API

## Contributing

Contributions to this project are welcome. If you encounter any issues or have suggestions for improvements, please submit a pull request or open an issue on the repository.

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgments

We would like to thank all the contributors and developers who have worked on this application to make it a comprehensive solution for managing the transit process efficiently.
