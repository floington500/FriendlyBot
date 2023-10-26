#!/bin/bash
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker buildx create --use
docker buildx version
docker buildx build --platform linux/amd64,linux/arm64 -t "$DOCKERHUB_USERNAME"/private:back-bond
docker push "$DOCKERHUB_USERNAME"/private:back-bond
docker buildx rm