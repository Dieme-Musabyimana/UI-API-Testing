# Use an official lightweight Java runtime image
FROM maven:3.9.6-eclipse-temurin-17

# Set the working directory inside the container
WORKDIR /app

# Copy all project files and subfolders into the container
COPY . .

# Run maven test across all modules in the project
CMD ["mvn", "clean", "test"]