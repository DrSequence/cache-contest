# build AMD
# ./gradlew bootBuildImage --imageName=ere/cf-redis-service

# build ARM + Dockerfile
docker build -t ere/cf-redis-service .
