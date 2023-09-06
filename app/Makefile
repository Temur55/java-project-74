.DEFAULT_GOAL := build-run

clean:
	./gradlew clean

build:
	./gradlew clean build

start:
	./gradlew bootRun --args='--spring.profiles.active=dev'

start-prod:
	./gradlew bootRun --args='--spring.profiles.active=prod'

install:
	./gradlew clean installBootDist

run-dist:
	./build/install/app/bin/app

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest

update-deps:
	./gradlew useLatestVersions

generate-migrations:
	gradle diffChangeLog

db-migrate:
	./gradlew update

check-updates:
	./gradlew dependencyUpdates

setup:
	gradle wrapper --gradle-version 8.3

build-run: build run

.PHONY: build
