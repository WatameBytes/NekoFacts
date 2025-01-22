# NekoFacts API

[![Build and Test](https://github.com/WatameBytes/NekoFacts/actions/workflows/build.yml/badge.svg)](https://github.com/WatameBytes/NekoFacts/actions/workflows/build.yml)

NekoFacts API is a Spring Boot application that integrates with the Nekos API to retrieve random anime images and random image files. This project provides REST endpoints for easy interaction with the Nekos API.

---

## Features

- Fetch random anime images with optional filters.
- Retrieve a random image file directly.
- Built using Spring Boot for rapid development and deployment.

---

## Prerequisites

- Java 17 or higher (Tested with OpenJDK 23.0.1)
- Gradle (for building the project)
- Internet connection (to interact with the Nekos API)

---

## Installation

1. Clone the repository:
   git clone https://github.com/yourusername/nekofacts-api.git
   cd nekofacts-api

2. Build the project:
   ./gradlew build

3. Run the application:
   ./gradlew bootRun

4. The application will be accessible at http://localhost:8080.

---

## Endpoints

### 1. Get Random Images

Fetch multiple random anime images.

- URL: /nekos/random
- Method: GET
- Query Parameters:
   - limit (optional): Number of images to fetch (default: 1, max: 100)

Example Request:
curl http://localhost:8080/nekos/random?limit=5

Example Response:
[
{
"id": "12345",
"url": "https://example.com/image1.jpg",
"tags": ["tag1", "tag2"]
},
{
"id": "12346",
"url": "https://example.com/image2.jpg",
"tags": ["tag3", "tag4"]
}
]

---

### 2. Get Random Image File

Fetch a direct URL to a random anime image.

- URL: /nekos/random/file
- Method: GET

Example Request:
curl http://localhost:8080/nekos/random/file

Example Response:
https://example.com/random-image.jpg

---

## Configuration

The application uses a RestTemplate bean to interact with the Nekos API. You can modify the base API URL or other configurations in the NekoApiService class.

---

## Technologies Used

- Spring Boot: Backend framework
- RestTemplate: For making HTTP requests
- Gradle: Build tool
- Nekos API: External API integration

---

## Troubleshooting

1. Error: Unsatisfied dependency for RestTemplate
   - Ensure you have defined the RestTemplate bean in the AppConfig or main application class.

2. Application won't start
   - Check your Java version. It should be Java 17 or higher.
   - Verify your internet connection to ensure the application can access the Nekos API.

---

## Contribution

Contributions are welcome! To contribute:
1. Fork the repository.
2. Create a feature branch:
   git checkout -b feature-name
3. Commit your changes:
   git commit -m "Add new feature"
4. Push the branch:
   git push origin feature-name
5. Open a Pull Request.

---

## License

This project is open-source and distributed under the MIT License. See the LICENSE file for details.

---

## Acknowledgments

- Thanks to Nekos API for providing the anime images.
- Special mention to the Spring Boot community for their excellent documentation and support.

---

## Contact

For any issues or suggestions, feel free to open an issue or contact the project maintainer.
