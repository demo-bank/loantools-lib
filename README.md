# Loan Tools

Purposely vulnerable "first party" library.

## GitLab Setup

This project publishes a JAR used as a dependency for other projects. To make the deployed JAR available to other
projects, you must add those projects to the "allow list" in GitLab:

1. Go to **Settings > CI/CD > Job Token Permissions** in the LoanTools project
2. Add the Group **cloudnative20031/apps/LoanCalculator** to the token allow list
