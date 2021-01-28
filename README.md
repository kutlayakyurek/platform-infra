# Sample CDK Project

Sample AWS CDK project written with Java

## Introduction

This project aims for implementing IAC on AWS environment

## How To Run

Use `cdk deploy` and other useful commands below to test out the system

## How To Test

1. Install OpenJDK8

(Ubuntu, Debian) https://docs.datastax.com/en/jdk-install/doc/jdk-install/installOpenJdkDeb.html
(Macosx) https://dzone.com/articles/install-openjdk-versions-on-the-mac

2. Install Apache Maven

https://www.baeldung.com/install-maven-on-windows-linux-mac

2. Install and configure AWS CDK

https://docs.aws.amazon.com/cdk/latest/guide/work-with.html

3. Configure your credentials and check `.aws` folder for local credential configuration. This way, you will assume necessary role automatically
4. Execute `cdk deploy` command
5. Check for new VPC, subnets etc
6. Check for new DynamoDB table
7. Check for new S3 bucket
8. Check for new Lambda function
9. Check ECR for Docker images

## Useful commands

mvn package compile
cdk ls - list all stacks in the app
cdk synth - emits the synthesized CloudFormation template
cdk deploy - deploy this stack to your default AWS account/region
cdk diff - compare deployed stack with current state
cdk docs - open CDK documentation

### Status

- ![#f03c15](https://placehold.it/15/f03c15/000000?text=+) `Urgent Things to Do`

CI/CD job implementation
  
- ![#c5f015](https://placehold.it/15/c5f015/000000?text=+) `Test Coverage`
  
- ![#1589F0](https://placehold.it/15/1589F0/000000?text=+) `Requests`
