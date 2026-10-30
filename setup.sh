#!/usr/bin/env bash
set -e
command -v java >/dev/null || { echo "Java JDK 11+ (17 recommended) is required"; exit 1; }
command -v mvn  >/dev/null || { echo "Apache Maven is required"; exit 1; }
mvn clean install -DskipTests
echo "Done. Run tests with: mvn test"
