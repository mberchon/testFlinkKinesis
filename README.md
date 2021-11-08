# Flink AWS Authentication test  

A java module to test AWS authentication

## Prerequisites
- JRE 1.11,
- MVN,
- AWS CLI,
- AWS account with a Kinesis data stream named 'test-flink',
- AWS credentials and policy allowing to describe and consume the stream,
- AWS SSO.

## Setup

Build
```
mvn clean compile
```
Test basic authentication
```
Update TestFlinkBasicAuthent with your AWS_ACCESS_KEY_ID and AWS_CREDENTIALS_PROVIDER
mvn -Dtest=TestFlinkBasicAuthent test
```
Test sso authentication
```
aws configure sso
mvn -Dtest=TestFlinkSsoAuthent test
```
